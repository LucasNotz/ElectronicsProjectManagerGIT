package apresentacao.part;

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

import apresentacao.project.ProjectMenu;
import negocio.Project;

public class PartView {
	//Class variables
	
	//Frame and panel
	private JFrame fPartView = new JFrame();
	private JPanel pn = new JPanel();
	
	public JFrame getJFrame() {
		return fPartView;
	}

	//Top menu 
	private JMenuBar barraMenu = new JMenuBar();
	private JMenu menuOpcao = new JMenu("Navegar");
	private JMenuItem menuItem = new JMenuItem("Menu Partes");
	private JMenuItem menuItem2 = new JMenuItem("Menu Projetos");
	
	//Fixed labels
	private JLabel lblPreco = new JLabel();
	private JLabel lblDescricao = new JLabel();
	
	//Variable labels
	private JLabel lblProject = new JLabel();
	private JLabel lblPrecoV = new JLabel();
	private JTextArea aDescricaoV = new JTextArea();
	private JScrollPane jspPreview = new JScrollPane(aDescricaoV,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


	
	public PartView(String user) throws Exception {
		//Frame configuration
		fPartView.setTitle("Project View");
		fPartView.setSize(1200,800);
		fPartView.setResizable(false);
		fPartView.setDefaultCloseOperation(fPartView.EXIT_ON_CLOSE);
		fPartView.setLocationRelativeTo(null);
	
		//Top menu configuration
		fPartView.setJMenuBar(barraMenu);
		barraMenu.add(menuOpcao);
		menuOpcao.add(menuItem); //a ser ativado
		menuOpcao.add(menuItem2); //inativo por agora
		
		//Panel configuration
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
		
		//Project label configuration
		lblProject.setFont(new Font("Serif", Font.BOLD, 40));
		lblProject.setText("fd");
		lblProject.setBounds(50,50,600,60);
		pn.add(lblProject);
		
		//Price label configuration
		lblPreco.setText("Orcamento: $");
		lblPreco.setBounds(60, 130, 190,30);
		lblPreco.setFont(new Font("Serif", Font.BOLD, 25));
		pn.add(lblPreco);
		
		//Description label configuration
		lblDescricao.setText("Descricao: ");
		lblDescricao.setBounds(60, 180, 170,30);
		lblDescricao.setFont(new Font("Serif", Font.BOLD, 25));
		pn.add(lblDescricao);
		
		//Set price text configuration
		lblPrecoV.setText("df");
		lblPrecoV.setBounds(250, 130, 230,30);
		lblPrecoV.setFont(new Font("Serif", Font.BOLD, 25));
		pn.add(lblPrecoV);
		
		aDescricaoV.setText("ds");
		aDescricaoV.setEditable(false);
		jspPreview.setBounds(210, 180,400,100);
		aDescricaoV.setLineWrap(true);
		aDescricaoV.setFont(new Font("Serif", Font.BOLD, 15));
		pn.add(jspPreview);
		
		
	}
}
	

