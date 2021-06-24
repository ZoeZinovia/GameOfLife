
/**
 * The Class Cell.
 */
public class Cell 
{

	private int cellValue;
	
	/**
	 * Instantiates a new dead cell.
	 */
	public Cell()
	{
		setCellValue(0);
	}
	
	/**
	 * Instantiates a new cell.
	 *
	 * @param x the value of the cell. 0 signifies dead. 1 signifies living.
	 */
	public Cell(int x)
	{
		setCellValue(x);
	}
	
	/**
	 * Sets the cell value.
	 *
	 * @param x the new cell value
	 */
	public void setCellValue(int x) 
	{
		this.cellValue = x;
	}
	
	/**
	 * Gets the cell value.
	 *
	 * @return the cell value
	 */
	public int getCellValue() 
	{
		return cellValue;
	}

	/**
	 * Checks if the cell is alive.
	 *
	 * @return true, if is alive
	 */
	public boolean isAlive()
	{
		return this.cellValue == 1;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString()
	{
		return "" + cellValue;
	}
	
}
