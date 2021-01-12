package application.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import application.model.Operation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UpdatingStaffNumController {
	
	private Operation operation = new Operation();
	
	private String userid;
	
	@FXML
	private Label time;
	
	@FXML
	private Label message;
	@FXML
	private TextField operationName;
	
	@FXML
	private TextField newNumofStaff;

	@FXML
	private SplitPane mainPane;

	@FXML
    public void ButtonClicked(ActionEvent event) throws Exception {
		String opName = operationName.getText();
		String numOfStaff = newNumofStaff.getText();
		Alert a = new Alert(AlertType.CONFIRMATION);
		
		boolean check;
		opName = opName.substring(0,1).toUpperCase() + opName.substring(1);
		check = operation.changeNumofStaff(opName, Integer.parseInt(numOfStaff));
		System.out.println(check);

		if(!check) {
			message.setText("Please check your inputs");
			message.setStyle("-fx-text-fill: red");
		}
		else {
			a.setContentText("Number of staff changed successfully");
			a.show();
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("application/view/DoctorMain.fxml"));
			mainPane = loader.load();
			DoctorMainController id = loader.getController();
			id.getUserType("doc");
			id.getUserID(userid);
			Scene scene = new Scene(mainPane);
			window.setScene(scene);
			window.show();
		}
	}
	 @FXML
	 public void returnButtonClicked(ActionEvent event) throws Exception {
	    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("application/view/DoctorMain.fxml"));
		mainPane = loader.load();
		DoctorMainController id = loader.getController();
		id.getUserID(userid);
		id.getUserType("doc");
		id.setName(userid);
		Scene scene = new Scene(mainPane);
		window.setScene(scene);
		window.show();
	}
	 
	 @FXML
		public void initialize() {
			 Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
			 time.setText(LocalDateTime.now().format(formatter));
			 }), new KeyFrame(Duration.seconds(1)));
			 clock.setCycleCount(Animation.INDEFINITE);
			 clock.play();
		}
	public void getUserID(String user) {
	  	userid = user;
		System.out.println(user);
	}
}
