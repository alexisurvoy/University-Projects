/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author pierre-antoinemontez
 */
abstract class Document {


    private String _id; // Nombre d'exemplaire d'un document.
    private String _categorie;
    private String _titre;
    private String _dateEdition;
    private Date _dateArrivee;
    private int _nbExemplaire;
    private Date _dateDispo;


    // Constructeur par défault
    protected Document(){
                this._id = "NoID";
                this._categorie = "NoCategorie";
                this._titre = "Pas de titre";
                this._dateEdition = "Pas de date d'édition";
                this._dateArrivee = new Date();
                this._nbExemplaire = 1;
                this._dateDispo = new Date();
    }

    // Constructeur avec tous les arguments
    protected Document(String id, String categorie, String titre, String dateEdition, String dateArrivee, int nbExemplaire, String dateDispo){
                this._id = id;
                this._categorie = categorie;
                this._titre = titre;
                this._dateEdition = dateEdition;
                this._dateArrivee = new Date(dateArrivee);
                this._nbExemplaire = nbExemplaire;
                this._dateDispo = new Date(dateDispo);

    }
    
    
    protected Document(Document D){
        this._id = D._id;
        this._categorie = D._categorie;
        this._titre = D._titre;
        this._dateEdition = D._dateEdition;
        this._dateArrivee = new Date(D._dateArrivee);
        this._nbExemplaire = D._nbExemplaire;
        this._dateDispo = new Date(D._dateDispo);

}
    

  // Fonction affiche
  protected String affiche(){
      return  " Id: "+this.getId()+"\n Catégorie : "+this.getCategorie()+"\n Titre: "+this.getTitre()+"\n Date d'édition: "+ this.getDateEdition()+"\n Date d'arrivée: "+ this.getDateArrivee() +"\n Nombre d'exemplaires: "+ this.getNbExemplaire()+"\n Date de dispo: "+this.getDateDispo()+"\n";
   }
  

  protected String racc(){
  	return " Id: "+this.getId()+" Catégorie : "+this.getCategorie()+" Titre: "+this.getTitre();
  }
  

    /**
     * @return the _id
     */
    public String getId() {
        return _id;
    }

    /**
     * @param id the _id to set
     */
    public void setId(String id) {
        this._id = id;
    }

    /**
     * @return the _categorie
     */
    public String getCategorie() {
        return _categorie;
    }

    /**
     * @param categorie the _categorie to set
     */
    public void setCategorie(String categorie) {
        this._categorie = categorie;
    }

    /**
     * @return the _titre
     */
    public String getTitre() {
        return _titre;
    }

    /**
     * @param titre the _titre to set
     */
    public void setTitre(String titre) {
        this._titre = titre;
    }

    /**
     * @return the _dateEdition
     */
    public String getDateEdition() {
        return _dateEdition;
    }

    /**
     * @param dateEdition the _dateEdition to set
     */
    public void setDateEdition(String dateEdition) {
        this._dateEdition = dateEdition;
    }

    /**
     * @return the _dateArrivee
     */
    public Date getDateArrivee() {
        return _dateArrivee;
    }

    /**
     * @param dateArrivee the _dateArrivee to set
     */
    public void setDateArrivee(Date dateArrivee) {
        this._dateArrivee = dateArrivee;
    }

    /**
     * @return the _nbExemplaire
     */
    public int getNbExemplaire() {
        return _nbExemplaire;
    }

    /**
     * @param nbExemplaire the _nbExemplaire to set
     */
    public void setNbExemplaire(int nbExemplaire) {
        this._nbExemplaire = nbExemplaire;
    }

    /**
     * @return the _dateDispo
     */
    public Date getDateDispo() {
        return _dateDispo;
    }

    /**
     * @param dateDispo the _dateDispo to set
     */
    public void setDateDispo(Date dateDispo) {
        this._dateDispo = dateDispo;
    }

  


}
