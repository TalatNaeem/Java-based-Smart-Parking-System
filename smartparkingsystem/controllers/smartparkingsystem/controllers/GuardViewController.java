package smartparkingsystem.controllers;
/*package smartparkingsystem.controllers;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import smartparkingsystem.database.DatabaseConnector;
import smartparkingsystem.util.ParkingLot;

public class GuardViewController {

	@FXML
	private Label contentTitleLabel;

	@FXML
	private VBox contentViewVBox;

	@FXML
	private Label currentUserNameLabel;

	private DatabaseConnector databaseConnector = null;

	private ParkingLot parkingLot;

	@FXML
	private Button parkingSpacesButton;

	@FXML
	private Button raiseAlertButton;

	@FXML
	private Button reportViolationsButton;

	@FXML
	private Button scanPlatesButton;

	@FXML
	private Button signOutButton;

	@FXML
	private TableView<?> tableView;

	public void autoInvoke() throws IOException {
		parkingLot = new ParkingLot();
		loadScanPlatesView();
	}

private void loadScanPlatesView() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/smartparkingsystem/presentation/ScanPlates.fxml"));
		contentViewVBox.getChildren().set(0, loader.load());

		ScanPlatesController scanPlatesController = loader.getController();
		scanPlatesController.setDatabaseConnector(databaseConnector);
		scanPlatesController.setParkingLot(parkingLot);
	}

	@FXML
	void parkingSpacesButtonClicked(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/smartparkingsystem/presentation/ParkingSpaces.fxml"));
		contentViewVBox.getChildren().set(0, loader.load());

		ParkingSpacesController parkingspace = loader.getController();
		parkingspace.setParkingLot(parkingLot);
		parkingspace.autoInvoke();
	}

	@FXML
	void raiseAlertButtonClicked(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/smartparkingsystem/presentation/RaiseAlert.fxml"));
		contentViewVBox.getChildren().set(0, loader.load());

		RaiseAlertController raiseAlertController = loader.getController();
		raiseAlertController.setDatabaseConnector(databaseConnector);
		raiseAlertController.populateVehicleNumberChoiceBox();
	}

	@FXML
	void reportViolationsButtonClicked(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/smartparkingsystem/presentation/ReportViolations.fxml"));
		contentViewVBox.getChildren().set(0, loader.load());

		ReportViolationsController reportViolationsController = loader.getController();
		reportViolationsController.setDatabaseConnector(databaseConnector);
		reportViolationsController.populateVehicleNumberChoiceBox();
	}

	@FXML
	void scanPlatesButtonClicked(MouseEvent event) throws IOException {
		loadScanPlatesView();
	}

	public void setCurrentUserName(String username) {
		currentUserNameLabel.setText(username);
	}

	public void setDatabaseConnector(DatabaseConnector databaseConnector) {
		this.databaseConnector = databaseConnector;
	}

	@FXML
	void signOutButtonClicked(MouseEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Sign Out Confirmation Dialog");
		alert.setContentText("Are you sure you want to sign out?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/smartparkingsystem/presentation/Login.fxml"));
			Parent root = loader.load();

			Stage currentStage = (Stage) signOutButton.getScene().getWindow();
			LoginController loginController = loader.getController();
			loginController.setDatabaseConnector(databaseConnector);

			currentStage.setScene(new Scene(root));
			currentStage.setTitle("Smart Parking System");
			currentStage.setResizable(false);
			currentStage.show();
		}
	}

}*/
