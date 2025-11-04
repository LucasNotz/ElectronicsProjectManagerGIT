package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class PartDAO {
	private Database objBanco = new Database();
	private PreparedStatement objExecucao = null;
	private ResultSet objCursor = null;
	
	public String[][] getAllParts() throws  Exception {
		objBanco.conectar();
		String[][] info = new String[100][10];
		objExecucao = objBanco.getObjConexao().prepareStatement(
				"Select * from PA_PART");
		int i = 0;
		objCursor = objExecucao.executeQuery();
		
		while (objCursor.next()) {
			info[i][0] = objCursor.getString("PA_name");
			i++;
		}
		objBanco.desconectar();
		return info;
	}
}
