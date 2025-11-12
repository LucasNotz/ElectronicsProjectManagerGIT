package visual;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import logical.Note;

public class CRUD {
	//Class attributes
	private JFrame frame;
	private JPanel panel;
	private JLabel lblTitle;
	private JTextArea textArea;
	private JButton btnCreate;
	private JButton btnRead;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel lblCrud;
	
	//Class constructor
	public CRUD(String username) {
		frame = new JFrame();
		frame.setSize(450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 450, 300);
		panel.setLayout(null);
		
		lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		lblTitle.setBounds(93, 15, 160, 17);
		frame.getContentPane().add(lblTitle);
		
		textArea = new JTextArea();
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setBounds(26, 44, 185, 198);
		panel.add(textArea);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Create create = new Create(username);
				create.getJFrame().setVisible(true);
			}
		});
		btnCreate.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		btnCreate.setBounds(277, 59, 105, 27);
		panel.add(btnCreate);
		
		btnRead = new JButton("Read");
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Read read = new Read(username, textArea, lblTitle);
				read.getJFrame().setVisible(true);
			}
		});
		btnRead.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		btnRead.setBounds(277, 98, 105, 27);
		panel.add(btnRead);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(String titleInUse : Note.getAllNoteInfo(username).keySet()) {
					System.out.println(titleInUse + "   " + lblTitle.getText());
					if (lblTitle.getText().equals(titleInUse)) {
						int confirmUpdateNote = JOptionPane.showConfirmDialog(null, "Update note?", "Confirm", JOptionPane.YES_NO_OPTION);
						if (confirmUpdateNote == JOptionPane.YES_OPTION) {
							Note.alterNote(username, lblTitle.getText(), textArea.getText());
							JOptionPane.showMessageDialog(null, "Note updated");
							return;
						} else {
							JOptionPane.showMessageDialog(null, "Update cancelled");
							return;
						}
					
					} 
				}
				JOptionPane.showMessageDialog(null, "Select a valid note");
			}
		});
		btnUpdate.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		btnUpdate.setBounds(277, 137, 105, 27);
		panel.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete delete = new Delete(username);
				delete.getJFrame().setVisible(true);
			}
		});
		btnDelete.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		btnDelete.setBounds(277, 176, 105, 27);
		panel.add(btnDelete);
		
		lblCrud = new JLabel("CRUD");
		lblCrud.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 20));
		lblCrud.setBounds(305, 25, 60, 17);
		panel.add(lblCrud);
		
		frame.add(panel);
	}
	
	//Class methods
	public JFrame getJFrame() {
		return frame;
	}
	
}
