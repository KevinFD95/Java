package es.ifp.programacion.databasemanager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import es.ifp.programacion.databasemanager.database.sqlqueries.SQLDpt;
import es.ifp.programacion.databasemanager.database.sqlqueries.SQLEmploy;
import es.ifp.programacion.databasemanager.database.sqlqueries.SQLLocate;
import es.ifp.programacion.databasemanager.database.sqlqueries.SQLUser;
import es.ifp.programacion.databasemanager.negocio.Department;
import es.ifp.programacion.databasemanager.negocio.Employer;
import es.ifp.programacion.databasemanager.negocio.classes.Locate;

public class Database {

	//================================================================================================================\\
	//================================================== ATTRIBUTES ==================================================\\
	//================================================================================================================\\
	
	private String url;
	private String userDB;
	private String passDB;
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private User user;
	private Department dpt;
	private Employer emp;
	private Locate loc;
	
	//================================================================================================================\\
	//================================================= CONSTRUCTOR ==================================================\\
	//================================================================================================================\\
	
	/**
	 * CONSTRUCTOR
	 * @param url
	 * @param userDB
	 * @param passDB
	 */
	public Database(String url, String userDB, String passDB) {
		this.url = url;
		this.userDB = userDB;
		this.passDB = passDB;
	}
	
	//================================================================================================================\\
	//=============================================== DATABASE METHODS ===============================================\\
	//================================================================================================================\\
	
	public boolean connectToDB() {
		try {
			con = DriverManager.getConnection(url, userDB, passDB);
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean disconnectFromDB() {
		
		try {
			
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (con != null && !con.isClosed()) {
				con.close();
			}
			
			return ((rs == null || rs.isClosed()) && (ps == null || ps.isClosed()) && (con == null || con.isClosed()));
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean accessDB(String user, String pass) {
		
		try {
			ps = con.prepareStatement(SQLUser.ACCESS_DB);
			ps.setString(1, user);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	//================================================================================================================\\
	//================================================= USER METHODS =================================================\\
	//================================================================================================================\\
	//--------------------------------------------------- GET DATA ---------------------------------------------------\\
	//----------------------------------------------------------------------------------------------------------------\\
	public ArrayList<User> getAllUsersDB() {
		
		ArrayList<User> listUsers = new ArrayList<>();
		
		try {
			ps = con.prepareStatement(SQLUser.GET_ALLUSERS);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				user = new User(rs.getInt("userId"), rs.getString("username"), rs.getString("pass"), rs.getString("role"));
				listUsers.add(user);
			}
			
			return listUsers;
		} 
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}
	
	public User getUser_By_ID(int userId) {
		
		try {
			ps = con.prepareStatement(SQLUser.GET_USER_BY_ID);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				user = new User(rs.getInt("userId"), rs.getString("username"), rs.getString("pass"), rs.getString("role"));
			}
			
			return user;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}
	
	public User getUser_By_Role(String role) {
		
		try {
			ps = con.prepareStatement(SQLUser.GET_USERS_BY_ROLE);
			ps.setString(1, role);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				user = new User(rs.getInt("userId"), rs.getString("username"), rs.getString("pass"), rs.getString("role"));
			}
			
			return user;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return user;
		}
	}
	
	public int getMinUsersID() {
		
		ArrayList<Integer> allUsersId = new ArrayList<>();
		int minId = 1;
		
		try {
			ps = con.prepareStatement(SQLUser.GET_ALLUSERSID);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				allUsersId.add(rs.getInt("userid"));
			}
			
			if (allUsersId.contains(minId)) {
				minId++;
			}
			
			return minId;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return minId;
		}
	}
	
	public String getUserAccessLevel(String username, String pass) {
		
		try {
			ps = con.prepareStatement(SQLUser.ACCESS_DB);
			ps.setString(1, username);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				user = new User(rs.getInt("userid"), rs.getString("username"), rs.getString("pass"), rs.getString("role"));
			}
			
			return user.getRole();
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}
	//----------------------------------------------------------------------------------------------------------------\\
	//------------------------------------------------- INSERTS DATA -------------------------------------------------\\
	//----------------------------------------------------------------------------------------------------------------\\
	public int insertUserToDB(User user) {
		
		int userInserted = 0;
		
		try {
			ps = con.prepareStatement(SQLUser.INSERT_USER);
			ps.setInt(1, user.getUserId());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPass());
			ps.setString(4, user.getRole());
			
			userInserted = ps.executeUpdate();
			
			return userInserted;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return userInserted;
		}
	}
	//----------------------------------------------------------------------------------------------------------------\\
	//------------------------------------------------- UPDATES DATA -------------------------------------------------\\
	//----------------------------------------------------------------------------------------------------------------\\
	public boolean updateUsername_By_ID(int userId, String newUsername) {
		
		try {
			ps = con.prepareStatement(SQLUser.UPDATE_USERNAME_BY_ID);
			ps.setString(1, newUsername);
			ps.setInt(2, userId);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateUserpass_By_ID(int userId, String newPass) {
		
		try {
			ps = con.prepareStatement(SQLUser.UPDATE_USERPASS_BY_ID);
			ps.setString(1, newPass);
			ps.setInt(2, userId);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateUserrole_by_ID(int userId, String newRole) {
		
		try {
			ps = con.prepareStatement(SQLUser.UPDATE_USERROLE_BY_ID);
			ps.setString(1, newRole);
			ps.setInt(2, userId);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateUserpass_By_Username(String username, String newPass) {
		
		try {
			ps = con.prepareStatement(SQLUser.UPDATE_USERPASS_BY_USER);
			ps.setString(1, newPass);
			ps.setString(2, username);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateUserrole_By_Username(String username, String newRole) {
		
		try {
			ps = con.prepareStatement(SQLUser.UPDATE_USERROLE_BY_USER);
			ps.setString(1, newRole);
			ps.setString(2, username);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	//----------------------------------------------------------------------------------------------------------------\\
	//------------------------------------------------- REMOVES DATA -------------------------------------------------\\
	//----------------------------------------------------------------------------------------------------------------\\
	public int removeUserFromDB_By_ID(int userId) {
		
		int userRemoved = 0;
		
		try {
			ps = con.prepareStatement(SQLUser.REMOVE_USER_BY_ID);
			ps.setInt(1, userId);
			
			userRemoved = ps.executeUpdate();
			
			return userRemoved;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return userRemoved;
		}
	}
	
	public int removeUserFromDB_By_Username(String username) {
		
		int userRemoved = 0;
		
		try {
			ps = con.prepareStatement(SQLUser.REMOVE_USER_BY_USERNAME);
			ps.setString(1, username);
			
			userRemoved = ps.executeUpdate();
			
			return userRemoved;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return userRemoved;
		}
	}
	
	public boolean removeAllUsersDB() {
		
		try {
			ps = con.prepareStatement(SQLUser.REMOVE_ALLUSERS);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	//================================================================================================================\\
	//============================================== DEPARTMENT METHODS ==============================================\\
	//================================================================================================================\\
	//--------------------------------------------------- GET DATA ---------------------------------------------------\\
	//----------------------------------------------------------------------------------------------------------------\\
	public ArrayList<Department> getAllDptsDB() {
		
		ArrayList<Department> listDpts = new ArrayList<>();
		
		try {
			ps = con.prepareStatement(SQLDpt.GET_ALLDPTS);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				dpt = new Department(rs.getInt("dptid"), rs.getString("dptname"));
				listDpts.add(dpt);
			}
			
			return listDpts;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return listDpts;
		}
	}
	
	public Department getDpt_By_ID(int dptId) {
		
		try {
			ps = con.prepareStatement(SQLDpt.GET_DPT_BY_ID);
			ps.setInt(1, dptId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				dpt = new Department(rs.getInt("dptid"), rs.getString("dptname"));
			}
			
			return dpt;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}
	
	public int getMinDptsID() {
		
		ArrayList<Integer> allDptsId = new ArrayList<>();
		int minId = 1;
		
		try {
			ps = con.prepareStatement(SQLDpt.GET_ALLDPTSID);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				allDptsId.add(rs.getInt("dptid"));
			}
			
			while (allDptsId.contains(minId)) {
				minId++;
			}
			
			return minId;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return minId;
		}
	}
	//----------------------------------------------------------------------------------------------------------------\\
	//------------------------------------------------- INSERTS DATA -------------------------------------------------\\
	//----------------------------------------------------------------------------------------------------------------\\
	public int insertDptToDB(Department dpt) {
		
		int dptInserted = 0;
		
		try {
			ps = con.prepareStatement(SQLDpt.INSERT_DPT);
			ps.setInt(1, dpt.getDptId());
			ps.setString(2, dpt.getDptName());
			
			dptInserted = ps.executeUpdate();
			
			return dptInserted;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return dptInserted;
		}
	}
	//----------------------------------------------------------------------------------------------------------------\\
	//------------------------------------------------- UPDATES DATA -------------------------------------------------\\
	//----------------------------------------------------------------------------------------------------------------\\
	public boolean updateDptName_By_ID(int dptId, String newDptName) {
		
		try {
			ps = con.prepareStatement(SQLDpt.UPDATE_DPTNAME_BY_ID);
			ps.setString(1, newDptName);
			ps.setInt(2, dptId);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateDptName_By_Name(String dptName, String newDptName) {
		
		try {
			ps = con.prepareStatement(SQLDpt.UPDATE_DPTNAME_BY_NAME);
			ps.setString(1, newDptName);
			ps.setString(2, dptName);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	//----------------------------------------------------------------------------------------------------------------\\
	//------------------------------------------------- REMOVES DATA -------------------------------------------------\\
	//----------------------------------------------------------------------------------------------------------------\\
	public int removeDptFromDB_By_ID(int dptId) {
		
		int dptRemoved = 0;
		
		try {
			ps = con.prepareStatement(SQLDpt.REMOVE_DPT_BY_ID);
			ps.setInt(1, dptId);
			
			dptRemoved = ps.executeUpdate();
			
			return dptRemoved;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return dptRemoved;
		}
	}
	
	public int removeDptFromDB_By_Name(String dptName) {
		
		int dptRemoved = 0;
		
		try {
			ps = con.prepareStatement(SQLDpt.REMOVE_DPT_BY_NAME);
			ps.setString(1, dptName);
			
			dptRemoved = ps.executeUpdate();
			
			return dptRemoved;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return dptRemoved;
		}
	}
	
	public boolean removeAllDptsFromDB() {
		
		try {
			ps = con.prepareStatement(SQLDpt.REMOVE_ALLDPTS);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	//================================================================================================================\\
	//=============================================== EMPLOYER METHODS ===============================================\\
	//================================================================================================================\\
	//--------------------------------------------------- GET DATA ---------------------------------------------------\\
	//----------------------------------------------------------------------------------------------------------------\\
	public ArrayList<Employer> getAllEmployersDB() {
		
		ArrayList<Employer> listEmp = new ArrayList<>();
		
		try {
			ps = con.prepareStatement(SQLEmploy.GET_ALLEMPLOYERS);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				loc = new Locate(rs.getString("street"), rs.getInt("num"), rs.getString("esc"), rs.getInt("floor"), 
						 rs.getString("door"), rs.getString("mun"), rs.getString("prov"), rs.getInt("cp"));
		
				emp = new Employer(rs.getInt("empid"), rs.getString("name"), rs.getString("surname"), rs.getString("dni"), 
								   rs.getString("email"), loc, rs.getInt("dptid"), rs.getInt("userid"));
				
				listEmp.add(emp);
			}
			
			return listEmp;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return listEmp;
		}
	}
	
	public HashMap<Integer, Employer> getAllEmployersDB_MapDptId() {
		
		HashMap<Integer, Employer> mapEmployers = new HashMap<>();
		
		try {
			ps = con.prepareStatement(SQLEmploy.GET_ALLEMPLOYERS);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				loc = new Locate(rs.getString("street"), rs.getInt("num"), rs.getString("esc"), rs.getInt("floor"), 
								 rs.getString("door"), rs.getString("mun"), rs.getString("prov"), rs.getInt("cp"));
				
				emp = new Employer(rs.getInt("empid"), rs.getString("name"), rs.getString("surname"), rs.getString("dni"), 
								   rs.getString("email"), loc, rs.getInt("dptid"), rs.getInt("userid"));
				
				mapEmployers.put(emp.getDptId(), emp);
			}
			
			return mapEmployers;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return mapEmployers;
		}
	}
	
	public Employer getEmployerDB_By_EmpID(int empId) {
		
		try {
			ps = con.prepareStatement(SQLEmploy.GET_EMPLOYER_BY_EMPID);
			ps.setInt(1, empId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				loc = new Locate(rs.getString("street"), rs.getInt("num"), rs.getString("esc"), rs.getInt("floor"), 
						 		 rs.getString("door"), rs.getString("mun"), rs.getString("prov"), rs.getInt("cp"));
		
				emp = new Employer(rs.getInt("empid"), rs.getString("name"), rs.getString("surname"), rs.getString("dni"), 
						   		   rs.getString("email"), loc, rs.getInt("dptid"), rs.getInt("userid"));
			}
			
			return emp;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return emp;
		}
	}
	
	public Employer getEmployerDB_By_UserID(int userId) {
		
		try {
			ps = con.prepareStatement(SQLEmploy.GET_EMPLOYER_BY_USERID);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				loc = new Locate(rs.getString("street"), rs.getInt("num"), rs.getString("esc"), rs.getInt("floor"), 
				 		 		 rs.getString("door"), rs.getString("mun"), rs.getString("prov"), rs.getInt("cp"));

				emp = new Employer(rs.getInt("empid"), rs.getString("name"), rs.getString("surname"), rs.getString("dni"), 
								   rs.getString("email"), loc, rs.getInt("dptid"), rs.getInt("userid"));
			}
			
			return emp;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return emp;
		}
	}
	
	public Employer getEmployerDB_By_DptID(int dptId) {
		
		try {
			ps = con.prepareStatement(SQLEmploy.GET_EMPLOYERS_BY_DPTID);
			ps.setInt(1, dptId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				loc = new Locate(rs.getString("street"), rs.getInt("num"), rs.getString("esc"), rs.getInt("floor"), 
				 		 		 rs.getString("door"), rs.getString("mun"), rs.getString("prov"), rs.getInt("cp"));

				emp = new Employer(rs.getInt("empid"), rs.getString("name"), rs.getString("surname"), rs.getString("dni"), 
								   rs.getString("email"), loc, rs.getInt("dptid"), rs.getInt("userid"));
			}
			
			return emp;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return emp;
		}
	}
	
	public Employer getEmployerDB_By_Dni(String dni) {
		
		try {
			ps = con.prepareStatement(SQLEmploy.GET_EMPLOYER_BY_DNI);
			ps.setString(1, dni);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				loc = new Locate(rs.getString("street"), rs.getInt("num"), rs.getString("esc"), rs.getInt("floor"), 
				 		 		 rs.getString("door"), rs.getString("mun"), rs.getString("prov"), rs.getInt("cp"));

				emp = new Employer(rs.getInt("empid"), rs.getString("name"), rs.getString("surname"), rs.getString("dni"), 
								   rs.getString("email"), loc, rs.getInt("dptid"), rs.getInt("userid"));
			}
			
			return emp;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return emp;
		}
	}
	
	public Employer getEmployerDB_By_Name_Surname(String name, String surname) {
		
		try {
			ps = con.prepareStatement(SQLEmploy.GET_EMPLOYER_BY_NAME_SURNAME);
			ps.setString(1, name);
			ps.setString(2, surname);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				loc = new Locate(rs.getString("street"), rs.getInt("num"), rs.getString("esc"), rs.getInt("floor"), 
				 		 		 rs.getString("door"), rs.getString("mun"), rs.getString("prov"), rs.getInt("cp"));

				emp = new Employer(rs.getInt("empid"), rs.getString("name"), rs.getString("surname"), rs.getString("dni"), 
								   rs.getString("email"), loc, rs.getInt("dptid"), rs.getInt("userid"));
			}
			
			return emp;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return emp;
		}
	}
	
	public int getEmployerMinID() {
		
		ArrayList<Integer> allEmpId = new ArrayList<>();
		int minId = 1;
		
		try {
			ps = con.prepareStatement(SQLEmploy.GET_ALLEMPLOYERSID);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				allEmpId.add(rs.getInt("empid"));
			}
			
			while (allEmpId.contains(minId)) {
				minId++;
			}
			
			return minId;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return minId;
		}
	}
	//----------------------------------------------------------------------------------------------------------------\\
	//------------------------------------------------- INSERTS DATA -------------------------------------------------\\
	//----------------------------------------------------------------------------------------------------------------\\
	public int insertEmployDB(Employer emp) {
		int empInserted = 0;
		
		try {
			ps = con.prepareStatement(SQLEmploy.INSERT_EMPLOYER);
			ps.setInt(1, emp.getEmpId());
			ps.setString(2, emp.getName());
			ps.setString(3, emp.getSurname());
			ps.setString(4, emp.getDni());
			ps.setString(5, emp.getEmail());
			ps.setString(6, emp.getLoc().getStreet());
			ps.setInt(7, emp.getLoc().getNum());
			ps.setString(8, emp.getLoc().getEsc());
			ps.setInt(9, emp.getLoc().getFloor());
			ps.setString(10, emp.getLoc().getDoor());
			ps.setString(11, emp.getLoc().getMun());
			ps.setString(12, emp.getLoc().getProv());
			ps.setInt(13, emp.getLoc().getCp());
			
			empInserted = ps.executeUpdate();
			
			return empInserted;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return empInserted;
		}
	}
	//----------------------------------------------------------------------------------------------------------------\\
	//------------------------------------------------- UPDATES DATA -------------------------------------------------\\
	//----------------------------------------------------------------------------------------------------------------\\
	public boolean updateEmployerName_By_EmpId(int empId, String newName) {
		
		try {
			ps = con.prepareStatement(SQLEmploy.UPDATE_EMPLOYER_NAME_BY_EMPID);
			ps.setString(1, newName);
			ps.setInt(2, empId);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployerName_By_UserId(int userId, String newName) {
		
		try {
			ps = con.prepareStatement(SQLEmploy.UPDATE_EMPLOYER_NAME_BY_USERID);
			ps.setString(1, newName);
			ps.setInt(2, userId);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployerName_By_Dni(String dni, String newName) {
		
		try {
			ps = con.prepareStatement(SQLEmploy.UPDATE_EMPLOYER_NAME_BY_DNI);
			ps.setString(1, newName);
			ps.setString(2, dni);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployerSurname_By_EmpId(int empId, String newSurname) {
		
		try {
			ps = con.prepareStatement(SQLEmploy.UPDATE_EMPLOYER_SURNAME_BY_EMPID);
			ps.setString(1, newSurname);
			ps.setInt(2, empId);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployerSurname_By_UserId(int userId, String newSurname) {

		try {
			ps = con.prepareStatement(SQLEmploy.UPDATE_EMPLOYER_SURNAME_BY_USERID);
			ps.setString(1, newSurname);
			ps.setInt(2, userId);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployerSurname_By_Dni(String dni, String newSurname) {

		try {
			ps = con.prepareStatement(SQLEmploy.UPDATE_EMPLOYER_SURNAME_BY_DNI);
			ps.setString(1, newSurname);
			ps.setString(2, dni);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployerDni_By_EmpId(int empId, String newDni) {

		try {
			ps = con.prepareStatement(SQLEmploy.UPDATE_EMPLOYER_DNI_BY_EMPID);
			ps.setString(1, newDni);
			ps.setInt(2, empId);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployerDni_By_UserId(int userId, String newDni) {

		try {
			ps = con.prepareStatement(SQLEmploy.UPDATE_EMPLOYER_DNI_BY_USERID);
			ps.setString(1, newDni);
			ps.setInt(2, userId);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployerDni_By_Dni(String dni, String newDni) {

		try {
			ps = con.prepareStatement(SQLEmploy.UPDATE_EMPLOYER_DNI_BY_DNI);
			ps.setString(1, newDni);
			ps.setString(2, dni);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployerEmail_By_EmpId(int empId, String newEmail) {

		try {
			ps = con.prepareStatement(SQLEmploy.UPDATE_EMPLOYER_EMAIL_BY_EMPID);
			ps.setString(1, newEmail);
			ps.setInt(2, empId);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployerEmail_By_UserId(int userId, String newEmail) {

		try {
			ps = con.prepareStatement(SQLEmploy.UPDATE_EMPLOYER_EMAIL_BY_USERID);
			return true;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployerEmail_By_Dni(String dni, String newEmail) {

		try {
			ps = con.prepareStatement(SQLEmploy.UPDATE_EMPLOYER_EMAIL_BY_DNI);
			ps.setString(1, newEmail);
			ps.setString(2, dni);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployerDpt_By_EmpId(int empId, int newDptId) {

		try {
			ps = con.prepareStatement(SQLEmploy.UPDATE_EMPLOYER_DPT_BY_EMPID);
			ps.setInt(1, newDptId);
			ps.setInt(2, empId);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployerDpt_By_UserId(int userId, int newDptId) {

		try {
			ps = con.prepareStatement(SQLEmploy.UPDATE_EMPLOYER_DPT_BY_USERID);
			ps.setInt(1, newDptId);
			ps.setInt(2, userId);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployerDpt_By_Dni(String dni, int newDptId) {

		try {
			ps = con.prepareStatement(SQLEmploy.UPDATE_EMPLOYER_DPT_BY_DNI);
			ps.setInt(1, newDptId);
			ps.setString(2, dni);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployerUser_By_EmpId(int empId, int newUserId) {

		try {
			ps = con.prepareStatement(SQLEmploy.UPDATE_EMPLOYER_USER_BY_EMPID);
			ps.setInt(1, newUserId);
			ps.setInt(2, empId);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployerUser_By_UserId(int userId, int newUserId) {

		try {
			ps = con.prepareStatement(SQLEmploy.UPDATE_EMPLOYER_USER_BY_USERID);
			ps.setInt(1, newUserId);
			ps.setInt(2, userId);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployerUser_By_Dni(String dni, int newUserId) {

		try {
			ps = con.prepareStatement(SQLEmploy.UPDATE_EMPLOYER_USER_BY_DNI);
			ps.setInt(1, newUserId);
			ps.setString(2, dni);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	//----------------------------------------------------------------------------------------------------------------\\
	//------------------------------------------------- REMOVES DATA -------------------------------------------------\\
	//----------------------------------------------------------------------------------------------------------------\\
	public int removeEmployer_By_EmpId(int empId) {

		int empRemoved = 0;
		
		try {
			ps = con.prepareStatement(SQLEmploy.REMOVE_EMPLOYER_BY_EMPID);
			ps.setInt(1, empId);
			
			empRemoved = ps.executeUpdate();
			
			return empRemoved;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return empRemoved;
		}
	}
	
	public int removeEmployer_By_UserId(int userId) {
		
		int empRemoved = 0;
		
		try {
			ps = con.prepareStatement(SQLEmploy.REMOVE_EMPLOYER_BY_USERID);
			ps.setInt(1, userId);
			
			empRemoved = ps.executeUpdate();
			
			return empRemoved;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return empRemoved;
		}
	}
	
	public int removeEmployer_By_Dni(String dni) {
		
		int empRemoved = 0;
		
		try {
			ps = con.prepareStatement(SQLEmploy.REMOVE_EMPLOYER_BY_DNI);
			ps.setString(1, dni);
			
			empRemoved = ps.executeUpdate();
			
			return empRemoved;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return empRemoved;
		}
	}
	
	public boolean removeAllEmployersDB() {
		
		try {
			ps = con.prepareStatement(SQLEmploy.REMOVE_ALLEMPLOYERS);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	//================================================================================================================\\
	//================================================ LOCATE METHODS ================================================\\
	//================================================================================================================\\
	//------------------------------------------------- UPDATE  DATA -------------------------------------------------\\
	//----------------------------------------------------------------------------------------------------------------\\
	public boolean updateEmployerLocate_By_EmpId(int empId, Locate newLocate) {

		try {
			ps = con.prepareStatement(SQLLocate.UPDATE_LOCATE_BY_EMPID);
			ps.setString(1, newLocate.getStreet());
			ps.setInt(2, newLocate.getNum());
			ps.setString(3, newLocate.getEsc());
			ps.setInt(4, newLocate.getFloor());
			ps.setString(5, newLocate.getDoor());
			ps.setString(6, newLocate.getMun());
			ps.setString(7, newLocate.getProv());
			ps.setInt(8, newLocate.getCp());
			ps.setInt(9, empId);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployerLocate_By_UserId(int userId, Locate newLocate) {

		try {
			ps = con.prepareStatement(SQLLocate.UPDATE_LOCATE_BY_USERID);
			ps.setString(1, newLocate.getStreet());
			ps.setInt(2, newLocate.getNum());
			ps.setString(3, newLocate.getEsc());
			ps.setInt(4, newLocate.getFloor());
			ps.setString(5, newLocate.getDoor());
			ps.setString(6, newLocate.getMun());
			ps.setString(7, newLocate.getProv());
			ps.setInt(8, newLocate.getCp());
			ps.setInt(9, userId);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployerLocate_By_Dni(String dni, Locate newLocate) {

		try {
			ps = con.prepareStatement(SQLLocate.UPDATE_LOCATE_BY_DNI);
			ps.setString(1, newLocate.getStreet());
			ps.setInt(2, newLocate.getNum());
			ps.setString(3, newLocate.getEsc());
			ps.setInt(4, newLocate.getFloor());
			ps.setString(5, newLocate.getDoor());
			ps.setString(6, newLocate.getMun());
			ps.setString(7, newLocate.getProv());
			ps.setInt(8, newLocate.getCp());
			ps.setString(9, dni);
			
			if (ps.executeUpdate()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
}