package smartparkingsystem.controllers;
/*package smartparkingsystem.controllers;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import smartparkingsystem.database.DatabaseConnector;
import smartparkingsystem.util.ParkingLot;
import smartparkingsystem.util.PlateScanner;

public class ScanPlatesController {

	@FXML
	private Button chooseImageButton;

	private DatabaseConnector databaseConnector = null;

	@FXML
	private Label fileNameLabel;

	@FXML
	private Label licenseNumberLabel;

	@FXML
	private Label outputLabel;

	private ParkingLot parkingLot;

	@FXML
	void chooseImageButtonClicked(MouseEvent event) {
		licenseNumberLabel.setText("");
		outputLabel.setText("");
		File chosenFile = null;
		chosenFile = getImageFile();

		if (chosenFile != null) {
			if (chosenFile.isFile()) {
				fileNameLabel.setText(chosenFile.getName());
				PlateScanner iReader = new PlateScanner();
				String registrationNumber = iReader.getLicenseNumber(chosenFile);
				licenseNumberLabel.setText(registrationNumber);

				if (databaseConnector.searchRegistrationNumber(registrationNumber)) {
					int isEntry = databaseConnector.addVehicleEntry(registrationNumber);
					updateParkingLot(isEntry, registrationNumber);
					outputLabel.setTextFill(Color.BLACK);
					outputLabel.setText("Authorized vehicle detected... Opening the gate!");
				} else {
					outputLabel.setText("Unknown vehicle detected... Access denied!");
				}
			}
		} else {
			outputLabel.setText("Please select an image file by clicking the Choose Image button!");
		}
	}

	private File getImageFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/resources"));
		fileChooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("Image files", "*.jpeg", "*.jpg", "*.png"));
		return fileChooser.showOpenDialog(chooseImageButton.getScene().getWindow());
	}

	public void setDatabaseConnector(DatabaseConnector databaseConnector) {
		this.databaseConnector = databaseConnector;
	}

	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}

	private void updateParkingLot(int isEntry, String registrationNumber) {
		if (isEntry == 0) {
			parkingLot.removeVehicle(registrationNumber);
		} else {
			parkingLot.parkVehicle(registrationNumber);
		}
	}

}
*/