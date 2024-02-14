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
import es.ifp.programacion.databasemanager.negocio.Department;

public class TableDptDB extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final String urldb = "jdbc:postgresql://localhost:5432/companydb";
	private final String userdb = "AdminDB";
	private final String passdb = "Admin";
	private Database db = new Database(urldb, userdb, passdb);
	
	private JScrollPane scrollPanel;
	private JTable dptTable;
	private DefaultTableModel tableModel;
	private TableRowSorter<DefaultTableModel> tableSorter;
	
	private boolean editMode = false;
	
	public TableDptDB() {
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
		String[] titles = {"ID Departamento", "Nombre"};
		String[][] dptInfo = getDptDBInfo();
		tableModel = new DefaultTableModel(dptInfo, titles);
		dptTable = new JTable(tableModel) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return editMode && column != 0;
			}
		};
		scrollPanel.setViewportView(dptTable);
		tableSorter();
	}
	
	private void tableSorter() {
		tableSorter = new TableRowSorter<>(tableModel);
		dptTable.setRowSorter(tableSorter);
		tableSorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(0, SortOrder.ASCENDING)));
	}
	
	private String[][] getDptDBInfo() {
		
		ArrayList<Department> listDpt = db.getAllDptsDB();
		String matrixInfo[][] = new String[listDpt.size()][2];
		
		for (int i=0;i<listDpt.size();i++) {
			matrixInfo[i][0] = listDpt.get(i).getDptId()+"";
			matrixInfo[i][1] = listDpt.get(i).getDptName()+"";
		}
		return matrixInfo;
	}
	
	public boolean setEditable(boolean editTable) {
		editMode = editTable;
		return editMode;
	}
}