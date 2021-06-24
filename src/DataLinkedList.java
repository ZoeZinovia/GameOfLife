
/**
 * The Class DataLinkedList.
 *
 * @param <T> the generic type
 */
public class DataLinkedList<T> implements ILinkedList<T>
{

	private DataNode<T> head;
	
	/**
	 * Instantiates a new empty data linked list.
	 */
	public DataLinkedList()
	{
		this.head = null;
	}
	
	/**
	 * Instantiates a new data linked list.
	 *
	 * @param node the node that will be placed at the head.
	 */
	public DataLinkedList(DataNode<T> node) 
	{
		this.head = node;
	}

	/**
	 * Gets the head.
	 *
	 * @return the head of the list.
	 */
	public DataNode<T> getHead() {
		return head;
	}

	/**
	 * Sets the head.
	 *
	 * @param head the new head of the list.
	 */
	public void setHead(DataNode<T> head) {
		this.head = head;
	}
	
	/**
	 * Adds the first node using an input node.
	 *
	 * @param newNode the new node that will be added at the head of the list.
	 * @param direction the direction can be south or east.
	 */
	public void addFirst(DataNode<T> newNode, String direction)
	{
		addFirst(newNode.getR(), newNode.getC(), newNode.getValue(), direction);
	}

	/**
	 * Adds the first node using the new r, c and value for that node.
	 *
	 * @param r the row of the new node.
	 * @param c the column of the new node.
	 * @param value the value of the new node.
	 * @param direction the direction can be south or east.
	 */
	@Override
	public void addFirst(int r, int c, T value, String direction) {
		if(direction.equals("south"))
			head = new DataNode<T>(r, c, head, null, value);
		else if(direction.equals("east"))
			head = new DataNode<T>(r, c, null, head, value);
	}

	/**
	 * Adds a new node to the end of the list.
	 *
	 * @param newNode the new node.
	 * @param direction the direction can be south or east.
	 */
	public void addLast(DataNode<T> newNode, String direction)
	{
		addLast(newNode.getR(), newNode.getC(), newNode.getValue(), direction);
	}
	
	/**
	 * Adds a new node to the end of the list.
	 *
	 * @param r the row of the new node.
	 * @param c the column of the new node.
	 * @param value the value of a non-empty cell.
	 * @param direction the direction can be south or east.
	 */
	@Override
	public void addLast(int r, int c, T value, String direction) 
	{
		if(head == null)
			addFirst(r, c, value, direction);
		
		else
		{
			DataNode<T> tmp = head;
			if(direction.equals("south"))
			{
				while(tmp.getSouth() != null)
					tmp = tmp.getSouth();
				tmp.setSouth(new DataNode<T>(r, c, null, null, value));
			}
			else if(direction.equals("east"))
			{
				while(tmp.getEast() != null)
					tmp = tmp.getEast();
				tmp.setEast(new DataNode<T>(r, c, null, null, value));
			}
		}
	}
	
	/**
	 * Insert after a given location.
	 *
	 * @param node the node that will be inserted.
	 * @param direction the direction can be south or east.
	 */
	public void insertAfter(DataNode<T> node, String direction) 
	{
		insertAfter(node.getR(), node.getC(), node.getValue(), direction);
	}
	
	/**
	 * Insert after.
	 *
	 * @param r the row after which to insert.
	 * @param c the column after which to insert.
	 * @param value the value of a non-empty cell.
	 * @param direction the direction can be south or east.
	 */
	@Override
	public void insertAfter(int r, int c, T value, String direction) 
	{
		DataNode<T> tmp = head;
		if(!exists(r, c, direction))
			throw new IllegalArgumentException("Provided node does not exist");
		if(direction.equals("south"))
		{
			while(tmp != null && (tmp.getR() != r || tmp.getC() != c))
				tmp = tmp.getSouth();
			if(tmp != null) //found the node after which to insert the new node
			{
				int newRow = r + 1;
				tmp.setSouth(new DataNode<T>(newRow, c, tmp.getSouth(), null, value)); //insert node
			}
		}
		else if(direction.equals("east"))
		{
			while(tmp != null && (tmp.getR() != r || tmp.getC() != c))
				tmp = tmp.getEast();
			if(tmp != null) //found the node after which to insert the new node
			{
				int newCol = c + 1;
				tmp.setEast(new DataNode<T>(r, newCol, null, tmp.getEast(), value)); //insert node
			}
		}
	}
	
	/**
	 * Insert before.
	 *
	 * @param node the node that will be inserted.
	 * @param direction the direction can be south or east.
	 */
	public void insertBefore(DataNode<T> node, String direction) 
	{
		insertBefore(node.getR(), node.getC(), node.getValue(), direction);
	}

	/**
	 * Insert before.
	 *
	 * @param r the row before which to insert.
	 * @param c the column before which to insert.
	 * @param value the value of a non-empty cell.
	 * @param direction the direction can be south or east.
	 */
	@Override
	public void insertBefore(int r, int c, T value, String direction) 
	{
		if(this.head == null)
			throw new NullPointerException("The linked list is empty");
		if(!exists(r, c, direction))
			throw new IllegalArgumentException("Provided node doesn't exist");
		if(head.getR() == r && head.getC() == c)// insert the new node at the head and augment all id values.
		{
			addFirst(r, c, value, direction);
			return;
		}
		DataNode<T> previous = null;
		DataNode<T> current = head;
		if(direction.equals("south"))
		{
			while(current != null && (current.getR() != r || current.getC() != c))
			{
				previous = current;
				current = current.getSouth();
			}
			if(current != null) //found place to insert
			{
				int newRow = r-1;
				previous.setSouth(new DataNode<T>(newRow, c, current, null, value));
			}
		}
		else if (direction.equals("east"))
		{
			while(current != null && (current.getR() != r || current.getC() != c))
			{
				previous = current;
				current = current.getEast();
			}
			if(current != null)
			{
				int newCol = c-1;
				previous.setEast(new DataNode<T>(r, newCol, null, current, value));
			}
		}
	}

	/**
	 * Insert node at a given location.
	 *
	 * @param node the node to insert.
	 * @param direction the direction can be south or east.
	 */
	public void insertAtLocation(DataNode<T> node, String direction)
	{
		insertAtLocation(node.getR(), node.getC(), node.getValue(), direction);
	}
	
	/**
	 * Insert node at a given location.
	 *
	 * @param r the row at which to insert.
	 * @param c the column at which to insert.
	 * @param value the value of a non-empty cell.
	 * @param direction the direction can be south or east.
	 */
	public void insertAtLocation(int r, int c, T value, String direction)
	{
		DataNode<T> tmp = head;
		if(exists(r, c, direction))
			throw new IllegalArgumentException("Node already exists");
		if(head == null)
			addFirst(r, c, value, direction);
		
		//south
		if(direction.equals("south"))
		{
			if(head.getR() > r && head.getC() == c)
			{
				addFirst(r, c, value, direction);
				return;
			}
			while(tmp.getSouth() != null && (tmp.getSouth().getR() < r || tmp.getSouth().getC() != c))
				tmp = tmp.getSouth();
			if(tmp.getSouth() != null) //found the node before which we want to insert the new node
				tmp.setSouth(new DataNode<T>(r, c, tmp.getSouth(), null, value));
			else //insert at end
				this.addLast(r, c, value, "south");
		}
		
		//east
		else if(direction.equals("east"))
		{
			if(head.getR() == r && head.getC() > c)
			{
				addFirst(r, c, value, direction);
				return;
			}
			while(tmp.getEast() != null && (tmp.getEast().getR() != r || tmp.getEast().getC() < c))
			{
				tmp = tmp.getEast();
			}
			if(tmp.getEast() != null) //found the node after which we want to insert the new node
				tmp.setEast(new DataNode<T>(r, c, null, tmp.getEast(), value)); //insert node
			else //must insert at end
				this.addLast(r, c, value, "east");
				
		}
	}
	
	/**
	 * Removes a node at the given location.
	 *
	 * @param r the row at which to remove.
	 * @param c the column at which to remove.
	 * @param direction the direction can be south or east.
	 */
	@Override
	public void remove(int r, int c, String direction) {
		DataNode<T> tmp = head;
		if(!exists(r, c, direction))
			throw new IllegalArgumentException("Node does not exist");
		if(direction.equals("south"))
		{
			if(head.getR() == r && head.getC() == c)// insert the new node at the head and augment all id values.
			{
				head = head.getSouth();
				return;
			}
			while(tmp.getSouth() != null && (tmp.getSouth().getR() != r || tmp.getSouth().getC() != c))
				tmp = tmp.getSouth();
			if(tmp.getSouth() != null) //found the node to remove
				tmp.setSouth(tmp.getSouth().getSouth());
		}
		else if(direction.equals("east"))
		{
			if(head.getR() == r && head.getC() == c)//we need to insert the new node at the head and augment all id values.
			{
				head = head.getEast();
				return;
			}
			while(tmp.getEast() != null && (tmp.getEast().getR() != r || tmp.getEast().getC() != c))
				tmp = tmp.getEast();
			if(tmp.getEast() != null) //found the node to remove
				tmp.setEast(tmp.getEast().getEast());
		}
	}

	/**
	 * CopyOf makes a deep copy of the linked list.
	 *
	 * @param direction the direction can be south or east.
	 * @param value the value of a non empty-cell.
	 * @param nonValue the value of an empty-cell.
	 * @return the ILinkedList.
	 */
	@Override
	public ILinkedList<T> copyOf(String direction, T value, T nonValue) 
	{
		DataNode<T> tmp = head;	
		DataLinkedList<T> copy = new DataLinkedList<T>();
		if(head == null)
			return copy;
		if(direction.equals("south"))
		{
			while(tmp != null)
			{
				DataNode<T> tmpCopy = tmp.copyOf(value, nonValue);
				copy.addLast(tmpCopy, "south");
				tmp = tmp.getSouth();
			}
		}
		else if(direction.equals("east"))
		{
			while(tmp != null)
			{
				DataNode<T> tmpCopy = tmp.copyOf(value, nonValue);
				copy.addLast(tmpCopy, "east");
				tmp = tmp.getEast();
			}
		}
		return copy;
	}

	/**
	 * Exists verifies whether a given node exists in the linked list.
	 *
	 * @param r the row to be checked.
	 * @param c the column to be checked.
	 * @param direction the direction can be south or east.
	 * @return true, if a node is found in the given location.
	 */
	@Override
	public boolean exists(int r, int c, String direction) {
		if(head == null)
			return false;
		if(head.getR() == r && head.getC() == c)
			return true;
		DataNode<T> tmp = head;
		if(direction.equals("south"))
		{
			while(tmp != null && (tmp.getR() != r || tmp.getC() != c))
				tmp = tmp.getSouth();
			if(tmp != null)
				return true;
		}
		else if(direction.equals("east"))
		{
			while(tmp != null && (tmp.getR() != r || tmp.getC() != c))
				tmp = tmp.getEast();
			if(tmp != null)
				return true;
		}
		return false;
	}
	
	/**
	 * method to return a data node from data linked list at position r, c.
	 *
	 * @param r the row of the node to be retrieved.
	 * @param c the column of the node to be retrieved.
	 * @param direction the direction can be south or east.
	 * @return the data node at the given location
	 * @pre the node to be returned must exist in the linked list.
	 * @post returns DataNode with given r and c
	 */
	public DataNode<T> getDataNode(int r, int c, String direction)
	{
		if(!exists(r, c, direction))
			throw new IllegalArgumentException("Node does not exist");
		DataNode<T> result = null;
		DataNode<T> tmp = head;
		if(direction.equals("south"))
		{
			while(tmp != null & (tmp.getR() != r || tmp.getC() != c))
			{
				tmp = tmp.getSouth();
			}
			if(tmp != null)
			{
				result = tmp;
			}
		}
		if(direction.equals("east"))
		{
			while(tmp != null & (tmp.getR() != r || tmp.getC() != c))
			{
				tmp = tmp.getEast();
			}
			if(tmp != null)
			{
				result = tmp;
			}
		}
		return result;
	}
	
	/**
	 * Augment row and column.
	 *
	 * @param amount the amount to increment or decrement.
	 * @param threshold the row or column value must exceed the threshold in order to be incremented/decremented.
	 * @param direction the direction can be south or east.
	 */
	public void augmentRowAndCol(int amount, int threshold, String direction)
	{
		DataNode<T> tmp = head;
		if(direction.equals("south"))
		{
			if(tmp.getR() >= threshold)
				tmp.setR(tmp.getR() + amount);
			if(tmp.getC() >= threshold)
				tmp.setC(tmp.getC() + amount);
			while(tmp.getSouth() != null)
			{
				tmp = tmp.getSouth();
				if(tmp.getR() >= threshold)
					tmp.setR(tmp.getR() + amount);
				if(tmp.getC() >= threshold)
					tmp.setC(tmp.getC() + amount);
			}
		}
		else if(direction.equals("east"))
		{
			if(tmp.getR() >= threshold)
				tmp.setR(tmp.getR() + amount);
			if(tmp.getC() >= threshold)
				tmp.setC(tmp.getC() + amount);
			while(tmp.getEast() != null)
			{
				tmp = tmp.getEast();
				if(tmp.getR() >= threshold)
					tmp.setR(tmp.getR() + amount);
				if(tmp.getC() >= threshold)
					tmp.setC(tmp.getC() + amount);
			}
		}
	}
	
	/**
	 * Augment row only.
	 *
	 * @param amount the amount to increment or decrement.
	 * @param threshold the row value must exceed the threshold in order to be incremented/decremented.
	 * @param direction the direction can be south or east.
	 */
	// used by shiftMatrix
	public void augmentRow(int amount, int threshold, String direction)
	{
		DataNode<T> tmp = head;
		if(direction.equals("south"))
		{
			if(tmp.getR() >= threshold)
				tmp.setR(tmp.getR() + amount);
			while(tmp.getSouth() != null)
			{
				tmp = tmp.getSouth();
				if(tmp.getR() >= threshold)
					tmp.setR(tmp.getR() + amount);
			}
		}
		else if(direction.equals("east"))
		{
			if(tmp.getR() >= threshold)
				tmp.setR(tmp.getR() + amount);
			while(tmp.getEast() != null)
			{
				tmp = tmp.getEast();
				if(tmp.getR() >= threshold)
					tmp.setR(tmp.getR() + amount);
			}	
		}
	}
	
	/**
	 * Augment col.
	 *
	 * @param amount the amount to increment or decrement.
	 * @param threshold the column value must exceed the threshold in order to be incremented/decremented.
	 * @param direction the direction can be south or east.
	 */
	// used by shiftMatrix
	public void augmentCol(int amount, int threshold, String direction)
	{
		DataNode<T> tmp = head;
		if(direction.equals("south"))
		{
			if(tmp.getC() >= threshold)
			tmp.setC(tmp.getC() + amount);
			while(tmp.getSouth() != null)
			{
				tmp = tmp.getSouth();
				if(tmp.getC() >= threshold)
					tmp.setC(tmp.getC() + amount);
			}
		}
		else if(direction.equals("east"))
		{
			if(tmp.getC() >= threshold)
				tmp.setC(tmp.getC() + amount);
			while(tmp.getEast() != null)
			{
				tmp = tmp.getEast();
				if(tmp.getC() >= threshold)
					tmp.setC(tmp.getC() + amount);
			}
		}
	}
	
	/**
	 * To string function generates a string representation of the linked list. To facilitate visibility, the string is generated in the easterly direction (row-wise).
	 *
	 * @return the string
	 */
	@Override
	public String toString()
	{
		DataNode<T> tmp = head;
		String result = "DataLinkedList: [" + tmp  + "]";
		while(tmp.getEast() != null)
		{
			result += ", [";
			tmp = tmp.getEast();
			result += tmp.toString() + "]";
		}
		return result;
	}
	
	/**
	 * To string south generates the string in the southerly direction.
	 *
	 * @return the string
	 */
	public String toStringSouth()
	{
		DataNode<T> tmp = this.getHead();
		if(tmp == null)
			return "DataLinkedList: [null]";
		String result = "DataLinkedList: [" + tmp  + "]";
		while(tmp.getSouth() != null)
		{
			result += ", [";
			tmp = tmp.getSouth();
			result += tmp.toString() + "]";
		}
		return result;
	}
	
	@Override
	public SentinelNode<T> getSentinelNode(int id)
	{
		return null;
	}
	
	@Override
	public SentinelNode<T> getSentinelHead()
	{
		return null;
	}
}
