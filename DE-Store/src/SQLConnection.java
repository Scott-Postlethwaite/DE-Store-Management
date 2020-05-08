import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;



public class SQLConnection {

	public static Connection dbConnector() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/de-store?user=scott1&password=password");
			
			return conn; 
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e); 
			return null; 
		}
		
	}
	
	public static Connection dbConnector2() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/accountingdatabase?user=scott&password=password");
			
			return conn; 
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e); 
			return null; 
		}
		
	}
}
