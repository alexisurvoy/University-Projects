/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author pierre-antoinemontez
 */
public class Journal extends Document {
    
    // Atributs
    private String _type; // Frequence du journal   EX: Journalier, mensuel ...

    private String _freq; // mensuel ...

    private String _editeur; // 

    // Constructeur par défault
    public Journal() {
        super();
        this._type= "Pas de type";
        this._freq = "Pas de fréquence";
        this._editeur = "Pas d'éditeur ";
    }

    // Constructeur avec paramètres
    public Journal(String id, String categorie, String titre, String dateEdition, String dateArrivee, int nbExemplaire, String dateDispo,String type, String freq, String editeur){
        super(id, categorie, titre, dateEdition, dateArrivee, nbExemplaire,dateDispo);
        this._type= type;
        this._freq = freq;
        this._editeur = editeur; 
    }
    
    public Journal(Journal J){
        super(J.getId(), J.getCategorie(), J.getTitre(), J.getDateEdition(), J.getDateArrivee().toString(), J.getNbExemplaire(),J.getDateDispo().toString());
        this._type= J._type;
        this._freq = J._freq;
        this._editeur = J._editeur; 
    }


   // Affiche la description du journal
   @Override
   public String affiche(){
      return super.affiche()+" Type: "+this.getType() +"\n Fréquence: "+this.getFreq() +"\n Editeur : "+this.getEditeur();
   }
   
   @Override
   protected String racc(){
   	return super.racc();
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
     * @return the _freq
     */
    public String getFreq() {
        return _freq;
    }

    /**
     * @param freq the _freq to set
     */
    public void setFreq(String freq) {
        this._freq = freq;
    }

    /**
     * @return the _editeur
     */
    public String getEditeur() {
        return _editeur;
    }

    /**
     * @param editeur the _editeur to set
     */
    public void setEditeur(String editeur) {
        this._editeur = editeur;
    }


}
