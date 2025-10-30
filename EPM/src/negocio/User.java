package negocio;

import persistencia.UserDAOInsert;
import persistencia.UserDAOSelect;

/**
 * Essa classe é a User
 *  - Modelo para as users
 *  - Overview
 *  	
 *  Propriedades
 *  
 *  Metodos construtores
 *  
 *  Metodos de acesso
 *  
 *  Metodos da classe
 *  
 *  	resgatar()
 *  
 *  	persistir()
 */

public class User {
	
	//propriedades da classe
	
	private String username = "";
	private String password = "";
	
	//métodos construtores
	
	public User() {
		super();
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	//métodos de acesso
	
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
	
	//métodos da classe
	
	//resgatar credenciais do banco de dados
	
	public static String resgatar(String username, int i) throws Exception {
		UserDAOSelect selectDAO = new UserDAOSelect();	
		String[] credenciais = selectDAO.select(username);
		
		return credenciais[i];
	}
	
	//registrar no bd
	
	public void persistir() throws Exception{
		new UserDAOInsert().insert(this);
	}
	
	
	
	
	
}



