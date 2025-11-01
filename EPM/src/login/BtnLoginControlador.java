package login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import apresentacao.project.ProjectMenu;
import negocio.User;

/**
 * Essa é a classe controlador do butao de login
 * 	- Overview da classe
 * 
 *  Propriedades
 *  
 *  Propriedade (a ser buscado do BD)
 *  
 *  Construtor
 *  
 *  actionPerformed()
 *  	
 *  	Critica de dados
 *  		
 *  		campo vazio user
 *  
 *  		campo vazio senha
 *  
 *  	resgatar propriedades do BD
 *  
 *  		Verificar se username existe/está correto
 *  
 *  			Verificar se senha coincide com a registrada
 *  
 *  !Na verificacao do username, a pesquisa no sql é case insensitive, 
 *  !ou seja, ASU AsU asU asu todos funcionam como um usuário, ou seja 
 *  !uma vez que um conjunto de caracteres seja escolhi para o usuário
 *  !esse conjunto não funciona mais. EX: usuario abc -> toda quere no 
 *  !sistema para para o sql como abc, e no BD tem "abc" registrado, 
 *  !entao se alguem tentar registrar ABC, o quere vai como abc e
 *  !retorna que ja existe esse usuário
 *  !--------------------------------------------
 *  !O query da senha funciona com base no username entao esse problema
 *  !nao existe para a senha
 *  
 */

public class BtnLoginControlador implements ActionListener {
	
	//propriedades da classe
	JFrame fLogin = null; //referenciado para pode descartar o frame mais a frente
	JTextField txtUser = null;
	JTextField txtSenha = null;
	
	//informacoes a serem buscadas
	String userBuscado = "";
	String senhaBuscada = "";
	
	//método construtor cheio
	public BtnLoginControlador(JFrame fLogin, JTextField txtUser, JTextField txtSenha) {
		this.fLogin = fLogin;
		this.txtUser = txtUser;
		this.txtSenha = txtSenha;
	}
	
	//ao clicar o botao:
	public void actionPerformed(ActionEvent e) {
		//crítica de dado
		try {
			//campo vazio
			if(txtUser.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "insira um username");
				return;
			}
			//campo vazio
			if(txtSenha.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "insira uma senha");
				return;
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, erro);
		}
		
		//verificar existencia de um login
		try {
			//if returns "" then login does not exist
			//if it returns itself the if matches and login exists
			userBuscado = User.resgatar(txtUser.getText(),0);
			senhaBuscada = User.resgatar(txtUser.getText(), 1);
			
			if(userBuscado.equals(txtUser.getText())) {
				//if equal and case equal (sql queries case are case insensitive)
				//System.out.println("user match");
				if(senhaBuscada.equals(txtSenha.getText())) {
					System.out.println("password match");
					//show next frame
					ProjectMenu prMenuGUI = new ProjectMenu();
					prMenuGUI.fProjectMenu().setVisible(true);
					//hide login
					fLogin.dispose();
				}
				
			} else {
				//if not equal and/or cases not equal
				return;
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(1);
		}

		
	}

}
