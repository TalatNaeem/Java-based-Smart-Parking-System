package smartparkingsystem.main;

import java.sql.Date;

public class Application {

	private int applierID;
	private boolean approved;
	private Date dateApproved;
	private Date dateSubmitted;
	private int id;
	private String vehicleRegistration;

	public Application(int id, int applierID, String vehicleRegistration, Date dateSubmitted, Date dateApproved,
			boolean approved) {
		super();
		this.id = id;
		this.applierID = applierID;
		this.vehicleRegistration = vehicleRegistration;
		this.dateSubmitted = dateSubmitted;
		this.dateApproved = dateApproved;
		this.approved = approved;
	}

	public int getApplierID() {
		return applierID;
	}

	public Date getDateApproved() {
		return dateApproved;
	}

	public Date getDateSubmitted() {
		return dateSubmitted;
	}

	public int getId() {
		return id;
	}

	public String getVehicleRegistration() {
		return vehicleRegistration;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApplierID(int applierID) {
		this.applierID = applierID;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public void setDateApproved(Date dateApproved) {
		this.dateApproved = dateApproved;
	}

	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setVehicleRegistration(String vehicleRegistration) {
		this.vehicleRegistration = vehicleRegistration;
	}
}
