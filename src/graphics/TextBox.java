package graphics;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import display.Window;

public class TextBox extends Clickable implements KeyListener, Renderable
{
	protected boolean hasFocus;
	protected int x, y;
	protected String text;
	

	public TextBox(Rectangle bounds, int x, int y) 
	{
		super(bounds);
		bounds.setLocation(x, y);
		this.x = x;
		this.y = y;
		text = new String();
		Window.getInstance().getWin().addKeyListener(this);
	}
	
	@Override
	public void render(Graphics2D g2) 
	{
		if (hasFocus)
		{
			
		}
	}


	public boolean hasFocus() 
	{
		return hasFocus;
	}

	public void setHasFocus(boolean hasFocus) 
	{
		this.hasFocus = hasFocus;
	}

	public int getWidth() 
	{
		return bounds.width;
	}

	public void setWidth(int width) 
	{
		this.bounds = new Rectangle(width, bounds.height);
	}

	public int getHeight()
	{
		return bounds.height;
	}

	public void setHeight(int height)
	{
		this.bounds = new Rectangle(bounds.width, height);
	}

	public int getX() 
	{
		return x;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public int getY() 
	{
		return y;
	}

	public void setY(int y) 
	{
		this.y = y;
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		Point p = e.getPoint();
		if (bounds.contains(p))
		{
			hasFocus = true;
		}
		else
		{
			hasFocus = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) 
	{
		if (hasFocus)
		{
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_BACK_SPACE)
			{
				if (text.length() > 0)
				{
					text = text.substring(0, text.length()-2);
				}
			}
			else if (key >= '0' && key <= '9')
			{
				text += key;
			}
		}
	}

}
