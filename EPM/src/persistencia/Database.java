package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Essa classe é a Database
 *  - Modelo para as database
 *  - Overview
 *  	
 *  Propriedades
 *  
 *  Metodos da classe
 *  
 *  	getObjConexao()
 *  
 *  	conectar()
 *  
 *  	desconectar()
 */

public class Database {
	
	//propriedades da classe
	
	private Connection objConexao = null;
	
	//métodos da classe
	
	Connection getObjConexao() throws Exception {
		return objConexao;
	}
	
		//instacia obj conexao e permite utilizar ele sem ter que instanciar a classe
		//getobjconexao fornece essa informacao
	void conectar() throws Exception {
		objConexao = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3306/HUBtest_db?user=root&password=inmariadbwetrust"
				+ "&serverTimezone=UTC&userSSL=false"
				);
				
	}
	
	void desconectar() throws Exception {
		objConexao.close();
	}
	
	
}
