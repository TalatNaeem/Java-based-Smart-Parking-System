package smartparkingsystem.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import smartparkingsystem.database.DatabaseConnector;

public class LoginController {

	private DatabaseConnector databaseConnector;

	@FXML
	private TextField emailAddressField;

	@FXML
	private Button loginButton;

	@FXML
	private Label outputLabel;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Label signupPromptLabel;

	private void authenticateUser() throws IOException {
		if (emailAddressField.getText().equals("")) {
			outputLabel.setText("Username cannot be blank!");
		} else if (passwordField.getText().equals("")) {
			outputLabel.setText("Password cannot be blank!");
		} else {
			System.out.println("above");
			String emailAddress = emailAddressField.getText();
			String password = passwordField.getText();
			boolean xor=databaseConnector.authenticateUser(emailAddress, password);
			System.out.println(xor);
			if (databaseConnector.authenticateUser(emailAddress, password)) {
				System.out.println("Entered inside if");
				String userType = databaseConnector.getUserType(emailAddress);

				if (userType.equals("Administrator")) {
					FXMLLoader fLoader = new FXMLLoader(
							getClass().getResource("E:/Uni Shuni/Semester 6/SE/Project/Parking System/SmartParkingSystem - Final Source/SmartParkingSystem/src/smartparkingsystem/presentation/AdministratorView.fxml"));
					Parent root = fLoader.load();

					AdministratorViewController administratorViewController = fLoader.getController();
					administratorViewController.setDatabaseConnector(databaseConnector);
					administratorViewController.setCurrentUserName(databaseConnector.getUserName(emailAddress));
					administratorViewController.autoInvoke();

					setScene(root);

				} /*else if (userType.equals("Guard")) {
					FXMLLoader fLoader = new FXMLLoader(
							getClass().getResource("E:/Uni Shuni/Semester 6/SE/Project/Parking System/SmartParkingSystem - Final Source/SmartParkingSystem/src/smartparkingsystem/presentation/GuardView.fxml"));
					Parent root = fLoader.load();

					GuardViewController guardViewController = fLoader.getController();
					guardViewController.setDatabaseConnector(databaseConnector);
					guardViewController.setCurrentUserName(databaseConnector.getUserName(emailAddress));
					guardViewController.autoInvoke();

					setScene(root);

				} */else if (userType.equals("Faculty")) {
					FXMLLoader fLoader = new FXMLLoader(
							getClass().getResource("E:/Uni Shuni/Semester 6/SE/Project/Parking System/SmartParkingSystem - Final Source/SmartParkingSystem/src/smartparkingsystem/presentation/FacultyView.fxml"));
					Parent root = fLoader.load();

					FacultyViewController facultyViewController = fLoader.getController();
					facultyViewController.setDatabaseConnector(databaseConnector);
					facultyViewController.setCurrentUserName(databaseConnector.getUserName(emailAddress));
					facultyViewController.autoInvoke();

					setScene(root);

				} /*(userType.contentEquals("Student"))*/
				else if (userType.equals("amna")) {
					System.out.println(" i'm in student");
					FXMLLoader fLoader = new FXMLLoader(
							//getClass().getResource("E:/Uni Shuni/Semester 6/SE/Project/Parking System/SmartParkingSystem - Final Source/SmartParkingSystem/src/smartparkingsystem/main/StudentView.fxml"));
					getClass().getResource("./StudentView.fxml"));

					//getClass().getResource("E:/Uni Shuni/Semester 6\SE\Project\Parking System\SmartParkingSystem - Final Source\SmartParkingSystem\src\smartparkingsystem\main/StudentView.fxml"));
					System.out.println("i'm out now");
					Parent root = fLoader.load();

					StudentViewController studentViewController = fLoader.getController();
					studentViewController.setDatabaseConnector(databaseConnector);
					studentViewController.setCurrentUserName(databaseConnector.getUserName(emailAddress));
					studentViewController.autoInvoke();

					setScene(root);
				}

			} else {
				outputLabel.setText("The email address/password do not match!");
			}
		}
		System.out.println("i'm at end");
		outputLabel.setVisible(true);
	}

	@FXML
	void loginButtonClicked(MouseEvent event) throws IOException {
		authenticateUser();
	}

	@FXML
	void loginButtonEnterPressed(KeyEvent event) throws IOException {
		if (event.getCode().equals(KeyCode.ENTER)) {
			authenticateUser();
		}
	}

	public void setDatabaseConnector(DatabaseConnector databaseConnector) {
		this.databaseConnector = databaseConnector;
	}

	private void setScene(Parent root) {
		Stage currentStage = (Stage) loginButton.getScene().getWindow();
		currentStage.setScene(new Scene(root));
		currentStage.setResizable(true);
		currentStage.show();
	}

	@FXML
	void signupPromptClicked(MouseEvent event) throws IOException {
		//FXMLLoader fLoader = new FXMLLoader(getClass().getResource("E:/Uni Shuni/Semester 6/SE/Project/Parking System/SmartParkingSystem - Final Source/SmartParkingSystem/src/smartparkingsystem/main/Signup.fxml"));
		FXMLLoader fLoader = new FXMLLoader(getClass().getResource("./smartparkingsystem/presentation/Signup.fxml"));
		Parent root = fLoader.load();

		SignupController signupController = fLoader.getController();
		signupController.setDatabaseConnector(databaseConnector);
		signupController.autoInvoke();

		setScene(root);
	}
}