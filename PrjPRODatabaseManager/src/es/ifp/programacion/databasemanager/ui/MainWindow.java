package es.ifp.programacion.databasemanager.ui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import es.ifp.programacion.databasemanager.database.Database;
import es.ifp.programacion.databasemanager.ui.components.IconUtil;
import es.ifp.programacion.databasemanager.ui.components.TableDptDB;
import es.ifp.programacion.databasemanager.ui.components.TableEmpDB;
import es.ifp.programacion.databasemanager.ui.components.TableUserDB;

import javax.swing.JButton;

public class MainWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private final String urldb = "jdbc:postgresql://localhost:5432/companydb";
	private final String userdb = "AdminDB";
	private final String passdb = "Admin";
	private Database db = new Database(urldb, userdb, passdb);
	
	private JPanel contentPane;
	
	private JMenuBar menuBar = new JMenuBar();;
	
	private JMenu menuFile ;
	private JMenuItem menuChangeUser;
	private JMenuItem menuImport;
	private JMenuItem menuExport;
	private JMenuItem menuExit;
	
	private JMenu menuOptions;
	
	private JMenu menuHelp;
	
	private Component currentComponent;
	private TableEmpDB empTable;
	private TableDptDB dptTable;
	private TableUserDB userTable;
	
	private JButton empButton;
	private JButton editEmpButton;
	private JButton dptButton;
	private JButton editDptButton;
	private JButton userButton;
	private JButton editUserButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		IconUtil.setDefaultIcon(this);
		setTitle("BASE DE DATOS DE LA EMPRESA");
		setSize(1920, 1080);
		setResizable(false);
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeConfirm();
			}
		});
		startAdminComponents();
	}
	
	public void startAdminComponents() {
		startEmpComponents();
		adminButtons();
	}
	public void startEmpComponents() {
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		menu();
		empButtons();
		keyListener();
	}
	
	private void menu() {
		setJMenuBar(menuBar);
		menuBar.removeAll();
		
		menuFile = new JMenu("Archivo");
		menuChangeUser = new JMenuItem("Cambiar Usuario");
		menuChangeUser.addActionListener(this);
		menuExit = new JMenuItem("Salir");
		menuExit.addActionListener(this);
		menuImport = new JMenuItem("Importar");
		menuImport.addActionListener(this);
		menuExport = new JMenuItem("Exportar");
		menuExport.addActionListener(this);
		
		menuOptions = new JMenu("Opciones");
		menuHelp = new JMenu("Ayuda");
		
		menuBar.add(menuFile);
		menuFile.add(menuChangeUser);
		menuFile.add(menuImport);
		menuFile.add(menuExport);
		menuFile.add(menuExit);
		
		menuBar.add(menuOptions);
		menuBar.add(menuHelp);
	}
	
	private void empButtons() {
		empButton = new JButton("Tabla de Empleados");
		empButton.setBounds(10, 11, 177, 23);
		contentPane.add(empButton);
		empButton.addActionListener(this);
		
		editEmpButton = new JButton("Editar Tabla de Empleados");
		editEmpButton.setBounds(210, 11, 216, 23);
		contentPane.add(editEmpButton);
		editEmpButton.addActionListener(this);
		
		dptButton = new JButton("Tabla de Departamentos");
		dptButton.setBounds(10, 45, 177, 23);
		contentPane.add(dptButton);
		dptButton.addActionListener(this);
		
		editDptButton = new JButton("Editar Tabla de Departamentos");
		editDptButton.setBounds(210, 45, 216, 23);
		contentPane.add(editDptButton);
		editDptButton.addActionListener(this);
	}
	
	private void adminButtons() {
		userButton = new JButton("Tabla de Usuarios");
		userButton.setBounds(10, 79, 177, 23);
		contentPane.add(userButton);
		userButton.addActionListener(this);
		
		editUserButton = new JButton("Editar Tabla de Usuarios");
		editUserButton.setBounds(210, 79, 216, 23);
		contentPane.add(editUserButton);
		editUserButton.addActionListener(this);
	}
	
	private void changeUser() {
		String[] options = {"    Cambiar Usuario    ", "    No cambiar de Usuario    "};
		int response = JOptionPane.showOptionDialog(this, "Estás seguro de querer cambiar de Usuario?", 
													"ADVERTENCIA: CAMBIO DE USUARIO", JOptionPane.YES_NO_OPTION, 
													JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		db.connectToDB();
		
		if (response == JOptionPane.YES_OPTION) {
			dispose();
			setVisible(false);
			LoginWindow lw = new LoginWindow();
			lw.setVisible(true);
			lw.startComponents();
		}
	}
	
	public void showEmpTable() {
		empTable = new TableEmpDB();
		empTable.setBounds(10, 473, 1884, 557);
		contentPane.add(empTable);
		currentComponent = empTable;
	}
	
	public void showDptTable() {
		dptTable = new TableDptDB();
		dptTable.setBounds(10, 473, 1884, 557);
		contentPane.add(dptTable);
		currentComponent = dptTable;
	}
	
	public void showUserTable() {
		userTable = new TableUserDB();
		userTable.setBounds(10, 473, 1884, 557);
		contentPane.add(userTable);
		currentComponent = userTable;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == menuChangeUser) {
			changeUser();
		}
		
		if (e.getSource() == menuExit) {
			closeConfirm();
		}
		
		if (currentComponent != null) {
			contentPane.remove(currentComponent);
		}
		
		if (e.getSource() == empButton) {
			showEmpTable();
		}
		if (e.getSource() == editEmpButton) {
			showEmpTable();
			if (empTable != null) {
				empTable.setEditable(true);
			}
		}
		
		if (e.getSource() == dptButton) {
			showDptTable();
		}
		if (e.getSource() == editDptButton) {
			showDptTable();
			if (dptTable != null) {
				dptTable.setEditable(true);
			}
		}
		
		if (e.getSource() == userButton) {
			showUserTable();
		}
		if (e.getSource() == editUserButton) {
			showUserTable();
			if (userTable != null) {
				userTable.setEditable(true);
			}
		}
	}
	private void keyListener() {
		InputMap inputMapExit = contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMapExit.put(KeyStroke.getKeyStroke("ESCAPE"), "doExitAction");
		ActionMap actionMapExit = contentPane.getActionMap();
		actionMapExit.put("doExitAction", new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent e) {
				closeConfirm();
			}
		});
	}
	
   public void closeConfirm() {
		String[] options = {"    Sí, estoy seguro    ", "    No, cancelar    "};
		int response = JOptionPane.showOptionDialog(this, "Estás seguro de querer cerrar el programa?", "ADVERTENCIA: CONFIRMAR CIERRE", 
													JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
													options, options[1]);
		
		if (response == JOptionPane.YES_OPTION) {
			dispose();
			db.disconnectFromDB();
			System.exit(0);
		}
	}
}