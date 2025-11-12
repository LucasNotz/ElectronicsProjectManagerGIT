package visual;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import logical.Note;

public class Create {
	//Class attributes
	JFrame frame;
	JPanel panel;
	JTextField textField;
	JTextArea textArea;
	JButton btnCreate; 
	JButton btnLeave;
	
	//Constructor 
	public Create(String username) {
		frame = new JFrame();
		frame.setSize(450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 450, 300);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(158, 12, 114, 25);
		panel.add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(25, 41, 401, 149);
		panel.add(textArea);
		
		btnLeave = new JButton("Leave");
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CRUD crud = new CRUD(username);
				crud.getJFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		btnLeave.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 12));
		btnLeave.setBounds(172, 228, 105, 27);
		panel.add(btnLeave);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Bruh");
					return;
				}
				if (textArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Bruh2");
					return;
				}
				if (Note.doesNoteExist(textField.getText(), username)) {
					JOptionPane.showMessageDialog(null, "Note already exists");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "Note created!");
					Note.createNote(username, textField.getText(), textArea.getText());
					frame.setVisible(false);
				}
			
			}
		});
		btnCreate.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 12));
		btnCreate.setBounds(172, 193, 105, 27);
		panel.add(btnCreate);
		
		frame.add(panel);
	}
	
	//Class methods
	public JFrame getJFrame() {
		return frame;
	}
}
