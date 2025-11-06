package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PartDAO {
	//Class Variables
	private Database objBanco = new Database();
	private PreparedStatement objExecucao = null;
	private ResultSet objCursor = null;
	
	//Get all parts from database
	public String[][] getAllParts() throws  Exception {
		//Connect to database
		objBanco.conectar();
		
		//Initialize return string ( = null instead of this will cause error)
		String[][] info = new String[100][10];
		
		//Prepare SQL statement
		objExecucao = objBanco.getObjConexao().prepareStatement(
				"Select * from PA_PART");
		
		//Execute statement
		objCursor = objExecucao.executeQuery();
		
		//Prepare return variable
		int i = 0;
		while (objCursor.next()) {
			info[i][0] = objCursor.getString("PA_name");
			i++;
		}
		
		//Disconnect
		objBanco.desconectar();
		
		//Return
		return info;
	}
	
	//Get part info 
	public String[][] getPartInfo() throws  Exception {
		//Connect
		objBanco.conectar();
		
		//Initialize return variable ( = null will return error)
		String[][] info = new String[100][10];
		
		//Prepare SQL Statement
		objExecucao = objBanco.getObjConexao().prepareStatement(
				"Select * from PA_PART");
		
		//Execute statement
		objCursor = objExecucao.executeQuery();
		
		//Prepare return variable
		int i = 0;
		while (objCursor.next()) {
			info[i][0] = objCursor.getString("PA_name");
			info[i][1] = objCursor.getString("PA_price");
			info[i][2] = objCursor.getString("PA_description");
			i++;
		}
		
		//Disconnect
		objBanco.desconectar();
		
		//Return
		return info;
	}
}
