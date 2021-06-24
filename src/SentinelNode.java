
/**
 * The Class SentinelNode.
 *
 * @param <T> the generic type
 */
public class SentinelNode<T>
{

	private int id;
	private SentinelNode<T> next;
	private DataLinkedList<T> south;
	private DataLinkedList<T> east;
	
	/**
	 * Instantiates a new, empty sentinel node.
	 */
	public SentinelNode()
	{
		setSouth(new DataLinkedList<T>());
		setEast(new DataLinkedList<T>());
		setId(-1);
		setNext(null);
	}

	/**
	 * Instantiates a new sentinel node.
	 *
	 * @param id the id of the sentinel node.
	 * @param s the data linked list in the southerly direction.
	 * @param e the data linked list in the easterly direction.
	 * @param next the next sentinel node or back to head.
	 */
	public SentinelNode(int id, DataLinkedList<T> s, DataLinkedList<T> e, SentinelNode<T> next) //S and E will be data linked lists
	{
		setSouth(s);
		setEast(e);
		setId(id);
		setNext(next);
	}
	
	/**
	 * Gets the id of the sentinel node.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of the sentinel node.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the next sentinel node.
	 *
	 * @return the next
	 */
	public SentinelNode<T> getNext() {
		return next;
	}

	/**
	 * Sets the next sentinel node.
	 *
	 * @param next the new next
	 */
	public void setNext(SentinelNode<T> next) {
		this.next = next;
	}
	
	/**
	 * Gets the data linked list in the southerly direction.
	 *
	 * @return the south
	 */
	public DataLinkedList<T> getSouth() {
		return south;
	}

	/**
	 * Sets the data linked list in the southerly direction.
	 *
	 * @param south the new south
	 */
	public void setSouth(DataLinkedList<T> south) {
		this.south = south;
	}

	/**
	 * Gets the data linked list in the easterly direction.
	 *
	 * @return the east
	 */
	public DataLinkedList<T> getEast() {
		return east;
	}

	/**
	 * Sets the east.
	 *
	 * @param east the data linked list in the easterly direction.
	 */
	public void setEast(DataLinkedList<T> east) {
		this.east = east;
	}
	
	/**
	 * Augment row and col.
	 *
	 * @param amount the amount with which to increment or decrement.
	 * @param newRow the new row, which won't be augmented.
	 * @param flag the flag indicates that node has been inserted before if value is 0, inserted after or removed if flaf is 1.
	 */
	public void augmentRowAndCol(int amount, int newRow, int flag)
	{
		if (flag == 0)
		{
			if(this.getId() == newRow)
				return;
			DataLinkedList<T> tmpSouth = this.getSouth();	
			tmpSouth.augmentRowAndCol(amount, newRow, "south");
			DataLinkedList<T> tmpEast = this.getEast();
			tmpEast.augmentRowAndCol(amount, newRow, "east");
		}
		else if (flag == 1)
		{
			if(this.getId() == newRow)
			{
				this.getSouth().getHead().setC(newRow);
				this.getEast().getHead().setR(newRow);			
				return;
			}
			DataLinkedList<T> tmpSouth = this.getSouth();	
			tmpSouth.augmentRowAndCol(amount, newRow, "south");
			DataLinkedList<T> tmpEast = this.getEast();
			tmpEast.augmentRowAndCol(amount, newRow, "east");
		}
	}
	
	/**
	 * To string generates the string representation of a sentinel node.
	 *
	 * @return the string
	 */
	public String toString()
	{
		String result = "SN" + this.id + ", south: (" + this.getSouth() + "), east: (" + this.getEast() + "), next: (";
		if(this.next == null)
			result += "null)";
		else
			result += this.next.getId() + ")";
		result += "\n";
		return result;
	}
	
}
