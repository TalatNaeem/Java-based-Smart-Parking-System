package smartparkingsystem.controllers;

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

public class StudentViewController {

	@FXML
	private Label contentTitleLabel;

	@FXML
	private VBox contentViewVBox;

	@FXML
	private Label currentUserNameLabel;

	private DatabaseConnector databaseConnector = null;

	@FXML
	private Button parkingStickerApplicationButton;

	@FXML
	private Button reportViolationButton;

	@FXML
	private Button signOutButton;

	@FXML
	private TableView<?> tableView;

	public void autoInvoke() throws IOException {
		loadParkingStickerApplicationView();
	}

	private void loadParkingStickerApplicationView() throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/smartparkingsystem/presentation/ParkingStickerApplication.fxml"));
		contentViewVBox.getChildren().set(0, loader.load());

		ParkingStickerApplicationController parkingStickerApplicationController = loader.getController();
		parkingStickerApplicationController.setDatabaseConnector(databaseConnector);
		parkingStickerApplicationController
				.setUserID(databaseConnector.getUserIDByName(currentUserNameLabel.getText()));
	}

	@FXML
	void parkingStickerApplicationButtonClicked(MouseEvent event) throws IOException {
		loadParkingStickerApplicationView();
	}

	@FXML
	void reportViolationButtonClicked(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/smartparkingsystem/presentation/ReportViolations.fxml"));
		contentViewVBox.getChildren().set(0, loader.load());
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

}
