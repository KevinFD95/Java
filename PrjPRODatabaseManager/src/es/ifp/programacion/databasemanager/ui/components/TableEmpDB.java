package es.ifp.programacion.databasemanager.ui.components;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import es.ifp.programacion.databasemanager.database.Database;
import es.ifp.programacion.databasemanager.negocio.Employer;

public class TableEmpDB extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private final String urldb = "jdbc:postgresql://localhost:5432/companydb";
	private final String userdb = "AdminDB";
	private final String passdb = "Admin";
	private Database db = new Database(urldb, userdb, passdb);
	
	private JScrollPane scrollPanel;
	private JTable empTable;
	private DefaultTableModel tableModel;
	private TableRowSorter<DefaultTableModel> tableSorter;
	
	private boolean editMode = false;
	
	public TableEmpDB() {
		startComponents();
	}
	
	private void startComponents() {
		db.connectToDB();
		definePanel();
		constructTableDB();
	}
	
	private void definePanel() {
		setLayout(null);
		scrollPanel = new JScrollPane();
		scrollPanel.setBounds(0, 0, 1884, 557);
		add(scrollPanel);
	}
	
	private void constructTableDB() {
		String[] titles = {"ID Empleado", "Nombre", "Apellidos", "DNI", "Email", "Calle", "Número", "Escalera", "Piso", 
						   "Puerta", "Municipio", "Provincia", "C. Postal", "ID Departamento", "ID Usuario"};
		String[][] empInfo = getEmpDBInfo();
		tableModel = new DefaultTableModel(empInfo, titles);
		empTable = new JTable(tableModel) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return editMode && column != 0 && column != getColumnCount()-1;
			}
		};
		scrollPanel.setViewportView(empTable);
		tableSorter();
	}
	
	private void tableSorter() {
		tableSorter = new TableRowSorter<>(tableModel);
		empTable.setRowSorter(tableSorter);
		tableSorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(0, SortOrder.ASCENDING)));
	}
	
	private String[][] getEmpDBInfo() {
		
		ArrayList<Employer> listEmp = db.getAllEmployersDB();
		String matrixInfo[][] = new String[listEmp.size()][15];
		
		for (int i=0;i<listEmp.size();i++) {
			matrixInfo[i][0] = listEmp.get(i).getEmpId()+"";
			matrixInfo[i][1] = listEmp.get(i).getName()+"";
			matrixInfo[i][2] = listEmp.get(i).getSurname()+"";
			matrixInfo[i][3] = listEmp.get(i).getDni()+"";
			matrixInfo[i][4] = listEmp.get(i).getEmail()+"";
			matrixInfo[i][5] = listEmp.get(i).getLoc().getStreet()+"";
			matrixInfo[i][6] = listEmp.get(i).getLoc().getNum()+"";
			matrixInfo[i][7] = listEmp.get(i).getLoc().getEsc()+"";
			matrixInfo[i][8] = listEmp.get(i).getLoc().getFloor()+"";
			matrixInfo[i][9] = listEmp.get(i).getLoc().getDoor()+"";
			matrixInfo[i][10] = listEmp.get(i).getLoc().getMun()+"";
			matrixInfo[i][11] = listEmp.get(i).getLoc().getProv()+"";
			matrixInfo[i][12] = listEmp.get(i).getLoc().getCp()+"";
			matrixInfo[i][13] = listEmp.get(i).getDptId()+"";
			matrixInfo[i][14] = listEmp.get(i).getUserId()+"";
		}
		return matrixInfo;
	}
	
	public boolean setEditable(boolean editTable) {
		editMode = editTable;
		return editMode;
	}
}