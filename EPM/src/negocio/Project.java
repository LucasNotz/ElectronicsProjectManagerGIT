package negocio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Essa classe é a Projeto
 *  - Modelo para as projetos
 *  - Overview
 *  	
 *  Propriedades
 *  
 *  Metodos construtores
 *  
 *  Metodos de acesso
 *  
 *  Metodos da classe
 *  
 *  	getTodos()
 */

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
	
	//not sure if this is necessary
	public Collection<String> getTodos() throws Exception {
		ArrayList<String> allParts = new ArrayList<String>();
		for(String part : this.parts) {
			allParts.add(part);
		}
		return allParts;
	}
	
	
	
	
}
