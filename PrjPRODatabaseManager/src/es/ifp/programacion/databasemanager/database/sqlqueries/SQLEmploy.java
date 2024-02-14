package es.ifp.programacion.databasemanager.database.sqlqueries;

public class SQLEmploy {
	
	//GET DATA
	public static String GET_ALLEMPLOYERS = "SELECT * FROM employers";
	public static String GET_EMPLOYER_BY_EMPID = "SELECT * FROM employers WHERE empid=?";
	public static String GET_EMPLOYER_BY_USERID = "SELECT * FROM employers WHERE userid=?";
	public static String GET_EMPLOYERS_BY_DPTID = "SELECT * FROM employers WHERE dptid=?";
	public static String GET_EMPLOYER_BY_DNI = "SELECT * FROM employers WHERE dni=?";
	public static String GET_EMPLOYER_BY_NAME_SURNAME = "SELECT * FROM employers WHERE name=? AND surname=?";
	
	//GET ALL IDs
	public static String GET_ALLEMPLOYERSID = "SELECT empid FROM employers";
	
	//INSERT DATA
	public static String INSERT_EMPLOYER = "INSERT INTO employers SET (empid, name, surname, dni, email, street, num, esc, floor, door, mun, prov, cp, dptid, userid) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	//UPDATE DATA
	public static String UPDATE_EMPLOYER_NAME_BY_EMPID = "UPDATE employers SET name=? WHERE empid=?";
	public static String UPDATE_EMPLOYER_NAME_BY_USERID = "UPDATE employers SET name=? WHERE userid=?";
	public static String UPDATE_EMPLOYER_NAME_BY_DNI = "UPDATE employers SET name=? WHERE dni=?";
	
	public static String UPDATE_EMPLOYER_SURNAME_BY_EMPID = "UPDATE employers SET surname=? WHERE empid=?";
	public static String UPDATE_EMPLOYER_SURNAME_BY_USERID = "UPDATE employers SET surname=? WHERE userid=?";
	public static String UPDATE_EMPLOYER_SURNAME_BY_DNI = "UPDATE employers SET surname=? WHERE dni=?";
	
	public static String UPDATE_EMPLOYER_DNI_BY_EMPID = "UPDATE employers SET dni=? WHERE empid=?";
	public static String UPDATE_EMPLOYER_DNI_BY_USERID = "UPDATE employers SET dni=? WHERE userid=?";
	public static String UPDATE_EMPLOYER_DNI_BY_DNI = "UPDATE employers SET dni=? WHERE userid=?";
	
	public static String UPDATE_EMPLOYER_EMAIL_BY_EMPID = "UPDATE employers SET email=? WHERE empid=?";
	public static String UPDATE_EMPLOYER_EMAIL_BY_USERID = "UPDATE employers SET email=? WHERE userid=?";
	public static String UPDATE_EMPLOYER_EMAIL_BY_DNI = "UPDATE employers SET email=? WHERE dni=?";
	
	public static String UPDATE_EMPLOYER_DPT_BY_EMPID = "UPDATE employers SET dptid=? WHERE empid=?";
	public static String UPDATE_EMPLOYER_DPT_BY_USERID = "UPDATE employers SET dptid=? WHERE userid=?";
	public static String UPDATE_EMPLOYER_DPT_BY_DNI = "UPDATE employers SET dptid=? WHERE dni=?";
	
	public static String UPDATE_EMPLOYER_USER_BY_EMPID = "UPDATE employers SET userid=? WHERE empid=?";
	public static String UPDATE_EMPLOYER_USER_BY_USERID = "UPDATE employers SET userid=? WHERE userid=?";
	public static String UPDATE_EMPLOYER_USER_BY_DNI = "UPDATE employers SET userid=? WHERE dni=?";
	
	//REMOVE DATA
	public static String REMOVE_EMPLOYER_BY_EMPID = "DELETE FROM employers WHERE empid=?";
	public static String REMOVE_EMPLOYER_BY_USERID = "DELETE FROM employers WHERE userid=?";
	public static String REMOVE_EMPLOYER_BY_DNI = "DELETE FROM employers WHERE dni=?";
	
	//REMOVE ALL DATA
	public static String REMOVE_ALLEMPLOYERS = "DELETE * FROM employers";
}