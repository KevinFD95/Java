package es.ifp.programacion.databasemanager.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.ifp.programacion.databasemanager.database.Database;
import es.ifp.programacion.databasemanager.ui.components.IconUtil;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JPasswordField;
import java.awt.Font;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;

public class LoginWindow extends JFrame implements ActionListener {

	/**
	 * ATRIBUTOS
	 */
	private static final long serialVersionUID = 1L;
	
	private final String urldb = "jdbc:postgresql://localhost:5432/companydb";
	private final String userdb = "AdminDB";
	private final String passdb = "Admin";
	private Database db = new Database(urldb, userdb, passdb);
	
	private MainWindow mw;
	
	private JPanel mainPane;
	private JTextField txtUsuario;
	private JPasswordField passField;
	private JButton enterButton;
	private JButton exitButton;
	
	/**
	 * METODO main
	 * 
	 * Inicia la ventana.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * CONSTRUCTOR LoginWindow
	 * 
	 * Constructor con la configuracion de la ventana.
	 * Configuracion de la accion al cerrar la ventana
	 * Llamada al inicio de los componentes (startComponents)
	 */
	public LoginWindow() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		IconUtil.setDefaultIcon(this);
		setTitle("LOGIN");
		setSize(750, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeConfirm();
			}
		});
		
		startComponents();
	}
	
	/**
	 * METODO startComponents
	 * 
	 * Metodo que arranca los componentes de la ventana.
	 */
	public void startComponents() {
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		mainPane.setLayout(null);
		db.connectToDB();
		
		labels();
		textFields();
		buttons();
		keyListener();
	}
	
	/**
	 * METODO labels
	 * 
	 * Metodo que crea y configura los JLabels de la ventana.
	 */
	public void labels() {
		JLabel userLabel = new JLabel("Usuario:");
		userLabel.setFont(new Font("Arial", Font.BOLD, 14));
		userLabel.setBounds(205, 249, 63, 14);
		mainPane.add(userLabel);
		
		JLabel passLabel = new JLabel("Contraseña:");
		passLabel.setFont(new Font("Arial", Font.BOLD, 14));
		passLabel.setBounds(206, 298, 92, 14);
		mainPane.add(passLabel);
		
		JLabel iconLabel = new JLabel("");
		iconLabel.setBounds(278, 11, 200, 200);
		mainPane.add(iconLabel);
		
		ImageIcon icon = new ImageIcon("icons/usuario.png");
		iconLabel.setIcon(icon);
		mainPane.add(iconLabel);
	}
	
	/**
	 * METODO textFields
	 * 
	 * Metodo donde se encuentran los JTextFields de la ventana. 
	 */
	public void textFields() {
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
		txtUsuario.setBounds(328, 247, 215, 20);
		mainPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		passField = new JPasswordField();
		passField.setFont(new Font("Arial", Font.PLAIN, 14));
		passField.setBounds(328, 292, 215, 20);
		mainPane.add(passField);
	}
	
	/**
	 * METODO BUTTONS
	 * 
	 * Metodo define, configura y añade los botones de la ventana en el panel principal.
	 * 
	 */
	public void buttons() {
		enterButton = new JButton("Ingresar");
		enterButton.setFont(new Font("Arial", Font.PLAIN, 14));
		enterButton.setBounds(240, 366, 112, 23);
		mainPane.add(enterButton);
		enterButton.addActionListener(this);
		
		exitButton = new JButton("Salir");
		exitButton.setFont(new Font("Arial", Font.PLAIN, 14));
		exitButton.setBounds(400, 366, 112, 23);
		mainPane.add(exitButton);
		exitButton.addActionListener(this);
	}
	
	/**
	 * METODO enterMainApp
	 * 
	 * Metodo que comprueba los datos introducidos en los JTextField con la DB y entra a la 
	 * ventana principal o no, segun la comprobacion de los datos en la DB.
	 */
	public void enterMainApp() {
		String user = txtUsuario.getText().trim();
		String pass = new String(passField.getPassword());
		
		db.disconnectFromDB();
		db = new Database(urldb, userdb, passdb);
		db.connectToDB();
		
		if (db.accessDB(user, pass)) {
			if (db.getUserAccessLevel(user, pass).equals("Admin")) {
				JOptionPane.showMessageDialog(this, "ADMINISTRADOR: Acceso Correcto");
				this.setVisible(false);
				mw = new MainWindow();
				mw.setVisible(true);
				mw.startAdminComponents();
			}
			else if (db.getUserAccessLevel(user, pass).equals("Emp")) {
				JOptionPane.showMessageDialog(this, "EMPLEADO: Acceso Correcto");
				this.setVisible(false);
				mw = new MainWindow();
				mw.setVisible(true);
				mw.startEmpComponents();
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Acceso Incorrecto");
		}
	}

	/**
	 * METODO actionPerformed
	 * 
	 * Metodo escuchador en el que estan definidas las acciones de los botones que se encuentran 
	 * en la ventana.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == enterButton) {
			enterMainApp();
		}
		
		if (e.getSource() == exitButton) {
			closeConfirm();
		}
	}
	/**
	 * METODO keyListener
	 * 
	 * Metodo escuchador en el que se encuentran definidas las acciones de ciertas teclas
	 * cuando el usuario se encuentra en la ventana.
	 */
	public void keyListener() {
		InputMap inputMapEnter = passField.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMapEnter.put(KeyStroke.getKeyStroke("ENTER"), "doEnterAction");
		ActionMap actionMapEnter = passField.getActionMap();
		actionMapEnter.put("doEnterAction", new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent e) {
				enterMainApp();
			}
		});
		InputMap inputMapExit = passField.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMapExit.put(KeyStroke.getKeyStroke("ESCAPE"), "doExitAction");
		ActionMap actionMapExit = passField.getActionMap();
		actionMapExit.put("doExitAction", new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent e) {
				closeConfirm();
			}
		});
	}
	
	/**
	 * METODO closeConfirm
	 * 
	 * Metodo que dispara una ventana emergente en el momento de cerrar la aplicacion.
	 * Esta ventana emergente es una confirmacion de cierre.
	 */
	private void closeConfirm() {
		String[] opciones = {"    Sí, estoy seguro    ", "    No, cancelar    "};
		int response = JOptionPane.showOptionDialog(this, "Estás seguro de querer cerrar el programa?", "ADVERTENCIA", 
													JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
													opciones, opciones[1]);
		if (response == JOptionPane.YES_OPTION) {
			dispose();
			db.disconnectFromDB();
			System.exit(0);
		}
	}
}