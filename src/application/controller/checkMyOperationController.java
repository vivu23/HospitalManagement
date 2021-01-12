package application.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import application.model.Operation;
import application.model.Patient;
import application.model.UserLogin;
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

public class checkMyOperationController {
	
	private String DoctorID;
		
	private Operation operation = new Operation();
	
	private ArrayList<String> patients = new ArrayList<String>();
	
	private ArrayList<String> OperationID = new ArrayList<String>();
	
	private ArrayList<String> OperationName = new ArrayList<String>();
	
	private ObservableList<Operation> OperationInfo = FXCollections.observableArrayList();	 

	private ObservableList<Patient> PatientInfo = FXCollections.observableArrayList();	 

	@FXML
	private Label time;
	
	@FXML
	private TableView<Operation> view;
	
	@FXML
	private TableColumn <Operation, String> patientName;
	
	@FXML
	private TableColumn <Operation, String> OpID;
	
	@FXML
	private TableColumn <Operation, String> OpName;
	
	@FXML 
	private SplitPane mainPane;
	
	
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
		 operation.checkMyOperation( DoctorID, patients, OperationID, OperationName);
		 if (this.patients.size() > 0) {
			 for(int i =0; i< this.patients.size(); i++) { 
				 OperationInfo.add(new Operation( OperationID.get(i),OperationName.get(i), null, patients.get(i), null, 0,0));
			 }
		 }
		
		 OpID.setCellValueFactory(new PropertyValueFactory<Operation,String>("operationID"));
		 OpName.setCellValueFactory(new PropertyValueFactory<Operation,String>("operationName"));
		 patientName.setCellValueFactory(new PropertyValueFactory<Operation, String>("patientid"));
		 view.setItems(OperationInfo);
		}
	 
	 @FXML
	    public void returnButtonClicked(ActionEvent event) throws Exception {
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("application/view/DoctorMain.fxml"));
			mainPane = loader.load();
			DoctorMainController id = loader.getController();
			id.getUserID(DoctorID);
			id.getUserType("doc");
			id.setName(DoctorID);
			Scene scene = new Scene(mainPane);
			window.setScene(scene);
			window.show();
		}
	 
	 public void setUserID(String user) {
			this.DoctorID = user;
			System.out.println(DoctorID);
			System.out.println(user);

	 }
}