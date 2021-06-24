
public interface IMatrix<T> 
{

	T getMatrixValue(int r, int c, T nonValue);
	
	void setMatrixValue(int r, int c, T value, String change);
	
	void increaseRows(String direction, T filler, T value);
	
	void increaseCols(String direction, T filler, T value);
	
	void setRows(int rows);
	
	void setCols(int cols);
	
	int numRows();
	
	int numCols();
	
	void setSentinelList(ILinkedList<T> list);
	
	void shiftMatrix(String direction, T value, T nonValue);
	
	IMatrix<T> deepCopyOf(T value, T nonValue);
	
	String toString(T value, T nonValue);
	
}
