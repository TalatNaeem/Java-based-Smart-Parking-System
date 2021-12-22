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

public class AdministratorViewController {

	@FXML
	private Button acceptRejectApplicationsButton;

	@FXML
	private Label contentTitleLabel;

	@FXML
	private VBox contentViewVBox;

	@FXML
	private Label currentUserNameLabel;

	private DatabaseConnector databaseConnector = null;

	@FXML
	private Button signOutButton;

	@FXML
	private TableView<?> tableView;

	@FXML
	private Button viewStickersButton;

	@FXML
	private Button viewUsersButton;

	@FXML
	private Button viewVehiclesButton;

	@FXML
	private Button viewViolationsButton;

	@FXML
	void acceptRejectApplicationsButtonClicked(MouseEvent event) throws IOException {
		loadApplicationTable();
	}

	public void autoInvoke() throws IOException {
		loadApplicationTable();
	}

	private void loadApplicationTable() throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/smartparkingsystem/presentation/ViewApplications.fxml"));
		contentViewVBox.getChildren().set(0, loader.load());
		ViewApplicationsController acceptRejectApplicationsController = loader.getController();
		acceptRejectApplicationsController.setDatabaseConnector(databaseConnector);
		acceptRejectApplicationsController.autoInvoke();
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

	@FXML
	void viewStickersButtonClicked(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/smartparkingsystem/presentation/ViewStickers.fxml"));
		contentViewVBox.getChildren().set(0, loader.load());
		ViewStickersController viewStickersController = loader.getController();
		viewStickersController.setDatabaseConnector(databaseConnector);
		viewStickersController.autoInvoke();
	}

	@FXML
	void viewUsersButtonClicked(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/smartparkingsystem/presentation/ViewUsers.fxml"));
		contentViewVBox.getChildren().set(0, loader.load());
		ViewUsersController viewUsersController = loader.getController();
		viewUsersController.setDatabaseConnector(databaseConnector);
		viewUsersController.autoInvoke();
	}

	@FXML
	void viewVehiclesButtonClicked(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/smartparkingsystem/presentation/ViewVehicles.fxml"));
		contentViewVBox.getChildren().set(0, loader.load());
		ViewVehiclesController viewVehiclesController = loader.getController();
		viewVehiclesController.setDatabaseConnector(databaseConnector);
		viewVehiclesController.autoInvoke();
	}

	@FXML
	void viewViolationsButtonClicked(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/smartparkingsystem/presentation/ViewViolations.fxml"));
		contentViewVBox.getChildren().set(0, loader.load());
		ViewViolationsController viewViolationsController = loader.getController();
		viewViolationsController.setDatabaseConnector(databaseConnector);
		viewViolationsController.autoInvoke();
	}

}
