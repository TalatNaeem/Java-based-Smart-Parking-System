package smartparkingsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import smartparkingsystem.util.ParkingLot;

public class ParkingSpacesController {

	private ParkingLot parkingLot = null;

	@FXML
	private Pane rootPane;

	public void autoInvoke() {
		// parkingLot.showParking(rootPane);
		int x = 5;
		int y = 15;

		for (int i = 0; i < 20; i++) {
			if (i == 10) {
				x = 5;
				y = y + 35;
			}
			if (parkingLot.getStudentCarsParkingSlot(i) != null) {
				rootPane.getChildren().add(new Circle(x, y, 10, Color.RED));
			} else if (parkingLot.getStudentCarsParkingSlot(i) == null) {
				rootPane.getChildren().add(new Circle(x, y, 10, Color.GREEN));
			}
			x = x + 35;
		}
		x = x + 100;
		y = 15;
		for (int i = 0; i < 20; i++) {
			if (i == 10) {
				x = 455;
				y = y + 35;
			}
			if (parkingLot.getStudentBikesParkingSlot(i) != null) {
				rootPane.getChildren().add(new Circle(x, y, 10, Color.RED));
			} else if (parkingLot.getStudentBikesParkingSlot(i) == null) {
				rootPane.getChildren().add(new Circle(x, y, 10, Color.GREEN));
			}
			x = x + 35;
		}
		x = 5;
		y = y + 65;
		for (int i = 0; i < 20; i++) {
			if (i == 10) {
				x = 5;
				y = y + 35;
			}
			if (parkingLot.getFacultyParkingSlot(i) != null) {
				rootPane.getChildren().add(new Circle(x, y, 10, Color.RED));
			} else if (parkingLot.getFacultyParkingSlot(i) == null) {
				rootPane.getChildren().add(new Circle(x, y, 10, Color.GREEN));
			}
			x = x + 35;
		}
	}

	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}
}