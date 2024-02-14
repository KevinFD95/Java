package es.ifp.programacion.databasemanager.database.sqlqueries;

public class SQLUser {

	//GET DATA
	public static String GET_ALLUSERS = "SELECT * FROM users";
	public static String GET_USER_BY_ID = "SELECT * FROM users WHERE userid=?";
	public static String GET_USERS_BY_ROLE = "SELECT * FROM users WHERE role=?";
	
	//GET ALL IDs
	public static String GET_ALLUSERSID = "SELECT userid FROM users";
	
	//GET ACCESS DATA
	public static String ACCESS_DB = "SELECT * FROM users WHERE username=? AND pass=?";
	
	//INSERT DATA
	public static String INSERT_USER = "INSERT INTO users (userid, username, pass, role) VALUES (?,?,?,?)";
	
	//UPDATE DATA
	public static String UPDATE_USERNAME_BY_ID = "UPDATE users SET username=? WHERE userid=?";
	public static String UPDATE_USERPASS_BY_ID = "UPDATE users SET pass=? WHERE userid=?";
	public static String UPDATE_USERROLE_BY_ID = "UPDATE users SET role=? WHERE userid=?";
	public static String UPDATE_USERPASS_BY_USER = "UPDATE users SET pass=? WHERE username=?";
	public static String UPDATE_USERROLE_BY_USER = "UPDATE users SET role=? WHERE username=?";
	
	//REMOVE DATA
	public static String REMOVE_USER_BY_ID = "DELETE FROM users WHERE userid=?";
	public static String REMOVE_USER_BY_USERNAME = "DELETE FROM users WHERE username=?";
	
	//REMOVE ALL DATA
	public static String REMOVE_ALLUSERS = "DELETE * FROM users";
}