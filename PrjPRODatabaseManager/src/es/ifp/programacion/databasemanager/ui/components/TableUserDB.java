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
import es.ifp.programacion.databasemanager.database.User;

public class TableUserDB extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final String urldb = "jdbc:postgresql://localhost:5432/companydb";
	private final String userdb = "AdminDB";
	private final String passdb = "Admin";
	private Database db = new Database(urldb, userdb, passdb);
	
	private JScrollPane scrollPanel;
	private JTable userTable;
	private DefaultTableModel tableModel;
	private TableRowSorter<DefaultTableModel> tableSorter;
	
	private boolean editMode = false;
	
	public TableUserDB() {
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
		String[] titles = {"ID Usuario","Usuario","Contraseña","Rol"};
		String[][] userInfo = getUserDBInfo();
		tableModel = new DefaultTableModel(userInfo, titles) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return editMode && column != 0;
			}
		};
		userTable = new JTable(tableModel);
		scrollPanel.setViewportView(userTable);
		tableSorter();
	}
	
	private void tableSorter() {
		tableSorter = new TableRowSorter<>(tableModel);
		userTable.setRowSorter(tableSorter);
		tableSorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(0, SortOrder.ASCENDING)));
	}
	
	private String[][] getUserDBInfo() {
		
		ArrayList<User> listUser = db.getAllUsersDB();
		String[][] matrixInfo = new String[listUser.size()][4];
		
		for (int i=0;i<listUser.size();i++) {
			matrixInfo[i][0] = listUser.get(i).getUserId()+"";
			matrixInfo[i][1] = listUser.get(i).getUsername()+"";
			matrixInfo[i][2] = listUser.get(i).getPass()+"";
			matrixInfo[i][3] = listUser.get(i).getRole()+"";
		}
		
		return matrixInfo;
	}
	
	public boolean setEditable(boolean editTable) {
		editMode = editTable;
		return editMode;
	}
}