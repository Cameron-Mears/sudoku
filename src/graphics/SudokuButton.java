package graphics;

public abstract class SudokuButton extends ButtonFunction 
{
	protected SodokuGraphics sg;
	public SudokuButton(SodokuGraphics sg) 
	{
		this.sg = sg;
	}
	@Override
	public abstract void func(Button button);

}
