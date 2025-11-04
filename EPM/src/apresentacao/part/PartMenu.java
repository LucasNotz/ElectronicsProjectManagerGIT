package apresentacao.part;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import apresentacao.project.ProjectMenu;
import negocio.Part;
//copiei e colei pq tinha collocado um elemento errado mas decidi deixar mas nao via ser assim
public class PartMenu {
	//propriedades da classe
	private JFrame fPartMenu = new JFrame();
	private JPanel pn = new JPanel();
	private JLabel lblTitulo = new JLabel("Logged in as: ");
	private JLabel lblTituloUser = new JLabel("Temporary");
	private JLabel lblProjeto = new JLabel("Partes");
	private JMenuBar barraMenu = new JMenuBar();
	private JMenu menuOpcao = new JMenu("Navegar");
	private JMenuItem menuItem = new JMenuItem("Menu Partes");
	private JMenuItem menuItem2 = new JMenuItem("Menu Projetos");
	private DefaultListModel<String> lstModelParts = new DefaultListModel<String>();
	private JList<String> lstParts = new JList<String>();;
	private JScrollPane jspParts = new JScrollPane(lstParts);
	private JLabel lblPreview = new JLabel("Preview");
	private JTextArea aPreview = new JTextArea();
	private JScrollPane jspPreview = new JScrollPane(aPreview);
	

	
	public PartMenu(String user) {
		//frame config
		fPartMenu.setTitle("Menu Projetos");
		fPartMenu.setSize(1200,800);
		fPartMenu.setResizable(false);
		fPartMenu.setDefaultCloseOperation(fPartMenu.EXIT_ON_CLOSE);
		fPartMenu.setLocationRelativeTo(null);
	
		fPartMenu.setJMenuBar(barraMenu);
		barraMenu.add(menuOpcao);
		menuOpcao.add(menuItem); //a ser ativado
		menuOpcao.add(menuItem2); //inativo por agora
		
		menuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ProjectMenu(user).fProjectMenu().setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				fPartMenu.setVisible(false);
			}
		});
		
		pn.setLayout(null);
		pn.setBackground(Color.white);
		fPartMenu.add(pn);
		
		lblTitulo.setBounds(320,40,300,65);
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 40));
		pn.add(lblTitulo);
		
		lblTituloUser.setText(user);
		lblTituloUser.setBounds(620,40,500,65);
		lblTituloUser.setFont(new Font("Serif", Font.BOLD, 40));
		pn.add(lblTituloUser);
		
		lblProjeto.setBounds(150, 150, 200, 45);
		lblProjeto.setFont(new Font("Serif", Font.PLAIN, 30));
		pn.add(lblProjeto);		
		
		String[][] data = null;
		try {
			data = Part.getAllParts();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			int i = 0;
			for(i = 0; i < data.length; i++ ) {

				lstModelParts.add(i, data[i][0]);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		lstParts.setModel(lstModelParts);
		lstParts.setFont(new Font("Serif", Font.BOLD, 20));
		jspParts.setBounds(150, 200, 400, 250);
		pn.add(jspParts);
		
		lblPreview.setBounds(150,500, 300,40);
		lblPreview.setFont(new Font("Serif", Font.ITALIC, 30));
		pn.add(lblPreview);
		
		aPreview.setLineWrap(true);
		jspPreview.setBounds(150,550,900,150);
		pn.add(jspPreview);
		

	}

	//métodos de accesso
	public JFrame fPartMenu() {
		return fPartMenu;
	}

	public void setfPartMenu(JFrame fPartMenu) {
		this.fPartMenu = fPartMenu;
	}
}

