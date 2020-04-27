package helpers;

import java.sql.*;

import com.mysql.jdbc.PreparedStatement;



public class MyConnection {

     public Connection MySQLConnection()  {
        Connection MySQLConnection = null;
        try {
            MySQLConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "");
       //   System.out.println("Udalo sie polaczyc");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return MySQLConnection;
    }
	

	
	
	
}