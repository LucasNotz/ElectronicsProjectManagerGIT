package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import negocio.User;

/**
 * Essa classe é a UserDAOSelect
 *  - Modelo para as resgatar usuario do BD
 *  - Overview
 *  	
 *  Propriedades
 *  
 *  Metodos da classe
 *  
 *  	select()
 */

public class UserDAOSelect {
	
	//propriedades da classe
	private Database objBanco = new Database();
	private PreparedStatement objExecucao = null;
	
	private String userBuscado = "";
	private String senhaBuscada = "";
	
	//métodos da classe
	public String[] select(String username) throws Exception {
		objBanco.conectar();
		
		objExecucao = objBanco.getObjConexao().prepareStatement(
				"select U_username, U_password from U_USER where U_username = ?"		
				);
		objExecucao.setString(1,username);
		try {
		//test to see if exists
			ResultSet rs = objExecucao.executeQuery();
			//Result set comeca em uma linha vazia inicail
			//rs.next() retorna true e permite comecar na primeira linha
			if (rs.next()) {
			userBuscado = rs.getString("U_username");
			senhaBuscada = rs.getString("U_password");
			}
		} catch (Exception e) {
			//define failed 
			JOptionPane.showMessageDialog(null, e);

		}
		
		//prepara variavel para retornar
		String[] credenciais = {userBuscado, senhaBuscada};
		objBanco.desconectar();
		return credenciais;
	}
}
