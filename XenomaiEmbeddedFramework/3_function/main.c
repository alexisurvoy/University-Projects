#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>

#define SENSOR_SIZE 270

int sensorArray[SENSOR_SIZE];

typedef struct obstacle obstacle;
struct obstacle
{
    int distance;
    struct obstacle *nxt;
};

typedef obstacle* linkedList;

linkedList obstacleList = NULL;

void sensorTask(){
	int i = 0;
	
	printf("******************** SENSOR TASK ********************\n");
	
	for(i = 0; i<SENSOR_SIZE; i++){
		sensorArray[i] = rand()%16;
	}
	usleep(100000);
	
}

void computationTask(){
	int i = 0;	
	
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
	usleep(200000);
}

void printTask(){

	printf("******************** PRINT TASK ********************\n");
	
	obstacle* temp = obstacleList;
	while(temp->nxt != NULL){
		printf("Obstacle found ! Distance : %d\n",temp->distance);
		temp = temp->nxt;
	}
	
}

int main(int argc, char* argv[]){
   
	struct timeval tim;  
	double start,stop,elapsed;
	FILE *file;
	char* string;
   
	/*initialize random seed*/
	srand(time(NULL));

	while(1){
	   char buffer[50];
   
	   gettimeofday(&tim, NULL);
	   start = tim.tv_sec+(tim.tv_usec/1000000.0);
	   
	   sensorTask();
	   computationTask();
	   printTask();
	   
	   gettimeofday(&tim, NULL);
	   stop = tim.tv_sec+(tim.tv_usec/1000000.0);
	   
	   elapsed = stop-start;
	   
	   /********** Ecriture dans le fichier **********/
	   file = fopen("output", "a");
	   if(file == NULL)	return -1;
	   
	   string = "Lap time : ";
	   sprintf(buffer, "%lf", elapsed);
	   
	   fputs(string, file);
	   fputs(buffer, file);
	   fputs("\n", file);
	   
	   fclose(file);   
	   /********** Fin ecriture dans le fichier **********/
	   
	   printf("Writing : %lf\n", elapsed);
	   
	}

	return 0;
}    
