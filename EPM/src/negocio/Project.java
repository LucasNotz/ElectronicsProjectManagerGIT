package negocio;

import java.util.Date;

import persistencia.ProjectDAOSelect;


public class Project {
	
	//propriedades da classe
	
	private String nome = "";
	private double orcamento = 0;
	private Date data = null;
	private String[] parts = {};
	
	//métodos construtores
	
	public Project() {
		super();
	}

	public Project(String nome, double orcamento, Date data, String[] parts) {
		super();
		this.nome = nome;
		this.orcamento = orcamento;
		this.data = data;
		this.parts = parts;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getPart(int i) {
		return this.parts[i];
	}
	
	public void setPart(String part, int i) {
		this.parts[i] = part;
	}

	//métodos da classe
	
	//persistir a ser adicionado
	
	public static String select(int i, int j) throws Exception {
		ProjectDAOSelect DAOSelect = new ProjectDAOSelect();
		String[][] projectData = DAOSelect.select();
		return projectData[i][j];
	}
	
	public static int getProjectSizes() throws Exception {
		ProjectDAOSelect DAOSelect2 = new ProjectDAOSelect();
		int i = DAOSelect2.howManyProjects();
		return i;
	}
	
	
}
