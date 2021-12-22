package smartparkingsystem.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import smartparkingsystem.controllers.LoginController;
import smartparkingsystem.database.DatabaseConnector;

public class main extends Application{

	@Override
	public void start(Stage primaryStage) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("./Login.fxml"));

		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			System.err.println("Caught an IOException when trying to load the LoginView");
			System.exit(-1);
		}

		DatabaseConnector databaseConnector = new DatabaseConnector("localhost", "3306", "talat", "1234");
		databaseConnector.initializeDatabase();

		LoginController loginController = loader.getController();
		loginController.setDatabaseConnector(databaseConnector);

		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Smart Parking System");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	public static void main(String[]args)
	{
		launch(args);
	}
}