package application;
	
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) throws IOException{
		
		Stage window = new Stage();
		window = primaryStage;
		window.setTitle("Hospital Management");
		window.getIcons().add(new Image("file:images/medicine.png"));
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("application/view/Hospital-Management.fxml"));
		SplitPane layout = new SplitPane();
		layout = loader.load();
		Scene scene = new Scene(layout);		
		window.setScene(scene);
		window.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
