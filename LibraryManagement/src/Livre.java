/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pierre-antoinemontez
 */
public class Livre extends Document{


    // Attributs
    private String _nomAuteur; // ex: aventure
    private String _prenomAuteur; // prenom de l'auteur
    private String _type;         // Aventure, Policier
    private String _trancheAge;   // 12-24
    private String _editeur; // Editeur du bouquin

    // Constructeur par défault
    public Livre() {
        super();
        this._nomAuteur="Pas de nom";
        this._prenomAuteur="Pas de prenom";
        this._type="Pas de type";
        this._trancheAge="Pas de tranche";
        this._editeur="Pas d'éditeur";
    }

    // Constructeur avec paramètres
    public Livre(String id, String categorie, String titre, String dateEdition, String dateArrivee, int nbExemplaire, String dateDispo, String nomAuteur, String prenomAuteur, String Type, String TrancheAge, String editeur){
        super(id, categorie, titre, dateEdition, dateArrivee, nbExemplaire,dateDispo);
        this._nomAuteur=nomAuteur;
        this._prenomAuteur=prenomAuteur;
        this._type=Type;
        this._trancheAge=TrancheAge;
        this._editeur=editeur;
    }
    
    public Livre(Livre L){
        super(L.getId(), L.getCategorie(), L.getTitre(), L.getDateEdition(), L.getDateArrivee().toString(), L.getNbExemplaire(),L.getDateDispo().toString());
        this._nomAuteur=L._nomAuteur;
        this._prenomAuteur=L._prenomAuteur;
        this._type=L._type;
        this._trancheAge=L._trancheAge;
        this._editeur=L._editeur;
    }


   // Affiche la description du livre
    @Override
   public String affiche(){
      return super.affiche()+" Auteur: "+this.getPrenomAuteur() +" "+this.getNomAuteur() +"\n Type :" +this.getType() +"\n Tranche d'âge : "+this.getTrancheAge();
   }
    
    @Override
    protected String racc(){
    	return super.racc();
    }
    

    /**
     * @return the _nomAuteur
     */
    public String getNomAuteur() {
        return _nomAuteur;
    }

    /**
     * @param nomAuteur the _nomAuteur to set
     */
    public void setNomAuteur(String nomAuteur) {
        this._nomAuteur = nomAuteur;
    }

    /**
     * @return the _prenomAuteur
     */
    public String getPrenomAuteur() {
        return _prenomAuteur;
    }

    /**
     * @param prenomAuteur the _prenomAuteur to set
     */
    public void setPrenomAuteur(String prenomAuteur) {
        this._prenomAuteur = prenomAuteur;
    }

    /**
     * @return the _type
     */
    public String getType() {
        return _type;
    }

    /**
     * @param type the _type to set
     */
    public void setType(String type) {
        this._type = type;
    }

    /**
     * @return the _trancheAge
     */
    public String getTrancheAge() {
        return _trancheAge;
    }

    public String getEditeur() {
        return _editeur;
    }
    /**
     * @param trancheAge the _trancheAge to set
     */
    public void setTrancheAge(String trancheAge) {
        this._trancheAge = trancheAge;
    }



}
