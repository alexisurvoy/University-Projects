#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <sys/time.h>
#include <sys/mman.h>
#include <native/task.h>
#include <native/sem.h>



/*Include pour utilisation des sémaphores*/
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <native/mutex.h>



#define SENSOR_SIZE 270
#define SEM_INIT 1       /* Initial semaphore count */
#define SEM_MODE S_FIFO  /* Wait by FIFO order */


//Variables pour calcul du temps
FILE *file;
struct timeval tim;  
double start, stop, elapsed;


RT_TASK sensorTaskt;
RT_TASK computationTaskt;
RT_TASK printTaskt;

RT_SEM sem_donnee;
RT_SEM sem_obs;

RT_MUTEX mutex_donnee;
RT_MUTEX mutex_obs;

int sensorArray[SENSOR_SIZE];
int tabDonnePris=0; // tableau de donnée en cours d'utilisation 
int tabObstaclePris=0; // tableau d'obstacle en cours d'utilisation 

typedef struct obstacle obstacle;
struct obstacle
{
    int distance;
    struct obstacle *nxt;
};

typedef obstacle* linkedList;

linkedList obstacleList = NULL;


void sensorTask(long arg)
{
   	while(1){
		int i = 0;
		rt_mutex_acquire(&mutex_donnee,TM_INFINITE);		
		printf("******************** SENSOR TASK ********************\n");
		while(tabDonnePris==1){
			rt_mutex_release(&mutex_donnee);
			 rt_sem_p(&sem_donnee,TM_INFINITE);
			 rt_mutex_acquire(&mutex_donnee,TM_INFINITE);
		}
		tabDonnePris==1;
		for(i = 0; i<SENSOR_SIZE; i++){
			sensorArray[i] = rand()%16;
		}
		tabDonnePris = 0;
		rt_mutex_release(&mutex_donnee);
		usleep(100000);
	}
}
void computationTask(long arg)
{
	usleep(1);
	while(1){
		int i = 0;
		rt_mutex_acquire(&mutex_donnee,TM_INFINITE);	

		while(tabDonnePris==1){
			rt_mutex_release(&mutex_donnee);
			rt_sem_p(&sem_donnee,TM_INFINITE);
			rt_mutex_acquire(&mutex_donnee,TM_INFINITE);
		}
		tabDonnePris = 1;
		
		rt_mutex_acquire(&mutex_obs,TM_INFINITE);	
		while(tabObstaclePris==1){
		  rt_mutex_release(&mutex_obs);
		  rt_sem_p(&sem_obs,TM_INFINITE);
		  rt_mutex_acquire(&mutex_obs,TM_INFINITE);
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
					if(temp->distance==0)temp->distance=newObstacle->distance;
					else{
						while(temp->nxt != NULL)temp = temp->nxt;
						temp->nxt = newObstacle;
					}
				}
			}			
		}
		tabDonnePris = 0;
		tabObstaclePris = 0;
		
		rt_mutex_release(&mutex_obs);
		rt_mutex_release(&mutex_donnee);
		rt_sem_v(&sem_obs);		
		

		usleep(200000);
		printf("fin compute\n");
	}
}

void printTask(long arg){
	
	usleep(2);
	char buffer[50];
	char* string;
	while(1){
		rt_sem_p(&sem_obs,TM_INFINITE);
	    rt_mutex_acquire(&mutex_obs,TM_INFINITE);	 
		
		printf("******************** PRINT TASK ********************\n");		
		
		tabObstaclePris=1;
		obstacle* temp = obstacleList;
		
		while(temp->nxt != NULL){
			printf("Obstacle found ! Distance : %d\n",temp->distance);
			temp = temp->nxt;
		}
		
		tabObstaclePris = 0;

		temp= obstacleList;
		temp->nxt=NULL;
		temp->distance=0;

		rt_mutex_release(&mutex_obs);	
		
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

int main(int argc, char* argv[])
{
	int semDonnee;
	int semObs;
    int mutDonnee;
	int mutObs;
	/*initialize random seed*/
	srand(time(NULL));
	
    mutDonnee = rt_mutex_create(&mutex_donnee,"mutexDonnee");
	mutObs = rt_mutex_create(&mutex_obs,"mutexObstacle");
    semDonnee = rt_sem_create(&sem_donnee,"Semaphore Donnee",SEM_INIT,SEM_MODE);
    semObs = rt_sem_create(&sem_obs,"Semaphore Obstacle",SEM_INIT,SEM_MODE);
	
	/* Avoids memory swapping for this program */
    mlockall(MCL_CURRENT|MCL_FUTURE);
    
	
	
	gettimeofday(&tim, NULL);
	start = tim.tv_sec+(tim.tv_usec/1000000.0);
	/*
	 * Arguments: &task,
	 * name,
	 * stack size (0=default),
	 * priority,
	 * mode (FPU, start suspended, ...)
	 */
    rt_task_create(&sensorTaskt, "sensor", 0, 99, 0);
    rt_task_create(&computationTaskt, "computation", 0, 99, 0);
	rt_task_create(&printTaskt, "print", 0, 99, 0);
	
	/*
	 * Arguments: &task,
	 * task function,
	 * function argument
	 */	
    rt_task_start(&sensorTaskt, &sensorTask, NULL);
	rt_task_start(&computationTaskt, &computationTask, NULL);
	rt_task_start(&printTaskt, &printTask, NULL);

    pause();
	
    rt_task_delete(&sensorTask);
    rt_task_delete(&computationTask);
    rt_task_delete(&printTask);
	
    rt_sem_delete(&sem_donnee);
    rt_sem_delete(&sem_obs);
	
	rt_mutex_delete(&mutex_donnee);
	rt_mutex_delete(&mutex_obs);
	
	obstacle* temp1 = obstacleList;
		obstacle* temp2;
		while(temp1->nxt != NULL){
			temp2 = temp1->nxt;
			free(temp1);
			temp1=temp2;
		} 
		free(temp1);

}