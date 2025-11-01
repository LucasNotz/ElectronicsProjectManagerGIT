package test;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class MyFrame{
	
	MyFrame() {
	JFrame f = new JFrame();
	f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
	f.setSize(500,500);
	f.setLocationRelativeTo(null);
	f.setVisible(true);
	}
	
	public void paint(Graphics g) {
		Graphics g2D = (Graphics2D) g;
		g2D.drawLine(0, 0, 500, 500);
		
	}
	
	public static void main(String[] args) {
		new MyFrame();
	}

}
