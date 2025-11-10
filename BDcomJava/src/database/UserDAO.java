package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class UserDAO {
	//Class attributes
	private PreparedStatement statementObj;
	private ResultSet cursorObj;
	private LinkedList<String> userCredentials = new LinkedList<String>();
	private LinkedList<String> usersUsernames = new LinkedList<String>();
	
	private Database database = new Database();

	
	//Class methods
	
	//get user credentials 
	public LinkedList<String> getUserCredentials(String usernameInput){
		database.connect();
		
		//Prepare statement
		try {
			statementObj = database.getConnectionObj().prepareStatement(
					"SELECT username, password FROM user WHERE username = ?"
					);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not retrieve user credentials (1)");
			error.printStackTrace();
		}
		
		//Prepare statement parameters
		try {
			statementObj.setString(1, usernameInput);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not retrieve user credentials (2)");
			error.printStackTrace();
		}
		
		//Execute statement and set return values
		try {
			cursorObj = statementObj.executeQuery();
			if (cursorObj.next()) {
				userCredentials.add(0,cursorObj.getString("username"));
				userCredentials.add(1,cursorObj.getString("password"));
			}
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not retrieve user credentials (3)");
			error.printStackTrace();
		}
		
		database.disconnect();
		
		return userCredentials;
	}
	
	//get all user credentials
	public LinkedList<String> getAllUsers() {
		database.connect();
		
		//Prepare statement
		try {
			statementObj = database.getConnectionObj().prepareStatement(
					"SELECT username FROM user"
					);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not retrieve all user usernames (1)");
			error.printStackTrace();
		}
		
		//Execute statement and set return values
		try {
			cursorObj = statementObj.executeQuery();
			while (cursorObj.next()) {
				usersUsernames.add(cursorObj.getString("username"));
			}
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not retrieve all user usernames (2)");
			error.printStackTrace();
		}
		
		database.disconnect();
		
		return usersUsernames;
	}
	
	//create user
	public void createUser(String username, String password) {
		database.connect();
		
		//Prepare statement
		try {
			statementObj = database.getConnectionObj().prepareStatement(
					"INSERT INTO user (username, password) values (?, ?)"
					);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not insert user into database (1)");
			error.printStackTrace();
		}
		
		//Prepare statement parameters
		try {
			statementObj.setString(1, username);
			statementObj.setString(2, password);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not insert user into database (2)");
			error.printStackTrace();
		}
		
		//Execute statement
		try {
			statementObj.executeUpdate();
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not insert user into database (3)");
			error.printStackTrace();
		}
		
		database.disconnect();
	}
	
}
