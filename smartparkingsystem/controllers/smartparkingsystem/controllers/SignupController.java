package smartparkingsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import smartparkingsystem.database.DatabaseConnector;

public class SignupController {

	@FXML
	private PasswordField confirmPasswordField;

	private DatabaseConnector databaseConnector = null;

	@FXML
	private TextField emailField;

	@FXML
	private TextField nameField;

	@FXML
	private Label outputLabel;

	@FXML
	private PasswordField passwordField;

	@FXML
	private TextField phoneField;

	@FXML
	private Button registerButton;

	@FXML
	private ChoiceBox<String> userTypeChoiceBox;

	public void autoInvoke() {
		userTypeChoiceBox.getItems().addAll("Guard", "Faculty", "Student");
		userTypeChoiceBox.getSelectionModel().selectFirst();
	}

	@FXML
	void registerButtonClicked(MouseEvent event) {
		if (nameField.getText().equals("")) {
			outputLabel.setText("Name is a required field!");
		} else if (emailField.getText().equals("")) {
			outputLabel.setText("Email is a required field!");
		} else if (phoneField.getText().equals("")) {
			outputLabel.setText("Phone is a required field!");
		} else if (passwordField.getText().equals("")) {
			outputLabel.setText("Please enter a password!");
		} else if (confirmPasswordField.getText().equals("")) {
			outputLabel.setText("Please reenter your password!");
		} else if (userTypeChoiceBox.getSelectionModel().getSelectedItem() == null) {
			outputLabel.setText("Please select an user type!");
		}

		String name = nameField.getText();
		String email = emailField.getText();
		String phone = phoneField.getText();
		String password = passwordField.getText();
		String selectedType = userTypeChoiceBox.getSelectionModel().getSelectedItem();
		int type = 0;

		if (selectedType.equals("Guard")) {
			type = 2;
		} else if (selectedType.equals("Faculty")) {
			type = 3;
		} else if (selectedType.equals("Student")) {
			type = 4;
		}

		databaseConnector.addUser(name, email, phone, password, type);
		outputLabel.setTextFill(Color.BLACK);
		outputLabel.setText("Successfully registered!");
	}

	public void setDatabaseConnector(DatabaseConnector databaseConnector) {
		this.databaseConnector = databaseConnector;
	}

}
