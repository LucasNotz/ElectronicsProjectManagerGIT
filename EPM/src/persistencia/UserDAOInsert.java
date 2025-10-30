package persistencia;

import java.sql.PreparedStatement;
import java.time.LocalDate;

import negocio.User;

/**
 * Essa classe é a UserDAOInsert
 *  - Modelo para as inserir usuarios no DB
 *  - Overview
 *  	
 *  Propriedades
 *  
 *  Metodos da classe
 *  
 *  	insert()
 */

public class UserDAOInsert {
	
	//propriedades da classe
	private Database objBanco = new Database();
	private PreparedStatement objExecucao = null;
	
	//métodos da class
	public void insert(User objUser) throws Exception {
		objBanco.conectar();
		
		//insere no banco
		objExecucao = objBanco.getObjConexao().prepareStatement(
				"insert into U_USER (U_username, U_password,U_date) values"
				+ "(?,?,?)"
				);
		objExecucao.setString(1, objUser.getUsername());
		objExecucao.setString(2, objUser.getPassword());
		try {
			LocalDate myObj = LocalDate.now();
			objExecucao.setString(3, myObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//insere no banco
		objExecucao.executeUpdate();
		objBanco.desconectar();
				
	}
	
}
