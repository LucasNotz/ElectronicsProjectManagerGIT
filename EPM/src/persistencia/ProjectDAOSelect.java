package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.JOptionPane;

public class ProjectDAOSelect {

	//propriedades da classe
	private Database objBanco = new Database();
	private PreparedStatement objExecucao = null;
	
	private int projectID = 0; //not used
	private String projectName = "";
	private double projectBudget = 0;
	private java.sql.Timestamp projectStartDate = null; 
	private java.sql.Timestamp projectDueDate = null; 
	
	public void select() throws Exception {
		objBanco.conectar();
		
		objExecucao = objBanco.getObjConexao().prepareStatement("select * from PR_PROJECT");
				
		try {
			ResultSet rs = objExecucao.executeQuery();
			if (rs.next()) {
				projectName = rs.getString("PR_name");
				projectBudget = rs.getDouble("PR_budget");
				projectStartDate = rs.getTimestamp("PR_sDate");
				projectDueDate = rs.getTimestamp("PR_dDate");
			}
			
		} catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to get project from database");
		}
		
		List listResult = null;
		listResult.add(projectName);
		//listResult.add(listResult);
	}
}
