package application.controller;

import application.model.Operation;
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

public class OptionController {
	
	private String userid;
	
	@FXML
	private Label time;
	@FXML
	private TextField num;

	@FXML
	private Label message;
	
	@FXML
	private SplitPane mainPane;

	@FXML
	private Pane mainPane1;
	@FXML
    public void ButtonClicked(ActionEvent event) throws Exception {
			
		if(num.getText().length() == 0) {
			message.setText("Input cannot be empty");
			message.setStyle("-fx-text-fill: red");
		}
		else {
			int number = Integer.parseInt(num.getText());
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("application/view/checkNumberStaff.fxml"));
			mainPane1 = loader.load();
			checkNumberStaffController id = loader.getController();
			id.getNumStaff(number);
			id.getUserID(userid);
			Scene scene = new Scene(mainPane1);
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
	 
	public void getUserID(String user) {
	  	userid = user;
		System.out.println(user);
	}
}
