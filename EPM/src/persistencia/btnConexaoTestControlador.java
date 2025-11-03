package persistencia;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	static private JFrame fBanco = null;
	static private JLabel lblConn = null;
	static private JLabel lblConnStatus = null;
	static private JTextField txtAddress = null;
	static private JTextField txtPort= null;
	static private JTextField txtBanco= null;
	static private JTextField txtUser = null;
	static private JTextField txtPassword = null;
	
	//variavel de controle
	private int result = 0;
	
	//propriedade da classe connection testing
	private Connection objConexaoTest = null;
	
	
	
	//construtor
	

	public btnConexaoTestControlador(JFrame fBanco, JLabel lblConnStatus, JLabel lblConn, JTextField txtAddress, JTextField txtPort, JTextField txtBanco, JTextField txtUser, JTextField txtPassword) {
		btnConexaoTestControlador.fBanco = fBanco;
		btnConexaoTestControlador.lblConnStatus = lblConnStatus;
		btnConexaoTestControlador.lblConn = lblConn;
		btnConexaoTestControlador.txtAddress = txtAddress;
		btnConexaoTestControlador.txtPort = txtPort;
		btnConexaoTestControlador.txtBanco = txtBanco;
		btnConexaoTestControlador.txtUser = txtUser;
		btnConexaoTestControlador.txtPassword = txtPassword;
	}

	//evento do butao pressionado
	public void actionPerformed(ActionEvent e) {
		//verifica conexao
		try {
			objConexaoTest = DriverManager.getConnection(lblConn.getText());
			System.out.println("Connection can be established");
			result++;
		} catch (Exception ex1) {
			ex1.printStackTrace();
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
			try {
				objConexaoTest.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			fBanco.setVisible(false);
			
		}
		result = 0;

	}	
}
