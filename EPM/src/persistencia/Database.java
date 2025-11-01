package persistencia;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Essa classe é a Database
 *  - Overview
 *  	
 *  Propriedades
 *  
 *  Propriedades para gui
 *  
 *  Propriedades escolher banco de dados
 *  
 *  FILE STUFF
 *  
 *  PSVM
 *  
 *  Método construtor gui
 *  
 *  Metodos da classe
 *  
 *  	getObjConexao()
 *  
 *  	conectar()
 *  
 *  	desconectar()
 */

public class Database {
	
	//propriedades da classe
	
	private Connection objConexao = null;
	
	//propriedade para gui
	
	private JFrame fBanco = new JFrame();
	private JPanel pn = new JPanel();
	
	//labels
	private JLabel lblBanco = new JLabel("Insert database name:");
	private JLabel lblAddress = new JLabel("Insert ip: ");
	private JLabel lblPort = new JLabel("Insert port: ");
	private JLabel lblUser = new JLabel("Insert username: ");
	private JLabel lblPassword = new JLabel("Insert password: ");
	
	private JLabel lblFileConn = new JLabel("Previous connections");
	
	//textfields
	private JTextField txtBanco = new JTextField();
	private JTextField txtAddress = new JTextField();
	private JTextField txtPort = new JTextField();
	private JTextField txtUser = new JTextField();
	private JTextField txtPassword = new JTextField();
		
	//buttons
	private JButton btnTestString = new JButton("Set connection");
	private JButton btnTestCon = new JButton("Connect");
	
	//connection labels
	private JLabel lblConnFormat = new JLabel(
			"jdbc:mariadb://<ip>:<port/<database>?user=<user>&password=<password>&serverTimezone=UTC&userSSL=false"
			);
	
	private JLabel lblConn = new JLabel(
			"jdbc:mariadb://"+txtAddress.getText()+":"+txtPort.getText()+"/"+txtBanco.getText()+
			"?user="+txtUser.getText()+"&password="+txtPassword.getText()+"&serverTimezone=UTC&userSSL=false"
			//"jdbc:mariadb://127.0.0.1:3306/HUBtest_db?user=java&password=ceub123456&serverTimezone=UTC&userSSL=false"
			);
	
	private JLabel lblConnStatus = new JLabel("No connection");
	
	private boolean connectPermission = false;
	
	private File infoFile;
	private JList<String> listInfo = new JList<String>();
	private DefaultListModel<String> dlm = new DefaultListModel<String>();
	private JScrollPane jspInfo = new JScrollPane(listInfo);
	
	private JButton btnSelectConn = new JButton("Use Connection");
	private JButton btnSaveConn = new JButton("Save connection");
	private JButton btnRemoveConn = new JButton("Remove Connection");
	private JButton btnRefreshConn = new JButton("Refresh Connection");
	
	public static void main(String[] args) {
		new Database().fBanco.setVisible(true);
	}
	
	//método construtor gui 
	
	public Database(){
		
		//frame config
		fBanco.setTitle("Login do Projeto HUB");
		fBanco.setSize(1000,500);
		fBanco.setResizable(false);
		fBanco.setDefaultCloseOperation(fBanco.EXIT_ON_CLOSE);
		fBanco.setLocationRelativeTo(null);
		
		//panel config
		pn.setLayout(null);
		pn.setBackground(Color.white);
		fBanco.add(pn);
		
		
		//banco config
		lblBanco.setBounds(20,20,200,20);
		pn.add(lblBanco);
		
		txtBanco.setBounds(220,20,200,20);
		pn.add(txtBanco);
		
		//address config
		lblAddress.setBounds(20,50,200,20);
		pn.add(lblAddress);
		
		txtAddress.setBounds(220,50,200,20);
		pn.add(txtAddress);
		
		//port config 
		lblPort.setBounds(20,80,200,20);
		pn.add(lblPort);
		
		txtPort.setBounds(220,80,200,20);
		pn.add(txtPort);
		
		//user config
		lblUser.setBounds(20,110,200,20);
		pn.add(lblUser);
		
		txtUser.setBounds(220,110,200,20);
		pn.add(txtUser);
		
		//password config
		lblPassword.setBounds(20,140,200,20);
		pn.add(lblPassword);
		
		txtPassword.setBounds(220,140,200,20);
		pn.add(txtPassword);
		

		
		//file controllers for text area and all file related things (included but not limited to
		//labels, buttons, action listeners
		
		
		//text area to choose connections
		jspInfo.setBounds(650,30,300,200);
		pn.add(jspInfo);
		listInfo.setModel(dlm);
		
		//buttons
		btnSaveConn.setBounds(450,50,170,20);
		pn.add(btnSaveConn);
		btnSaveConn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//crítica de dados
				if (txtBanco.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Insert database name");
					return;
				}
				if (txtAddress.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Insert ip address");
					return;
				}
				if (txtPort.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Insert port number");
					return;
				}
				if (txtUser.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Insert database user");
					return;
				}
				if (txtPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Insert database user password");
					return;
				}
				try (FileWriter infoWriter = new FileWriter("connections.txt", true)) {
					infoWriter.append("\n");
					infoWriter.append(lblConn.getText());
					
					/*infoWriter.append(txtBanco.getText() +
							"#:" + txtAddress.getText() +
							"#" + txtPort.getText() +
							"#" + txtUser.getText() + 
							"#" + txtPassword.getText());*/
				} catch (IOException ex) {
					ex.printStackTrace();
				}

			}
		});
		
		btnSelectConn.setBounds(450,80,170,20);
		pn.add(btnSelectConn);
		
		
		btnRemoveConn.setBounds(450,110,170,20);
		pn.add(btnRemoveConn);
		btnRemoveConn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e3) {
				Database.removeLineFromFile(infoFile, lblConn.getText());
			}
		});
		
		btnRefreshConn.setBounds(450,140,170,20);
		pn.add(btnRefreshConn);
		btnRefreshConn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				dlm.removeAllElements();
				int i = 0;
				try (Scanner myFileScanner = new Scanner(infoFile)){
					while (myFileScanner.hasNextLine()){
						String data = myFileScanner.nextLine();
						dlm.add(i, data);
						i++;
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		lblFileConn.setBounds(730,10,200,20);
		pn.add(lblFileConn);
		
		//create file if not exists
		try {
			infoFile = new File("connections.txt");
			if (infoFile.createNewFile()) {
				System.out.println("File created: " + infoFile.getName());
			} else {
				System.out.println("File " + infoFile.getName() + " alerady exists.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// end of fil shananagins 
		
		
		//btn set string config
		btnTestString.setBounds(80, 200, 150,20);
		pn.add(btnTestString);
		btnTestString.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblConn.setText("jdbc:mariadb://"+ txtAddress.getText() + ":"+ txtPort.getText() + "/" + txtBanco.getText() 
				+"?user="+ txtUser.getText() +"&password=" + txtPassword.getText() + "&serverTimezone=UTC&userSSL=false");
			}
		});
		
		//btn connect config
		btnTestCon.setBounds(270, 200, 150,20);
		pn.add(btnTestCon);
		btnTestCon.addActionListener(new btnConexaoTestControlador(connectPermission, fBanco, lblConnStatus, lblConn, txtAddress, txtPort, txtBanco, txtUser, txtPassword));
		
		
		
		//lbl connection Fomrat
		lblConnFormat.setBounds(50,300,900,20);
		pn.add(lblConnFormat);
		
		//lbl connection
		lblConn.setBounds(50,350,900,20);
		pn.add(lblConn);
		
		//lbl conn status
		lblConnStatus.setBounds(150,400,250,20);
		lblConnStatus.setForeground(Color.black);
		pn.add(lblConnStatus);
		
	}
	
	//métodos da classe
	
	Connection getObjConexao() throws Exception {
		return objConexao;
	}
	
		//instacia obj conexao e permite utilizar ele sem ter que instanciar a classe
		//getobjconexao fornece essa informacao
	void conectar() throws Exception {
		objConexao = DriverManager.getConnection(lblConn.getText());
				
	}
	
	
	void desconectar() throws Exception {
		objConexao.close();
	}
	
	//copiado: https://stackoverflow.com/questions/1377279/find-a-line-in-a-file-and-remove-it
	 public static void removeLineFromFile(File file, String lineToRemove) {

		    try {

		      File inFile = file;

		      if (!inFile.isFile()) {
		        System.out.println("Parameter is not an existing file");
		        return;
		      }

		      //Construct the new file that will later be renamed to the original filename.
		      File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

		      BufferedReader br = new BufferedReader(new FileReader(file));
		      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

		      String line = null;

		      //Read from the original file and write to the new
		      //unless content matches data to be removed.
		      while ((line = br.readLine()) != null) {

		        if (!line.trim().equals(lineToRemove)) {

		          pw.println(line);
		          pw.flush();
		        }
		      }
		      pw.close();
		      br.close();

		      //Delete the original file
		      if (!inFile.delete()) {
		        System.out.println("Could not delete file");
		        return;
		      }

		      //Rename the new file to the filename the original file had.
		      if (!tempFile.renameTo(inFile))
		        System.out.println("Could not rename file");

		    }
		    catch (FileNotFoundException ex) {
		      ex.printStackTrace();
		    }
		    catch (IOException ex) {
		      ex.printStackTrace();
		    }
		  }
	
}

//"jdbc:mariadb://127.0.0.1:3306/HUBtest_db?user=java&password=ceub123456&serverTimezone=UTC&userSSL=false"
