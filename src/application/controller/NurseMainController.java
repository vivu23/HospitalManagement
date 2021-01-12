package application.controller;

import application.model.UserLogin;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NurseMainController {
	
private UserLogin User = new UserLogin();
	
	@FXML
	private Label name;
	
	@FXML
	private Label time;
	
	@FXML
	private SplitPane mainPane;
	
	@FXML
	private Pane mainPane1; 
	
	private String userID;
	
	private String userType;
	
	private String fullname;
	
	 @FXML
	    public void LogoutButtonClicked(ActionEvent event) throws Exception {
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("application/view/Hospital-Management.fxml"));
			mainPane = loader.load();
			Scene scene = new Scene(mainPane);
			window.setScene(scene);
			window.show();
		}
	@FXML
	 public void PatientbuttonClicked(ActionEvent event) throws Exception {
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("application/view/AddDeletePatient.fxml"));
			mainPane1 = loader.load();
			checkPatientController id = loader.getController();
			id.getUserID(userID);
			Scene scene = new Scene(mainPane1);
			window.setScene(scene);
			window.show();
		}

	 @FXML
	    public void changePWClicked(ActionEvent event) throws Exception {
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("application/view/ChangePassword.fxml"));
			mainPane = loader.load();
			ChangePWController id = loader.getController();
			id.getUserid(userID);
			id.getUsertype(userType);
			Scene scene = new Scene(mainPane);
			window.setScene(scene);
			window.show();
		}	 
//	 
//	 @FXML
//	    public void OperationButtonClicked(ActionEvent event) throws Exception {
//	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(getClass().getClassLoader().getResource("application/view/NurseCheck.fxml"));
//			mainPane = loader.load();
//			Scene scene = new Scene(mainPane);
//			window.setScene(scene);
//			window.show();
//		}
	public void initialize() {
		 Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		 time.setText(LocalDateTime.now().format(formatter));
		 }), new KeyFrame(Duration.seconds(1)));
		 clock.setCycleCount(Animation.INDEFINITE);
		 clock.play();
	}
	public void getUserID(String user) {
	  	userID = user;
	}
	
	 public void getUserType(String user) {
		  	userType = user;
	 }
	 
	 public void setName(String userID) {
		 this.fullname = User.getFullName(userType, userID);
		 name.setText("Hello " + fullname + "!");
	 }
}
