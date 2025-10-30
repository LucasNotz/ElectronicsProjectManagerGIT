package test;

import negocio.Part;
import java.awt.Desktop;
import java.io.File;
import java.util.Collection;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Test2 {
	public static void main(String[] args) {
		Collection<String> a = null;
		try {
			a = Part.getTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(a);
	}
	
	public static boolean OpenFileViaExplorer() {
		try {
			
			JFileChooser fileChooser = new JFileChooser();
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"Image files", "png", "jpg");
			
			fileChooser.setFileFilter(filter);
			
			fileChooser.setCurrentDirectory(new File(""));
			
			int result = fileChooser.showOpenDialog(null);
			
			System.out.println("Result " + result);
			
			if(result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
				System.out.println("FIlepath " + selectedFile);
				
				if(!Desktop.isDesktopSupported()) {
					System.out.println("Not Supported");
					return false;
				} else {
					Desktop desktop = Desktop.getDesktop();
					desktop.open(selectedFile);
					System.out.println(selectedFile.getClass());
					return true;
				}
			} else if (result == JFileChooser.CANCEL_OPTION) {
				System.out.println("Cancelled");
				return false;
			}
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return false;
	}
	
}
	
	
	
	
	