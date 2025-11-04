package negocio;

import java.util.ArrayList;
import java.util.Date;

import persistencia.ProjectDAO;


public class Project {
	
	//propriedades da classe
	
	private String nome = "";
	private double orcamento = 0;
	private String[] parts = {};
	private String user = "";
	
	//métodos construtores
	
	public Project() {
		super();
	}

	public Project(String nome, double orcamento, String[] parts, String user) {
		super();
		this.nome = nome;
		this.orcamento = orcamento;
		this.parts = parts;
		this.user = user;
	}

	//métodos de acesso 
	
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

	public String getPart(int i) {
		return this.parts[i];
	}
	
	public void setPart(String part, int i) {
		this.parts[i] = part;
	}

	//métodos da classe
	
	//persistir a ser adicionado
	
	public static String select(String user,int i, int j) throws Exception {
		ProjectDAO DAOSelect = new ProjectDAO();
		String[][] projectData = DAOSelect.select(user);
		return projectData[i][j];
	}

	
	public static int getProjectSize(String user) throws Exception {
		ProjectDAO DAOSelect2 = new ProjectDAO();
		int i = DAOSelect2.howManyProjects(user);
		return i;
	}
	
	public static void deleteProjeto(String projectName) throws Exception {
		ProjectDAO DAOdelete = new ProjectDAO();
		DAOdelete.deleteProject(projectName);
	}
	
	public static ArrayList<Object> getProjectInfo(String projectName) throws Exception {
		ProjectDAO DAOget = new ProjectDAO();
		ArrayList<Object> data = new ArrayList<Object>();
		data = DAOget.getProjectInfo(projectName);
		System.out.println(4);
		return data;
	}
	
	
}
