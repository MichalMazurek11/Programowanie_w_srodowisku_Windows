package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.PreparedStatement;

import helpers.MyConnection;
import helpers.MyEmail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RegisterController {

	public MyConnection conn = new MyConnection();
	public MyConnection conn2 = new MyConnection();

	@FXML
	private TextField r_imie;

	@FXML
	private TextField r_nazwisko;

	@FXML
	private TextField r_login;

	@FXML
	private PasswordField r_password;

	@FXML
	private TextField r_email;

	@FXML
	private PasswordField r_password2;

	@FXML
	private CheckBox checkPassword;

	@FXML
	private CheckBox checkPassword2;

	@FXML
	private TextField pass_text;

	@FXML
	private TextField pass_text2;

	@FXML
	private PasswordField pass_hidden;

	@FXML
	void showPassword(ActionEvent event) throws IOException {
		if (checkPassword.isSelected()) {
			pass_text.setText(r_password.getText());
			pass_text.setVisible(true);
			r_password.setVisible(false);
			return;
		}
		r_password.setText(pass_text.getText());
		r_password.setVisible(true);
		pass_text.setVisible(false);
	}

	@FXML
	void showPassword2(ActionEvent event) throws IOException {

		if (checkPassword2.isSelected()) {
			pass_text2.setText(r_password2.getText());
			pass_text2.setVisible(true);
			r_password2.setVisible(false);
			return;
		}
		r_password2.setText(pass_text2.getText());
		r_password2.setVisible(true);
		pass_text2.setVisible(false);

	}

	@FXML
	void r_back(ActionEvent event) throws IOException {

		try {

			Parent root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/LoginController.fxml"));

			Node node = (Node) event.getSource();

			Stage stage = (Stage) node.getScene().getWindow();

			stage.setScene(new Scene(root));

		} catch (Exception e) {
			// System.err.println("Problem z po³aczeniem");
		}

	}

	ResultSet rs;

	@FXML
	void rejestruj(ActionEvent event) throws IOException {
		// pobieram dane z pol
		String imie = r_imie.getText();
		String nazwisko = r_nazwisko.getText();
		String login = r_login.getText();
		String password = r_password.getText();
		String password2 = r_password2.getText();
		String email = r_email.getText();

		// DO SPRAWDZENIA CZY TAKI UZYTKOWNIK JUZ ISTNIEJE
		try {
			String sql = "SELECT * FROM rejestracja WHERE Login = " + "'" + login + "'";
			Statement preparedStatement = conn2.MySQLConnection().prepareStatement(sql);

			rs = preparedStatement.executeQuery(sql);

		} catch (SQLException e) {

			infoBox("ERROR: PRZY PROBIE POLACZENIA !", null, "Failed");
		}

		// ZADANE POLE NIE MOZE BYC PUSTE
		if ((r_imie.getText().trim().isEmpty() | r_nazwisko.getText().trim().isEmpty()
				| r_login.getText().trim().isEmpty() | r_password.getText().trim().isEmpty()
				| r_password2.getText().trim().isEmpty() | r_email.getText().trim().isEmpty())) {

			infoBox("ERROR: Wype³nij wszystkie pola !", null, "Failed");

			// czy oba hasla sa takie same =
		} else if (!(password.equals(password2))) { // hasla musza byc takie same

			infoBox("ERROR: HAS£A MUSZA BYC IDENATYCZNE !", null, "Failed");
			// czy login ma 5 znakow
		} else if (login.length() < 5) { // login dluzszy niz 4 znaki

			infoBox("ERROR: LOGIN MUSI BYC DLUZY NIZ 4 ZNAKI !", null, "Failed");

			// sprawdzamy czy uzytkownik taki istneije
		} else
			try {
				if (rs != null && !rs.isClosed() && rs.next()) {
					infoBox("Taki uzytkownik juz istnieje !", null, "Failed");
				} else {

					String Query = "INSERT INTO rejestracja " + "(id_klienta, " + "imie, " + "nazwisko, " + "login, "
							+ "password, " + "email)" +

							"VALUES " + "(DEFAULT," + "'" + imie + "', " + "'" + nazwisko + "', " + "'" + login + "', "
							+ "'" + password + "', " + "'" + email + "'" + ");";

					System.out.println(Query);
					System.out.println(login);
					System.out.println(password);
					try {

						Statement preparedStmt = conn.MySQLConnection().createStatement();
						preparedStmt.executeUpdate(Query);
						 new MyEmail().send(email); //wysylanie maila

						Parent root = (AnchorPane) FXMLLoader
								.load(getClass().getResource("/views/LoginController.fxml"));
						Node node = (Node) event.getSource();
						Stage stage = (Stage) node.getScene().getWindow();
						stage.setScene(new Scene(root));

					} catch (SQLException e) {
						System.err.println("Problem z po³aczeniem");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	// do wyswietlania bledow/powiadomien
	public static void infoBox(String infoMessage, String headerText, String title) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText(infoMessage);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.showAndWait();
	}

	@FXML
	void initialize() {
		assert r_imie != null : "fx:id=\"r_imie\" was not injected: check your FXML file 'RegisterController.fxml'.";
		assert r_nazwisko != null : "fx:id=\"r_nazwisko\" was not injected: check your FXML file 'RegisterController.fxml'.";
		assert r_login != null : "fx:id=\"r_login\" was not injected: check your FXML file 'RegisterController.fxml'.";
		assert r_password != null : "fx:id=\"r_password\" was not injected: check your FXML file 'RegisterController.fxml'.";
		assert r_email != null : "fx:id=\"r_email\" was not injected: check your FXML file 'RegisterController.fxml'.";
		assert r_password2 != null : "fx:id=\"r_password2\" was not injected: check your FXML file 'RegisterController.fxml'.";
		assert checkPassword != null : "fx:id=\"checkPassword\" was not injected: check your FXML file 'RegisterController.fxml'.";
		assert checkPassword2 != null : "fx:id=\"checkPassword2\" was not injected: check your FXML file 'RegisterController.fxml'.";

	}

}
