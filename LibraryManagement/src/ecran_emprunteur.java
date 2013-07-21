import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class ecran_emprunteur extends JPanel{
	
	public ecran_emprunteur(Emprunteur empt) throws Exception
	{
		this.setLayout(null);
		
		// Cr�ation d'un onglet
        JTabbedPane Onglet = new JTabbedPane();

        // Cr�ation du panelChassis
        JPanel panelRecherche = new recherche_doc("emprun");
        //
        //
        //
        //Important, passer au constructeur l'instance de l'emprunteur !!
        JPanel panelConsultationEmprunt = new fiche_emprunteur(empt, 0);

        // Remplissage des onglets
        Onglet.addTab("Recherche documentaire", null, panelRecherche,
        "Effectuer une recherche documentaire");
        Onglet.addTab("Liste des emprunts", null, panelConsultationEmprunt,
        "Consulter sa liste de documents emprunt�s");
        
        
        this.add(Onglet);

        // Placement des composants
        Onglet.setBounds(0, 0, 800, 600);
		
	}

}
