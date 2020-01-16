package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;

import display.Window;
import sodoku.Solver;
import util.Node;
import util.list.List;

public class SodokuGraphics implements Renderable
{
	private SudokuTile[][] inputs;
	
	private int canvasWidth, canvasHeight;
	private JFrame frame;
	private SudokuTile focused;
	private boolean solveable = true;
	private int numBoxes;
	private int size;
	int cellWidth = 32;
	int cellHeight = 32;
	private int boardx, boardy;
	private List<Renderable> components;
	private Node<Renderable> gnode;
	
	public SodokuGraphics(int size) 
	{
		boardx = 100;
		boardy = 150;
		frame = Window.getInstance().getWin();
		canvasHeight = frame.getHeight();
		canvasWidth = frame.getWidth();
		this.size = size;
		numBoxes = (int) Math.sqrt(size);
		inputs = new SudokuTile[size][size];
		components = new List<Renderable>();
		
		for (int row = 0; row < size; row ++)
		{
			for (int col = 0; col < size; col ++)
			{
				inputs[row][col] = new SudokuTile(new Rectangle(cellWidth, cellHeight), boardx + row*cellWidth, 150 + col*cellHeight, this);
			}
		}
		gnode = new Node<Renderable>(this);
		Renderer.getInstance().addComponent(gnode);
		
		SudokuButton solve = new SudokuButton(this) 
		{			
			@Override
			public void func(Button button) {
				sg.solve();
				sg.solveable = true;
			}
		};
		
		SudokuButton to4x4 = new SudokuButton(this) 
		{			
			@Override
			public void func(Button button) 
			{
				sg.switchSize(4);
				sg.solveable = true;
			}
		};
		
		SudokuButton to9x9 = new SudokuButton(this) 
		{			
			@Override
			public void func(Button button) 
			{
				sg.switchSize(9);
				sg.solveable = true;
			}
		};
		
		SudokuButton to16x16 = new SudokuButton(this) 
		{			
			@Override
			public void func(Button button) 
			{
				sg.switchSize(16);
				sg.solveable = true;
			}
		};
		
		SudokuButton clear = new SudokuButton(this) 
		{			
			@Override
			public void func(Button button) 
			{
				for (int row = 0; row < inputs.length; row++) 
				{
					for (int col = 0; col < inputs.length; col++) 
					{
						inputs[row][col].setText(new String());
						inputs[row][col].setOringal(false);
						sg.solveable = true;
					}
				}
				
			}
		};
		
	
		
		Rectangle buttonRect = new Rectangle(164,72);
		Button solveButton = new Button(new Rectangle(164,72), 520, 800, "Solve",  new Font("Arial", Font.BOLD, 50), Color.BLACK, Color.BLACK, Color.WHITE, solve);
		Button to4x4Button = new Button(new Rectangle(164,72), 10, 800, "4x4",  new Font("Arial", Font.BOLD, 50), Color.BLACK, Color.BLACK, Color.WHITE, to4x4);
		Button to9x9Button = new Button(new Rectangle(164,72), 180, 800, "9x9",  new Font("Arial", Font.BOLD, 50), Color.BLACK, Color.BLACK, Color.WHITE, to9x9);
		Button to16x16Button = new Button(new Rectangle(164,72), 350, 800, "16x16",  new Font("Arial", Font.BOLD, 50), Color.BLACK, Color.BLACK, Color.WHITE, to16x16);
		Button clearButton = new Button(new Rectangle(164,72), 690, 800, "Clear",  new Font("Arial", Font.BOLD, 50), Color.BLACK, Color.BLACK, Color.WHITE, clear);
		components.append(new Node<Renderable>(solveButton));
		components.append(new Node<Renderable>(clearButton));
		components.append(new Node<Renderable>(to4x4Button));
		components.append(new Node<Renderable>(to9x9Button));
		components.append(new Node<Renderable>(to16x16Button));
	}
	
	public void setFocuseed(SudokuTile tile)
	{
		focused = tile;
	}
	
	@Override
	public void render(Graphics2D g2) 
	{
		GradientPaint gp = new GradientPaint(0, 0, Color.WHITE, canvasWidth, canvasHeight, Color.BLUE, false);
		g2.setPaint(gp);
		g2.fillRect(0, 0, canvasWidth, canvasHeight);
		
		g2.setColor(Color.RED);
		Font f = new Font("Arial", Font.BOLD, 50);
		g2.setFont(f);
		g2.drawString("Sodoku Solver", boardx, 100);
		if (!solveable)
		{
			g2.drawString("Puzzle Not Solveable", boardx, 140);
		}
		int boardWidth = cellWidth * size;
		int boardHeight = cellHeight * size;
		
		g2.setColor(Color.WHITE);
		g2.fillRect(boardx, boardy, boardWidth, boardWidth);
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.BLACK);
		g2.drawRect(boardx,boardy, boardWidth, boardHeight);
		for (int row = 0; row < size; row ++)
		{
			for (int col = 0; col < size; col ++)
			{
				
				if (row % numBoxes == 0)
				{
					g2.setStroke(new BasicStroke(3));
					g2.setColor(Color.BLACK);
					g2.drawLine(boardx + row*cellWidth, boardy, boardx + row*cellWidth, boardy + cellHeight * size);
				}
				
				if (col % numBoxes == 0)
				{
					g2.setStroke(new BasicStroke(3));
					g2.setColor(Color.BLACK);
					g2.drawLine(boardx, boardy + col*cellHeight, boardx + size*cellWidth, boardy + col*cellHeight);
				}
				f = new Font("Arial", Font.BOLD, 25);
				g2.setFont(f);
				g2.setStroke(new BasicStroke(1));
				inputs[row][col].render(g2);
			}
		}
		if (focused != null) focused.render(g2);
		
		for (Renderable renderable : components) 
		{
			renderable.render(g2);
		}
	}
	
	public void solve()
	{
		int[][] grid = new int[size][size];
		for (int row = 0; row < size; row++) 
		{
			for (int col = 0; col < size; col ++)
			{
				SudokuTile tile = inputs[row][col];
				if (tile.getText().length() == 0)
				{
					grid[row][col] = 0;
				}
				else
				{
					grid[row][col] = Integer.parseInt(tile.getText());
				}
			}
		}
		
		Solver solver = new Solver(grid, size, this);
		long timenow = System.currentTimeMillis();
		solveable = solver.solve(false);
		System.out.println(System.currentTimeMillis() - timenow);	
			
	}
	
	
	public void updateGrid(int[][] grid)
	{
		for (int row = 0; row < grid.length; row++) 
		{
			for (int col = 0; col < grid.length; col++) 
			{
				SudokuTile tile = inputs[row][col];
				int num = grid[row][col];
				String text = new String();
				if (num != 0) text = Integer.toString(num);
				tile.setText(text);
			}
		}
	}
	
	public void updateCell(int num, int row, int col)
	{
		inputs[row][col].setText(Integer.toString(num));
	
	}

	public void switchSize(int newSize)
	{
		for (SudokuTile[] arr : inputs) 
		{
			for (SudokuTile tile: arr) 
			{
				Window.getInstance().getWin().removeKeyListener(tile);
				Window.getInstance().getWin().removeMouseListener(tile);
			}
		}
		
		size = newSize;
		numBoxes = (int) Math.sqrt(newSize);
		inputs = new SudokuTile[size][size];
		for (int row = 0; row < size; row ++)
		{
			for (int col = 0; col < size; col ++)
			{
				inputs[row][col] = new SudokuTile(new Rectangle(cellWidth,cellHeight), boardx + row*cellWidth, boardy + col*cellWidth, this);
			}
		}
	}
}


