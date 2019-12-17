package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import util.Node;

public class SudokuTile extends TextBox
{
	private SodokuGraphics sg;
	private boolean isOrginal;
	private boolean disabled;

	public SudokuTile(Rectangle bounds, int x, int y, SodokuGraphics sodokuGraphics) 
	{
		super(bounds, x, y);
		sg = sodokuGraphics;
	}
	
	public String getText()
	{
		return text;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public void setOringal(boolean original)
	{
		this.isOrginal = original;
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if (hasFocus)
		{
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_BACK_SPACE)
			{
				if (text.length() > 0)
				{
					text = text.substring(0, text.length()-1);
					if (text.length() == 0) isOrginal = false;
				}
			}
			else if (key >= '0' && key <= '9')
			{
				text += e.getKeyChar();
			}
			
			if (text.length() > 0) isOrginal = true;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		Point p = e.getPoint();
		hasFocus = bounds.contains(p);
		if (hasFocus) sg.setFocuseed(this);
	}
	
	@Override
	public void render(Graphics2D g2)
	{
		g2.setColor(Color.BLACK);
		if (hasFocus && !disabled)
		{
			g2.setColor(Color.RED);
			g2.setStroke(new BasicStroke(5));
			
		}
		g2.drawRect(x, y, bounds.width, bounds.height);
		if (isOrginal) g2.setColor(Color.RED);
		else g2.setColor(Color.BLUE);
		int yoff = (bounds.height-g2.getFontMetrics().getHeight())/2;
		int xoff = (bounds.width-g2.getFontMetrics().stringWidth(text))/2;
		g2.drawString(text, x+xoff, y + g2.getFontMetrics().getHeight()-yoff);
	}


}
