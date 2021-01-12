package application.controller;

import application.model.ChangePassword;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChangePWController {
	
	private ChangePassword Change = new ChangePassword();

	private String UserType;
	
	private String userid;
	
	@FXML
	private Label message;
	
	@FXML
	private PasswordField oldPW;
	
	@FXML
	private Stage window;
	
	@FXML
	private PasswordField newPW;

	@FXML
	private SplitPane mainPane;
	
	@FXML
	public void returnButtonClicked(ActionEvent event) throws Exception {
		window = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		if(UserType == "nurse") {
			loader.setLocation(getClass().getClassLoader().getResource("application/view/NurseMain.fxml"));
			mainPane = loader.load();
			NurseMainController id = loader.getController();
			id.getUserID(userid);
			id.getUserType("nurse");
			id.setName(userid);
		}
		else if(UserType == "doc") {
			loader.setLocation(getClass().getClassLoader().getResource("application/view/DoctorMain.fxml"));
			mainPane = loader.load();
			DoctorMainController id = loader.getController();
			id.getUserID(userid);
			id.getUserType("doc");
			id.setName(userid);
		}
		Scene scene = new Scene(mainPane);
		window.setScene(scene);
		window.show();
	}
	
	@FXML
    public void ButtonClicked(ActionEvent event) throws Exception {
		String oldpasswd = oldPW.getText();
		String newpasswd = newPW.getText();
		Alert a = new Alert(AlertType.CONFIRMATION);

		boolean check;
		
		check = Change.changePassword(userid, oldpasswd, UserType, newpasswd);
		System.out.println(check);

		if(!check) {
			message.setStyle("-fx-text-fill: red");
		}
		else {
			a.setContentText("Password Changed successfully");
			a.show();
			window = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
		
			if(UserType == "doc") {
				loader.setLocation(getClass().getClassLoader().getResource("application/view/DoctorMain.fxml"));
				mainPane = loader.load();
				DoctorMainController id = loader.getController();
				id.getUserType(UserType);
				id.getUserID(userid);
				Scene scene = new Scene(mainPane);
				window.setScene(scene);
				window.show();
			}
			else if(UserType == "nurse") {
			loader.setLocation(getClass().getClassLoader().getResource("application/view/NurseMain.fxml"));
			mainPane = loader.load();
			NurseMainController id = loader.getController();
			id.getUserID(userid);
			id.getUserType(UserType);
			Scene scene = new Scene(mainPane);
			window.setScene(scene);
			window.show();
		}
		}
	}	 
	
	public void getUserid(String userid) {
	  	this.userid = userid;
		System.out.println(this.userid);
	}
	
	 public void getUsertype(String user) {
		  	UserType = user;
	 }
	 
}
