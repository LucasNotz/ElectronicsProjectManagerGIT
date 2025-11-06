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

public class btnConexaoTestControlador implements ActionListener {
	//Class variables
	static private JFrame fBanco = null;
	static private JLabel lblConn = null;
	static private JLabel lblConnStatus = null;
	static private JTextField txtAddress = null;
	static private JTextField txtPort= null;
	static private JTextField txtBanco= null;
	static private JTextField txtUser = null;
	static private JTextField txtPassword = null;
	
	//Control variables
	private int result = 0;
	
	//Connection testing variables
	private Connection objConexaoTest = null;

	//constructor
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

	public void actionPerformed(ActionEvent e) {
		//Test Connection
		try {
			objConexaoTest = DriverManager.getConnection(lblConn.getText());
			System.out.println("Connection can be established");
			result++;
		} catch (Exception ex1) {
			ex1.printStackTrace();
		} 

		//IF ELSE (connection can be established)
		if (result == 0) {
			//Connection failed
			lblConnStatus.setText("Connection cannot be established");
			lblConnStatus.setForeground(Color.red);
		} else {
			//Connection can be established 
			//Change visible frames
			new Login().getfLogin().setVisible(true);
			fBanco.setVisible(false);
			try {
				//Close test connection
				objConexaoTest.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		//Reset control variable
		result = 0;
	}	
}
