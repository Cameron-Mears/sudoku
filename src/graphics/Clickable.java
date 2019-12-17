package graphics;

import java.awt.Rectangle;
import java.awt.event.MouseListener;

import javax.swing.event.MouseInputListener;

import display.Window;

public abstract class Clickable implements MouseListener
{
	protected Rectangle bounds;
	
	public Clickable(Rectangle bounds)
	{
		this.bounds = bounds;
		Window.getInstance().getWin().addMouseListener(this);
		System.out.println(Window.getInstance().getWin().getMouseListeners().length);
	}
}
