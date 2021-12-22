package smartparkingsystem.controllers;

import java.sql.Date;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import smartparkingsystem.database.DatabaseConnector;
import smartparkingsystem.database.Sticker;

public class ViewStickersController {

	private DatabaseConnector databaseConnector = null;

	@FXML
	private TableColumn<Sticker, Integer> stickerIDColumn;

	@FXML
	private TableColumn<Sticker, Integer> stickerIssuedToColumn;

	private ObservableList<Sticker> stickersObservableList;

	@FXML
	private TableView<Sticker> stickerTableView;

	@FXML
	private TableColumn<Sticker, Date> stickerValidFromColumn;

	@FXML
	private TableColumn<Sticker, Date> stickerValidTillColumn;

	public void autoInvoke() {
		loadStickersTable();
	}

	private void loadStickersTable() {
		stickersObservableList = databaseConnector.getStickersObservableList();

		stickerIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		stickerIssuedToColumn.setCellValueFactory(new PropertyValueFactory<>("issuedTo"));
		stickerValidFromColumn.setCellValueFactory(new PropertyValueFactory<>("validFrom"));
		stickerValidTillColumn.setCellValueFactory(new PropertyValueFactory<>("validTill"));

		stickerTableView.setItems(stickersObservableList);
	}

	public void setDatabaseConnector(DatabaseConnector databaseConnector) {
		this.databaseConnector = databaseConnector;
	}

}
