

public class Emprunt {
	
	private String _dateEmprunt; // Date de l'emprunt
	private Emprunteur _Emprunteur1; // Id de l'emprunteur
	private Document _Document1; // Id du document
	private String _idEmprunt;
	
	
	public Emprunt( Emprunteur Emprunteur1, Document Document1, String idEmprunt, String date){
		
		this._Document1=Document1;
		this._Emprunteur1= Emprunteur1;
		this._idEmprunt= idEmprunt;
		this._dateEmprunt= date;
	}

	public String get_dateEmprunt() {
		return _dateEmprunt;
	}

	public void set_dateEmprunt(String _dateEmprunt) {
		this._dateEmprunt = _dateEmprunt;
	}

	public Emprunt( Emprunteur Emprunteur1, Document Document1, String idEmprunt){
		_dateEmprunt= new Date().affiche();
		this._Document1=Document1;
		this._Emprunteur1= Emprunteur1;
		this._idEmprunt= idEmprunt;
		
		//this._Emprunteur1= new Emprunteur(Emprunteur1);
		//this._idEmprunt=idEmprunt;
		/*
		if(Document1.getCategorie().equalsIgnoreCase("cd")){
			_Document1=new CD((CD) Document1);
		}
		else if(Document1.getCategorie().equalsIgnoreCase("Journal")){
			_Document1=new Journal((Journal) Document1);
		}
		else if(Document1.getCategorie().equalsIgnoreCase("Livre")){
			_Document1=new Livre((Livre) Document1);
		}
		else if(Document1.getCategorie().equalsIgnoreCase("Magazine")){
			_Document1=new Magazine((Magazine) Document1);
		}
		
		// SI IL LUI RESTE DES EMPRUNTS
		if(VerifierNbDocRestant(Emprunteur1)){
			// Il a le droit a un document en moins
			Emprunteur1.setNbEmpruntRestant(Emprunteur1.getNbEmpruntRestant()-1);
		}
		
		// Si il n'y a qu'un seul document. Il faut changer la date de dispo
		//if(_Document1.getNbExemplaire()==0){
	    //	_Document1.getDateDispo().ajouteUnMois();
		//}
		*/
		
	}
	
	// Permet de tester si l'utilisateur ˆ le droit d'emprunter d'autre documents
	public boolean VerifierNbDocRestant(Emprunteur Emprunteur2){
			return Emprunteur2.getNbEmpruntRestant()!=0;
	}
	
	


	public Emprunteur get_Emprunteur1() {
		return _Emprunteur1;
	}

	public void set_Emprunteur1(Emprunteur _Emprunteur1) {
		this._Emprunteur1 = _Emprunteur1;
	}

	public Document get_Document1() {
		return _Document1;
	}

	public void set_Document1(Document _Document1) {
		this._Document1 = _Document1;
	}

	public String get_idEmprunt() {
		return _idEmprunt;
	}

	public void set_idEmprunt(String _idEmprunt) {
		this._idEmprunt = _idEmprunt;
	}

}
