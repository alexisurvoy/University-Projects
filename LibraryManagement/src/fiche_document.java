import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class fiche_document extends JPanel{
	
	private String appelant;
	private JButton valider;
	private JButton modif;
	private JButton suppress;
	private JButton empr;
	private JLabel label_id;
	private JLabel label_cate;
	private JLabel label_titre;
	private JLabel label_dateEdition;
	private JLabel label_dateArrivee;
	private JLabel label_nbExemplaire;
	private JLabel label_dateDispo;
	private JLabel label_Artiste;
	private JLabel label_nbrPiste;
	private JLabel label_type;
	private JLabel label_genre;
	private JLabel label_freq;
	private JLabel label_editeur;
	private JLabel label_nomAuteur;
	private JLabel label_prenomAuteur;
	private JLabel label_trancheAge;
	private JLabel label_theme;
	private JTextField label_idT;
	private JTextField label_cateT;
	private JTextField label_titreT;
	private JTextField label_dateEditionT;
	private JTextField label_dateArriveeT;
	private JTextField label_nbExemplaireT;
	private JTextField label_dateDispoT;
	private JTextField label_ArtisteT;
	private JTextField label_nbrPisteT;
	private JTextField label_typeT;
	private JTextField label_genreT;
	private JTextField label_freqT;
	private JTextField label_editeurT;
	private JTextField label_nomAuteurT;
	private JTextField label_prenomAuteurT;
	private JTextField label_trancheAgeT;
	private JTextField label_themeT;
	private Document d;
	
	public fiche_document(Document _d, String page_appelante){

		this.setLayout(null);
				
		// Permet de récupérer la hashmap contenant les documents
		HashMap Doc = logiciel.getFenetre().getDocs();
		d = _d;
		
		label_id = new JLabel("ID : " );
		label_cate = new JLabel("Catégorie : ");
		label_titre = new JLabel("Titre : ");
		label_dateEdition = new JLabel("Date d'édition : ");
		label_dateArrivee = new JLabel("Date d'arrivée : ");
		label_nbExemplaire = new JLabel("Nombre d'exemplaire disponible : ");
		label_dateDispo = new JLabel("Date de disponibilité : ");
		
		label_idT=new JTextField(d.getId());
		label_cateT=new JTextField(d.getCategorie());
		label_titreT=new JTextField(d.getTitre());
		label_dateEditionT=new JTextField(d.getDateEdition());
		label_dateArriveeT=new JTextField(d.getDateArrivee().affiche());
		label_nbExemplaireT=new JTextField(Integer.toString(d.getNbExemplaire()));
		label_dateDispoT=new JTextField(d.getDateDispo().affiche());
		
		this.add(label_id);
		this.add(label_cate);
		this.add(label_titre);
		this.add(label_dateEdition);
		this.add(label_dateArrivee);
		this.add(label_nbExemplaire);
		this.add(label_dateDispo);
		
		this.add(label_idT);
		this.add(label_cateT);
		this.add(label_titreT);
		this.add(label_dateEditionT);
		this.add(label_dateArriveeT);
		this.add(label_nbExemplaireT);
		this.add(label_dateDispoT);
		
		
		label_id.setBounds(10, 10, 380, 30);
		label_cate.setBounds(400, 10, 380, 30);
		label_titre.setBounds(10, 90, 600, 30);
		label_dateEdition.setBounds(10, 170, 380, 30);
		label_dateArrivee.setBounds(400, 170, 380, 30);
		label_nbExemplaire.setBounds(10, 250, 380, 30);
		label_dateDispo.setBounds(400, 250, 380, 30);
		
		label_idT.setBounds(10, 50, 380, 30);
		label_cateT.setBounds(400, 50, 380, 30);
		label_titreT.setBounds(10, 130, 770, 30);
		label_dateEditionT.setBounds(10, 210, 380, 30);
		label_dateArriveeT.setBounds(400, 210, 380, 30);
		label_nbExemplaireT.setBounds(10, 290, 380, 30);
		label_dateDispoT.setBounds(400, 290, 380, 30);
		
		
		label_idT.setEditable(false);
		label_cateT.setEditable(false);
		label_titreT.setEditable(false);
		label_dateEditionT.setEditable(false);
		label_dateArriveeT.setEditable(false);
		label_nbExemplaireT.setEditable(false);
		label_dateDispoT.setEditable(false);	
		
		
		label_idT.setVisible(true);
		label_cateT.setVisible(true);
		label_titreT.setVisible(true);
		label_dateEditionT.setVisible(true);
		label_dateArriveeT.setVisible(true);
		label_nbExemplaireT.setVisible(true);
		label_dateDispoT.setVisible(true);		
		
		if(d.getCategorie().equalsIgnoreCase("cd"))//si CD
		{
			label_Artiste= new JLabel("Artiste : ");//CD
			label_nbrPiste = new JLabel("Nombre de pistes : ");//CD
			label_type = new JLabel("Type : ");//pour CD et journal
			label_genre = new JLabel("Genre : ");//CD
			
			label_ArtisteT=new JTextField(((CD)d).getArtiste());
			label_nbrPisteT=new JTextField(Integer.toString(((CD)d).getNbPistes()));
			label_typeT=new JTextField(((CD)d).getType());
			label_genreT=new JTextField(((CD)d).getGenre());
			
			this.add(label_Artiste);
			this.add(label_nbrPiste);
			this.add(label_type);
			this.add(label_genre);
			
			this.add(label_ArtisteT);
			this.add(label_nbrPisteT);
			this.add(label_typeT);
			this.add(label_genreT);
			
			label_ArtisteT.setBounds(10, 370, 380, 30);
			label_nbrPisteT.setBounds(400, 370, 380, 30);
			label_typeT.setBounds(10, 450, 380, 30);
			label_genreT.setBounds(400, 450, 380, 30);
			
			label_Artiste.setBounds(10, 330, 380, 30);
			label_nbrPiste.setBounds(400, 330, 380, 30);
			label_type.setBounds(10, 410, 380, 30);
			label_genre.setBounds(400, 410, 380, 30);
			
			label_ArtisteT.setEditable(false);
			label_nbrPisteT.setEditable(false);
			label_typeT.setEditable(false);
			label_genreT.setEditable(false);
			
			
			label_ArtisteT.setVisible(true);
			label_nbrPisteT.setVisible(true);
			label_typeT.setVisible(true);
			label_genreT.setVisible(true);
		}
		if(d.getCategorie().equalsIgnoreCase("journal"))//Si journal
		{
			label_type = new JLabel("Type : ");//pour CD et journal
			label_freq = new JLabel("Fréquence : ");//pour journal magazine
			label_editeur = new JLabel("Editeur : ");//journal livre magazine
			
			label_typeT=new JTextField(((Journal)(d)).getType());
			label_freqT=new JTextField(((Journal)(d)).getFreq());
			label_editeurT=new JTextField(((Journal)(d)).getEditeur());
			
			this.add(label_type);
			this.add(label_freq);
			this.add(label_editeur);
			
			this.add(label_typeT);
			this.add(label_freqT);
			this.add(label_editeurT);
			
			label_typeT.setBounds(10, 370, 380, 30);
			label_freqT.setBounds(400, 370, 380, 30);
			label_editeurT.setBounds(10, 450, 380, 30);
			
			label_type.setBounds(10, 330, 380, 30);
			label_freq.setBounds(400, 330, 380, 30);
			label_editeur.setBounds(10, 410, 380, 30);
			
			label_typeT.setEditable(false);
			label_freqT.setEditable(false);
			label_editeurT.setEditable(false);
						
			label_typeT.setVisible(true);
			label_freqT.setVisible(true);
			label_editeurT.setVisible(true);
		}
		if(d.getCategorie().equalsIgnoreCase("livre"))//Si Livre
		{
			label_editeur = new JLabel("Editeur : ");//journal livre magazine
			label_nomAuteur = new JLabel("Nom de l'auteur : ");//livre
			label_prenomAuteur = new JLabel("Prénom de l'auteur : ");//livre
			label_trancheAge = new JLabel("Tranche d'âge : ");//livre
			
			label_editeurT=new JTextField(((Livre)(d)).getEditeur());
			label_nomAuteurT=new JTextField(((Livre)(d)).getNomAuteur());
			label_prenomAuteurT=new JTextField(((Livre)(d)).getPrenomAuteur());
			label_trancheAgeT=new JTextField(((Livre)(d)).getTrancheAge());
			
			this.add(label_editeur);
			this.add(label_nomAuteur);
			this.add(label_prenomAuteur);
			this.add(label_trancheAge);
			
			this.add(label_editeurT);
			this.add(label_nomAuteurT);
			this.add(label_prenomAuteurT);
			this.add(label_trancheAgeT);
			
			label_editeurT.setBounds(10, 370, 380, 30);
			label_nomAuteurT.setBounds(400, 370, 380, 30);
			label_prenomAuteurT.setBounds(10, 450, 380, 30);
			label_trancheAgeT.setBounds(400, 450, 380, 30);
			
			label_editeur.setBounds(10, 330, 380, 30);
			label_nomAuteur.setBounds(400, 330, 380, 30);
			label_prenomAuteur.setBounds(10, 410, 380, 30);
			label_trancheAge.setBounds(400, 410, 380, 30);
			
			label_editeurT.setEditable(false);
			label_nomAuteurT.setEditable(false);
			label_prenomAuteurT.setEditable(false);
			label_trancheAgeT.setEditable(false);
			
			
			label_editeurT.setVisible(true);
			label_nomAuteurT.setVisible(true);
			label_prenomAuteurT.setVisible(true);
			label_trancheAgeT.setVisible(true);
		}
		if(d.getCategorie().equalsIgnoreCase("magazine"))//Si Magazine
		{
			label_freq = new JLabel("Fréquence : ");//pour journal magazine
			label_editeur = new JLabel("Editeur : ");//journal livre magazine
			label_theme = new JLabel("Theme : ");//magazine
			
			label_freqT=new JTextField(((Magazine)(d)).getFreq());
			label_editeurT=new JTextField(((Magazine)(d)).getEditeur());
			label_themeT=new JTextField(((Magazine)(d)).getTheme());
			
			this.add(label_freq);
			this.add(label_editeur);
			this.add(label_theme);
			
			this.add(label_freqT);
			this.add(label_editeurT);
			this.add(label_themeT);
			
			label_freqT.setBounds(10, 370, 380, 30);
			label_editeurT.setBounds(400, 370, 380, 30);
			label_themeT.setBounds(10, 450, 380, 30);
			
			label_freq.setBounds(10, 330, 380, 30);
			label_editeur.setBounds(400, 330, 380, 30);
			label_theme.setBounds(10, 410, 380, 30);
			
			label_freqT.setEditable(false);
			label_editeurT.setEditable(false);
			label_themeT.setEditable(false);
			
			label_freqT.setVisible(true);
			label_editeurT.setVisible(true);
			label_themeT.setVisible(true);
		}

		
		
		
		
		appelant=page_appelante;
		
		JButton retour = new JButton("Effectuer une autre recherche");
		retour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(appelant.compareTo("biblio")==0)
					{
						logiciel.getFenetre().changerEcran(new ecran_bibliothecaire(0));
					}
					else if(appelant.compareTo("emprun")==0)
					{
						logiciel.getFenetre().changerEcran(new ecran_emprunteur(new Emprunteur()));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		this.add(retour);
		retour.setBounds(50, 500, 300, 30);
		
		JButton delog= new JButton("Se déconnecter");
		
		delog.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int reponse = JOptionPane.showConfirmDialog(null,
                        "Voulez-vous déconnecter ?",
                        "Déconnection",
                        JOptionPane.YES_NO_OPTION);
                if (reponse == 0) {
                	logiciel.getFenetre().changerEcran(new Accueil());
                }
			}
		});
		this.add(delog);
		delog.setBounds(550, 500, 150, 30);
		if(appelant=="biblio")
		{
			valider= new JButton("Valider");
			
			valider.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					int reponse = JOptionPane.showConfirmDialog(null,
	                        "Voulez-vous enregistrer les modifications ?",
	                        "Enregistrer ?",
	                        JOptionPane.YES_NO_OPTION);
					if (reponse == 0) {
						modif.setEnabled(true);
						modif.setVisible(true);
						valider.setEnabled(false);
						valider.setVisible(false);
						
						label_titreT.setEditable(false);
						label_dateEditionT.setEditable(false);
						label_dateArriveeT.setEditable(false);
						label_nbExemplaireT.setEditable(false);
						label_dateDispoT.setEditable(false);
						
						int nbEx = new Integer(label_nbExemplaireT.getText());
						logiciel.getFenetre().removeDoc(label_idT.getText());
						
						// Propre à tous les documents
	                	String id=label_idT.getText(); 
	                    String categorie=label_cateT.getText();
	                    String titre=label_titreT.getText();
	                    String dateEdition=label_dateEditionT.getText();
	                    String dateArrivee= label_dateArriveeT.getText();
	                    int nbExemplaire= new Integer(label_nbExemplaireT.getText());
	                    String dateDispo= label_dateDispoT.getText();
						
						if(d.getCategorie().equalsIgnoreCase("cd"))//si CD
						{	
							label_ArtisteT.setEditable(false);
							label_nbrPisteT.setEditable(false);
							label_typeT.setEditable(false);
							label_genreT.setEditable(false);
							
							String artiste=label_ArtisteT.getText(); 
	                        int nbPistes= new Integer(label_nbrPisteT.getText());
	                        String type=label_typeT.getText();
	                        String genre=label_genreT.getText();
	                      
	                        CD cda= new CD(id, categorie, titre, dateEdition, dateArrivee, nbExemplaire, dateDispo, artiste, nbPistes, type, genre);
	                        logiciel.getFenetre().addDocs(cda);
						}
						else if(d.getCategorie().equalsIgnoreCase("journal"))//si CD
						{
							label_typeT.setEditable(false);
							label_freqT.setEditable(false);
							label_editeurT.setEditable(false);
							
							// Propres aux journaux
		                    String freq=label_freqT.getText(); 
		                    String type=label_typeT.getText();
		                    String editeur=label_editeurT.getText();									                   		                    
		                    
		                    Journal j1= new Journal(id, categorie, titre, dateEdition, dateArrivee, nbExemplaire, dateDispo, type, freq, editeur);
		                    logiciel.getFenetre().addDocs(j1);
						}
						else if(d.getCategorie().equalsIgnoreCase("livre"))//si CD
						{
							label_editeurT.setEditable(false);
							label_nomAuteurT.setEditable(false);
							label_prenomAuteurT.setEditable(false);
							label_trancheAgeT.setEditable(false);					
							
							// Propre aux livre seulement
		                    String nomAuteur=label_nomAuteurT.getText(); 
		                    String prenomAuteur=label_prenomAuteurT.getText(); 
		                    String type="Tout public"; // Oubli de ce texteField ^^         
		                    String trancheAge=label_trancheAgeT.getText();   
		                    String editeur=label_editeurT.getText(); 
									                    
		                    
	                		Livre l1= new Livre(id, categorie, titre, dateEdition, dateArrivee, nbExemplaire, dateDispo, nomAuteur, prenomAuteur, type, trancheAge, editeur);                		
	                		logiciel.getFenetre().addDocs(l1);
	                		
						}
						else if(d.getCategorie().equalsIgnoreCase("magazine"))//si CD
						{
							label_freqT.setEditable(false);
							label_editeurT.setEditable(false);
							label_themeT.setEditable(false);
							
							// Propres au magazine
		                    String theme=label_themeT.getText(); 
		                    String freq=label_freqT.getText();  
		                    String editeur=label_editeurT.getText(); 
		                    		                 		                    
		                    Magazine m1 = new Magazine(id, categorie, titre, dateEdition, dateArrivee, nbExemplaire, dateDispo, theme,  freq,  editeur);
		                    logiciel.getFenetre().addDocs(m1);
						}
						suppress.setVisible(true);
						suppress.setEnabled(true);					
	                }
				}
			});
			this.add(valider);
			valider.setBounds(375, 500, 150, 30);
			
			valider.setEnabled(false);
			valider.setVisible(false);
			
			modif= new JButton("Modifier");
			
			modif.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					valider.setEnabled(true);
					valider.setVisible(true);
					modif.setEnabled(false);
					modif.setVisible(false);
					suppress.setVisible(false);
					suppress.setEnabled(false);
					
					label_titreT.setEditable(true);
					label_dateEditionT.setEditable(true);
					label_dateArriveeT.setEditable(true);
					label_nbExemplaireT.setEditable(true);
					label_dateDispoT.setEditable(true);
					
					if(d.getCategorie().equalsIgnoreCase("cd"))//si CD
					{
						label_ArtisteT.setEditable(true);
						label_nbrPisteT.setEditable(true);
						label_typeT.setEditable(true);
						label_genreT.setEditable(true);
					}
					if(d.getCategorie().equalsIgnoreCase("journal"))//si CD
					{
						label_typeT.setEditable(true);
						label_freqT.setEditable(true);
						label_editeurT.setEditable(true);
					}
					if(d.getCategorie().equalsIgnoreCase("livre"))//si CD
					{
						label_editeurT.setEditable(true);
						label_nomAuteurT.setEditable(true);
						label_prenomAuteurT.setEditable(true);
						label_trancheAgeT.setEditable(true);
					}
					if(d.getCategorie().equalsIgnoreCase("magazine"))//si CD
					{						
						label_freqT.setEditable(true);
						label_editeurT.setEditable(true);
						label_themeT.setEditable(true);
					}
				}
			});
			this.add(modif);
			modif.setBounds(375, 500, 150, 30);
			
			suppress= new JButton("Supprimer");
			
			suppress.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					int reponse = JOptionPane.showConfirmDialog(null,
	                        "Voulez-vous vraiment supprimer ce document ?",
	                        "Suppression",
	                        JOptionPane.YES_NO_OPTION);
	                if (reponse == 0) {
	                	logiciel.getFenetre().removeDoc(label_idT.getText());
	                	try {
							logiciel.getFenetre().changerEcran(new ecran_bibliothecaire(0));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                }
				}
			});
			this.add(suppress);
			suppress.setBounds(550, 550, 150, 30);
			
			empr= new JButton("Emprunter");
			
			empr.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					int error=1;
					String reponse = JOptionPane.showInputDialog("Veuillez rentrer le pseudo de l'utilisateur : ", "");
					
					Iterator i = logiciel.getFenetre().getUtilisateurs().keySet().iterator();
					
					while(i.hasNext()){
						String idUtil = (String)i.next();
						Personne util = (Personne)logiciel.getFenetre().getUtilisateurs().get(idUtil);
						// System.out.println(util.get_login());
						
							if((util._login.equals(reponse))){
								error=0;
								// System.out.println(util._id + util._login);
								
								if(((Emprunteur) util).getNbEmpruntRestant()>0){ // Si il lui reste des emprunts restants
										if(d.getNbExemplaire()>0){
											// On rentre l'emprunt dans la HashMap !
											String key = util.get_login() + d.getId();
											Emprunt e = new Emprunt( ((Emprunteur)(util)), d, key);
											logiciel.getFenetre().addDocsEmpruntes(e,key);
											JOptionPane.showMessageDialog(null, "Document bien emprunté !", "Information", JOptionPane.INFORMATION_MESSAGE);
											// System.out.println(logiciel.getFenetre().getDocsEmpruntes());
											((Emprunteur) util).setNbEmpruntRestant(((Emprunteur) util).getNbEmpruntRestant()-1); // on lui enleve un emprunt
											d.setNbExemplaire(d.getNbExemplaire()-1); // On enleve un exemplaire de document
											try {
												logiciel.getFenetre().changerEcran(new ecran_bibliothecaire(0));
											} catch (Exception e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
										}
										else{
											JOptionPane.showMessageDialog(null, "Plus de livre restants", "Information", JOptionPane.ERROR_MESSAGE);
										}
								}
								else{
									JOptionPane.showMessageDialog(null, "Plus d'emprunts restants", "Information", JOptionPane.ERROR_MESSAGE);
								}
								
							}
					}
					if(error==1){
						JOptionPane.showMessageDialog(null, "Pseudo invalide", "Information", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			this.add(empr);
			empr.setBounds(50, 550, 150, 30);
		}
	}
	
	

}
