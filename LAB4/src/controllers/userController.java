package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.mail.FetchProfile.Item;
import controllers.LoginController;
import helpers.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import model.User;
import model.wydarzenie;

public class userController implements Initializable {

	
	public MyConnection conn = new MyConnection();

	
	 @FXML
	    private Button add_event;

	
	@FXML
	private ComboBox<String> nazwa_wydarzenia;

	@FXML
	private TextArea agenda;

	@FXML
	private TextField termin;

	@FXML
	private ChoiceBox<String> uczestnik;

	@FXML
	private ChoiceBox<String> wyzywienie;

	@FXML
	private Button wyloguj;

	
	public static void infoBox(String infoMessage, String headerText, String title) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText(infoMessage);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.showAndWait();
	}
	
	
	@FXML
    void add_event(ActionEvent event) throws IOException{
		String nazwa = nazwa_wydarzenia.getValue();
		String login = "5";
		Object uczestnik1 = (String) uczestnik.getValue();
		Object wyzywienie1 = (String) wyzywienie.getValue();
	
		System.out.println(uczestnik1);
		System.out.println(wyzywienie1);


		String sql = "INSERT INTO zapisy " + "(id_wydarzenia, " + "nazwa, " + "login, " + "uczestnik, "
			+ "wyzywienie)" +

			"VALUES " + "(DEFAULT," + "'" + nazwa + "', "  + "'" + login + "', "
			+ "'" + uczestnik1 + "', " + "'" + wyzywienie1 + "'" + ");";
		
		try {

			Statement preparedStmt = conn.MySQLConnection().createStatement();
			preparedStmt.executeUpdate(sql);
			System.out.print("Dodales uzytkownika do wydarzenia  : " + nazwa);
			
		} catch (SQLException e) {
			System.err.println(e);
		}
		
		
    }

	@FXML
	void nazwa_wydarzenia(ActionEvent event) {

	
		
		
		
		String jakaNazwa = nazwa_wydarzenia.getSelectionModel().getSelectedItem().toString();
		//System.out.println(jakaNazwa);
		String jakiTermin;
		String jakaAgenda ;
		
		if(jakaNazwa.trim().isEmpty() ) {
			
			System.out.println("PUSTO");
			
		}else {
			
		 	try {
			String zapytanie = "Select  * from wydarzenia where nazwa = "+"'"+jakaNazwa+"'";

			Statement preparedStatement = conn.MySQLConnection().prepareStatement(zapytanie);
			rs = preparedStatement.executeQuery(zapytanie);
			
				while (rs.next()) {
				
				
				 jakiTermin = rs.getString("termin");
				 jakaAgenda = rs.getString("agenda");
			
				
				 agenda.setText(jakaAgenda);
				 termin.setText(jakiTermin);
				}
		 	}catch (SQLException ex) {
					System.out.println(ex);
					
				}
	
			
		 	}
		


	}



	@FXML
	public void wyloguj(ActionEvent actionEvent) throws IOException {
		Stage stage = (Stage) wyloguj.getScene().getWindow();
		stage.close();

		showMain();
	}

	public void showMain() {
		Parent root = null;

		try {
			root = FXMLLoader.load(getClass().getResource("/views/LoginController.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Stage stage = new Stage();
		stage.setTitle("Panel Logowania");
		stage.setScene(new Scene(root, 800, 550));
		stage.show();
	}

	ResultSet rs = null;


	ObservableList<wydarzenie> list = FXCollections.observableArrayList();
	ArrayList wydarzenia = new ArrayList<Item>();
	ArrayList list_termin = new ArrayList<String>();
	ArrayList list_agenda = new ArrayList<String>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {

			String sql = "Select * from wydarzenia;";

			Statement preparedStatement = conn.MySQLConnection().prepareStatement(sql);
			rs = preparedStatement.executeQuery(sql);

			while (rs.next()) {

				String nazwa = rs.getString("nazwa");
				String termin = rs.getString("termin");
				String agenda = rs.getString("agenda");
				wydarzenia.add(nazwa);
				list_termin.add(termin);
				list_agenda.add(agenda);
				System.out.println("Nazwa " + wydarzenia);
				System.out.println("Termin " + list_termin);
				System.out.println("agenda " + list_agenda);

			}

		} catch (SQLException ex) {
			System.out.println(ex);

		}

		wyzywienie.setValue("Bez preferencji");
		wyzywienie.getItems().add("Bez preferencji");
		wyzywienie.getItems().add("Wegetariañskie");
		wyzywienie.getItems().add("Bez glutenu");

		uczestnik.setValue("S³uchacz");
		uczestnik.getItems().add("S³uchacz");
		uczestnik.getItems().add("Autor");
		uczestnik.getItems().add("Sponsor");
		uczestnik.getItems().add("Organizator");
		nazwa_wydarzenia.getItems().addAll(wydarzenia);

	}

}
