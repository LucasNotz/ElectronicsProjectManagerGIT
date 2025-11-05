package negocio;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import persistencia.PartDAO;

/**
 * Essa classe é a Part
 *  - Modelo para as partes 
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

public class Part {
	
	//propriedade da classe
	
	private String nome = "";
	private double preco = 0;
	private String descricao = "";
	private File imagem = null;

	
	//métodos construtores
	
	public Part() {
		super();
	}


	public Part(String nome, double preco, String descricao, File imagem) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.imagem = imagem;
	}

	//métodos de acesso
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public double getPreco() {
		return preco;
	}


	public void setPreco(double preco) {
		this.preco = preco;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public File getImagem() {
		return imagem;
	}


	public void setImagem(File imagem) {
		this.imagem = imagem;
	}
	
	//métodos da classe
	
	//cirar métodos persistir
	
	public static Collection<String> getTodosS() throws Exception {
		ArrayList<String> type = new ArrayList<String>();
		type.add("Ressitor");
		type.add("Capacitor");
		type.add("Indutor");
		type.add("Diodo");
		type.add("Transistor");
		type.add("Fonte");
		type.add("Microcontrolador");
		type.add("Fio connector");
		type.add("Sensor");
		type.add("Conversor AD");
		type.add("Conversor DA");
		
		return type;
		
	}

	public static String[][] getAllParts() throws Exception {
		String[][] data = null;
		PartDAO PartDAO = new PartDAO();
		data = PartDAO.getAllParts();
		return data;
	}
	
	public static String getPart(int i,int j) throws Exception {
		String[][] data = null;
		PartDAO PartDAO = new PartDAO();
		data = PartDAO.getPartInfo();
		return data[i][j];
	}
	
}
