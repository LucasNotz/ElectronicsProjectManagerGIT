package login;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import persistencia.Database;

public class Login {
	//Class variables
	
	//Connection testing variables
	private static Connection objConexaoTestLogin = null;
	private static int result = 0;
	private static String defaultConn = ""; //get from default connection.txt
	
	//Frame, panel and title
	private static JFrame fLogin = new JFrame(); //Making this static makes life easier
	private JPanel pn = new JPanel(); 
	private JLabel lblTitulo = new JLabel("Projeto HUB");
	
	//User Variables
	private JLabel lblUser = new JLabel("Username: ");
	private JTextField txtUser = new JTextField();
	
	//Password Variables
	private JLabel lblSenha = new JLabel("Senha: ");
	private JTextField txtSenha = new JTextField();
	
	//Button Variables
	private JButton btnLogin = new JButton("Login");
	private JButton btnRegistrar = new JButton("Registrar");
	private JButton btnSair = new JButton("Sair");
	private JButton btnLimpar = new JButton("Limpar");
	private JButton btnDatabase = new JButton("Database settings");
	
	//login status
	private JLabel lblLoginRegisterStatus = new JLabel("");
	
	//Flogin frame getters and setters (allows access when setting frame to visible)
	public JFrame getfLogin() {
		return fLogin;
	}

	@SuppressWarnings("static-access")
	public void setfLogin(JFrame fLogin) {
		this.fLogin = fLogin;
	}
	
	//main method, whole program starts here
	public static void main(String[] args) {
		//create login gui
		new Login();
		Login.fLogin.setVisible(true);
		
		//defines file as 'default connection.txt'
		File file = new File("default connection.txt");
		
		//defaultConn holds default connection value saved
		try (Scanner myReader = new Scanner(file)){
				defaultConn = myReader.nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Try to connect using defaultConn and if connection succeeds, then allow normal flow of program + control mechanism
		try {
			System.out.println("Default connection to be tested: " + defaultConn);
			
			//test connection
			objConexaoTestLogin = DriverManager.getConnection(defaultConn);
			result++;
			objConexaoTestLogin.close();
			
			System.out.println("Default connection closed: " +defaultConn);
			
			//set lblCon (value used to connect) to defaultconn
			Database.setLblCon(new JLabel(defaultConn));
		} catch (Exception ex1) {
			ex1.printStackTrace();
		} 

		System.out.println("Default connection test results: " + result);
		
		//Define actions for test results
		if (result == 0) {
			//Connection failed 
			
			//Go to database configuration frame
			JOptionPane.showMessageDialog(null, "Connection cannot be established");
			System.out.println("Database connection failed on: " + defaultConn);
			fLogin.setVisible(false);
			new Database().getfBanco().setVisible(true);
		} else {
			//Connection worked: stay on login frame
		}
	}
	
	//Constructor
	@SuppressWarnings({ "static-access"})
	public Login() {
		//Frame configuration
		getfLogin().setTitle("Login do Projeto HUB");
		getfLogin().setSize(1200,800);
		getfLogin().setResizable(false);
		getfLogin().setDefaultCloseOperation(fLogin.EXIT_ON_CLOSE);
		getfLogin().setLocationRelativeTo(null);
		
		//Panel configuration
		pn.setLayout(null);
		pn.setBackground(Color.white);
		getfLogin().add(pn);
		
		//Title configuration
		lblTitulo.setBounds(440,60,400,65);
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 50));
		pn.add(lblTitulo);
		
		//User configuration
		lblUser.setBounds(200, 200, 200, 40);
		lblUser.setFont(new Font("Serif", Font.PLAIN, 35));
		pn.add(lblUser);
		
		txtUser.setBounds(410, 200, 600, 40);
		txtUser.setFont(new Font("Serif", Font.PLAIN, 35));
		pn.add(txtUser);
		
		//Password configuration
		lblSenha.setBounds(240, 300, 150, 40);
		lblSenha.setFont(new Font("Serif", Font.PLAIN, 35));
		pn.add(lblSenha);
		
		txtSenha.setBounds(410, 300, 600, 40);
		txtSenha.setFont(new Font("Serif", Font.PLAIN, 35));
		pn.add(txtSenha);
		
		//Login button configuration
		btnLogin.setBounds(130, 450, 200, 60);
		btnLogin.setFont(new Font("Serif", Font.PLAIN, 35));
		pn.add(btnLogin);
		
		//Register button configuration
		btnRegistrar.setBounds(380, 450, 200, 60);
		btnRegistrar.setFont(new Font("Serif", Font.PLAIN, 35));
		pn.add(btnRegistrar);
	
		//Clear fields button configuration
		btnLimpar.setBounds(630, 450, 200, 60);
		btnLimpar.setFont(new Font("Serif", Font.PLAIN, 35));
		pn.add(btnLimpar);
		
		//Exit button configuration
		btnSair.setBounds(880, 450, 200, 60);
		btnSair.setFont(new Font("Serif", Font.PLAIN, 35));
		pn.add(btnSair);
		
		//Status labels configuration
		lblLoginRegisterStatus.setBounds(580,350,400,60);
		lblLoginRegisterStatus.setFont(new Font("Serif", Font.PLAIN, 25));
		pn.add(lblLoginRegisterStatus);
		
		//Button controllers configuration
		//Login button references controller class
		btnLogin.addActionListener(new BtnLoginControlador(getfLogin(), txtUser, txtSenha, lblLoginRegisterStatus));
		
		//Register button references controller class
		btnRegistrar.addActionListener(new BtnRegistrarControlador(txtUser, txtSenha, lblLoginRegisterStatus));
		
		//Exit button 
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//Clean fields button
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUser.setText("");
				txtSenha.setText("");
			}
		});
		
		//Database settings button
		btnDatabase.setBounds(800,700,200,20);
		btnDatabase.setFont(new Font("Serif", Font.BOLD, 15));
		pn.add(btnDatabase);
		btnDatabase.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Database().getfBanco().setVisible(true);
				Login.fLogin.setVisible(false);
			}
		});
	}

}
