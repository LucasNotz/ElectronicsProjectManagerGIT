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
import javax.swing.JTextField;

import apresentacao.part.PartMenu;
import negocio.Project;

public class ProjectView {
	private JFrame fPartView = new JFrame();
	private JPanel pn = new JPanel();
	private JLabel lblUser = new JLabel();
	private JLabel lblProject = new JLabel();
	private JMenuBar barraMenu = new JMenuBar();
	private JMenu menuOpcao = new JMenu("Navegar");
	private JMenuItem menuItem = new JMenuItem("Menu Partes");
	private JMenuItem menuItem2 = new JMenuItem("Menu Projetos");
	private JLabel lblOrcamento = new JLabel();
	private JLabel lblDescricao = new JLabel();
	private JLabel lblOrcamentoV = new JLabel();
	private JTextArea aDescricaoV = new JTextArea();
	private JScrollPane jspPreview = new JScrollPane(aDescricaoV,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	public JFrame getJFrame() {
		return fPartView;
	}
	
	public ProjectView(String user, String projectName) throws Exception {
		fPartView.setTitle("Project View");
		fPartView.setSize(1200,800);
		fPartView.setResizable(false);
		fPartView.setDefaultCloseOperation(fPartView.EXIT_ON_CLOSE);
		fPartView.setLocationRelativeTo(null);
	
		fPartView.setJMenuBar(barraMenu);
		barraMenu.add(menuOpcao);
		menuOpcao.add(menuItem); //a ser ativado
		menuOpcao.add(menuItem2); //inativo por agora
		
		pn.setLayout(null);
		pn.setBackground(Color.white);
		fPartView.add(pn);
		
		menuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ProjectMenu(user).fProjectMenu().setVisible(true);
					fPartView.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new PartMenu(user).fPartMenu().setVisible(true);
					fPartView.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		lblProject.setFont(new Font("Serif", Font.BOLD, 40));
		lblProject.setText(projectName);
		lblProject.setBounds(50,50,600,60);
		pn.add(lblProject);
		
		lblOrcamento.setText("Orcamento: $");
		lblOrcamento.setBounds(60, 130, 190,30);
		lblOrcamento.setFont(new Font("Serif", Font.BOLD, 25));
		pn.add(lblOrcamento);
		
		lblDescricao.setText("Descricao: ");
		lblDescricao.setBounds(60, 180, 170,30);
		lblDescricao.setFont(new Font("Serif", Font.BOLD, 25));
		pn.add(lblDescricao);
		
		System.out.println(Project.getProjectInfo(projectName));
		lblOrcamentoV.setText(Project.getProjectInfo(projectName).get(0).toString());
		lblOrcamentoV.setBounds(250, 130, 230,30);
		lblOrcamentoV.setFont(new Font("Serif", Font.BOLD, 25));
		pn.add(lblOrcamentoV);
		
		aDescricaoV.setText(Project.getProjectInfo(projectName).get(1).toString());
		aDescricaoV.setEditable(false);
		jspPreview.setBounds(210, 180,400,100);
		aDescricaoV.setLineWrap(true);
		aDescricaoV.setFont(new Font("Serif", Font.BOLD, 15));
		pn.add(jspPreview);
		
		
	}
}