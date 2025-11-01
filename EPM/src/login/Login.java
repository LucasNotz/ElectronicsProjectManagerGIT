package login;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Essa é a classe de Login
 * 	-Inicio do programa (PSVM)
 * 	- Overview da classe
 * 
 * Propriedade da classe
 * 
 * 
 * Login() - construtor do gui
 * 
 * Controladores
 */

public class Login {

	//propriedades da classe
	private JFrame fLogin = new JFrame(); //Frame
	private JPanel pn = new JPanel(); //Painel
	private JLabel lblTitulo = new JLabel("Projeto HUB"); //Título
	
	//User Variables
	private JLabel lblUser = new JLabel("Username: ");
	private JTextField txtUser = new JTextField();
	
	//Password Varibles
	private JLabel lblSenha = new JLabel("Senha: ");
	private JTextField txtSenha = new JTextField();
	
	//Button Variables
	private JButton btnLogin = new JButton("Login");
	private JButton btnRegistrar = new JButton("Registrar");
	private JButton btnSair = new JButton("Sair");
	private JButton btnLimpar = new JButton("Limpar");
	
	//flogin frame getters and setters (allows access when setting frame to visible)
	public JFrame getfLogin() {
		return fLogin;
	}


	public void setfLogin(JFrame fLogin) {
		this.fLogin = fLogin;
	}
	
	
	
	//Construtor do GUI de login
	public Login() {
		
		//frame config
		getfLogin().setTitle("Login do Projeto HUB");
		getfLogin().setSize(1200,800);
		getfLogin().setResizable(false);
		getfLogin().setDefaultCloseOperation(getfLogin().EXIT_ON_CLOSE);
		getfLogin().setLocationRelativeTo(null);
		
		
		
		//panel config
		pn.setLayout(null);
		pn.setBackground(Color.white);
		getfLogin().add(pn);
		
		//titulo config
		lblTitulo.setBounds(440,60,400,65);
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 50));
		pn.add(lblTitulo);
		
		//username config
		lblUser.setBounds(200, 200, 200, 40);
		lblUser.setFont(new Font("Serif", Font.PLAIN, 35));
		pn.add(lblUser);
		
		txtUser.setBounds(410, 200, 600, 40);
		txtUser.setFont(new Font("Serif", Font.PLAIN, 35));
		pn.add(txtUser);
		
		//password config
		lblSenha.setBounds(240, 300, 150, 40);
		lblSenha.setFont(new Font("Serif", Font.PLAIN, 35));
		pn.add(lblSenha);
		
		txtSenha.setBounds(410, 300, 600, 40);
		txtSenha.setFont(new Font("Serif", Font.PLAIN, 35));
		pn.add(txtSenha);
		
		//button login config
		btnLogin.setBounds(130, 450, 200, 60);
		btnLogin.setFont(new Font("Serif", Font.PLAIN, 35));
		pn.add(btnLogin);
		
		//button register config
		btnRegistrar.setBounds(380, 450, 200, 60);
		btnRegistrar.setFont(new Font("Serif", Font.PLAIN, 35));
		pn.add(btnRegistrar);
	
		//button limpar config
		btnLimpar.setBounds(630, 450, 200, 60);
		btnLimpar.setFont(new Font("Serif", Font.PLAIN, 35));
		pn.add(btnLimpar);
		
		//button sair config
		btnSair.setBounds(880, 450, 200, 60);
		btnSair.setFont(new Font("Serif", Font.PLAIN, 35));
		pn.add(btnSair);
		
		//Button controllers
		//Login button references controller class
		btnLogin.addActionListener(new BtnLoginControlador(getfLogin(), txtUser, txtSenha));
		
		//Register button references controller class
		btnRegistrar.addActionListener(new BtnRegistrarControlador(txtUser, txtSenha));
		
		//Sair button 
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//Lipar button
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUser.setText("");
				txtSenha.setText("");
			}
		});
		
	}



	
}
