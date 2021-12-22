package database;

public class Vehicle {

	private int id;
	private int owner_id;
	private String registration_number;

	public Vehicle(int id, String registrationNumber, int ownerID) {
		super();
		this.id = id;
		this.registration_number = registrationNumber;
		this.owner_id = ownerID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}

	public String getRegistration_number() {
		return registration_number;
	}

	public void setRegistration_number(String registration_number) {
		this.registration_number = registration_number;
	}



}
