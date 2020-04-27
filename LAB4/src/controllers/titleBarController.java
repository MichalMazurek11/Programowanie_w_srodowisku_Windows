package controllers;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class titleBarController {





    @FXML
    void welcome(ActionEvent event) throws IOException {
    	
    	
    	
    	Parent root = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/LoginController.fxml"));
    	Node node = (Node) event.getSource();
    	Stage stage = (Stage) node.getScene().getWindow();
    	
    	stage.setScene(new Scene(root));
    	
    	
    	


    }

	
}
