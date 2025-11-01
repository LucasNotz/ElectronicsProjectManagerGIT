package persistencia;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;

import javax.swing.JTextField;

import org.mariadb.jdbc.Connection;
/**
 *  Esse é a classe controlador do butao de testConnection
 *   - Overview da classe
 *  	
 *  Propriedades
 *  
 *  Construtor
 *  
 *  actionPerformed()
 *  	
 *  
 */

public class btnConexaoTestControlador implements ActionListener {
	
	//propriedades da classe
	String connField = null;
	JTextField txtAddress = new JTextField();
	JTextField txtPort= new JTextField();
	JTextField txtBanco= new JTextField();
	JTextField txtUser = new JTextField();
	JTextField txtPassword = new JTextField();
	
	//propriedade da classe connection testing
	private Connection objConexao = null;
	private btnConexaoTestControlador objTest = new btnConexaoTestControlador();
	
	//construtor
	
	public btnConexaoTestControlador(){
		
	}
	
	public btnConexaoTestControlador(String connField, JTextField txtAddress, JTextField txtPort, JTextField txtBanco, JTextField txtUser, JTextField txtPassword) {
		this.connField = connField;
		this.txtAddress = txtAddress;
		this.txtPort = txtPort;
		this.txtBanco = txtBanco;
		this.txtUser = txtUser;
		this.txtPassword = txtPassword;
	}

	public void actionPerformed(ActionEvent e) {
		connField = "jdbc:mariadb://"+ txtAddress.getText() + ":"+ txtPort.getText() + "/" + txtBanco.getText() 
		+"?user="+ txtUser.getText() +"&password=" + txtPassword.getText() + "&serverTimezone=UTC&userSSL=false";
		
		try {
			objConexao.conectarTest();
		}
		
	}

	Connection getObjConexaoTest() throws Exception {
		return objConexao;
	}
	
	void conectarTest() throws Exception {
		objConexao = DriverManager.getConnection(connField);
	}
	
	void desconectarTest() throws Exception {
		objConexao.close();
	}
}
