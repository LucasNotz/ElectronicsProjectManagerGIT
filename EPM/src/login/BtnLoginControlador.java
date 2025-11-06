package login;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import apresentacao.project.ProjectMenu;
import negocio.User;

public class BtnLoginControlador implements ActionListener {
	//Class variables
	private JFrame fLogin = null; 
	private JLabel lblLoginRegisterStatus = null;
	private JTextField txtUser = null;
	private JTextField txtSenha = null;
	
	//Variables to be used for login
	static String userBuscado = "";
	private String senhaBuscada = "";
	
	//Constructor 
	public BtnLoginControlador(JFrame fLogin, JTextField txtUser, JTextField txtSenha, JLabel lblLoginRegisterStatus) {
		this.fLogin = fLogin;
		this.lblLoginRegisterStatus = lblLoginRegisterStatus;
		this.txtUser = txtUser;
		this.txtSenha = txtSenha;
	}
	
	public void actionPerformed(ActionEvent e) {
		//Data critique
		try {
			if(txtUser.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "insira um username");
				return;
			}
			if(txtSenha.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "insira uma senha");
				return;
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, erro);
		}
		
		//Check if user exists
		try {
			//if returns "" then login does not exist
			//if it returns itself the if matches and login exists
			userBuscado = User.resgatar(txtUser.getText(),0);
			senhaBuscada = User.resgatar(txtUser.getText(), 1);
			
			if(userBuscado.equals(txtUser.getText())) {
				//if equal and case equal (sql queries case are case insensitive)
				//System.out.println("user match");
				if(senhaBuscada.equals(txtSenha.getText())) {
					System.out.println("password match, login success");
					//show next frame
					ProjectMenu prMenuGUI = new ProjectMenu(userBuscado);
					prMenuGUI.fProjectMenu().setVisible(true);
					//hide login
					fLogin.dispose();
				} else {
					lblLoginRegisterStatus.setText("Senha incorreta");
					lblLoginRegisterStatus.setForeground(Color.RED);
				}
				
			} else {
				//if not equal and/or cases not equal
				lblLoginRegisterStatus.setText("User inexistente");
				lblLoginRegisterStatus.setForeground(Color.RED);
				return;
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(1);
		}
	}
}
