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
	
	public Connection objConexao = null;
	
	//propriedade para gui
	
	//panel e frame
	private JFrame fBanco = new JFrame();
	private JPanel pn = new JPanel();
	
	//banco de dados nome 
	private JLabel lblBanco = new JLabel("Insert database name:");
	private JTextField txtBanco = new JTextField();
	
	//banco de dados ip address
	private JLabel lblAddress = new JLabel("Insert ip: ");
	private JTextField txtAddress = new JTextField();
	
	//banco de dados port address
	private JLabel lblPort = new JLabel("Insert port: ");
	private JTextField txtPort = new JTextField();
	
	//banco de dados user
	private JLabel lblUser = new JLabel("Insert username: ");
	private JTextField txtUser = new JTextField();

	//banco de dados password
	private JLabel lblPassword = new JLabel("Insert password: ");
	private JTextField txtPassword = new JTextField();

	//buttons regarding connection
	private JButton btnTestString = new JButton("Set connection");
	private JButton btnTestCon = new JButton("Connect");
	
	//connection labels
	private JLabel lblConnFormat = new JLabel(
			"jdbc:mariadb://<ip>:<port/<database>?user=<user>&password=<password>&serverTimezone=UTC&userSSL=false"
			);

	private static JLabel lblConn = new JLabel();
	
	private JLabel lblConnStatus = new JLabel("No connection");
	
	//file aka previous connections
	private JLabel lblFileConn = new JLabel("Previous connections");
	private File infoFile = new File("connections.txt");
	private JList<String> listInfo = new JList<String>();
	private DefaultListModel<String> dlm = new DefaultListModel<String>();
	private JScrollPane jspInfo = new JScrollPane(listInfo);
	// same but buttons
	private JButton btnSelectConn = new JButton("Use Connection");
	private JButton btnSaveConn = new JButton("Save connection");
	private JButton btnRemoveConn = new JButton("Remove Connection");
	private JButton btnRefreshConn = new JButton("Refresh Connection");
	
	//main method, whole program starts here
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
		listInfo.setModel(dlm); //list set to default list model
		
		//label -> previous connections
		lblFileConn.setBounds(730,10,200,20);
		pn.add(lblFileConn);
		
		//buttons
		
		//saves connection to file
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
				
				//writes saved connection (lblConn) to the file and goes to next line
				try (FileWriter infoWriter = new FileWriter("connections.txt", true)) {
					infoWriter.append("\n");
					infoWriter.append(lblConn.getText());

				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		//uses saved connection and sets lblConn to it
		btnSelectConn.setBounds(450,80,170,20);
		pn.add(btnSelectConn);
		btnSelectConn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e4) {
				//try catch to initialize bufferedreader
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(infoFile));
				} catch (FileNotFoundException e8) {
					e8.printStackTrace();
				}
				//defined strings
				String selected = listInfo.getSelectedValue();
				String line = null;
				//checks line until it finds the one that is selected
				try {
					while ((line = br.readLine()) != null) {
						if (line.trim().equals(selected)) {
							lblConn.setText(selected);
							//get address
							String temp = selected;
							String[] temp0 = temp.split("//");
							//temp0[0] // jdbc:mariadb
							String[] temp1 = temp0[1].split(":");
							//temp1[0] // 127.0.0.1
							String[] temp2 = temp1[1].split("/");
							//temp2[0] // 3306
							String[] temp3 = temp2[1].split("\\?");
							//temp3[0] // HUBtest_db
							String[] temp4 = temp3[1].split("&");
							String[] temp41 = temp4[0].split("=");
							// temp41[1] // java
							String[] temp5 = temp4[1].split("=");
							//temp5[1] //ceub123456
							System.out.println(temp1[0]);
							System.out.println(temp2[0]);
							System.out.println(temp3[0]);
							System.out.println(temp41[1]);
							System.out.println(temp5[1]);
							txtBanco.setText(temp3[0]);
							txtAddress.setText(temp1[0]);
							txtPort.setText(temp2[0]);
							txtUser.setText(temp41[1]);
							txtPassword.setText(temp5[1]);
							lblConn.setText("jdbc:mariadb://"+ txtAddress.getText() + ":"+ txtPort.getText() + "/" + txtBanco.getText() 
							+"?user="+ txtUser.getText() +"&password=" + txtPassword.getText() + "&serverTimezone=UTC&userSSL=false");
						}
					}
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		});
		
		//removes selected item from file
		btnRemoveConn.setBounds(450,110,170,20);
		pn.add(btnRemoveConn);
		btnRemoveConn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e3) {
				Database.removeLineFromFile(infoFile, lblConn.getText());
			}
		});
		
		//refresh to be able to see all saved on file
		btnRefreshConn.setBounds(450,140,170,20);
		pn.add(btnRefreshConn);
		btnRefreshConn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e4) {
				dlm.removeAllElements();
				int i = 0;
				try (Scanner myFileScanner = new Scanner(infoFile)){
					while (myFileScanner.hasNextLine()){
						String data = myFileScanner.nextLine();
						dlm.add(i, data);
						i++;
					}
				} catch (IOException e5) {
					e5.printStackTrace();
				}
			}
		});
		
		//file handling ends here !
		
		//button set string config
		btnTestString.setBounds(80, 200, 150,20);
		pn.add(btnTestString);
		btnTestString.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e6) {
				//updates value of lblConn
				lblConn.setText("jdbc:mariadb://"+ txtAddress.getText() + ":"+ txtPort.getText() + "/" + txtBanco.getText() 
				+"?user="+ txtUser.getText() +"&password=" + txtPassword.getText() + "&serverTimezone=UTC&userSSL=false");
			}
		});
		
		//button connect config
		btnTestCon.setBounds(270, 200, 150,20);
		pn.add(btnTestCon);
		//check connection, atribute new value to lblConn and goes to new page if all works out
		btnTestCon.addActionListener(new btnConexaoTestControlador(fBanco, lblConnStatus, lblConn, txtAddress, txtPort, txtBanco, txtUser, txtPassword));
		
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
		System.out.println(lblConn.getText());

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

