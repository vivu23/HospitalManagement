package application.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import application.model.Operation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
/* 
 *  public class AddOperationController
 *  
 *  This is a controller class that control all of the buttons in CheckMyOperation.fxml
 *  
 */

public class AddOperationController {
		
	private Operation operation = new Operation();
	
	//ArrayList which holds all the operation IDs
	private ArrayList<String> operationID = new ArrayList<String>();
	
	//ArrayList which holds all the operation Names
	private ArrayList<String> operationName = new ArrayList<String>();
	
	//ArrayList which holds all the number of Staff of each operation
	private ArrayList<Integer> numOfStaff = new ArrayList<Integer>();
	
	//ArrayList which holds all the patient information of each operation
	private ArrayList<String> patients = new ArrayList<String>();
	
	//ArrayList which holds all the doctor information of each operation
	private ArrayList<String> doctors = new ArrayList<String>();
	
	//ArrayList which holds all the date for each operation
	private ArrayList<LocalDate> scheduleDate = new ArrayList<LocalDate>();
	
	private ObservableList<Operation> OperationInfo = FXCollections.observableArrayList();	 
	
	@FXML
	private SplitPane mainPane;
	
	@FXML
	private Label time;
	
	@FXML
	private TableView<Operation> view;
	
	@FXML
	private TableColumn <Operation, String> opID;
	
	@FXML
	private TableColumn <Operation, String> opName;
	
	@FXML
	private TableColumn <Operation, String> doc;
	
	@FXML
	private TableColumn <Operation, String> patientid;
	
	@FXML
	private TableColumn <Operation, Integer> numofstaff;
	
	@FXML
	private TableColumn <Operation, String> date;
	
	@FXML
	private TextField idInput;
	
	@FXML
	private TextField nameInput;
	
	@FXML
	private TextField docInput;
	
	@FXML
	private TextField patientInput;
	
	@FXML
	private TextField StaffnumInput;
	
	@FXML
	private DatePicker DateInput;
	
	@FXML
	private Label message;
	
	private String userID;
	
	@FXML
	Stage window = new Stage();
	
	/*
	 * public void returnButtonClicked(ActionEvent) 
	 * 
	 * This function is used to control the return button on the fxml file. 
	 * It will take the user back to the Main Doctor Menu, and also pass the full name of the user back to the previous 
	 * page.
	 * 
	 */
	 @FXML
	    public void returnButtonClicked(ActionEvent event) throws Exception {
	    	window = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("application/view/DoctorMain.fxml"));
			mainPane = loader.load();
			DoctorMainController id = loader.getController();
			id.getUserID(userID);
			id.getUserType("doc");
			Scene scene = new Scene(mainPane);
			window.setScene(scene);
			window.show();
		}
	 
	 /*
	  * public void initialize()
	  * 
	  * This function will initialize the clock and the operation list.
	  *
	  */
	 
	@FXML
	 public void initialize() {
		//Setting the calendar on the screen	
		 Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		 time.setText(LocalDateTime.now().format(formatter));
		 }), new KeyFrame(Duration.seconds(1)));
		 clock.setCycleCount(Animation.INDEFINITE);
		 clock.play();
		 
		 //Take all the information of all operations from the database
		 operation.showOperation(doctors, patients, operationID, operationName, scheduleDate, numOfStaff);
		 
		 //Put each information into each corresponding ArrayList
		 if (this.operationID.size() > 0) {
			 for(int i =0; i< this.operationID.size(); i++) { 
				 OperationInfo.add(new Operation(this.operationID.get(i), this.operationName.get(i), this.doctors.get(i), this.patients.get(i),
						 this.scheduleDate.get(i), 0,this.numOfStaff.get(i)));
			 }
		 }
		 
		 //Putting each value to a corresponding column in the table
		 opID.setCellValueFactory(new PropertyValueFactory<>("operationID"));

		 opName.setCellValueFactory(new PropertyValueFactory<>("operationName"));

		 doc.setCellValueFactory(new PropertyValueFactory<>("docid"));
		 
		 patientid.setCellValueFactory(new PropertyValueFactory<>("patientid"));

		 numofstaff.setCellValueFactory(new PropertyValueFactory<>("numofStaff"));
		 
		 date.setCellValueFactory(new PropertyValueFactory<>("Strdate"));
		 
		 view.setItems(OperationInfo);
	}
	 
	/*
	 * public void AddButtonClicked(ActionEvent)
	 * Control the add operation button.
	 * 
	 * If the user input is invalid, there will be message telling the user to check their input. 
	 * If the user input is valid, the new operation information will be added to the database and the information will
	 * be added to the list on the screen. A message will tell the user that the operation is added successfully 
	 * 
	 */
	 @FXML
	 public void AddButtonClicked(ActionEvent event) throws Exception {
		 String id = idInput.getText();
		 String name = nameInput.getText();
		 String doc = docInput.getText();
		 String patient = patientInput.getText();
		 
		 //Check if any input of the operation is empty or not 
		 if(id == null || name == null || doc == null || patient == null || StaffnumInput.getText() == null  || 
				 DateInput.getValue() == null) {
				
			 	//If any inputs is empty, there will be a message tell the user that input cannot be empty
			 	message.setText("Input cannot be empty");
				message.setStyle("-fx-text-fill: red");
		 }
		 
		 else { 
			 name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
			 int numStaff = Integer.parseInt(StaffnumInput.getText());
			 LocalDate date = DateInput.getValue();
			 boolean check = false;
			 
			 operationID.add(id);
			 operationName.add(name);
			 numOfStaff.add(numStaff);
			 patients.add(patient);
			 doctors.add(doc);
			 scheduleDate.add(date);
			 //add operation information to all of these ArrayList
			 check = operation.addOperation(doc, patient, id, name, date, numStaff);
			 
			 //If cannot add the operation information
			 if(!check){
				message.setText("Please check your inputs!");
				message.setStyle("-fx-text-fill: red");
			 }
			 // If operation information added successfully
			 else {
				 OperationInfo.add(new Operation(id, name, doc, patient, date,0, numStaff));
				 idInput.clear();
				 nameInput.clear();
				 docInput.clear();
				 patientInput.clear();
				 StaffnumInput.clear();
				 DateInput.setValue(null);
				 message.setText("Operation added sucessfully!");
				 message.setStyle("-fx-text-fill: green");
			 }
		 }
	 }
	 
	 /*
	  * public void DeleteButtonClicked(ActionEvent) 
	  * 
	  * This function controls the delete button. 
	  * 
	  * If the user input is invalid, there will be a message tell the user to check the input.
	  * If the operation doesn't exist, there will be a message tell the user the operation doens't exist
	  * If the user input is valid, the operation will be deleted from the database and removed from the list on the screen
	  * 
	  */
	 @FXML
	 public void DeleteButtonClicked(ActionEvent event) throws Exception {
		 String OID = idInput.getText();
		 String name = nameInput.getText();
		 boolean check = false;
		 int i =0;
		 
		 if(OID == null || name == null) {
			message.setText("Input cannot be empty");
			message.setStyle("-fx-text-fill: red");
		 }
		 else {
			 //check if the operation is successfully deleted
			 check = operation.deleteOperation(OID, operationID, operationName, doctors, patients, scheduleDate, numOfStaff, i);
			 //if not -> display a message to inform the user
			 if(!check){
				message.setText("Operation doesn't exist!");
				message.setStyle("-fx-text-fill: red");
			 }
			 else {
				 OperationInfo.remove(i);
				 idInput.clear();
				 nameInput.clear();
				 message.setText("Operation deleted sucessfully!");
				 message.setStyle("-fx-text-fill: green");
			 }
			 
			 //Clear the operation out from the view list in the screen and display the new list without the
			 //deleted operation information 
			 OperationInfo.clear();
			 if (this.operationID.size() > 0) {
				 for(i =0; i< this.operationID.size(); i++) { 
					 OperationInfo.add(new Operation(this.operationID.get(i), this.operationName.get(i), this.doctors.get(i), this.patients.get(i),
							 this.scheduleDate.get(i), 0,this.numOfStaff.get(i)));
				 }
			 }
			 
			 opID.setCellValueFactory(new PropertyValueFactory<Operation,String>("operationID"));

			 opName.setCellValueFactory(new PropertyValueFactory<Operation, String>("operationName"));

			 doc.setCellValueFactory(new PropertyValueFactory<Operation, String>("docid"));
			 
			 patientid.setCellValueFactory(new PropertyValueFactory<Operation, String>("patientid"));

			 numofstaff.setCellValueFactory(new PropertyValueFactory<Operation, Integer>("numofStaff"));
			 
			 date.setCellValueFactory(new PropertyValueFactory<>("Strdate"));
			 
			 view.setItems(OperationInfo);
		 }
		 
	 }
	 
	 /*
	  * public void getUserID(String)
	  * 
	  * This function is used to pass the userID through each screen
	  */
	 
	 public void getUserID(String user) {
		 userID = user;
	}
}
