package logical;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import database.UserDAO;

public class User {
	//Class attributes
	private String username = "";
	private String password = "";
	
	//Constructors 
	public User() {
		super();
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	//Getters and setters
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	//Database methods
	
	//get user credentials
	public static LinkedList<String> getUserCredentials(String usernameInput) {
		UserDAO dao = new UserDAO();
		LinkedList<String> credentials = new LinkedList<String>();
		credentials = dao.getUserCredentials(usernameInput);
		return credentials;
	}
	
	//get all user usernames
	public static LinkedList<String> getAllUsers() {
		UserDAO dao = new UserDAO();
		LinkedList<String> usersUsernames = new LinkedList<String>();
		usersUsernames = dao.getAllUsers();
		return usersUsernames;
	}
	
	//create new user
	public static void createUser(String username, String password) {
		UserDAO dao = new UserDAO();
		dao.createUser(username, password);
	}
	
	//check if user exists
	public static boolean doesUserExist(String usernameInput) {
		UserDAO dao = new UserDAO();
		LinkedList<String> usersUsernames = new LinkedList<String>();
		usersUsernames = dao.getAllUsers();
		for (String username : usersUsernames) {
			if (usernameInput.equals(username) ) {
				return true;
			} 
		}
		return false;

	}
}
