#include <LPC23xx.H>                    /* LPC23xx/LPC24xx definitions        */

int I2C_SI;
int I2C_STA;
int I2C_STO	;
int I2C_CLR;
int I2C_AA;
int I2C_I2EN;
int buttonSatus;

int stat;

int frequence = 1000000;
int read = 0;
int write = 0;

int verrou = 0;

void led210_turn_on(void){
 	FIO2CLR = 1<<10;
}

void led210_turn_off(void){
 	FIO2SET = 1<< 10;
}

void affiche8its(char val);
int lirebouton(void);
void ecriture(void);


__irq void isr(void){
//	VICIntEnable &= ~(1<<9);	

//
//	if(I20CONSET & I2C_SI)
//	{
//		// soit allumage des bouton 
//		// soit affichage 8 bits
//		//affiche8its(1);
//		
//		stat = I20STAT;
//		
//		if( read ==1)
//		{
//			switch(I20STAT){
//	
//				case (0x08) : //start bit
//					I20DAT = 0xC0; /* PCA address */
//					I20CONCLR = I2C_SI | I2C_STA; /* clear SI and STA */
//					break;
//				case(0x10) :
//					 I20DAT = 0xC0 | 0x1; /* PCA address + commande de lecture */
//					 I20CONCLR = I2C_SI | I2C_STA; /* clear SI and STA */ 
//					break;
//				case(0x18): //slave address ack
//					I20DAT = 0x0; /* select register Input0  pas de AI*/
//					I20CONCLR = I2C_SI; /* clear SI */
//					I20CONSET = I2C_STA;
//					break;
//				case(0x20): // slave address nack
//					I20DAT = 0xC0; /* PCA address */
//					break;
//				case(0x28): // data ack
//					buttonSatus = I20DAT;
//					I20CONSET = I2C_STO; /* send STOP */
//					I20CONCLR = I2C_SI; /* clear SI */
//					break ;
//				default :
//					break;
//			}
//		}  
		
//		if( write == 1)
//		{
//
//			switch(I20STAT){
//					
//				case (0x08) : //start bit
//					I20DAT = 0xC0; /* PCA address */
//					I20CONCLR = I2C_SI | I2C_STA; /* clear SI and STA */
//					break;
//				case(0x18): //slave address ack
//					I20DAT = 0x18; /* select register LS2 et AI*/
//					I20CONCLR = I2C_SI; /* clear SI */
//					break;
//				case(0x20): // slave address nack
//					//I2DAT = I2CAddress;
//					break;
//				case(0x28): // data ack
//					I20DAT =0; 
//					if(!(buttonSatus & (1<<3))) I20DAT |= 0x40;
//					if(!(buttonSatus & (1<<2))) I20DAT |=0x10; 
//					if(!(buttonSatus & (1<<1))) I20DAT |= 0x4;
//					if(!(buttonSatus & 1)) I20DAT |= 0x1;
//
//					I20CONSET = I2C_STO; /* send STOP */
//					break ;
//				default :
//					break;
//			}  
//		
//		
//		
//		}  	   
//
// 		
//		
//	}
//	I20CONCLR = I2C_SI; //  clear interrupt SI
//	VICVectAddr = 0;	 // on acquitte le vic
//	VICIntEnable |= (1<<9); //reactive les interuptions
}

void init(void){

//Init de ICONSET
	I2C_STA = 1<<5;
	I2C_SI = 1<<3;
	I2C_STO = 1<<4;
	//I2C_CLR = 0;
	I2C_AA= 1<<2 ;
	I2C_I2EN = 1<<6;
	
	PCONP|= 1<<7;
	PCLKSEL0 &=~(3<<14);
	PINSEL1 &= ~(3<<22|3<<24);
	PINSEL1	|=01<<22 | 01<<24;
	I20SCLH = 90;
	I20SCLL = 90;
	I20CONCLR = I2C_AA | I2C_SI | I2C_STO | I2C_STA | I2C_I2EN;
	I20CONSET = I2C_I2EN;

	//active les interruption sur SI


	//VICVectAddr9 = (int)isr;
	//VICIntEnable |= (1<<9);
	//VICIntSelect &= ~(1<<9);   
 }




int main(void){

	//Init de ICONSET
	init();
	 while(1)
	 {	 		
		//lirebouton();
		//ecriture();
		affiche8its(10);	
	  }

  return 0;
}

void affiche8its(char val)
{
	I20CONCLR = I2C_SI | I2C_STA; /* clear SI and STA */

	I20CONSET = I2C_STA; /* send START */
	
	while (!(I20CONSET & I2C_SI)); /* Wait for START */
	/* check status to handle error */
	I20DAT = 0xC0; /* PCA address */
	I20CONCLR = I2C_SI | I2C_STA; /* clear SI and STA */

	while (!(I20CONSET & I2C_SI)); /* Wait for ADDRESS send */
	/* check status to handle error (nack)*/
	I20DAT = 0x18; /* select register LS2 et AI*/
	I20CONCLR = I2C_SI; /* clear SI */
	
	while (!(I20CONSET & I2C_SI)); /* Wait for DATA send */
	/* check status to handle error (nack)*/
	
	//4 bits de poids faible
	I20DAT =0; 
	if(val & (1<<3)) I20DAT |= 0x01;
	if(val & (1<<2)) I20DAT |=0x4; 
	if(val & (1<<1)) I20DAT |= 0x10;
	if(val & 1) I20DAT |= 0x40;
	I20CONCLR = I2C_SI; /* clear SI */

	//4 bits de poid fort
	while (!(I20CONSET & I2C_SI)); /* Wait for DATA send */
	/* check status to handle error (nack)*/
	I20DAT =0; 
	if(val & (1<<4)) I20DAT |= 0x01;
	if(val & (1<<5)) I20DAT |=0x4; 
	if(val & (1<<6)) I20DAT |= 0x10;
	if(val & (1<<7)) I20DAT |= 0x40;
	I20CONCLR = I2C_SI; /* clear SI */
	
	while (!(I20CONSET & I2C_SI)); /* Wait for DATA send */

	I20CONSET = I2C_STO; /* send STOP */
	I20CONCLR = I2C_SI; /* clear SI */
	while (I20CONSET & I2C_STO);

}

int lirebouton(void)
{
	// lecture 
	while(verrou == 1);		//Attente verrou dispo

	verrou = 1;
	read = 1;

	I20CONCLR = I2C_SI | I2C_STA; /* clear SI and STA */

	I20CONSET = I2C_STA; /* send START */
	
	

	while (!(I20CONSET & I2C_SI)); /* Wait for START */
	/* check status to handle error */
	I20DAT = 0xC0; /* PCA address */
	I20CONCLR = I2C_SI | I2C_STA; /* clear SI and STA */

	while (!(I20CONSET & I2C_SI)); /* Wait for ADDRESS send */
	/* check status to handle error (nack)*/
	I20DAT = 0x0; /* select register Input0  pas de AI*/
	I20CONCLR = I2C_SI; /* clear SI */
	while (!(I20CONSET & I2C_SI)); /* Wait for START */

	I20CONCLR = I2C_SI; /* clear SI */

	I20CONSET = I2C_STA; /* send START */
	
	while (!(I20CONSET & I2C_SI)); /* Wait for START */
	/* check status to handle error */
	I20DAT = 0xC0 | 0x1; /* PCA address + commande de lecture */
	I20CONCLR = I2C_SI | I2C_STA; /* clear SI and STA */  
	while (!(I20CONSET & I2C_SI)); /* Wait for START */
	I20CONCLR = I2C_SI; /* clear SI */
	//wait for data

	while (!(I20CONSET & I2C_SI)); /* Wait for DATA send */
	buttonSatus = I20DAT;
	I20CONSET = I2C_STO; /* send STOP */
	I20CONCLR = I2C_SI; /* clear SI */
	while (I20CONSET & I2C_STO);

	verrou = 0;
	read = 0;

	return buttonSatus; 
}

void ecriture()
{
	// lecture 
	while(verrou == 1);		//Attente verrou dispo

	verrou = 1;
	write = 1;

	//affichage des boutons sur les leds
	//ecriture
	I20CONCLR = I2C_SI | I2C_STA; /* clear SI and STA */

	I20CONSET = I2C_STA; /* send START */
	
	while (!(I20CONSET & I2C_SI)); /* Wait for START */
	/* check status to handle error */
	I20DAT = 0xC0; /* PCA address */
	I20CONCLR = I2C_SI | I2C_STA; /* clear SI and STA */

	while (!(I20CONSET & I2C_SI)); /* Wait for ADDRESS send */
	/* check status to handle error (nack)*/
	I20DAT = 0x18; /* select register LS2 et AI*/
	I20CONCLR = I2C_SI; /* clear SI */
	
	while (!(I20CONSET & I2C_SI)); /* Wait for DATA send */
	/* check status to handle error (nack)*/
	
	//4 bits de poids faible
	I20DAT =0; 
	if(!(buttonSatus & (1<<3))) I20DAT |= 0x40;
	if(!(buttonSatus & (1<<2))) I20DAT |=0x10; 
	if(!(buttonSatus & (1<<1))) I20DAT |= 0x4;
	if(!(buttonSatus & 1)) I20DAT |= 0x1;  

	I20CONCLR = I2C_SI; /* clear SI */
	while (!(I20CONSET & I2C_SI)); /* Wait for DATA send */

	I20CONSET = I2C_STO; /* send STOP */
	I20CONCLR = I2C_SI; /* clear SI */
	while (I20CONSET & I2C_STO);

	verrou = 0;
	write=0;

}

