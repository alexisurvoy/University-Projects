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


public class liste_emprunteur extends JPanel implements MouseListener{
	
	private HashMap liste_utils;
	private MyTableModel model;
	
	liste_emprunteur(String name, String id, String type)
	{
		this.setLayout(null);
		
		//on prend toute la liste de personne trouv√© et on l'affiche dans contenu_recherche
		if(type.compareTo("emprunt_fraude")==0)
		{
			
		}
		else if(type.compareTo("emprunt_seul")==0)
		{
			
		}
		
		//On récupère la liste des utilisateurs
		Iterator i = logiciel.getFenetre().getUtilisateurs().keySet().iterator();
		this.liste_utils = new HashMap();
		
		//On parcoure la liste pour récupérer les utilisateurs qui nous interessent
		while(i.hasNext()){
			
			String idUtil = (String)i.next();
			Personne util = (Personne)logiciel.getFenetre().getUtilisateurs().get(idUtil);
			
			if((util._id == id) || (util._login.toLowerCase().contains(name.toLowerCase())) || (util._nom.toLowerCase().contains(name.toLowerCase())) || util._prenom.toLowerCase().contains(name.toLowerCase())){
				this.liste_utils.put(idUtil, util);
			}
			
		}
		
		
		
		if(this.liste_utils.isEmpty()){
			JLabel liste = new JLabel("Aucun utilisateur ne correspond à votre recherche");
			JScrollPane defilement_label = new JScrollPane(liste);
			this.add(defilement_label);
			defilement_label.setBounds(50, 10, 700, 400);
		}else{
			Iterator ite = this.liste_utils.keySet().iterator();
			String[][] donnees = new String[this.liste_utils.size()][5];
			int cpt = 0;
			while(ite.hasNext()){
				String idUtil = (String)ite.next();
				Personne util = (Personne)this.liste_utils.get(idUtil);
				donnees[cpt][0] = idUtil;
				donnees[cpt][1] = util._nom;
				donnees[cpt][2] = util._prenom;
				donnees[cpt][3] = util._mail;
				donnees[cpt][4] = util._type;
				cpt++;
			}
			
			String[] nomColonnes = {"id","nom", "prenom", "mail", "Type"};
			model = new MyTableModel(donnees, nomColonnes);
			JTable table = new JTable(model);
			//table.setAutoCreateRowSorter(true);
			
			table.addMouseListener(this);
			
			//puis on les affiche dans le label liste
			//JLabel liste = new JLabel(liste_utils.size() + " document(s) trouvés.");
			JScrollPane defilement_label = new JScrollPane(table);
			this.add(defilement_label);
			defilement_label.setBounds(50, 10, 700, 400);
			//JLabel label_saisie = new JLabel("");
			//JTextField saisie = new JTextField();
			//this.add(label_saisie);
			//this.add(saisie);
			//label_saisie.setBounds(50, 500, 150, 30);
			//saisie.setBounds(200, 500, 150, 30);
		}

		
		JButton retour = new JButton("Effectuer une autre recherche");
		retour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
						logiciel.getFenetre().changerEcran(new ecran_bibliothecaire(1));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		this.add(retour);
		
		//defilement_label.setBounds(50, 10, 700, 400);
		retour.setBounds(50, 500, 250, 30);
		
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
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2){//double click
			System.out.println("ID selectionné : " + this.model.getValueAt(((JTable)(e.getSource())).getSelectedRow(), 0) );
		
			//Recuperation des donnees de l'emprunteur
			Iterator ite = this.liste_utils.keySet().iterator();
			
			while(ite.hasNext()){
				String idUtil = (String)ite.next();
				if(idUtil == this.model.getValueAt(((JTable)(e.getSource())).getSelectedRow(), 0)){
					if(this.model.getValueAt(((JTable)(e.getSource())).getSelectedRow(), 4) == "Emprunteur"){
						Emprunteur emp = (Emprunteur) this.liste_utils.get(idUtil);
						logiciel.getFenetre().changerEcran(new fiche_emprunteur(emp, 1));
					}else{
						Bibliothecaire emp = (Bibliothecaire) this.liste_utils.get(idUtil);
						logiciel.getFenetre().changerEcran(new fiche_emprunteur(emp, 1));
					}
				}
				
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
