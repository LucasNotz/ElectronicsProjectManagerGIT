package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
	private String projectDescription = "";
	
	private String[][] listResult = new String[100][5];
	private int control = 0;
	
	public String[][] select() throws Exception {
		objBanco.conectar();
		
		objExecucao = objBanco.getObjConexao().prepareStatement("select * from PR_PROJECT");
				
		try {
			ResultSet rs = objExecucao.executeQuery();
			while (rs.next()) {
				String[] temp = new String[5];
				projectName = rs.getString("PR_name");
				temp[0] = projectName;
				projectBudget = rs.getDouble("PR_budget");
				temp[1] = String.valueOf(projectBudget);
				projectStartDate = rs.getTimestamp("PR_sDate");
				temp[2] = String.valueOf(projectStartDate);
				projectDueDate = rs.getTimestamp("PR_dDate");
				temp[3] = String.valueOf(projectDueDate);
				projectDescription = rs.getString("PR_description");
				temp[4] = projectDescription;
				listResult[control] = temp;
				control++;
			}
			
		} catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to get project from database1");
		}

		objBanco.desconectar();
		return listResult;
	}
	
	public int howManyProjects() throws Exception {
		objBanco.conectar();
		
		objExecucao = objBanco.getObjConexao().prepareStatement("select * from PR_PROJECT");
				
		try {
			ResultSet rs = objExecucao.executeQuery();
			while (rs.next()) {
				String[] temp = new String[5];
				projectName = rs.getString("PR_name");
				temp[0] = projectName;
				projectBudget = rs.getDouble("PR_budget");
				temp[1] = String.valueOf(projectBudget);
				projectStartDate = rs.getTimestamp("PR_sDate");
				temp[2] = String.valueOf(projectStartDate);
				projectDueDate = rs.getTimestamp("PR_dDate");
				temp[3] = String.valueOf(projectDueDate);
				projectDescription = rs.getString("PR_description");
				temp[4] = projectDescription;
				listResult[control] = temp;
				control++;
			}
			
		} catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to get project from database2");
		}

		objBanco.desconectar();
		return control;
	}
}
