package application.controller;

import application.model.Patient;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class checkPatientController {
	
	private Patient patient = new Patient();
	
	private ArrayList<String> id = new ArrayList<String>();
	
	private ArrayList<String> name = new ArrayList<String>();
	
	private ArrayList<String> diagnoses = new ArrayList<String>();
	
	private ObservableList<Patient> PatientInfo = FXCollections.observableArrayList();	 
	
	private String userid;
	
	@FXML
	private SplitPane mainPane;
	
	@FXML
	private Pane mainPane1;
	
	@FXML
	private Label time;
	
	@FXML
	private TableView<Patient> view;
	@FXML
	private TableColumn <Patient, String> patientID;
	
	@FXML
	private TableColumn <Patient, String> patientName;
	
	@FXML
	private TableColumn <Patient, String> diagnose;
	
	@FXML
	private TextField idInput;
	
	@FXML
	private TextField nameInput;
	
	@FXML
	private TextField diagnoseInput;
	
	@FXML
	private Label message;
	
	@FXML
	Stage window = new Stage();
	
	 @FXML
	    public void returnButtonClicked(ActionEvent event) throws Exception {
	    	window = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("application/view/NurseMain.fxml"));
			mainPane = loader.load();
			NurseMainController id = loader.getController();
			id.getUserID(userid);
			id.getUserType("nurse");
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
		 patient.showPatient(this.id, this.name, this.diagnoses);
		 
		 if (this.id.size() > 0) {
			 for(int i =0; i< this.id.size(); i++) { 
				 PatientInfo.add(new Patient(this.id.get(i), this.name.get(i), this.diagnoses.get(i)));
			 }
		 }
		 
		 patientID.setCellValueFactory(new PropertyValueFactory<Patient,String>("id"));

		 patientName.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));

		 diagnose.setCellValueFactory(new PropertyValueFactory<Patient, String>("diagnose"));

		 view.setItems(PatientInfo);
	}
	 
	 @FXML
	 public void AddButtonClicked(ActionEvent event) throws Exception {
		 String id = idInput.getText();
		 String name = nameInput.getText();
		 String diagnose = diagnoseInput.getText();
		 boolean check = false;
		 
		 if(id.trim().length() == 0 || name.trim().length() == 0 || diagnose.trim().length() == 0) {
			message.setText("Input cannot be empty");
			message.setStyle("-fx-text-fill: red");
		 }
		 else {
			 this.id.add(id);
			 this.name.add(name);
			 this.diagnoses.add(diagnose);
			 check = patient.addPatient(id, name, diagnose);
			 System.out.println(check);
			 if(!check){
				message.setText("Patient already existed!");
				message.setStyle("-fx-text-fill: #CD5C5C");
			 }
			 else {
				 PatientInfo.add(new Patient(id, name, diagnose));
				 idInput.clear();
				 nameInput.clear();
				 diagnoseInput.clear();
				 message.setText("Patient added sucessfully!");
				 message.setStyle("-fx-text-fill: green");
			 }
		 }
	 }
	 
	 @FXML
	 public void DeleteButtonClicked(ActionEvent event) throws Exception {
		 String Pid = idInput.getText();
		 String name = nameInput.getText();
		 boolean check = false;
		 int i =0;
		 
		 if(id == null || name == null) {
			message.setText("Input cannot be empty");
			message.setStyle("-fx-text-fill: red");
		 }
		 else {
			 
			 check = patient.deletePatient(this.id, this.name, this.diagnoses, Pid, i);
			 if(!check){
				message.setText("Patient doesn't exist!");
				message.setStyle("-fx-text-fill: red");
			 }
			 else {
				 PatientInfo.remove(i);
				 idInput.clear();
				 nameInput.clear();
				 message.setText("Patient deleted sucessfully!");
				 message.setStyle("-fx-text-fill: green");
			 }
			 
			 PatientInfo.clear();

			 if (this.id.size() > 0) {
				 for(i =0; i< this.id.size(); i++) { 
					 PatientInfo.add(new Patient(this.id.get(i), this.name.get(i), this.diagnoses.get(i)));
				 }
			 }
			 
			 patientID.setCellValueFactory(new PropertyValueFactory<Patient,String>("id"));

			 patientName.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));

			 diagnose.setCellValueFactory(new PropertyValueFactory<Patient, String>("diagnose"));

			 view.setItems(PatientInfo);
		 }
	 }
	 
	 public void getUserID(String user) {
		  userid = user;
	}
	
}
