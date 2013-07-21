import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class fiche_emprunteur extends JPanel implements MouseListener{
	
	private JButton fiche;
	private JButton suppress;
	private JButton valider;
	private JButton autre_recherche;
	private JTextField id;
	private JTextField nom;
	private JTextField prenom;
	private JTextField mail;
	private HashMap liste_docEmp;
	private MyTableModel model;
	private JTable table;
	private Personne empr2;
	
	fiche_emprunteur(final Personne empr, int appelant)
	{
		this.setLayout(null);
		
		empr2=empr;
		//Emprunteur empr = new Emprunteur("H0057", "login", "Benito", "Lucas", "0000","lucas.benito@utbm.fr", 5);
		
		JLabel label_id = new JLabel("ID : ");
		id = new JTextField(empr.getId());
		JLabel label_nom = new JLabel("Nom : ");
		nom = new JTextField(empr.get_nom());
		JLabel label_prenom = new JLabel("Prenom : ");
		prenom = new JTextField(empr.get_prenom());
		JLabel label_mail = new JLabel("Mail : ");
		mail = new JTextField(empr.get_mail());
		
		//Affichage des emprunts
		Iterator i = logiciel.getFenetre().getDocsEmpruntes().keySet().iterator();
		this.liste_docEmp = new HashMap();

		while(i.hasNext()){
			
			String idUtil = (String)i.next();			
			if(idUtil.contains(empr._id+"-")){
				Emprunt emp = (Emprunt)logiciel.getFenetre().getDocsEmpruntes().get(idUtil);
				Document doc = emp.get_Document1();
				this.liste_docEmp.put(doc.getId(), emp);
			}
		}
		

		if(this.liste_docEmp.isEmpty()){
			//Aucun emprunt
			JLabel liste = new JLabel("Aucun emprunt à votre actif.");
			JScrollPane defilement_label = new JScrollPane(liste);
			this.add(defilement_label);
			defilement_label.setBounds(50, 200, 700, 200);
		}else{
			Iterator ite = this.liste_docEmp.keySet().iterator();
			String[][] donnees = new String[this.liste_docEmp.size()][4];
			int cpt = 0;
			while(ite.hasNext()){
				String idDoc = (String)ite.next();
				Emprunt emp = (Emprunt)this.liste_docEmp.get(idDoc);
				Document doc = emp.get_Document1();
				donnees[cpt][0] = idDoc;
				donnees[cpt][1] = doc.getTitre();
				donnees[cpt][2] = doc.getCategorie();
				donnees[cpt][3] = emp.get_dateEmprunt();
				cpt++;
			}
			String[] nomColonnes = {"id","document", "Catégorie", "Date de retour"};
			this.model = new MyTableModel(donnees, nomColonnes);
			this.table = new JTable(this.model);
			
			if(Accueil.user._type.equals("Bibliothecaire")){
				this.table.addMouseListener(this);
			}
			
			//this.table.setAutoCreateRowSorter(true);
			//this.table.addMouseListener(this);

			
			//puis on les affiche dans le label liste
			JScrollPane defilement_label = new JScrollPane(this.table);
			this.add(defilement_label);
			defilement_label.setBounds(50, 200, 700, 200);
		}
		
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
		
		fiche = new JButton("Modifier la fiche");
		fiche.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				//logiciel.getFenetre().changerEcran(new modifier_emprunteur(empt));
				suppress.setEnabled(false);
				suppress.setVisible(false);
				valider.setEnabled(true);
				valider.setVisible(true);
				id.setEnabled(true);
				nom.setEnabled(true);
				prenom.setEnabled(true);
				mail.setEnabled(true);
			}
		});
		
		suppress = new JButton("Supprimer la fiche");
		suppress.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					int reponse = JOptionPane.showConfirmDialog(null,
	                        "Voulez-vous supprimer cette fiche ?",
	                        "Suppression",
	                        JOptionPane.YES_NO_OPTION);
	                if (reponse == 0) {
	                	//on supprime la fiche dans la table de hashage
	                	logiciel.getFenetre().removeUtilisateurs(id.getText());
	                	
	                	logiciel.getFenetre().changerEcran(new ecran_bibliothecaire(1));
	                }
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		autre_recherche = new JButton("Effectuer une autre recherche");
		autre_recherche.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					
	                	logiciel.getFenetre().changerEcran(new ecran_bibliothecaire(1));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		valider= new JButton("Valider la modification");
		
		valider.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int reponse = JOptionPane.showConfirmDialog(null,
                        "Voulez-vous enregistrer les modifications ?",
                        "Enregistrer ?",
                        JOptionPane.YES_NO_OPTION);
                if (reponse == 0) {
                	suppress.setEnabled(true);
    				suppress.setVisible(true);
    				valider.setEnabled(false);
    				valider.setVisible(false);
                	//on enregistre les modifs
    				id.setEnabled(false);
    				nom.setEnabled(false);
    				prenom.setEnabled(false);
    				mail.setEnabled(false);
                }
			}
		});
		valider.setEnabled(false);
		valider.setVisible(false);
		
		id.setEnabled(false);
		nom.setEnabled(false);
		prenom.setEnabled(false);
		mail.setEnabled(false);
		if(appelant==0)
		{
			valider.setEnabled(false);
			valider.setVisible(false);
			fiche.setVisible(false);
			fiche.setEnabled(false);
			autre_recherche.setVisible(false);
			autre_recherche.setEnabled(false);
			suppress.setVisible(false);
			suppress.setEnabled(false);
		}
		
		
		
		this.add(delog);
		this.add(fiche);
		this.add(suppress);
		this.add(id);
		this.add(label_id);
		this.add(nom);
		this.add(label_nom);
		this.add(prenom);
		this.add(label_prenom);
		this.add(mail);
		this.add(label_mail);
		this.add(valider);
		this.add(autre_recherche);
		
		
		delog.setBounds(550, 500, 150, 30);
		fiche.setBounds(325, 500, 200, 30);
		suppress.setBounds(50, 500, 250, 30);
		valider.setBounds(50, 500, 250, 30);
		id.setBounds(200, 50, 150, 30);
		label_id.setBounds(50, 50, 150, 30);
		nom.setBounds(200, 100, 150, 30);
		label_nom.setBounds(50, 100, 150, 30);
		prenom.setBounds(500, 100, 150, 30);
		label_prenom.setBounds(350, 100, 150, 30);
		mail.setBounds(200, 150, 300, 30);
		label_mail.setBounds(50, 150, 150, 30);
		autre_recherche.setBounds(50, 540, 250, 30);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount() == 2){//double click
			int reponse = JOptionPane.showConfirmDialog(null,
                    "Confirmer ce document comme rendu ?",
                    "Confirmer",
                    JOptionPane.YES_NO_OPTION);
            if (reponse == 0) {
            	
            	// Supprimer l'emprunt
            	String key = empr2.getId()+"-" + this.model.getValueAt(((JTable)(e.getSource())).getSelectedRow(), 0);
            	logiciel.getFenetre().removeDocsEmpruntes(key);
            	String cle = this.model.getValueAt(((JTable)(e.getSource())).getSelectedRow(), 0).toString();
            	Document d = (Document) logiciel.getFenetre().getDocs().get(cle);
            	((Emprunteur) empr2).setNbEmpruntRestant(((Emprunteur) empr2).getNbEmpruntRestant()+1); // on ajoute un exemplaire en plus autorisé à l'emprunteur
            	d.setNbExemplaire(d.getNbExemplaire()+1);
            	
            	JOptionPane.showMessageDialog(null, "Document bien rendu !", "Information", JOptionPane.INFORMATION_MESSAGE);
            	logiciel.getFenetre().changerEcran(new fiche_emprunteur(empr2, 1));
            }
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
