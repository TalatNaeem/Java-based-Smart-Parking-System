package smartparkingsystem.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import smartparkingsystem.database.DatabaseConnector;
import smartparkingsystem.util.MailSender;

public class RaiseAlertController {

	private DatabaseConnector databaseConnector = null;

	@FXML
	private TextArea descriptionTextArea;

	@FXML
	private Label outputLabel;

	@FXML
	private Button raiseAlertButton;

	@FXML
	private ChoiceBox<String> vehicleNumberChoiceBox;

	public void populateVehicleNumberChoiceBox() {
		ObservableList<String> currentlyParkedVehicles = databaseConnector.getCurrentlyParkedVehiclesNumbers();
		currentlyParkedVehicles = removeDuplicates(currentlyParkedVehicles);
		vehicleNumberChoiceBox.getItems().addAll(currentlyParkedVehicles);
	}

	@FXML
	void raiseAlertButtonClicked(MouseEvent event) {

		if (vehicleNumberChoiceBox.getSelectionModel().getSelectedIndex() < 0) {
			outputLabel.setTextFill(Color.RED);
			outputLabel.setText("You have not select any vehicle registration number!");
		} else if (descriptionTextArea.getText().isEmpty()) {
			outputLabel.setTextFill(Color.RED);
			outputLabel.setText("You have not entered a description!");
		} else {

			MailSender.initializeMail(
					databaseConnector.getEmailByVehicleID(vehicleNumberChoiceBox.getSelectionModel().getSelectedItem()),
					"Parking Alert", descriptionTextArea.getText());
			outputLabel.setText("Alert successfully raised!");
		}
	}

	private ObservableList<String> removeDuplicates(ObservableList<String> currentlyParkedVehicles) {
		ObservableList<String> uniqueVehicles = FXCollections.observableArrayList();

		for (String s : currentlyParkedVehicles) {
			if (!uniqueVehicles.contains(s)) {
				uniqueVehicles.add(s);
			}
		}

		return uniqueVehicles;
	}

	public void setDatabaseConnector(DatabaseConnector databaseConnector) {
		this.databaseConnector = databaseConnector;
	}

}
