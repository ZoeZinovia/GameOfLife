
/**
 * The Class DataNode.
 *
 * @param <T> the generic type
 */
public class DataNode<T>
{

	private int r;
	private int c;
	private T value;
	private DataNode<T> south;
	private DataNode<T> east;
	
	/**
	 * Instantiates a new data node.
	 *
	 * @param value the value of a non-empty cell.
	 */
	public DataNode(T value) //to create an empty data node
	{
		setSouth(null);
		setEast(null);
		setR(-1);
		setC(-1);
		setValue(value);
	}

	/**
	 * Instantiates a new data node.
	 *
	 * @param row the row of the data node.
	 * @param col the column of the data node.
	 * @param s the next data node in the southerly direction.
	 * @param e the next data node in the easterly direction.
	 * @param value the value of the data node.
	 */
	public DataNode(int row, int col, DataNode<T> s, DataNode<T> e, T value) {
		setSouth(s);
		setEast(e);
		setR(row);
		setC(col);
		setValue(value);
	}

	/**
	 * Gets the row.
	 *
	 * @return the row of the data node.
	 */
	public int getR() {
		return r;
	}

	/**
	 * Sets the row.
	 *
	 * @param r the new row of the data node.
	 */
	public void setR(int r) {
		this.r = r;
	}

	/**
	 * Gets the column.
	 *
	 * @return the column of the data node.
	 */
	public int getC() {
		return c;
	}

	/**
	 * Sets the column.
	 *
	 * @param c the new column of the data node.
	 */
	public void setC(int c) {
		this.c = c;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value of the data node.
	 */
	public T getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value of the data node.
	 */
	public void setValue(T value) {
		this.value = value;
	}
	
	/**
	 * Gets the south.
	 *
	 * @return the next data node in the southerly direction.
	 */
	public DataNode<T> getSouth() {
		return south;
	}

	/**
	 * Sets the south.
	 *
	 * @param south the next data node in the southerly direction.
	 */
	public void setSouth(DataNode<T> south) {
		this.south = south;
	}

	/**
	 * Gets the east.
	 *
	 * @return the next data node in the easterly direction.
	 */
	public DataNode<T> getEast() {
		return east;
	}

	/**
	 * Sets the east.
	 *
	 * @param east the next data node in the easterly direction.
	 */
	public void setEast(DataNode<T> east) {
		this.east = east;
	}
	
	/**
	 * CopyOf returns a deep copy of the data node.
	 *
	 * @param passedValue the passed value
	 * @param nonValue the non value/filler.
	 * @return the data node copy.
	 */
	public DataNode<T> copyOf(T passedValue, T nonValue)
	{
		T tmpValue = passedValue;
		DataNode<T> tmpSouth = null;
		DataNode<T> tmpEast = null;
		if(this.getR() == -1 || this.getC() == -1)
			tmpValue = nonValue;			
		if(this.getSouth() == null && this.getEast() != null)
			tmpEast = getEast().copyOf(passedValue, nonValue);
		else if(this.getSouth() != null && this.getEast() == null)
			tmpSouth = getSouth().copyOf(passedValue, nonValue);
		else if(this.getSouth() != null && this.getEast() != null)
		{
			tmpSouth = getSouth().copyOf(passedValue, nonValue);
			tmpEast = getEast().copyOf(passedValue, nonValue);
		}
		return new DataNode<T>(getR(), getC(), tmpSouth, tmpEast, tmpValue);
	}
	
	/**
	 * To string generates a string representation of the data node.
	 *
	 * @return the string
	 */
	public String toString()
	{
		String result =  "row: " + this.r + ", col: " + this.c + ", value: " + value.toString() + ", south: ";
		if(this.getSouth() == null)
			result += "(null, null), east: ";
		else
			result += "(" + this.getSouth().getR() + ", " + this.getSouth().getC() + "), east: ";
		if(this.getEast() == null)
			result += "(null, null)";
		else
			result += "(" + this.getEast().getR() + ", " + this.getEast().getC() + ")"; 
		return result;
	}
}
