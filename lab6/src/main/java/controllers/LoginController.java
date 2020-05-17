package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.mysql.jdbc.PreparedStatement;
import helpers.MyConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.rejestracja;


public class LoginController {
	

	ResultSet result;
	public String tmp;
	public MyConnection conn = new MyConnection();

	public static String loginRemember;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="login_login"
	private TextField login_login; // Value injected by FXMLLoader

	@FXML // fx:id="login_password"
	private PasswordField login_password; // Value injected by FXMLLoader
	Stage dialogStage = new Stage();
	Scene scene;

	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;


	// DO WYSWIETLANIA ALERTOW
	public static void infoBox(String infoMessage, String headerText, String title) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText(infoMessage);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.showAndWait();
	}
	
	@FXML
	private TextField login_text;

	@FXML
	private CheckBox checkPassword;

	// SPRAWDZANIE HASLA CHECKBOX
	@FXML
	void showPassword(ActionEvent event) throws IOException {

		login_text.managedProperty().bind(checkPassword.selectedProperty());
		login_text.visibleProperty().bind(checkPassword.selectedProperty());
		login_password.managedProperty().bind(checkPassword.selectedProperty().not());
		login_password.visibleProperty().bind(checkPassword.selectedProperty().not());
		login_text.textProperty().bindBidirectional(login_password.textProperty());

	}


	@FXML
	void log_in(ActionEvent event) throws IOException {

		 String Login = login_login.getText().toString();
		String Password = login_password.getText().toString();

	//	String sql = "SELECT * FROM rejestracja WHERE login = " + "'" + Login + "'" + " and password =  " + "'"+ Password + "';";
		Configuration cfg = new Configuration();
    	cfg.configure("hibernate.cfg.xml");
    	Session session = cfg.buildSessionFactory().openSession();
    	Transaction txn = session.beginTransaction();

    	 String[] dane = new String[6];
    	 List<rejestracja> logowanie = session.createQuery("from rejestracja where login =" +"'"+Login+"'" + " and password =  " + "'"+ Password +"'" ).list();
    	 dane[0] = logowanie.get(0).getLogin();
         dane[1] = logowanie.get(0).getPassword();
         dane[2] = logowanie.get(0).getUprawnienia();
    
         
         System.out.println(dane[0]);
         System.out.println(dane[1]);
         System.out.println(dane[2]);
    	 txn.commit();
    	 session.close();

/*		try {

		
			Statement preparedStatement = conn.MySQLConnection().prepareStatement(sql);
			resultSet = preparedStatement.executeQuery(sql);
			if (!resultSet.next()) {
				infoBox("Please enter correct Login and Password", null, "Failed");
			} else {
				System.out.println("Login Successfull");
				String zalogowany = Login;
				String	checkPermision = "";

				String sql1 = "select uprawnienia from rejestracja where login = " + "'"  + zalogowany + "'";

				preparedStatement.close();
				conn.MySQLConnection().close();

			
				try {

					Statement preparedStatement1 = conn.MySQLConnection().prepareStatement(sql1);
					result = preparedStatement1.executeQuery(sql1);
					while (result.next()) {
					checkPermision = result.getString("uprawnienia");

					}
					
				} catch (SQLException e) {

					infoBox("ERROR: PRZY PROBIE POLACZENIA !", null, "Failed");
				}
				// sprawdzamy uprawnienia here
*/				if (logowanie.get(0).getUprawnienia().equals("user")) {
				
								
					loginRemember =  dane[0];
		
					
					Node node = (Node) event.getSource();
					dialogStage = (Stage) node.getScene().getWindow();
					dialogStage.close();
					scene = new Scene(FXMLLoader.load(getClass().getResource("/views/users.fxml")));
					dialogStage.setScene(scene);
					dialogStage.show();

				
					
				} else {

					Node node = (Node) event.getSource();
					dialogStage = (Stage) node.getScene().getWindow();
					dialogStage.close();
					scene = new Scene(FXMLLoader.load(getClass().getResource("/views/admin.fxml")));
					dialogStage.setScene(scene);
					dialogStage.show();

				

				}


	}

	@FXML
	void login_signUp(ActionEvent event) throws IOException {

		Parent root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/RegisterController.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();

		stage.setScene(new Scene(root));

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert login_login != null : "fx:id=\"login_login\" was not injected: check your FXML file 'LoginController.fxml'.";
		assert login_password != null : "fx:id=\"login_password\" was not injected: check your FXML file 'LoginController.fxml'.";
		
	}



	
	
}
