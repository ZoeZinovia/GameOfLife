import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.Assert;

class GoL_All_Tests {

	// -- GameOfLife class Tests with Array Matrix-- //
	@Test
	void testGameOfLifeConstructor0() 
	{
		//Example 1
		Cell[][] arr1 = {
				{new Cell(0), new Cell(1), new Cell(1), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(1)}
		};
		IMatrix<Cell> matrix1 = new ArrayMatrix<Cell>(arr1);
		GameOfLife gol1 = new GameOfLife(matrix1, matrix1.numRows(), matrix1.numCols());
		Assert.assertEquals(matrix1.toString(new Cell(1), new Cell(0)), gol1.toString());
		
		//Example 2
		Cell[][] arr2 = {
				{new Cell(1), new Cell(0)},
				{new Cell(1), new Cell(1)},
				{new Cell(0), new Cell(0)}
		};
		IMatrix<Cell> matrix2 = new ArrayMatrix<Cell>(arr2);
		GameOfLife gol2 = new GameOfLife(matrix2, matrix2.numRows(), matrix2.numCols());
		Assert.assertEquals(matrix2.toString(new Cell(1), new Cell(0)), gol2.toString());
	}
	
	@Test
	void testGameOfLifeSetMatrix0() 
	{
		//Example 1
		Cell[][] arr1 = {
				{new Cell(0), new Cell(1), new Cell(1), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(1)}
		};
		IMatrix<Cell> matrixEmpty = new ArrayMatrix<Cell>();
		IMatrix<Cell> matrix1 = new ArrayMatrix<Cell>(arr1);
		GameOfLife gol1 = new GameOfLife(matrixEmpty, matrixEmpty.numRows(), matrixEmpty.numCols());
		gol1.setMatrix(matrix1);
		Assert.assertEquals(matrix1.toString(new Cell(1), new Cell(0)), gol1.toString());
		
		//Example 2
		Cell[][] arr2 = {
				{new Cell(1), new Cell(0)},
				{new Cell(1), new Cell(1)},
				{new Cell(0), new Cell(0)}
		};

		IMatrix<Cell> matrix2 = new ArrayMatrix<Cell>(arr2);
		GameOfLife gol2 = new GameOfLife(matrixEmpty, matrixEmpty.numRows(), matrixEmpty.numCols());
		gol2.setMatrix(matrix2);
		Assert.assertEquals(matrix2.toString(new Cell(1), new Cell(0)), gol2.toString());
	}
	
	@Test
	void testTransition0()
	{
		//Example 1
		Cell[][] arr1 = {
				{new Cell(0), new Cell(1), new Cell(0)},
				{new Cell(0), new Cell(1), new Cell(0)},
				{new Cell(0), new Cell(1), new Cell(0)}
		};
		
		Cell[][] answer1 = {
				{new Cell(0), new Cell(0), new Cell(0)},
				{new Cell(1), new Cell(1), new Cell(1)},
				{new Cell(0), new Cell(0), new Cell(0)}
		};
		
		IMatrix<Cell> matrix1 = new ArrayMatrix<Cell>(arr1);
		IMatrix<Cell> matrixAnswer1 = new ArrayMatrix<Cell>(answer1);

		GameOfLife gol1 = new GameOfLife(matrix1, matrix1.numRows(), matrix1.numCols());
		gol1.transition();
		Assert.assertEquals(matrixAnswer1.toString(new Cell(1), new Cell(0)), gol1.toString());	
		
		//Example 2
		Cell[][] arr2 = {
				{new Cell(1), new Cell(1), new Cell(0)},
				{new Cell(1), new Cell(0), new Cell(1)},
				{new Cell(1), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0)}
		};
		
		Cell[][] answer2 = {
				{new Cell(0), new Cell(1), new Cell(1), new Cell(0)},
				{new Cell(1), new Cell(1), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(1), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
		};

		IMatrix<Cell> matrix2 = new ArrayMatrix<Cell>(arr2);
		IMatrix<Cell> matrixAnswer2 = new ArrayMatrix<Cell>(answer2);
		GameOfLife gol2 = new GameOfLife(matrix2, matrix2.numRows(), matrix2.numCols());
		gol2.transition();
		Assert.assertEquals(matrixAnswer2.toString(new Cell(1), new Cell(0)), gol2.toString());
		
		//Example 3
		Cell[][] arr3 = {
				{new Cell(1), new Cell(1)},
				{new Cell(1), new Cell(1)}
		};
		
		Cell[][] answer3 = {
				{new Cell(1), new Cell(1)},
				{new Cell(1), new Cell(1)}
		};

		IMatrix<Cell> matrix3 = new ArrayMatrix<Cell>(arr3);
		IMatrix<Cell> matrixAnswer3 = new ArrayMatrix<Cell>(answer3);
		GameOfLife gol3 = new GameOfLife(matrix3, matrix3.numRows(), matrix3.numCols());
		gol3.transition();
		Assert.assertEquals(matrixAnswer3.toString(new Cell(1), new Cell(0)), gol3.toString());
		
		//Example 4
		Cell[][] arr4 = {
				{new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(1), new Cell(1), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(1), new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(1), new Cell(1), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)}
		};
		
		Cell[][] answer4 = {
				{new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(1), new Cell(0), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(1), new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(1), new Cell(0), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)}
		};

		IMatrix<Cell> matrix4 = new ArrayMatrix<Cell>(arr4);
		IMatrix<Cell> matrixAnswer4 = new ArrayMatrix<Cell>(answer4);
		GameOfLife gol4 = new GameOfLife(matrix4, matrix4.numRows(), matrix4.numCols());
		gol4.transition();
		Assert.assertEquals(matrixAnswer4.toString(new Cell(1), new Cell(0)), gol4.toString());
	}
	
	// -- GameOfLife class Tests with Sparse Matrix-- //
	
		@Test
		void testGameOfLifeConstructor1() 
		{
			//Example 1
			Cell[][] arr1 = {
					{new Cell(0), new Cell(1), new Cell(1), new Cell(0)},
					{new Cell(0), new Cell(0), new Cell(0), new Cell(1)}
			};
			IMatrix<Cell> matrix1 = new SparseMatrix<Cell>(arr1, new Cell(1), new Cell(0));
			GameOfLife gol1 = new GameOfLife(matrix1, matrix1.numRows(), matrix1.numCols());
			Assert.assertEquals(matrix1.toString(new Cell(1), new Cell(0)), gol1.toString());
			
			//Example 2
			Cell[][] arr2 = {
					{new Cell(1), new Cell(0)},
					{new Cell(1), new Cell(1)},
					{new Cell(0), new Cell(0)}
			};
			IMatrix<Cell> matrix2 = new SparseMatrix<Cell>(arr2, new Cell(1), new Cell(0));
			GameOfLife gol2 = new GameOfLife(matrix2, matrix2.numRows(), matrix2.numCols());
			Assert.assertEquals(matrix2.toString(new Cell(1), new Cell(0)), gol2.toString());
		}
		
		@Test
		void testGameOfLifeSetMatrix1() 
		{
			//Example 1
			Cell[][] arr1 = {
					{new Cell(0), new Cell(1), new Cell(1), new Cell(0)},
					{new Cell(0), new Cell(0), new Cell(0), new Cell(1)}
			};
			IMatrix<Cell> matrixEmpty = new SparseMatrix<Cell>();
			IMatrix<Cell> matrix1 = new SparseMatrix<Cell>(arr1, new Cell(1), new Cell(0));
			GameOfLife gol1 = new GameOfLife(matrixEmpty, matrixEmpty.numRows(), matrixEmpty.numCols());
			gol1.setMatrix(matrix1);
			Assert.assertEquals(matrix1.toString(new Cell(1), new Cell(0)), gol1.toString());
			
			//Example 2
			Cell[][] arr2 = {
					{new Cell(1), new Cell(0)},
					{new Cell(1), new Cell(1)},
					{new Cell(0), new Cell(0)}
			};

			IMatrix<Cell> matrix2 = new SparseMatrix<Cell>(arr2, new Cell(1), new Cell(0));
			GameOfLife gol2 = new GameOfLife(matrixEmpty, matrixEmpty.numRows(), matrixEmpty.numCols());
			gol2.setMatrix(matrix2);
			Assert.assertEquals(matrix2.toString(new Cell(1), new Cell(0)), gol2.toString());
		}
		
		@Test
		void testTransition1()
		{
			//Example 1
			Cell[][] arr1 = {
					{new Cell(0), new Cell(1), new Cell(0)},
					{new Cell(0), new Cell(1), new Cell(0)},
					{new Cell(0), new Cell(1), new Cell(0)}
			};
			
			Cell[][] answer1 = {
					{new Cell(0), new Cell(0), new Cell(0)},
					{new Cell(1), new Cell(1), new Cell(1)},
					{new Cell(0), new Cell(0), new Cell(0)}
			};
			
			IMatrix<Cell> matrix1 = new SparseMatrix<Cell>(arr1, new Cell(1), new Cell(0));
			IMatrix<Cell> matrixAnswer1 = new SparseMatrix<Cell>(answer1, new Cell(1), new Cell(0));
			 
			GameOfLife gol1 = new GameOfLife(matrix1, matrix1.numRows(), matrix1.numCols());
			gol1.transition();
			Assert.assertEquals(matrixAnswer1.toString(new Cell(1), new Cell(0)), gol1.toString());	
			
			//Example 2
			Cell[][] arr2 = {
					{new Cell(1), new Cell(1), new Cell(0)},
					{new Cell(1), new Cell(0), new Cell(1)},
					{new Cell(1), new Cell(0), new Cell(0)},
					{new Cell(0), new Cell(0), new Cell(0)}
			};
			
			Cell[][] answer2 = {
					{new Cell(0), new Cell(1), new Cell(1), new Cell(0)},
					{new Cell(1), new Cell(1), new Cell(0), new Cell(0)},
					{new Cell(0), new Cell(0), new Cell(1), new Cell(0)},
					{new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
			};

			IMatrix<Cell> matrix2 = new SparseMatrix<Cell>(arr2, new Cell(1), new Cell(0));
			IMatrix<Cell> matrixAnswer2 = new SparseMatrix<Cell>(answer2, new Cell(1), new Cell(0));
			GameOfLife gol2 = new GameOfLife(matrix2, matrix2.numRows(), matrix2.numCols());
			gol2.transition();
			Assert.assertEquals(matrixAnswer2.toString(new Cell(1), new Cell(0)), gol2.toString());
			
			//Example 3
			Cell[][] arr3 = {
					{new Cell(1), new Cell(1)},
					{new Cell(1), new Cell(1)}
			};
			
			Cell[][] answer3 = {
					{new Cell(1), new Cell(1)},
					{new Cell(1), new Cell(1)}
			};

			IMatrix<Cell> matrix3 = new SparseMatrix<Cell>(arr3, new Cell(1), new Cell(0));
			IMatrix<Cell> matrixAnswer3 = new SparseMatrix<Cell>(answer3, new Cell(1), new Cell(0));
			GameOfLife gol3 = new GameOfLife(matrix3, matrix3.numRows(), matrix3.numCols());
			gol3.transition();
			Assert.assertEquals(matrixAnswer3.toString(new Cell(1), new Cell(0)), gol3.toString());
			
			//Example 4
			Cell[][] arr4 = {
					{new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
					{new Cell(0), new Cell(0), new Cell(1), new Cell(1), new Cell(0), new Cell(0)},
					{new Cell(0), new Cell(1), new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
					{new Cell(0), new Cell(0), new Cell(1), new Cell(1), new Cell(0), new Cell(0)},
					{new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)}
			};
			
			Cell[][] answer4 = {
					{new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
					{new Cell(0), new Cell(0), new Cell(1), new Cell(0), new Cell(0), new Cell(0)},
					{new Cell(0), new Cell(1), new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
					{new Cell(0), new Cell(0), new Cell(1), new Cell(0), new Cell(0), new Cell(0)},
					{new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)}
			};

			IMatrix<Cell> matrix4 = new SparseMatrix<Cell>(arr4, new Cell(1), new Cell(0));
			IMatrix<Cell> matrixAnswer4 = new SparseMatrix<Cell>(answer4, new Cell(1), new Cell(0));
			GameOfLife gol4 = new GameOfLife(matrix4, matrix4.numRows(), matrix4.numCols());
			gol4.transition();
			Assert.assertEquals(matrixAnswer4.toString(new Cell(1), new Cell(0)), gol4.toString());
		}
	
	// -- Cell class Tests -- //
	
	@Test
	void testCellConstructor0()
	{
		Assert.assertEquals("1", new Cell(1).toString());
		Assert.assertEquals("0", new Cell(0).toString());	
	}
	
	@Test
	void testIsAlive()
	{
		Assert.assertEquals(true, new Cell(1).isAlive());
		Assert.assertEquals(false, new Cell(0).isAlive());	
	}
	
	// -- Array Matrix class Tests --//
	
	@Test
	void testArrMatrixConstructor0()
	{
		//Example 1
		Cell[][] arr1 = {
				{new Cell(0), new Cell(1), new Cell(1), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(1)}
		};
		Assert.assertEquals("0110\n" + 
	 			            "0001\n", new ArrayMatrix<Cell>(arr1).toString(new Cell(1), new Cell(0)));
		
		//Example 2
		Cell[][] arr2 = {
				{new Cell(1), new Cell(0)},
				{new Cell(1), new Cell(1)},
				{new Cell(0), new Cell(0)}
		};
		Assert.assertEquals("10\n" +
	 			            "11\n" +
	 			            "00\n", new ArrayMatrix<Cell>(arr2).toString(new Cell(1), new Cell(0)));
		
		//Example 3
		Cell[][] arr3 = {
				{new Cell(1)}
		};
		Assert.assertEquals("1\n", new ArrayMatrix<Cell>(arr3).toString(new Cell(1), new Cell(0)));
	}
	
	@Test
	void testArrMatrixGetValue0()
	{
		//Example 1
		Cell[][] arr1 = {
				{new Cell(0), new Cell(1), new Cell(1), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(1)}
		};
		IMatrix<Cell> matrix1 = new ArrayMatrix<Cell>(arr1);
		Assert.assertEquals(1, matrix1.getMatrixValue(0, 2, new Cell(0)).getCellValue());
		Assert.assertEquals(0, matrix1.getMatrixValue(0, 3, new Cell(0)).getCellValue());
		Assert.assertEquals(1, matrix1.getMatrixValue(1, 3, new Cell(0)).getCellValue());
		
		//Example 2
		Cell[][] arr2 = {
				{new Cell(1), new Cell(0)},
				{new Cell(1), new Cell(1)},
				{new Cell(0), new Cell(0)}
		};
		IMatrix<Cell> matrix2 = new ArrayMatrix<Cell>(arr2);
		Assert.assertEquals(0, matrix2.getMatrixValue(0, 1, new Cell(0)).getCellValue());
		Assert.assertEquals(1, matrix2.getMatrixValue(0, 0, new Cell(0)).getCellValue());
		Assert.assertEquals(0, matrix2.getMatrixValue(2, 1, new Cell(0)).getCellValue());
		
		//Example 3
		Cell[][] arr3 = {
				{new Cell(1)}
		};
		IMatrix<Cell> matrix3 = new ArrayMatrix<Cell>(arr3);
		Assert.assertEquals(1, matrix3.getMatrixValue(0, 0, new Cell(0)).getCellValue());
	}
	
	@Test
	void testArrMatrixSetValue0()
	{
		Cell[][] arr1 = {
				{new Cell(0), new Cell(1), new Cell(1), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(1)}
		};
		IMatrix<Cell> matrix1 = new ArrayMatrix<Cell>(arr1);
		
		matrix1.setMatrixValue(0, 1, new Cell(0), "");
		Assert.assertEquals(0, matrix1.getMatrixValue(0, 1, new Cell(0)).getCellValue());
		
		matrix1.setMatrixValue(1, 0, new Cell(1), "");
		Assert.assertEquals(1, matrix1.getMatrixValue(1, 0, new Cell(0)).getCellValue());
		
		matrix1.setMatrixValue(1, 3, new Cell(0), "");
		Assert.assertEquals(0, matrix1.getMatrixValue(1, 3, new Cell(0)).getCellValue());
	}
	
	@Test
	void testArrMatrixIncreaseRow0()
	{
		//Example 1
		Cell[][] arr1 = {
				{new Cell(0), new Cell(1), new Cell(1), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(1)}
		};
		Cell[][] arr1increased = {
				{new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(1), new Cell(1), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(1)}
		};
		IMatrix<Cell> matrix1 = new ArrayMatrix<Cell>(arr1);
		IMatrix<Cell> matrix1increased = new ArrayMatrix<Cell>(arr1increased);
		matrix1.increaseRows("top", new Cell(1), new Cell(0));
		Assert.assertEquals(matrix1increased.toString(new Cell(1), new Cell(0)), matrix1.toString(new Cell(1), new Cell(0)));
		
		//Example 2
		Cell[][] arr2 = {
				{new Cell(0), new Cell(1), new Cell(1)},
				{new Cell(0), new Cell(0), new Cell(0)}
		};
		Cell[][] arr2increased = {
				{new Cell(0), new Cell(1), new Cell(1)},
				{new Cell(0), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0)}
		};
		IMatrix<Cell> matrix2 = new ArrayMatrix<Cell>(arr2);
		IMatrix<Cell> matrix2increased = new ArrayMatrix<Cell>(arr2increased);
		matrix2.increaseRows("bottom", new Cell(1), new Cell(0));
		Assert.assertEquals(matrix2increased.toString(new Cell(1), new Cell(0)), matrix2.toString(new Cell(1), new Cell(0)));
	}
	
	@Test
	void testArrMatrixIncreaseCol0()
	{
		//Example 1
		Cell[][] arr1 = {
				{new Cell(0), new Cell(1), new Cell(1), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(1)}
		};
		Cell[][] arr1increased = {
				{new Cell(0), new Cell(1), new Cell(1), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(1), new Cell(0)}
		};
		IMatrix<Cell> matrix1 = new ArrayMatrix<Cell>(arr1);
		IMatrix<Cell> matrix1increased = new ArrayMatrix<Cell>(arr1increased);
		matrix1.increaseCols("right", new Cell(1), new Cell(0));
		Assert.assertEquals(matrix1increased.toString(new Cell(1), new Cell(0)), matrix1.toString(new Cell(1), new Cell(0)));
		
		//Example 2
		Cell[][] arr2 = {
				{new Cell(0), new Cell(1), new Cell(1)},
				{new Cell(0), new Cell(0), new Cell(0)}
		};
		Cell[][] arr2increased = {
				{new Cell(0), new Cell(0), new Cell(1), new Cell(1)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
		};
		IMatrix<Cell> matrix2 = new ArrayMatrix<Cell>(arr2);
		IMatrix<Cell> matrix2increased = new ArrayMatrix<Cell>(arr2increased);
		matrix2.increaseCols("left", new Cell(1), new Cell(0));
		Assert.assertEquals(matrix2increased.toString(new Cell(1), new Cell(0)), matrix2.toString(new Cell(1), new Cell(0)));
	}
	
	@Test
	void testArrMatrixDeepCopy0()
	{
		//Example 1
		Cell[][] arr1 = {
				{new Cell(0), new Cell(1), new Cell(1)},
				{new Cell(0), new Cell(0), new Cell(0)}
		};
		
		IMatrix<Cell> matrix1 = new ArrayMatrix<Cell>(arr1);
		IMatrix<Cell> copy1 = matrix1.deepCopyOf(new Cell(1), new Cell(0));
		Assert.assertEquals(matrix1.toString(new Cell(1), new Cell(0)), copy1.toString(new Cell(1), new Cell(0)));	
		
		//Example 2
		Cell[][] arr2 = {
				{new Cell(0), new Cell(0), new Cell(1), new Cell(1)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
		};

		IMatrix<Cell> matrix2 = new ArrayMatrix<Cell>(arr2);
		IMatrix<Cell> copy2 = matrix2.deepCopyOf(new Cell(1), new Cell(0));
		Assert.assertEquals(matrix2.toString(new Cell(1), new Cell(0)), copy2.toString(new Cell(1), new Cell(0)));	
	}
	
	// -- Sparse Matrix class Tests --//

	@Test
	void testSparseMatrixConstructor0()
	{
		//Example 1
		Integer[][] arr1 = {
				{1, 0},
				{0, 1}
		};
		IMatrix<Integer> matrix1 = new SparseMatrix<Integer>(arr1, 1, 0);
		Assert.assertEquals("10\n" +
						    "01", matrix1.toString(1, 0));
		
		//Example 2
		Cell[][] arr2 = {
				{new Cell(0), new Cell(1), new Cell(0)},
				{new Cell(1), new Cell(0), new Cell(1)},
				{new Cell(0), new Cell(1), new Cell(0)}
		};
		IMatrix<Cell> matrix2 = new SparseMatrix<Cell>(arr2, new Cell(1), new Cell(0));
		Assert.assertEquals("010\n" +
							"101\n" +
			    			"010", matrix2.toString(new Cell(1), new Cell(0)));
	}	
	
	@Test
	void testSparseMatrixGetValue0()
	{
		//Example 1
		Integer[][] arr1 = {
				{1, 0},
				{0, 1}
		};
		IMatrix<Integer> matrix1 = new SparseMatrix<Integer>(arr1, 1, 0);
		Assert.assertEquals("1", matrix1.getMatrixValue(0, 0, 0).toString());
		
		//Example 2
		Cell[][] arr2 = {
				{new Cell(0), new Cell(1), new Cell(0)},
				{new Cell(1), new Cell(0), new Cell(1)},
				{new Cell(0), new Cell(1), new Cell(0)}
		};
		IMatrix<Cell> matrix2 = new SparseMatrix<Cell>(arr2, new Cell(1), new Cell(0));
		Assert.assertEquals("1", matrix2.getMatrixValue(2, 1, new Cell(0)).toString());
		Assert.assertEquals("0", matrix2.getMatrixValue(2, 2, new Cell(0)).toString());
		Assert.assertEquals("1", matrix2.getMatrixValue(1, 0, new Cell(0)).toString());
	}
	
	@Test
	void testSparseMatrixSetValue0()
	{
		Cell[][] arr1 = {
				{new Cell(0), new Cell(1), new Cell(1), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(1)}
		};
		IMatrix<Cell> matrix1 = new SparseMatrix<Cell>(arr1, new Cell(1), new Cell(0));
		
		matrix1.setMatrixValue(0, 1, new Cell(0), "death");
		Assert.assertEquals(0, matrix1.getMatrixValue(0, 1, new Cell(0)).getCellValue());
		
		matrix1.setMatrixValue(1, 0, new Cell(1), "birth");
		Assert.assertEquals(1, matrix1.getMatrixValue(1, 0, new Cell(0)).getCellValue());
		
		matrix1.setMatrixValue(1, 3, new Cell(0), "death");
		Assert.assertEquals(0, matrix1.getMatrixValue(1, 3, new Cell(0)).getCellValue());
	}
	
	@Test
	void testSparseMatrixDeepCopy0()
	{
		//Example 1
		Cell[][] arr1 = {
				{new Cell(0), new Cell(1), new Cell(1), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(1)}
		};
		IMatrix<Cell> matrix1 = new SparseMatrix<Cell>(arr1, new Cell(1), new Cell(0));
		IMatrix<Cell> copy1 = matrix1.deepCopyOf(new Cell(1), new Cell(0));
		Assert.assertEquals(matrix1.toString(new Cell(1), new Cell(0)), copy1.toString(new Cell(1), new Cell(0)));
		
		//Example 2
		Cell[][] arr2 = {
				{new Cell(0), new Cell(1), new Cell(1), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(1)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(1)},
				{new Cell(1), new Cell(1), new Cell(1), new Cell(1)}
		};
		IMatrix<Cell> matrix2 = new SparseMatrix<Cell>(arr2, new Cell(1), new Cell(0));
		IMatrix<Cell> copy2 = matrix2.deepCopyOf(new Cell(1), new Cell(0));
		Assert.assertEquals(matrix2.toString(new Cell(1), new Cell(0)), copy2.toString(new Cell(1), new Cell(0)));	
	}
	
	@Test
	void testSparseMatrixShift0()
	{
		//Example 1
		Cell[][] arr1 = {
				{new Cell(1), new Cell(1)},
				{new Cell(1), new Cell(1)},
				{new Cell(1), new Cell(1)}
		};
		SparseMatrix<Cell> matrix1 = new SparseMatrix<Cell>(arr1, new Cell(1), new Cell(0));
		matrix1.shiftMatrix("right", new Cell(1), new Cell(0));
		Assert.assertEquals("011\n" + 
							"011\n" + 
							"011", matrix1.toString(new Cell(1), new Cell(0)));
		
		//Example 2
		Cell[][] arr2 = {
				{new Cell(1), new Cell(1)}
		};
		IMatrix<Cell> matrix2 = new SparseMatrix<Cell>(arr2, new Cell(1), new Cell(0));
		matrix2.shiftMatrix("down", new Cell(1), new Cell(0));
		Assert.assertEquals("00\n" + 
							"11", matrix2.toString(new Cell(1), new Cell(0)));
		
		
		//Example 3
		Cell[][] arr3 = {
				{new Cell(0), new Cell(1), new Cell(1), new Cell(0), new Cell(1)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(1), new Cell(0)},
				{new Cell(0), new Cell(0), new Cell(0), new Cell(1), new Cell(1)},
				{new Cell(1), new Cell(1), new Cell(1), new Cell(1), new Cell(0)}
		};
		IMatrix<Cell> matrix3 = new SparseMatrix<Cell>(arr3, new Cell(1), new Cell(0));
		matrix3.shiftMatrix("down", new Cell(1), new Cell(0));
		Assert.assertEquals("00000\n" + 
							"01101\n" + 
							"00010\n" + 
							"00011\n" + 
							"11110", matrix3.toString(new Cell(1), new Cell(0)));	
		
		//Example 4
		Cell[][] arr4 = {
				{new Cell(0), new Cell(1), new Cell(1)},
				{new Cell(0), new Cell(1), new Cell(1)},
				{new Cell(0), new Cell(1), new Cell(1)}
		};
		SparseMatrix<Cell> matrix4 = new SparseMatrix<Cell>(arr4, new Cell(1), new Cell(0));
		matrix4.shiftMatrix("left", new Cell(1), new Cell(0));
		Assert.assertEquals("11\n" + 
							"11\n" + 
							"11", matrix4.toString(new Cell(1), new Cell(0)));
		
		//Example 5
		Cell[][] arr5 = {
				{new Cell(0), new Cell(0), new Cell(0)},
				{new Cell(1), new Cell(1), new Cell(1)},
				{new Cell(1), new Cell(1), new Cell(1)}
		};
		SparseMatrix<Cell> matrix5 = new SparseMatrix<Cell>(arr5, new Cell(1), new Cell(0));
		matrix5.shiftMatrix("up", new Cell(1), new Cell(0));
		Assert.assertEquals("111\n" + 
							"111", matrix5.toString(new Cell(1), new Cell(0)));
	
		
		//Example 6
		Cell[][] arr6 = {
				{new Cell(0), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(1), new Cell(1)},
				{new Cell(0), new Cell(1), new Cell(1)}
		};
		SparseMatrix<Cell> matrix6 = new SparseMatrix<Cell>(arr6, new Cell(1), new Cell(0));
		matrix6.shiftMatrix("left", new Cell(1), new Cell(0));
		Assert.assertEquals("00\n" + 
							"11\n" + 
							"11", matrix6.toString(new Cell(1), new Cell(0)));
	}
	
	@Test
	void testSparseMatrixIncreaseRows0()
	{
		//Example 1
		Cell[][] arr1 = {
				{new Cell(1), new Cell(1)},
				{new Cell(1), new Cell(1)}
		};
		SparseMatrix<Cell> matrix1 = new SparseMatrix<Cell>(arr1, new Cell(1), new Cell(0));
		matrix1.increaseRows("top", new Cell(1), new Cell(0));
		Assert.assertEquals("00\n" +
							"11\n" + 
							"11", matrix1.toString(new Cell(1), new Cell(0)));
		
		//Example 2
		
		Cell[][] arr2 = {
				{new Cell(0), new Cell(1), new Cell(0)},
				{new Cell(0), new Cell(1), new Cell(1)}
		};
		IMatrix<Cell> matrix2 = new SparseMatrix<Cell>(arr2, new Cell(1), new Cell(0));
		matrix2.increaseRows("top", new Cell(1), new Cell(0));
		Assert.assertEquals("000\n" + 
							"010\n" + 
							"011", matrix2.toString(new Cell(1), new Cell(0)));
		
		//Example 2
		
		Cell[][] arr3 = {
				{new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(1)},
				{new Cell(0), new Cell(1)}
		};
		IMatrix<Cell> matrix3 = new SparseMatrix<Cell>(arr3, new Cell(1), new Cell(0));
		matrix3.increaseRows("top", new Cell(1), new Cell(0));
		Assert.assertEquals("00\n" + 
							"00\n" + 
							"01\n" + 
							"01", matrix3.toString(new Cell(1), new Cell(0)));
		
		//Example 4
		
		Cell[][] arr4 = {
				{new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(1)},
				{new Cell(0), new Cell(1)}
		};
		IMatrix<Cell> matrix4 = new SparseMatrix<Cell>(arr4, new Cell(1), new Cell(0));
		matrix4.increaseRows("bottom", new Cell(1), new Cell(0));
		Assert.assertEquals("00\n" + 
							"01\n" + 
							"01\n" + 
							"00", matrix4.toString(new Cell(1), new Cell(0)));
		
		//Example 3
		Cell[][] arr5 = {
				{new Cell(1), new Cell(1), new Cell(0)},
				{new Cell(1), new Cell(1), new Cell(1)}
		};
		SparseMatrix<Cell> matrix5 = new SparseMatrix<Cell>(arr5, new Cell(1), new Cell(0));
		matrix5.increaseRows("bottom", new Cell(1), new Cell(0));
		Assert.assertEquals("110\n" + 
							"111\n" + 
							"000", matrix5.toString(new Cell(1), new Cell(0)));
	}
	
	@Test
	void testSparseMatrixIncreaseCols0()
	{
		//Example 1
		Cell[][] arr1 = {
				{new Cell(1), new Cell(1)}
		};
		SparseMatrix<Cell> matrix1 = new SparseMatrix<Cell>(arr1, new Cell(1), new Cell(0));
		matrix1.increaseCols("left", new Cell(1), new Cell(0));
		Assert.assertEquals("011", matrix1.toString(new Cell(1), new Cell(0)));

		
		//Example 2
		
		Cell[][] arr2 = {
				{new Cell(0), new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(1), new Cell(1)},
				{new Cell(0), new Cell(1), new Cell(1)}
		};
		IMatrix<Cell> matrix2 = new SparseMatrix<Cell>(arr2, new Cell(1), new Cell(0));
		matrix2.increaseCols("left", new Cell(1), new Cell(0));
		Assert.assertEquals("0000\n" + 
							"0011\n" + 
							"0011", matrix2.toString(new Cell(1), new Cell(0)));
		
		//Example 3
		Cell[][] arr3 = {
				{new Cell(1), new Cell(1)},
				{new Cell(1), new Cell(1)},
				{new Cell(1), new Cell(1)}
		};
		SparseMatrix<Cell> matrix3 = new SparseMatrix<Cell>(arr3, new Cell(1), new Cell(0));
		matrix3.increaseCols("left", new Cell(1), new Cell(0));
		Assert.assertEquals("011\n" +
							"011\n" +
							"011", matrix3.toString(new Cell(1), new Cell(0)));
		
		//Example 4
		
		Cell[][] arr4 = {
				{new Cell(0), new Cell(0)},
				{new Cell(0), new Cell(1)},
				{new Cell(0), new Cell(1)}
		};
		IMatrix<Cell> matrix4 = new SparseMatrix<Cell>(arr4, new Cell(1), new Cell(0));
		matrix4.increaseCols("right", new Cell(1), new Cell(0));
		Assert.assertEquals("000\n" + 
							"010\n" + 
							"010", matrix4.toString(new Cell(1), new Cell(0)));
		
		//Example 5
		Cell[][] arr5 = {
				{new Cell(1), new Cell(1)},
				{new Cell(1), new Cell(1)}
		};
		SparseMatrix<Cell> matrix5 = new SparseMatrix<Cell>(arr5, new Cell(1), new Cell(0));
		matrix5.increaseCols("right", new Cell(1), new Cell(0));
		Assert.assertEquals("110\n" + 
							"110", matrix5.toString(new Cell(1), new Cell(0)));
	}
	
	// -- Data Node class Tests -- //
	
	@Test
	void testDataNodeConstructor0()
	{
		DataNode<Integer> node1 = new DataNode<Integer>(1, 2, null, null, 10);
		Assert.assertEquals("row: 1, col: 2, value: 10, south: (null, null), east: (null, null)", node1.toString());
	}
	
	// -- Sentinel Node class Tests -- //

	@Test
	void testSentinelNodeConstructor0()
	{
		DataNode<Cell> node1 = new DataNode<Cell>(1, 2, null, null, new Cell(1));
		DataLinkedList<Cell> DLL1 = new DataLinkedList<Cell>();
		DLL1.addLast(node1, "east");
		SentinelNode<Cell> sentinelN1 = new SentinelNode<Cell>(1, DLL1, DLL1, null);
		Assert.assertEquals("SN1, south: (DataLinkedList: [row: 1, col: 2, value: 1, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 1, col: 2, value: 1, south: (null, null), east: (null, null)]), next: (null)\n", sentinelN1.toString());
	}
	
	// -- Sentinel Linked List class Tests -- //
	
	@Test
	void testSentinelLinkedListConstructor0()
	{
		SentinelLinkedList<Integer> SLL = new SentinelLinkedList<Integer>();
		SLL.addLast(0, -1);
		SLL.addLast(1, -1);
		SLL.addLast(2, -1);
		Assert.assertEquals("SentinelLinkedList:\n" + 
				"SN0, south: (DataLinkedList: [row: -1, col: 0, value: -1, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 0, col: -1, value: -1, south: (null, null), east: (null, null)]), next: (1)\n" + 
				"SN1, south: (DataLinkedList: [row: -1, col: 1, value: -1, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 1, col: -1, value: -1, south: (null, null), east: (null, null)]), next: (2)\n" + 
				"SN2, south: (DataLinkedList: [row: -1, col: 2, value: -1, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 2, col: -1, value: -1, south: (null, null), east: (null, null)]), next: (0)\n", SLL.toString());
	}
	
	@Test
	void testSentinelLinkedListInsertAfter0()
	{
		SentinelLinkedList<Integer> SLL = new SentinelLinkedList<Integer>();
		SLL.addLast(0, -1);
		SLL.addLast(1, -1);
		SLL.addLast(2, -1);
		SLL.insertAfter(1, 1);
		Assert.assertEquals("SentinelLinkedList:\n" + 
				"SN0, south: (DataLinkedList: [row: -1, col: 0, value: -1, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 0, col: -1, value: -1, south: (null, null), east: (null, null)]), next: (1)\n" + 
				"SN1, south: (DataLinkedList: [row: -1, col: 1, value: -1, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 1, col: -1, value: -1, south: (null, null), east: (null, null)]), next: (2)\n" + 
				"SN2, south: (DataLinkedList: [row: -1, col: 2, value: 1, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 2, col: -1, value: 1, south: (null, null), east: (null, null)]), next: (3)\n" + 
				"SN3, south: (DataLinkedList: [row: -1, col: 3, value: -1, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 3, col: -1, value: -1, south: (null, null), east: (null, null)]), next: (0)\n", SLL.toString());

		//Example 3
				SentinelLinkedList<Cell> SLL3 = new SentinelLinkedList<Cell>();
				SLL3.addLast(0, new Cell(0));
				SLL3.addLast(1, new Cell(0));
				SLL3.addLast(2, new Cell(0));
				SLL3.getSentinelNode(0).getEast().addLast(0, 1, new Cell(1), "east");
				SLL3.getSentinelNode(1).getSouth().addLast(0, 1, new Cell(1), "south");
				SLL3.getSentinelNode(0).getSouth().addLast(1, 0, new Cell(1), "south");
				SLL3.getSentinelNode(1).getEast().addLast(1, 0, new Cell(1), "east");
				SLL3.insertAfter(1, new Cell(1));
				Assert.assertEquals("SentinelLinkedList:\n" + 
						"SN0, south: (DataLinkedList: [row: -1, col: 0, value: 0, south: (1, 0), east: (null, null)]), east: (DataLinkedList: [row: 0, col: -1, value: 0, south: (null, null), east: (0, 1)], [row: 0, col: 1, value: 1, south: (null, null), east: (null, null)]), next: (1)\n" + 
						"SN1, south: (DataLinkedList: [row: -1, col: 1, value: 0, south: (0, 1), east: (null, null)]), east: (DataLinkedList: [row: 1, col: -1, value: 0, south: (null, null), east: (1, 0)], [row: 1, col: 0, value: 1, south: (null, null), east: (null, null)]), next: (2)\n" + 
						"SN2, south: (DataLinkedList: [row: -1, col: 2, value: 1, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 2, col: -1, value: 1, south: (null, null), east: (null, null)]), next: (3)\n" + 
						"SN3, south: (DataLinkedList: [row: -1, col: 3, value: 0, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 3, col: -1, value: 0, south: (null, null), east: (null, null)]), next: (0)\n", SLL3.toString());
	}
	
	@Test
	void testSentinelLinkedListInsertBefore0()
	{
		//Example 1
		SentinelLinkedList<Integer> SLL1 = new SentinelLinkedList<Integer>();
		SLL1.addLast(0, -1);
		SLL1.addLast(1, -1);
		SLL1.addLast(2, -1);
		SLL1.insertBefore(1, 1);
		Assert.assertEquals("SentinelLinkedList:\n" + 
				"SN0, south: (DataLinkedList: [row: -1, col: 0, value: -1, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 0, col: -1, value: -1, south: (null, null), east: (null, null)]), next: (1)\n" + 
				"SN1, south: (DataLinkedList: [row: -1, col: 1, value: 1, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 1, col: -1, value: 1, south: (null, null), east: (null, null)]), next: (2)\n" + 
				"SN2, south: (DataLinkedList: [row: -1, col: 2, value: -1, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 2, col: -1, value: -1, south: (null, null), east: (null, null)]), next: (3)\n" + 
				"SN3, south: (DataLinkedList: [row: -1, col: 3, value: -1, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 3, col: -1, value: -1, south: (null, null), east: (null, null)]), next: (0)\n", SLL1.toString());
		
		//Example 2
		SentinelLinkedList<Cell> SLL2 = new SentinelLinkedList<Cell>();
		SLL2.addLast(0, new Cell(0));
		SLL2.addLast(1, new Cell(0));
		SLL2.addLast(2, new Cell(0));
		SLL2.getSentinelNode(0).getSouth().addLast(1, 0, new Cell(1), "south");
		SLL2.getSentinelNode(1).getEast().addLast(1, 0, new Cell(1), "east");
		SLL2.insertBefore(1, new Cell(1));
		Assert.assertEquals("SentinelLinkedList:\n" + 
				"SN0, south: (DataLinkedList: [row: -1, col: 0, value: 0, south: (2, 0), east: (null, null)]), east: (DataLinkedList: [row: 0, col: -1, value: 0, south: (null, null), east: (null, null)]), next: (1)\n" + 
				"SN1, south: (DataLinkedList: [row: -1, col: 1, value: 1, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 1, col: -1, value: 1, south: (null, null), east: (null, null)]), next: (2)\n" + 
				"SN2, south: (DataLinkedList: [row: -1, col: 2, value: 0, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 2, col: -1, value: 0, south: (null, null), east: (2, 0)], [row: 2, col: 0, value: 1, south: (null, null), east: (null, null)]), next: (3)\n" + 
				"SN3, south: (DataLinkedList: [row: -1, col: 3, value: 0, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 3, col: -1, value: 0, south: (null, null), east: (null, null)]), next: (0)\n", SLL2.toString());
	
		//Example 3
		SentinelLinkedList<Cell> SLL3 = new SentinelLinkedList<Cell>();
		SLL3.addLast(0, new Cell(0));
		SLL3.addLast(1, new Cell(0));
		SLL3.addLast(2, new Cell(0));
		SLL3.getSentinelNode(0).getEast().addLast(0, 1, new Cell(1), "east");
		SLL3.getSentinelNode(1).getSouth().addLast(0, 1, new Cell(1), "south");
		SLL3.getSentinelNode(0).getSouth().addLast(1, 0, new Cell(1), "south");
		SLL3.getSentinelNode(1).getEast().addLast(1, 0, new Cell(1), "east");
		SLL3.insertBefore(1, new Cell(1));
		
		Assert.assertEquals("SentinelLinkedList:\n" + 
				"SN0, south: (DataLinkedList: [row: -1, col: 0, value: 0, south: (2, 0), east: (null, null)]), east: (DataLinkedList: [row: 0, col: -1, value: 0, south: (null, null), east: (0, 2)], [row: 0, col: 2, value: 1, south: (null, null), east: (null, null)]), next: (1)\n" + 
				"SN1, south: (DataLinkedList: [row: -1, col: 1, value: 1, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 1, col: -1, value: 1, south: (null, null), east: (null, null)]), next: (2)\n" + 
				"SN2, south: (DataLinkedList: [row: -1, col: 2, value: 0, south: (0, 2), east: (null, null)]), east: (DataLinkedList: [row: 2, col: -1, value: 0, south: (null, null), east: (2, 0)], [row: 2, col: 0, value: 1, south: (null, null), east: (null, null)]), next: (3)\n" + 
				"SN3, south: (DataLinkedList: [row: -1, col: 3, value: 0, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 3, col: -1, value: 0, south: (null, null), east: (null, null)]), next: (0)\n", SLL3.toString());
	}
	
	@Test
	void testSentinelLinkedListRemove0()
	{
		//Example 1
		SentinelLinkedList<Cell> SLL1 = new SentinelLinkedList<Cell>();
		SLL1.addLast(0, new Cell(0));
		SLL1.addLast(1, new Cell(0));
		SLL1.addLast(2, new Cell(0));
		SLL1.remove(1);
		Assert.assertEquals("SentinelLinkedList:\n" + 
				"SN0, south: (DataLinkedList: [row: -1, col: 0, value: 0, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 0, col: -1, value: 0, south: (null, null), east: (null, null)]), next: (1)\n" + 
				"SN1, south: (DataLinkedList: [row: -1, col: 1, value: 0, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 1, col: -1, value: 0, south: (null, null), east: (null, null)]), next: (0)\n", SLL1.toString());
		
		//Example 2
		SentinelLinkedList<Integer> SLL2 = new SentinelLinkedList<Integer>();
		SLL2.addLast(0, -1);
		SLL2.addLast(1, -1);
		SLL2.addLast(2, -1);
		SLL2.addLast(3, -1);
		SLL2.addLast(4, -1);
		SLL2.remove(4);
		Assert.assertEquals("SentinelLinkedList:\n" + 
				"SN0, south: (DataLinkedList: [row: -1, col: 0, value: -1, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 0, col: -1, value: -1, south: (null, null), east: (null, null)]), next: (1)\n" + 
				"SN1, south: (DataLinkedList: [row: -1, col: 1, value: -1, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 1, col: -1, value: -1, south: (null, null), east: (null, null)]), next: (2)\n" + 
				"SN2, south: (DataLinkedList: [row: -1, col: 2, value: -1, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 2, col: -1, value: -1, south: (null, null), east: (null, null)]), next: (3)\n" + 
				"SN3, south: (DataLinkedList: [row: -1, col: 3, value: -1, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 3, col: -1, value: -1, south: (null, null), east: (null, null)]), next: (0)\n", SLL2.toString());
	}
	
	@Test
	void testSentinelLinkedListExists0()
	{
		SentinelLinkedList<Integer> SLL = new SentinelLinkedList<Integer>();
		SLL.addLast(0, -1);
		SLL.addLast(1, -1);
		SLL.addLast(2, -1);
		SLL.insertBefore(1, -1);
		Assert.assertEquals(true, SLL.exists(1));
		Assert.assertEquals(false, SLL.exists(4));
	}

	@Test
	void testSentinelLinkedCopyOf0()
	{
		//Example 1
		SentinelLinkedList<Cell> SLL1 = new SentinelLinkedList<Cell>();
		SLL1.addLast(0, new Cell(0));
		SLL1.addLast(1, new Cell(0));
		SLL1.getSentinelNode(1).getEast().addLast(1, 2, new Cell(1), "east");
		SentinelLinkedList<Cell> copy1 = (SentinelLinkedList<Cell>) SLL1.copyOf(new Cell(1), new Cell(0));
		Assert.assertEquals(SLL1.toString(), copy1.toString());
		
		//Example 2
		SentinelLinkedList<Cell> SLL2 = new SentinelLinkedList<Cell>();
		SLL2.addLast(0, new Cell(0));
		SLL2.addLast(1, new Cell(0));
		SLL2.addLast(2, new Cell(0));
		SLL2.addLast(3, new Cell(0));
		SLL2.getSentinelNode(1).getEast().addLast(1, 2, new Cell(1), "east");
		SLL2.getSentinelNode(1).getEast().addLast(1, 3, new Cell(1), "east");	
		SLL2.getSentinelNode(2).getEast().addLast(2, 0, new Cell(1), "east");
		SLL2.getSentinelNode(3).getSouth().addLast(2, 3, new Cell(1), "south");
		SentinelLinkedList<Cell> copy2 = (SentinelLinkedList<Cell>) SLL2.copyOf(new Cell(1), new Cell(0));
		Assert.assertEquals(SLL2.toString(), copy2.toString());
		
		//Example 3
		SentinelLinkedList<Integer> SLL3 = new SentinelLinkedList<Integer>();
		SentinelLinkedList<Integer> copy3 = (SentinelLinkedList<Integer>) SLL3.copyOf(1, 0);
		Assert.assertEquals(SLL3.toString(), copy3.toString());
	}
	
	// -- Data Linked List class Tests -- //	
	
	@Test
	void testDataLinkedListConstructor0()
	{
		//Example 1
		DataLinkedList<Integer> DLL1 = new DataLinkedList<Integer>(new DataNode<Integer>(1, 2, null, null, 10)); //starting point is random data node
		Assert.assertEquals("DataLinkedList: [row: 1, col: 2, value: 10, south: (null, null), east: (null, null)]", DLL1.toString());
		
		//Example 2
		SentinelLinkedList<Integer> SLL = new SentinelLinkedList<Integer>();
		SLL.addLast(0, -1);
		DataLinkedList<Integer> DLL2 = SLL.getSentinelHead().getSouth(); //starting point is node stored in South variable of Sentinel Node
		Assert.assertEquals("DataLinkedList: [row: -1, col: 0, value: -1, south: (null, null), east: (null, null)]", DLL2.toString());
		
		//Example 3
		SentinelLinkedList<Cell> SLL2 = new SentinelLinkedList<Cell>();
		SLL2.addLast(0, new Cell(0));
		SLL2.addLast(1, new Cell(0));
		SLL2.getSentinelNode(1).getEast().addLast(1, 2, new Cell(1), "east");
		Assert.assertEquals("SentinelLinkedList:\n" + 
				"SN0, south: (DataLinkedList: [row: -1, col: 0, value: 0, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 0, col: -1, value: 0, south: (null, null), east: (null, null)]), next: (1)\n" + 
				"SN1, south: (DataLinkedList: [row: -1, col: 1, value: 0, south: (null, null), east: (null, null)]), east: (DataLinkedList: [row: 1, col: -1, value: 0, south: (null, null), east: (1, 2)], [row: 1, col: 2, value: 1, south: (null, null), east: (null, null)]), next: (0)\n", SLL2.toString());
	}
	
	@Test
	void testDataLinkedListAddLast0()
	{
		//Example 1
		DataLinkedList<Integer> DLL1 = new DataLinkedList<Integer>();
		DataNode<Integer> DN1 = new DataNode<Integer>(1, 2, null, null, 10);
		DataNode<Integer> DN2 = new DataNode<Integer>(1, 4, null, null, 20);
		DLL1.addLast(DN1, "east");
		DLL1.addLast(DN2, "east");
		Assert.assertEquals("DataLinkedList: [row: 1, col: 2, value: 10, south: (null, null), east: (1, 4)], [row: 1, col: 4, value: 20, south: (null, null), east: (null, null)]", DLL1.toString());
				
		//Example 2
		DataLinkedList<Cell> DLL2 = new DataLinkedList<Cell>();
		DataNode<Cell> DN3 = new DataNode<Cell>(1, 2, null, null, new Cell(1));
		DataNode<Cell> DN4 = new DataNode<Cell>(3, 2, null, null, new Cell(1));
		DLL2.addLast(DN3, "south");
		DLL2.addLast(DN4, "south");
		Assert.assertEquals("DataLinkedList: [row: 1, col: 2, value: 1, south: (3, 2), east: (null, null)], [row: 3, col: 2, value: 1, south: (null, null), east: (null, null)]", DLL2.toStringSouth());
		
		//Example 3
		DataLinkedList<Integer> DLL3 = new DataLinkedList<Integer>();
		DataNode<Integer> DN5 = new DataNode<Integer>(1, 2, null, null, 10);
		DataNode<Integer> DN6 = new DataNode<Integer>(3, 4, null, null, 20);
		DataNode<Integer> DN7 = new DataNode<Integer>(5, 6, null, null, 30);
		DLL3.addLast(DN5, "south");
		DLL3.addLast(DN6, "east");
		DLL3.addLast(DN7, "south");
		DLL3.addLast(DN7, "east");
		Assert.assertEquals("DataLinkedList: [row: 1, col: 2, value: 10, south: (5, 6), east: (3, 4)], [row: 3, col: 4, value: 20, south: (null, null), east: (5, 6)], [row: 5, col: 6, value: 30, south: (null, null), east: (null, null)]", DLL3.toString());
	}
	
	@Test
	void testDataLinkedListInsertAfter0()
	{
		//Example 1
		DataLinkedList<Integer> DLL1 = new DataLinkedList<Integer>();
		DataNode<Integer> DN1 = new DataNode<Integer>(1, 2, null, null, 10);
		DataNode<Integer> DN2 = new DataNode<Integer>(1, 4, null, null, 20);
		DataNode<Integer> DN3 = new DataNode<Integer>(1, 2, null, null, 30);	
		
		DLL1.addLast(DN1, "east");
		DLL1.addLast(DN2, "east");
		DLL1.insertAfter(DN3, "east");
		Assert.assertEquals("DataLinkedList: [row: 1, col: 2, value: 10, south: (null, null), east: (1, 3)], [row: 1, col: 3, value: 30, south: (null, null), east: (1, 4)], [row: 1, col: 4, value: 20, south: (null, null), east: (null, null)]", DLL1.toString());
		
		//Example 2
		
		DataLinkedList<Cell> DLL2 = new DataLinkedList<Cell>();
		DataNode<Cell> DN1_1 = new DataNode<Cell>(2, 1, null, null, new Cell(1));
		DataNode<Cell> DN1_2 = new DataNode<Cell>(4, 1, null, null, new Cell(1));
		DataNode<Cell> DN1_3 = new DataNode<Cell>(2, 1, null, null, new Cell(1));	
		
		DLL2.addLast(DN1_1, "south");
		DLL2.addLast(DN1_2, "south");
		DLL2.insertAfter(DN1_3, "south");
		Assert.assertEquals("DataLinkedList: [row: 2, col: 1, value: 1, south: (3, 1), east: (null, null)], [row: 3, col: 1, value: 1, south: (4, 1), east: (null, null)], [row: 4, col: 1, value: 1, south: (null, null), east: (null, null)]", DLL2.toStringSouth());
	}
	
	@Test
	void testDataLinkedListinsertBefore0()
	{
		DataLinkedList<Integer> DLL1 = new DataLinkedList<Integer>();
		DataNode<Integer> DN1 = new DataNode<Integer>(1, 2, null, null, 10);
		DataNode<Integer> DN2 = new DataNode<Integer>(1, 4, null, null, 20);
		DataNode<Integer> DN3 = new DataNode<Integer>(1, 6, null, null, 30);
		DataNode<Integer> DN4 = new DataNode<Integer>(1, 6, null, null, 40);
		
		//Example 1
		DLL1.addLast(DN1, "east");
		DLL1.addLast(DN2, "east");
		DLL1.addLast(DN3, "east");
		DLL1.insertBefore(DN4, "east");
		Assert.assertEquals("DataLinkedList: [row: 1, col: 2, value: 10, south: (null, null), east: (1, 4)], [row: 1, col: 4, value: 20, south: (null, null), east: (1, 5)], [row: 1, col: 5, value: 40, south: (null, null), east: (1, 6)], [row: 1, col: 6, value: 30, south: (null, null), east: (null, null)]", DLL1.toString());
		
		//Example 2
		
		DataLinkedList<Cell> DLL2 = new DataLinkedList<Cell>();
		DataNode<Cell> DN1_1 = new DataNode<Cell>(2, 1, null, null, new Cell(1));
		DataNode<Cell> DN1_2 = new DataNode<Cell>(3, 1, null, null, new Cell(1));
		DataNode<Cell> DN1_3 = new DataNode<Cell>(6, 1, null, null, new Cell(1));
		DataNode<Cell> DN1_4 = new DataNode<Cell>(6, 1, null, null, new Cell(1));
		
		DLL2.addLast(DN1_1, "south");
		DLL2.addLast(DN1_2, "south");
		DLL2.addLast(DN1_3, "south");
		DLL2.insertBefore(DN1_4, "south");
		Assert.assertEquals("DataLinkedList: [row: 2, col: 1, value: 1, south: (3, 1), east: (null, null)], [row: 3, col: 1, value: 1, south: (5, 1), east: (null, null)], [row: 5, col: 1, value: 1, south: (6, 1), east: (null, null)], [row: 6, col: 1, value: 1, south: (null, null), east: (null, null)]", DLL2.toStringSouth());
	}
	
	@Test
	void testDataLinkedListinsertAtLocation0()
	{
		DataLinkedList<Cell> DLL1 = new DataLinkedList<Cell>();
		DataNode<Cell> DN1 = new DataNode<Cell>(1, 2, null, null, new Cell(1));
		DataNode<Cell> DN2 = new DataNode<Cell>(1, 3, null, null, new Cell(1));
		DataNode<Cell> DN3 = new DataNode<Cell>(1, 4, null, null, new Cell(1));
		DataNode<Cell> DN4 = new DataNode<Cell>(1, 8, null, null, new Cell(1));
		DataNode<Cell> DN5 = new DataNode<Cell>(1, 6, null, null, new Cell(1));
		
		//Example 1
		DLL1.addLast(DN1, "east");
		DLL1.addLast(DN2, "east");
		DLL1.addLast(DN3, "east");
		DLL1.addLast(DN4, "east");
		DLL1.insertAtLocation(DN5, "east");
		Assert.assertEquals("DataLinkedList: [row: 1, col: 2, value: 1, south: (null, null), east: (1, 3)], [row: 1, col: 3, value: 1, south: (null, null), east: (1, 4)], [row: 1, col: 4, value: 1, south: (null, null), east: (1, 6)], [row: 1, col: 6, value: 1, south: (null, null), east: (1, 8)], [row: 1, col: 8, value: 1, south: (null, null), east: (null, null)]", DLL1.toString());

		//Example 2
		DataNode<Integer> DN1_1 = new DataNode<Integer>(2, 1, null, null, 10);
		DataNode<Integer> DN1_2 = new DataNode<Integer>(3, 1, null, null, 20);
		DataNode<Integer> DN1_3 = new DataNode<Integer>(4, 1, null, null, 30);
		DataNode<Integer> DN1_4 = new DataNode<Integer>(8, 1, null, null, 40);
		DataNode<Integer> DN1_5 = new DataNode<Integer>(6, 1, null, null, 50);
		
		DataLinkedList<Integer> DLL2 = new DataLinkedList<Integer>();
		DLL2.addLast(DN1_1, "south");
		DLL2.addLast(DN1_2, "south");
		DLL2.addLast(DN1_3, "south");
		DLL2.addLast(DN1_4, "south");
		DLL2.insertAtLocation(DN1_5, "south");
		Assert.assertEquals("DataLinkedList: [row: 2, col: 1, value: 10, south: (3, 1), east: (null, null)], [row: 3, col: 1, value: 20, south: (4, 1), east: (null, null)], [row: 4, col: 1, value: 30, south: (6, 1), east: (null, null)], [row: 6, col: 1, value: 50, south: (8, 1), east: (null, null)], [row: 8, col: 1, value: 40, south: (null, null), east: (null, null)]", DLL2.toStringSouth());
	}
	
	@Test
	void testDataLinkedListRemove0()
	{
		//Example 1
		DataNode<Integer> DN1 = new DataNode<Integer>(1, 2, null, null, 10);
		DataNode<Integer> DN2 = new DataNode<Integer>(1, 3, null, null, 20);
		DataNode<Integer> DN3 = new DataNode<Integer>(1, 4, null, null, 30);
		DataNode<Integer> DN4 = new DataNode<Integer>(1, 6, null, null, 40);
		DataNode<Integer> DN5 = new DataNode<Integer>(1, 8, null, null, 50);
		
		DataLinkedList<Integer> DLL1 = new DataLinkedList<Integer>();
		DLL1.addLast(DN1, "east");
		DLL1.addLast(DN2, "east");
		DLL1.addLast(DN3, "east");
		DLL1.addLast(DN4, "east");
		DLL1.addLast(DN5, "east");
		DLL1.remove(1, 4, "east");
		Assert.assertEquals("DataLinkedList: [row: 1, col: 2, value: 10, south: (null, null), east: (1, 3)], [row: 1, col: 3, value: 20, south: (null, null), east: (1, 6)], [row: 1, col: 6, value: 40, south: (null, null), east: (1, 8)], [row: 1, col: 8, value: 50, south: (null, null), east: (null, null)]", DLL1.toString());
		
		DLL1.remove(1, 2, "east");
		Assert.assertEquals("DataLinkedList: [row: 1, col: 3, value: 20, south: (null, null), east: (1, 6)], [row: 1, col: 6, value: 40, south: (null, null), east: (1, 8)], [row: 1, col: 8, value: 50, south: (null, null), east: (null, null)]", DLL1.toString());
		
		//Example 2
		
		DataNode<Cell> DN1_1 = new DataNode<Cell>(2, 1, null, null, new Cell(1));
		DataNode<Cell> DN1_2 = new DataNode<Cell>(3, 1, null, null, new Cell(1));
		DataNode<Cell> DN1_3 = new DataNode<Cell>(4, 1, null, null, new Cell(1));
		DataNode<Cell> DN1_4 = new DataNode<Cell>(6, 1, null, null, new Cell(1));
		DataNode<Cell> DN1_5 = new DataNode<Cell>(8, 1, null, null, new Cell(1));
		
		DataLinkedList<Cell> DLL2 = new DataLinkedList<Cell>();
		DLL2.addLast(DN1_1, "south");
		DLL2.addLast(DN1_2, "south");
		DLL2.addLast(DN1_3, "south");
		DLL2.addLast(DN1_4, "south");
		DLL2.addLast(DN1_5, "south");
		DLL2.remove(4, 1, "south");
		Assert.assertEquals("DataLinkedList: [row: 2, col: 1, value: 1, south: (3, 1), east: (null, null)], [row: 3, col: 1, value: 1, south: (6, 1), east: (null, null)], [row: 6, col: 1, value: 1, south: (8, 1), east: (null, null)], [row: 8, col: 1, value: 1, south: (null, null), east: (null, null)]", DLL2.toStringSouth());
	}
	
	@Test
	void testDataLinkedListExists0()
	{
		//Example 1
		DataNode<Integer> DN1 = new DataNode<Integer>(1, 2, null, null, 10);
		DataNode<Integer> DN2 = new DataNode<Integer>(1, 3, null, null, 20);
		DataNode<Integer> DN3 = new DataNode<Integer>(1, 4, null, null, 30);
		
		DataLinkedList<Integer> DLL1 = new DataLinkedList<Integer>();
		DLL1.addLast(DN1, "east");
		DLL1.addLast(DN2, "east");
		DLL1.addLast(DN3, "east");
		Assert.assertEquals(true, DLL1.exists(1, 2, "east"));
		Assert.assertEquals(false, DLL1.exists(1, 7, "east"));
		org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> DLL1.remove(1, 7, "east"));
		
		//Example 2
		DataNode<Cell> DN1_1 = new DataNode<Cell>(2, 1, null, null, new Cell(1));
		DataNode<Cell> DN1_2 = new DataNode<Cell>(3, 1, null, null, new Cell(1));
		DataNode<Cell> DN1_3 = new DataNode<Cell>(4, 1, null, null, new Cell(1));
		
		DataLinkedList<Cell> DLL2 = new DataLinkedList<Cell>();
		DLL2.addLast(DN1_1, "south");
		DLL2.addLast(DN1_2, "south");
		DLL2.addLast(DN1_3, "south");
		Assert.assertEquals(true, DLL2.exists(4, 1, "south"));
		Assert.assertEquals(false, DLL2.exists(1, 7, "south"));
	}
	
	@Test
	void testDataLinkedListGetDataNode0()
	{
		//Example 1
		DataNode<Integer> DN1 = new DataNode<Integer>(1, 2, null, null, 10);
		DataNode<Integer> DN2 = new DataNode<Integer>(1, 3, null, null, 20);
		DataNode<Integer> DN3 = new DataNode<Integer>(1, 4, null, null, 30);
		
		DataLinkedList<Integer> DLL1 = new DataLinkedList<Integer>();
		DLL1.addLast(DN1, "east");
		DLL1.addLast(DN2, "east");
		DLL1.addLast(DN3, "east");
		Assert.assertEquals("row: 1, col: 2, value: 10, south: (null, null), east: (1, 3)", DLL1.getDataNode(1, 2, "east").toString());
		Assert.assertEquals("row: 1, col: 4, value: 30, south: (null, null), east: (null, null)", DLL1.getDataNode(1, 4, "east").toString());
		org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> DLL1.getDataNode(1, 7, "east"));
		
		//Example 2
		DataNode<Cell> DN1_1 = new DataNode<Cell>(2, 1, null, null, new Cell(1));
		DataNode<Cell> DN1_2 = new DataNode<Cell>(3, 1, null, null, new Cell(1));
		DataNode<Cell> DN1_3 = new DataNode<Cell>(4, 1, null, null, new Cell(1));
		
		DataLinkedList<Cell> DLL2 = new DataLinkedList<Cell>();
		DLL2.addLast(DN1_1, "south");
		DLL2.addLast(DN1_2, "south");
		DLL2.addLast(DN1_3, "south");
		Assert.assertEquals(true, DLL2.exists(4, 1, "south"));
		Assert.assertEquals(false, DLL2.exists(1, 7, "south"));
	}
	
	@Test
	void testDataLinkedListCopyOf0()
	{
		//Example 1
		DataNode<Integer> DN1 = new DataNode<Integer>(1, 2, null, null, 1);
		DataNode<Integer> DN2 = new DataNode<Integer>(1, 3, null, null, 1);
		DataNode<Integer> DN3 = new DataNode<Integer>(1, 4, null, null, 1);
		
		DataLinkedList<Integer> DLL1 = new DataLinkedList<Integer>();
		DLL1.addLast(DN1, "east");
		DLL1.addLast(DN2, "east");
		DLL1.addLast(DN3, "east");
		DataLinkedList<Integer> copy1 = (DataLinkedList<Integer>) DLL1.copyOf("east", 1, 0);
		Assert.assertEquals(DLL1.toString(), copy1.toString());
		
		//Example 2
		DataNode<Cell> DN1_1 = new DataNode<Cell>(2, 1, null, null, new Cell(1));
		DataNode<Cell> DN1_2 = new DataNode<Cell>(3, 1, null, null, new Cell(1));
		DataNode<Cell> DN1_3 = new DataNode<Cell>(4, 1, null, null, new Cell(1));
		
		DataLinkedList<Cell> DLL2 = new DataLinkedList<Cell>();
		DLL2.addLast(DN1_1, "south");
		DLL2.addLast(DN1_2, "south");
		DLL2.addLast(DN1_3, "south");
		DataLinkedList<Cell> copy2 = (DataLinkedList<Cell>) DLL2.copyOf("south", new Cell(1), new Cell(0));
		Assert.assertEquals(DLL2.toStringSouth(), copy2.toStringSouth());
	}
	
	// -- Client Tests -- //
	
	@Test
	void testInt2Cell0() throws IntputLimitExceededException
	{
		//Example 1
		ArrayList<ArrayList<Integer>> list1 = new ArrayList<ArrayList<Integer>>();	
		ArrayList<Integer> i1 = new ArrayList<Integer>();
		i1.add(1);
		i1.add(1);
		ArrayList<Integer> i2 = new ArrayList<Integer>();
		i2.add(0);
		i2.add(0);
		list1.add(i1); 
		list1.add(i2);
		
		Cell[][] cells1 =  new Cell[][]{
			{new Cell(1), new Cell(1)},
			{new Cell(0), new Cell(0)}
		   };

		Assert.assertEquals(cells1[0][0].toString(), Client.int2Cell(list1)[0][0].toString());
		Assert.assertEquals(cells1[1][0].toString(), Client.int2Cell(list1)[1][0].toString());
		Assert.assertEquals(cells1[1][1].toString(), Client.int2Cell(list1)[1][1].toString());
		
		//Example 2
		ArrayList<ArrayList<Integer>> list2 = new ArrayList<ArrayList<Integer>>();	
		ArrayList<Integer> j1 = new ArrayList<Integer>();
		j1.add(1);
		j1.add(0); 
		j1.add(0);
		ArrayList<Integer> j2 = new ArrayList<Integer>();
		j2.add(0);
		j2.add(1);
		j2.add(1);
		list2.add(j1); 
		list2.add(j2);
		
		Cell[][] cells2 =  new Cell[][]{
			{new Cell(1), new Cell(0), new Cell(0)},
			{new Cell(0), new Cell(1), new Cell(1)}
		   };
		   
		Assert.assertEquals(cells2[0][0].toString(), Client.int2Cell(list2)[0][0].toString());
		Assert.assertEquals(cells2[1][2].toString(), Client.int2Cell(list2)[1][2].toString());
		Assert.assertEquals(cells2[1][1].toString(), Client.int2Cell(list2)[1][1].toString());
	}
	

}
