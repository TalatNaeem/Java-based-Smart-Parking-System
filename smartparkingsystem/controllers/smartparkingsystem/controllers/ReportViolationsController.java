package smartparkingsystem.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import smartparkingsystem.database.DatabaseConnector;

public class ReportViolationsController {

	private DatabaseConnector databaseConnector = null;

	@FXML
	private TextArea descriptionTextArea;

	@FXML
	private Label outputLabel;

	@FXML
	private TextField reporterIDField;

	@FXML
	private Button reportViolationButton;

	@FXML
	private ChoiceBox<String> violatorVehicleNumberChoiceBox;

	public void populateVehicleNumberChoiceBox() {
		ObservableList<String> currentlyParkedVehicles = databaseConnector.getCurrentlyParkedVehiclesNumbers();
		currentlyParkedVehicles = removeDuplicates(currentlyParkedVehicles);
		violatorVehicleNumberChoiceBox.getItems().addAll(currentlyParkedVehicles);
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

	@FXML
	void reportViolationButtonClicked(MouseEvent event) {
		if (violatorVehicleNumberChoiceBox.getSelectionModel().getSelectedIndex() < 0) {
			outputLabel.setText("You have not select any vehicle registration number!");
		} else if (reporterIDField.getText().isEmpty()) {
			outputLabel.setText("You have not entered your user ID!");
		} else if (descriptionTextArea.getText().isEmpty()) {
			outputLabel.setText("You have not entered a description!");
		} else {
			databaseConnector.addViolation(
					databaseConnector
							.getVehicleID(violatorVehicleNumberChoiceBox.getSelectionModel().getSelectedItem()),
					Integer.parseInt(reporterIDField.getText()), descriptionTextArea.getText());
			outputLabel.setText("Violation reported successfully!");
			outputLabel.setTextFill(Color.BLACK);
		}
	}

	public void setDatabaseConnector(DatabaseConnector databaseConnector) {
		this.databaseConnector = databaseConnector;
	}
}
