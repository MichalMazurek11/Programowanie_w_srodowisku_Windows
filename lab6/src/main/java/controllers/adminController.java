package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
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

import com.mysql.jdbc.PreparedStatement;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.User;
import model.nazwaLogin;
import model.rejestracja;
import model.wydarzenia;

public class adminController implements Initializable {

	public MyConnection conn = new MyConnection();

	public MyConnection conn2 = new MyConnection();

	PreparedStatement preparedStatement = null;

	ResultSet resultSet = null;

	ResultSet rs = null;
	
	ResultSet rs1 = null;

	ResultSet result;

	Set<String> wydarzenia = new TreeSet<String>();

	ObservableList<User> oblist;
	ObservableList<nazwaLogin> listOfAsking;
	ObservableList<nazwaLogin> listOfJoined;

	@FXML
	private Button btn_addEvent;

	@FXML
	private TextField txt_deleteLoginUser;

	@FXML
	private Button btn_deleteUser;

	@FXML
	private Button btn_logoutUser;

	@FXML
	private TextField txt_addNameUser;

	@FXML
	private TextField txt_addSurnameUser;

	@FXML
	private TextField txt_addLoginUser;

	@FXML
	private PasswordField pas_addPasswordUser;

	@FXML
	private TextField txt_addEmailUser;

	@FXML
	private ChoiceBox<String> cho_addPermisionUser;

	@FXML
	private TableView<User> table_allUsers;

	@FXML
	private TableColumn<User, Integer> col_id;

	@FXML
	private TableColumn<User, String> col_imie;

	@FXML
	private TableColumn<User, String> col_nazwisko;

	@FXML
	private TableColumn<User, String> col_login;

	@FXML
	private TableColumn<User, String> col_password;

	@FXML
	private TableColumn<User, String> col_email;

	@FXML
	private TableColumn<User, String> col_uprawnienia;

	@FXML
	private TableColumn<User, Timestamp> col_data_rejestracji;

	@FXML
	private Button btn_loadAllUsers;

	@FXML
	private TextField txt_addEventName;

	@FXML
	private DatePicker dat_addEventDate;

	@FXML
	private TextArea txt_addEventAgenda;

	@FXML
	private PasswordField pas_changePassword;

	@FXML
	private PasswordField pas_changePassword2;

	@FXML
	private TextField txt_changeLoginPassword;

	@FXML
	private Button btn_changePassword;

	@FXML
	private TableView<nazwaLogin> tbl_askJoin;

	@FXML
	private TableColumn<nazwaLogin, String> col_askLogin;

	@FXML
	private TableColumn<nazwaLogin, String> col_askMember;

	@FXML
	private TableView<nazwaLogin> tbl_joinMember;

	@FXML
	private TableColumn<nazwaLogin, String> col_joinLogin;

	@FXML
	private TableColumn<nazwaLogin, String> col_joinMember;

	@FXML
	private TextField txt_insertAskLogin;

	@FXML
	private ComboBox<String> com_listOfExistingEvents;

	@FXML
	private Button btn_addAsk;

	@FXML
	void btn_addAsk(ActionEvent event) {
		
	
		String nameOfEvent = com_listOfExistingEvents.getSelectionModel().getSelectedItem().toString();
	
		String login = txt_insertAskLogin.getText();
		listOfJoined = FXCollections.observableArrayList();
		listOfAsking = FXCollections.observableArrayList();
		int czyZmienilo;
	
		try {
			String yes1= "\"tak\"";
			String sql = " UPDATE zapisy SET czyzapisano =" + yes1 +" where login = " + "'" + login + "'" + " and nazwa = "+"'"+ nameOfEvent +"'";
			Statement preparedStatement = conn.MySQLConnection().createStatement();
			czyZmienilo = preparedStatement.executeUpdate(sql);
			if (czyZmienilo == 1) {
			
					String yes2= "\"tak\"";
					String zapytanie2 = " select * from zapisy where nazwa = " + "'" + nameOfEvent  + "'"+" and czyzapisano = "+yes2;
					
					Statement preparedStatement1 = conn.MySQLConnection().prepareStatement(zapytanie2);
					rs = preparedStatement1.executeQuery(zapytanie2);
					while (rs.next()) {
						
						listOfJoined.add(new nazwaLogin(rs.getString("login"), rs.getString("uczestnik")));
						
						}
					
					
					String no= "\"nie\"";
					String zapytanie3 = " select * from zapisy where nazwa = " + "'" + nameOfEvent  + "'"+" and czyzapisano = "+no;
					
					Statement preparedStatement2 = conn.MySQLConnection().prepareStatement(zapytanie3);
					rs1 = preparedStatement2.executeQuery(zapytanie3);
					while (rs1.next()) {
						
						listOfAsking.add(new nazwaLogin(rs1.getString("login"), rs1.getString("uczestnik")));
						
						}
	
			}else {
				infoBox("Eror, nie udalo sie zmienic", null, "Failed");
			}
		}catch(SQLException error) {
			System.out.println(error);
		}
		col_askLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
		col_askMember.setCellValueFactory(new PropertyValueFactory<>("uczestnik"));
		tbl_askJoin.setItems(listOfAsking);
		
		
		col_joinLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
		col_joinMember.setCellValueFactory(new PropertyValueFactory<>("uczestnik"));
		tbl_joinMember.setItems(listOfJoined);
		
	}

	@FXML
	void com_listOfExistingEvents(ActionEvent event) throws SQLException {

		String nameOfEvent = com_listOfExistingEvents.getSelectionModel().getSelectedItem().toString();
		listOfAsking = FXCollections.observableArrayList();
		listOfJoined = FXCollections.observableArrayList();

		

		if (nameOfEvent.trim().isEmpty() || nameOfEvent == null ) {

			infoBox("Wybierz wydarzenie!", null, "Failed");

		} else {

			try {
				String zapytanie1 = "Select login,uczestnik from zapisy where nazwa = " + "'" + nameOfEvent 
						+"'"+ " and czyzapisano =" + "\'nie\'" ;

				ResultSet rs = conn.MySQLConnection().createStatement().executeQuery(zapytanie1);
				while (rs.next()) {

					listOfAsking.add(new nazwaLogin(rs.getString("login"), rs.getString("uczestnik")));

				}
		
			String zapytanie2 = " select login,uczestnik from zapisy where nazwa = " + "'"+ nameOfEvent + "'"+
			"and czyzapisano = "+ "\'tak\'";

				Statement preparedStatement = conn2.MySQLConnection().prepareStatement(zapytanie2);
				resultSet = preparedStatement.executeQuery(zapytanie2);
				

				while (resultSet.next()) {

					listOfJoined.add(new nazwaLogin(resultSet.getString("login"), resultSet.getString("uczestnik")));

				}

			} catch (SQLException ex) {
				System.out.println(ex);
			}
			col_askLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
			col_askMember.setCellValueFactory(new PropertyValueFactory<>("uczestnik"));
			tbl_askJoin.setItems(listOfAsking);

			col_joinLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
			col_joinMember.setCellValueFactory(new PropertyValueFactory<>("uczestnik"));
			tbl_joinMember.setItems(listOfJoined);

		}

	}

	@FXML
	void btn_changePassword(ActionEvent event) {

		int czyZmienilo = 0;
		String login = txt_changeLoginPassword.getText();
		String password = pas_changePassword.getText();
		String password2 = pas_changePassword2.getText();

		String sql = "select password from rejestracja where login =" + "'" + login + "';";
		try {

			Statement preparedStatement = conn.MySQLConnection().prepareStatement(sql);
			resultSet = preparedStatement.executeQuery(sql);
			if (!resultSet.next()) {
				infoBox("NIE MA TAKIEGO UZYTKOWNIKA !", null, "Failed");
			} else {
				if (!(password.equals(password2))) {
					infoBox("ERROR: HAS�A MUSZA BYC IDENATYCZNE !", null, "Failed");
				} else {
					String update = "update rejestracja set password = " + "'" + password + "'" + "where login = " + "'"
							+ login + "'";

					Statement preparedStatement2 = conn.MySQLConnection().createStatement();
					czyZmienilo = preparedStatement2.executeUpdate(update);
					if (czyZmienilo == 1) {
						infoBox("HASLO ZOSTALO ZMIENIONE !", null, "Success");
					} else {
						infoBox("NIE UDALO SIE ZMIENIC HASLA, SPROBUJ ONE MORE TIME !" + login, null, "Failed");
					}

				}

			}
		} catch (SQLException ex) {
		}

	}

	@FXML
	void btn_deleteUser(ActionEvent event) throws SQLException {

		int czyUsunelo = 0;
		String loginWithDelete = txt_deleteLoginUser.getText();

		String sql = "delete from rejestracja where login =" + "'" + loginWithDelete + "';";
		try {

			Statement preparedStatement2 = conn.MySQLConnection().createStatement();
			czyUsunelo = preparedStatement2.executeUpdate(sql);
			if (czyUsunelo == 1) {
				infoBox(loginWithDelete + " ZOSTAL USUNIETY !", null, "Success");
			} else {
				infoBox("NIE MA TAKIEGO UZYTKOWNIKA !" + loginWithDelete, null, "Failed");
			}

		} catch (SQLException ex) {
			System.out.println(ex);
		}
		conn.MySQLConnection().close();

	}

	@FXML
	void btn_addEvent(ActionEvent event) throws SQLException {
		
		String nazwa_wydarzenia = txt_addEventName.getText();

		String agenda = txt_addEventAgenda.getText();

		LocalDate termin = dat_addEventDate.getValue();
		
		 Date date1 = Date.from(termin.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		/*try {
			String sql = "SELECT * FROM wydarzenia WHERE nazwa = " + "'" + nazwa_wydarzenia + "'";

			Statement preparedStatement1 = conn.MySQLConnection().prepareStatement(sql);

			rs = preparedStatement1.executeQuery(sql);

		} catch (SQLException e) {

			infoBox("ERROR: PRZY PROBIE POLACZENIA !", null, "Failed");

		}*/

	Configuration cfg = new Configuration();
	cfg.configure("hibernate.cfg.xml");
	Session session = cfg.buildSessionFactory().openSession();
	Transaction txn = session.beginTransaction();

	String[] dane = new String[6];
	
	String hql = "from wydarzenia where nazwa =" +"'"+nazwa_wydarzenia+"'";
	Query query = session.createQuery(hql);
	List<wydarzenia> events = query.list();
	wydarzenia d = new wydarzenia();
   //List<wydarzenia> events = session.createQuery("from wydarzenia where nazwa =" +"'"+nazwa_wydarzenia+"'" ).list();
	 
	// dane[0] = d.getNazwa(); 
	 System.out.println(dane[0]);
	 System.out.println(dane[1]);
	 System.out.println(dane[2]);

	    	d.setAgenda(agenda);
	    	d.setNazwa(nazwa_wydarzenia);
	    //	d.setTermin(date1);
	    	session.save(d);
	    	txn.commit();
	    	session.close();
	
	}

	@FXML
	void btn_loadAllUsers(ActionEvent event) throws SQLException {

		try {

			oblist = FXCollections.observableArrayList();
			String sql = "Select * from rejestracja";

			ResultSet rs = conn.MySQLConnection().createStatement().executeQuery(sql);

		/*	while (rs.next()) {
				oblist.add(new User(rs.getInt("id_klienta"), rs.getString("imie"), rs.getString("nazwisko"),
						rs.getString("login"), rs.getString("password"), rs.getString("email"),
						rs.getString("uprawnienia"), rs.getTimestamp("data_rejestracji")));
			}
*/
		} catch (SQLException ex) {
			System.out.println(ex);

		}

		col_id.setCellValueFactory(new PropertyValueFactory<>("id_klienta"));
		col_imie.setCellValueFactory(new PropertyValueFactory<>("imie"));
		col_nazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
		col_login.setCellValueFactory(new PropertyValueFactory<>("login"));
		col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
		col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
		col_uprawnienia.setCellValueFactory(new PropertyValueFactory<>("uprawnienia"));
		col_data_rejestracji.setCellValueFactory(new PropertyValueFactory<>("data_rejestracji"));

		table_allUsers.setItems(oblist);
		conn.MySQLConnection().close();

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
		stage.setTitle("Panel rejestracji");
		stage.setScene(new Scene(root, 600, 400));
		stage.show();
	}

	@FXML
	void btn_addUsers(ActionEvent event) throws SQLException {

		String imie = txt_addNameUser.getText();

		String nazwisko = txt_addSurnameUser.getText();

		String login = txt_addLoginUser.getText();

		String password = pas_addPasswordUser.getText();

		String email = txt_addEmailUser.getText();

		Object uprawnienia = (String) cho_addPermisionUser.getValue();

		try {
			String sql = "SELECT * FROM rejestracja WHERE Login = " + "'" + login + "'";

			Statement preparedStatement = conn.MySQLConnection().prepareStatement(sql);

			rs = preparedStatement.executeQuery(sql);

		} catch (SQLException e) {

			infoBox("ERROR: PRZY PROBIE POLACZENIA !", null, "Failed");
		}
		if ((txt_addNameUser.getText().trim().isEmpty() | txt_addSurnameUser.getText().trim().isEmpty()
				| txt_addLoginUser.getText().trim().isEmpty() | pas_addPasswordUser.getText().trim().isEmpty()
				| txt_addEmailUser.getText().trim().isEmpty())) {

			infoBox("ERROR: Wype�nij wszystkie pola !", null, "Failed");

			// login dluzszy niz 4 znaki
		} else if (login.length() < 5) {

			infoBox("ERROR: LOGIN MUSI BYC DLUZY NIZ 4 ZNAKI !", null, "Failed");

			// sprawdzamy czy uzytkownik taki istneije
		} else
			try {
				if (rs != null && !rs.isClosed() && rs.next()) {

					infoBox("Taki uzytkownik juz istnieje !", null, "Failed");

				} else {

					String Query = "INSERT INTO rejestracja " + "(id_klienta, " + "imie, " + "nazwisko, " + "login, "
							+ "password, " + "email," + "uprawnienia)" + "VALUES " + "(DEFAULT," + "'" + imie + "', "
							+ "'" + nazwisko + "', " + "'" + login + "', " + "'" + password + "', " + "'" + email + "',"
							+ "'" + uprawnienia + "');";

					try {

						Statement preparedStmt = conn.MySQLConnection().createStatement();

						preparedStmt.executeUpdate(Query);

						System.out.print("Dodales uzytkownika : " + login);

					} catch (SQLException e) {

						System.err.println(e);
					}

				}

			} catch (SQLException e) {

				System.err.println("Problem z po�aczeniem2");
			}
		conn.MySQLConnection().close();

	}

	public static void infoBox(String infoMessage, String headerText, String title) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText(infoMessage);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cho_addPermisionUser.setValue("user");
		cho_addPermisionUser.getItems().add("user");
		cho_addPermisionUser.getItems().add("admin");
		
		
/*		col_id.setCellValueFactory(new PropertyValueFactory<table_allUsers,Integer>("id_klienta")); // here id is a variable name which is define in pojo.
		  col_imie.setCellValueFactory(new PropertyValueFactory<PoJo,String>("imie"));

		    data  =  FXCollections.observableArrayList();        
		    SessionFactory sf = new Configuration().configure().buildSessionFactory();
		    Session sess  =sf.openSession();        
		    Query qee = sess.createQuery("from PoJoName");
		    Iterator ite  =qee.iterate();
		    while(ite.hasNext())
		    {
		        PoJoName obj = (PoJoName)ite.next();    

		        data.add(obj);           
		    }        
		    table.setItems(data);
		
		*/
		
		
		
		
		
		
		
		

/*		try {

			oblist = FXCollections.observableArrayList();
			String sql = "Select * from rejestracja";

			ResultSet rs = conn.MySQLConnection().createStatement().executeQuery(sql);

			while (rs.next()) {
				oblist.add(new User(rs.getInt("id_klienta"), rs.getString("imie"), rs.getString("nazwisko"),
						rs.getString("login"), rs.getString("password"), rs.getString("email"),
						rs.getString("uprawnienia"), rs.getTimestamp("data_rejestracji").toLocalDateTime()));
			}

		} catch (SQLException ex) {
			System.out.println(ex);

		}

		col_id.setCellValueFactory(new PropertyValueFactory<>("id_klienta"));
		col_imie.setCellValueFactory(new PropertyValueFactory<>("imie"));
		col_nazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
		col_login.setCellValueFactory(new PropertyValueFactory<>("login"));
		col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
		col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
		col_uprawnienia.setCellValueFactory(new PropertyValueFactory<>("uprawnienia"));
		col_data_rejestracji.setCellValueFactory(new PropertyValueFactory<>("data_rejestracji"));

		col_joinLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
		col_joinMember.setCellValueFactory(new PropertyValueFactory<>("uczestnik"));
		tbl_askJoin.setItems(listOfJoined);

		col_askLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
		col_askMember.setCellValueFactory(new PropertyValueFactory<>("uczestnik"));
		tbl_joinMember.setItems(listOfAsking);

		table_allUsers.setItems(oblist);

		String sql = "Select * from wydarzenia;";
		try {
			Statement preparedStatement = conn.MySQLConnection().prepareStatement(sql);
			rs = preparedStatement.executeQuery(sql);

			while (rs.next()) {
				String nazwa = rs.getString("nazwa");
				wydarzenia.add(nazwa);
				System.out.println("Nazwa " + wydarzenia);
			}
		} catch (SQLException ex) {
			System.out.println(ex);

		}
		com_listOfExistingEvents.getItems().addAll(wydarzenia);*/
	}

}
