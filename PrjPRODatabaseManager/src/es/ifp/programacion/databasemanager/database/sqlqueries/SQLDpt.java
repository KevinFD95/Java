package es.ifp.programacion.databasemanager.database.sqlqueries;

public class SQLDpt {

	//GET DATA
	public static String GET_ALLDPTS = "SELECT * FROM departments";
	public static String GET_DPT_BY_ID = "SELECT dptid, dptname FROM departments WHERE dptid=?";
	
	//GET ALL IDs
	public static String GET_ALLDPTSID = "SELECT dptid FROM departments";
	
	//INSERT DATA
	public static String INSERT_DPT = "INSERT INTO departments (dptid, dptname) VALUES (?,?)";
	
	//UPDATE DATA
	public static String UPDATE_DPTNAME_BY_ID = "UPDATE departments SET dptname=? WHERE dptid=?";
	public static String UPDATE_DPTNAME_BY_NAME = "UPDATE departments SET dptname=? WHERE dptname=?";
	
	//REMOVE DATA
	public static String REMOVE_DPT_BY_ID = "DELETE FROM departments WHERE dptid=?";
	public static String REMOVE_DPT_BY_NAME = "DELETE FROM departments WHERE dptname=?";
	
	//REMOVE ALL DATA
	public static String REMOVE_ALLDPTS = "DELETE * FROM departments";
}