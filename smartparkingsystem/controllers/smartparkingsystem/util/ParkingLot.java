package smartparkingsystem.util;

import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ParkingLot {

	private String[] facultyParkingSlots = null;
	private String[] studentBikeParkingSlots = null;
	private String[] studentParkingSlots = null;

	public ParkingLot() {
		facultyParkingSlots = new String[20];
		studentBikeParkingSlots = new String[20];
		studentParkingSlots = new String[20];
	}

	public String getFacultyParkingSlot(int index) {
		return facultyParkingSlots[index];
	}

	public String getStudentBikesParkingSlot(int index) {
		return studentBikeParkingSlots[index];
	}

	public String getStudentCarsParkingSlot(int index) {
		return studentParkingSlots[index];
	}

	public void parkVehicle(String vehicleRegistrationNumber) {
		Random random = new Random();
		int spaceType = random.nextInt(3);
		int spaceIndex = random.nextInt(20);

		if (spaceType == 0) {
			if (studentParkingSlots[spaceIndex] == null)
				studentParkingSlots[spaceIndex] = vehicleRegistrationNumber;
			else {
				while (studentParkingSlots[spaceIndex] != null) {
					spaceIndex = random.nextInt(20);
					studentParkingSlots[spaceIndex] = vehicleRegistrationNumber;
				}
			}
		} else if (spaceType == 1) {
			if (studentBikeParkingSlots[spaceIndex] == null)
				studentBikeParkingSlots[spaceIndex] = vehicleRegistrationNumber;
			else {
				while (studentBikeParkingSlots[spaceIndex] != null) {
					spaceIndex = random.nextInt(20);
					studentBikeParkingSlots[spaceIndex] = vehicleRegistrationNumber;
				}
			}
		} else if (spaceType == 2) {
			if (facultyParkingSlots[spaceIndex] == null)
				facultyParkingSlots[spaceIndex] = vehicleRegistrationNumber;
			else {
				while (facultyParkingSlots[spaceIndex] != null) {
					spaceIndex = random.nextInt(20);
					facultyParkingSlots[spaceIndex] = vehicleRegistrationNumber;
				}
			}
		}
	}

	public void removeVehicle(String vehicleRegistrationNumber) {
		for (int i = 0; i < 20; i++) {
			if (studentParkingSlots[i] != null && studentParkingSlots[i].equals(vehicleRegistrationNumber)) {
				studentParkingSlots[i] = null;
				break;
			} else if (studentBikeParkingSlots[i] != null
					&& studentBikeParkingSlots[i].equals(vehicleRegistrationNumber)) {
				studentBikeParkingSlots[i] = null;
				break;
			} else if (facultyParkingSlots[i] != null && facultyParkingSlots[i].equals(vehicleRegistrationNumber)) {
				facultyParkingSlots[i] = null;
				break;
			}
		}
	}

	public void showParking(Pane rootPane) {
		int x = 5;
		int y = 15;

		for (int i = 0; i < 20; i++) {
			if (i == 10) {
				x = 5;
				y = y + 35;
			}
			if (getStudentCarsParkingSlot(i) != null) {
				rootPane.getChildren().add(new Circle(x, y, 10, Color.RED));
			} else if (getStudentCarsParkingSlot(i) == null) {
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
			if (getStudentBikesParkingSlot(i) != null) {
				rootPane.getChildren().add(new Circle(x, y, 10, Color.RED));
			} else if (getStudentBikesParkingSlot(i) == null) {
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
			if (getFacultyParkingSlot(i) != null) {
				rootPane.getChildren().add(new Circle(x, y, 10, Color.RED));
			} else if (getFacultyParkingSlot(i) == null) {
				rootPane.getChildren().add(new Circle(x, y, 10, Color.GREEN));
			}
			x = x + 35;
		}
	}
}