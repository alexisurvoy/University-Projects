import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.jdom.Element;
import org.jdom.input.SAXBuilder;


public class recherche_doc extends JPanel{

		private JComboBox type;
		private ButtonGroup bouton_choix;
		private JRadioButton Titre;
		private JRadioButton ID;
		private JRadioButton Categorie;
		private JRadioButton Artiste;
		private JTextField saisie;
		private String appelant;
		
		private List listeDocs;
		
		public recherche_doc(String perso_appelant) throws Exception
		{
			//Rï¿½cupï¿½ration des documents
			chargerDocuments();
			
			appelant=perso_appelant;
			this.setLayout(null);
			
			JButton delog= new JButton("Se dŽconnecter");
			
			delog.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					int reponse = JOptionPane.showConfirmDialog(null,
	                        "Voulez-vous dŽconnecter ?",
	                        "DŽconnection",
	                        JOptionPane.YES_NO_OPTION);
	                if (reponse == 0) {
	                	logiciel.getFenetre().changerEcran(new Accueil());
	                }
				}
			});
			
			this.add(delog);
			
			delog.setBounds(550, 500, 150, 30);
			
			JLabel labelType = new JLabel("Type de document : ");
			
			type = new JComboBox();
			type.addItem("Tout");
			type.addItem("CD");
			type.addItem("Journal");
			type.addItem("Livre");
			type.addItem("Magazine");
			type.addItemListener(new ItemState());
			
			JLabel type_rech = new JLabel("Veuillez cocher la case correspondant au type de recherche que vous voulez effectuer : ");
			
			bouton_choix = new ButtonGroup();
			Titre = new JRadioButton("Titre");
			ID = new JRadioButton("ID");
			Categorie = new JRadioButton("CatŽgorie");
			Artiste = new JRadioButton("Artiste");
			bouton_choix.add(Titre);
			bouton_choix.add(ID);
			bouton_choix.add(Categorie);
			bouton_choix.add(Artiste);
			Titre.setSelected(true);
			Artiste.setVisible(false);
			
			JLabel label_saisie = new JLabel("Veuillez saisir un mot permettant de faire la recherche : ");
			
			saisie = new JTextField();
			
			JButton rech = new JButton("Rechercher");
			rech.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {

						String selected="";
						if(Titre.isSelected())
						{
							selected = "Titre";
						}
						else if(ID.isSelected())
						{
							selected = "ID";
						}
						else if(Categorie.isSelected())
						{
							selected = "Categorie";
						}
						else if(Artiste.isSelected())
						{
							selected = "Artiste";
						}
						if(appelant.compareTo("biblio")==0)
						{
							logiciel.getFenetre().changerEcran(new liste_recherche_doc(type.getSelectedItem().toString(), selected, saisie.getText(), listeDocs, appelant));
						}
						else if(appelant.compareTo("emprun")==0)
						{
							logiciel.getFenetre().changerEcran(new liste_recherche_doc(type.getSelectedItem().toString(), selected, saisie.getText(), listeDocs, appelant));
						}
					}
			});
				

			this.add(labelType);
			this.add(type);
			this.add(rech);
			this.add(type_rech);
			this.add(Titre);
			this.add(ID);
			this.add(Categorie);
			this.add(Artiste);
			this.add(label_saisie);
			this.add(saisie);
			
			
			labelType.setBounds(70, 70, 150, 30);
			type.setBounds(220, 70, 150, 30);
			rech.setBounds(100, 500, 150, 30);
			type_rech.setBounds(50, 110, 700, 30);
			Titre.setBounds(50, 150, 100, 30);
			ID.setBounds(50, 190, 100, 30);
			Categorie.setBounds(50, 230, 100, 30);
			Artiste.setBounds(50, 270, 100, 30);
			label_saisie.setBounds(50, 320, 450, 30);
			saisie.setBounds(450, 320, 250, 30);
		}
		
		public void chargerDocuments() throws Exception{
			org.jdom.Document document = new org.jdom.Document();
			SAXBuilder sxb = new SAXBuilder();
			try{
				document = sxb.build(new File("XML/documents.xml"));
			}
			catch(Exception e){}

			Element racine = document.getRootElement();

			//Rï¿½cupï¿½re tout les documents
			this.listeDocs = racine.getChildren();
		}
		
		class ItemState implements ItemListener{
			 
	        public void itemStateChanged(ItemEvent e) {
	                if(type.getSelectedItem().toString().compareTo("CD")==0)
	                {
	                	Artiste.setVisible(true);
	                }
	                if(type.getSelectedItem().toString().compareTo("CD")!=0)
	                {
	                	Artiste.setVisible(false);
	                	if(Artiste.isSelected()==true)
	                	{
	                		Titre.setSelected(true);
	                	}
	                }
	               
	        }
		}
}
