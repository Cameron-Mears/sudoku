package sodoku;

import graphics.Renderer;
import graphics.SodokuGraphics;

public class Solver 
{
	private static final int EMPTY = 0;
	private int size;
	private int[][] grid;
	private int numBoxes;
	private SodokuGraphics sg;
	private boolean gridOk;
	
	public Solver(int[][] grid, int size, SodokuGraphics sg)
	{
		this.numBoxes = (int) Math.sqrt(size);
		this.size = size;
		this.grid = grid;
		this.sg = sg;
		this.gridOk = isInputOk(grid);
	}
	
	public boolean isGridOk()
	{
		return gridOk;
	}
	
	public boolean isInRow(int row, int num)
	{
		for (int index = 0; index < size; index++)
		{
			if (grid[row][index] == num) return true;
		}
		return false;
	}
	
	public boolean isInCol(int col, int num)
	{
		for (int index = 0; index < size; index ++)
		{
			if (grid[index][col] == num) return true;
		}
		return false;
	}
	
	public boolean isInBox(int row, int col, int num)
	{
		int r = 0, c = 0;
		r = row - row % numBoxes;
		c = col - col % numBoxes;
		
		for (int i = r; i < r + numBoxes ; i++) 
		{
			for (int j = c; j < c + numBoxes ; j++) 
			{
				if (grid[i][j] == num) return true;
			}
		}
		
		return false;
	}
	
	public boolean isOk(int num, int row, int col)
	{
		return !isInBox(row, col, num) && !isInRow(row, num) && !isInCol(col, num);
	}
	
	public boolean solve(boolean doRender)
	{ 
		for (int row = 0; row < size; row ++)
		{
			for (int col = 0; col < size; col ++)
			{
				if (grid[row][col] == EMPTY)
				{
					for (int num = 1; num <= size; num ++)
					{
						if (isOk(num, row, col))
						{
							grid[row][col] = num;
							
							if (doRender)
							{
								sg.updateCell(num, row, col);
								Renderer.getInstance().render();
							}
							if (solve(doRender))
							{
								return true;
								
							}
							else
							{
								grid[row][col] = EMPTY;
							}
						}
					}	
					return false;
				}
			}
		}
		sg.updateGrid(grid);
		return true;
	}

	
	public boolean isInputOk(int grid[][])
	{
		for (int row = 0; row < size; row ++)
		{
			for (int col = 0; col < size; col ++)
			{
				if (grid[row][col] != EMPTY)
				{
					if (!isOk(grid[row][col], row, col)) return false;
				}
			}
		}
		return true;
	}

	
	
}
