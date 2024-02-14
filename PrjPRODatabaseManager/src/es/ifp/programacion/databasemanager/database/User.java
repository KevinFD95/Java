package es.ifp.programacion.databasemanager.database;

public class User {

	private int userId;
	private String username;
	private String pass;
	private String role;
	
	/**
	 * CONSTRUCTOR
	 * @param userId
	 * @param user
	 * @param pass
	 * @param role
	 */
	public User(int userId, String username, String pass, String role) {
		this.userId = userId;
		this.username = username;
		this.pass = pass;
		this.role = role;
	}
	
	public User(String username, String pass) {
		this.username = username;
		this.pass = pass;
	}
	
	public int getUserId() {
		return this.userId;
	}
	
	/**
	 * @return user
	 */
	public String getUsername() {
		return this.username;
	}
	/**
	 * @param newUser
	 */
	public void setUsername(String newUsername) {
		this.username = newUsername;
	}

	/**
	 * @return pass
	 */
	public String getPass() {
		return this.pass;
	}
	/**
	 * @param newPass
	 */
	public void setPass(String newPass) {
		this.pass = newPass;
	}

	/**
	 * @return role
	 */
	public String getRole() {
		return this.role;
	}
	/**
	 * @param newRole
	 */
	public void setRole(String newRole) {
		this.role = newRole;
	}
	
	/**
	 * toString
	 */
	public String toString() {
		return  "ID de Usuario: "+this.getUserId()+"\n"+
				"Usuario: "+this.getUsername()+"\n"+
				"Contraseña: "+this.getPass()+"\n"+
				"Rol: "+this.getRole();
	}
}