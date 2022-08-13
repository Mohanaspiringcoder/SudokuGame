package sudoku.game;

/*
 * Name: Mohan Dongara
 * Email ID: mohandongara@gmail.com
 * Java Project
 */
import java.util.Scanner;

public class PlaySudoku {
	
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

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char level_char = 0;
		int level = 0;
		
		System.out.println("Hey " + TEXT_BLUE + TEXT_BOLD + "USER! " + TEXT_RESET + "Welcome to " + TEXT_PURPLE_BOLD + "SUDOKU9x9\n" + TEXT_RESET);
		System.out.println(TEXT_BOLD + "RULES OF THE GAME: " + TEXT_RESET);
		System.out.println("Enter " + TEXT_CYAN_BOLD + "Row No. (1-9), " + TEXT_GREEN + "Column No. (1-9)" + TEXT_RESET + " and Number (1-9)\nto place the number at the given location.");
		System.out.println("For Example, " + TEXT_BOLD + "1 4 6 " + TEXT_RESET + "will place the number 6 at (1, 4).");
		System.out.println("Enter " + TEXT_BOLD + "q " + TEXT_RESET + "to quit the game.\n");
		do {
			System.out.println("Please choose a level (1, 2, 3): ");
			System.out.println("1. Easy");
			System.out.println("2. Medium");
			System.out.println("3. Hard");
			level_char = sc.next().charAt(0); 
			level = Character.getNumericValue(level_char); 
			if(level < 1 || level > 3)
				System.out.println(TEXT_RED + "Invalid choice.\n" +TEXT_RESET);
			}while(level < 1 || level > 3);
		
		Sudoku s = new Sudoku(level);
		
		s.playSudoku();

	}

}
