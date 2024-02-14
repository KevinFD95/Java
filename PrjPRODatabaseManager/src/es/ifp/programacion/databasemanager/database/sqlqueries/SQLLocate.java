package es.ifp.programacion.databasemanager.database.sqlqueries;

public class SQLLocate {

	//UPDATE ALL DATA
	public static String UPDATE_LOCATE_BY_EMPID = "UPDATE employers SET street=?, num=?, esc=?, floor=?, door=?, mun=?, prov=?, cp=? WHERE empid=?";
	public static String UPDATE_LOCATE_BY_USERID = "UPDATE employers SET street=?, num=?, esc=?, floor=?, door=?, mun=?, prov=?, cp=? WHERE userid=?";
	public static String UPDATE_LOCATE_BY_DNI = "UPDATE employers SET street=?, num=?, esc=?, floor=?, door=?, mun=?, prov=?, cp=? WHERE dni=?";
	
	//UPDATE SINGLE DATA
	public static String UPDATE_LOCATE_STREET_BY_EMPID = "UPDATE employers SET street=? WHERE empid=?";
	public static String UPDATE_LOCATE_STREET_BY_USERID = "UPDATE employers SET street=? WHERE userid=?";
	public static String UPDATE_LOCATE_STREET_BY_DNI = "UPDATE employers SET street=? WHERE dni=?";
	
	public static String UPDATE_LOCATE_NUM_BY_EMPID = "UPDATE employers SET num=? WHERE empid=?";
	public static String UPDATE_LOCATE_NUM_BY_USERID = "UPDATE employers SET num=? WHERE userid=?";
	public static String UPDATE_LOCATE_NUM_BY_DNI = "UPDATE employers SET num=? WHERE dni=?";
	
	public static String UPDATE_LOCATE_ESC_BY_EMPID = "UPDATE employers SET esc=? WHERE empid=?";
	public static String UPDATE_LOCATE_ESC_BY_USERID = "UPDATE employers SET esc=? WHERE userid=?";
	public static String UPDATE_LOCATE_ESC_BY_DNI = "UPDATE employers SET esc=? WHERE dni=?";
	
	public static String UPDATE_LOCATE_FLOOR_BY_EMPID = "UPDATE employers SET floort=? WHERE empid=?";
	public static String UPDATE_LOCATE_FLOOR_BY_USERID = "UPDATE employers SET floor=? WHERE userid=?";
	public static String UPDATE_LOCATE_FLOOR_BY_DNI = "UPDATE employers SET floort=? WHERE dni=?";
	
	public static String UPDATE_LOCATE_DOOR_BY_EMPID = "UPDATE employers SET door=? WHERE empid=?";
	public static String UPDATE_LOCATE_DOOR_BY_USERID = "UPDATE employers SET door=? WHERE userid=?";
	public static String UPDATE_LOCATE_DOOR_BY_DNI = "UPDATE employers SET doort=? WHERE dni=?";
	
	public static String UPDATE_LOCATE_MUN_BY_EMPID = "UPDATE employers SET mun=? WHERE empid=?";
	public static String UPDATE_LOCATE_MUN_BY_USERID = "UPDATE employers SET mun=? WHERE userid=?";
	public static String UPDATE_LOCATE_MUN_BY_DNI = "UPDATE employers SET mun=? WHERE dni=?";
	
	public static String UPDATE_LOCATE_PROV_BY_EMPID = "UPDATE employers SET prov=? WHERE empid=?";
	public static String UPDATE_LOCATE_PROV_BY_USERID = "UPDATE employers SET prov=? WHERE userid=?";
	public static String UPDATE_LOCATE_PROV_BY_DNI = "UPDATE employers SET prov=? WHERE dni=?";
	
	public static String UPDATE_LOCATE_CP_BY_EMPID = "UPDATE employers SET cp=? WHERE empid=?";
	public static String UPDATE_LOCATE_CP_BY_USERID = "UPDATE employers SET cp=? WHERE userid=?";
	public static String UPDATE_LOCATE_CP_BY_DNI = "UPDATE employers SET cp=? WHERE dni=?";
}