import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import negocio.User;

/**
 *  Esse é a classe controlador do butao de registrar
 *   - Overview da classe
 *  	
 *  Propriedades
 *  
 *  Propriedade a ser buscada
 *  
 *  Construtor
 *  
 *  actionPerformed()
 *  	
 *  	critica de dados
 *  
 *  	verificar existencia do usuario a ser registrado
 *  
 *  	regatar senha digitada
 *  
 *  	pedir confirmacao
 *  
 */

public class BtnRegistrarControlador implements ActionListener {

	//propriedade da classe
	private JTextField txtUser = null;
	private JTextField txtSenha = null;
	
	private String userBuscado = "";

	
	//construtor
	public BtnRegistrarControlador(JTextField txtUser, JTextField txtSenha) {
		this.txtUser = txtUser;
		this.txtSenha = txtSenha;
	}
	
	public void actionPerformed(ActionEvent e) {
		//crítica de dados
		if(txtUser.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "campo usuario obrigatorio");
			return;
		}
		
		if(txtSenha.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "campo senha obrigatorio");
			return;
		}
		
		//verificar se ja existe usuário com esse nome
		try {
			//if returns "" then login does not exist -- in this case this is what i want
			//if it returns itself the if matches and login exists -- not what i want
			//considerar que todas as queries passam para o sql como minuscula
			// ou seja só existe uma variacao para cada conjunto de caracters
			// ABC -> abc | aBC -> abc |
			userBuscado = User.resgatar(txtUser.getText(),0);
			
			//System.out.println(userBuscado);
			//System.out.println(txtUser.getText());
			
			//explicacao acima
			if(userBuscado.equals("")) {
				
				int confirm = JOptionPane.showConfirmDialog(null, "Registrar usuário?", "Confimrar", JOptionPane.YES_NO_OPTION);
				
				//pedido de confirmacao
				if (confirm == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Usuário criado");
					//inserir no banco de dados
					User objUser = new User();
					objUser.setUsername(txtUser.getText());
					objUser.setPassword(txtSenha.getText());
					objUser.persistir();
					
					
				} else if (confirm == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(null, "Processo cancelado");
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
