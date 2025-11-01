package persistencia;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Essa classe é a Database
 *  - Modelo para as database
 *  - Overview
 *  	
 *  Propriedades
 *  
 *  Propriedades para gui
 *  
 *  PSVM
 *  
 *  Método construtor gui
 *  
 *  Metodos da classe
 *  
 *  	getObjConexao()
 *  
 *  	conectar()
 *  
 *  	desconectar()
 */

public class Database {
	
	//propriedades da classe
	
	private Connection objConexao = null;
	
	//propriedade para gui
	
	JFrame fBanco = new JFrame();
	JPanel pn = new JPanel();
	JLabel lblBanco = new JLabel("Insert database name:");
	JLabel lblAddress = new JLabel("Insert ip: ");
	JLabel lblPort = new JLabel("Insert port: ");
	JLabel lblUser = new JLabel("Insert username: ");
	JLabel lblPassword = new JLabel("Insert password: ");
	
	JTextField txtBanco = new JTextField();
	JTextField txtAddress = new JTextField();
	JTextField txtPort = new JTextField();
	JTextField txtUser = new JTextField();
	JTextField txtPassword = new JTextField();
	
	JButton btnTestString = new JButton("Set connection");
	JButton btnTestCon = new JButton("Test connection");
	JButton btnCon = new JButton("Connect");
	
	JLabel lblConnFormat = new JLabel(
			"jdbc:mariadb://<ip>:<port/<database>?user=<user>&password=<password>&serverTimezone=UTC&userSSL=false"
			);
	
	JLabel lblConn = new JLabel(
			"jdbc:mariadb://"+txtAddress.getText()+":"+txtPort.getText()+"/"+txtBanco.getText()+
			"?user="+txtUser.getText()+"&password="+txtPassword.getText()+"&serverTimezone=UTC&userSSL=false"
			//"jdbc:mariadb://127.0.0.1:3306/HUBtest_db?user=java&password=ceub123456&serverTimezone=UTC&userSSL=false"
			);
	
	JLabel lblConnStatus = new JLabel("No connection");
	
	public static void main(String[] args) {
		new Database().fBanco.setVisible(true);
	}
	
	//método construtor gui 
	
	public Database() {
		
		//frame config
		fBanco.setTitle("Login do Projeto HUB");
		fBanco.setSize(1000,500);
		fBanco.setResizable(false);
		fBanco.setDefaultCloseOperation(fBanco.EXIT_ON_CLOSE);
		fBanco.setLocationRelativeTo(null);
		
		//panel config
		pn.setLayout(null);
		pn.setBackground(Color.white);
		fBanco.add(pn);
		
		//banco config
		lblBanco.setBounds(20,20,200,20);
		pn.add(lblBanco);
		
		txtBanco.setBounds(220,20,200,20);
		pn.add(txtBanco);
		
		//address config
		lblAddress.setBounds(20,50,200,20);
		pn.add(lblAddress);
		
		txtAddress.setBounds(220,50,200,20);
		pn.add(txtAddress);
		
		//port config 
		lblPort.setBounds(20,80,200,20);
		pn.add(lblPort);
		
		txtPort.setBounds(220,80,200,20);
		pn.add(txtPort);
		
		//user config
		lblUser.setBounds(20,110,200,20);
		pn.add(lblUser);
		
		txtUser.setBounds(220,110,200,20);
		pn.add(txtUser);
		
		//password config
		lblPassword.setBounds(20,140,200,20);
		pn.add(lblPassword);
		
		txtPassword.setBounds(220,140,200,20);
		pn.add(txtPassword);
		
		//btn string config
		btnTestString.setBounds(50, 200, 100,20);
		pn.add(btnTestString);
		btnTestString.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblConn.setText("jdbc:mariadb://"+ txtAddress.getText() + ":"+ txtPort.getText() + "/" + txtBanco.getText() 
				+"?user="+ txtUser.getText() +"&password=" + txtPassword.getText() + "&serverTimezone=UTC&userSSL=false");
			}
		});
		
		//btn test connection config
		btnTestCon.setBounds(170, 200, 150,20);
		pn.add(btnTestCon);
		btnTestCon.addActionListener(new btnConexaoTestControlador(lblConnStatus, lblConn, txtAddress, txtPort, txtBanco, txtUser, txtPassword));
		
		//btn connection config
		btnCon.setBounds(340, 200, 100,20);
		pn.add(btnCon);
		
		//lbl connection Fomrat
		lblConnFormat.setBounds(50,300,900,20);
		pn.add(lblConnFormat);
		
		//lbl connection
		lblConn.setBounds(50,350,900,20);
		pn.add(lblConn);
		
		//lbl conn status
		lblConnStatus.setBounds(150,400,250,20);
		lblConnStatus.setForeground(Color.black);
		pn.add(lblConnStatus);
		
	}
	
	//métodos da classe
	
	Connection getObjConexao() throws Exception {
		return objConexao;
	}
	
		//instacia obj conexao e permite utilizar ele sem ter que instanciar a classe
		//getobjconexao fornece essa informacao
	void conectar() throws Exception {
		objConexao = DriverManager.getConnection(lblConn.getText());
				
	}
	
	
	void desconectar() throws Exception {
		objConexao.close();
	}
	
	
}

//"jdbc:mariadb://127.0.0.1:3306/HUBtest_db?user=java&password=ceub123456&serverTimezone=UTC&userSSL=false"
