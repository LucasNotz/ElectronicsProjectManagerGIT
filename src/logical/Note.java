package logical;

import java.util.LinkedHashMap;

import database.NoteDAO;

public class Note {
	//Class attributes
	private String note = "";
	private User userObj;
	
	//Constructors
	public Note() {
		super();
	}
	
	public Note(String note, User userObj) {
		super();
		this.note = note;
		this.userObj = userObj;
	}
	
	//Getters and setters
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public User getUserObj() {
		return userObj;
	}
	
	public void setUserObj(User userObj) {
		this.userObj = userObj;
	}
	
	//Database methods
	
	//Get all notes info
	public static LinkedHashMap<String, String> getAllNoteInfo(String usernameInput) {
		NoteDAO dao = new NoteDAO();
		LinkedHashMap<String, String> noteInfo = new LinkedHashMap<String, String>();
		noteInfo = dao.getAllNoteInfo(usernameInput);
		return noteInfo;
	}
	
	//get note content
	public static String getNoteContent(String noteTitle, String usernameInput) {
		NoteDAO dao = new NoteDAO();
		String noteContent = dao.getNoteContent(noteTitle, usernameInput);
		return noteContent;
	}
	
	//create note
	public static void createNote(String usernameInput, String title, String content) {
		NoteDAO dao = new NoteDAO();
		dao.createNote(usernameInput, title, content);
	}
	
	//alter note
	public static void alterNote(String usernameInput, String title, String content) {
		NoteDAO dao = new NoteDAO();
		dao.alterNote(usernameInput, title, content);
	}
	
	//delete note
	public static void deleteNote(String usernameInput, String title) {
		NoteDAO dao = new NoteDAO();
		dao.deleteNote(usernameInput, title);
	}
	
	//check if note exists
	public static boolean doesNoteExist(String title, String usernameInput) {
		NoteDAO dao = new NoteDAO();
		LinkedHashMap<String, String> noteInfo = new LinkedHashMap<String, String>();
		noteInfo = dao.getAllNoteInfo(usernameInput);
		for (String noteTitle : noteInfo.keySet()) {
			if (title.equals(noteTitle)) {
				return true;
			}
		}
		return false;
	}
}