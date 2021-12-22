package smartparkingsystem.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import smartparkingsystem.database.DatabaseConnector;
import smartparkingsystem.database.User;

public class ViewUsersController {

	private DatabaseConnector databaseConnector = null;

	@FXML
	private TableColumn<User, String> userEmailColumn;

	@FXML
	private TableColumn<User, Integer> userIDColumn;

	@FXML
	private TableColumn<User, String> userNameColumn;

	@FXML
	private TableColumn<User, String> userPhoneColumn;

	private ObservableList<User> usersObservableList;

	@FXML
	private TableView<User> userTableView;

	@FXML
	private TableColumn<User, Integer> userTypeColumn;

	public void autoInvoke() {
		loadUsersTable();
	}

	private void loadUsersTable() {
		usersObservableList = databaseConnector.getUsersObservableList();

		userIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		userNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		userEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		userPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
		userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

		userTableView.setItems(usersObservableList);
	}

	public void setDatabaseConnector(DatabaseConnector databaseConnector) {
		this.databaseConnector = databaseConnector;
	}

}
