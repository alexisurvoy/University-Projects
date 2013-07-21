import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ajouter_utilisateur extends JPanel{
	
	private JButton ajouter;

	private JLabel label_id;
	private JLabel label_type;
	private JLabel label_login;
	private JLabel label_mdp;
	private JLabel label_nom;
	private JLabel label_prenom;
	private JLabel label_mail;

	private JTextField label_idT;
	private JTextField label_loginT;
	private JPasswordField label_mdpT;
	private JTextField label_nomT;
	private JTextField label_prenomT;
	private JTextField label_mailT;
	
	private JComboBox choix;
	
	public ajouter_utilisateur()
	{
		
		this.setLayout(null);
		
		choix = new JComboBox();
		
		choix.addItem("Emprunteur");
        choix.addItem("Bibliothecaire");
        //choix.addItemListener(new ItemState());
        
        this.add(choix);
        choix.setBounds(400, 50, 200, 30);

		label_id = new JLabel("ID : ");
		label_type = new JLabel("Type d'utilisateur : ");
		label_login = new JLabel("Pseudo : ");
		label_mdp = new JLabel("Mot de passe : ");
		label_nom = new JLabel("Nom : ");
		label_prenom = new JLabel("Prenom : ");
		label_mail = new JLabel("Mail : ");
		
		label_idT=new JTextField();
		label_loginT=new JTextField();
		label_mdpT=new JPasswordField();
		label_nomT=new JTextField();
		label_prenomT=new JTextField();
		label_mailT=new JTextField();
		
		this.add(label_id);
		this.add(label_type);
		this.add(label_login);
		this.add(label_mdp);
		this.add(label_nom);
		this.add(label_prenom);
		this.add(label_mail);
		
		this.add(label_idT);
		this.add(label_loginT);
		this.add(label_mdpT);
		this.add(label_nomT);
		this.add(label_prenomT);
		this.add(label_mailT);
		
		label_idT.setBounds(10, 50, 380, 30);
		label_loginT.setBounds(10, 130, 380, 30);
		label_mdpT.setBounds(400, 130, 380, 30);
		label_nomT.setBounds(10, 210, 380, 30);
		label_prenomT.setBounds(400, 210, 380, 30);
		label_mailT.setBounds(10, 290, 380, 30);
		
		label_id.setBounds(10, 10, 380, 30);
		label_type.setBounds(400, 10, 380, 30);
		label_login.setBounds(10, 90, 600, 30);
		label_mdp.setBounds(400, 90, 380, 30);
		label_nom.setBounds(10, 170, 380, 30);
		label_prenom.setBounds(400, 170, 380, 30);
		label_mail.setBounds(10, 250, 380, 30);
		
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
		
		ajouter= new JButton("Ajouter l'utilisateur");
		
		ajouter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(label_idT.getText().toString().compareTo("")==0 || label_loginT.getText().toString().compareTo("")==0 || label_nomT.getText().toString().compareTo("")==0 || label_prenomT.getText().toString().compareTo("")==0 || label_mailT.getText().toString().compareTo("")==0)
				{
					String type=choix.getSelectedItem().toString();
					JOptionPane.showMessageDialog(null, "Vous devez remplir tout les champs de cette page !" + type, "Information", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
						int reponse = JOptionPane.showConfirmDialog(null,
		                        "Voulez-vous enregistrer cet utilisateur ?",
		                        "Enregistrer ?",
		                        JOptionPane.YES_NO_OPTION);
		                if (reponse == 0) {
		                	//on cr√© le document dans la table de hashage
		                    
		                    String id=label_idT.getText();
		                	String login = label_loginT.getText();
		                	String nom = label_nomT.getText();
		                	String prenom = label_prenomT.getText();
		                	String mail = label_mailT.getText();
		                	String mdp="";
							try {
								mdp = AeSimpleSHA1.SHA1(label_mdpT.getText());
							} catch (NoSuchAlgorithmException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (UnsupportedEncodingException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		                	
		                	
		                	if(choix.getSelectedItem().toString().compareTo("Emprunteur")==0){
		                		Emprunteur e = new Emprunteur(id, login, nom, prenom, mdp, mail, 5);
		                		logiciel.getFenetre().addUtilisateur(e);
		                		
		                	}
		                	else if(choix.getSelectedItem().toString().compareTo("Bibliothecaire")==0){
		                		Bibliothecaire b = new Bibliothecaire(id, login, nom, prenom, mdp, mail);
		                		logiciel.getFenetre().addUtilisateur(b);
		                		
		                	}
		                	                        
	                        
		                	JOptionPane.showMessageDialog(null, "Utilisateur bien enregistré.", "Information", JOptionPane.INFORMATION_MESSAGE);
		                	
		                	label_idT.setText("");
		                	label_loginT.setText("");
		                	label_nomT.setText("");
		                	label_prenomT.setText("");
		                	label_mailT.setText("");
		                	label_mdpT.setText("");
		                }
					}
				}
		});
		this.add(ajouter);
		ajouter.setBounds(375, 500, 150, 30);	               
	}

}
