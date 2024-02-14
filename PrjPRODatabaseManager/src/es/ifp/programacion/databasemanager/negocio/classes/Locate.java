package es.ifp.programacion.databasemanager.negocio.classes;

public class Locate {

	/**
	 * ATTRIBUTES
	 */
	private String street;
	private int num;
	private String esc;
	private int floor;
	private String door;
	private String mun;
	private String prov;
	private int cp;
	
	/**
	 * CONSTRUCTORS
	 */
	/**
	 * FULL CONSTRUCTOR
	 * @param street
	 * @param num
	 * @param esc
	 * @param floor
	 * @param door
	 * @param mun
	 * @param prov
	 * @param cp
	 */
	public Locate(String street, int num, String esc, int floor, String door, String mun, String prov, int cp) {
		this.street = street;
		this.num = num;
		this.esc = esc;
		this.floor = floor;
		this.door = door;
		this.mun = mun;
		this.prov = prov;
		this.cp = cp;
	}
	
	/**
	 * CONSTRUCTOR WITHOUT ESC
	 * @param street
	 * @param num
	 * @param floor
	 * @param door
	 * @param mun
	 * @param prov
	 * @param cp
	 */
	public Locate(String street, int num, int floor, String door, String mun, String prov, int cp) {
		this.street = street;
		this.num = num;
		this.floor = floor;
		this.door = door;
		this.mun = mun;
		this.prov = prov;
		this.cp = cp;
	}

	/**
	 * METHODS
	 */
	
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * @return the esc
	 */
	public String getEsc() {
		return esc;
	}
	/**
	 * @param esc the esc to set
	 */
	public void setEsc(String esc) {
		this.esc = esc;
	}
	
	/**
	 * @return the floor
	 */
	public int getFloor() {
		return floor;
	}
	/**
	 * @param floor the floor to set
	 */
	public void setFloor(int floor) {
		this.floor = floor;
	}

	/**
	 * @return the door
	 */
	public String getDoor() {
		return door;
	}
	/**
	 * @param door the door to set
	 */
	public void setDoor(String door) {
		this.door = door;
	}

	/**
	 * @return the mun
	 */
	public String getMun() {
		return mun;
	}
	/**
	 * @param mun the mun to set
	 */
	public void setMun(String mun) {
		this.mun = mun;
	}

	/**
	 * @return the prov
	 */
	public String getProv() {
		return prov;
	}
	/**
	 * @param prov the prov to set
	 */
	public void setProv(String prov) {
		this.prov = prov;
	}

	/**
	 * @return the cp
	 */
	public int getCp() {
		return cp;
	}
	/**
	 * @param cp the cp to set
	 */
	public void setCp(int cp) {
		this.cp = cp;
	}
	
	/**
	 * toString
	 */
	public String toString() {
		if (this.getEsc() != null) {
			return  "Calle: "+this.getStreet()+"\n"+
					"Número: "+this.getNum()+"\n"+
					"Escalera: "+this.getEsc()+"\n"+
					"Planta: "+this.getFloor()+"\n"+
					"Puerta: "+this.getDoor()+"\n"+
					"Municipio: "+this.getMun()+"\n"+
					"Provincia: "+this.getProv()+"\n"+
					"C.P.: "+this.getCp();
		}
		else {
			return  "Calle: "+this.getStreet()+"\n"+
					"Número: "+this.getNum()+"\n"+
					"Planta: "+this.getFloor()+"\n"+
					"Puerta: "+this.getDoor()+"\n"+
					"Municipio: "+this.getMun()+"\n"+
					"Provincia: "+this.getProv()+"\n"+
					"C.P.: "+this.getCp();
		}
	}
}