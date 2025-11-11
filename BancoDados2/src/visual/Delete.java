package visual;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import logical.Note;

public class Delete {
	//Class attributes
	private JFrame frame;
	private JPanel panel;
	private JList<String> list;
	private JScrollPane scroll;
	private DefaultListModel<String> dlm;
	
	//Constructor
	public Delete(String username) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 450, 300);
		panel.setLayout(null);
		
		list = new JList<>();
		dlm = new DefaultListModel<String>();
		list.setModel(dlm);
		dlm.addAll(Note.getAllNoteInfo(username).keySet());
		list.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selectedTitle = list.getSelectedValue();
				int confirmDeleteNote = JOptionPane.showConfirmDialog(null, "Delete note?", "Confirm", JOptionPane.YES_NO_OPTION);
				if (confirmDeleteNote == JOptionPane.YES_OPTION) {
					Note.deleteNote(username, selectedTitle);
					JOptionPane.showMessageDialog(null, "Note deleted");
					frame.setVisible(false);
					return;
				} else {
					frame.setVisible(false);
				}
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
		});
		
		scroll = new JScrollPane(list);
		scroll.setBounds(142, 25, 157, 202);
		panel.add(scroll);
		
		frame.add(panel);
	}
	
	//Class methods
	public JFrame getJFrame() {
		return frame;
	}
}