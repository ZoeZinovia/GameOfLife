
/**
 * The Class GameOfLife.
 */
public class GameOfLife  
{
	
	private IMatrix<Cell> matrix;
	private int numRows;
	private int numCols;
	
	/**
	 * Instantiates a new game of life.
	 *
	 * @param matrix the matrix
	 * @param rows the number of rows
	 * @param cols the number of cols
	 * @pre provided matrix must not be empty.
	 * @pre number of rows and columns must not exceed max allowable integer value.
	 * @pre matrix values must be of type cell.
	 * @post creates a new game of life.
	 */
	public GameOfLife(IMatrix<Cell> matrix, int rows, int cols)
	{
		 setMatrix(matrix);
		 setNumRows(rows);
		 setNumCols(cols);
	}
	
	/**
	 * Transition method is responsible for evolving to the next generation of the board given the transition rules.
	 */
	public void transition()
	{
		//check if matrix needs to grow in any direction
		if(verifyHorizontalBorder(0))
		{
			this.matrix.increaseRows("top", new Cell(1), new Cell(0));
			this.numRows += 1;
		}
		if(verifyHorizontalBorder(this.numRows-1))
		{
			this.matrix.increaseRows("bottom", new Cell(1), new Cell(0));
			this.numRows += 1;
		}
		if(verifyVerticalBorder(0))
		{
			this.matrix.increaseCols("left", new Cell(1), new Cell(0));
			this.numCols += 1;
		}
		if(verifyVerticalBorder(this.numCols-1))
		{
			this.matrix.increaseCols("right", new Cell(1), new Cell(0));
			this.numCols += 1;
		}
		
		//create deep copy of matrix	
		IMatrix<Cell> originalMatrix = this.matrix.deepCopyOf(new Cell(1), new Cell(0));
		
		//check which cells die and which ones live
		for(int i = 0; i < this.numRows; i++)
		{
			for(int j = 0; j < this.numCols; j++)
			{
				if(!(numCols == 1 && numRows ==1))
				{	
					//Get number of living neighbours
					int aliveNeighbours = RowLoop(i, j, originalMatrix);
				
					//Game of Life Rules
						//Rule 1: if cell is alive but has less than 2 neighbours, it dies
						if(originalMatrix.getMatrixValue(i, j, new Cell(0)).isAlive() && aliveNeighbours < 2)
						{
							this.matrix.setMatrixValue(i, j, new Cell(0), "death");
						}
						//Rule 2: if cell is alive but has more than 3 neighbours, it dies
						else if(originalMatrix.getMatrixValue(i, j, new Cell(0)).isAlive() && aliveNeighbours > 3)
						{
							this.matrix.setMatrixValue(i, j, new Cell(0), "death");
						}
						//Rule 3: if a dead cell has 3 neighbours, it is born
						else if(!originalMatrix.getMatrixValue(i, j, new Cell(0)).isAlive() && aliveNeighbours == 3)
						{
							this.matrix.setMatrixValue(i, j, new Cell(1), "birth");
						}
						//Rule 4: nothing changes
				}
				else if(numRows == 1 && numCols == 1 && originalMatrix.getMatrixValue(0, 0, new Cell(0)).isAlive())
				{
					this.matrix.setMatrixValue(0, 0, new Cell(0), "death");
				}
			}
		}
	}
	
	/**
	 * Row loop method is used to calculate the number of living neighbours using the ColLoop method.
	 *
	 * @param i the row of the node that is being checked.
	 * @param j the column of the node that is being checked.
	 * @param originalMatrix the original matrix, which is unchanged.
	 * @return the number of living neighbours as int.
	 */
	public int RowLoop(int i, int j, IMatrix<Cell> originalMatrix)
	{
		int aliveNeighbours = 0;
		if(i == 0)
		{
			for(int k = 0; k <= 1; k++)
			{
				aliveNeighbours += colLoop(i, j, k, originalMatrix);
			}
		}
		else if(i == this.numRows-1)
		{
			for(int k = -1; k <= 0; k++)
			{
				aliveNeighbours += colLoop(i, j, k, originalMatrix);
			}
		}
		else
		{
			for(int k = -1; k <= 1; k++)
			{
				aliveNeighbours += colLoop(i, j, k, originalMatrix);
			}
		}
		return aliveNeighbours;
	}
	
	/**
	 * Col loop function loops through the columns
	 *
	 * @param i the row of the node that is being checked.
	 * @param j the column of the node that is being checked.
	 * @param k the row of the current neighbour of node i, j.
	 * @param originalMatrix the original matrix, which remains unchanged.
	 * @return the number of living neighbours found.
	 */
	public int colLoop(int i, int j, int k, IMatrix<Cell> originalMatrix)
	{
		int result = 0;
		if(j == 0)
		{
			for(int l = 0; l <= 1; l++)
			{
				result += checkNeighbour(i, j, k, l, originalMatrix);
			}
		}
		else if(j == this.numCols-1)
		{
			for(int l = -1; l <= 0; l++)
			{
				result += checkNeighbour(i, j, k, l, originalMatrix);
			}
		}
		else
		{
			for(int l = -1; l <= 1; l++)
			{
				result += checkNeighbour(i, j, k, l, originalMatrix);
			}	
		}
		return result;
	}
	
	/**
	 * Checks whether the current neighbour is dead or alive.
	 *
	 * @param i the row of the node that is being checked.
	 * @param j the column of the node that is being checked.
	 * @param k the row of the current neighbour of node i, j.
	 * @param l the column of the current neighbour of node i, j.
	 * @param originalMatrix the original matrix
	 * @return the number of living neighbours found.
	 */
	public int checkNeighbour(int i, int j, int k, int l, IMatrix<Cell> originalMatrix)
	{
		int result = 0;
		if(!(k == 0 && l == 0)) //not a neighbour but the current cell	
		{
			Cell cell = originalMatrix.getMatrixValue(i+k, j+l, new Cell(0)); //will return a Cell
			result = cell.getCellValue();
		}
		return result;
	}
	
	/**
	 * Verify if horizontal border requires growth.
	 *
	 * @param row the row being checked.
	 * @return true, if growth is needed.
	 */
	public boolean verifyHorizontalBorder(int row)
	{
		boolean result = false;
		int c = matrix.numCols();
		int countConsecutiveAlive = 0;
		int i = 0;
		while(countConsecutiveAlive < 3 && i < c)
		{
			if(matrix.getMatrixValue(row, i, new Cell(0)).isAlive())
				countConsecutiveAlive++;
			else
				countConsecutiveAlive = 0;
			i++;
		}
		if(countConsecutiveAlive >= 3)
			result = true;
		return result;
	}
	
	/**
	 * Verify if vertical border requires growth.
	 *
	 * @param col the column being checked.
	 * @return true, if growth is needed.
	 */
	public boolean verifyVerticalBorder(int col)
	{
		boolean result = false;
		int r = matrix.numRows();
		int countConsecutiveAlive = 0;
		int i = 0;
		while(countConsecutiveAlive < 3 && i < r)
		{
			if(matrix.getMatrixValue(i, col, new Cell(0)).isAlive())
				countConsecutiveAlive++;
			else
				countConsecutiveAlive = 0;
			i++;
		}
		if(countConsecutiveAlive >= 3)
			result = true;
		return result;
	}
	
	/**
	 * Gets the matrix.
	 *
	 * @return the matrix
	 */
	public IMatrix<Cell> getMatrix() {
		return matrix;
	}

	/**
	 * Sets the matrix.
	 *
	 * @param matrix the new matrix
	 */
	public void setMatrix(IMatrix<Cell> matrix) {
		this.matrix = matrix;
	}

	/**
	 * Gets the number of rows.
	 *
	 * @return the number of rows
	 */
	public int getNumRows() {
		return numRows;
	}

	/**
	 * Sets the number of rows.
	 *
	 * @param numRows the new number of rows
	 */
	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	/**
	 * Gets the number of cols.
	 *
	 * @return the number of cols
	 */
	public int getNumCols() {
		return numCols;
	}

	/**
	 * Sets the number of cols.
	 *
	 * @param numCols the new number of cols
	 */
	public void setNumCols(int numCols) {
		this.numCols = numCols;
	}
	
	/**
	 * ToString() genertated the string representation of the Game of Life.
	 *
	 * @return the string
	 */
	public String toString()
	{
		return this.matrix.toString(new Cell(1), new Cell(0));
	}

}
