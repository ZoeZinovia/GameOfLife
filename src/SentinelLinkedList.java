
/**
 * The Class SentinelLinkedList implements the IlinkedList<T> interface.
 *
 * @param <T> the generic type
 */
public class SentinelLinkedList<T> implements ILinkedList<T>
{

	private SentinelNode<T> head;
	
	/**
	 * Instantiates a new, empty sentinel linked list.
	 */
	public SentinelLinkedList()
	{
		setHead(null);
	}

	/**
	 * Gets the head.
	 *
	 * @return the head of the linked list
	 */
	public SentinelNode<T> getSentinelHead() {
		return head;
	}

	/**
	 * Sets the head.
	 *
	 * @param head the new head
	 */
	public void setHead(SentinelNode<T> head) {
		this.head = head;
	}
	
	/**
	 * Adds the first sentinel node.
	 *
	 * @param value the value of the new node.
	 * @param id the id of the new node.
	 */
	public void addFirst(T value, int id)
	{
		addFirst(id, id, value, "");
	}
	
	/**
	 * Adds the first.
	 *
	 * @param r the id of the sentinel node and the row of the data node in the data linked list in easterly direction.
	 * @param c the id of the sentinel node and the column of the data node in the data linked list in southerly direction.
	 * @param nonValue the value of an empty cell.
	 * @param direction the direction can be south or east.
	 */
	@Override
	public void addFirst(int r, int c, T nonValue, String direction)  //with SentinelNode, r=c
	{
		if(head == null)
		{
			head = new SentinelNode<T>(r, new DataLinkedList<T>(new DataNode<T>(-1, c, null, null, nonValue)), new DataLinkedList<T>(new DataNode<T>(r, -1, null, null, nonValue)), null);
			head.setNext(head);
		}
		else
		{
			SentinelNode<T> tmp = head;
			while(tmp.getNext() != head)
				tmp = tmp.getNext();
			head = new SentinelNode<T>(r, new DataLinkedList<T>(new DataNode<T>(-1, c, null, null, nonValue)), new DataLinkedList<T>(new DataNode<T>(r, -1, null, null, nonValue)), head);
			tmp.setNext(head);
		}	
	}

	/**
	 * Adds the last sentinel node.
	 *
	 * @param nonValue the value of an empty cell.
	 * @param id the id of the new node.
	 */
	public void addLast(int id, T nonValue)
	{
		addLast(id, id, nonValue, "");
	}
	
	/**
	 * Adds the last sentinel node.
	 *
	 * @param node the new node
	 * @param nonValue the value of an empty cell.
	 */
	public void addLast(SentinelNode<T> node, T nonValue)  //with SentinelNode, r=c
	{
		addLast(node.getId(), node.getId(), nonValue, "");
	}
	
	/**
	 * Adds the last sentinel node.
	 *
	 * @param r the id of the sentinel node and the row of the data node in the data linked list in easterly direction.
	 * @param c the id of the sentinel node and the column of the data node in the data linked list in southerly direction.
	 * @param nonValue the value of an empty cell.
	 * @param direction the direction can be south or east.
	 */
	@Override
	public void addLast(int r, int c, T nonValue, String direction) 
	{
		if(head == null)
			addFirst(r, c, nonValue, direction);
		
		else
		{
			SentinelNode<T> tmp = head;
			while(tmp.getNext() != head)
				tmp = tmp.getNext();
			tmp.setNext(new SentinelNode<T>(r, new DataLinkedList<T>(new DataNode<T>(-1, c, null, null, nonValue)), new DataLinkedList<T>(new DataNode<T>(r, -1, null, null, nonValue)), head));
		}
	}
	
	/**
	 * Insert after given location.
	 *
	 * @param id the id of the new node.
	 * @param nonValue the value of an empty cell.
	 */
	public void insertAfter(int id, T nonValue)
	{
		insertAfter(id, id, nonValue, "");
	}

	/**
	 * Insert after.
	 *
	 * @param r the id of the sentinel node and the row of the data node in the data linked list in easterly direction.
	 * @param c the id of the sentinel node and the column of the data node in the data linked list in southerly direction.
	 * @param nonValue the value of an empty cell.
	 * @param direction the direction can be south or east.
	 */
	@Override
	public void insertAfter(int r, int c, T nonValue, String direction) //for sentinel node c = r
	{
		int newRow = r + 1;
		if(!exists(r))
			throw new IllegalArgumentException("Provided node does not exist");
		SentinelNode<T> tmp = head;
		while(tmp.getNext() != head && tmp.getId() != r)
			tmp = tmp.getNext();
		if(tmp.getNext() != head) //found the node after which we want to insert the new node
		{
			tmp.setNext(new SentinelNode<T>(r, new DataLinkedList<T>(new DataNode<T>(-1, c, null, null, nonValue)), new DataLinkedList<T>(new DataNode<T>(r, -1, null, null, nonValue)), tmp.getNext())); //insert node
			while(tmp.getNext() != head) //increment all the id's of all of the nodes that follow
			{
				tmp = tmp.getNext();
				tmp.setId(++r);
			}
			tmp = head;
			tmp.augmentRowAndCol(1, newRow, 1);
			while(tmp.getNext() != head)
			{
				tmp.augmentRowAndCol(1, newRow, 1);//to increment all data node row and column values of S and E of each sentinel node after inserted node
				tmp = tmp.getNext();
			}
			tmp.augmentRowAndCol(1, newRow, 1);
		}
		else //insert after last node
		{
			tmp.setNext(new SentinelNode<T>(++r, new DataLinkedList<T>(new DataNode<T>(-1, c, null, null, nonValue)), new DataLinkedList<T>(new DataNode<T>(r, -1, null, null, nonValue)), head));
		}
	}

	/**
	 * Insert before given location.
	 *
	 * @param id the id of the new node.
	 * @param nonValue the value of an empty cell.
	 */
	public void insertBefore(int id, T nonValue)
	{
		insertBefore(id, id, nonValue, "");
	}
	
	/**
	 * Insert before given location.
	 *
	 * @param r the id of the sentinel node and the row of the data node in the data linked list in easterly direction.
	 * @param c the id of the sentinel node and the column of the data node in the data linked list in southerly direction.
	 * @param nonValue the value of an empty cell.
	 * @param direction the direction can be south or east.
	 */
	@Override
	public void insertBefore(int r, int c, T nonValue, String direction) 
	{
		int newRow = r;
		if(this.head == null) 
			throw new NullPointerException("The linked list is empty");
		if(!exists(r))
				throw new IllegalArgumentException("Provided node does not exist");
		if(head.getId() == r)//insert the new node at the head and augment all id values.
		{
			addFirst(r, c, nonValue, direction);
			SentinelNode<T> tmp = head;
			while(tmp.getNext() != head) //increment all the id's of all of the nodes that follow
			{
				tmp = tmp.getNext();
				tmp.setId(++r);
			}
			tmp = head;
			while(tmp.getNext() != head)
			{
				tmp.augmentRowAndCol(1, newRow, 0);//increment all data node row and column values of S and E of each sentinel node after inserted node
				tmp = tmp.getNext();
			}
			tmp.augmentRowAndCol(1, newRow, 0);
			return;
		}
		SentinelNode<T> previous = null;
		SentinelNode<T> current = head;
		while(current.getNext() != head && current.getId() != r)
		{
			previous = current;
			current = current.getNext();
		}
		if(current.getNext() != head)
		{
			previous.setNext(new SentinelNode<T>(r, new DataLinkedList<T>(new DataNode<T>(-1, c, null, null, nonValue)), new DataLinkedList<T>(new DataNode<T>(r, -1, null, null, nonValue)), current));
			SentinelNode<T> tmp = previous.getNext();
			while(tmp.getNext() != head) //increment all the id's of all of the nodes that follow
			{
				tmp = tmp.getNext();
				tmp.setId(++r);
			}
			tmp = head;
			while(tmp.getNext() != head)
			{
				tmp.augmentRowAndCol(1, newRow, 0);//increment all data node row and column values of S and E of each sentinel node after inserted node
				tmp = tmp.getNext();
			}
			tmp.augmentRowAndCol(1, newRow, 0);
		}
		else //insert node before last node
		{
			previous.setNext(new SentinelNode<T>(r, new DataLinkedList<T>(new DataNode<T>(-1, c, null, null, nonValue)), new DataLinkedList<T>(new DataNode<T>(r, -1, null, null, nonValue)), current));
			current.setId(++r);
			SentinelNode<T> tmp = head;
			while(tmp.getNext() != head)
			{
				tmp.augmentRowAndCol(1, newRow, 0);//increment all data node row and column values of S and E of each sentinel node after inserted node
				tmp = tmp.getNext();
			}
			tmp.augmentRowAndCol(1, newRow, 0);
		}
	}

	/**
	 * Removes node from given location.
	 *
	 * @param id the id of the new node.
	 */
	public void remove(int id)
	{
		remove(id, id, "");
	}
	
	/**
	 * Removes node from given location.
	 *
	 * @param r the id of the sentinel node and the row of the data node in the data linked list in easterly direction.
	 * @param c the id of the sentinel node and the column of the data node in the data linked list in southerly direction.
	 * @param direction the direction can be south or east.
	 */
	@Override
	public void remove(int r, int c, String direction) 
	{
		int newRow = r;
		if(!exists(r))
			throw new IllegalArgumentException("Provided node does not exist");
		SentinelNode<T> tmp = head;
		while(tmp.getNext() != head && tmp.getNext().getId() != r)
		{
			tmp = tmp.getNext();
		}
		if(tmp.getNext() != head) //found the node that we want to remove
		{
			tmp.setNext(tmp.getNext().getNext());
			while(tmp.getNext() != head) //have to decrement all the id's of all of the nodes that follow
			{
				tmp = tmp.getNext();
				tmp.setId(r++);
			}
			tmp = head;
			while(tmp.getNext() != head)
			{
				tmp.augmentRowAndCol(-1, newRow, 1);//also have to increment all data node row and column values of S and E of each sentinel node after inserted node
				tmp = tmp.getNext();
			}
			tmp.augmentRowAndCol(-1, newRow, 1);
		}
	}
	
	/**
	 * method to return a sentinel node from sentinel linked list at position r, c.
	 *
	 * @param id the id of the new node.
	 * @return the sentinel node
	 */
	public SentinelNode<T> getSentinelNode(int id)
	{
		if(!this.exists(id))
			throw new IllegalArgumentException("Provided node does not exist");
		SentinelNode<T> tmp = head;
		while(tmp.getNext() != head && tmp.getId() != id)
			tmp = tmp.getNext();
		return tmp;
	}
	
	/**
	 * CopyOf method returns a deep copy of the sentinel linked list.
	 *
	 * @param value the value of a non-empty cell.
	 * @param nonValue the value of an empty cell.
	 * @return the ILinkedList
	 */
	public ILinkedList<T> copyOf(T value, T nonValue)
	{
		return copyOf("", value, nonValue);
	}
	
	/**
 	 * CopyOf method returns a deep copy of the sentinel linked list.
	 *
	 * @param direction the direction can be south or east.
	 * @param value the value of a non-empty cell.
	 * @param nonValue the value of an empty cell.
	 * @return the ILinkedList
	 */
	@Override
	public ILinkedList<T> copyOf(String direction, T value, T nonValue) 
	{
		SentinelNode<T> tmp = head;
		if(tmp == null)
			return new SentinelLinkedList<T>();
		SentinelLinkedList<T> copy = new SentinelLinkedList<T>();
		while(tmp.getNext() != head)
		{
			DataLinkedList<T> tmpSouth = (DataLinkedList<T>) tmp.getSouth().copyOf("south", value, nonValue);
			DataLinkedList<T> tmpEast = (DataLinkedList<T>) tmp.getEast().copyOf("east", value, nonValue);
			copy.addLast(tmp.getId(), tmpSouth.getHead().getValue());
			copy.getSentinelNode(tmp.getId()).setSouth(tmpSouth);
			copy.getSentinelNode(tmp.getId()).setEast(tmpEast);
			tmp = tmp.getNext();
		}
		DataLinkedList<T> tmpSouth = (DataLinkedList<T>) tmp.getSouth().copyOf("south", value, nonValue);
		DataLinkedList<T> tmpEast = (DataLinkedList<T>) tmp.getEast().copyOf("east", value, nonValue);
		copy.addLast(tmp.getId(), tmpSouth.getHead().getValue());
		copy.getSentinelNode(tmp.getId()).setSouth(tmpSouth);
		copy.getSentinelNode(tmp.getId()).setEast(tmpEast);
		return copy;
	}

	/**
	 * Exists method verifies if a node with given id exists.
	 *
	 * @param id is the id of the sentinel node.
	 * @return true, if exists.
	 */
	public boolean exists(int id) 
	{
		return exists(id, id, "");
	}
	
	/**
	 * Exists method verifies if a node with given id exists.
	 *
	 * @param r the id of the sentinel node to look for.
	 * @param c not used but required be interface.
	 * @param direction the direction can be south or east.
	 * @return true, if the sentinel node is found in the list.
	 */
	@Override
	public boolean exists(int r, int c, String direction) {
		SentinelNode<T> tmp = head;
		if(tmp.getId() == r)
			return true;
		else {
			while(tmp.getNext() != head)
			{
				tmp = tmp.getNext();
				if (tmp.getId() == r)
					return true;
			}
		}
		return false;
	}
	
	/**
	 * To string generates a string representation of the sentinel linked list.
	 *
	 * @return the string
	 */
	public String toString()
	{
		SentinelNode<T> tmp = head;
		if(tmp == null)
			return "SentinelLinkedList:\nnull";
		String result = "SentinelLinkedList:\n" + tmp;
		while(tmp.getNext() != head)
		{
			tmp = tmp.getNext();
			result += tmp.toString();
		}
		return result;
	}

}
