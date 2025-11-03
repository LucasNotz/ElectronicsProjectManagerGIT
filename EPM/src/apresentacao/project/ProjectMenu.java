package apresentacao.project;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import negocio.Project;

public class ProjectMenu {
	
	private String user = "";
	//propriedades da classe
	private JFrame fProjectMenu = new JFrame();
	private JPanel pn = new JPanel();
	private JLabel lblTitulo = new JLabel("Logged in as: ");
	private JLabel lblTituloUser = new JLabel("");
	private JLabel lblProjeto = new JLabel("Projetos");
	private JMenuBar barraMenu = new JMenuBar();
	private JMenu menuOpcao = new JMenu("Navegar");
	private JMenuItem menuItem = new JMenuItem("Menu Partes");
	private JMenuItem menuItem2 = new JMenuItem("Menu Projetos");
	private DefaultListModel<String> dlm2 = new DefaultListModel<String>();
	private JList<String> listProjects = new JList<String>();
	private JScrollPane jspProjects = new JScrollPane(listProjects);
	private JButton btnVer = new JButton("Ver");
	private JButton btnAdicionar = new JButton("Adicionar");
	private JButton btnMudar = new JButton("Alterar");
	private JButton btnRemover = new JButton("Remover");
	private JLabel lblPreview = new JLabel("Preview");
	private JTextArea aPreview = new JTextArea();
	
	

	public ProjectMenu(String user) throws Exception {
		//frame config
		this.user = user;
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
		
		//titulo config
		lblTitulo.setBounds(320,40,300,65);
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 40));
		pn.add(lblTitulo);
		
		lblTituloUser.setBounds(620,40,500,65);
		lblTituloUser.setFont(new Font("Comic Sans", Font.BOLD, 40));
		pn.add(lblTituloUser);
		lblTituloUser.setText(user);
		System.out.println("Current user(ProjMenu)" + user);
		System.out.println("-----");
		
		//projects display config
		lblProjeto.setBounds(150, 150, 200, 45);
		lblProjeto.setFont(new Font("Serif", Font.ITALIC, 30));
		pn.add(lblProjeto);
		
		jspProjects.setBounds(150,200,900,200);
		listProjects.setModel(dlm2);
		pn.add(jspProjects);
		int k = 0;
		for (int i = 0; i < Project.getProjectSizes(); i++) {
			for (int j = 0; j < 5; j ++) {
				dlm2.add(k, Project.select(i, j));
				k++;
			}
		}
		
		//buttons config
		btnVer.setBounds(150,420,200,60);
		btnVer.setFont(new Font("Serif", Font.PLAIN, 25));
		pn.add(btnVer);
		
		btnAdicionar.setBounds(380,420,200,60);
		btnAdicionar.setFont(new Font("Serif", Font.PLAIN, 25));
		pn.add(btnAdicionar);
		
		btnMudar.setBounds(620,420,200,60);
		btnMudar.setFont(new Font("Serif", Font.PLAIN, 25));
		pn.add(btnMudar);
		
		btnRemover.setBounds(850,420,200,60);
		btnRemover.setFont(new Font("Serif", Font.PLAIN, 25));
		pn.add(btnRemover);
		
		//preview display config
		lblPreview.setBounds(150,500, 300,40);
		lblPreview.setFont(new Font("Serif", Font.ITALIC, 30));
		pn.add(lblPreview);
		
		aPreview.setBounds(150,200,900,200);
		pn.add(aPreview);
		
		
		
		
	}

	//métodos de accesso
	public JFrame fProjectMenu() {
		return fProjectMenu;
	}

	public void setfProjectMenu(JFrame fProjectMenu) {
		this.fProjectMenu = fProjectMenu;
	}
}
