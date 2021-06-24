import java.util.ArrayList;
import java.util.Scanner;

public class Client 
{
	
	final static int MAX_INT = Integer.MAX_VALUE;
	
	public static Cell[][] int2Cell(ArrayList<ArrayList<Integer>> values) throws IntputLimitExceededException
	{
		//convert data from array list of integers to 2D cell array
		if(values.size() > MAX_INT || values.get(0).size() > MAX_INT)
			throw new IntputLimitExceededException("Input matrix is too large");
		Cell[][] result = new Cell[values.size()][values.get(0).size()];
		for(int i = 0; i < values.size(); i++)
		{
			for(int j = 0; j < values.get(0).size(); j++)
			{
				if(values.get(i).get(j) != 0 && values.get(i).get(j) != 1)
					throw new IllegalArgumentException("Only int values of 0 and 1 are accepted.");
				result[i][j] = values.get(i).get(j) == 1? new Cell(1): new Cell(0);
			}
		}
		return result;
	}
	
	public static Cell[][] parseData(ArrayList<String> lines) throws EmptyMatrixException, IntputLimitExceededException
	{
		//parse data into correct format (ArrayList of ArrayList of Integers)
		ArrayList<ArrayList<Integer>> values = new ArrayList<ArrayList<Integer>>();
		if(lines.size() < 2)
			throw new EmptyMatrixException("Provided matrix cannot be empty");
		for(int i = 1; i < lines.size(); i++) 
		{
			ArrayList<Integer> tempIntegerList= new ArrayList<Integer>();
			String[] tempString = lines.get(i).split("");

				for(int j = 0; j < tempString.length; j++)
				{
					tempIntegerList.add(Integer.parseInt(tempString[j]));
				}
				values.add(tempIntegerList); 
		}
		return int2Cell(values);
	}

	public static void main(String[] args) throws Exception
	{
		// scan all values
		Scanner sc = new Scanner(System.in);
		ArrayList<String> lines = new ArrayList<String>();
		while(sc.hasNextLine())
		{
			String line = sc.nextLine();
			lines.add(line);
		}
		sc.close();
		try {
			//Parse data and create matrix
			int iterations = Integer.parseInt(lines.get(0));
			Cell[][] cells = parseData(lines);
			SparseMatrix<Cell> matrix = new SparseMatrix<Cell>(cells, new Cell(1), new Cell(0));
			
			//Create Game of Life board/object and transition as many times as required.
			GameOfLife GoL = new GameOfLife(matrix, matrix.numRows(), matrix.numCols());
			for(int i = 0; i < iterations-1; i++)
			{
				GoL.transition();
				System.out.print(GoL);	
				System.out.print("\n");	
			}
			GoL.transition();
			System.out.print(GoL);
		} catch(NumberFormatException e)
		{
			System.out.println(e + ". Only inputs of type int are accepted.");
		} catch(NullPointerException e)
		{
			System.out.println("NullPointerException: " + e.getMessage());
		} catch(IllegalArgumentException e)
		{
			System.out.println("IllegalArgumentException: " + e.getMessage());
		} catch(EmptyMatrixException e)
		{
			System.out.println("EmptyMatrixException: " + e.getMessage());
		} catch(IntputLimitExceededException e)
		{
			System.out.println("IntputLimitExceededException: " + e.getMessage());
		}
	}
}