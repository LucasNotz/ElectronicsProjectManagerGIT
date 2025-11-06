package apresentacao.project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import apresentacao.part.PartMenu;
import negocio.Project;

public class ProjectMenu {
	//Class Variables
	
	//Frame and panel
	private JFrame fProjectMenu = new JFrame();
	private JPanel pn = new JPanel();
	
	//Log user variables
	private JLabel lblTitulo = new JLabel("Logged in as: ");
	private JLabel lblTituloUser = new JLabel("");
	
	//Menu variables
	private JMenuBar barraMenu = new JMenuBar();
	private JMenu menuOpcao = new JMenu("Navegar");
	private JMenuItem menuItem = new JMenuItem("Menu Partes");
	private JMenuItem menuItem2 = new JMenuItem("Menu Projetos");
	
	//Menu display variables
	private JLabel lblProjeto = new JLabel("Projetos");
	private DefaultListModel<String> dlm2 = new DefaultListModel<String>();
	private JList<String> listProjects = new JList<String>();
	private JScrollPane jspProjects = new JScrollPane(listProjects);
	
	//Button variables
	private JButton btnVer = new JButton("Ver");
	private JButton btnAdicionar = new JButton("Adicionar");
	private JButton btnMudar = new JButton("Alterar");
	private JButton btnRemover = new JButton("Remover");
	
	//Preview variables
	private JLabel lblPreview = new JLabel("Preview");
	private JTextArea aPreview = new JTextArea();
	private JScrollPane jspPreview = new JScrollPane(aPreview, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	//Constructor
	@SuppressWarnings("static-access")
	public ProjectMenu(String user) throws Exception {
		//Frame configuration
		fProjectMenu.setTitle("Menu Projetos");
		fProjectMenu.setSize(1200,800);
		fProjectMenu.setResizable(false);
		fProjectMenu.setDefaultCloseOperation(fProjectMenu.EXIT_ON_CLOSE);
		fProjectMenu.setLocationRelativeTo(null);
	
		//Menu configuration
		fProjectMenu.setJMenuBar(barraMenu);
		barraMenu.add(menuOpcao);
		menuOpcao.add(menuItem); 
		menuOpcao.add(menuItem2); 
		
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PartMenu(user).fPartMenu().setVisible(true);
				fProjectMenu.setVisible(false);
			}
		});
		
		//Panel configuration
		pn.setLayout(null);
		pn.setBackground(Color.white);
		fProjectMenu.add(pn);
		
		//Title configuration
		lblTitulo.setBounds(320,40,300,65);
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 40));
		pn.add(lblTitulo);
		
		//Title user configuration
		lblTituloUser.setBounds(620,40,500,65);
		lblTituloUser.setFont(new Font("Comic Sans", Font.BOLD, 40));
		pn.add(lblTituloUser);
		lblTituloUser.setText(user);
		System.out.println("Current user (ProjMenu): " + user);
		
		//Project display configuration
		lblProjeto.setBounds(150, 150, 200, 45);
		lblProjeto.setFont(new Font("Serif", Font.ITALIC, 30));
		pn.add(lblProjeto);
		
		//Display all projects of user configuration
		jspProjects.setBounds(150,210,900,200);
		listProjects.setFont(new Font("Serif", Font.BOLD, 20));
		listProjects.setModel(dlm2);
		pn.add(jspProjects);
		for (int i = 0; i < Project.getProjectSize(user); i++) {
				dlm2.add(i, "NAME: " + Project.select(user, i, 0));

		}
		
		//View button configuration
		btnVer.setBounds(150,420,200,60);
		btnVer.setFont(new Font("Serif", Font.PLAIN, 25));
		pn.add(btnVer);
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listProjects.getSelectedIndex() == -1) {
					return;
				}
				try {
					new ProjectView(user, dlm2.getElementAt(listProjects.getSelectedIndex()).substring(5)).getJFrame().setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				fProjectMenu.setVisible(false);
			}
		});
		
		//Add button configuration
		btnAdicionar.setBounds(380,420,200,60);
		btnAdicionar.setFont(new Font("Serif", Font.PLAIN, 25));
		pn.add(btnAdicionar);
		
		//Alter button configuration
		btnMudar.setBounds(620,420,200,60);
		btnMudar.setFont(new Font("Serif", Font.PLAIN, 25));
		pn.add(btnMudar);
		
		//Remove button configuration
		btnRemover.setBounds(850,420,200,60);
		btnRemover.setFont(new Font("Serif", Font.PLAIN, 25));
		pn.add(btnRemover);
		
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Don't do anything if no project is selected
				if (listProjects.getSelectedIndex() == -1){
					return;
				} else {
					//Confirmation to remove
					int confirm = JOptionPane.showConfirmDialog(null, "Deletar projeto?", "Confimrar", JOptionPane.YES_NO_OPTION);
					if (confirm == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, "Projeto deletado");
						try {
							Project.deleteProjeto(dlm2.getElementAt(listProjects.getSelectedIndex()).substring(5));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						//Reload all elements 
						dlm2.removeAllElements();
						try {
							for (int i = 0; i < Project.getProjectSize(user); i++) {
								dlm2.add(i, "NAME: " + Project.select(user, i, 0));
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else if (confirm == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(null, "Processo cancelado");
					}
				}

			}
		});
		
		//Preview display configuration
		lblPreview.setBounds(150,500, 300,40);
		lblPreview.setFont(new Font("Serif", Font.ITALIC, 30));
		pn.add(lblPreview);
		
		//Project preview display
		jspPreview.setBounds(150,550,900,150);
		pn.add(jspPreview);

		//Display preview of chosen project
		listProjects.setSelectedIndex(0);
		aPreview.setLineWrap(true);
		aPreview.setEditable(false);
		//Show on click
		listProjects.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				System.out.println(listProjects.getSelectedIndex());
				try {
					aPreview.setText(Project.select(user,listProjects.getSelectedIndex(), 2));
					aPreview.setFont(new Font("Serif", Font.ITALIC, 20));
					} catch (Exception e1) {
					e1.printStackTrace();
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

	}

	//Class access functions
	public JFrame fProjectMenu() {
		return fProjectMenu;
	}

	public void setfProjectMenu(JFrame fProjectMenu) {
		this.fProjectMenu = fProjectMenu;
	}
}
