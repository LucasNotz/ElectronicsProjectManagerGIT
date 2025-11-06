package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import negocio.Project;

public class ProjectDAO {
	//Class variables
	private Database objBanco = new Database();
	private PreparedStatement objExecucao = null;
	private ResultSet objCursor = null;
	
	//select() 
	private String projetoNome = "";
	private double projetoOrcamento = 0;
	private String projetoDescricao = "";
	
	//select() return and control variables
	private String[][] listResult = new String[100][5];
	private int control = 0;
	
	//Select projects from specific user
	// ! To be resolved: due to the way the code was made, for each information you get
	//                   the database makes and closes a connection. Notice how
	//                   the console fills up with connection strings.
	public String[][] select(String user) throws Exception {
		//Connect to database
		objBanco.conectar();
		
		//Prepare SQL statement
		objExecucao = objBanco.getObjConexao().prepareStatement(
				"select * from PR_PROJECT where PR_username = ?");
		
		//Define statement parameters
		objExecucao.setString(1,user);
		
		//Try executing statement
		try {
			//Accept query results
			objCursor = objExecucao.executeQuery();
			
			//Do this for all query results
			while (objCursor.next()) {
				//Instantiate return variable
				String[] temp = new String[4];
				
				//Prepare return variable [name, budget, description, username]
				projetoNome = objCursor.getString("PR_name");
				temp[0] = projetoNome;
				projetoOrcamento = objCursor.getDouble("PR_budget");
				temp[1] = String.valueOf(projetoOrcamento);
				projetoDescricao = objCursor.getString("PR_description");
				temp[2] = projetoDescricao;
				user = objCursor.getString("PR_username");
				temp[3] = user;
				listResult[control] = temp;
				
				//Count how many projects per user
				control++;
			}
		} catch (Exception e){
			//Error
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to get project from database: ProjectDAO");
		}

		//Disconnect from database
		objBanco.desconectar();
		
		//Return
		return listResult;
	}
	
	//Get number of projects per user (Exact same fucntion with different return)
	public int howManyProjects(String user) throws Exception {
		//Connect to database
		objBanco.conectar();
		
		//Prepare SQL statement
		objExecucao = objBanco.getObjConexao().prepareStatement(
				"select * from PR_PROJECT where PR_username = ?");
		
		//Define statement parameters
		objExecucao.setString(1,user);
		
		//Try executing statement
		try {
			//Accept query results
			objCursor = objExecucao.executeQuery();
			
			//Do this for all query results
			while (objCursor.next()) {
				//Instantiate return variable
				
				//Prepare return variable [name, budget, description, user] 
				// ! IS NOT USED
				String[] temp = new String[4];
				projetoNome = objCursor.getString("PR_name");
				temp[0] = projetoNome;
				projetoOrcamento = objCursor.getDouble("PR_budget");
				temp[1] = String.valueOf(projetoOrcamento);
				projetoDescricao = objCursor.getString("PR_description");
				temp[2] = projetoDescricao;
				user = objCursor.getString("PR_username");
				temp[3] = user;
				listResult[control] = temp;
				
				//Count how many projects per user
				// ! WILL BE RETURNED 
				control++;
			}
		} catch (Exception e){
			//Error
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to get project from database: ProjectDAO");
		}
		
		//Disconnect from database
		objBanco.desconectar();
		
		//Return
		return control;
	}
	
	//Delete project from database
	public void deleteProject(String projectName) throws Exception {
		//Connect to database
		objBanco.conectar();
		
		//Prepare 1st SQL statement : delete all instances of PR_name from PR_PA to
		// 							  resolve foreign key inconsistency 
		
		objExecucao = objBanco.getObjConexao().prepareStatement(
				"delete from PR_PA where PR_name = ?");
		
		//Define 1st SQL statement parameter
		objExecucao.setString(1, projectName.strip());
		
		//Try executing 1st statement
		try {
			objExecucao.executeQuery();
		} catch (Exception e) {
			//Error
			e.printStackTrace();
		}
		
		//Prepare 2nd SQL statement
		objExecucao = objBanco.getObjConexao().prepareStatement(
				"delete from PR_PROJECT where PR_name = ?");
		
		//Define 2nd statement parameters
		objExecucao.setString(1, projectName.strip());
		
		//Try executing 2nd statement
		try {
			objExecucao.executeQuery();
		} catch (Exception e) {
			//Error
			e.printStackTrace();
		}
		
		//Disconnect
		objBanco.desconectar();			
	}

	//Get info from project
	public ArrayList<Object> getProjectInfo(String projectName) throws Exception {
		//Connect to database
		objBanco.conectar();
		
		//Prepare SQL statement
		objExecucao = objBanco.getObjConexao().prepareStatement(
				"select * from PR_PROJECT where PR_name = ?");
		
		//Control mechanism
		System.out.println("Info of project: " + projectName + " being displayed");
		
		//Instantiate return variable
		ArrayList<Object> info = new ArrayList<Object>();
		
		//Define statement parameters
		objExecucao.setString(1, projectName.strip());
		
		//Try executing statement
		try {
			objCursor = objExecucao.executeQuery();
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error when retrieving project info.");
			e.printStackTrace();
		}
		
		//Prepare return variable
		if(objCursor.next()) {
			info.add(0, objCursor.getString("PR_budget"));
			info.add(1, objCursor.getString("PR_description"));
		}
		
		//Disconnect
		objBanco.desconectar();
		
		//Return
		return info;
	}
	
	public void insert(Project objProjeto) throws Exception {
		objBanco.conectar();
		
		objExecucao = objBanco.getObjConexao().prepareStatement(
				"insert into PR_PROJECT (PR_name, PR_budget, PR_description, PR_username) values (?,?,?,?)");
		
		objExecucao.setString(1, objProjeto.getNome());
		objExecucao.setDouble(2, 0);
		objExecucao.setString(3 , objProjeto.getDescricao());
		objExecucao.setString(4, objProjeto.getUser());
		
		objExecucao.executeQuery();
		objBanco.desconectar();
	}
	
	public void insertParts(Object partName, String projectName, Object qtd) throws Exception {
		objBanco.conectar();
		
		objExecucao = objBanco.getObjConexao().prepareStatement(
				"insert into PR_PA (PR_name, PA_name, PR_PA_qnt) values (?,?,?)");
		
		objExecucao.setString(1, projectName);
		objExecucao.setString(2, (String) partName);
		objExecucao.setInt(3, (int) qtd);
		
		objExecucao.executeQuery();
		objBanco.desconectar();
	}
}
