/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pierre-antoinemontez
 */
public class Bibliothecaire extends Personne {

    public Bibliothecaire(){
        super();
    }

    // Constructeur surcharg�
    public Bibliothecaire(String id, String login, String nom, String prenom,String mdp, String mail){
        super(id, login, nom, prenom,mdp, mail, "Bibliothecaire");
    }

    protected String racc(){
	      return  super.racc();
	   }
    
	public String getId() {
		return _id;
	}
    
}