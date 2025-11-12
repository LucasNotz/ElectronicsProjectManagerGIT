package visual;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.*;

public class Settings {
	//Class attributes
	private JFrame frame;
	private JPanel panel;
	private JLabel lbldatabaseType;
	private JLabel lblDatabaseUser;
	private JLabel lblDatabasePassword;
	private JComboBox<String> cboDatabaseType;
	private JTextField txtDatabaseUser;
	private JPasswordField txtDatabasePassword;
	private JButton btnConnect; 
	
	//Constructor 
	public Settings() {
		frame = new JFrame();
		frame.setSize(450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		panel = new JPanel();
		panel.setBounds(0, 0, 450, 300);
		panel.setLayout(null);
		
		lbldatabaseType = new JLabel("Database type:");
		lbldatabaseType.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		lbldatabaseType.setBounds(70, 20, 120, 25);
		panel.add(lbldatabaseType);
		
		cboDatabaseType = new JComboBox<String>();
		cboDatabaseType.setBounds(190, 20, 120, 25);
		cboDatabaseType.addItem("MySql");
		cboDatabaseType.addItem("MariaDB");
		panel.add(cboDatabaseType);
		
		lblDatabaseUser = new JLabel("Username:");
		lblDatabaseUser.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		lblDatabaseUser.setBounds(70, 70, 80, 25);
		panel.add(lblDatabaseUser);
		
		lblDatabasePassword = new JLabel("Password:");
		lblDatabasePassword.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		lblDatabasePassword.setBounds(70, 110, 80, 25);
		panel.add(lblDatabasePassword);
		
		txtDatabaseUser = new JTextField();
		txtDatabaseUser.setBounds(160, 70, 120, 25);
		panel.add(txtDatabaseUser);
		
		txtDatabasePassword = new JPasswordField();
		txtDatabasePassword.setBounds(160, 110, 120, 25);
		panel.add(txtDatabasePassword);

		btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String databaseType = "";
				if (cboDatabaseType.getSelectedIndex() == 0) {
					databaseType = "mysql";
				} else {
					databaseType = "mariadb";
				}

				Database.setConnectionURl("jdbc:"+ databaseType +"://localhost:3306/note_db?user="+ txtDatabaseUser.getText() +
						"&password=" + txtDatabasePassword.getText() +"&serverTimezone=UTC&useSSL=false");
				
				new Login().getJFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		btnConnect.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 12));
		btnConnect.setBounds(172, 193, 105, 27);
		panel.add(btnConnect);
		
		frame.add(panel);
	}
	
	//Class methods
	public JFrame getJFrame() {
		return frame;
	}
}




