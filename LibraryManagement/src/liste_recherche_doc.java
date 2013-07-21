import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.RowSorterEvent;


public class liste_recherche_doc extends JPanel implements MouseListener{
	
	private HashMap liste_doc;
	private String appelant;
	private JTextField saisie1;
	private MyTableModel model;
	private JTable table;
	
	public liste_recherche_doc(String rtype, String rrecherche, String rmot, List documents, String page_appelante)
	{
		
		appelant=page_appelante;
		this.setLayout(null);
		
		//JButton voir_doc= new JButton("Fiche du document");
		
		// Ajout d'un texfield ou la bibliothécaire pourrait faire son choix

		/*saisie1 = new JTextField();
		JLabel label_saisie1 = new JLabel("Choissez l'ID de votre document : ");
		this.add(saisie1);
		this.add(label_saisie1);
		label_saisie1.setBounds(50, 450, 350, 30);
		saisie1.setBounds(260, 450, 250, 30);
		
		voir_doc.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				logiciel.getFenetre().changerEcran(new fiche_document(new Livre(), appelant)); // Appelle la fenetre de la fiche du document
			}
		});
		this.add(voir_doc);
		voir_doc.setBounds(350, 500, 200, 30);*/
		
		//on rÔøΩcupÔøΩre la liste des documents correspondant ÔøΩ la recherche
		Iterator i = logiciel.getFenetre().getDocs().keySet().iterator();
		liste_doc = new HashMap();

		while(i.hasNext()){
			
			String idDoc = (String)i.next();
			Document doc = (Document)logiciel.getFenetre().getDocs().get(idDoc);
			
			if(rtype.equalsIgnoreCase("tout")){
				
				if(rrecherche.equalsIgnoreCase("titre")){
					if(doc.getTitre().toLowerCase().contains(rmot.toLowerCase())){
						liste_doc.put(doc.getId(), doc);
					}
				}else if(rrecherche.equalsIgnoreCase("id")){
					if(doc.getId().toLowerCase().contains(rmot.toLowerCase())){
						liste_doc.put(doc.getId(), doc);
					}
				}else if(rrecherche.equalsIgnoreCase("categorie")){
					if(doc.getCategorie().toLowerCase().contains(rmot.toLowerCase())){
						liste_doc.put(doc.getId(), doc);
					}
				}
				
			}else if(rtype.equalsIgnoreCase("cd") && doc.getCategorie().equalsIgnoreCase("cd")){
				
				CD cd = (CD)doc;
				
				if(rrecherche.equalsIgnoreCase("titre")){
					if(cd.getTitre().toLowerCase().contains(rmot.toLowerCase())){
						liste_doc.put(cd.getId(), cd);
					}
				}else if(rrecherche.equalsIgnoreCase("id")){
					if(cd.getId().toLowerCase().contains(rmot.toLowerCase())){
						liste_doc.put(cd.getId(), cd);
					}
				}else if(rrecherche.equalsIgnoreCase("categorie")){
					if(cd.getCategorie().toLowerCase().contains(rmot.toLowerCase())){
						liste_doc.put(cd.getId(), cd);
					}
				}else if(rrecherche.equalsIgnoreCase("artiste")){
					if(cd.getArtiste().toLowerCase().contains(rmot.toLowerCase())){
						liste_doc.put(cd.getId(), cd);
					}
				}
				
			}else if(rtype.equalsIgnoreCase("livre") && doc.getCategorie().equalsIgnoreCase("livre")){
				
				Livre livre = (Livre)doc;
				
				if(rrecherche.equalsIgnoreCase("titre")){
					if(livre.getTitre().toLowerCase().contains(rmot.toLowerCase())){
						liste_doc.put(livre.getId(), livre);
					}
				}else if(rrecherche.equalsIgnoreCase("id")){
					if(livre.getId().toLowerCase().contains(rmot.toLowerCase())){
						liste_doc.put(livre.getId(), livre);
					}
				}else if(rrecherche.equalsIgnoreCase("categorie")){
					if(livre.getCategorie().toLowerCase().contains(rmot.toLowerCase())){
						liste_doc.put(livre.getId(), livre);
					}
				}
	
			}else if(rtype.equalsIgnoreCase("magazine") && doc.getCategorie().equalsIgnoreCase("magazine")){
				
				Magazine mag = (Magazine)doc;
				
				if(rrecherche.equalsIgnoreCase("titre")){
					if(mag.getTitre().toLowerCase().contains(rmot.toLowerCase())){
						liste_doc.put(mag.getId(), mag);
					}
				}else if(rrecherche.equalsIgnoreCase("id")){
					if(mag.getId().toLowerCase().contains(rmot.toLowerCase())){
						liste_doc.put(mag.getId(), mag);
					}
				}else if(rrecherche.equalsIgnoreCase("categorie")){
					if(mag.getCategorie().toLowerCase().contains(rmot.toLowerCase())){
						liste_doc.put(mag.getId(), mag);
					}
				}
	
			}else if(rtype.equalsIgnoreCase("journal") && doc.getCategorie().equalsIgnoreCase("journal")){
				
				Journal journal = (Journal)doc;
				
				if(rrecherche.equalsIgnoreCase("titre")){
					if(journal.getTitre().toLowerCase().contains(rmot.toLowerCase())){
						liste_doc.put(journal.getId(), journal);
					}
				}else if(rrecherche.equalsIgnoreCase("id")){
					if(journal.getId().toLowerCase().contains(rmot.toLowerCase())){
						liste_doc.put(journal.getId(), journal);
					}
				}else if(rrecherche.equalsIgnoreCase("categorie")){
					if(journal.getCategorie().toLowerCase().contains(rmot.toLowerCase())){
						liste_doc.put(journal.getId(), journal);
					}
				}
			}
		}//Fin du while
		
		
		if(liste_doc.isEmpty()){
			//puis on les affiche dans le label liste
			JLabel liste = new JLabel("Aucun document ne correspond à votre recherche");
			JScrollPane defilement_label = new JScrollPane(liste);
			this.add(defilement_label);
			defilement_label.setBounds(50, 10, 700, 400);
		}else{
			Iterator ite = this.liste_doc.keySet().iterator();
			String[][] donnees = new String[this.liste_doc.size()][3];
			int cpt = 0;
			while(ite.hasNext()){
				
				String idDoc = (String)ite.next();
				Document doc = (Document)this.liste_doc.get(idDoc);
				donnees[cpt][0] = idDoc;
				donnees[cpt][1] = doc.getTitre();
				donnees[cpt][2] = doc.getCategorie();
				cpt++;
				
			}
			String[] nomColonnes = {"id","document", "Catégorie"};
			this.model = new MyTableModel(donnees, nomColonnes);
			this.table = new JTable(this.model);
			//this.table.setAutoCreateRowSorter(true);
			this.table.addMouseListener(this);

			
			//puis on les affiche dans le label liste
			JLabel liste = new JLabel(liste_doc.size() + " document(s) trouvés.");
			JScrollPane defilement_label = new JScrollPane(this.table);
			this.add(defilement_label);
			defilement_label.setBounds(50, 10, 700, 400);
			JLabel label_saisie = new JLabel("");
			//JTextField saisie = new JTextField();
			this.add(label_saisie);
			//this.add(saisie);
			label_saisie.setBounds(50, 500, 150, 30);
			//saisie.setBounds(200, 500, 150, 30);
		}
		
		
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
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2){//double click
			
			//this.model.fireTableChanged(e);
			this.model = (MyTableModel) this.table.getModel();
			
			// System.out.println("ID selectionné : " + this.model.getValueAt(((JTable)(e.getSource())).getSelectedRow(), 0) );
		
			//Recuperation des donnees de l'emprunteur
			Iterator ite = this.liste_doc.keySet().iterator();
			
			while(ite.hasNext()){
				String idDoc = (String)ite.next();
				if(idDoc == this.model.getValueAt(((JTable)(e.getSource())).getSelectedRow(), 0)){
					//System.out.println(this.model.getValueAt(((JTable)(e.getSource())).getSelectedRow(), 2));
					
					if(this.model.getValueAt(((JTable)(e.getSource())).getSelectedRow(), 2).equals("livre")){
						Livre livre = (Livre) this.liste_doc.get(idDoc);
						logiciel.getFenetre().changerEcran(new fiche_document(livre, appelant));
					}else if(this.model.getValueAt(((JTable)(e.getSource())).getSelectedRow(), 2).equals("cd")){
						CD cd = (CD) this.liste_doc.get(idDoc);
						logiciel.getFenetre().changerEcran(new fiche_document(cd, appelant));
					}else if(this.model.getValueAt(((JTable)(e.getSource())).getSelectedRow(), 2).equals("journal")){
						Journal journal = (Journal) this.liste_doc.get(idDoc);
						logiciel.getFenetre().changerEcran(new fiche_document(journal, appelant));
					}else if(this.model.getValueAt(((JTable)(e.getSource())).getSelectedRow(), 2).equals("magazine")){
						Magazine mag = (Magazine) this.liste_doc.get(idDoc);
						logiciel.getFenetre().changerEcran(new fiche_document(mag, appelant));
					}
				}
				
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
