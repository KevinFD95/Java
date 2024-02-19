package src.todolistprogram.database.sqlqueries;

public class SQLUser {
    public static String ACCESS_DB = "SELECT * FROM users WHERE username=? AND password=?";

    public static String GET_ALL_USERS = "SELECT * FROM users";
    public static String GET_USER_BY_ID = "SELECT * FROM users WHERE userid=?";

    public static String GET_ALL_USERID = "SELECT userid FROM users";

    public static String INSERT_USER = "INSERT INTO users (userid, username, password) VALUES (?,?,?)";

    public static String UPDATE_USERPASS_BY_ID = "UPDATE users SET password=? WHERE userid=?";

    public static String DELETE_USER_BY_ID = "DELETE FROM users WHERE userid=?";
}