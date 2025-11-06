package apresentacao.project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import apresentacao.part.PartMenu;
import negocio.Project;

public class ProjectView {
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
	
	//Value labels
	private JLabel orcamento = new JLabel();
	private JTextArea descricao = new JTextArea();
	
	//Preview
	private JScrollPane jspPreview = new JScrollPane(descricao,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	//Constructor
	@SuppressWarnings("static-access")
	public ProjectView(String user, String projectName) throws Exception {
		//Frame configuration
		fProjectView.setTitle("Project View");
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
		lblProject.setText(projectName);
		lblProject.setBounds(50,50,600,60);
		pn.add(lblProject);
		
		//Project budget label configuration
		lblOrcamento.setBounds(60, 130, 190,30);
		lblOrcamento.setFont(new Font("Serif", Font.BOLD, 25));
		pn.add(lblOrcamento);
		
		//Project description label configuration
		lblDescricao.setBounds(60, 180, 170,30);
		lblDescricao.setFont(new Font("Serif", Font.BOLD, 25));
		pn.add(lblDescricao);
		
		//control mechanism
		System.out.println("Project View:");
		System.out.println("Project budget: " + Project.getProjectInfo(projectName).get(0).toString());
		System.out.println("Project description: " + Project.getProjectInfo(projectName).get(1).toString());
		
		//Set project budget label
		orcamento.setText(Project.getProjectInfo(projectName).get(0).toString());
		orcamento.setBounds(250, 130, 230,30);
		orcamento.setFont(new Font("Serif", Font.BOLD, 25));
		pn.add(orcamento);
		
		//Set project description label
		descricao.setText(Project.getProjectInfo(projectName).get(1).toString());
		descricao.setEditable(false);
		jspPreview.setBounds(210, 180,400,100);
		descricao.setLineWrap(true);
		descricao.setFont(new Font("Serif", Font.BOLD, 15));
		pn.add(jspPreview);
		
	}
}