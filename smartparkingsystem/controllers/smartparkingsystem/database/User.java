package database;
//Database File Updated (Arshad)
public class User {

	private String email;
	private int id;
	private String name;
	private String phone;
	private int type;

	public User(int id, String name, String email, String phone, int type) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public int getType() {
		return type;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setType(int type) {
		this.type = type;
	}

}
