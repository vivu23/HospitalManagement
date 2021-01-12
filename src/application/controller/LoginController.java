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

public class LoginController {
	
	private UserLogin User = new UserLogin();
	
	private String userType;
	
	@FXML 
	private SplitPane mainPane;
	
	@FXML
	private TextField userID;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private Label message;
	
	  @FXML
	    public void returnButtonClicked(ActionEvent event) throws Exception {
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("application/view/Hospital-Management.fxml"));
			mainPane = loader.load();
			Scene scene = new Scene(mainPane);
			window.setScene(scene);
			window.show();
		}
	  
	  @FXML
	    public void buttonClicked(ActionEvent event) throws Exception {
		  	String userid = userID.getText();
			String passwd = password.getText();
			boolean check = User.checkPassword(userType, userid, passwd);
			if(!check) {
				message.setStyle("-fx-text-fill: red");
				userID.clear();
				password.clear();
			}
			else {
				Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
				FXMLLoader loader = new FXMLLoader();
				if(userType == "doc") {
					loader.setLocation(getClass().getClassLoader().getResource("application/view/DoctorMain.fxml"));
					mainPane = loader.load();
					DoctorMainController id = loader.getController();
					id.getUserID(userid);
					id.getUserType(userType);
					id.setName(userid);
				}
				else if (userType == "nurse") {
					loader.setLocation(getClass().getClassLoader().getResource("application/view/NurseMain.fxml"));
					mainPane = loader.load();
					NurseMainController id = loader.getController();
					id.getUserID(userid);
					id.getUserType(userType);
					id.setName(userid);
				}
				Scene scene = new Scene(mainPane);
				window.setScene(scene);
				window.show();
			}
		}
	  
	  @FXML
	    public void signUpbuttonClicked(ActionEvent event) throws Exception {
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("application/view/SignUp.fxml"));
			mainPane = loader.load();
			SignUpController user = loader.getController();
			user.getUserType(userType);
			Scene scene = new Scene(mainPane);
			window.setScene(scene);
			window.show();
		}
	  
	  
	  public void getUserType(String user) {
		  	userType = user;
			System.out.println(user);
		}
}
