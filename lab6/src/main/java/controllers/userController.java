package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.rejestracja;
import model.wydarzenia;
import model.wydarzenie;
import model.zapisy;

public class userController implements Initializable {
	


	public MyConnection conn = new MyConnection();

	ResultSet rs = null;

	ObservableList<wydarzenie> list = FXCollections.observableArrayList();
	
	    
	Set<String> ListOfEvent = new TreeSet<String>();
	
	Set<String> ListOfDate = new TreeSet<String>();

	Set<String> ListOfAgend = new TreeSet<String>();
	
	
	public  String loginUser = new LoginController().loginRemember;

	@FXML
	private Button btn_joinEventUser;

	@FXML
	private ComboBox<String> com_choiceNameOfEvents;

	@FXML
	private TextArea txt_insertEventAgenda;

	@FXML
	private TextField txt_displayEventDate;

	@FXML
	private ChoiceBox<String> cho_insertEventMember;

	@FXML
	private ChoiceBox<String> cho_insertEventEating;

	@FXML
	private Button btn_logoutUser;

	public static void infoBox(String infoMessage, String headerText, String title) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText(infoMessage);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.showAndWait();
	}

	@FXML
    void btn_joinEventUser(ActionEvent event) throws IOException{
		
		
		String nameOfEvent = com_choiceNameOfEvents.getValue();
		
		String  login = loginUser;
		
		
		String memberOfEvent = (String) cho_insertEventMember.getValue();
		
		String eatingOfEvent = (String) cho_insertEventEating.getValue();
		
		 

	
				Configuration cfg = new Configuration();
		    	cfg.configure("hibernate.cfg.xml");
		    	Session session = cfg.buildSessionFactory().openSession();
		    	Transaction txn = session.beginTransaction();
		    	zapisy d = new zapisy();
		    	d.setLogin(login);;
		    	d.setNazwa(nameOfEvent);;
		    	d.setUczestnik(memberOfEvent);;
		    	d.setWyzywienie(eatingOfEvent );;
		    
		    	session.save(d);
		    	txn.commit();
		    	session.close();
		    	d.getLogin();
			
	
	}

	@FXML
	void com_choiceNameOfEvents(ActionEvent event) throws IOException {

		String nameOfEvent = com_choiceNameOfEvents.getSelectionModel().getSelectedItem().toString();

		String dateOfEvent;

		String agendaOfEvent;

		if (nameOfEvent.trim().isEmpty()) {

			infoBox("Wybierz wydarzenie !", null, "Failed");

		} else {

			try {
		
				
				String zapytanie = "Select  * from wydarzenia where nazwa = " + "'" + nameOfEvent + "'";

				Statement preparedStatement = conn.MySQLConnection().prepareStatement(zapytanie);

				rs = preparedStatement.executeQuery(zapytanie);

				while (rs.next()) {

					dateOfEvent = rs.getString("termin");
					agendaOfEvent = rs.getString("agenda");

					txt_insertEventAgenda.setText(agendaOfEvent);
					txt_displayEventDate.setText(dateOfEvent);

					
				}
			} catch (SQLException ex) {
				System.out.println(ex);
				
			}
		}
	}
	
	@FXML
	public void btn_logoutUser(ActionEvent actionEvent) throws IOException {

		Stage stage = (Stage) btn_logoutUser.getScene().getWindow();

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
		stage.setScene(new Scene(root, 600, 400));
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Configuration cfg = new Configuration();
    	cfg.configure("hibernate.cfg.xml");
    	Session session = cfg.buildSessionFactory().openSession();
    	Transaction txn = session.beginTransaction();

    	 String[] dane = new String[6];
    	 List<wydarzenia> logowanie = session.createQuery("from wydarzenia" ).list();
    	 dane[0] = logowanie.get(0).getAgenda();
         dane[1] = logowanie.get(0).getNazwa();
    //     dane[2] = logowanie.get(0).getUprawnienia();
    
         
         System.out.println(dane[0]);
         System.out.println(dane[1]);
         System.out.println(dane[2]);
    	 txn.commit();
    	 session.close();

		
	/*	
		try {

			String sql = "Select * from wydarzenia;";

			Statement preparedStatement = conn.MySQLConnection().prepareStatement(sql);
			rs = preparedStatement.executeQuery(sql);

			while (rs.next()) {

				String Names = rs.getString("nazwa");
				String Dates = rs.getString("termin");
				String Agenda = rs.getString("agenda");

				ListOfEvent.add(Names);
				ListOfDate.add(Dates);
				ListOfAgend.add(Agenda);

			}

		} catch (SQLException ex) {
			System.out.println(ex);

		}
		
		
		cho_insertEventEating.setValue("Bez preferencji");
		cho_insertEventEating.getItems().add("Bez preferencji");
		cho_insertEventEating.getItems().add("Wegetaria�skie");
		cho_insertEventEating.getItems().add("Bez glutenu");

		cho_insertEventMember.setValue("S�uchacz");
		cho_insertEventMember.getItems().add("S�uchacz");
		cho_insertEventMember.getItems().add("Autor");
		cho_insertEventMember.getItems().add("Sponsor");
		cho_insertEventMember.getItems().add("Organizator");

		com_choiceNameOfEvents.getItems().addAll(ListOfEvent);
	

	}*/

	}

}


