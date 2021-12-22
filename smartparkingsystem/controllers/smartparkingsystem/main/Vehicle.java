package smartparkingsystem.main;

public class Vehicle {

	private int id;
	private int ownerID;
	private String registrationNumber;

	public Vehicle(int id, String registrationNumber, int ownerID) {
		super();
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.ownerID = ownerID;
	}

	public int getId() {
		return id;
	}

	public int getOwnerID() {
		return ownerID;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

}
