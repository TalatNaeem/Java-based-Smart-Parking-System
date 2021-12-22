package smartparkingsystem.database;

import java.sql.Date;

public class Sticker {

	private int id;
	private int issuedTo;
	private Date validFrom;
	private Date validTill;

	public Sticker(int id, int issuedTo, Date validFrom, Date validTill) {
		super();
		this.id = id;
		this.issuedTo = issuedTo;
		this.validFrom = validFrom;
		this.validTill = validTill;
	}

	public int getId() {
		return id;
	}

	public int getIssuedTo() {
		return issuedTo;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public Date getValidTill() {
		return validTill;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIssuedTo(int issuedTo) {
		this.issuedTo = issuedTo;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}

}
