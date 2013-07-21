import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//import recherche_doc.ItemState;


public class recherche_emprunteur extends JPanel{

	private ButtonGroup bouton_choix;
	private JRadioButton emprunt_seul;
	private JRadioButton emprunt_fraude;
	private JTextField nom;
	private JTextField ID;
	private JLabel label_saisie;
	private JLabel label_nom;
	private JLabel label_ID;
	
	public recherche_emprunteur() throws Exception
	{
		this.setLayout(null);
		
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
		
		JLabel labelType = new JLabel("Type de recherche : ");	
		
		
		bouton_choix = new ButtonGroup();
		emprunt_seul = new JRadioButton("Emprunteur normal");
		emprunt_fraude = new JRadioButton("Emprunteur n'ayant pas respecté les délais d'emprunts");
		bouton_choix.add(emprunt_seul);
		bouton_choix.add(emprunt_fraude);
		emprunt_seul.setSelected(true);
		
		emprunt_seul.addActionListener(new StateListener());
	    emprunt_fraude.addActionListener(new StateListener());
		
		label_saisie = new JLabel("Veuillez saisir au moins un des deux champs suivants : ");
		label_nom = new JLabel("Nom : ");
		label_ID = new JLabel("ID : ");
		
		nom = new JTextField();
		ID= new JTextField();
		
		JButton rech = new JButton("Rechercher");
		rech.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(emprunt_fraude.isSelected()==true)
				{
					logiciel.getFenetre().changerEcran(new liste_emprunteur("", "", "emprunt_fraude"));
				}
				
				else logiciel.getFenetre().changerEcran(new liste_emprunteur(nom.getText().toString(), ID.getText().toString(), "emprunt_seul"));
			}
		});
			

		this.add(labelType);
		this.add(rech);
		this.add(emprunt_seul);
		this.add(emprunt_fraude);
		this.add(ID);
		this.add(nom);
		this.add(label_nom);
		this.add(label_saisie);
		this.add(label_ID);
		
		
		labelType.setBounds(70, 70, 150, 30);
		rech.setBounds(100, 500, 150, 30);
		emprunt_seul.setBounds(50, 110, 700, 30);
		emprunt_fraude.setBounds(50, 150, 700, 30);
		ID.setBounds(200, 270, 250, 30);
		nom.setBounds(200, 320, 250, 30);
		label_nom.setBounds(50, 320, 100, 30);
		label_saisie.setBounds(50, 190, 450, 30);
		label_ID.setBounds(50, 270, 250, 30);
	}
	
	class StateListener implements ActionListener{
		 
        public void actionPerformed(ActionEvent e) {
                if(emprunt_fraude.isSelected()==true)
                {
                	label_saisie.setVisible(false);
            		label_nom.setVisible(false);
            		label_ID.setVisible(false);
            		nom.setVisible(false);
            		ID.setVisible(false);
                }
                else
                {
                	label_saisie.setVisible(true);
            		label_nom.setVisible(true);
            		label_ID.setVisible(true);
            		nom.setVisible(true);
            		ID.setVisible(true);
                }
        }
        
}
}
