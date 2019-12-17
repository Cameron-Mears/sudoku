package display;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Window 
{
	private JFrame frame;
	
	private static Window instance;
	
	public static Window getInstance()
	{
		return instance;
	}
	
	public Window(int width, int height)
	{
		frame = new JFrame();
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.requestFocus();
		frame.setResizable(false);
		frame.setTitle("Epic Soduku Garbage (Not Epic)");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		instance = this;
	}
	
	public Window(boolean fullscreen)
	{
		frame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize);
		frame.setUndecorated(fullscreen);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		instance = this;
	}
	
	public JFrame getWin()
	{
		return frame;
	}
}
