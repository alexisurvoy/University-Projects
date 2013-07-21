#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <pthread.h>


#define SENSOR_SIZE 270

//Variables pour calcul du temps
FILE *file;
struct timeval tim;  
double start, stop, elapsed;

int sensorArray[SENSOR_SIZE];
pthread_t tid[3];
pthread_mutex_t mutexTabDonnee;
pthread_cond_t condTabDonnee;
int tabDonnePris=0; // tableau de donnée en cours d'utilisation 


pthread_mutex_t mutexTabObstacle;
pthread_cond_t condTabObstacle;
int tabObstaclePris=0; // tableau d'obstacle en cours d'utilisation 

typedef struct obstacle obstacle;
struct obstacle
{
    int distance;
    struct obstacle *nxt;
};

typedef obstacle* linkedList;

linkedList obstacleList = NULL;

void * sensorTask(){
	while(1){
		int i = 0;
		pthread_mutex_lock(&mutexTabDonnee);	
		while(tabDonnePris==1){
		   pthread_cond_signal(&condTabDonnee);
		   pthread_cond_wait(&condTabDonnee, &mutexTabDonnee);
		}
		printf("******************** SENSOR TASK ********************\n");
		tabDonnePris==1;
		for(i = 0; i<SENSOR_SIZE; i++){
			sensorArray[i] = rand()%16;
		}
		tabDonnePris = 0;
		pthread_cond_signal(&condTabDonnee);
		pthread_mutex_unlock(&mutexTabDonnee);	
		usleep(100000);
	}
}

void * computationTask(){
	usleep(1);
	
	while(1){
		int i = 0;	
		pthread_mutex_lock(&mutexTabDonnee);	
		while(tabDonnePris==1){
		   pthread_cond_signal(&condTabDonnee);
		   pthread_cond_wait(&condTabDonnee, &mutexTabDonnee);
		}
		tabDonnePris = 1;
		pthread_mutex_lock(&mutexTabObstacle);	
		while(tabObstaclePris==1){
		   pthread_cond_signal(&condTabObstacle);
		   pthread_cond_wait(&condTabObstacle, &mutexTabObstacle);
		}
		tabObstaclePris = 1;
		printf("***************** COMPUTATION TASK ******************\n");

		for(i = 0; i<SENSOR_SIZE; i++){
			//If the obstacle is farther than 7meters
			if(sensorArray[i] > 7){

				obstacle* newObstacle = malloc(sizeof(obstacle));
				newObstacle->distance = sensorArray[i];
				
				//Add the new obstacle at the end of the list
				newObstacle->nxt = NULL;
				
				if(obstacleList == NULL) obstacleList = newObstacle;
				else{
					obstacle* temp = obstacleList;
					while(temp->nxt != NULL)
					{
						temp = temp->nxt;
					}
					temp->nxt = newObstacle;
				}
			}			
		}
		tabDonnePris = 0;
		tabObstaclePris = 0;
		
		pthread_cond_signal(&condTabDonnee);
		pthread_cond_signal(&condTabObstacle);
		pthread_mutex_unlock(&mutexTabObstacle);
		pthread_mutex_unlock(&mutexTabDonnee);
		
		usleep(200000);
	}
}

void * printTask(){
	char buffer[50];
	char* string;

	usleep(2);
	
	while(1){
	
		pthread_mutex_lock(&mutexTabObstacle);	
		while(tabObstaclePris==1){
		   pthread_cond_signal(&condTabObstacle);
		   pthread_cond_wait(&condTabObstacle, &mutexTabObstacle);
		}
		printf("******************** PRINT TASK ********************\n");
		tabObstaclePris=1;
		obstacle* temp = obstacleList;
		while(temp->nxt != NULL){
			printf("Obstacle found ! Distance : %d\n",temp->distance);
			temp = temp->nxt;
		}
		
		tabObstaclePris = 0;
		obstacleList=NULL;
		
		
		pthread_cond_wait(&condTabObstacle, &mutexTabObstacle);
		pthread_cond_signal(&condTabObstacle);
		pthread_mutex_unlock(&mutexTabObstacle);
		
		//Fin de la simulation, calcul du temps d'exécution
		gettimeofday(&tim, NULL);
		stop = tim.tv_sec+(tim.tv_usec/1000000.0);
		elapsed = stop-start;
	
		/********** Ecriture dans le fichier **********/
		file = fopen("output", "a");
   
		string = "Lap time : ";
		sprintf(buffer, "%lf", elapsed);
   
		fputs(string, file);
		fputs(buffer, file);
		fputs("\n", file);
   
		fclose(file);   
		/********** Fin ecriture dans le fichier **********/
		
		printf("Time : %lf\n", elapsed);
	
		gettimeofday(&tim, NULL);
		start = tim.tv_sec+(tim.tv_usec/1000000.0);
	}
}

int main(void){
	int num = 0;

	/*initialize random seed*/
	srand(time(NULL));
  
	pthread_mutex_init(&mutexTabDonnee,0);
	pthread_mutex_init(&mutexTabObstacle,0);
   
	pthread_cond_init(&condTabDonnee,0);
	pthread_cond_init(&condTabObstacle,0);
	
	gettimeofday(&tim, NULL);
	start = tim.tv_sec+(tim.tv_usec/1000000.0);
	
	//creation des threads
	num=0;
    pthread_create(tid+num,0,(void *(*)())sensorTask,NULL);
	num=1;
    pthread_create(tid+num,0,(void *(*)())computationTask,NULL);
	num=2;
    pthread_create(tid+num,0,(void *(*)())printTask,NULL);
	

    //attend la fin de toutes les threads
	for(num=0;num<3;num ++) pthread_join(tid[num],NULL);
	
    pthread_mutex_destroy(&mutexTabDonnee);
    pthread_mutex_destroy(&mutexTabObstacle);
   
	pthread_cond_destroy(&condTabDonnee);
	pthread_cond_destroy(&condTabObstacle);
 
	return 0;	
}    
