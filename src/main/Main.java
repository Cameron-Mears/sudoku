package main;

import display.Window;
import graphics.Renderable;
import graphics.Renderer;
import graphics.SodokuGraphics;
import sodoku.Solver;
import util.Node;
import util.list.List;

public class Main 
{
	public static void main(String[] args) throws InterruptedException
	{
		Window win = new Window(900,900);
		Renderer renderer = new Renderer();
		SodokuGraphics sg = new SodokuGraphics(9);
		
		while (true)
		{
			renderer.render();
			
		}
	}
	
	
}
