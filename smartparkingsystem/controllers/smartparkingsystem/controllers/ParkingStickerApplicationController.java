package smartparkingsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import smartparkingsystem.database.DatabaseConnector;

public class ParkingStickerApplicationController {

	private DatabaseConnector databaseConnector = null;

	@FXML
	private TextField licenseField;

	@FXML
	private Label outputLabel;

	@FXML
	private Button submitButton;

	private int userID = -1;

	public void setDatabaseConnector(DatabaseConnector databaseConnector) {
		this.databaseConnector = databaseConnector;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	@FXML
	void submitButtonPressed(MouseEvent event) {

		outputLabel.setVisible(true);
		outputLabel.setText("");
		if (licenseField.getText().equals("")) {
			outputLabel.setText("Vehicle number cannot be empty!");
		} else {

			int returnValue = databaseConnector.addStickerApplication(userID, licenseField.getText());
			if (returnValue == 0) {
				outputLabel.setTextFill(Color.BLACK);
				outputLabel.setText("Your application has been successfully submitted!");
			} else if (returnValue == 1) {
				outputLabel.setTextFill(Color.RED);
				outputLabel.setText("Your application is already pending approval!");
			} else if (returnValue == 2) {
				outputLabel.setTextFill(Color.RED);
				outputLabel.setText("Your application is already approvedl!");
			}
		}
	}

}
