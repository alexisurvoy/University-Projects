package {
	import flash.display.Stage;
	import flash.display.MovieClip;
	import flash.events.MouseEvent;
	import flash.events.Event;
	
	public class Spaceship extends MovieClip {
		
		public function Spaceship() {
			//Initialisation de la position du vaisseau
			this.x = 275;
			this.y = 340;
			
			this.addEventListener(Event.ADDED_TO_STAGE, activerEcouteurs);
			this.addEventListener(Event.REMOVED_FROM_STAGE, desactiverEcouteurs);
		}
		
		//Activation des écouteurs
		private function activerEcouteurs(evt:Event){
			this.stage.addEventListener(MouseEvent.MOUSE_MOVE, bouger);
		}
		
		//Desactivation des écouteurs
		private function desactiverEcouteurs(evt:Event){
			this.stage.removeEventListener(MouseEvent.MOUSE_MOVE, bouger);
		}
		
		//Déplacer le vaisseau
		private function bouger(evt:MouseEvent):void {
			this.x = Math.min(evt.stageX, (this.stage.stageWidth-(this.width)));
			evt.updateAfterEvent();
		}
	}
}