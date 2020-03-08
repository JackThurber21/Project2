package Project2;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


public class MainFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Project 2 GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel panel = new mainPanel();
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			//	panel.doClose();
			}
		});
	}
}

