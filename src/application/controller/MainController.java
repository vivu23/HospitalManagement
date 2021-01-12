package application.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import javafx.fxml.*;


public class MainController {
	
	@FXML
	public SplitPane mainPane;
	
	 @FXML
	    public void PButtonClicked(ActionEvent event) throws Exception {
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("application/view/Login.fxml"));
			mainPane = loader.load();
			LoginController name = loader.getController();
			name.getUserType("patient");
			Scene scene = new Scene(mainPane);
			window.setScene(scene);
			window.show();
		}
	 @FXML
	    public void DButtonClicked(ActionEvent event) throws Exception {
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("application/view/Login.fxml"));
			mainPane = loader.load();
			LoginController name = loader.getController();
			name.getUserType("doc");
			Scene scene = new Scene(mainPane);
			window.setScene(scene);
			window.show();
		}
	 
	 @FXML
	    public void NButtonClicked(ActionEvent event) throws Exception {
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("application/view/Login.fxml"));
			mainPane = loader.load();
			LoginController name = loader.getController();
			name.getUserType("nurse");
			Scene scene = new Scene(mainPane);
			window.setScene(scene);
			window.show();
		}
}
