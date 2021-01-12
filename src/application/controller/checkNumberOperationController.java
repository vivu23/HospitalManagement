package application.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import application.model.Operation;
import application.model.Patient;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class checkNumberOperationController {
	
	private String userID;
	
	private Operation operation = new Operation();
	
	private ArrayList<String> doctors = new ArrayList<String>();
	
	private ArrayList<Integer> NumOfOp = new ArrayList<Integer>();
	
	private ObservableList<Operation> OperationInfo = FXCollections.observableArrayList();	 

	@FXML
	private Label time;
	
	@FXML
	private TableView<Operation> view;
	
	@FXML
	private TableColumn <Operation, String> docid;
	
	@FXML
	private TableColumn <Operation, String> numOfOperation;
	
	@FXML 
	private SplitPane mainPane;
	
	public void initialize() {
		 Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		 time.setText(LocalDateTime.now().format(formatter));
		 }), new KeyFrame(Duration.seconds(1)));
		 clock.setCycleCount(Animation.INDEFINITE);
		 clock.play();
		 
		 operation.checkNumOperation(doctors, NumOfOp);
		 if (this.doctors.size() > 0) {
			 for(int i =0; i< this.doctors.size(); i++) { 
				 OperationInfo.add(new Operation(null,null, doctors.get(i), null, null, NumOfOp.get(i),0));
			 }
		 }
		 
		 numOfOperation.setCellValueFactory(new PropertyValueFactory<Operation,String>("count"));
		 docid.setCellValueFactory(new PropertyValueFactory<Operation, String>("docid"));
		 view.setItems(OperationInfo);
	}
	 @FXML
	    public void returnButtonClicked(ActionEvent event) throws Exception {
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("application/view/DoctorMain.fxml"));
			mainPane = loader.load();
			DoctorMainController id = loader.getController();
			id.getUserID(userID);
			id.getUserType("doc");
			id.setName(userID);
			Scene scene = new Scene(mainPane);
			window.setScene(scene);
			window.show();
		}
	 public void getUserID(String user) {
		  userID = user;
		  System.out.println(user);
	}
}
