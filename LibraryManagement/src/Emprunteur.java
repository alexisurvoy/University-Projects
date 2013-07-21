import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pierre-antoinemontez
 */
public class Emprunteur extends Personne{
    
    // Attributs de la classe
    private int _nbEmpruntRestant;
    private Document[] _documentsEmpruntes;

    public Emprunteur(){
        super();
        this._nbEmpruntRestant=5;
        this._documentsEmpruntes=new Document[5];
    }

    public Emprunteur(String id, String login, String nom, String prenom, String mdp, String mail, int nbEmpruntRestant){
        super(id, login, nom, prenom, mdp, mail, "Emprunteur");
        this._nbEmpruntRestant=nbEmpruntRestant;
        this._documentsEmpruntes=new Document[5];
    }
    
    public Emprunteur(Emprunteur e){
        super(e._id, e._login, e._nom, e._prenom, e._mdp, e._mail, "Emprunteur");
        this._nbEmpruntRestant=e._nbEmpruntRestant;
        this._documentsEmpruntes=new Document[5];
    }
    
    // Permet d'acceder ï¿½ un documents
    public Document accessDocument(int i){
    	return _documentsEmpruntes[i];
    }
    

    @Override
   public String affiche(){
      String temp = super.affiche()+" Nombre d'emprunts restants : "+this.getNbEmpruntRestant()+" \n Documents empruntŽs : \n";

      for(int i=0; i<5-getNbEmpruntRestant();i++){
          temp= temp +" "+ getDocumentsEmpruntes()[i].getTitre() + "\n";
      }
      return temp;
   }
    
    @Override
    protected String racc(){
    	return super.racc();
    }

   public void attribuerDoc(Document doc){
          int i=5-getNbEmpruntRestant();
          if(i==5){
        	  JOptionPane.showMessageDialog(null, "Cet utilisateur a dŽjˆ 5 documents empruntŽs. Il doit en rendre un pour emprunter un autre document.", "Attention", JOptionPane.ERROR_MESSAGE);
          }
          else{
            this.getDocumentsEmpruntes()[i]=doc;
            this.setNbEmpruntRestant(this.getNbEmpruntRestant() - 1);
         }

   }
   
	public String getId() {
		return _id;
	}

    /**
     * @return the _nbEmpruntRestant
     */
    public int getNbEmpruntRestant() {
        return _nbEmpruntRestant;
    }

    /**
     * @param nbEmpruntRestant the _nbEmpruntRestant to set
     */
    public void setNbEmpruntRestant(int nbEmpruntRestant) {
        this._nbEmpruntRestant = nbEmpruntRestant;
    }

    /**
     * @return the _documentsEmpruntes
     */
    public Document[] getDocumentsEmpruntes() {
        return _documentsEmpruntes;
    }

    /**
     * @param documentsEmpruntes the _documentsEmpruntes to set
     */
    public void setDocumentsEmpruntes(Document[] documentsEmpruntes) {
        this._documentsEmpruntes = documentsEmpruntes;
    }

   


}