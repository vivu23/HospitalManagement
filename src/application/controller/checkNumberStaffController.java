package application.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import application.model.Operation;
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

public class checkNumberStaffController {
	
	private String userID;
	
	private int numStaff; 
	
	private Operation operation = new Operation();
	
	private ArrayList<String> opName = new ArrayList<String>();
	
	private ArrayList<String> opID = new ArrayList<String>();
	
	private ArrayList<Integer> numofStaff = new ArrayList<Integer>();
	
	private ObservableList<Operation> OperationInfo = FXCollections.observableArrayList();	 

	@FXML
	private Label time;
	
	@FXML
	private TableView<Operation> view;
	
	@FXML
	private TableColumn <Operation, String> operationName;
	
	@FXML
	private TableColumn <Operation, String> operationID;
	
	@FXML
	private TableColumn<Operation, Integer> num;
	
	@FXML 
	private SplitPane mainPane1;
	
	public void initialize() {
		 Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		 time.setText(LocalDateTime.now().format(formatter));
		 }), new KeyFrame(Duration.seconds(1)));
		 clock.setCycleCount(Animation.INDEFINITE);
		 clock.play();
	}
	
	 @FXML
	 public void searchButtonClicked(ActionEvent event) throws Exception {
			System.out.println(numStaff);
		 operation.checkNumofStaff(opID, opName, numofStaff,numStaff);
		 if (this.opID.size() > 0) {
			 for(int i =0; i< this.opID.size(); i++) { 
				 OperationInfo.add(new Operation(opID.get(i),opName.get(i), null, null, null, 0,numofStaff.get(i)));
			 }
		 }
		 
		 operationName.setCellValueFactory(new PropertyValueFactory<Operation,String>("operationName"));
		 operationID.setCellValueFactory(new PropertyValueFactory<Operation, String>("operationID"));
		 num.setCellValueFactory(new PropertyValueFactory<Operation, Integer>("numofStaff"));
		 view.setItems(OperationInfo);
	}
	 @FXML
	 public void returnButtonClicked(ActionEvent event) throws Exception {
	    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("application/view/DoctorMain.fxml"));
		mainPane1 = loader.load();
		DoctorMainController id = loader.getController();
		id.getUserID(userID);
		id.getUserType("doc");
		id.setName(userID);
		Scene scene = new Scene(mainPane1);
		window.setScene(scene);
		window.show();
	}
	 
	 
	 public void getUserID(String user) {
		  userID = user;
	}
	 
	public void getNumStaff(int num) {
		numStaff = num;
	}
}
