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
import javax.swing.JTextField;

import apresentacao.part.PartMenu;
import negocio.Part;
import negocio.Project;

public class ProjectMake {
	//Class Variables
	
	//Frame and Panel
	private JFrame fProjectView = new JFrame();
	private JPanel pn = new JPanel();
	
	public JFrame getJFrame() {
		return fProjectView;
	}
	
	//Top areas
	private JLabel lblProject = new JLabel();
	private JMenuBar barraMenu = new JMenuBar();
	private JMenu menuOpcao = new JMenu("Navegar");
	private JMenuItem menuItemPA = new JMenuItem("Menu Partes");
	private JMenuItem menuItemPR = new JMenuItem("Menu Projetos");
	
	//Fixed labels
	private JLabel lblOrcamento = new JLabel("Orcamento: $");
	private JLabel lblDescricao = new JLabel("Descricao: ");
	
	//Text field
	private JTextField titulo = new JTextField();
	private JTextField orcamento = new JTextField();
	
	//Button
	private JButton btnSaveProject = new JButton("Adicionar projeto");
	private JButton btnSaveParts = new JButton("Adicionar partes");
	
	//Description text area
	private JTextArea descricao = new JTextArea();
	private JScrollPane jspPreview = new JScrollPane(descricao,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	//Parts to choose from 
	private DefaultListModel<String> lstModelParts = new DefaultListModel<String>();
	private JList<String> lstParts = new JList<String>();;
	private JScrollPane jspParts = new JScrollPane(lstParts);
	
	//Parts chosen
	private DefaultListModel<String> dlmChosen = new DefaultListModel<String>();
	private JList<String> lstPartsChosen = new JList<String>();
	private JScrollPane jspPartsChosen = new JScrollPane(lstPartsChosen);
	
	//Button
	//private JButton btnCriar = new JButton("Criar");
	
	//Constructor
	@SuppressWarnings("static-access")
	public ProjectMake(String user) throws Exception {
		//Frame configuration
		fProjectView.setTitle("Project Maker");
		fProjectView.setSize(1200,800);
		fProjectView.setResizable(false);
		fProjectView.setDefaultCloseOperation(fProjectView.EXIT_ON_CLOSE);
		fProjectView.setLocationRelativeTo(null);
	
		//Top bar configuration
		fProjectView.setJMenuBar(barraMenu);
		barraMenu.add(menuOpcao);
		menuOpcao.add(menuItemPA); 
		menuOpcao.add(menuItemPR); 
		
		//Panel configuration
		pn.setLayout(null);
		pn.setBackground(Color.white);
		fProjectView.add(pn);
		
		//Project menu navigator
		menuItemPR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ProjectMenu(user).fProjectMenu().setVisible(true);
					fProjectView.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//Part menu navigator
		menuItemPA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new PartMenu(user).fPartMenu().setVisible(true);
					fProjectView.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//Project name configuration
		lblProject.setFont(new Font("Serif", Font.BOLD, 40));
		lblProject.setText("Project Name:");
		lblProject.setBounds(50,50,600,60);
		pn.add(lblProject);
		
		titulo.setBounds(360,60,400,45);
		titulo.setFont(new Font("Comic Sans", Font.BOLD, 30));
		pn.add(titulo);

		//Project budget label configuration
		lblOrcamento.setBounds(60, 130, 190,30);
		lblOrcamento.setFont(new Font("Serif", Font.BOLD, 25));
		pn.add(lblOrcamento);
		
		//Project description label configuration
		lblDescricao.setBounds(60, 180, 170,30);
		lblDescricao.setFont(new Font("Serif", Font.BOLD, 25));
		pn.add(lblDescricao);
		
		//Budget text field
		orcamento.setBounds(250, 130, 230,30);
		orcamento.setFont(new Font("Serif", Font.BOLD, 25));
		pn.add(orcamento);
		
		//Set project description label
		jspPreview.setBounds(210, 180,400,100);
		descricao.setFont(new Font("Serif", Font.BOLD, 15));
		descricao.setLineWrap(true);
		pn.add(jspPreview);
		
		//Creates string that CAN hold all existing parts
		//[name,price,quantity]
		Object[][] chosen = new Object[Part.getAllParts().length][3];
		
		//Show all parts on list
		String[][] data = null;
		try {
			data = Part.getAllParts();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			int i = 0;
			for(i = 0; i < data.length; i++ ) {
				lstModelParts.add(i, data[i][0]);
			}
			for(i = 0; i < lstModelParts.size(); i++ ) {
				chosen[i][0] = data[i][0];
				chosen[i][1] = data[i][1];
				chosen[i][2] = 0;
				dlmChosen.add(i, "Part: " + chosen[i][0]
			    		+ " | Qtd: " + chosen[i][2] );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		lstParts.setModel(lstModelParts);
		lstParts.setFont(new Font("Serif", Font.BOLD, 20));
		jspParts.setBounds(100, 400, 400, 250);
		pn.add(jspParts);
		
		//Chosen parts list
		lstPartsChosen.setModel(dlmChosen);
		lstPartsChosen.setFont(new Font("Serif", Font.BOLD, 20));
		jspPartsChosen.setBounds(600, 400, 400, 250);
		pn.add(jspPartsChosen);
		
		//on double click
		lstParts.addMouseListener(new MouseListener() {
			// Source - https://stackoverflow.com/questions/4051659/identifying-double-click-in-java
			// Posted by kaliatech
			// Retrieved 2025-11-06, License - CC BY-SA 4.0
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1) {
					try {
						String qtdString = JOptionPane.showInputDialog("Quantidade: ");
						
						for (int i = 0; i < lstModelParts.size(); i++) {
							
							if (chosen[lstParts.getSelectedIndex()][0] == chosen[i][0]) {
						    	int qtdExistente = (int) chosen[lstParts.getSelectedIndex()][2];
						    	if (qtdString.contains("[a-zA-Z]+") == false) {
							    	int qtd = Integer.parseInt(qtdString);	
						    		chosen[lstParts.getSelectedIndex()][2] = qtd + qtdExistente;
						    		dlmChosen.removeElementAt(i);;
						    		
									dlmChosen.add(i, "Part: " + chosen[i][0]
								    		+ " | Qtd: " + chosen[i][2] );
						    	} else {
						    		return;
						    	}
							}
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
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
		
		btnSaveProject.setBounds(850,50,200,40);
		btnSaveProject.setFont(new Font("Serif", Font.BOLD, 25));
		pn.add(btnSaveProject);
		
		btnSaveProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Project objProject = new Project();
				objProject.setNome(titulo.getText());
				objProject.setOrcamento(0);
				objProject.setDescricao(descricao.getText());
				objProject.setUser(user);

				try {
					Project.insertProject(objProject);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		btnSaveParts.setBounds(850,260,200,40);
		btnSaveParts.setFont(new Font("Serif", Font.BOLD, 25));
		pn.add(btnSaveParts);
		btnSaveParts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < dlmChosen.getSize(); i++) {
					try {
						Project.insertPart(chosen[i][0], titulo.getText(), chosen[i][2]);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}	
		});
			
	}
}