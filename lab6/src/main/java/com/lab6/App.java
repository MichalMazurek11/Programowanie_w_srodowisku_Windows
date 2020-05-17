package com.lab6;



import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;
import model.rejestracja;

/**
 * Hello world!
 *
 */
public class App extends Application
{
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/title_bar.fxml"));
			Scene scene = new Scene(root,600,400);
			primaryStage.setScene(scene);
			primaryStage.show();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
    public static void main( String[] args )
    {
    	
    	Configuration cfg = new Configuration();
    	cfg.configure("hibernate.cfg.xml");
    	Session session = cfg.buildSessionFactory().openSession();
    	Transaction txn = session.beginTransaction();
    /*	String hql = "FROM rejestracja";
    	Query query = session.createQuery(hql);
    	List results = query.list();*/
    	String[] dane = new String[6];
/*    	 List<rejestracja> logowanie = session.createQuery("from rejestracja").list();
    	 dane[0] = "zalogowany";
         dane[1] = logowanie.get(0).getUprawnienia();
         dane[2] = logowanie.get(0).getImie();
         dane[3] = logowanie.get(0).getNazwisko();
         dane[4] = logowanie.get(0).getData_rejestracji().toString();
         */
         System.out.println("\n"+dane[1]);
    	txn.commit();
    	session.close();
    	
    	
    	
    	System.out.println( "Hello World!" );
       launch(args);
    }

}




