package src.todolistprogram.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import src.todolistprogram.database.classes.User;
import src.todolistprogram.database.sqlqueries.SQLUser;

public class Database {

    /**
     * ATTRIBUTES
     */
    private String dbUrl;
    private String dbUser;
    private String dbPass;

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    /**
     * CONSTRUCTOR
     */

    public Database(String dbUrl, String dbUser, String dbPass) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }

    /**
     * METHODS
     */

    /**
     * DATABASE PERSISTENCE
     */
    public boolean connectToDb() {
        try {
            con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    public boolean disconnectFromDb() {
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

            return ((rs == null || rs.isClosed()) &&
                    (ps == null || ps.isClosed()) &&
                    (con == null || con.isClosed()));
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    /**
     * ACCESS DB METHOD
     */
    public boolean accessToDb(String username, String userPass) {
        try {
            ps = con.prepareStatement(SQLUser.ACCESS_DB);
            ps.setString(1, username);
            ps.setString(2, userPass);

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    /**
     * GET DATA METHODS
     */
    public HashMap<Integer, User> getAllUsersFromDb() {
        HashMap<Integer, User> userList = new HashMap<>();
        User user;

        try {
            ps = con.prepareStatement(SQLUser.GET_ALL_USERS);
            rs = ps.executeQuery();

            while (rs.next()) {
                user = new User(rs.getInt("userid"), rs.getString("username"),
                        rs.getString("password"));

                userList.put(user.getUserId(), user);
            }

            return userList;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return userList;
        }
    }

    public User getUserFromDb(User user) {
        try {
            ps = con.prepareStatement(SQLUser.GET_USER_BY_ID);
            ps.setInt(1, user.getUserId());
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt("userid"), rs.getString("username"),
                        rs.getString("password"));
            }

            return user;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return user;
        }
    }

    /**
     * INSERT DATA METHOD
     */
    public int insertUserToDb(User user) {
        int insertedUser = 0;

        try {
            ps = con.prepareStatement(SQLUser.INSERT_USER);
            ps.setInt(1, user.getUserId());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getUserPass());

            insertedUser = ps.executeUpdate();

            return insertedUser;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return insertedUser;
        }
    }

    /**
     * UPDATE DATA METHOD
     */
    public boolean updateUserPassFromDb(User user, String newUserpass) {
        try {
            ps = con.prepareStatement(SQLUser.UPDATE_USERPASS_BY_ID);
            ps.setString(1, newUserpass);
            ps.setInt(2, user.getUserId());

            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    /**
     * DELETE DATA METHOD
     */
    public boolean deleteUserFromDb(User user) {
        try {
            ps = con.prepareStatement(SQLUser.DELETE_USER_BY_ID);
            ps.setInt(1, user.getUserId());

            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }
}