import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class Fenetre extends JFrame{
	
		private HashMap lesUtilisateurs;
		private HashMap lesDocuments;
		private HashMap lesDocumentsEmpruntes;
		private org.jdom.Document document;
		private SAXBuilder sxb;
		
		public Fenetre()
		{
			this.setSize(800, 600);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			this.setTitle("Logiciel d'emprunt de LO43");
			this.setResizable(false);
			
			//Init du SAXBuilder
			document = new org.jdom.Document();
			sxb = new SAXBuilder();
			
			this.recupDocuments();
			this.recupUtilisateurs();
			this.recupDocumentsEmpruntes();
			
			this.setContentPane(new Accueil());
			
			this.setVisible(true);
		}
		
		public void changerEcran(JPanel jp)
		{
			this.setContentPane(jp);
			this.setVisible(true);
			repaint();
		}
		
		private void recupUtilisateurs(){
			try{
				document = sxb.build(new File("XML/utilisateurs.xml"));
			}
			catch(Exception e){}
			
			this.lesUtilisateurs = new HashMap();
			Element racine = document.getRootElement();
			List utils = racine.getChildren();
			Iterator i = utils.iterator();
			while(i.hasNext()){
				Element courant = (Element)i.next();
	            
	            if (courant.getName().equals("Emprunteur"))
	            {
	                String id = courant.getChild("id").getText();
	                String nom = courant.getChild("nom").getText();
	                String prenom = courant.getChild("prenom").getText();
	                String mdp = courant.getChild("mdp").getText();
	                String mail = courant.getChild("mail").getText();
	                String login = courant.getChild("login").getText();
	                int nbEmprunt = Integer.parseInt(courant.getChild("nbEmpruntRestant").getText());
	                Emprunteur Emp= new Emprunteur(id,login, nom, prenom, mdp, mail, nbEmprunt);
	                this.lesUtilisateurs.put(Emp.getId(), Emp);
	            }
	            else if (courant.getName().equals("Bibliothecaire"))
	            {
	            	String id = courant.getChild("id").getText();
	                String nom = courant.getChild("nom").getText();
	                String prenom = courant.getChild("prenom").getText();
	                String mdp = courant.getChild("mdp").getText();
	                String mail = courant.getChild("mail").getText();
	                String login = courant.getChild("login").getText();
	                Bibliothecaire Biblio= new Bibliothecaire(id,login, nom, prenom, mdp, mail);
	                this.lesUtilisateurs.put(Biblio.getId(), Biblio);
	            }
	        }	
		}
		
		private void recupDocuments(){
			try{
				document = sxb.build(new File("XML/documents.xml"));
			}
			catch(Exception e){}
			
			this.lesDocuments = new HashMap();
			Element racine = document.getRootElement();
			List docs = racine.getChildren();
			Iterator i = docs.iterator();
			
			while(i.hasNext()){
				Element courant = (Element) i.next();

	            String id = courant.getChild("ID").getText();
	            String categorie = courant.getChild("categorie").getText();
	            String titre = courant.getChild("titre").getText();
	            String dateEdition = courant.getChild("dateEdition").getText();
	            String dateArrivee = courant.getChild("dateArrivee").getText();
	            int nbExemplaire = Integer.parseInt(courant.getChild("nbExemplaire").getText());
	            String dateDispo = courant.getChild("dateArrivee").getText();

	            if (courant.getName().equals("Livre")) {
	                String nom = courant.getChild("nomAuteur").getText();
	                String prenom = courant.getChild("prenomAuteur").getText();
	                String type = courant.getChild("type").getText();
	                String trancheAge = courant.getChild("trancheAge").getText();
	                String editeur = courant.getChild("editeur").getText();
	                Livre MA = new Livre(id, categorie, titre, dateEdition, dateArrivee, nbExemplaire, dateDispo, nom, prenom, type, trancheAge, editeur);
	                this.lesDocuments.put(MA.getId(), MA);
	            } else if (courant.getName().equals("Journal")) {
	                String type = courant.getChild("type").getText();
	                String freq = courant.getChild("freq").getText();
	                String editeur = courant.getChild("editeur").getText();
	                Journal JA = new Journal(id, categorie, titre, dateEdition, dateArrivee,  nbExemplaire, dateDispo, type, freq, editeur);
	                this.lesDocuments.put(JA.getId(), JA);
	            } else if (courant.getName().equals("cd")) {
	                String artiste = courant.getChild("artiste").getText();
	                int nbPistes = Integer.parseInt(courant.getChild("nbPistes").getText());
	                String type = courant.getChild("type").getText();
	                String genre = courant.getChild("genre").getText();
	                CD cdA = new CD(id, categorie, titre, dateEdition,dateArrivee, nbExemplaire, dateDispo, artiste, nbPistes, type, genre);
	                this.lesDocuments.put(cdA.getId(), cdA);
	            } else if (courant.getName().equals("Magazine")) {
	                String theme = courant.getChild("theme").getText();
	                String freq = courant.getChild("freq").getText();
	                String editeur = courant.getChild("editeur").getText();
	                Magazine magA = new Magazine(id, categorie, titre, dateArrivee, dateArrivee, nbExemplaire, dateDispo, theme, freq, editeur);
	                this.lesDocuments.put(magA.getId(), magA);
	            }
			}
		}
		
		private void recupDocumentsEmpruntes(){
			
			try{
				document = sxb.build(new File("XML/documentsEmpruntes.xml"));
			}
			catch(Exception e){}
			
			this.lesDocumentsEmpruntes = new HashMap();
			Element racine = document.getRootElement();
			List docs = racine.getChildren();
			Iterator i = docs.iterator();
			
			while(i.hasNext()){
				Element courant = (Element) i.next();
				String idEmprunt = courant.getChild("idEmprunt").getText();
	            String dateEmprunt = courant.getChild("dateEmprunt").getText();
	            String idEmprunteur = courant.getChild("idEmprunteur").getText();
	            String idDocument = courant.getChild("idDocument").getText();
	            
	            //System.out.println("debut");
	            //System.out.println(idEmprunteur);
	            //System.out.println(getUtilisateurs().get(idEmprunteur));
	            //System.out.println("fin");
	            
	            Emprunteur e = (Emprunteur)getUtilisateurs().get(idEmprunteur);	            
	            Document d = (Document)getDocs().get(idDocument);
	            
				Emprunt emp = new Emprunt (e, d, idEmprunt, dateEmprunt);
				String key = e._id + "-" + d.getId();
				this.lesDocumentsEmpruntes.put(key, emp);
			}
							
		}
		
		public HashMap getUtilisateurs(){
			return this.lesUtilisateurs;
		}
		
		public void addUtilisateur(Personne p){
			this.lesUtilisateurs.put(p.getId(), p);
		}
		
		public HashMap getDocs(){
			return this.lesDocuments;
		}
		
		public void addDocs(Document d){
			this.lesDocuments.put(d.getId(), d);
		}
		
		public HashMap getDocsEmpruntes(){
			return this.lesDocumentsEmpruntes;
		}
		
		public void addDocsEmpruntes(Emprunt e, String key){
			lesDocumentsEmpruntes.put(key, e);
		}
		
		public void removeDocsEmpruntes(String id){
			lesDocumentsEmpruntes.remove(id);
		}
		
		
		public void removeDoc(String id){
			lesDocuments.remove(id);
		}
		
		public void removeUtilisateurs(String id){
			lesUtilisateurs.remove(id);
		}
		
}
