package visual;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import database.*;


public class Main {
	
	public static void main(String[] args) {
				
		for(LookAndFeelInfo lafInfo : UIManager.getInstalledLookAndFeels()) {
			System.out.println(lafInfo.getClassName());
		}
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new Settings().getJFrame().setVisible(true);
	}
}
