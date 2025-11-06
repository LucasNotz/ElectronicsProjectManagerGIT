package negocio;

import java.util.ArrayList;

import persistencia.ProjectDAO;

public class Project {
	//Class variables
	private String nome = "";
	private double orcamento = 0;
	private String descricao = "";
	private String user = "";
	
	//Constructor function
	public Project() {
		super();
	}

	public Project(String nome, double orcamento, String descricao, String user) {
		super();
		this.descricao = descricao;
		this.nome = nome;
		this.orcamento = orcamento;
		this.user = user;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	//Access functions
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(double orcamento) {
		this.orcamento = orcamento;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	//Class functions
	
	//Get project info from database for specific 
	public static String select(String user,int i, int j) throws Exception {
		ProjectDAO DAOSelect = new ProjectDAO();
		String[][] projectData = DAOSelect.select(user);
		return projectData[i][j];
	}

	//Get project amount for a specific user
	public static int getProjectSize(String user) throws Exception {
		ProjectDAO DAOSelect2 = new ProjectDAO();
		int i = DAOSelect2.howManyProjects(user);
		return i;
	}
	
	//delete project from database (all projects have to have unique names)
	public static void deleteProjeto(String projectName) throws Exception {
		ProjectDAO DAOdelete = new ProjectDAO();
		DAOdelete.deleteProject(projectName);
	}
	
	public static ArrayList<Object> getProjectInfo(String projectName) throws Exception {
		ProjectDAO DAOget = new ProjectDAO();
		ArrayList<Object> data = new ArrayList<Object>();
		data = DAOget.getProjectInfo(projectName);
		System.out.println("Project: " + projectName + " was delete from database");
		return data;
	}
	
	public static void insertProject(Project projeto) throws Exception {
		ProjectDAO DAOinsert = new ProjectDAO();
		DAOinsert.insert(projeto);
	}
	
	public static void insertPart(Object partName, String projectName,  Object qtd) throws Exception {
		ProjectDAO DAOinsert2 = new ProjectDAO();
		DAOinsert2.insertParts(partName, projectName, qtd);
	}
	
}
