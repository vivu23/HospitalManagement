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

public class DoctorMainController {
	
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
	 
	 @FXML
	 public void AddDeleteOperationClicked(ActionEvent event) throws Exception {
	   	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("application/view/checkOperation.fxml"));
		mainPane1 = loader.load();
		AddOperationController user = loader.getController();
		user.getUserID(userID);
		Scene scene = new Scene(mainPane1);
		window.setScene(scene);
		window.show();
	}	
	 
	 @FXML
	 public void checkOperationClicked(ActionEvent event) throws Exception {
	   	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("application/view/CheckMyOperation.fxml"));
		mainPane1 = loader.load();
		checkMyOperationController id = loader.getController();
		id.setUserID(userID);
		Scene scene = new Scene(mainPane1);
		window.setScene(scene);
		window.show();
	}	
	 
	 @FXML
	 public void checkNumOperationClicked(ActionEvent event) throws Exception {
	   	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("application/view/checkNumberOperation.fxml"));
		mainPane1 = loader.load();
		checkNumberOperationController user = loader.getController();
		user.getUserID(userID);
		Scene scene = new Scene(mainPane1);
		window.setScene(scene);
		window.show();
	}	
	 
	@FXML
	public void UpdatingStaffClicked(ActionEvent event) throws Exception {
	   	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("application/view/UpdatingStaffNum.fxml"));
		mainPane = loader.load();
		UpdatingStaffNumController user = loader.getController();
		user.getUserID(userID);
		Scene scene = new Scene(mainPane);
		window.setScene(scene);
		window.show();
	}	
	
	 @FXML
	 public void checkNumofStaffClicked(ActionEvent event) throws Exception {
	    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("application/view/Option.fxml"));
		mainPane = loader.load();
		OptionController id = loader.getController();
		id.getUserID(userID);
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
	  	userID = user;
		System.out.println(user);
	}
	
	 public void getUserType(String user) {
		  	userType = user;
	 }
	 
	 public void setName(String userID) {
		 this.fullname = User.getFullName(userType, userID);
		 name.setText("Hello " + fullname + "!");
	 }
}
