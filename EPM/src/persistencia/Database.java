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

public class Database {
	//Class variables
	public Connection objConexao = null;
	
	//GUI variables
	
	//Frame and Panel
	private JFrame fBanco = new JFrame();
	private JPanel pn = new JPanel();
	
	//Get frame
	public JFrame getfBanco() {
		return fBanco;
	}
	
	//Database name
	private JLabel lblBanco = new JLabel("Insert database name:");
	private JTextField txtBanco = new JTextField();
	
	//Database Internet Protocol
	private JLabel lblAddress = new JLabel("Insert ip: ");
	private JTextField txtAddress = new JTextField();
	
	//Database port
	private JLabel lblPort = new JLabel("Insert port: ");
	private JTextField txtPort = new JTextField();
	
	//Database user
	private JLabel lblUser = new JLabel("Insert username: ");
	private JTextField txtUser = new JTextField();

	//Database password
	private JLabel lblPassword = new JLabel("Insert password: ");
	private JTextField txtPassword = new JTextField();

	//Database connection buttons
	private JButton btnTestString = new JButton("Set connection"); //set connection string to be used
	private JButton btnCon = new JButton("Connect"); //use connection string to connect
	
	//Database connection labels
	private JLabel lblConFormato = new JLabel(
			"jdbc:mariadb://<ip>:<port/<database>?user=<user>&password=<password>&serverTimezone=UTC&userSSL=false");

	//IMPORTANT: this label value will be the value used for all connections
	static JLabel lblCon = new JLabel();
	
	//Used in login.Login.java to set default database connection
	// IMPORTANT: this function is used to set the default lblCon value that will be used for
	//            future preset connections (references the first value of "default connections.txt" file)
	public static void setLblCon(JLabel lblConn) {
		Database.lblCon = lblConn;
	}
	
	private JLabel lblConnStatus = new JLabel("No connection");
	
	//Variables for Saved Connections in 'connections.txt' file
	private JLabel lblFileConn = new JLabel("Previous connections");
	private File infoFile = new File("connections.txt");
	
	//Display for Saved Connections in 'connections.txt' file
	private JList<String> listInfo = new JList<String>();
	private DefaultListModel<String> dlm = new DefaultListModel<String>();
	private JScrollPane jspInfo = new JScrollPane(listInfo);
	
	//Buttons for Saved Connections in 'connections.txt' file
	private JButton btnSelectConn = new JButton("Use Connection");
	private JButton btnSaveConn = new JButton("Save connection");
	private JButton btnRemoveConn = new JButton("Remove Connection");
	private JButton btnRefreshConn = new JButton("Refresh Connection");
	
	//Variables for Default connection in 'default onnection.txt' file
	private JLabel lblFileConnDefault = new JLabel("Default connections");
	private File infoFileDefault = new File("default connection.txt");
	
	//Display for Saved Connections in 'default onnection.txt' file
	private JList<String> listInfoDefualt = new JList<String>();
	private DefaultListModel<String> dlmDefault = new DefaultListModel<String>();
	private JScrollPane jspInfoDefault = new JScrollPane(listInfoDefualt);
	
	//Buttons for Default connection in 'default onnection.txt' file
	private JButton btnSelectConnDefault = new JButton("Use Connection");
	private JButton btnSaveConnDefault = new JButton("Save connection");
	private JButton btnRemoveConnDefault = new JButton("Remove Connection");
	private JButton btnRefreshConnDefault = new JButton("Refresh Connection");
	
	//GUI constructor function
	public Database(){
		//////////////////////////////////////////////////////////////Beginning of basic element configuration
		
		//Frame configuration
		fBanco.setTitle("Login do Projeto HUB");
		fBanco.setSize(1000,500);
		fBanco.setResizable(false);
		fBanco.setDefaultCloseOperation(fBanco.EXIT_ON_CLOSE);
		fBanco.setLocationRelativeTo(null);
		
		//Panel configuration
		pn.setLayout(null);
		pn.setBackground(Color.white);
		fBanco.add(pn);
		
		//Database name configuration (label and text area)
		lblBanco.setBounds(20,20,200,20);
		pn.add(lblBanco);
		txtBanco.setBounds(220,20,200,20);
		pn.add(txtBanco);
		
		//Database Internet Protocol configuration (label and text area)
		lblAddress.setBounds(20,50,200,20);
		pn.add(lblAddress);
		txtAddress.setBounds(220,50,200,20);
		pn.add(txtAddress);
		
		//Database port configuration (label and text area)
		lblPort.setBounds(20,80,200,20);
		pn.add(lblPort);
		txtPort.setBounds(220,80,200,20);
		pn.add(txtPort);
		
		//Database user configuration (label and text area)
		lblUser.setBounds(20,110,200,20);
		pn.add(lblUser);
		txtUser.setBounds(220,110,200,20);
		pn.add(txtUser);
		
		//Database password configuration (label and text area)
		lblPassword.setBounds(20,140,200,20);
		pn.add(lblPassword);
		txtPassword.setBounds(220,140,200,20);
		pn.add(txtPassword);
		
		////////////////////////////////////////////////////////////// End of basic element configuration
		
		//////////////////////////////////////////////////////////////Beginning of default connection configuration
		
		//Label for default file connection
		lblFileConnDefault.setBounds(730,190,200,20);
		pn.add(lblFileConnDefault);
		
		//Scroll Pane/List/List Model for default connection file
		jspInfoDefault.setBounds(650,210,300,140);
		pn.add(jspInfoDefault);
		listInfoDefualt.setModel(dlmDefault); //list set to default list model
		
		//Save connection button
		btnSaveConnDefault.setBounds(450,230,170,20);
		pn.add(btnSaveConnDefault);
		btnSaveConnDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Data critique
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
				
				//Writes saved connection (lblConn) to the file and goes to next line
				try (FileWriter infoWriter2 = new FileWriter("default connection.txt", true)) {
					infoWriter2.append(lblCon.getText());

				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		//Select connection button
		btnSelectConnDefault.setBounds(450,260,170,20);
		pn.add(btnSelectConnDefault);
		btnSelectConnDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e4) {
				//try catch to initialize bufferedreader
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(infoFileDefault));
				} catch (FileNotFoundException e8) {
					e8.printStackTrace();
				}
				//defined strings
				String selected2 = listInfoDefualt.getSelectedValue();
				String line2 = null;
				//checks line until it finds the one that is selected
				try {
					while ((line2 = br.readLine()) != null) {
						if (line2.trim().equals(selected2)) {
							//lblConn.setText(selected2);
							//get address
							String temp2 = selected2;
							String[] temp02 = temp2.split("//");
							//temp0[0] // jdbc:mariadb
							String[] temp12 = temp02[1].split(":");
							//temp1[0] // 127.0.0.1
							String[] temp22 = temp12[1].split("/");
							//temp2[0] // 3306
							String[] temp32 = temp22[1].split("\\?");
							//temp3[0] // HUBtest_db
							String[] temp42 = temp32[1].split("&");
							String[] temp412 = temp42[0].split("=");
							// temp41[1] // java
							String[] temp52 = temp42[1].split("=");
							//temp5[1] //ceub123456
							System.out.println("--- Database connection info (Default connection) ---");
							System.out.println("IP: " + temp12[0]);
							System.out.println("Port: " + temp22[0]);
							System.out.println("Database name: " + temp32[0]);
							System.out.println("User: " + temp412[1]);
							System.out.println("Password: " + temp52[1]);
							txtBanco.setText(temp32[0]);
							txtAddress.setText(temp12[0]);
							txtPort.setText(temp22[0]);
							txtUser.setText(temp412[1]);
							txtPassword.setText(temp52[1]);
							//prepare lblConn
							lblCon.setText("jdbc:mariadb://"+ txtAddress.getText() + ":"+ txtPort.getText() + "/" + txtBanco.getText() 
							+"?user="+ txtUser.getText() +"&password=" + txtPassword.getText() + "&serverTimezone=UTC&userSSL=false");
						}
					}
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		});
		
		//Remove selected button
		btnRemoveConnDefault.setBounds(450,290,170,20);
		pn.add(btnRemoveConnDefault);
		btnRemoveConnDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e3) {
				//Uses function I found online
				Database.removeLineFromFile(infoFileDefault, listInfoDefualt.getSelectedValue());
			}
		});
		
		//Refresh button
		btnRefreshConnDefault.setBounds(450,320,170,20);
		pn.add(btnRefreshConnDefault);
		btnRefreshConnDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e4) {
				//Removes so that all can be re-added
				dlmDefault.removeAllElements();
				
				//Adds all elements back
				int i = 0;
				try (Scanner myFileScanner = new Scanner(infoFileDefault)){
					while (myFileScanner.hasNextLine()){
						String data2 = myFileScanner.nextLine();
						dlmDefault.add(i, data2);
						i++;
					}
				} catch (IOException e5) {
					e5.printStackTrace();
				}
			}
		});
		//////////////////////////////////////////////////////////////End of default connection configuration
		
		//////////////////////////////////////////////////////////////////////////////////////////////////
		///Up top is default connection.txt file handling
		///Down below is connection.txt file handling  
		////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//////////////////////////////////////////////////////////////Beginning of previous connection configuration
		
		//Scroll Pane/List/Default List Model
		jspInfo.setBounds(650,30,300,140);
		pn.add(jspInfo);
		listInfo.setModel(dlm); 
		
		//label -> previous connections
		lblFileConn.setBounds(730,10,150,20);
		pn.add(lblFileConn);
		
		//save to previous connections button
		btnSaveConn.setBounds(450,50,170,20);
		pn.add(btnSaveConn);
		btnSaveConn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Data critique
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
				//To be resolved: I don't know how to limit the file to have only one 
				//                value, but it work, I'm pretty sure
				try (FileWriter infoWriter = new FileWriter("connections.txt", true)) {
					infoWriter.append(lblCon.getText());
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		//Select from previous connections button
		btnSelectConn.setBounds(450,80,170,20);
		pn.add(btnSelectConn);
		btnSelectConn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e4) {
				//try catch to initialize buffered reader
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
							lblCon.setText(selected);
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
							System.out.println("--- Database connection info (Previous connection)---");
							System.out.println("IP " + temp1[0]);
							System.out.println("Port " + temp2[0]);
							System.out.println("DB name " + temp3[0]);
							System.out.println("User " + temp41[1]);
							System.out.println("Password " + temp5[1]);
							txtBanco.setText(temp3[0]);
							txtAddress.setText(temp1[0]);
							txtPort.setText(temp2[0]);
							txtUser.setText(temp41[1]);
							txtPassword.setText(temp5[1]);
							lblCon.setText("jdbc:mariadb://"+ txtAddress.getText() + ":"+ txtPort.getText() + "/" + txtBanco.getText() 
							+"?user="+ txtUser.getText() +"&password=" + txtPassword.getText() + "&serverTimezone=UTC&userSSL=false");
						}
					}
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		});
		
		//button to remove from previous selection file
		btnRemoveConn.setBounds(450,110,170,20);
		pn.add(btnRemoveConn);
		btnRemoveConn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e3) {
				//Uses function I found online
				Database.removeLineFromFile(infoFile, listInfo.getSelectedValue());
			}
		});
		
		//refresh
		btnRefreshConn.setBounds(450,140,170,20);
		pn.add(btnRefreshConn);
		btnRefreshConn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e4) {
				//removes all elements 
				dlm.removeAllElements();
				
				//add all element back
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
		//////////////////////////////////////////////////////////////End of previous connection configuration
		
		//////////////////////////////////////////////////////////////Beginning of connection visuals
		//Set string button
		btnTestString.setBounds(50, 180, 150,20);
		pn.add(btnTestString);
		btnTestString.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e6) {
				//updates value of lblConn
				lblCon.setText("jdbc:mariadb://"+ txtAddress.getText() + ":"+ txtPort.getText() + "/" + txtBanco.getText() 
				+"?user="+ txtUser.getText() +"&password=" + txtPassword.getText() + "&serverTimezone=UTC&userSSL=false");
			}
		});
		
		//test connection/connect button
		btnCon.setBounds(230, 180, 150,20);
		pn.add(btnCon);
		//check connection, attribute new value to lblConn and goes to new page if all works out
		btnCon.addActionListener(new btnConexaoTestControlador(fBanco, lblConnStatus, lblCon, txtAddress, txtPort, txtBanco, txtUser, txtPassword));
		
		//connection format label
		lblConFormato.setBounds(50,370,900,20);
		pn.add(lblConFormato);
		
		//connection to be used label
		lblCon.setBounds(50,400,900,20);
		pn.add(lblCon);
		
		//connection status label
		lblConnStatus.setBounds(150,430,250,20);
		lblConnStatus.setForeground(Color.black);
		pn.add(lblConnStatus);
		
	}
	
	//Class functions
	
	//Get connection object
	Connection getObjConexao() throws Exception {
		return objConexao;
	}
	
	//Connect to database + control mechanism
	static int ic = 0;
	void conectar() throws Exception {
		objConexao = DriverManager.getConnection(lblCon.getText());
		ic++;
		System.out.println("Connected to " + lblCon.getText() + " (" + ic + ")");
	}
	
	//Disconnect from database + control mechanism
	static int idc = 0;
	void desconectar() throws Exception {
		objConexao.close();
		System.out.println("Disconnected from " + lblCon.getText() + " (" + idc + ")");
		idc++;
	}
	
	//Copied from stack overflow: https://stackoverflow.com/questions/1377279/find-a-line-in-a-file-and-remove-it
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

