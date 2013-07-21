#include <LPC23xx.H>                    /* LPC23xx/LPC24xx definitions        */



void led210_init(void){
 	// Power control
 	//GPIO cannot be turned off 
 	// CLOCK
 	PCLKSEL1 &= ~(0x3 << 2); //3:2 = 0b00	(CCLK / 4)
 	// PIN :
	 // function select for P2.10 (GPIO) in PINSEL4	(PINSEL4[21..20] = 0b00	)		 (RW)
	 PINSEL4 &= ~(3 << 20) ;
	 // connect mode selection for pin  (00 = pull up resistor selected) (RW)
	 PINMODE4 &= ~(3 << 20) ;
 	//PIO
	 // direction mode selection : output 	= 1 et input =  0	(out selected)	(R/W)
	 FIO2DIR |= (1 <<10);
	 // to allowed read an write on the selected pin	  (0 = enable)
	 FIO2MASK &= ~(1 <<10); 
 }

void led210_turn_on(void){
 	FIO2CLR = 1<<10;
}

void led210_turn_off(void){
 	FIO2SET = 1<< 10;
}


int main(void){
	int i;

	led210_init();
	while (1){
	led210_turn_on();
	for (i = 0; i < 1000000; i++) ;
 	led210_turn_off();
 	for (i = 0; i <1000000; i++);
	}

  return 0;
}
