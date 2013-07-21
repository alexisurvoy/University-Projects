package {
	import flash.display.Sprite;
	import flash.events.MouseEvent;
	import flash.events.Event;
	import flash.text.TextField;
	import flash.utils.Timer;
	import flash.events.TimerEvent;

	import Alien;
	import Fortification;
	import Missile;
	import Spaceship;
	import Soucoupe;

	public class Niveau extends Sprite {
		/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Données membres*/
		//Objets
		private var _vaisseau:Spaceship;
		private var _sacAMissiles:Sprite;
		private var _sacAMissilesAliens:Sprite;
		private var _lesFortifications:Sprite;
		private var _lesAliens:Sprite;
		private var _alien1:Alien;
		private var _soucoupe:Soucoupe;
		
		//Propriétés
		private var _sens:Boolean;
		private var _nbVies:int = 3;
		private var _score:int;
		private var _valeurDestruction:Number = .5;
		private var _nombreMissilesAliens:int = 5;
		private var _vitesseMissileAliens:int = 500;
		
		//Textfields
		private var _chpTxtVies:TextField;
		private var _chpNbVies:TextField;
		private var _chpTxtScore:TextField;
		private var _chpScore:TextField;
		private var _chpTxtLvl:TextField;
		private var _chpLvl:TextField;
		
		//Timer
		private var _timer:Timer;
		private var _timer2:Timer;
		
		/*////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Constructeur*/
		public function Niveau(pNbVies:int, pScore:int, pLvl:int) {
			//Création du vaisseau
			this._vaisseau = new Spaceship();
			this._vaisseau.scaleX = .4;
			this._vaisseau.scaleY = .4;
			this.addChild(_vaisseau);
			
			//Création des aliens
			creerAlien();
			
			//Créations des fortifications
			this._lesFortifications = new Sprite();

			for (var i:int=0; i<5; i++) {
				var fortification:Fortification = new Fortification((i*100)+60, 300);
				this._lesFortifications.addChild(fortification);
			}

			this.addChild(this._lesFortifications);
			
			//Création du conteneur à missiles spaceship
			this._sacAMissiles = new Sprite();
			this.addChild(_sacAMissiles);
			
			//Création du conteneur à missiles aliens
			this._sacAMissilesAliens = new Sprite();
			this._lesAliens.addChild(_sacAMissilesAliens);

			//Création des champs d'informations
			this._chpTxtVies = creerUneInfo("Vies :", 20, 375);
			this.addChild(_chpTxtVies);
			this._chpNbVies = creerUneInfo(String(pNbVies), 55, 375);
			this.addChild(_chpNbVies);

			this._chpTxtScore = creerUneInfo("Score :", 225, 375);
			this.addChild(_chpTxtScore);
			this._chpScore = creerUneInfo(String(pScore), 265, 375);
			this.addChild(_chpScore);

			this._chpTxtLvl = creerUneInfo("Niveau :", 420, 375);
			this.addChild(_chpTxtLvl);
			this._chpLvl = creerUneInfo(String(pLvl), 480, 375);
			this.addChild(_chpLvl);
			
			//Écouteurs d'ajout ou suppression du niveau sur la scène
			this.addEventListener(Event.ADDED_TO_STAGE, activer);
			this.addEventListener(Event.REMOVED_FROM_STAGE, desactiver);
		}
		
		/*/////////////////////////////////////////////////////////////////////////////////////////////////////Méthodes de création d'éléments*/
		//Création des aliens
		function creerAlien() {
			// Creation d'Alien
			var posX:int;
			var posY:int = 150;
			this._lesAliens = new Sprite();
			this._sens = true;
			var alien:Alien;

			for (var i:int=0; i<5; i++) {
				posX=0;

				for (var j:int=0; j<6; j++) {
					if (i==0||i==1) {
						alien = new Alien(this, posX, posY, 10, 10, _vaisseau);
						this._lesAliens.addChild(alien);
					} else if (i==2||i==3) {
						alien = new Alien(this, posX, posY, 20, 10, _vaisseau);
						this._lesAliens.addChild(alien);
					} else {
						alien = new Alien(this, posX, posY, 40, 10,_vaisseau);
						this._lesAliens.addChild(alien);
					}
					
					posX += 50;
				}
				
				posY -= 30;
			}
			
			this._lesAliens.x = 100;
			this._lesAliens.y = 10;
			this.addChild(_lesAliens);

			this._lesAliens.addEventListener(Event.ENTER_FRAME,avancer);
		}
		
		//Création d'un champ d'information
		private function creerUneInfo(ptext:String, px:int, py:int):TextField {
			var txtField = new TextField();
			txtField.text = ptext;
			txtField.textColor = 0xFFFFFF;
			txtField.x = px;
			txtField.y = py;
			return txtField;
		}
		/*/////////////////////////////////////////////////////////////////////////////////////////////Timers à insérer au démarrage du niveau*/
		/*----------------------------------------------------------------------------------------------------------------------------------
			//Timer créateur de soucoupe
			trace(int(Math.random()*50+1));
			var aleatoireSoucoupe:int=int(Math.random()+1*50);
			this._timer=new Timer(30000,0);
			this._timer.start();
			this._timer.addEventListener(TimerEvent.TIMER, creerSoucoupe);

			//Timer lancer un missile Alien
			var aleatoire:int=Math.random()*25+1;
			this._timer2=new Timer(aleatoire, 50);
			this._timer2.start();
			this._timer2.addEventListener(TimerEvent.TIMER_COMPLETE, lancerMissileAlien);
			----------------------------------------------------------------------------------------------------------------------------------*/
		/*//////////////////////////////////////////////////////////////////////////////////////////////////////////////Gestion des événements*/
		//Activation des écouteurs
		private function activer(evt:Event):void {
			this.stage.addEventListener(MouseEvent.CLICK, lancerMissile);
		}

		//Désactivation des écouteurs
		private function desactiver(evt:Event):void {
			this.stage.removeEventListener(MouseEvent.CLICK, lancerMissile);
		}
		
		//Insérer une soucoupe sur la scène
		private function creerSoucoupe(pEvt:Event):void{
			//Création de la soucoupe
			this._soucoupe = new Soucoupe(600,15,3);
			this.stage.addChild(this._soucoupe);
		}
		
		//Tester la suppression d'un élément
		private function testerSupression(pEvt:Event):void{
			var missile:Missile = pEvt.currentTarget as Missile;
		
			//Supression des missiles s'ils dépassent la zone de jeu
			if(missile.y< 0){
				this._sacAMissiles.removeChild(missile);
				pEvt.currentTarget.removeEventListener(Event.ENTER_FRAME, testerSupression);
				missile = null;
			}else if(missile.y> 350){
				this._sacAMissilesAliens.removeChild(missile);
				pEvt.currentTarget.removeEventListener(Event.ENTER_FRAME, testerSupression);
				missile = null;
			}else{
				
				//Collisions fortifications
				for(var i:int = _lesFortifications.numChildren-1; i>=0;i--){
				
					//Collision d'un missile du spaceship
					if(missile.hitTestObject(_lesFortifications.getChildAt(i)) && missile.getSens()=="HAUT"){
						pEvt.currentTarget.removeEventListener(Event.ENTER_FRAME, testerSupression);
						this._sacAMissiles.removeChild(missile);
						break;
					}else if(missile.hitTestObject(_lesFortifications.getChildAt(i)) && missile.getSens()=="BAS"){//Colision d'un missile alien
					
						var fortif:Fortification = Fortification(this._lesFortifications.getChildAt(i));
						fortif.detruire(this._valeurDestruction);
					
						if(fortif.getHauteur() <= 0){
							this._lesFortifications.removeChild(fortif);
							fortif=null;
						}
						pEvt.currentTarget.removeEventListener(Event.ENTER_FRAME, testerSupression);
						this._sacAMissilesAliens.removeChild(missile);
						break;
					}
				}
			
				//Collisions aliens
				for(var j:int = 0; j<_lesAliens.numChildren;j++){
					if(missile.hitTestObject(_lesAliens.getChildAt(j)) && missile.getSens()=="HAUT"){
						this._sacAMissiles.removeChild(missile);
						
					
						var alien2:Alien = _lesAliens.getChildAt(j) as Alien;
						_chpScore.text = String(int(_chpScore.text)+alien2.getPoint());
						this._lesAliens.removeChildAt(j);
						pEvt.currentTarget.removeEventListener(Event.ENTER_FRAME, testerSupression);
						break;
					}
				}
			
			
				//Collisions spaceship
				for(var k:int = 0; k<_lesAliens.numChildren;k++){
					if(missile.hitTestObject(_vaisseau) && missile.getSens()=="BAS"){
					
						this._sacAMissilesAliens.removeChild(missile);
						if(this._nbVies == 0){
							this.removeChild(_vaisseau);
							pEvt.currentTarget.removeEventListener(Event.ENTER_FRAME, testerSupression);
						}else{
							this._nbVies--;
							//_chpScore.text = String(int(_chpScore.text)+alien2.getPoint());
							this._chpNbVies.text = String(this._nbVies);
						}
						break;
					}
				}
				
				/* Collision missile spaceship / alien probleme
				
				for(var l:int = 0; l<this._sacAMissilesAliens.numChildren; l++){
					if(missile.hitTestObject(this._sacAMissilesAliens.getChildAt(l))){
						pEvt.currentTarget.removeEventListener(Event.ENTER_FRAME, testerSupression);
						this._sacAMissiles.removeChild(Missile(pEvt.currentTarget));
						this._sacAMissilesAliens.removeChild(this._sacAMissilesAliens.getChildAt(l));
						break;
					}
				}*/
				// trace(this._soucoupe+ "soucoupe");
				 //trace(missile);
				if(this._soucoupe&&missile.hitTestObject(this._soucoupe)){
					this._sacAMissiles.removeChild(missile);
					_chpScore.text = String(int(_chpScore.text)+this._soucoupe.getPoints());
					this.stage.removeChild(this._soucoupe);
					trace(this._soucoupe.parent);
					pEvt.currentTarget.removeEventListener(Event.ENTER_FRAME, testerSupression);
				}
			
			}
		}

		//Gérer le déplacement du conteneur d'aliens
		public function avancer(e:Event):void {
			var i:int;
			var nombreEnfants:int = _lesAliens.numChildren;
			if (_sens==true) {
				//trace(this._lesAliens.getChildAt(1).x);
				if (this._lesAliens.x>30) {
					this._lesAliens.x-=1;
				} else {
					_sens=false;
					this._lesAliens.y+=10;
				}
			} else {
				if (this._lesAliens.x + (this._lesAliens.width) < 480 ) {
					this._lesAliens.x+=1;
				} else {
					_sens=true;
						this._lesAliens.y+=10;
				}
			}
		}
		
		//Lancer un missile du vaisseau
		private function lancerMissile(evt:Event):void {
			if(this._sacAMissiles.numChildren <5){
				var mis:Missile;
				mis = new Missile(_vaisseau, 0, 0, "HAUT", "White");
				mis.x = (_vaisseau.width/2)+_vaisseau.x;
				mis.y = _vaisseau.y;
				mis.addEventListener(Event.ENTER_FRAME, testerSupression);
			
				this._sacAMissiles.addChild(mis);
			}
		}
		
		//Lancer un missile Alien 
		public function lancerMissileAlien(e:Event):void{
			if(e.type == "timerComplete"){
				for(var i:int = 0; i<this._nombreMissilesAliens; i++){
					var rand:int = Math.floor(Math.random()*this._lesAliens.numChildren);
					var alien:Alien = this._lesAliens.getChildAt(rand) as Alien;
					
					var mis:Missile;
					
					switch(alien.getPoint()){
						case 10 :
							mis = new Missile(alien, alien.x, alien.y, "BAS", "Yellow");
							mis.x = (alien.width/2)+alien.x;
							mis.y = alien.y;
							mis.addEventListener(Event.ENTER_FRAME, testerSupression);
					
							this._sacAMissilesAliens.addChild(mis);
							break;
					
						case 20 : 
							mis = new Missile(alien, alien.x, alien.y, "BAS", "Blue");
							mis.x = (alien.width/2)+alien.x;
							mis.y = alien.y;
							mis.addEventListener(Event.ENTER_FRAME, testerSupression);
					
							this._sacAMissilesAliens.addChild(mis);
							break;
					
						case 40 : 
							mis = new Missile(alien, alien.x, alien.y, "BAS", "Red");
							mis.x = (alien.width/2)+alien.x;
							mis.y = alien.y;
							mis.addEventListener(Event.ENTER_FRAME, testerSupression);
					
							this._sacAMissilesAliens.addChild(mis);
							break; 
					}	
				}
			}
			
			//Timer lancer un missile Alien
			var aleatoire:int=Math.random()*this._vitesseMissileAliens;
			this._timer=new Timer(50, 0);
			this._timer.start();
			this._timer.addEventListener(TimerEvent.TIMER_COMPLETE, lancerMissileAlien);
		}
		
	}
}