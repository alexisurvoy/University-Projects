/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author pierre-antoinemontez
 */
public class Magazine extends Document {


    private String _theme; // ex : mensuel, annuel
    private String _freq;  // mensuel, journalier ...
    private String _editeur; //

    // Constructeur par défault
    public Magazine(){
        super();
        this._theme="Pas de thème";
        this._freq="Pas de fréquence";
        this._editeur="Pas d'éditeur";

    }

    // Constructeur avec paramètres
    public Magazine(String id, String categorie, String titre, String dateEdition, String dateArrivee, int nbExemplaire, String dateDispo,String theme, String freq, String editeur){
        super(id, categorie, titre, dateEdition, dateArrivee, nbExemplaire, dateDispo);
        this._theme=theme;
        this._freq=freq;
        this._editeur=editeur;

    }
    
    public Magazine(Magazine M){
    	super(M.getId(), M.getCategorie(), M.getTitre(), M.getDateEdition(), M.getDateArrivee().toString(), M.getNbExemplaire(),M.getDateDispo().toString());
        this._theme=M._theme;
        this._freq=M._freq;
        this._editeur=M._editeur;

    }

    // Affiche la description du magazine
   @Override
   public String affiche(){
      return super.affiche()+" Theme: "+this.getTheme() +"\n Fréquence : "+this.getFreq() +"\n Editeur : "+this.getEditeur();
   }
   
   @Override
   protected String racc(){
   	return super.racc();
   }

    /**
     * @return the _theme
     */
    public String getTheme() {
        return _theme;
    }

    /**
     * @param theme the _theme to set
     */
    public void setTheme(String theme) {
        this._theme = theme;
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
