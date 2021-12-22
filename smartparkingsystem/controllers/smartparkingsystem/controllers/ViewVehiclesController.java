package smartparkingsystem.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import smartparkingsystem.database.DatabaseConnector;
import smartparkingsystem.database.Vehicle;

public class ViewVehiclesController {

	private DatabaseConnector databaseConnector = null;

	@FXML
	private TableColumn<Vehicle, Integer> vehicleIDColumn;

	@FXML
	private TableColumn<Vehicle, Integer> vehicleOwnerIDColumn;

	@FXML
	private TableColumn<Vehicle, String> vehicleRegistrationNumberColumn;

	private ObservableList<Vehicle> vehiclesObservableList;

	@FXML
	private TableView<Vehicle> vehicleTableView;

	public void autoInvoke() {
		loadVehiclesTable();
	}

	private void loadVehiclesTable() {
		vehiclesObservableList = databaseConnector.getVehiclesObservableList();

		vehicleIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		vehicleRegistrationNumberColumn.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));
		vehicleOwnerIDColumn.setCellValueFactory(new PropertyValueFactory<>("ownerID"));

		vehicleTableView.setItems(vehiclesObservableList);
	}

	public void setDatabaseConnector(DatabaseConnector databaseConnector) {
		this.databaseConnector = databaseConnector;
	}

}
