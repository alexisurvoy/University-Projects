import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class Accueil extends JPanel implements KeyListener, ActionListener{
	
	private JComboBox choix;
	private JTextField pseudo;
	private JPasswordField mdp;
	
	private HashMap lesDocuments;
	static Personne user;
	
	// Variables création du XML pour les documents
	static Element racine = new Element("documents");
	static org.jdom.Document document = new org.jdom.Document(racine);
	
	// Variables création du XML pour les emprunts
	static Element racine1 = new Element("Emprunts");
	static org.jdom.Document document1 = new org.jdom.Document(racine1);
	
	// Variables création du XML pour les utilisateurs
	static Element racine2 = new Element("utilisateurs");
	static org.jdom.Document document2 = new org.jdom.Document(racine2);
	
	public Accueil()
	{
		this.setLayout(null);
		
		this.
		
		choix = new JComboBox();
		choix.addKeyListener(this);
		
		choix.addItem("Bibliothecaire");
        choix.addItem("Emprunteur");
        
		JButton login= new JButton("Se connecter");

		login.addActionListener(this);

		JButton quitter = new JButton("Quitter");
		
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int Res = JOptionPane.showConfirmDialog(null,
                        "Voulez-vous quitter ?",
                        "Quitter ?",
                        JOptionPane.YES_NO_OPTION);
                if (Res == 0) {
                	// Mettre dans un fichier xml le contenu de la hash map
                	remplirXml();
                	remplirXml2();
                	remplirXml3();
                	              	
                    System.exit(1);
                }
			}
		});
		
		pseudo = new JTextField();
		pseudo.addKeyListener(this);
		mdp = new JPasswordField();
		mdp.addKeyListener(this);
		
		JLabel ps = new JLabel("Pseudo : ");
		JLabel md = new JLabel("Mot de passe : ");
        
		this.add(login);
		
		this.add(quitter);
		
		this.add(pseudo);
		
		this.add(mdp);
		
		this.add(ps);
		
		this.add(md);
		
		this.add(choix);
		
		login.setBounds(230, 350, 130, 30);
		quitter.setBounds(400, 350, 130, 30);
		pseudo.setBounds(350, 200, 180, 30);
		mdp.setBounds(350, 250, 180, 30);
		ps.setBounds(230, 200, 100, 30);
		md.setBounds(230, 250, 120, 30);
		choix.setBounds(320, 300, 150, 30);
	}

	public void actionPerformed(ActionEvent arg0){
		String type = choix.getSelectedItem().toString();
		String nom = pseudo.getText();
		String cle = mdp.getText();
		if(nom.compareTo("")==0)
		{
			JOptionPane.showMessageDialog(null, "Vous devez remplir le champ Pseudo !", "Information", JOptionPane.ERROR_MESSAGE);
		}
		else if(cle.compareTo("")==0)
		{
			JOptionPane.showMessageDialog(null, "Vous devez remplir le champ Mot de passe !", "Information", JOptionPane.ERROR_MESSAGE);
		}
		else 
		{
				try {
					if(verifPseudo()){
						if(type.compareTo("Bibliothecaire")==0)
						{
							try {
								logiciel.getFenetre().changerEcran(new ecran_bibliothecaire(0));
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else 
						{
							try {
								logiciel.getFenetre().changerEcran(new ecran_emprunteur((Emprunteur)Accueil.user));
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}else{
						JOptionPane.showMessageDialog(null, "Le pseudo ou le mot de passe est incorrect ou l'utilisateur ne correspond pas au type selectionné !", "Information", JOptionPane.ERROR_MESSAGE);
					}
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	private boolean verifPseudo() throws Exception{
		
		Iterator i = logiciel.getFenetre().getUtilisateurs().keySet().iterator();
		
		while(i.hasNext()){
			String idUtil = (String)i.next();
			Personne util = (Personne)logiciel.getFenetre().getUtilisateurs().get(idUtil);

				if((util._login.equals(pseudo.getText())) && (util._mdp.equals(AeSimpleSHA1.SHA1(mdp.getText())))){
					//Sauvegarde de l'utilisateur pour utilisation dans l'application
					if(util._type == "Bibliothecaire" && choix.getSelectedItem().toString().compareTo("Bibliothecaire")==0){
						Accueil.user = (Bibliothecaire)util;
						return true;
					}else if(util._type == "Emprunteur" && choix.getSelectedItem().toString().compareTo("Emprunteur")==0){
						Accueil.user = (Emprunteur)util;
						return true;
					}
					else return false;
				}
		}
		return false;

	}
	
	
	private void remplirXml3(){
		
		Iterator i = logiciel.getFenetre().getUtilisateurs().keySet().iterator();
		// System.out.println(logiciel.getFenetre().getUtilisateurs());
		
		while(i.hasNext()){
			
			  String idUtil = (String)i.next();
			  Personne p = (Personne)logiciel.getFenetre().getUtilisateurs().get(idUtil);
			
			  Element id = new Element("id");
			  id.setText(p.getId());
		      
		      Element login = new Element("login");
		      login.setText(p.get_login());
		      
		      Element nom = new Element("nom");
		      nom.setText(p.get_nom());
		      
		      Element prenom = new Element("prenom");
		      prenom.setText(p.get_prenom());
		      
		      Element mdp = new Element("mdp");
		      mdp.setText(p.get_mdp());
		      
		      Element mail = new Element("mail");
		      mail.setText(p.get_mail());
		      
		      
		      if(p.get_type().equals("Emprunteur")){
		    	  Element etudiant2 = new Element("Emprunteur");
				  racine2.addContent(etudiant2);
				  
				  Element nbEmpruntRestant = new Element("nbEmpruntRestant");
				  nbEmpruntRestant.setText(Integer.toString(((Emprunteur)(p)).getNbEmpruntRestant()));
				  
				  etudiant2.addContent(id);
				  etudiant2.addContent(login);
				  etudiant2.addContent(nom);
				  etudiant2.addContent(prenom);
				  etudiant2.addContent(mdp);
				  etudiant2.addContent(mail);
				  etudiant2.addContent(nbEmpruntRestant);
		    	  
		      }
		      else if(p.get_type().equals("Bibliothecaire")){
		    	  Element etudiant2 = new Element("Bibliothecaire");
				  racine2.addContent(etudiant2);
				  
				  etudiant2.addContent(id);
				  etudiant2.addContent(login);
				  etudiant2.addContent(nom);
				  etudiant2.addContent(prenom);
				  etudiant2.addContent(mdp);
				  etudiant2.addContent(mail);
		      }		      
			
		}
		
		enregistre2("XML/utilisateurs.xml");
	}
	
	
	
	private void remplirXml2(){
		Iterator i = logiciel.getFenetre().getDocsEmpruntes().keySet().iterator();
		
		while(i.hasNext()){
			
			  String idUtil = (String)i.next();
			  Emprunt emp = (Emprunt)logiciel.getFenetre().getDocsEmpruntes().get(idUtil);
			  // System.out.println(logiciel.getFenetre().getDocsEmpruntes());
			
			  Element idEmprunt = new Element("idEmprunt");
			  idEmprunt.setText(emp.get_idEmprunt());
		      
		      Element dateEmprunt = new Element("dateEmprunt");
		      dateEmprunt.setText(emp.get_dateEmprunt());
		      
		      Element idEmprunteur = new Element("idEmprunteur");
		      idEmprunteur.setText(emp.get_Emprunteur1().getId());
		      
		      Element idDocument = new Element("idDocument");
		      idDocument.setText(emp.get_Document1().getId());
		      
		      Element etudiant1 = new Element("Emprunt");
			  racine1.addContent(etudiant1);			  
			  
			  etudiant1.addContent(idEmprunt);
			  etudiant1.addContent(dateEmprunt);
			  etudiant1.addContent(idEmprunteur);
			  etudiant1.addContent(idDocument);
			
		}
		
		enregistre1("XML/documentsEmpruntes.xml");
	}
	
	private void remplirXml(){
		// Connection à la hashmap et visualisation de son contenu
		//lesDocuments = logiciel.getFenetre().getDocs();
		// System.out.println(""+lesDocuments.values());
		//System.out.println(lesDocuments);
		
		Iterator i = logiciel.getFenetre().getDocs().keySet().iterator();
		
		while(i.hasNext()){
			String idUtil = (String)i.next();
			Document doc = (Document)logiciel.getFenetre().getDocs().get(idUtil);
			
			// Affiche tous les documents
			// System.out.println(doc.affiche());
					
		      // Ajout du champs ID
		      Element id = new Element("ID");
		      id.setText(doc.getId());
		      
			  // Ajout du champs titre
		      Element titre = new Element("titre");
		      titre.setText(doc.getTitre());
		      
		      
		      // Ajout du champs catégorie
		      Element cat = new Element("categorie");
		      cat.setText(doc.getCategorie());
		      
		      
		      
			  // Ajout du champs dateEdition
		      Element dateEdition = new Element("dateEdition");
		      dateEdition.setText(doc.getDateEdition());
		      
		      
		      
		      // Ajout du champs dateArrivee
		      Element dateArrivee = new Element("dateArrivee");
		      dateArrivee.setText(doc.getDateArrivee().affiche());
		      
		      
		      
		      // Ajout du champs nbExemplaire
		      Element nbExemplaire = new Element("nbExemplaire");
		      nbExemplaire.setText("" +doc.getNbExemplaire());
		      
		      
		      
		      // Ajout du champs dateDispo
		     Element dateDispo = new Element("dateDispo");
		     dateDispo.setText(doc.getDateDispo().affiche());
		    
		     
		     if(doc.getCategorie().equals("livre")){
		    	 
			     Element etudiant = new Element("Livre");
			     racine.addContent(etudiant);
			      
			     etudiant.addContent(id);
			     etudiant.addContent(titre);
			     etudiant.addContent(cat);
			     etudiant.addContent(dateEdition);
			     etudiant.addContent(dateArrivee);
			     etudiant.addContent(nbExemplaire);
			     etudiant.addContent(dateDispo);

			     Element nomAuteur = new Element("nomAuteur");
			     nomAuteur.setText(((Livre) doc).getNomAuteur());
			     etudiant.addContent(nomAuteur);
			     
			     Element prenomAuteur = new Element("prenomAuteur");
			     prenomAuteur.setText(((Livre) doc).getPrenomAuteur());
			     etudiant.addContent(prenomAuteur);
			     
			     Element type = new Element("type");
			     type.setText(((Livre) doc).getType());
			     etudiant.addContent(type);
			     
			     Element trancheAge = new Element("trancheAge");
			     trancheAge.setText(((Livre) doc).getTrancheAge());
			     etudiant.addContent(trancheAge);
			     
			     Element editeur = new Element("editeur");
			     editeur.setText(((Livre) doc).getEditeur());
			     etudiant.addContent(editeur);
			     
		     }
		     else if(doc.getCategorie().equals("cd")){
		    	 
			     Element etudiant = new Element("cd");
			     racine.addContent(etudiant);
			      
			     etudiant.addContent(id);
			     etudiant.addContent(titre);
			     etudiant.addContent(cat);
			     etudiant.addContent(dateEdition);
			     etudiant.addContent(dateArrivee);
			     etudiant.addContent(nbExemplaire);
			     etudiant.addContent(dateDispo);
		         
		         Element artiste = new Element("artiste");
		         artiste.setText(((CD) doc).getArtiste());
			     etudiant.addContent(artiste);
			     
			     Element nbPistes = new Element("nbPistes");
			     nbPistes.setText(""+((CD) doc).getNbPistes());
			     etudiant.addContent(nbPistes);
			     
			     Element type = new Element("type");
			     type.setText(((CD) doc).getType());
			     etudiant.addContent(type);
			     
			     Element genre = new Element("genre");
			     genre.setText(((CD) doc).getGenre());
			     etudiant.addContent(genre);
		         		         
		     }
		     else if(doc.getCategorie().equals("magazine")){
		    	 
			     Element etudiant = new Element("Magazine");
			     racine.addContent(etudiant);
			      
			     etudiant.addContent(id);
			     etudiant.addContent(titre);
			     etudiant.addContent(cat);
			     etudiant.addContent(dateEdition);
			     etudiant.addContent(dateArrivee);
			     etudiant.addContent(nbExemplaire);
			     etudiant.addContent(dateDispo);
		    	    
	    	    Element theme = new Element("theme");
	    	    theme.setText(((Magazine) doc).getTheme());
			    etudiant.addContent(theme);
			    
			    Element freq = new Element("freq");
			    freq.setText(((Magazine) doc).getFreq());
			    etudiant.addContent(freq);
			    
			    Element editeur = new Element("editeur");
			    editeur.setText(((Magazine) doc).getEditeur());
			    etudiant.addContent(editeur);	    	    
		    	 
		     }
		     else if(doc.getCategorie().equals("journal")){
		    	 
			     Element etudiant = new Element("Journal");
			     racine.addContent(etudiant);
			      
			     etudiant.addContent(id);
			     etudiant.addContent(titre);
			     etudiant.addContent(cat);
			     etudiant.addContent(dateEdition);
			     etudiant.addContent(dateArrivee);
			     etudiant.addContent(nbExemplaire);
			     etudiant.addContent(dateDispo);
		    	    
	    	    Element type = new Element("type");
	    	    type.setText(((Journal) doc).getType());
			    etudiant.addContent(type);
			    
			    Element freq = new Element("freq");
			    freq.setText(((Journal) doc).getFreq());
			    etudiant.addContent(freq);
			    
			    Element editeur = new Element("editeur");
			    editeur.setText(((Journal) doc).getEditeur());
			    etudiant.addContent(editeur);
		     }
		     	    
	      
		} // Fin du while

	      enregistre("XML/documents.xml");		

	}
	
	static void enregistre(String fichier)
	{
	   try
	   {
	      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	      sortie.output(document, new FileOutputStream(fichier));
	     // System.out.println("XML créé !");
	   }
	   catch (java.io.IOException e){}
	}
	
	static void enregistre1(String fichier)
	{
	   try
	   {
	      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	      sortie.output(document1, new FileOutputStream(fichier));
	     // System.out.println("XML créé !");
	   }
	   catch (java.io.IOException e){}
	}
	
	static void enregistre2(String fichier)
	{
	   try
	   {
	      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	      sortie.output(document2, new FileOutputStream(fichier));
	     // System.out.println("XML créé !");
	   }
	   catch (java.io.IOException e){}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if(e.getKeyCode()==10){
			this.actionPerformed(null);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
