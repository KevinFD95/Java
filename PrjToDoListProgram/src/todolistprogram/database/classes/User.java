package src.todolistprogram.database.classes;

public class User {

    /**
     * ATTRIBUTES
     */
    private int userId;
    private String userName;
    private String userPass;

    /**
     * CONSTRUCTOR
     */
    public User(int userId, String userName, String userPass) {
        this.userId = userId;
        this.userName = userName;
        this.userPass = userPass;
    }

    /**
     * METHODS
     */

    /**
     * GET USER ID METHOD
     * Method used to get the user's ID
     * 
     * @return int with the user's ID
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * GET USERNAME METHOD
     * Method used to get the username
     * 
     * @return String with the username
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * GET USER PASSWORD METHOD
     * Method used to get the user's password
     * 
     * @return String with the user's password
     */
    public String getUserPass() {
        return this.userPass;
    }

    /**
     * SET USER PASSWORD METHOD
     * Method used to set a new password to an user
     * 
     * @param newUserPass String with the new user's password
     */
    public void setUserPass(String newUserPass) {
        this.userPass = newUserPass;
    }

    /**
     * toString METHOD
     */
    public String toString() {
        return "User ID: " + this.getUserId() + "\n" +
                "Username: " + this.getUserName() + "\n" +
                "Password: " + this.getUserPass();
    }
}