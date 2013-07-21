
public class logiciel {
	
	private logiciel logi;
	private static Fenetre laFenetre;
	
	public logiciel()
	{
		laFenetre = new Fenetre();
		Accueil acceuil = new Accueil();
		laFenetre.changerEcran(acceuil);
	}
	
	public static Fenetre getFenetre()
	{
		return laFenetre;
	}
}
