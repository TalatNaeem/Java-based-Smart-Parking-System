package smartparkingsystem.controllers;

import java.sql.Date;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import smartparkingsystem.database.Application;
import smartparkingsystem.database.DatabaseConnector;

public class ViewApplicationsController {

	@FXML
	private TableColumn<Application, Integer> applicationApplierIDColumn;

	@FXML
	private TableColumn<Application, Date> applicationDateReviewedColumn;

	@FXML
	private TableColumn<Application, Date> applicationDateSubmittedColumn;

	@FXML
	private TableColumn<Application, Integer> applicationIDColumn;

	@FXML
	private TableColumn<Application, Boolean> applicationIsApprovedColumn;

	private ObservableList<Application> applicationsObservableList;

	@FXML
	private TableView<Application> applicationTableView;

	@FXML
	private TableColumn<Application, String> applicationVehicleRegistrationNumberColumn;

	@FXML
	private Button approveApplicationButton;

	private DatabaseConnector databaseConnector = null;

	@FXML
	private Label outputLabel;

	@FXML
	private Button rejectApplicationButton;

	@FXML
	void approveApplicationButtonClicked(MouseEvent event) {
		Application selectedApplication = applicationTableView.getSelectionModel().getSelectedItem();

		if (selectedApplication == null) {
			outputLabel.setText("You have not selected any application!");
			outputLabel.setTextFill(Color.RED);
		} else {
			if (databaseConnector.isApplicationReviewed(selectedApplication.getId())) {
				outputLabel.setText("This application has been reviewed already!");
				outputLabel.setTextFill(Color.RED);
			} else {
				databaseConnector.approveApplication(selectedApplication.getId());
				loadApplicationsTable();
				outputLabel.setText("Application successfully approved!");
				outputLabel.setTextFill(Color.BLACK);
			}
		}
	}

	public void autoInvoke() {
		loadApplicationsTable();
	}

	private void loadApplicationsTable() {
		applicationsObservableList = databaseConnector.getApplicationsObservableList();

		applicationIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		applicationApplierIDColumn.setCellValueFactory(new PropertyValueFactory<>("applierID"));
		applicationVehicleRegistrationNumberColumn
				.setCellValueFactory(new PropertyValueFactory<>("vehicleRegistration"));
		applicationDateSubmittedColumn.setCellValueFactory(new PropertyValueFactory<>("dateSubmitted"));
		applicationDateReviewedColumn.setCellValueFactory(new PropertyValueFactory<>("dateApproved"));
		applicationIsApprovedColumn.setCellValueFactory(new PropertyValueFactory<>("approved"));

		applicationTableView.setItems(applicationsObservableList);
		applicationTableView.getSelectionModel().setCellSelectionEnabled(true);
	}

	@FXML
	void rejectApplicationButtonClicked(MouseEvent event) {
		Application selectedApplication = applicationTableView.getSelectionModel().getSelectedItem();

		if (selectedApplication == null) {
			outputLabel.setText("You have not selected any application!");
			outputLabel.setTextFill(Color.RED);
		} else {
			if (databaseConnector.isApplicationReviewed(selectedApplication.getId())) {
				outputLabel.setText("This application has been reviewed already!");
				outputLabel.setTextFill(Color.RED);
			} else {
				databaseConnector.rejectApplication(selectedApplication.getId());
				loadApplicationsTable();
				outputLabel.setText("Application successfully rejected!");
				outputLabel.setTextFill(Color.BLACK);
			}
		}
	}

	public void setDatabaseConnector(DatabaseConnector databaseConnector) {
		this.databaseConnector = databaseConnector;
	}
}
