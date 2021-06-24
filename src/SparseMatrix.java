
/**
 * The Class SparseMatrix.
 *
 * @param <T> the generic type
 */
public class SparseMatrix<T> implements IMatrix<T>
{
	
	private ILinkedList<T> sentinelList;
	private int rows;
	private int cols;
	
	/**
	 * Instantiates a new, empty sparse matrix.
	 */
	public SparseMatrix()
	{
		sentinelList = new SentinelLinkedList<T>();
	}
	
	/**
	 * Instantiates a new sparse matrix.
	 *
	 * @param array the 2d array
	 * @param value the value of a non-empty cell.
	 * @param nonValue the value of an empty cell.
	 */
	public SparseMatrix(T[][] array, T value, T nonValue)
	{
		this();
		setSentinelList(array, value, nonValue);
	}

	/**
	 * Gets the sentinel list.
	 *
	 * @return the sentinel list
	 */
	public ILinkedList<T> getSentinelList() {
		return sentinelList;
	}
	
	/**
	 * Sets the sentinel list.
	 *
	 * @param list the new sentinel list
	 */
	public void setSentinelList(ILinkedList<T> list) 
	{
		this.sentinelList = list;
	}

	/**
	 * Sets the sentinel list.
	 *
	 * @param array the 2d array
	 * @param value the value of a non-empty cell.
	 * @param nonValue the value of an empty cell.
	 */
	public void setSentinelList(T[][] array, T value, T nonValue) {
		setRows(array.length);
		setCols(array[0].length);
		int maxRowOrCol = Math.max(rows, cols);
		for(int i = 0; i < maxRowOrCol; i++)
		{
			sentinelList.addLast(i, i, nonValue, "");
		}
		//add items by row, then col
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				if(array[i][j].toString().equals(value.toString()))
				{
					sentinelList.getSentinelNode(i).getEast().addLast(i, j, value, "east");
					sentinelList.getSentinelNode(j).getSouth().addLast(i, j, value, "south");
				}
			}
		}
	}
	
	/**
	 * Gets the number of rows.
	 *
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Sets the number of rows.
	 *
	 * @param rows the new rows
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * Gets the number of cols.
	 *
	 * @return the cols
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * Sets the number of cols.
	 *
	 * @param cols the new cols
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}

	/**
	 * method to return value of a data node from data linked list at position r, c.
	 *
	 * @param r the row value of the given position.
	 * @param c the column value of the given position.
	 * @param nonValue the value of an empty cell.
	 * @return the matrix value
	 */
	@Override
	public T getMatrixValue(int r, int c, T nonValue) {
		if(r > rows || c > cols)
			throw new IllegalArgumentException("Index out of sparse matrix bounds");
		SentinelNode<T> tmpS = sentinelList.getSentinelNode(r);
		DataLinkedList<T> tmpD = tmpS.getEast();
		if(!tmpD.exists(r, c, "east"))
			return nonValue;
		DataNode<T> tmpN = tmpD.getDataNode(r, c, "east");
		return tmpN.getValue();
	}

	/**
	 * This function creates a node in the sparse matrix if new value is not 0/dead. It removes a node in the sparse matrix if the new value is 0/dead
	 *
	 * @param r the row value of the given position.
	 * @param c the column value of the given position.
	 * @param value the value of a non-empty cell.
	 * @param change the change to be affected.
	 */
	@Override
	public void setMatrixValue(int r, int c, T value, String change) {
		SentinelNode<T> tmpS = sentinelList.getSentinelNode(r);
		DataLinkedList<T> tmpD = tmpS.getEast();
		if(change.equals("death"))
		{
			tmpD.remove(r, c, "east");
		}
		else if(change.equals("birth"))
		{
			tmpD.insertAtLocation(r, c, value, "east");
		}
	}

	/**
	 * Increase rows.
	 *
	 * @param increaseDirection the direction in which to increase.
	 * @param value the value of a non-empty cell.
	 * @param nonValue the value of an empty cell.
	 */
	@Override
	public void increaseRows(String increaseDirection, T value, T nonValue) {
		if(increaseDirection.equals("top"))
		{
			if(cols > rows)
			{
				shiftMatrix("down", value, nonValue);
			}
			else
			{	
				sentinelList.insertBefore(0, 0, nonValue, "");
				cols++; //the shiftMatrix function will decrement cols after its operations
				rows++;
				shiftMatrix("left", value, nonValue);
			}
		}
		else if(increaseDirection.equals("bottom"))
		{
			if(cols > rows)
				rows++;
			else
			{
				sentinelList.insertAfter(rows-1, rows-1, nonValue, "");
				rows++;
			}
		}	
	}

	/**
	 * Increase cols.
	 *
	 * @param increaseDirection the direction in which to increase.
	 * @param value the value of a non-empty cell.
	 * @param nonValue the value of an empty cell.
	 */
	@Override
	public void increaseCols(String increaseDirection, T value, T nonValue) {
		if(increaseDirection.equals("left"))
		{
			if(rows > cols)
			{
				shiftMatrix("right", value, nonValue);
			}
			else
			{
				sentinelList.insertBefore(0, 0, nonValue, "");
				cols++;
				rows++;
				shiftMatrix("up", value, nonValue);
			}
		}
		else if(increaseDirection.equals("right"))
		{
			if(rows > cols)
				cols++;
			else
			{
				sentinelList.insertAfter(cols-1, cols-1, nonValue, "");
				cols++;
			}
		}
	}
	
	/**
	 * Shift matrix maintains same number of rows and columns, but shift all values in given direction.
	 *
	 * @param direction the direction in which to shift.
	 * @param value the value of a non-empty cell.
	 * @param nonValue the value of an empty cell.
	 */
	public void shiftMatrix(String direction, T value, T nonValue)
	{
		if(direction.equals("right"))
		{
			cols++;
			for(int i = cols - 1; i > 0; i--)
			{
				SentinelNode<T> tmpCurrent = sentinelList.getSentinelNode(i);
				SentinelNode<T> tmpPrevious = sentinelList.getSentinelNode(i-1);
				if(i - 1 == 0)
				{
					DataLinkedList<T> copy = (DataLinkedList<T>) tmpPrevious.getSouth().copyOf("south", value, nonValue);
					tmpCurrent.setSouth(copy);
					tmpPrevious.getSouth().getHead().setSouth(null);
				}
				else
				{
					DataLinkedList<T> copy = (DataLinkedList<T>) tmpPrevious.getSouth().copyOf("south", value, nonValue);
					tmpCurrent.setSouth(copy);
				}
			}
			for(int i = 0; i < Math.max(rows, cols); i++)
			{
				sentinelList.getSentinelNode(i).getSouth().augmentCol(1, 0,"south");	
				sentinelList.getSentinelNode(i).getEast().augmentCol(1, 0,"east");
				
				//col number must be changed back to 0 for head
				if(i == 0)
					sentinelList.getSentinelNode(i).getSouth().getHead().setC(0);
			}
		}
		else if(direction.equals("left"))
		{
			for(int i = 1; i < cols; i++)
			{
				SentinelNode<T> tmpCurrent = sentinelList.getSentinelNode(i);
				SentinelNode<T> tmpPrevious = sentinelList.getSentinelNode(i-1);
				if(i == cols - 1)
				{
					DataLinkedList<T> copy = (DataLinkedList<T>) tmpCurrent.getSouth().copyOf("south", value, nonValue);
					tmpPrevious.setSouth(copy);
					tmpCurrent.getSouth().getHead().setSouth(null);
				}
				else
				{
					DataLinkedList<T> copy = (DataLinkedList<T>) tmpCurrent.getSouth().copyOf("south", value, nonValue);
					tmpPrevious.setSouth(copy);
				}
			}
			for(int i = 0; i < Math.max(rows, cols); i++)
			{
				sentinelList.getSentinelNode(i).getSouth().augmentCol(-1, 0,"south");	
				sentinelList.getSentinelNode(i).getEast().augmentCol(-1, 0,"east");
				
				//col number must be changed for head of last node
				if(i == Math.max(rows, cols) - 1)
					sentinelList.getSentinelNode(i).getSouth().getHead().setC(Math.max(rows, cols) - 1);
			}
			cols--;
		}
		else if(direction.equals("down"))
		{
			rows++;
			for(int i = rows - 1; i > 0; i--)
			{
				SentinelNode<T> tmpCurrent = sentinelList.getSentinelNode(i);
				SentinelNode<T> tmpPrevious = sentinelList.getSentinelNode(i-1);
				if(i - 1 == 0)
				{
					DataLinkedList<T> copy = (DataLinkedList<T>) tmpPrevious.getEast().copyOf("east", value, nonValue);
					tmpCurrent.setEast(copy);
					tmpPrevious.getEast().getHead().setEast(null);
				}
				else
				{
					DataLinkedList<T> copy = (DataLinkedList<T>) tmpPrevious.getEast().copyOf("east", value, nonValue);
					tmpCurrent.setEast(copy);
				}
			}
			for(int i = 0; i < Math.max(rows, cols); i++)
			{
				sentinelList.getSentinelNode(i).getEast().augmentRow(1, 0,"east");
				sentinelList.getSentinelNode(i).getSouth().augmentRow(1, 0,"south");
				
				//row number must be changed back to 0 for head
				if(i == 0)
					sentinelList.getSentinelNode(i).getEast().getHead().setR(0);
			}
		}
		else if(direction.equals("up"))
		{
			for(int i = 1; i < rows; i++)
			{
				SentinelNode<T> tmpCurrent = sentinelList.getSentinelNode(i);
				SentinelNode<T> tmpPrevious = sentinelList.getSentinelNode(i-1);
				if(i == rows - 1)
				{
					DataLinkedList<T> copy = (DataLinkedList<T>) tmpCurrent.getEast().copyOf("east", value, nonValue);
					tmpPrevious.setEast(copy);
					tmpCurrent.getEast().getHead().setEast(null);
				}
				else
				{
					DataLinkedList<T> copy = (DataLinkedList<T>) tmpCurrent.getEast().copyOf("east", value, nonValue);
					tmpPrevious.setEast(copy);
				}
			}
			for(int i = 0; i < Math.max(rows, cols); i++)
			{
				sentinelList.getSentinelNode(i).getEast().augmentRow(-1, 0,"east");
				sentinelList.getSentinelNode(i).getSouth().augmentRow(-1, 0,"south");
				
				//row number must be changed for head of last node
				if(i == Math.max(rows, cols) - 1)
					sentinelList.getSentinelNode(i).getEast().getHead().setR(Math.max(rows, cols) - 1);
			}
			rows--;
		}
	}

	/**
	 * Number of rows.
	 *
	 * @return the int
	 */
	@Override
	public int numRows() 
	{
		return rows;
	}

	/**
	 * Number of cols.
	 *
	 * @return the int
	 */
	@Override
	public int numCols() 
	{
		return cols;
	}

	/**
	 * Deep copy of method returns a deep copy of the sparse matrix
	 *
	 * @param value the value of a non-empty cell.
	 * @param nonValue the value of an empty cell.
	 * @return the IMatrix copy
	 */
	@Override
	public IMatrix<T> deepCopyOf(T value, T nonValue) {
		IMatrix<T> copy = new SparseMatrix<T>();
		copy.setRows(this.rows);		
		copy.setCols(this.cols);
		ILinkedList<T> list =  this.sentinelList.copyOf("", value, nonValue);
		copy.setSentinelList(list);
		return copy;
	}

	/**
	 * Matrix 2 array.
	 *
	 * @param value the value of a non-empty cell.
	 * @param nonValue the value of an empty cell.
	 * @return the 2d generic array.
	 */
	@SuppressWarnings("unchecked")
	public T[][] matrix2Array(T value, T nonValue)
	{
		//create empty array of zeros
		T[][] array = (T[][]) new Object[rows][cols];
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				array[i][j] = nonValue;
			}
		}
		//fill array
		SentinelNode<T> tmp = sentinelList.getSentinelHead();
		while(tmp.getNext() != sentinelList.getSentinelHead())
		{
			DataNode<T> tmpEast = tmp.getEast().getHead();
			while(tmpEast != null)
			{
				if(tmpEast.getValue().toString().equals(value.toString()))
				{
					array[tmpEast.getR()][tmpEast.getC()] = value;
				}
				tmpEast = tmpEast.getEast();
			}
			tmp = tmp.getNext();
		}	
		DataNode<T> tmpEast = tmp.getEast().getHead();
		while(tmpEast != null)
		{
			if(tmpEast.getValue().toString().equals(value.toString()))
			{
				array[tmpEast.getR()][tmpEast.getC()] = value;
			}
			tmpEast = tmpEast.getEast();
		}
		return array;
	}

	/**
	 * To string generates a string representation of the matrix using the matrix2array method.
	 *
	 * @param value the value of a non-empty cell.
	 * @param nonValue the value of an empty cell.
	 * @return the string
	 */
	public String toString(T value, T nonValue)
	{
		String result = "";
		T[][] array = this.matrix2Array(value, nonValue);
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				result += array[i][j]; 
			}
			result += "\n";
		}
//		for(int j = 0; j < cols; j++)
//		{
//			result += array[rows-1][j];
//		}
		return result;	
	}
}
