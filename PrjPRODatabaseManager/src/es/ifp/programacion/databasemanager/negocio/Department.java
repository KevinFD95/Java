package es.ifp.programacion.databasemanager.negocio;

public class Department {

	/**
	 * ATTRIBUTES
	 */
	private int dptId;
	private String dptName;
	
	/**
	 * CONSTRUCTOR
	 * @param dptId
	 * @param dptName
	 */
	public Department(int dptId, String dptName) {
		this.dptId = dptId;
		this.dptName = dptName;
	}

	/**
	 * @return the dptId
	 */
	public int getDptId() {
		return dptId;
	}
	
	/**
	 * @return the dptName
	 */
	public String getDptName() {
		return dptName;
	}
	/**
	 * @param newDptName
	 */
	public void setDptName(String newDptName) {
		this.dptName = newDptName;
	}
	
	/**
	 * toString
	 */
	public String toString() {
		return  "ID Departamento: "+this.getDptId()+"\n"+
				"Departamento: "+this.getDptName();
	}
}