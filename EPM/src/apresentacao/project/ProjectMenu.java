package apresentacao.project;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import negocio.Part;

public class ProjectMenu {
	
	//propriedades da classe
	private JFrame fProjectMenu = new JFrame();
	private JPanel pn = new JPanel();
	private JLabel lblTitulo = new JLabel("Logged in as: ");
	private JLabel lblTituloUser = new JLabel("Temporary");
	private JLabel lblProjeto = new JLabel("Projetos");
	private JMenuBar barraMenu = new JMenuBar();
	private JMenu menuOpcao = new JMenu("Navegar");
	private JMenuItem menuItem = new JMenuItem("Menu Partes");
	private JMenuItem menuItem2 = new JMenuItem("Menu Projetos");
	private DefaultListModel<String> lstModelParts = new DefaultListModel<String>();
	private JList<String> lstParts = null;
	

	
	public ProjectMenu() {
		//frame config
		fProjectMenu.setTitle("Menu Projetos");
		fProjectMenu.setSize(1200,800);
		fProjectMenu.setResizable(false);
		fProjectMenu.setDefaultCloseOperation(fProjectMenu.EXIT_ON_CLOSE);
		fProjectMenu.setLocationRelativeTo(null);
	
		fProjectMenu.setJMenuBar(barraMenu);
		barraMenu.add(menuOpcao);
		menuOpcao.add(menuItem); //a ser ativado
		menuOpcao.add(menuItem2); //inativo por agora
		
		pn.setLayout(null);
		pn.setBackground(Color.white);
		fProjectMenu.add(pn);
		
		lblTitulo.setBounds(320,40,300,65);
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 40));
		pn.add(lblTitulo);
		
		lblTituloUser.setBounds(620,40,500,65);
		lblTituloUser.setFont(new Font("Serif", Font.BOLD, 40));
		pn.add(lblTituloUser);
		
		lblProjeto.setBounds(150, 150, 200, 45);
		lblProjeto.setFont(new Font("Serif", Font.PLAIN, 30));
		pn.add(lblProjeto);
		
		
		
		
	}

	//métodos de accesso
	public JFrame fProjectMenu() {
		return fProjectMenu;
	}

	public void setfProjectMenu(JFrame fProjectMenu) {
		this.fProjectMenu = fProjectMenu;
	}
}
