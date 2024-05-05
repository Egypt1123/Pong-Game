package pongGame;

import javax.swing.JFrame;


public class Pong extends JFrame {
	final static String SCREEN_TITLE = "Pong";
	final static int SCREEN_WIDTH = 800;
	final static int SCREEN_HEIGHT = 600;
	public Pong() {
		setTitle(SCREEN_TITLE);
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false);
		add(new PongPanel());
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
	
		javax.swing.SwingUtilities.invokeLater(new Runnable() {;
		
			public void run() {
				
				new Pong();
			}
		
		});

	}
		
}
