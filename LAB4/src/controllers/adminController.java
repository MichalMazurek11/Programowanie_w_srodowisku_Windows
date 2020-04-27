package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.mail.FetchProfile.Item;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import helpers.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;
import model.nazwaLogin;

public class adminController implements Initializable {

	public MyConnection conn = new MyConnection();
	public MyConnection conn2 = new MyConnection();
	public MyConnection conn3 = new MyConnection();
	public MyConnection conn4 = new MyConnection();
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	ResultSet rs = null;

	@FXML
	private TextField user_usun;

	@FXML
	private Button btn_delete_user;

	@FXML
	private Button wyloguj;

	@FXML
	private TextField admin_imie;

	@FXML
	private TextField admin_nazwisko;

	@FXML
	private TextField admin_login;

	@FXML
	private PasswordField admin_password;

	@FXML
	private TextField admin_email;

	@FXML
	private ChoiceBox<String> admin_uprawnienia;

	@FXML
	private TableView<User> tableUser;

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
	private Button load;

	@FXML
	private TextField addEventName;

	@FXML
	private DatePicker addEventTermin;

	@FXML
	private TextArea addEventAgenda;

	ResultSet result;

	@FXML
	private PasswordField change_password;

	@FXML
	private PasswordField change_password2;

	@FXML
	private TextField change_login;

	@FXML
	private Button btn_change_password;

	@FXML
	private TableView<nazwaLogin> tabela_prosba;

	@FXML
	private TableColumn<nazwaLogin, String> col_nazwa1;

	@FXML
	private TableColumn<nazwaLogin, String> col_login1;

	@FXML
	private TableView<nazwaLogin> tabela_dodani;

	@FXML
	private TableColumn<nazwaLogin, String> col_nazwa2;

	@FXML
	private TableColumn<nazwaLogin, String> col_login2;

	@FXML
	private TextField podaj_login;

	@FXML
	private ComboBox<String> lista_wydarzen;

	@FXML
	void dodaj_prosbe(ActionEvent event) {
		String jakaNazwa = lista_wydarzen.getSelectionModel().getSelectedItem().toString();
		String prosba = podaj_login.getText();
		int czyZmienilo;

		try {

			String sql = " UPDATE zapisy SET czyzapisano ='tak' where login = " + "'" + prosba + "'";

			Statement preparedStatement = conn.MySQLConnection().createStatement();
			czyZmienilo = preparedStatement.executeUpdate(sql);
			if (czyZmienilo == 1) {
				infoBox("Nie udalo sie  !", null, "Success");
			}

			else {
				infoBox("Tak udalo sie !", null, "Failed");
				
				
				
				
				
			}
		} catch (SQLException ex) {

		}

	}
	ObservableList<nazwaLogin> lista_prosba1;
	ObservableList<nazwaLogin> lista_prosba;
	ArrayList wydarzenia = new ArrayList<Item>();

	@FXML
	void lista_wydarzen(ActionEvent event) throws SQLException {

		String jakaNazwa = lista_wydarzen.getSelectionModel().getSelectedItem().toString();
		// System.out.println(jakaNazwa);
		String jakiTermin;
		String jakaAgenda;

		if (jakaNazwa.trim().isEmpty()) {

			System.out.println("PUSTO");

		} else {
			String zapytanie = "Select  * from zapisy where nazwa = " + "'" + jakaNazwa + "'"+"and czyzapisano = "+"'"+"tak"+"';";
			
			lista_prosba1 = FXCollections.observableArrayList();
			try {
			Statement preparedStatement = conn.MySQLConnection().prepareStatement(zapytanie);
			rs = preparedStatement.executeQuery(zapytanie);
		
			while (rs.next()) {
				lista_prosba1.add(new nazwaLogin(rs.getString("nazwa"), rs.getString("login")));
				
			}
		}catch (SQLException ex) {
			System.out.println(ex);

		}	
	
			try {
				String zapytanie1 = "Select  * from zapisy where nazwa = " + "'" + jakaNazwa + "'";
				lista_prosba = FXCollections.observableArrayList();
				Statement preparedStatement = conn.MySQLConnection().prepareStatement(zapytanie1);
				rs = preparedStatement.executeQuery(zapytanie1);

				while (rs.next()) {
					
					lista_prosba.add(new nazwaLogin(rs.getString("nazwa"), rs.getString("login")));

				}
			} catch (SQLException ex) {
				System.out.println(ex);

			}

			col_nazwa1.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
			col_login1.setCellValueFactory(new PropertyValueFactory<>("login"));
			tabela_prosba.setItems(lista_prosba);
			col_nazwa1.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
			col_login1.setCellValueFactory(new PropertyValueFactory<>("login"));
			tabela_dodani.setItems(lista_prosba1);
			
		}

	}

	@FXML
	void btn_change_password(ActionEvent event) {

		int czyZmienilo = 0;
		String login = change_login.getText();
		String password = change_password.getText();
		String password2 = change_password2.getText();

		String sql = "select password from rejestracja where login =" + "'" + login + "';";
		try {

			Statement preparedStatement = conn.MySQLConnection().prepareStatement(sql);
			resultSet = preparedStatement.executeQuery(sql);
			if (!resultSet.next()) {
				infoBox("NIE MA TAKIEGO UZYTKOWNIKA !", null, "Failed");
			} else {
				if (!(password.equals(password2))) {
					infoBox("ERROR: HAS£A MUSZA BYC IDENATYCZNE !", null, "Failed");
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
	void btn_delete_user(ActionEvent event) {

		int czyUsunelo = 0;
		String login = user_usun.getText();

		String sql = "delete from rejestracja where login =" + "'" + login + "';";
		try {

			Statement preparedStatement2 = conn4.MySQLConnection().createStatement();
			czyUsunelo = preparedStatement2.executeUpdate(sql);
			if (czyUsunelo == 1) {
				infoBox(login + " ZOSTAL USUNIETY !", null, "Success");
			} else {
				infoBox("NIE MA TAKIEGO UZYTKOWNIKA !" + login, null, "Failed");
			}

		} catch (SQLException ex) {
			System.out.println(ex);
		}

	}

	@FXML
	void addEvent(ActionEvent event) {

		String nazwa_wydarzenia = addEventName.getText();
		String agenda = addEventAgenda.getText();
		LocalDate termin = addEventTermin.getValue();

		System.out.println(nazwa_wydarzenia);
		System.out.println(agenda);
		System.out.println(termin);

		try {
			String sql = "SELECT * FROM wydarzenia WHERE nazwa = " + "'" + nazwa_wydarzenia + "'";
			Statement preparedStatement = conn2.MySQLConnection().prepareStatement(sql);

			rs = preparedStatement.executeQuery(sql);

		} catch (SQLException e) {

			infoBox("ERROR: PRZY PROBIE POLACZENIA !", null, "Failed");
		}
		try {
			if (rs != null && !rs.isClosed() && rs.next()) {
				infoBox("NIE MOZNA DODAC TEGO SAMEGO WYDARZENIA !", null, "Failed");
			} else {

				String sql = "INSERT INTO wydarzenia " + "(id_wydarzenia, " + "nazwa, " + "termin, " + "agenda) "
						+ "VALUES " + "(DEFAULT," + "'" + nazwa_wydarzenia + "', " + "'" + termin + "', " + "'" + agenda
						+ "'" + ");";

				try {

					Statement preparedStmt = conn.MySQLConnection().createStatement();
					preparedStmt.executeUpdate(sql);
					System.out.print("Dodales uzytkownika do wydarzenia  : " + nazwa_wydarzenia);

				} catch (SQLException e) {
					System.err.println(e);
				}

			}

		} catch (SQLException ex) {
			System.err.println(ex);
		}

		String sql = "INSERT INTO `wydarzenia`(`id_wydarzenia`, `nazwa`, `termin`, `agenda`) VALUES(DEFAULT," + "'"
				+ nazwa_wydarzenia + "'," + "'" + termin + "'," + "'" + agenda + "')";

		try {
			Statement preparedStmt = conn.MySQLConnection().createStatement();
			preparedStmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.err.println(e);
		}

	}

	ObservableList<User> oblist;

	@FXML
	void load(ActionEvent event) throws IOException {

		try {

			oblist = FXCollections.observableArrayList();
			String sql = "Select * from rejestracja";

			ResultSet rs = conn3.MySQLConnection().createStatement().executeQuery(sql);

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

		// tableUser.setItems(null);
		tableUser.setItems(oblist);
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
		stage.setTitle("Panel rejestracji");
		stage.setScene(new Scene(root, 800, 550));
		stage.show();
	}

	@FXML
	void admin_addUser(ActionEvent event) throws IOException {

		String imie = admin_imie.getText();
		String nazwisko = admin_nazwisko.getText();
		String login = admin_login.getText();
		String password = admin_password.getText();
		String email = admin_email.getText();
		Object uprawnienia = (String) admin_uprawnienia.getValue();

		// System.out.println(uprawnienia);

		try {
			String sql = "SELECT * FROM rejestracja WHERE Login = " + "'" + login + "'";
			Statement preparedStatement = conn2.MySQLConnection().prepareStatement(sql);

			rs = preparedStatement.executeQuery(sql);

		} catch (SQLException e) {

			infoBox("ERROR: PRZY PROBIE POLACZENIA !", null, "Failed");
		}
		if ((admin_imie.getText().trim().isEmpty() | admin_nazwisko.getText().trim().isEmpty()
				| admin_login.getText().trim().isEmpty() | admin_password.getText().trim().isEmpty()
				| admin_email.getText().trim().isEmpty())) {

			infoBox("ERROR: Wype³nij wszystkie pola !", null, "Failed");

			// czy oba hasla sa takie same =
		} else if (login.length() < 5) { // login dluzszy niz 4 znaki

			infoBox("ERROR: LOGIN MUSI BYC DLUZY NIZ 4 ZNAKI !", null, "Failed");

			// sprawdzamy czy uzytkownik taki istneije
		} else
			try {
				if (rs != null && !rs.isClosed() && rs.next()) {
					infoBox("Taki uzytkownik juz istnieje !", null, "Failed");
				} else {

					String Query = "INSERT INTO rejestracja " + "(id_klienta, " + "imie, " + "nazwisko, " + "login, "
							+ "password, " + "email," + "uprawnienia)" +

							"VALUES " + "(DEFAULT," + "'" + imie + "', " + "'" + nazwisko + "', " + "'" + login + "', "
							+ "'" + password + "', " + "'" + email + "'," + "'" + uprawnienia + "');";

					try {

						Statement preparedStmt = conn.MySQLConnection().createStatement();
						preparedStmt.executeUpdate(Query);
						System.out.print("Dodales uzytkownika : " + login);
						// new MyEmail().send(email); //wysylanie maila

					} catch (SQLException e) {
						System.err.println(e);
					}

				}

			} catch (SQLException e) {
				System.err.println("Problem z po³aczeniem2");
			}

	}

	public static void infoBox(String infoMessage, String headerText, String title) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText(infoMessage);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.showAndWait();
	}

	void test() throws IOException {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		admin_uprawnienia.setValue("user");
		admin_uprawnienia.getItems().add("user");
		admin_uprawnienia.getItems().add("admin");

		try {

			oblist = FXCollections.observableArrayList();
			String sql = "Select * from rejestracja";

			ResultSet rs = conn3.MySQLConnection().createStatement().executeQuery(sql);

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

		col_nazwa1.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
		col_login1.setCellValueFactory(new PropertyValueFactory<>("login"));
		tabela_prosba.setItems(lista_prosba);
		
		
		col_nazwa1.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
		col_login1.setCellValueFactory(new PropertyValueFactory<>("login"));
		tabela_dodani.setItems(lista_prosba1);
		
		
		
		// tableUser.setItems(null);

		tableUser.setItems(oblist);

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

		lista_wydarzen.getItems().addAll(wydarzenia);
		
		

	}

}
