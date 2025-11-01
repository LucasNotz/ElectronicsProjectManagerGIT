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
 *  conectarTest()
 *  
 *  desconectarTest()
 *  	
 *  !static variables are constant through the whole class
 *  !aka there is no need to instance the class to access it
 *  !eu estou usando ela para poder testar a connexao sem ter que instancia essa classe
 */

public class btnConexaoTestControlador implements ActionListener {
	
	//propriedades da classe
	private boolean connectPermission = false;
	private JFrame fBanco = null;
	private JLabel lblConn = null;
	private JLabel lblConnStatus = null;
	private JTextField txtAddress = null;
	private JTextField txtPort= null;
	private JTextField txtBanco= null;
	private JTextField txtUser = null;
	private JTextField txtPassword = null;
	
	private int result = 0;
	
	//propriedade da classe connection testing
	public Connection objConexao = null;
	
	//construtor
	
	public btnConexaoTestControlador(boolean connectPermission, JFrame fBanco, JLabel lblConnStatus, JLabel lblConn, JTextField txtAddress, JTextField txtPort, JTextField txtBanco, JTextField txtUser, JTextField txtPassword) {
		this.connectPermission = connectPermission;
		this.fBanco = fBanco;
		this.lblConnStatus = lblConnStatus;
		this.lblConn = lblConn;
		this.txtAddress = txtAddress;
		this.txtPort = txtPort;
		this.txtBanco = txtBanco;
		this.txtUser = txtUser;
		this.txtPassword = txtPassword;
	}

	public void actionPerformed(ActionEvent e) {
		//System.out.println(lblConn.getText());
		try {
			objConexao = DriverManager.getConnection(lblConn.getText());
			result++;
			//System.out.println(lblConn.getText());
		} catch (Exception ex1) {
			ex1.printStackTrace();
		} 
		try {
			objConexao.close();
			
		} catch (Exception ex2) {
			ex2.printStackTrace();
		}
		if (result == 0) {
			lblConnStatus.setText("Connection cannot be established");
			lblConnStatus.setForeground(Color.red);
		} else {
			lblConnStatus.setText("Connection can be established");
			lblConnStatus.setForeground(Color.green);
			new Login().getfLogin().setVisible(true);
			fBanco.setVisible(false);
			
		}
		result = 0;

	}	
}
