package visual;

import database.Database;

public class Main {
	public static void main(String[] args) {
		Database.setConnectionURl("jdbc:mariadb://localhost:3306/note_db?user=java&password=ceub123456&serverTimezone=UTC&useSSL=false");
		new Login().getJFrame().setVisible(true);
	}
}
