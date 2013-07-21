import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.HashMap;


public class ajouter_document extends JPanel{
	
	private JButton valider;
	private JButton modif;
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
	private JComboBox choix;
	
	public ajouter_document()
	{
		
		this.setLayout(null);
		
		choix = new JComboBox();
		
		choix.addItem("CD");
        choix.addItem("Journal");
        choix.addItem("Livre");
        choix.addItem("Magazine");
        choix.addItemListener(new ItemState());
        
		label_id = new JLabel("ID : ");
		label_cate = new JLabel("Catégorie : ");
		label_titre = new JLabel("Titre : ");
		label_dateEdition = new JLabel("Date d'édition : ");
		label_dateArrivee = new JLabel("Date d'arrivée : ");
		label_nbExemplaire = new JLabel("Nombre d'exemplaire disponible : ");
		label_dateDispo = new JLabel("Date de disponibilité : ");
		label_Artiste= new JLabel("Artiste : ");//CD
		label_nbrPiste = new JLabel("Nombre de pistes : ");//CD
		label_type = new JLabel("Type : ");//pour CD et journal
		label_genre = new JLabel("Genre : ");//CD
		label_freq = new JLabel("Fr√©quence : ");//pour journal magazine
		label_editeur = new JLabel("Editeur : ");//journal livre magazine
		label_nomAuteur = new JLabel("Nom de l'auteur : ");//livre
		label_prenomAuteur = new JLabel("Prénom de l'auteur : ");//livre
		label_trancheAge = new JLabel("Tranche d'âge : ");//livre
		label_theme = new JLabel("Theme : ");//magazine
		
		label_idT=new JTextField();
		label_titreT=new JTextField();
		label_dateEditionT=new JTextField();
		label_dateArriveeT=new JTextField();
		label_nbExemplaireT=new JTextField();
		label_dateDispoT=new JTextField();
		label_ArtisteT=new JTextField();
		label_nbrPisteT=new JTextField();
		label_typeT=new JTextField();
		label_genreT=new JTextField();
		label_freqT=new JTextField();
		label_editeurT=new JTextField();
		label_nomAuteurT=new JTextField();
		label_prenomAuteurT=new JTextField();
		label_trancheAgeT=new JTextField();
		label_themeT=new JTextField();
		
		this.add(label_id);
		this.add(label_cate);
		this.add(label_titre);
		this.add(label_dateEdition);
		this.add(label_dateArrivee);
		this.add(label_nbExemplaire);
		this.add(label_dateDispo);
		this.add(label_Artiste);
		this.add(label_nbrPiste);
		this.add(label_type);
		this.add(label_genre);
		this.add(label_freq);
		this.add(label_editeur);
		this.add(label_nomAuteur);
		this.add(label_prenomAuteur);
		this.add(label_trancheAge);
		this.add(label_theme);
		
		this.add(label_idT);
		this.add(choix);
		this.add(label_titreT);
		this.add(label_dateEditionT);
		this.add(label_dateArriveeT);
		this.add(label_nbExemplaireT);
		this.add(label_dateDispoT);
		this.add(label_ArtisteT);
		this.add(label_nbrPisteT);
		this.add(label_typeT);
		this.add(label_genreT);
		this.add(label_freqT);
		this.add(label_editeurT);
		this.add(label_nomAuteurT);
		this.add(label_prenomAuteurT);
		this.add(label_trancheAgeT);
		this.add(label_themeT);
		
		label_idT.setBounds(10, 50, 380, 30);
		choix.setBounds(400, 50, 380, 30);
		label_titreT.setBounds(10, 130, 770, 30);
		label_dateEditionT.setBounds(10, 210, 380, 30);
		label_dateArriveeT.setBounds(400, 210, 380, 30);
		label_nbExemplaireT.setBounds(10, 290, 380, 30);
		label_dateDispoT.setBounds(400, 290, 380, 30);	
		label_ArtisteT.setBounds(10, 370, 380, 30);
		label_nbrPisteT.setBounds(400, 370, 380, 30);
		label_typeT.setBounds(10, 450, 380, 30);
		label_genreT.setBounds(400, 450, 380, 30);
		
		label_id.setBounds(10, 10, 380, 30);
		label_cate.setBounds(400, 10, 380, 30);
		label_titre.setBounds(10, 90, 600, 30);
		label_dateEdition.setBounds(10, 170, 380, 30);
		label_dateArrivee.setBounds(400, 170, 380, 30);
		label_nbExemplaire.setBounds(10, 250, 380, 30);
		label_dateDispo.setBounds(400, 250, 380, 30);
		label_Artiste.setBounds(10, 330, 380, 30);
		label_nbrPiste.setBounds(400, 330, 380, 30);
		label_type.setBounds(10, 410, 380, 30);
		label_genre.setBounds(400, 410, 380, 30);
		
		label_freqT.setVisible(false);
		label_editeurT.setVisible(false);
		label_nomAuteurT.setVisible(false);
		label_prenomAuteurT.setVisible(false);
		label_trancheAgeT.setVisible(false);
		label_themeT.setVisible(false);

		
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
		
		valider= new JButton("Enregistrer");
		
		valider.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(label_idT.getText().toString().compareTo("")==0 || label_titreT.getText().toString().compareTo("")==0 || label_dateEditionT.getText().toString().compareTo("")==0 || label_dateArriveeT.getText().toString().compareTo("")==0 || label_nbExemplaireT.getText().toString().compareTo("")==0 || label_dateDispoT.getText().toString().compareTo("")==0)
				{
					JOptionPane.showMessageDialog(null, "Vous devez remplir tout les champs de cette page !", "Information", JOptionPane.ERROR_MESSAGE);
				}
				else if(choix.getSelectedItem().toString().compareTo("CD")==0)
				{
					if(label_ArtisteT.getText().toString().compareTo("")==0 || label_nbrPisteT.getText().toString().compareTo("")==0 || label_genreT.getText().toString().compareTo("")==0 || label_typeT.getText().toString().compareTo("")==0 )
					{
						JOptionPane.showMessageDialog(null, "Vous devez remplir tout les champs de cette page !", "Information", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						int reponse = JOptionPane.showConfirmDialog(null,
		                        "Voulez-vous enregistrer ce document ?",
		                        "Enregistrer ?",
		                        JOptionPane.YES_NO_OPTION);
		                if (reponse == 0) {
		                	//on cr√© le document dans la table de hashage
		                    
		                	// Propre à tous les documents
		                	String id=label_idT.getText(); 
		                    String categorie="cd";
		                    String titre=label_titreT.getText();
		                    String dateEdition=label_dateEditionT.getText();
		                    String dateArrivee= label_dateArriveeT.getText();
		                    int nbExemplaire= new Integer(label_nbExemplaireT.getText());
		                    String dateDispo= label_dateDispoT.getText();
		                    
		                    // Propre aux CD seulement
	                    	String artiste=label_ArtisteT.getText(); 
	                        int nbPistes= new Integer(label_nbrPisteT.getText());
	                        String type=label_typeT.getText();
	                        String genre=label_genreT.getText();
	                      
	                        CD cda= new CD(id, categorie, titre, dateEdition, dateArrivee, nbExemplaire, dateDispo, artiste, nbPistes, type, genre);
	                        logiciel.getFenetre().addDocs(cda);
	                        
	                        
		                	JOptionPane.showMessageDialog(null, "Document bien enregistré.", "Information", JOptionPane.INFORMATION_MESSAGE);
		                	
		                	label_idT.setText("");
		                	label_titreT.setText("");
		                	label_dateEditionT.setText("");
		                	label_dateArriveeT.setText("");
		                	label_nbExemplaireT.setText("");
		                	label_dateDispoT.setText("");
		                	
		                	label_ArtisteT.setText("");
		                	label_nbrPisteT.setText("");
		                	label_genreT.setText("");
		                	label_typeT .setText("");
		                	
		                }
					}
				}
				else if(choix.getSelectedItem().toString().compareTo("journal")==0)
				{
					if(label_freqT.getText().toString().compareTo("")==0 || label_editeurT.getText().toString().compareTo("")==0 || label_typeT.getText().toString().compareTo("")==0 )
					{
						JOptionPane.showMessageDialog(null, "Vous devez remplir tout les champs de cette page !", "Information", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						int reponse = JOptionPane.showConfirmDialog(null,
		                        "Voulez-vous enregistrer ce document ?",
		                        "Enregistrer ?",
		                        JOptionPane.YES_NO_OPTION);
						if (reponse == 0) {
		                	//on cr√© le document dans la table de hashage
							
							// Propre à tous les documents
		                	String id=label_idT.getText(); 
		                    String categorie="journal";
		                    String titre=label_titreT.getText();
		                    String dateEdition=label_dateEditionT.getText();
		                    String dateArrivee= label_dateArriveeT.getText();
		                    int nbExemplaire= new Integer(label_nbExemplaireT.getText());
		                    String dateDispo= label_dateDispoT.getText();
							
		                    // Propres aux journaux
		                    String freq=label_freqT.getText(); 
		                    String type=label_typeT.getText();
		                    String editeur=label_editeurT.getText();
									                    
		                    
		                    
		                    Journal j1= new Journal(id, categorie, titre, dateEdition, dateArrivee, nbExemplaire, dateDispo, type, freq, editeur);
		                    logiciel.getFenetre().addDocs(j1);
		                    
		                    
		                	JOptionPane.showMessageDialog(null, "Document bien enregistré.", "Information", JOptionPane.INFORMATION_MESSAGE);
		                
		                	label_idT.setText("");
		                	label_titreT.setText("");
		                	label_dateEditionT.setText("");
		                	label_dateArriveeT.setText("");
		                	label_nbExemplaireT.setText("");
		                	label_dateDispoT.setText("");
						
		                	label_freqT.setText("");
		                	label_editeurT.setText("");
		                	label_typeT.setText("");
		                	
						
						}
					}
				}
				else if(choix.getSelectedItem().toString().compareTo("Livre")==0)
				{
					if(label_editeurT.getText().toString().compareTo("")==0 || label_nomAuteurT.getText().toString().compareTo("")==0 || label_prenomAuteurT.getText().toString().compareTo("")==0 || label_trancheAgeT.getText().toString().compareTo("")==0 )
					{
						JOptionPane.showMessageDialog(null, "Vous devez remplir tout les champs de cette page !", "Information", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						int reponse = JOptionPane.showConfirmDialog(null,
		                        "Voulez-vous enregistrer ce document ?",
		                        "Enregistrer ?",
		                        JOptionPane.YES_NO_OPTION);
						if (reponse == 0) {
		                	//on cr√© le document dans la table de hashage
		                	
							// Propre à tous les documents
		                	String id=label_idT.getText(); 
		                    String categorie="livre";
		                    String titre=label_titreT.getText();
		                    String dateEdition=label_dateEditionT.getText();
		                    String dateArrivee= label_dateArriveeT.getText();
		                    int nbExemplaire= new Integer(label_nbExemplaireT.getText());
		                    String dateDispo= label_dateDispoT.getText();
		                    
		                    // Propre aux livre seulement
		                    String nomAuteur=label_nomAuteurT.getText(); 
		                    String prenomAuteur=label_prenomAuteurT.getText(); 
		                    String type="Tout public"; // Oubli de ce texteField ^^         
		                    String trancheAge=label_trancheAgeT.getText();   
		                    String editeur=label_editeurT.getText(); 
									                    
		                    
	                		Livre l1= new Livre(id, categorie, titre, dateEdition, dateArrivee, nbExemplaire, dateDispo, nomAuteur, prenomAuteur, type, trancheAge, editeur);
	                		
	                		logiciel.getFenetre().addDocs(l1);
		                    
		                	
		                	JOptionPane.showMessageDialog(null, "Document bien enregistré.", "Information", JOptionPane.INFORMATION_MESSAGE);
		                	
		                	label_idT.setText("");
		                	label_titreT.setText("");
		                	label_dateEditionT.setText("");
		                	label_dateArriveeT.setText("");
		                	label_nbExemplaireT.setText("");
		                	label_dateDispoT.setText("");
		                	
		                	label_editeurT.setText("");
		                	label_nomAuteurT.setText("");
		                	label_prenomAuteurT.setText("");
		                	label_trancheAgeT.setText("");
						
						
						}
					}
				}
				else if(choix.getSelectedItem().toString().compareTo("Magazine")==0)
				{
					if(label_freqT.getText().toString().compareTo("")==0 || label_editeurT.getText().toString().compareTo("")==0 || label_themeT.getText().toString().compareTo("")==0)
					{
						JOptionPane.showMessageDialog(null, "Vous devez remplir tout les champs de cette page !", "Information", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						int reponse = JOptionPane.showConfirmDialog(null,
		                        "Voulez-vous enregistrer ce document ?",
		                        "Enregistrer ?",
		                        JOptionPane.YES_NO_OPTION);
						if (reponse == 0) {
		                	//on cr√© le document dans la table de hashage
							
							// Propre à tous les documents
		                	String id=label_idT.getText(); 
		                    String categorie="magazine";
		                    String titre=label_titreT.getText();
		                    String dateEdition=label_dateEditionT.getText();
		                    String dateArrivee= label_dateArriveeT.getText();
		                    int nbExemplaire= new Integer(label_nbExemplaireT.getText());
		                    String dateDispo= label_dateDispoT.getText();
		                    
		                    // Propres au magazine
		                    String theme=label_themeT.getText(); 
		                    String freq=label_freqT.getText();  
		                    String editeur=label_editeurT.getText(); 
		                    		                 
		                    
		                    Magazine m1 = new Magazine(id, categorie, titre, dateEdition, dateArrivee, nbExemplaire, dateDispo, theme,  freq,  editeur);
		                    
		                    logiciel.getFenetre().addDocs(m1);
		                	
		                	JOptionPane.showMessageDialog(null, "Document bien enregistré.", "Information", JOptionPane.INFORMATION_MESSAGE);
		                
		                	label_idT.setText("");
		                	label_titreT.setText("");
		                	label_dateEditionT.setText("");
		                	label_dateArriveeT.setText("");
		                	label_nbExemplaireT.setText("");
		                	label_dateDispoT.setText("");
						
		                	label_freqT.setText("");
		                	label_editeurT.setText("");
		                	label_themeT.setText("");
						
						}
					}
				}
			}
		});
		this.add(valider);
		valider.setBounds(375, 500, 150, 30);
	}
	
	class ItemState implements ItemListener{
		 
        public void itemStateChanged(ItemEvent e) {
        	if(choix.getSelectedItem().toString().compareTo("CD")==0)
			{
        		label_Artiste.setBounds(10, 330, 380, 30);
        		label_nbrPiste.setBounds(400, 330, 380, 30);
        		label_type.setBounds(10, 410, 380, 30);
        		label_genre.setBounds(400, 410, 380, 30);
        		
        		label_ArtisteT.setBounds(10, 370, 380, 30);
        		label_nbrPisteT.setBounds(400, 370, 380, 30);
        		label_typeT.setBounds(10, 450, 380, 30);
        		label_genreT.setBounds(400, 450, 380, 30);
        		
        		label_Artiste.setVisible(true);
				label_nbrPiste.setVisible(true);
				label_type.setVisible(true);
				label_genre.setVisible(true);
				
				label_ArtisteT.setVisible(true);
				label_nbrPisteT.setVisible(true);
				label_typeT.setVisible(true);
				label_genreT.setVisible(true);
				
				label_freq.setVisible(false);
				label_editeur.setVisible(false);
				label_nomAuteur.setVisible(false);
				label_prenomAuteur.setVisible(false);
				label_trancheAge.setVisible(false);
				label_theme.setVisible(false);
				label_freqT.setVisible(false);
				label_editeurT.setVisible(false);
				label_nomAuteurT.setVisible(false);
				label_prenomAuteurT.setVisible(false);
				label_trancheAgeT.setVisible(false);
				label_themeT.setVisible(false);
			}
			if(choix.getSelectedItem().toString().compareTo("Journal")==0)
			{
				label_type.setBounds(10, 330, 380, 30);
        		label_freq.setBounds(400, 330, 380, 30);
        		label_editeur.setBounds(10, 410, 380, 30);
        		
        		label_typeT.setBounds(10, 370, 380, 30);
        		label_freqT.setBounds(400, 370, 380, 30);
        		label_editeurT.setBounds(10, 450, 380, 30);
        		
				label_type.setVisible(true);
				label_freq.setVisible(true);
				label_editeur.setVisible(true);
				
				label_typeT.setVisible(true);
				label_freqT.setVisible(true);
				label_editeurT.setVisible(true);
				
				
				label_Artiste.setVisible(false);
				label_nbrPiste.setVisible(false);
				label_genre.setVisible(false);
				label_nomAuteur.setVisible(false);
				label_prenomAuteur.setVisible(false);
				label_trancheAge.setVisible(false);
				label_theme.setVisible(false);
				label_ArtisteT.setVisible(false);
				label_nbrPisteT.setVisible(false);
				label_genreT.setVisible(false);
				label_nomAuteurT.setVisible(false);
				label_prenomAuteurT.setVisible(false);
				label_trancheAgeT.setVisible(false);
				label_themeT.setVisible(false);
			}
			if(choix.getSelectedItem().toString().compareTo("Livre")==0)
			{
				label_editeur.setBounds(10, 330, 380, 30);
        		label_nomAuteur.setBounds(400, 330, 380, 30);
        		label_prenomAuteur.setBounds(10, 410, 380, 30);
        		label_trancheAge.setBounds(400, 410, 380, 30);
        		
        		label_editeurT.setBounds(10, 370, 380, 30);
        		label_nomAuteurT.setBounds(400, 370, 380, 30);
        		label_prenomAuteurT.setBounds(10, 450, 380, 30);
        		label_trancheAgeT.setBounds(400, 450, 380, 30);
        		
				label_editeur.setVisible(true);
				label_nomAuteur.setVisible(true);
				label_prenomAuteur.setVisible(true);
				label_trancheAge.setVisible(true);
				
				label_editeurT.setVisible(true);
				label_nomAuteurT.setVisible(true);
				label_prenomAuteurT.setVisible(true);
				label_trancheAgeT.setVisible(true);
				
				label_type.setVisible(false);
				label_freq.setVisible(false);
				label_Artiste.setVisible(false);
				label_nbrPiste.setVisible(false);
				label_genre.setVisible(false);
				label_theme.setVisible(false);
				label_typeT.setVisible(false);
				label_freqT.setVisible(false);
				label_ArtisteT.setVisible(false);
				label_nbrPisteT.setVisible(false);
				label_genreT.setVisible(false);
				label_themeT.setVisible(false);
			}
			if(choix.getSelectedItem().toString().compareTo("Magazine")==0)//si magazine
			{
				label_freq.setBounds(10, 330, 380, 30);
        		label_editeur.setBounds(400, 330, 380, 30);
        		label_theme.setBounds(10, 410, 380, 30);
        		
        		label_freqT.setBounds(10, 370, 380, 30);
        		label_editeurT.setBounds(400, 370, 380, 30);
        		label_themeT.setBounds(10, 450, 380, 30);
        		
				label_freq.setVisible(true);
				label_editeur.setVisible(true);
				label_theme.setVisible(true);
				
				label_freqT.setVisible(true);
				label_editeurT.setVisible(true);
				label_themeT.setVisible(true);
				
				label_Artiste.setVisible(false);
				label_nbrPiste.setVisible(false);
				label_genre.setVisible(false);
				label_nomAuteur.setVisible(false);
				label_prenomAuteur.setVisible(false);
				label_trancheAge.setVisible(false);
				label_type.setVisible(false);
				label_typeT.setVisible(false);
				label_ArtisteT.setVisible(false);
				label_nbrPisteT.setVisible(false);
				label_genreT.setVisible(false);
				label_nomAuteurT.setVisible(false);
				label_prenomAuteurT.setVisible(false);
				label_trancheAgeT.setVisible(false);
			}
               
        }
	}
}