import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


public class ecran_bibliothecaire extends JPanel{
	private JComboBox type;
	private ButtonGroup bouton_choix;
	private JRadioButton Titre;
	private JRadioButton ID;
	private JRadioButton Categorie;
	private JRadioButton Artiste;
	private JTextField saisie;
	
	public ecran_bibliothecaire(int indice) throws Exception
	{
		this.setLayout(null);
		// Crï¿½ation d'un onglet
        JTabbedPane Onglet = new JTabbedPane();

        // Crï¿½ation du panelChassis
        JPanel panelRecherche = new recherche_doc("biblio");
        JPanel panelConsultationEmprunteur = new recherche_emprunteur();
        JPanel panelAjouterDocument = new ajouter_document();
        JPanel panelAjouterUtilisateur= new ajouter_utilisateur();
        prepare_panel(panelConsultationEmprunteur, "Consultation");
        prepare_panel(panelAjouterDocument, "Ajouter");

        // Remplissage des onglets
        Onglet.addTab("Recherche documentaire", null, panelRecherche,
                "Effectuer une recherche documentaire");
        Onglet.addTab("Fiche d'emprunteur", null, panelConsultationEmprunteur,
        "Voir la fiche d'un emprunteur");
        Onglet.addTab("Ajouter un document", null, panelAjouterDocument,
        "Ajouter un document");
        Onglet.addTab("Ajouter un utilisateur", null, panelAjouterUtilisateur,
        "Ajouter un utilisateur");
        
        Onglet.setSelectedIndex(indice);
        this.add(Onglet);

        // Placement des composants
        Onglet.setBounds(0, 0, 800, 600);
		
	}
	
	public void prepare_panel(JPanel jp, String name)
	{
		jp.setLayout(null);
		
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
		
		jp.add(delog);
		
		delog.setBounds(550, 500, 150, 30);
		if(name.compareTo("Recherche")==0)
		{	
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
			Artiste.setVisible(false);
			
			JLabel label_saisie = new JLabel("Veuillez saisir un mot permettant de faire la recherche : ");
			
			saisie = new JTextField();
			
			JButton rech = new JButton("Rechercher");
			rech.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					if(saisie.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Vous devez saisir au moins un mot pour effectuer une recherche !", "Attention", JOptionPane.ERROR_MESSAGE);
					}
					else System.out.println("appel du panel et de la fonction recherche");	
				}
			});
			
			

			jp.add(labelType);
			jp.add(type);
			jp.add(rech);
			jp.add(type_rech);
			jp.add(Titre);
			jp.add(ID);
			jp.add(Categorie);
			jp.add(Artiste);
			jp.add(label_saisie);
			jp.add(saisie);
			
			
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
		else if(name.compareTo("Consultation")==0)
		{

		}
		else if(name.compareTo("Ajouter")==0)
		{

		}
		
		
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
                }
               
        }
	}

}
