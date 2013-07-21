package{
	
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.TimerEvent;
	import flash.utils.Timer;
	
	public class Soucoupe extends Sprite{
		
		private var _posX:int;
		private var _posY:int;
		private var _vitesse:int;
		private var _timer:Timer;
		private var _points:int;
		
		public function Soucoupe(posX:int,posY:int,vitesse:int){
			
			this._posX = posX;
			this._posY = posY;
			this._vitesse = vitesse;
			this._points = Math.ceil(Math.random()*6)*50;
			this.addEventListener(Event.ADDED_TO_STAGE, activer);
			this.addEventListener(Event.REMOVED_FROM_STAGE, desactiver);

		}
		
		public function activer(pEvt:Event):void{
			this.x = this._posX;
			this.y = this._posY;
			this.addEventListener(Event.ENTER_FRAME, declencherSoucoupe);
		}
		
		public function declencherSoucoupe(pEvt:Event):void{
			this.x -= this._vitesse;
		}
		
		public function desactiver(pevt:Event){
			this.removeEventListener(Event.ENTER_FRAME, declencherSoucoupe);
		}
		
		/*********************
		* Getters et setters *
		**********************/
		public function getPosX():int{
			return this._posX;
		}
		public function getPosY():int{
			return this._posY;
		}
		
		public function setPosX(newX:int):void{
			this._posX = newX;
		}
		public function setPosY(newY:int):void{
			this._posY = newY;
		}
		
		public function getVitesse():int{
			return this._vitesse;
		}
		public function setVitesse(newVitesse:int):void{
			this._vitesse = newVitesse;
		}
		
		public function getPoints():int {
			return this._points;
		}
		
	}
	
}