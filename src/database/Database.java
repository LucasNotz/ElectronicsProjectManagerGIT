package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import visual.Settings;

public class Database {
	//Class attributes
	private static String connectionURL = "";
	private Connection connectionObj;
	
	//Class methods
	
	//Connection url definition 
	public static void setConnectionURl(String connectionURL) {
		Database.connectionURL = connectionURL;
	}
	
	//Database access
	Connection getConnectionObj() {
		return connectionObj;
	}
	
	void connect() {
		try {
			connectionObj = DriverManager.getConnection(connectionURL);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not connect to database");
			error.printStackTrace();
			new Settings().getJFrame().setVisible(true);
		}
	}
	
	void disconnect() {
		try {
			connectionObj.close();
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not disconnect from database");
			error.printStackTrace();
		}
	}
}
