package persistencia;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import login.Login;
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
 *  	try connectar
 *  
 *  	try desconectar
 *  
 *  	reslutados
 *  	
 *  		proxima tela (se sucesso)
 */

public class btnConexaoTestControlador implements ActionListener {
	
	//propriedades da classe
	private JFrame fBanco = null;
	private JLabel lblConn = null;
	private JLabel lblConnStatus = null;
	private JTextField txtAddress = null;
	private JTextField txtPort= null;
	private JTextField txtBanco= null;
	private JTextField txtUser = null;
	private JTextField txtPassword = null;
	
	//variavel de controle
	private int result = 0;
	
	//propriedade da classe connection testing
	public Connection objConexao = null;
	
	//construtor
	
	public btnConexaoTestControlador(JFrame fBanco, JLabel lblConnStatus, JLabel lblConn, JTextField txtAddress, JTextField txtPort, JTextField txtBanco, JTextField txtUser, JTextField txtPassword) {
		this.fBanco = fBanco;
		this.lblConnStatus = lblConnStatus;
		this.lblConn = lblConn;
		this.txtAddress = txtAddress;
		this.txtPort = txtPort;
		this.txtBanco = txtBanco;
		this.txtUser = txtUser;
		this.txtPassword = txtPassword;
	}

	//evento do butao pressionado
	public void actionPerformed(ActionEvent e) {
		//verifica conexao
		try {
			objConexao = DriverManager.getConnection(lblConn.getText());
			result++;
		} catch (Exception ex1) {
			ex1.printStackTrace();
		} 
		//fecha conexao
		try {
			objConexao.close();
			
		} catch (Exception ex2) {
			ex2.printStackTrace();
		}
		//define resultados para campo de verificacao de conexao
		if (result == 0) {
			lblConnStatus.setText("Connection cannot be established");
			lblConnStatus.setForeground(Color.red);
		} else {
			lblConnStatus.setText("Connection can be established");
			lblConnStatus.setForeground(Color.green);
			//conexao bem sucedia ->tela de login
			new Login().getfLogin().setVisible(true);
			fBanco.setVisible(false);
			
		}
		result = 0;

	}	
}
