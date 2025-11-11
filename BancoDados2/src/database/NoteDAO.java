package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import javax.swing.JOptionPane;

public class NoteDAO {
	//Class attribute
	private PreparedStatement statementObj;
	private ResultSet cursorObj;
	private LinkedHashMap<String,String> noteInfo = new LinkedHashMap<String, String>();
	private String noteContent = "";
	
	private Database database = new Database();
	
	//Class methods
	
	//get note information
	public LinkedHashMap<String,String> getAllNoteInfo(String usernameInput) {
		database.connect();
		
		//Prepare statement
		try {
			statementObj = database.getConnectionObj().prepareStatement(
					"SELECT note, title FROM note WHERE username_fk = ?"
					);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not retrieve all notes (1)");
			error.printStackTrace();
		}
		
		//Prepare statement parameters
		try {
			statementObj.setString(1, usernameInput);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not retrieve all notes (2)");
			error.printStackTrace();
		}
		
		//Execute statement and prepare return value
		try {
			cursorObj = statementObj.executeQuery();
			while (cursorObj.next()) {
				noteInfo.put(cursorObj.getString("title"),cursorObj.getString("note"));
				
			}
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not retrieve all notes (3)");
			error.printStackTrace();
		}
		
		database.disconnect();
		
		return noteInfo;
	}
	
	//get note content
	public String getNoteContent(String noteTitle, String usernameInput) {
		database.connect();
		
		try {
			statementObj = database.getConnectionObj().prepareStatement(
					"SELECT note FROM note WHERE title = ? and username_fk = ?"
					);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not retrieve note content (3)");
			error.printStackTrace();
		}
		
		//Prepare statement parameters
		try {
			statementObj.setString(1, noteTitle);
			statementObj.setString(2, usernameInput);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not retrieve all notes (2)");
			error.printStackTrace();
		}
		
		//Execute statement and prepare return value
		try {
			cursorObj = statementObj.executeQuery();
			if(cursorObj.next()) {
				noteContent = cursorObj.getString("note");
			}	
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not retrieve all notes (3)");
			error.printStackTrace();
		}
		
		database.disconnect();
		
		return noteContent;
	}	
	
	//create note
	public void createNote(String usernameInput, String title, String content) {
		database.connect();
		
		try {
			statementObj = database.getConnectionObj().prepareStatement(
					"INSERT INTO note (username_fk, title, note) values (?, ?, ?)"
					);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not create note (1)");
			error.printStackTrace();
		}
		
		try {
			statementObj.setString(1, usernameInput);
			statementObj.setString(2, title);
			statementObj.setString(3, content);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not create note (2)");
			error.printStackTrace();
		}
		
		try {
			statementObj.executeUpdate();
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not create note (3)");
			error.printStackTrace();
		}
		
		database.disconnect();
	}
	
	//alter note
	public void alterNote(String usernameInput, String title, String content) {
		database.connect();
		
		try {
			statementObj = database.getConnectionObj().prepareStatement(
					"UPDATE note SET note = ? where title = ? and username_fk = ?"
					);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not alter note (1)");
			error.printStackTrace();
		}
		
		try {
			statementObj.setString(1, content);
			statementObj.setString(2, title);
			statementObj.setString(3, usernameInput);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not alter note (2)");
			error.printStackTrace();
		}
		
		try {
			statementObj.executeUpdate();
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not alter note (3)");
			error.printStackTrace();
		}
		
		database.disconnect();
	}
	
	//delete note
	public void deleteNote(String usernameInput, String title) {
		database.connect();
		
		try {
			statementObj = database.getConnectionObj().prepareStatement(
					"DELETE FROM note where title = ? and username_fk = ?"
					);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not delete note (1)");
			error.printStackTrace();
		}
		
		try {
			statementObj.setString(1, title);
			statementObj.setString(2, usernameInput);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not delete note (2)");
			error.printStackTrace();
		}
		
		try {
			statementObj.executeUpdate();
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Could not delete note (3)");
			error.printStackTrace();
		}
		
		database.disconnect();

	}
}
