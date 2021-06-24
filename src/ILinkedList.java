public interface ILinkedList<T> 
{
	
	void addFirst(int r, int c, T value, String direction);
	
	void addLast(int r, int c, T value, String direction);
	
	void insertAfter(int r, int c, T value, String direction);
	
	void insertBefore(int r, int c, T value, String direction);
	
	void remove(int r, int c, String direction);
	
	ILinkedList<T> copyOf(String direction, T value, T nonValue);
	
	boolean exists(int r, int c, String direction);
	
	SentinelNode<T> getSentinelNode(int id);
	
	SentinelNode<T> getSentinelHead();
	
	String toString();
	
}
