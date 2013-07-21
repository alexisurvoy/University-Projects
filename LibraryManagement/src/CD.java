/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author pierre-antoinemontez
 */
public class CD extends Document {



    private String _artiste; // ex : single, album, compile
    private int _nbPistes;
    private String _type;
    private String _genre;

    // Constructeur par défault
    public CD(){
        super();
        this._artiste="Pas d'artiste";
        this._nbPistes=0;
        this._type="Pas de type";
        this._genre="Pas de genre";
        }

    // Constructeur avec paramètres
    public CD (String id, String categorie, String titre, String dateEdition, String dateArrivee, int nbExemplaire, String dateDispo, String artiste, int nbPistes, String type, String genre){
        super(id, categorie, titre, dateEdition, dateArrivee, nbExemplaire, dateDispo);
        this._artiste=artiste;
        this._nbPistes=nbPistes;
        this._type=type;
        this._genre=genre;
        }
    
    public CD (CD c){
        super(c.getId(), c.getCategorie(), c.getTitre(), c.getDateEdition(), c.getDateArrivee().toString(), c.getNbExemplaire(), c.getDateDispo().toString());
        this._artiste=c._artiste;
        this._nbPistes=c._nbPistes;
        this._type=c._type;
        this._genre=c._genre;
        }

    // Affiche la description du CD
     @Override
   public String affiche(){
      return super.affiche()+" Artiste: "+this.getArtiste() +"\n Nombre de pistes : "+this.getNbPistes() +"\n Type : "+this.getType() +"\n Genre : " + this.getGenre();
   }
     
     @Override
     protected String racc(){
     	return super.racc();
     }

    /**
     * @return the _artiste
     */
    public String getArtiste() {
        return _artiste;
    }

    /**
     * @param artiste the _artiste to set
     */
    public void setArtiste(String artiste) {
        this._artiste = artiste;
    }

    /**
     * @return the _nbPistes
     */
    public int getNbPistes() {
        return _nbPistes;
    }

    /**
     * @param nbPistes the _nbPistes to set
     */
    public void setNbPistes(int nbPistes) {
        this._nbPistes = nbPistes;
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
     * @return the _genre
     */
    public String getGenre() {
        return _genre;
    }

    /**
     * @param genre the _genre to set
     */
    public void setGenre(String genre) {
        this._genre = genre;
    }

     


}
