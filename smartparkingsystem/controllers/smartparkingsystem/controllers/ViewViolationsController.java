package smartparkingsystem.controllers;

import java.sql.Date;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import smartparkingsystem.database.DatabaseConnector;
import smartparkingsystem.database.Violation;

public class ViewViolationsController {

	private DatabaseConnector databaseConnector = null;

	@FXML
	private TableColumn<Violation, Date> violationDateOfViolation;

	@FXML
	private TableColumn<Violation, String> violationDescription;

	@FXML
	private TableColumn<Violation, Integer> violationIDColumn;

	private ObservableList<Violation> violationObservableList;

	@FXML
	private TableView<Violation> violationTableView;

	@FXML
	private TableColumn<Violation, String> violationViolatorVehicleNumberColumn;

	@FXML
	private TableColumn<Violation, Integer> violatonReporterID;

	public void autoInvoke() {
		loadViolationsTable();
	}

	private void loadViolationsTable() {
		violationObservableList = databaseConnector.getViolationsObservableList();

		violationIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		violationViolatorVehicleNumberColumn
				.setCellValueFactory(new PropertyValueFactory<>("violatorRegistrationNumber"));
		violatonReporterID.setCellValueFactory(new PropertyValueFactory<>("reporterID"));
		violationDateOfViolation.setCellValueFactory(new PropertyValueFactory<>("date"));
		violationDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

		violationTableView.setItems(violationObservableList);
	}

	public void setDatabaseConnector(DatabaseConnector databaseConnector) {
		this.databaseConnector = databaseConnector;
	}

}
