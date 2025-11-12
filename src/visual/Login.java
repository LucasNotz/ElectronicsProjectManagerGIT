package visual;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import logical.User;

public class Login {
	//Class Attributes
	private JFrame frame;
	private JPanel panel;
	private JLabel lblWelcome;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private DefaultListModel<String> dlm;
	private JList<String> listUsers;
	private JScrollPane scrollUsers;
	private JLabel lblPastUsers;
	private JButton btnLogin; 
	private JButton btnRegister;
	
	//Constructor
	public Login() {
		frame = new JFrame();
		frame.setSize(450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 450, 300);
		panel.setLayout(null);
		
		lblWelcome = new JLabel("Welcome");
		lblWelcome.setFont(new Font("DejaVu Sans", Font.BOLD, 25));
		lblWelcome.setBounds(150, 0, 140, 60);
		panel.add(lblWelcome);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		lblUsername.setBounds(70, 70, 80, 25);
		panel.add(lblUsername);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		lblPassword.setBounds(70, 110, 80, 25);
		panel.add(lblPassword);
		
		usernameField = new JTextField();
		usernameField.setBounds(160, 70, 120, 25);
		panel.add(usernameField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(160, 110, 120, 25);
		panel.add(passwordField);
		
		dlm = new DefaultListModel<String>();
		listUsers = new JList<String>();
		listUsers.setModel(dlm);
		int control = 0;
		for (String username : User.getAllUsers()) {
			dlm.add(control, username);
			control++;
		}
		scrollUsers = new JScrollPane(listUsers);
		scrollUsers.setBounds(320, 70, 100, 160);
		panel.add(scrollUsers);
		
		lblPastUsers = new JLabel("Past Users");
		lblPastUsers.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		lblPastUsers.setBounds(320, 40, 90, 25);
		panel.add(lblPastUsers);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				//Data check
				if (usernameField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Didn't know you were chill like that");
					return;
				}
				if (passwordField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "You be profen?");
					return;
				}
				
				LinkedList<String> userCredentials = User.getUserCredentials(usernameField.getText());
				if (userCredentials == null) {
					JOptionPane.showMessageDialog(null, "You trippin' dog");
				}
				String testUsername = userCredentials.get(0);
				String testPassword = userCredentials.get(1);
				
				if (!usernameField.getText().equals(testUsername)) {
					JOptionPane.showMessageDialog(null, "God just be makin' people huh");
					return;
				} else {
					if (!passwordField.getText().equals(testPassword)) {
						JOptionPane.showMessageDialog(null, "Bro this hes slick");
						return;
					} else {
						CRUD crud = new CRUD(usernameField.getText());
						crud.getJFrame().setVisible(true);
						frame.setVisible(false);
					}
				}
				
			}
		});
		btnLogin.setFont(new Font("Liberation Mono", Font.BOLD, 14));
		btnLogin.setBounds(60, 170, 100, 30);
		panel.add(btnLogin);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			@SuppressWarnings({ "deprecation" })
			public void actionPerformed(ActionEvent e) {
				//Data check
				if (usernameField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Didn't know you were chill like that");
					return;
				}
				if (passwordField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "You be profen?");
					return;
				}
				if (User.doesUserExist(usernameField.getText())) {
					JOptionPane.showMessageDialog(null, "U need to leave");
					return;
				} else {
					User.createUser(usernameField.getText(), passwordField.getText());
					JOptionPane.showMessageDialog(null, "U exist now - God circa 2025");
				}
			}
		});
		btnRegister.setFont(new Font("Liberation Mono", Font.BOLD, 14));
		btnRegister.setBounds(170, 170, 100, 30);
		panel.add(btnRegister);
		
		frame.add(panel);

		
	}
	
	//Class methods
	public JFrame getJFrame() {
		return frame;
	}
}
