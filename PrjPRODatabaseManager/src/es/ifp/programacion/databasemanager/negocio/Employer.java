package es.ifp.programacion.databasemanager.negocio;

import es.ifp.programacion.databasemanager.negocio.classes.Locate;

public class Employer {

	/**
	 * ATTRIBUTES
	 */
	private int empId;
	private String name;
	private String surname;
	private String dni;
	private String email;
	private Locate loc;
	private int userId;
	private int dptId;
	
	/**
	 * CONSTRUCTOR
	 * @param empId
	 * @param name
	 * @param surname
	 * @param dni
	 * @param user
	 * @param dpt
	 */
	public Employer(int empId, String name, String surname, String dni, int dptId, int userId) {
		this.empId = empId;
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.dptId = dptId;
		this.userId = userId;
	}
	
	public Employer(int empId, String name, String subname, String dni, String email, Locate loc, int dptId, int userId) {
		this.empId = empId;
		this.name = name;
		this.surname = subname;
		this.dni = dni;
		this.email = email;
		this.loc = loc;
		this.dptId = dptId;
		this.userId = userId;
	}
	
	/**
	 * @return empId
	 */
	public int getEmpId() {
		return this.empId;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @param newName
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * @return surname
	 */
	public String getSurname() {
		return this.surname;
	}
	/**
	 * @param newSubname
	 */
	public void setSurname(String newSubname) {
		this.surname = newSubname;
	}

	/**
	 * @return dni
	 */
	public String getDni() {
		return this.dni;
	}
	/**
	 * @param newDni
	 */
	public void setDni(String newDni) {
		this.dni = newDni;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return this.email;
	}
	/**
	 * @param newEmail
	 */
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	
	/**
	 * @return user
	 */
	public int getUserId() {
		return this.userId;
	}
	
	public int getDptId() {
		return this.dptId;
	}
	public void setDpt(int newDptId) {
		this.dptId = newDptId;
	}

	/**
	 * @return loc
	 */
	public Locate getLoc() {
		return this.loc;
	}
	/**
	 * @param newLoc
	 */
	public void setLoc(Locate newLoc) {
		this.loc = newLoc;
	}
	
	/**
	 * toString
	 */
	public String toString() {
		return  "ID de Empleado: "+this.getEmpId()+"\n"+
				"Nombre: "+this.getName()+"\n"+
				"Apellido/s: "+this.getSurname()+"\n"+
				"DNI: "+this.getDni()+"\n"+
				"E-mail: "+this.getEmail()+"\n"+
				"Localidad:\n"+this.getLoc()+"\n"+
				"ID de Departamento:\n"+this.getDptId()+"\n"+
				"ID de Usuario:\n"+this.getUserId();
	}
}