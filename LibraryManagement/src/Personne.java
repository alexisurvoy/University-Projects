/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pierre-antoinemontez
 */
abstract class Personne {

    // Attributs de la classe
    protected String _id; // EX HO019
    protected String _login; // Login pour la connection
    protected String _nom; // Nom de la personne
    protected String _prenom; // Prenom de la personne
    protected String _mdp; // Mot de passe
    protected String _mail; // mail de la personne
    protected String _type; // Emprunteur ou bibliothŽcaire


    // Constructeur par dï¿½fault
    protected Personne(){
        this._id="noID";
        this._login="Pas de login";
        this._nom="Pas de nom";
        this._prenom="Pas de prenom";
        this._mdp="Pas de mot de passe";
        this._mail="Mail";
        this._type="Pas de type";
        
    }

    // Constructeur surchargï¿½
    protected Personne(String id, String login, String nom, String prenom,String mdp, String mail, String type){
        this._id=id;
        this._login=login;
        this._nom=nom;
        this._prenom=prenom;
        this._mdp=mdp;
        this._mail=mail;
        this._type=type;

    }

   // Methode qui affiche les info de la personne
   protected String affiche(){
      return  " Id: "+this._id+"\n Prenom : "+this._prenom+"\n Nom: "+this._nom+"\n Mail: "+ this._mail+"\n";
   }
   
   protected String racc(){
	      return  " Id: "+this._id+" Prenom : "+this._prenom+" Nom: "+this._nom;
	   }
   

   // Fonction qui permet de rechercher un document
   public void rechercherDoc(){

   }

	public String getId() {
		return _id;
	}
	
	public void set_id(String _id) {
		this._id = _id;
	}
	
	public String get_nom() {
		return _nom;
	}
	
	public void set_nom(String _nom) {
		this._nom = _nom;
	}
	
	public String get_prenom() {
		return _prenom;
	}
	
	public String get_login() {
		return _login;
	}
	
	public void set_prenom(String _prenom) {
		this._prenom = _prenom;
	}
	
	public String get_mdp() {
		return _mdp;
	}
	
	public void set_mdp(String _mdp) {
		this._mdp = _mdp;
	}
	
	public String get_mail() {
		return _mail;
	}
	
	public void set_mail(String _mail) {
		this._mail = _mail;
	}
	
	public void set_login(String _login) {
		this._login = _login;
	}
	   
	public String get_type() {
		return _type;
	}
	
	public void set_type(String type) {
		this._type = type;
	}

}