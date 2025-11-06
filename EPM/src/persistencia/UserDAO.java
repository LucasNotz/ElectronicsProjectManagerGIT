package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import negocio.User;

public class UserDAO {
	//Class Variables
	private Database objBanco = new Database();
	private PreparedStatement objExecucao = null;
	private ResultSet objCursor = null;

	//insert() method variables
	private String userBuscado = "";
	private String senhaBuscada = "";

	//Class functions
	
	//Insert user into database function
	public void insert(User objUser) throws Exception {
		//Database connection
		objBanco.conectar();

		//Define SQL statement
		objExecucao = objBanco.getObjConexao().prepareStatement(
				"insert into U_USER (U_username, U_password) values(?,?)");
	
		//Define statement parameters 
		objExecucao.setString(1, objUser.getUsername());
		objExecucao.setString(2, objUser.getPassword());
		
		//Execute statement
		objExecucao.executeUpdate();
		
		//Disconnect
		objBanco.desconectar();
	}
	
	//Retrieve user information
	public String[] select(String username) throws Exception {
		//Database connection
		objBanco.conectar();
		
		//Define SQL statement
		objExecucao = objBanco.getObjConexao().prepareStatement(
				"select U_username, U_password from U_USER where U_username = ?");
		
		//Define statement parameters
		objExecucao.setString(1,username);
		
		//Try to insert into database
		try {
			//Accept query results
			objCursor = objExecucao.executeQuery();
			
			//Go to first line
			if (objCursor.next()) {
				//Set variables to be returned
				userBuscado = objCursor.getString("U_username");
				senhaBuscada = objCursor.getString("U_password");
			}
		} catch (Exception e) {
			//Error
			JOptionPane.showMessageDialog(null, e);
		}
		
		//Prepare return variable (initialize and set)
		String[] credenciais = {userBuscado, senhaBuscada};
		
		//Disconnect AFTER setting return variable
		objBanco.desconectar();
		
		//Return
		return credenciais;
	}
}
