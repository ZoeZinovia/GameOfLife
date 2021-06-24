
/**
 * The Class ArrayMatrix.
 *
 * @param <T> the generic type
 */
public class ArrayMatrix<T> implements IMatrix<T>
{
	
	private T[][] array2D;
	private int rows;
	private int cols;
	
	/**
	 * Instantiates a new, empty array matrix.
	 */
	@SuppressWarnings("unchecked")
	public ArrayMatrix()
	{
		setRows(0);
		setCols(0);
		setArray2D((T[][]) new Object[rows][cols]);
	}
	
	/**
	 * Instantiates a new array matrix.
	 *
	 * @param arr the 2D array
	 */
	public ArrayMatrix(T[][] arr)
	{
		setRows(arr.length);
		setCols(arr[0].length);
		setArray2D(arr);
	}

	/**
	 * Gets the 2D array .
	 *
	 * @return the 2D array
	 */
	public T[][] getArray2D() {
		return array2D;
	}

	/**
	 * Sets the 2D array.
	 *
	 * @param array2d the new 2D array
	 */
	public void setArray2D(T[][] array2d) {

		this.array2D = array2d;	
	}

	/**
	 * Gets the rows.
	 *
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	
	/**
	 * Sets the rows.
	 *
	 * @param rows the new rows
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * Gets the cols.
	 *
	 * @return the cols
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * Sets the cols.
	 *
	 * @param cols the new cols
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}
	
	/**
	 * To string.
	 *
	 * @param value the value of a cell in the array that is non-empty
	 * @param nonValue the value of a cell in the array is empty
	 * @return the string
	 */
	public String toString(T value, T nonValue)
	{
		String result = "";
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				result += array2D[i][j]; 
			}
			result += "\n";
		}
		return result;
	}

	/**
	 * Gets the matrix value.
	 *
	 * @param r the row in the matrix
	 * @param c the column in the matrix
	 * @param nonValue  the value of a cell in the array that is empty. Required by the interface.
	 * @return the matrix value
	 */
	@Override
	public T getMatrixValue(int r, int c, T nonValue) {
		return array2D[r][c];
	}

	/**
	 * Sets the matrix value.
	 *
	 * @param r the row in the matrix
	 * @param c the column in the matrix
	 * @param value the value of a cell in the array that is non empty.
	 * @param change the type of change that will be affected.
	 */
	@Override
	public void setMatrixValue(int r, int c, T value, String change) {		
		 array2D[r][c] = value;
	}

	/**
	 * Increase rows.
	 *
	 * @param direction the direction in which a new row needs to be added (above or below).
	 * @param value the value of a cell in the array that is non empty. Required by the interface.
	 * @param filler the filler used to fill the new row.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void increaseRows(String direction,  T value, T filler) {
		int r = this.rows + 1;
		int c = this.cols;
		T[][] tempArray = (T[][]) new Object[r][c];
		if(direction.equals("top"))
		{
			for(int i = 0; i < r; i++)
			{
				for(int j = 0; j < c; j++)
				{
					if(i == 0)
					{
						tempArray[i][j] = filler;
					}
					else
					{
						tempArray[i][j] = this.array2D[i-1][j];
					}
				}
			}
		}
		else if(direction.equals("bottom"))
		{
			for(int i = 0; i < r; i++)
			{
				for(int j = 0; j < c; j++)
				{
					if(i == r - 1)
					{
						tempArray[i][j] = filler;
					}
					else
					{
						tempArray[i][j] = this.array2D[i][j];
					}
				}
			}
		}
		setArray2D(tempArray);
		this.rows = r;
	}

	/**
	 * Increase cols.
	 *
	 * @param direction the direction in which a new column needs to be added (left or right).
	 * @param value the value of a cell in the array that is non empty. Required by the interface.
	 * @param filler the filler used to fill the new column.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void increaseCols(String direction, T value, T filler) {
		int r = this.rows;
		int c = this.cols + 1;
		T[][] tempArray = (T[][]) new Object[r][c];
		if(direction.equals("left"))
		{
			for(int i = 0; i < r; i++)
			{
				for(int j = 0; j < c; j++)
				{
					if(j == 0)
					{
						tempArray[i][j] = filler;
					}
					else
					{
						tempArray[i][j] = this.array2D[i][j-1];
					}
				}
			}
		}
		else if(direction.equals("right"))
		{
			for(int i = 0; i < r; i++)
			{
				for(int j = 0; j < c; j++)
				{
					if(j == c - 1)
					{
						tempArray[i][j] = filler;
					}
					else
					{
						tempArray[i][j] = this.array2D[i][j];
					}
				}
			}
		}
		setArray2D(tempArray);
		this.cols = c;
	}

	/**
	 * Number of rows in the matrix.
	 *
	 * @return the int
	 */
	@Override
	public int numRows() {
		return getRows();
	}

	/**
	 * Number of columns in the matrix.
	 *
	 * @return the int
	 */
	@Override
	public int numCols() {
		return getCols();
	}
	
	/**
	 * Deep copy of a matrix.
	 *
	 * @param value the value of a cell in the array that is non empty
	 * @param nonValue the value of a cell in the array that is empty
	 * @return IMatrix
	 */
	@SuppressWarnings("unchecked")
	@Override
	public IMatrix<T> deepCopyOf(T value, T nonValue) {
		T[][] arrayCopy= (T[][]) new Object[this.rows][this.cols];
		for(int i = 0; i < this.rows; i++)
		{
			for(int j = 0; j < this.cols; j++)
			{
				arrayCopy[i][j] = this.array2D[i][j];
			}
		}
		IMatrix<T> result = new ArrayMatrix<T>(arrayCopy);
		return result;
	}
	
	/**
	 * Required by the interface. Not used by ArrayMatrix.
	 *
	 * @param list the new sentinel list
	 */
	public void setSentinelList(ILinkedList<T> list)
	{}
	
	/**
	 * Required by the interface. Not used by ArrayMatrix.
	 *
	 * @param direction the direction
	 * @param value the value
	 * @param nonValue the non value
	 */
	public void shiftMatrix(String direction, T value, T nonValue)
	{}
}
