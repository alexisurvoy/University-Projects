#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <signal.h>
#include <string.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <signal.h>

/*********************************** Variables globales ***************************************/
int nbVoitures;			//Le nombre de voitures a creer
int filesDArrivees[8];		//Files de messages utilisées pour les arrivées des véhicules
int filesInterieures[8];	//Files intérieures
int fileServeur;		//File pour parler avec le serveur
int shmId;			//Liste des ids des mémoires partagées : 0 = nbVoituresArrivees
key_t cle;			//Clé pour les files de messages
int semId;			//mémoire partagée 

//Files d'écoute des echangeurs
int echangeur1[2] = {0, 1};
int echangeur2[2] = {2, 3};
int echangeur3[2] = {4, 5};
int echangeur4[2] = {6, 7};

//Files d'envoi des échangeurs
int envoiEch1[2] = {7,2};
int envoiEch2[2] = {1,4};
int envoiEch3[2] = {3,6};
int envoiEch4[2] = {5,0};

//Trajets prédéterminés
int trajets[8][8];

/**********************************************************************************************/

#define TAILLE_SHM  5120	//Taille d'un segment de mémoire partagée
#define FILE_ENTREE 1		//
#define FILE_INTER  2		//

/********************************** Structures de données **************************************/
typedef struct {
	long type;		//2 : voiture, 1 : prioritaire
	int numero;		//plaque d'immatriculation		
	int actuelle;		//file actuelle sur le carrefour
	int arrivee;		//file de sortie
	int typeFile;		//entrée, sortie ou intérieure
	int echangeur;		//Echangeur actuel
} tvoiture;

typedef struct {		//Requète d'un échangeur vers le serveur-controleur
	long type;
	int numEchangeur;
	int actuelle;
	int arrivee;
	int typeFile;
} treqechangeur;

typedef struct {		//Réponse du serveur-controleur vers un échangeur
	long type;
	int prochaineFile;
	int typeFile;
	int echangeur;
	int estArrivee;		//0 : pas arrivé, 1 sinon
} trepechangeur;
/***********************************************************************************************/

/**************************************** Sémaphores *******************************************/
int P(int semId, int nSem){
	struct sembuf semBuf = {0, -1, 0};
	semBuf.sem_num = nSem;
	return semop(semId, &semBuf, 1);
}

int V(int semId, int nSem){
	struct sembuf semBuf = {0, 1, 0};
	semBuf.sem_num = nSem;
	return semop(semId, &semBuf, 1);
}
/***********************************************************************************************/

void error(const char* msg){
	int k;
	
	perror(msg);
	//Suppression des IPCs
 	for(k=0;k<8;k++){
		msgctl(filesDArrivees[k], IPC_RMID, NULL);
		msgctl(filesInterieures[k], IPC_RMID, NULL);		
	}	
	semctl(semId, 0, IPC_RMID);		
	msgctl(fileServeur, IPC_RMID, NULL);
	shmctl(shmId, IPC_RMID, NULL);
	exit(1);
}

void *serveurControleur(){
	int nbVoituresArrivees, i, nbFileAParcourir;
	char *data;	
	treqechangeur reqEchangeur;
	trepechangeur repEchangeur;
	int prochaineFile[2];
	
	printf("Création du serveur-controleur\n");

	//Création de la file de message serveur	
	if((fileServeur = msgget(cle+getpid(), IPC_CREAT | IPC_EXCL | 0600))== -1)
		error("Problème création file de messages serveur");
	
	nbVoituresArrivees = 0;

	while(nbVoituresArrivees != nbVoitures){
		//Reçoit un message d'un échangeur
		if( msgrcv(fileServeur, &reqEchangeur, sizeof(treqechangeur)-sizeof(long), 5, NULL) ){
			usleep(10); //pour l'affichage
			printf("Le serveur reçoit un message de l'échangeur n°%d\n", reqEchangeur.numEchangeur);
			
			//Traite la demande
			printf("La voiture est sur la file %d et veut aller sur la file %d\n", reqEchangeur.actuelle, reqEchangeur.arrivee);
			sleep(1);			
			//Test, est-on arrivé ?
			repEchangeur.prochaineFile = 8;			//Chiffre impossible, ne sera pas changé si la voiture part directement après etre arrivée

			if(reqEchangeur.actuelle%2){			//File impair
				if((reqEchangeur.actuelle == reqEchangeur.arrivee) || ((reqEchangeur.actuelle-1) == reqEchangeur.arrivee)){	//La voiture est arrivée
					repEchangeur.estArrivee = 1;
					nbVoituresArrivees++;
				}else
					repEchangeur.estArrivee = 0;
			}else{						//File pair
				if((reqEchangeur.actuelle == reqEchangeur.arrivee) || ((reqEchangeur.actuelle+1) == reqEchangeur.arrivee)){	//La voiture est arrivée
					repEchangeur.estArrivee = 1;
					nbVoituresArrivees++;
				}else
					repEchangeur.estArrivee = 0;		
			}
			
			if(repEchangeur.estArrivee==0){							//Si on est pas arrivé, on change de file 
				switch(reqEchangeur.numEchangeur){					//SAvoir les prochaines files sur lesquelles on peut envoyer la voiture
					case 1: prochaineFile[0] = envoiEch1[0]; prochaineFile[1] = envoiEch1[1]; break;
					case 2: prochaineFile[0] = envoiEch2[0]; prochaineFile[1] = envoiEch2[1]; break;
					case 3: prochaineFile[0] = envoiEch3[0]; prochaineFile[1] = envoiEch3[1]; break;
					case 4: prochaineFile[0] = envoiEch4[0]; prochaineFile[1] = envoiEch4[1]; break;
				}

				if(reqEchangeur.typeFile == FILE_ENTREE)				//Si on est sur une file d'entrée
					repEchangeur.typeFile = FILE_INTER;				//La prochaine est forcement une file interieure
				
				//Algorithme de choix de prochaine file	
				repEchangeur.prochaineFile = -1;					//Pas encore déterminer la prochaine file
				nbFileAParcourir = reqEchangeur.actuelle;								
				for(i=0;i<5;i++){
					nbFileAParcourir++;
					if(nbFileAParcourir > 7) nbFileAParcourir = 0;
					if(nbFileAParcourir==reqEchangeur.arrivee)
						repEchangeur.prochaineFile = prochaineFile[1];		//On tourne dans le sens positif des files (sens anti horaire) 
				}
				if(repEchangeur.prochaineFile == -1 )
					repEchangeur.prochaineFile = prochaineFile[0];			//On tourne dans l'autre sens

			}

			//Retourne la réponse à cet échangeur
			repEchangeur.type =  reqEchangeur.numEchangeur;
			repEchangeur.echangeur = quelEstMonEchangeur(repEchangeur.prochaineFile);	//Prochain échangeur

			//printf("La prochaine file de la voiture est : %d\n", repEchangeur.prochaineFile);
			if(msgsnd(fileServeur, &repEchangeur, sizeof(trepechangeur)-sizeof(long), NULL) != -1){
				printf("Le serveur réponds à l'échangeur n°%d\n", repEchangeur.type);
				sleep(1);
			}else{error("Problème envoi message serveur vers échangeur");}
		}else{error("Problème reception message serveur");}

		/*//Récupère le segment de mémoire partagée contenant le nbVoituresArrivees
		P(semId, 0);
			if((data = shmat(shmIds[0], NULL, 0)) == (char *)-1)
				error("Problème récupération data serveur");
			nbVoituresArrivees = atoi(data);
			printf("nbVoitures arrivees : %d\n", atoi(data));
			shmdt(data);
		V(semId, 0);*/
	}
	sleep(2);	
	printf("Fin du serveur\n");
	//Programme terminé, on sort
	pthread_exit(NULL);
}

void routineEchangeur(tvoiture voiture, int numEch){
	treqechangeur reqEchangeur;
	trepechangeur repServeur;
	int nbVoituresArrivees, i;
	char* data;
	char nb[100];

	if(voiture.type == 1) { 
		printf("Une voiture prioritaire arrive à l'échangeur %d : file actuelle %d => arrivée %d\n", voiture.echangeur,voiture.actuelle, voiture.arrivee); 
		sleep(1);
	}else {
		printf("Une voiture arrive à l'échangeur %d : file actuelle %d => arrivée %d\n", voiture.echangeur,voiture.actuelle, voiture.arrivee); 
		sleep(1);
	}

	//envoyer un message au serveur
	reqEchangeur.type     	  = 5;			// 5 : message au serveur
	reqEchangeur.numEchangeur = voiture.echangeur;
	reqEchangeur.actuelle 	  = voiture.actuelle;
	reqEchangeur.arrivee  	  = voiture.arrivee;
	reqEchangeur.typeFile 	  = voiture.typeFile;
	
	if(msgsnd(fileServeur, &reqEchangeur, sizeof(treqechangeur)-sizeof(long), NULL) != -1){
		printf("L'échangeur n°%d envoi une requete au serveur.\n", reqEchangeur.numEchangeur);
		sleep(1);
	}else{error("Erreur envoi message échangeur vers serveur");}

	//réception message serveur
	if(msgrcv(fileServeur, &repServeur,  sizeof(trepechangeur)-sizeof(long), numEch, NULL) != -1)
		{printf("Message reçu par l'échangeur, de la part du serveur : arrivé : %d, prochaine : %d,  type : %d\n", repServeur.estArrivee, repServeur.prochaineFile, repServeur.typeFile);
		sleep(1);}
	else{error("Erreur reception message serveur par l'échangeur");}

	//traiter réponse du serveur, envoyer la voiture dans la file indiquée par le serveur
	if(repServeur.estArrivee == 1){					//Si la voiture est arrivee
		P(semId, 0);	
			if((data = shmat(shmId, NULL, 0)) == (char *)-1)
				error("Problème récupération mémoire partagée");
			nbVoituresArrivees = atoi(data);
			nbVoituresArrivees++;				//Récupération, incrémentation et écriture du nombre de voiture arrivées
			sprintf(nb, "%d", nbVoituresArrivees);			
			strncpy(data, (char *)nb, TAILLE_SHM);
		
			printf("nbVoituresArrivees : %d sur nbVoitures : %d\n", nbVoituresArrivees, nbVoitures);
			sleep(1);
			shmdt(data);					//On détache data avant de terminer
		V(semId, 0);
	}else{								//Sinon, on change de file
		//Update de la voiture
		voiture.actuelle = repServeur.prochaineFile;
		voiture.typeFile = repServeur.typeFile;
		voiture.echangeur = repServeur.echangeur;
	
		//Envoi de la voiture au bon endroit
		if(msgsnd(filesInterieures[voiture.actuelle], &voiture, sizeof(tvoiture)-sizeof(long), NULL) != -1)
			printf("La voiture n°%d quitte l'échangeur n°%d et part sur la file %d\n", voiture.numero, numEch, voiture.actuelle); 
		sleep(1);
	}
}

void *echangeur(){
	int i, k, j, l, idFileMini, idFileMaxi, nbVoituresArrivees, size;
	int idRand[8];
	trepechangeur repServeur;
	char nb[100];

	nbVoituresArrivees = 0;

	for(l=0;l<8;l++){
		idRand[l] = (rand()%1000+1); 	//Génération id unique file de message
		for(j=0;j<8;j++){
			while(idRand[l] == idRand[j] && (j != l))
				idRand[l] = rand()%1000+1;
		}
	}

	//Création des files de messages
	for(k=0;k<8;k++){
		if((filesDArrivees[k] = msgget(cle+1024+idRand[k], IPC_CREAT | IPC_EXCL | 0600))== -1)
			error("Problème création file de messages arrivee echangeur");
		if((filesInterieures[k] = msgget(cle+1026+idRand[k], IPC_CREAT | IPC_EXCL | 0600))== -1)
			error("Problème création file de messages interieure echangeur");							
	}	

	for(i=0;i<4;i++){

		if(fork()==0){
			treqechangeur reqEchangeur;
			tvoiture voiture;
			char* data;

			printf("Création de l'échangeur n°%d\n", i+1);

			//Récupération des numéros de files
			switch(i+1){
				case 1: idFileMini = echangeur1[0]; idFileMaxi = echangeur1[1]; break;
				case 2: idFileMini = echangeur2[0]; idFileMaxi = echangeur2[1]; break;
				case 3: idFileMini = echangeur3[0]; idFileMaxi = echangeur3[1]; break;
				case 4: idFileMini = echangeur4[0]; idFileMaxi = echangeur4[1]; break;
			}

			//Attente fin du programme
			while(nbVoituresArrivees != nbVoitures){
				
				if(msgrcv(filesDArrivees[idFileMini], &voiture,  sizeof(tvoiture)-sizeof(long), -2, IPC_NOWAIT) != -1){		//-2 car comme ça les véhicules prio passeront avant	
					routineEchangeur(voiture, i+1);
				}
	
				if(msgrcv(filesDArrivees[idFileMaxi], &voiture,  sizeof(tvoiture)-sizeof(long), -2, IPC_NOWAIT) != -1){		//-2 car comme ça les véhicules prio passeront avant	;
					routineEchangeur(voiture, i+1);
				}

				if(msgrcv(filesInterieures[idFileMaxi], &voiture,  sizeof(tvoiture)-sizeof(long), -2, IPC_NOWAIT) != -1){		//-2 car comme ça les véhicules prio passeront avant	
					routineEchangeur(voiture, i+1);
				}

				if(msgrcv(filesInterieures[idFileMini], &voiture,  sizeof(tvoiture)-sizeof(long), -2, IPC_NOWAIT) != -1){		//-2 car comme ça les véhicules prio passeront avant	
					routineEchangeur(voiture, i+1);
				}

				//Récupère le segment de mémoire partagée contenant le nbVoituresArrivees
				P(semId, 0);
					nbVoituresArrivees = combienVoituresArrivees();		
				V(semId, 0);
			}
			printf("Fin de l'échangeur n°%d\n",i+1);
			exit(0);
		}
	}

	//Attente fin du programme
	while(nbVoituresArrivees != nbVoitures){
		//Récupère le segment de mémoire partagée contenant le nbVoituresArrivees
		P(semId, 0);
			nbVoituresArrivees = combienVoituresArrivees();
		V(semId, 0);
	}

	pthread_exit(NULL);
}

int combienVoituresArrivees(){
	char* data;
	int nb;
	if((data = shmat(shmId, NULL, 0)) == (char *)-1)				//Récupère le segment de mémoire partagée contenant le nbVoituresArrivees
		error("Problème récupération data echangeur");
	nb = atoi(data);
	shmdt(data);
	return nb;
}

int quelEstMonEchangeur(int file){
	if(file>=0 && file < 2)
		return 1;
	else if(file>=2 && file < 4)
		return 2;
	else if(file>=4 && file < 6)
		return 3;
	else
		return 4;
}

void *generateurDeVoitures(){
	int j, nbVoituresArrivees, ran;
	
	nbVoituresArrivees = 0;
		
	printf("Création du générateur de voitures\n");
	
	for(j=0;j<nbVoitures;j++){
		//Création de la voiture
		tvoiture voiture;
		ran=rand()%100;
		if( ran < 10)
			voiture.type = 1;
		else
			voiture.type = 2;
		voiture.numero = j+1;						//plaque d'immatriculation		
		voiture.actuelle = rand()%7;					//file actuelle de la voiture
		voiture.arrivee = rand()%7;					//file de sortie
		voiture.typeFile = FILE_ENTREE;					//Type de file : entree ou interieure
		voiture.echangeur = quelEstMonEchangeur(voiture.actuelle);	//echangeur actuel de la voiture 

		//Envoyer la voiture a la bonne file
		if( msgsnd(filesDArrivees[voiture.actuelle], &voiture, sizeof(tvoiture)-sizeof(long), NULL) == -1 )
			error("Impossible d'envoyer la voiture à l'échangeur correspondant");
		printf("Envoi de la voiture %d à l'échangeur %d sur la file %d, sortie n°%d, \n", voiture.numero, voiture.echangeur, voiture.actuelle, voiture.arrivee);
		sleep(1);	
	}
	
	//Attente que toutes les voitures soient sorties pour quitter
	while(nbVoituresArrivees != nbVoitures){
		P(semId, 0);
			nbVoituresArrivees = combienVoituresArrivees();
		V(semId, 0);
	}

	printf("Fin du générateur de voiture\n");
	pthread_exit(NULL);
}

void traitantSIGINT(int s){
	printf("\npassage par traitant SIGINT\n");
	int i;
	for(i=0;i<8;i++){
		msgctl(filesDArrivees[i], IPC_RMID, NULL);
		msgctl(filesInterieures[i], IPC_RMID, NULL);		
	}
	semctl(semId, 0, IPC_RMID);
	shmctl(shmId, IPC_RMID, NULL);
	msgctl(fileServeur, IPC_RMID, NULL);

	exit(0);
}

int main(int argc, char* argv[]){

	if(argc-1 != 1){							//s'il n'y a aucun paramètre
		fprintf(stderr, "Appel : main <nombre de voitures>\n");
		exit(1);
	}

	int k, m;
	int pth_id[3];								//0 : serveur; 1 echangeurs;  2 : generateur;
	
	nbVoitures = atoi(argv[1]);
	printf("Lancement du programme : %d voiture(s) à creer\n\n", atoi(argv[1]));

	signal(SIGINT, traitantSIGINT);						//Gestion du ctrl-c

	srand(getpid());							//Initialisation des nombres aléatoires
	
	if((cle= ftok(argv[0], '0')) == -1)					//Génération de la clé
		error("Problème Ftok");

	if((shmId = shmget(cle*12, TAILLE_SHM, 0644 | IPC_CREAT | IPC_EXCL)) == -1)
		error("Erreur création de mémoire partagée");

	if((semId = semget(cle, 1, IPC_CREAT | 0666))){				//Création du sémaphores
			V(semId, 0); 						//Initialisation à 1
	}else
		error("Erreur création sémaphores");

	pthread_create(&pth_id[0], NULL, (void *)serveurControleur, NULL);	//Création du serveur-controleur

	sleep(1);

	pthread_create(&pth_id[1], NULL, (void *)echangeur, NULL);		//Création des échangeurs		

	sleep(1);								//Sleep pour laisser le temps de créer l'infrastructure avant d'envoyer les voitures

	pthread_create(&pth_id[2], NULL, (void *)generateurDeVoitures, NULL);	//Création des voitures

	pthread_join(pth_id[0], NULL);
	pthread_join(pth_id[2], NULL);
	pthread_join(pth_id[1], NULL);
	
	printf("\nFin du programme\n");
	
 	for(k=0;k<8;k++){							//Suppression des IPCs		
		msgctl(filesDArrivees[k], IPC_RMID, NULL);
		msgctl(filesInterieures[k], IPC_RMID, NULL);			
	}
	msgctl(fileServeur, IPC_RMID, NULL);
	shmctl(shmId, IPC_RMID, NULL);
	semctl(semId, 0, IPC_RMID);	

	exit(0);
}