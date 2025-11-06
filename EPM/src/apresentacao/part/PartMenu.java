package apresentacao.part;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import apresentacao.project.ProjectMenu;
import negocio.Part;
public class PartMenu {
	//Class variables
	
	//Frame and panel
	private static JFrame fPartMenu = new JFrame(); 
	private JPanel pn = new JPanel();
	
	//User
	private JLabel lblTituloUser = new JLabel("Temporary");
	
	//Fixed labels
	private JLabel lblTitulo = new JLabel("Logged in as: ");
	private JLabel lblPart= new JLabel("Partes");
	
	//Top menu 
	private JMenuBar barraMenu = new JMenuBar();
	private JMenu menuOpcao = new JMenu("Navegar");
	private JMenuItem menuItemPa = new JMenuItem("Menu Partes");
	private JMenuItem menuItemPr = new JMenuItem("Menu Projetos");
	
	//Parts
	private DefaultListModel<String> lstModelParts = new DefaultListModel<String>();
	private JList<String> lstParts = new JList<String>();;
	private JScrollPane jspParts = new JScrollPane(lstParts);
	
	//Part preview
	private JLabel lblPreview = new JLabel("Preview");
	private JTextArea aPreview = new JTextArea();
	private JScrollPane jspPreview = new JScrollPane(aPreview, 
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	//Button variables
	private JButton btnVer = new JButton("Ver");
	private JButton btnAdicionar = new JButton("Adicionar");
	private JButton btnMudar = new JButton("Alterar");
	private JButton btnRemover = new JButton("Remover");
	
	@SuppressWarnings({ "static-access" })
	public PartMenu(String user) {
		//Frame configuration
		fPartMenu.setTitle("Menu Projetos");
		fPartMenu.setSize(1200,800);
		fPartMenu.setResizable(false);
		fPartMenu.setDefaultCloseOperation(fPartMenu.EXIT_ON_CLOSE);
		fPartMenu.setLocationRelativeTo(null);
	
		//Top menu configuration
		fPartMenu.setJMenuBar(barraMenu);
		barraMenu.add(menuOpcao);
		menuOpcao.add(menuItemPa); 
		menuOpcao.add(menuItemPr); 
		
		menuItemPr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ProjectMenu(user).fProjectMenu().setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				fPartMenu.setVisible(false);
			}
		});
		
		//Panel configuration
		pn.setLayout(null);
		pn.setBackground(Color.white);
		fPartMenu.add(pn);
		
		//Title configuration
		lblTitulo.setBounds(320,40,300,65);
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 40));
		pn.add(lblTitulo);
		
		//User title configuration
		lblTituloUser.setText(user);
		lblTituloUser.setBounds(620,40,500,65);
		lblTituloUser.setFont(new Font("Serif", Font.BOLD, 40));
		pn.add(lblTituloUser);
		
		//Part label configuration
		lblPart.setBounds(150, 150, 200, 45);
		lblPart.setFont(new Font("Serif", Font.PLAIN, 30));
		pn.add(lblPart);		
		
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		lstParts.setModel(lstModelParts);
		lstParts.setFont(new Font("Serif", Font.BOLD, 20));
		jspParts.setBounds(150, 200, 400, 250);
		pn.add(jspParts);
		
		//Show part description on click
		lstParts.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				System.out.println(1);
				try {
					aPreview.setText(Part.getPart(lstParts.getSelectedIndex(), 2));
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
		
		//Preview configuration
		lblPreview.setBounds(150,500, 300,40);
		lblPreview.setFont(new Font("Serif", Font.ITALIC, 30));
		pn.add(lblPreview);
		
		//Part preview 
		aPreview.setLineWrap(true);
		aPreview.setEditable(false);
		jspPreview.setBounds(150,550,900,150);
		pn.add(jspPreview);
		
		//Button configuration
		
		//View button configuration
		btnVer.setBounds(620,240,200,60);
		btnVer.setFont(new Font("Serif", Font.PLAIN, 25));
		pn.add(btnVer);

		//Add button configuration
		btnAdicionar.setBounds(850,240,200,60);
		btnAdicionar.setFont(new Font("Serif", Font.PLAIN, 25));
		pn.add(btnAdicionar);
		
		//Alter button configuration
		btnMudar.setBounds(620,340,200,60);
		btnMudar.setFont(new Font("Serif", Font.PLAIN, 25));
		pn.add(btnMudar);
		
		//Remove button configuration
		btnRemover.setBounds(850,340,200,60);
		btnRemover.setFont(new Font("Serif", Font.PLAIN, 25));
		pn.add(btnRemover);

	}
	//Access functions
	public JFrame fPartMenu() {
		return fPartMenu;
	}
}

