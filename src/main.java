import java.awt.Color;

import javax.swing.JFrame;

public class main {

	public static int Width = 500;
	public static int Height = 500;
	
	public static int rows = 3;
	public static int match = 3;
	public static int size = rows * rows;
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame ("Tick-Tac-Toe! :");
		GamePanel game = new GamePanel(new Color (0x464646)); //0x464646
		
		frame.add(game);
		frame.addMouseListener(game);
		frame.addMouseMotionListener(game);
		
		
		frame.pack();
		frame.setResizable(false);
		//frame.setSize(Width, Height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

}
