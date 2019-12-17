package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class Button extends Clickable implements Renderable
{
	private String text;
	private Color textc;
	private Color border;
	private Color bg;
	private Font font;
	private ButtonFunction func;
	private int x, y;

	public Button(Rectangle bounds, int x, int y, String text, Font font, Color textc, Color border, Color bg, ButtonFunction func) 
	{
		super(bounds);
		bounds.setLocation(x, y);
		this.text = text;
		this.textc = textc;
		this.bg = bg;
		this.border = border;
		this.font = font;
		this.func = func;
		this.x = x;
		this.y = y;
		
	}
	
	
	


	@Override
	public void render(Graphics2D g2) 
	{
		g2.setFont(font);
		g2.setColor(bg);
		g2.fillRect(x, y, bounds.width, bounds.height);
		g2.setColor(border);
		int yoff = (bounds.height - g2.getFontMetrics().getHeight())/2;
		int xoff = (bounds.width - g2.getFontMetrics().stringWidth(text))/2;
		g2.drawRect(x, y, bounds.width, bounds.height);
		g2.setColor(textc);
		g2.drawString(text, x+xoff, y+g2.getFontMetrics().getHeight()-yoff);
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if (bounds.contains(e.getPoint()) && e.getButton() == MouseEvent.BUTTON1)
		{
			func.func(this);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
