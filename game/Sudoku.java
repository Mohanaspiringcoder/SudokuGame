package sudoku.game;
/*
 * Name: Mohan Dongara
 * Email ID: mohandongara@gmail.com
 * Java Project
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sudoku {
	
	private static final int GRID_SIZE = 9;
	private static final String TEXT_RESET = "\u001B[0m";
	private static final String TEXT_BLUE = "\u001B[34m";
	private static final String TEXT_BOLD = "\u001B[1m";
	private static final String TEXT_GREEN = "\u001B[32m";
	private static final String TEXT_RED = "\u001B[31m";
	private static final String TEXT_PURPLE = "\033[0;35m";  // PURPLE
    private static final String TEXT_CYAN = "\033[0;36m";    // CYAN
    private static final String TEXT_CYAN_BOLD = "\033[1;36m";   // CYAN
    private static final String TEXT_YELLOW = "\033[0;33m";  // YELLOW
    private static final String TEXT_PURPLE_BOLD = "\033[1;35m"; // PURPLE
	private int level;

	private int[][] sudokuBoardEasy = {{ 1, 2, 9, 6, 5, 3, 4, 7, 8 },
			  						   { 8, 4, 5, 7, 9, 1, 2, 6, 3 },
			  						   { 3, 6, 7, 8, 2, 4, 5, 9, 1 },
			  						   { 4, 8, 6, 3, 1, 2, 7, 5, 9 },
			  						   { 5, 3, 1, 4, 7, 9, 6, 8, 2 },
			  						   { 7, 9, 2, 5, 8, 6, 3, 1, 4 },
			  						   { 2, 7, 8, 1, 4, 5, 9, 3, 6 },
			  						   { 6, 5, 4, 9, 3, 8, 1, 2, 7 },
			  						   { 0, 0, 3, 2, 0, 0, 0, 4, 5 }};
	
	private int[][] sudokuBoardMedium = {{1, 2, 0, 0, 0, 3, 4, 0, 0},
	   		 							 {0, 4, 5, 0, 0, 0, 2, 6, 0},
	   		 							 {0, 0, 7, 8, 0, 0, 0, 9, 1},
	   		 							 {0, 0, 0, 3, 1, 0, 0, 0, 9},
	   		 							 {0, 0, 0, 0, 7, 0, 0, 0, 0},
	   		 							 {7, 0, 0, 0, 8, 6, 0, 0, 0},
	   		 							 {2, 7, 0, 0, 0, 5, 9, 0, 0},
	   		 							 {0, 5, 4, 0, 0, 0, 1, 2, 0},
	   		 							 {0, 0, 3, 2, 0, 0, 0, 4, 5}};
	
	private int[][] sudokuBoardHard = {{6, 0, 0, 0, 4, 0, 0, 0, 0},
									   {9, 0, 0, 0, 0, 5, 6, 0, 1},
									   {1, 0, 0, 0, 7, 0, 3, 0, 0},
									   {0, 0, 0, 0, 0, 0, 0, 6, 4},
									   {0, 3, 0, 0, 0, 4, 0, 2, 0},
									   {0, 8, 0, 0, 2, 0, 5, 0, 0},
									   {0, 0, 0, 0, 0, 0, 0, 0, 0},
									   {3, 0, 0, 9, 0, 0, 2, 0, 0},
									   {0, 7, 0, 0, 5, 0, 1, 0, 0}};
	
	private int[][] sudokuBoard; 
	private List<int[]> inBuiltSlotsList = new ArrayList<int[]>();
	
	public Sudoku() {
		// default constructor
	}
	
	public Sudoku(int level) {
		this.level = level;
		setSudokuBoard();
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int[][] getSudokuBoard() {
		return sudokuBoard;
	}

	public void setSudokuBoard() {
		if(level == 1)
			this.sudokuBoard = sudokuBoardEasy;
		else if(level == 2)
			this.sudokuBoard = sudokuBoardMedium;
		else
			this.sudokuBoard = sudokuBoardHard;
	}
	
	public boolean isNumberInLocalGrid(int row, int column, int number) {
		int localGridRow = row - row % 3; 
		int localGridColumn = column - column % 3; 
		for(int i = localGridRow; i > localGridRow + 3; i++) {
			for(int j = localGridColumn; j < localGridColumn + 3; j++) {
				if(sudokuBoard[i][j] == number) {
					System.out.println(TEXT_RED + "Invalid input: " + TEXT_RESET + number + " is present in local 3x3 grid");
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isNumberInColumn(int column, int number) {
		for(int i = 0; i < GRID_SIZE; i++) {
			if(sudokuBoard[i][column] == number) {
				System.out.println(TEXT_RED + "Invalid input: " + TEXT_RESET + number + " is present in column " + (column + 1));
				return true;
			}
		}
		return false;
	}
	
	public boolean isNumberInRow(int row, int number) {
		for(int i = 0; i < GRID_SIZE; i++) {
			if(sudokuBoard[row][i] == number) {
				System.out.println(TEXT_RED + "Invalid input: " + TEXT_RESET + number + " is present in row " + (row + 1));
				return true;
			}
		}
		return false;
	}
	
	public boolean isSlotInBuilt(int row, int column) {
		for(int[] slotPosition: inBuiltSlotsList) {
			if(slotPosition[0] == row && slotPosition[1] == column) {
				System.out.println(TEXT_RED + "Invalid input: " + TEXT_RESET + "Cannot change initial configuration.");
				return true;
			}
		}
		return false;
	}
	
	public boolean isValidNumber(int number) {
		if(number >= 1 && number <= 9)
			return true;
		System.out.println(TEXT_RED + "Invalid input: " + TEXT_RESET + "Invalid number.");
		return false;
	}
	
	public boolean isValidColumn(int column) {
		if(column >= 0 && column <= 8)
			return true;
		System.out.println(TEXT_RED + "Invalid input: " + TEXT_RESET + "Invalid column.");
		return false;
	}
	
	public boolean isValidRow(int row) {
		if(row >=0 && row <= 8)
			return true;
		System.out.println(TEXT_RED + "Invalid input: " + TEXT_RESET + "Invalid row.");
		return false;
	}
	
	public boolean isValidSlot(int row, int column, int number) {
		return (isValidRow(row)) &&
				(isValidColumn(column)) &&
				(isValidNumber(number)) &&
				(!isSlotInBuilt(row, column)) && 
				(!isNumberInRow(row, number))  &&
				(!isNumberInColumn(column, number)) &&
				(!isNumberInLocalGrid(row, column, number));
	}
		
	public void addNumbersToSudoku() {
		Scanner sc = new Scanner(System.in);
		System.out.println(TEXT_BOLD + TEXT_BLUE + "USER" + TEXT_RESET + " input: ");
		char row_char = sc.next().charAt(0);
		if(row_char == 'Q' || row_char == 'q') {
			System.out.println("You have quit the game.");
			System.exit(0);
		}
		char column_char = sc.next().charAt(0);
		char number_char = sc.next().charAt(0);
		int row = Character.getNumericValue(row_char);
		int column = Character.getNumericValue(column_char);
		int number = Character.getNumericValue(number_char);
		row -= 1;
		column -= 1;
		if(isValidSlot(row, column, number)) 
			sudokuBoard[row][column] = number;
	}
	
	public boolean isComplete() {
		for(int i = 0; i < GRID_SIZE; i++) {
			for(int j = 0; j < GRID_SIZE; j++) {
				if(sudokuBoard[i][j] == 0)
					return false;
			}
		}
		return true;
	}
	
	public boolean isInBuiltSlot(int row, int column) {
		for(int[] slotPosition : inBuiltSlotsList) {
			if(slotPosition[0] == row && slotPosition[1] == column)
				return true;
		}
		return false;
	}
	
	public void displaySudoku() {
		System.out.println();
		System.out.println(TEXT_PURPLE_BOLD + "SUDOKU" + "\t" + TEXT_RESET + TEXT_GREEN + TEXT_BOLD + "1 2 3   4 5 6   7 8 9" + TEXT_RESET);
		System.out.println(TEXT_PURPLE_BOLD + "9x9\n" + TEXT_RESET);
		
		for(int i = 0; i < GRID_SIZE; i++) {
			if(i % 3 == 0 && i != 0)
				System.out.println("\t" + TEXT_BOLD + "------+-------+------" + TEXT_RESET);
			
			System.out.print(TEXT_CYAN_BOLD + " " + (i+1) + "\t" + TEXT_RESET);

			for(int j = 0; j < GRID_SIZE; j++) {
				if(j % 3 == 0 && j != 0)
					System.out.print(TEXT_BOLD + "| " + TEXT_RESET);
				
				if(sudokuBoard[i][j] == 0)
					System.out.print(". ");
				else if(isInBuiltSlot(i ,j))
					System.out.print(TEXT_BOLD + sudokuBoard[i][j] + " " + TEXT_RESET);
				else
					System.out.print(TEXT_BLUE + TEXT_BOLD + sudokuBoard[i][j] + " " + TEXT_RESET);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void getInBuiltSlots() {
		for(int i = 0; i < GRID_SIZE; i++) {
			for(int j = 0; j < GRID_SIZE; j++) {
				if(sudokuBoard[i][j] != 0) {
					int[] inBuiltSlot = {i, j};
					inBuiltSlotsList.add(inBuiltSlot);
				}
			}
		}
	}
	
	public void playSudoku() {
		int moveCount = 0; 
		getInBuiltSlots();
		displaySudoku();
		while(!isComplete()) {
			addNumbersToSudoku();
			displaySudoku();
			moveCount++;
		}
		System.out.println("Congratulations! You solved the puzzle in " + moveCount + " moves.");
	}

}
