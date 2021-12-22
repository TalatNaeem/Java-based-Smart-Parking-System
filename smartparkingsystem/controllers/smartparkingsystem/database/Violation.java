package database;

import java.sql.Date;

public class Violation {

	private Date date;
	private String description;
	private int id;
	private int reporterID;
	private String violatorRegistrationNumber;

	public Violation(int id, String violatorRegistrationNumber, int reporterID, Date date, String description) {
		super();
		this.id = id;
		this.violatorRegistrationNumber = violatorRegistrationNumber;
		this.reporterID = reporterID;
		this.date = date;
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public int getReporterID() {
		return reporterID;
	}

	public String getViolatorRegistrationNumber() {
		return violatorRegistrationNumber;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setReporterID(int reporterID) {
		this.reporterID = reporterID;
	}

	public void setViolatorRegistrationNumber(String violatorRegistrationNumber) {
		this.violatorRegistrationNumber = violatorRegistrationNumber;
	}

}
