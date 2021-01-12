package application.controller;

import application.model.UserLogin;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SignUpController {

	private UserLogin User = new UserLogin();
	
	private String userType;
	
	@FXML
	private SplitPane mainPane1;
	
	@FXML 
	private Pane mainPane2;
	
	@FXML
	private TextField fullname;
	
	@FXML
	private TextField userID;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private Label message;
	
    @FXML
    public void buttonClicked(ActionEvent event) throws Exception {
    	String fname = fullname.getText();
    	String userid = userID.getText();
		String passwd = password.getText();
		
		
		
		boolean check = User.createAccount(userType, fname, userid, passwd);
		if(fname.trim().length() == 0 || userid.trim().length() ==0 || passwd.trim().length() == 0) {
			message.setText("Input cannot be empty");
			message.setStyle("-fx-text-fill: red");
		}
		
		else if(!check) {
			message.setText("User already existed");
			message.setStyle("-fx-text-fill: red");
		}
		else {
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			if(userType == "doc") {
				loader.setLocation(getClass().getClassLoader().getResource("application/view/DoctorMain.fxml"));
				mainPane2 = loader.load();
				DoctorMainController id = loader.getController();
				id.getUserID(userid);
				id.getUserType(userType);
				id.setName(userid);
			}
			else if (userType == "nurse") {
				loader.setLocation(getClass().getClassLoader().getResource("application/view/NurseMain.fxml"));
				mainPane2 = loader.load();
				NurseMainController id = loader.getController();
				id.getUserID(userid);
				id.getUserType(userType);
				id.setName(userid);
			}
			Scene scene = new Scene(mainPane2);
			window.setScene(scene);
			window.show();
		}
	}
    
    @FXML
    public void returnButtonClicked(ActionEvent event) throws Exception {
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("application/view/Hospital-Management.fxml"));
		mainPane1 = loader.load();
		Scene scene = new Scene(mainPane1);
		window.setScene(scene);
		window.show();
	}
    
    public String getUserType(String user) {
	  	userType = user;
		System.out.println(user);
		return user;
	}
}
