import javax.swing.table.AbstractTableModel;


public class MyTableModel extends AbstractTableModel{

	private String[] lesColonnes;
	private String[][] lesDonnees;
	
	public MyTableModel(String[][] data, String[] cols){
		this.lesColonnes = cols;
		this.lesDonnees = data;
	}
	
	public String getColumnName(int col){
		return lesColonnes[col];
	}
	
	public Class getColumnClass(int c){
		return getValueAt(0, c).getClass();
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return lesColonnes.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lesDonnees.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return lesDonnees[row][col];
	}

}
