package login;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import negocio.User;

public class BtnRegistrarControlador implements ActionListener {
	//Class variables
	private JTextField txtUser = null;
	private JTextField txtSenha = null;
	private JLabel lblLoginRegisterStatus = null;
	private String userBuscado = "";

	//Constructor function
	public BtnRegistrarControlador(JTextField txtUser, JTextField txtSenha, JLabel lblLoginRegisterStatus) {
		this.txtUser = txtUser;
		this.txtSenha = txtSenha;
		this.lblLoginRegisterStatus = lblLoginRegisterStatus;
	}
	
	public void actionPerformed(ActionEvent e) {
		//Data critique
		if(txtUser.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "campo usuario obrigatorio");
			return;
		}
		
		if(txtSenha.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "campo senha obrigatorio");
			return;
		}
		
		//Check if user name exists
		try {
			//if returns "" then login does not exist -- in this case this is what i want
			//if it returns itself the if matches and login exists -- not what i want
			//considerar que todas as queries passam para o sql como minuscula
			// ou seja só existe uma variacao para cada conjunto de caracters
			// ABC -> abc | aBC -> abc |
			userBuscado = User.resgatar(txtUser.getText(),0);

			if(userBuscado.equals("")) {
				
				int confirm = JOptionPane.showConfirmDialog(null, "Registrar usuário?", "Confimrar", JOptionPane.YES_NO_OPTION);
				
				//Ask for confirmation
				if (confirm == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Usuário criado");
					
					//Insert new user into database
					User objUser = new User();
					objUser.setUsername(txtUser.getText());
					objUser.setPassword(txtSenha.getText());
					objUser.persistir();
					lblLoginRegisterStatus.setText("Usuário novo criado");
					lblLoginRegisterStatus.setForeground(Color.GREEN);
	
				} else if (confirm == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(null, "Processo cancelado");
					lblLoginRegisterStatus.setText("");
				}
			
			} else {
				//if not equal and/or cases not equal
				lblLoginRegisterStatus.setText("Usuário já existe");
				lblLoginRegisterStatus.setForeground(Color.RED);
				return;
			}

		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(1);
		}
	}
}
