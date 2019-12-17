package graphics;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import display.Window;
import util.Node;
import util.list.List;

public class Renderer 
{
	private static Renderer instance;
	private GradientPaint background;
	private int rows;
	private int columns;
	
	private Color borderColor;
	private JFrame win;
	
	private List<Renderable> renderables;
	
	
	public Renderer()
	{
		renderables = new List<Renderable>();
		win = Window.getInstance().getWin();
		instance = this;
	}
	
	
	public static Renderer getInstance()
	{
		return instance;
	}
	
	public void addComponent(Node<Renderable> node)
	{
		renderables.append(node);
	}
	
	public void removeComponent(Node<Renderable> node)
	{
		renderables.remove(node);
	}
	
	public void render()
	{
		try
		{
			BufferStrategy bs = Window.getInstance().getWin().getBufferStrategy();
			if (bs == null)
			{
				Window.getInstance().getWin().createBufferStrategy(3);
				render();
				return;
			}
			Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();
			
			for (Renderable renderable : renderables) 
			{
				renderable.render(g2);
			}
			
			
			bs.show();
			g2.dispose();
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	
	
	
}
