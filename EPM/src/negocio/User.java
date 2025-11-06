package negocio;

import persistencia.UserDAO;

public class User {
	//Class variables
	private String username = "";
	private String password = "";

	//Constructor functions
	public User() {
		super();
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	//Access functions
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	//Class functions
	
	//Get user info from database
	public static String resgatar(String username, int i) throws Exception {
		UserDAO selectDAO = new UserDAO();	
		String[] credenciais = selectDAO.select(username);
		return credenciais[i];
	}
	
	//Insert user info into database
	public void persistir() throws Exception{
		new UserDAO().insert(this);
	}
}
