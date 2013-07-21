
import java.util.Calendar;

public class Date {
	
	private int _jour;
	private int _mois;
	private int _annee;
	private String _date;
	
	public Date(){
		Calendar calendrier = Calendar.getInstance();
		_jour=calendrier.get(Calendar.DAY_OF_MONTH);
		_mois=calendrier.get(Calendar.MONTH);
		_annee=calendrier.get(Calendar.YEAR);
		_date= new String();
		_date=_date.valueOf(_jour)+"/"+_date.valueOf(_mois)+"/"+_date.valueOf(_annee);
	}
	
	public Date(String str){
		_annee =Integer.parseInt(str.substring(str.lastIndexOf("/")+1,str.length()));
		_mois =Integer.parseInt(str.substring(str.lastIndexOf("/",str.lastIndexOf("/")-1)+1,str.lastIndexOf("/")));
		_jour =Integer.parseInt(str.substring(str.lastIndexOf("/",str.lastIndexOf("/",str.lastIndexOf("/")-1)-1)+1,str.lastIndexOf("/",str.lastIndexOf("/")-1)));
		_date=new String();
		_date=_date.valueOf(_jour)+"/"+_date.valueOf(_mois)+"/"+_date.valueOf(_annee);
	}
	
	public Date(Date d){
		this._jour=d._jour;
		this._mois=d._mois;
		this._annee=d._annee;
		this._date=d._date;
	}
	
	public boolean estAvant(Date d){
		if (_annee<d._annee)
		{
			return true;
		}
			else if (this._annee==d._annee && this._mois<d._mois)
			{
				return true;
			}
			else if (this._annee==d._annee && this._mois==d._mois && this._jour<d._jour)
			{
				return true;
			}
			else
			{
				return false;
			}
	}
	
	public boolean estApres(Date d){
		if (this.estAvant(d)==true)
		{
			return false;
		}
		else 
		{
			return true;
		}
	}
	
	public void ajouteUnMois(){
		if(this._jour>29){
			this._jour=29;
		}
		this._mois=this._mois+1;
	}
	
	
	public int getJour(){
		return _jour;
	}
	
	public void setJour(int jour){
		_jour=jour;
	}
	public int getMois(){
		return _mois;
	}
	
	public void setMois(int mois){
		_mois=mois;
	}
	
	public int getAnnee(){
		return _annee;
	}
	
	public void setAnnee(int annee){
		_annee=annee;
	}
	
	public String affiche(){
		return _date;
	}

}
	


