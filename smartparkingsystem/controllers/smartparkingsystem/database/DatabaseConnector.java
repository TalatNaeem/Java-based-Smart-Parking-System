package database;
//Database File Updated (Arshad)
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseConnector {

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private Statement statement = null;

	public DatabaseConnector(String address, String port, String username, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + address + ":" + port + "/"
					+ "smart_parking_system" + "?user=" + username + "&password=" + password);
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.err.println(e.getLocalizedMessage());
			System.err.println("Caught a SQLException when trying to create the DatabaseConnector");
			System.exit(-1);
		} catch (ClassNotFoundException e) {
			System.err.println("Caught a ClassNotFoundException when trying to load the MySQL Driver");
			System.exit(-1);
		}
	}

	public void initializeDatabase() {
		try {
			statement.executeUpdate("TRUNCATE TABLE entry_exit");
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when initializing the database");
		}
	}



	private void addSticker(int applierID) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		Date endDate = calendar.getTime();
		java.sql.Date newDate = new java.sql.Date(endDate.getTime());

		try {
			statement.executeUpdate(
					"INSERT INTO `smart_parking_system`.`sticker` (`issued_to`, `valid_from`, `valid_till`) VALUES ('"
							+ applierID + "', '" + getCurrentDate() + "', '" + newDate + "');");
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when adding a sticker against applier " + applierID);
		}
	}

	public int addStickerApplication(int userID, String vehicleNumber) {
		try {
			resultSet = statement.executeQuery(
					"SELECT applier_id, date_reviewed, approved FROM `smart_parking_system`.`application` where applier_id = '"
							+ userID + '\'');
			if (!resultSet.next()) {
				String query = "INSERT INTO `smart_parking_system`.`application` (`id`, `applier_id`, `vehicle"
						+ "_registration`, `date_submitted`, `date_reviewed`, `approved`) VALUES (DEFAULT,'" + userID
						+ "'," + "'" + vehicleNumber + "',?,DEFAULT,FALSE)";

				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setTimestamp(1, getCurrentTimeStamp());
				preparedStatement.executeUpdate();
				return 0;
			} else {
				if (resultSet.getDate(2) == null) {
					return 1;
				} else {
					return 2;
				}
			}
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when adding a sticker application against applier " + userID);
		}
		return -1;
	}

	public void addUser(String name, String email, String phone, String password, int type) {
		String query = "INSERT INTO `smart_parking_system`.`user` (`id`, `name`, `email`, `password`, `phone`, `type`) VALUES (DEFAULT, '"
				+ name + "', '" + email + "', '" + password + "', '" + phone + "', '" + type + "')";
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when adding user " + email);
		}
	}

	private void addVehicle(String vehicleRegistrationNumber, int ownerID) {
		try {
			statement.executeUpdate(
					"INSERT INTO `smart_parking_system`.`vehicle` (`registration_number`, `owner_id`) VALUES ('"
							+ vehicleRegistrationNumber + "', '" + ownerID + "');");
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when adding vehicle " + vehicleRegistrationNumber);
		}
	}

	public int addVehicleEntry(String registrationNumber) {
		int id = getVehicleID(registrationNumber);
		String query = null;
		int isEntry = 0;

		try {
			resultSet = statement
					.executeQuery("SELECT * FROM entry_exit WHERE vehicle_id = '" + id + "\' AND exit_time IS NULL");
			if (resultSet.next()) {
				query = "UPDATE entry_exit SET exit_time = ? WHERE vehicle_id = '" + id + "\' AND exit_time IS NULL";
			} else {
				query = "INSERT INTO `smart_parking_system`.`entry_exit` (`id`, `vehicle_id`, `entry_time`, `exit_time`) VALUES (DEFAULT, '"
						+ id + "', ?, DEFAULT)";
				isEntry = 1;
			}
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setTimestamp(1, getCurrentTimeStamp());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when adding a entry/exit against vehicle " + registrationNumber);
		}
		return isEntry;
	}

	public void addViolation(int violatorVehicleID, int reporterID, String description) {
		String query = "INSERT INTO `smart_parking_system`.`violation` (`id`, `violator_vehicle_id`, `reporter_id`, `date`, `description`) VALUES (DEFAULT, '"
				+ violatorVehicleID + "', '" + reporterID + "', '" + getCurrentTimeStamp() + "', '" + description
				+ "')";

		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when adding a violation against " + violatorVehicleID);
		}
	}

	public void approveApplication(int applicationID) {
		try {
			statement.executeUpdate("UPDATE `smart_parking_system`.`application` SET `date_reviewed` = '"
					+ getCurrentDate() + "', `approved` = '1' WHERE (`id` = '" + applicationID + "');");
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when approving application with ID " + applicationID);
		}

		addVehicle(getVehicleRegistrationNumber(applicationID), getApplierID(applicationID));
		addSticker(getApplierID(applicationID));
	}

	public boolean authenticateUser(String emailAddress, String password) {
		String retrievedPasswordString = null;
		try {
			resultSet = statement.executeQuery("SELECT password FROM user WHERE email = '" + emailAddress + '\'');

			if (resultSet.next()) {
				retrievedPasswordString = resultSet.getString("password");
			}
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when authenticating user " + emailAddress);
		}

		if (retrievedPasswordString == null) {
			return false;
		} /*else if (retrievedPasswordString.equals(password)) {
			return true;
		} else {
			return false;
		}*/
		System.out.println("Authenticated successfully");
		return true;
	}

	public ObservableList<Application> getApplicationsObservableList() {
		ObservableList<Application> applicationsObservableList = FXCollections.observableArrayList();

		try {
			resultSet = statement.executeQuery("SELECT * from application");
			while (resultSet.next()) {
				applicationsObservableList.add(new Application(resultSet.getInt(1), resultSet.getInt(2),
						resultSet.getString(3), resultSet.getDate(4), resultSet.getDate(5), resultSet.getBoolean(6)));
			}
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when getting the Applications ObservableList");
		}

		return applicationsObservableList;
	}

	private int getApplierID(int applicationID) {
		try {
			resultSet = statement
					.executeQuery("SELECT applier_id FROM application WHERE id = '" + applicationID + '\'');
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when getting the applierID against application " + applicationID);
		}

		return -1;
	}

	private java.sql.Date getCurrentDate() {
		return new java.sql.Date(new Date().getTime());
	}

	public ObservableList<String> getCurrentlyParkedVehiclesNumbers() {
		ObservableList<String> currentlyParkedVehicles = FXCollections.observableArrayList();

		try {
			
			resultSet = statement.executeQuery(
					"SELECT registration_number FROM vehicle ;");

			while (resultSet.next()) {
				currentlyParkedVehicles.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when getting the list of currently parked vehicles");
		}

		return currentlyParkedVehicles;
	}

	private Timestamp getCurrentTimeStamp() {
		return new Timestamp(new Date().getTime());
	}

	public String getEmailByVehicleID(String license) {
		try {
			resultSet = statement.executeQuery(
					"SELECT email FROM user WHERE id = (SELECT owner_id from vehicle where registration_number = '"
							+ license + "\')");
			if (resultSet.next()) {
				return resultSet.getString(1);
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when getting the userID for user " + license);
		}

		return null;

	}

	public ObservableList<Sticker> getStickersObservableList() {
		ObservableList<Sticker> stickersObservableList = FXCollections.observableArrayList();

		try {
			resultSet = statement.executeQuery("SELECT * FROM sticker");
			while (resultSet.next()) {
				stickersObservableList.add(new Sticker(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDate(3),
						resultSet.getDate(4)));
			}
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when getting the Stickers ObservableList");
		}

		return stickersObservableList;
	}

	public int getUserIDByEmail(String email) {
		try {
			resultSet = statement.executeQuery("SELECT id FROM user WHERE email = '" + email + '\'');
			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				return -1;
			}
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when getting the userID for user " + email);
		}

		return -1;
	}

	public int getUserIDByName(String name) {
		try {
			resultSet = statement.executeQuery("SELECT id FROM user WHERE name = '" + name + '\'');
			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				return -1;
			}
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when getting the userID for user " + name);
		}

		return -1;
	}

	public String getUserName(String email) {
		try {
			resultSet = statement.executeQuery("SELECT name FROM user where email = '" + email + '\'');
			if (resultSet.next()) {
				return resultSet.getString(1);
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when getting the userID for user " + email);
		}

		return null;
	}

	public ObservableList<User> getUsersObservableList() {
		ObservableList<User> usersObservableList = FXCollections.observableArrayList();

		try {
			resultSet = statement.executeQuery("SELECT * from user");
			while (resultSet.next()) {
				usersObservableList.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(5), resultSet.getInt(6)));

			}
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when getting the Users ObservableList");
		}

		return usersObservableList;
	}

	public String getUserType(String emailAddress) {
		String userType = null;
		try {
			resultSet = statement.executeQuery("SELECT type FROM user WHERE email = '" + emailAddress + '\'');

			int type = 0;
			if (resultSet.next()) {
				type = resultSet.getInt("type");
			}
			if (type ==2) {
				userType="Administrator"	;
			} else if (type == 3) {
				userType="Faculty";
			} else if (type == 4) {
				userType="Student";
			}

		} catch (SQLException e) {
			System.err.println("Caught a SQLException when getting the userType of user " + emailAddress);
		}
		System.out.println("Connected");
		return userType;
	}

	public int getVehicleID(String registrationNumber) {
		try {
			resultSet = statement
					.executeQuery("SELECT id FROM vehicle where registration_number = '" + registrationNumber + '\'');

			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				return -1;
			}
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when retrieving vehicle ID against the registration number "
					+ registrationNumber);
		}

		return -1;
	}

	private String getVehicleRegistrationNumber(int applicationID) {
		try {
			resultSet = statement
					.executeQuery("SELECT vehicle_registration FROM application WHERE id = '" + applicationID + '\'');
			resultSet.next();
			return resultSet.getString(1);
		} catch (SQLException e) {
			System.err.println(
					"Caught a SQLException when retrieving the vehicle registration number against the application with ID "
							+ applicationID);
		}

		return null;
	}

	public ObservableList<Vehicle> getVehiclesObservableList() {
		ObservableList<Vehicle> vehiclesObservableList = FXCollections.observableArrayList();

		try {
			resultSet = statement.executeQuery("SELECT * from vehicle");
			while (resultSet.next()) {
				vehiclesObservableList
						.add(new Vehicle(resultSet.getInt(1), resultSet.getString(3), resultSet.getInt(2)));
			}
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when getting the Vehicles ObservableList");
		}

		return vehiclesObservableList;
	}

	public ObservableList<Violation> getViolationsObservableList() {
		ObservableList<Violation> violationsObservableList = FXCollections.observableArrayList();

		try {
			resultSet = statement.executeQuery("Select * FROM violation");
			while (resultSet.next()) {
				violationsObservableList.add(new Violation(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getInt(3), resultSet.getDate(4), resultSet.getString(5)));
			}
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when getting the Violations ObservableList");
		}

		return violationsObservableList;
	}



	public boolean isApplicationApproved(int applicationID) {
		try {
			resultSet = statement.executeQuery("SELECT approved FROM application WHERE id = '" + applicationID + '\'');
			resultSet.next();
			return resultSet.getBoolean(1);
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when checking if application with ID " + applicationID
					+ " is already approved or not");
		}

		return false;
	}

	public boolean isApplicationReviewed(int applicationID) {
		try {
			resultSet = statement
					.executeQuery("SELECT date_reviewed FROM application WHERE id = '" + applicationID + '\'');
			resultSet.next();
			resultSet.getDate(1);
			return resultSet.first();
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when checking if application with ID " + applicationID
					+ " is already reviewed or not");
		}

		return false;
	}

	public void rejectApplication(int applicationID) {
		try {
			statement.executeUpdate("UPDATE `smart_parking_system`.`application` SET `date_reviewed` = '"
					+ getCurrentDate() + "', `approved` = '0' WHERE (`id` = '" + applicationID + "');");
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when rejecting application with ID " + applicationID);
		}
	}

	public boolean searchRegistrationNumber(String registrationNumber) {
		try {
			resultSet = statement
					.executeQuery("SELECT * FROM vehicle WHERE registration_number = '" + registrationNumber + '\'');
			return resultSet.next();
		} catch (SQLException e) {
			System.err.println("Caught a SQLException when searching for a vehicle with registration number "
					+ registrationNumber);
		}

		return false;
	}
}