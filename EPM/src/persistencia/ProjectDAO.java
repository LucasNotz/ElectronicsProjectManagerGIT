package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ProjectDAO {

	//propriedades da classe
	private Database objBanco = new Database();
	private PreparedStatement objExecucao = null;
	
	private String projectName = "";
	private double projectBudget = 0;
	private String projectDescription = "";
	
	private String[][] listResult = new String[100][5];
	private int control = 0;
	
	public String[][] select(String user) throws Exception {
		objBanco.conectar();
		
		objExecucao = objBanco.getObjConexao().prepareStatement("select * from PR_PROJECT where PR_username = ?");
		objExecucao.setString(1,user);
		try {
			ResultSet rs = objExecucao.executeQuery();
			while (rs.next()) {
				String[] temp = new String[4];
				projectName = rs.getString("PR_name");
				temp[0] = projectName;
				projectBudget = rs.getDouble("PR_budget");
				temp[1] = String.valueOf(projectBudget);
				projectDescription = rs.getString("PR_description");
				temp[2] = projectDescription;
				user = rs.getString("PR_username");
				temp[3] = user;
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
	
	public int howManyProjects(String user) throws Exception {
		objBanco.conectar();
		
		objExecucao = objBanco.getObjConexao().prepareStatement("select * from PR_PROJECT where PR_username = ?");
		objExecucao.setString(1,user);
		try {
			ResultSet rs = objExecucao.executeQuery();
			while (rs.next()) {
				String[] temp = new String[4];
				projectName = rs.getString("PR_name");
				temp[0] = projectName;
				projectBudget = rs.getDouble("PR_budget");
				temp[1] = String.valueOf(projectBudget);
				projectDescription = rs.getString("PR_description");
				temp[2] = projectDescription;
				user = rs.getString("PR_username");
				temp[3] = user;
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
	
	public void deleteProject(String projectName) throws Exception {
		objBanco.conectar();
		
		objExecucao = objBanco.getObjConexao().prepareStatement(
				"delete from PR_PROJECT where PR_name = ?"
				);
		objExecucao.setString(1, projectName.strip());
		try {
			objExecucao.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		objBanco.desconectar();
				
	}

	public ArrayList<Object> getProjectInfo(String projectName) throws Exception {
		objBanco.conectar();
		
		objExecucao = objBanco.getObjConexao().prepareStatement(
				"select * from PR_PROJECT where PR_name = ?"
				);
		System.out.println(projectName);
		ArrayList<Object> info = new ArrayList<Object>();
		objExecucao.setString(1, projectName.strip());
		
		ResultSet rs = objExecucao.executeQuery();

		if(rs.next()) {

			info.add(0, rs.getString("PR_budget"));
			info.add(1, rs.getString("PR_description"));

		}
		
		objBanco.desconectar();
		return info;
	}
	
}
