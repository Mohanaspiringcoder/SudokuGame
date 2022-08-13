package sudoku.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestingSudoku {
	Sudoku ps = new Sudoku(1);
	
	@Test
	void validColumn() {
		boolean isValidColumn = ps.isValidColumn(8); //0 TO 8
		assertEquals(true,isValidColumn);
	}
	
	@Test
	void validRow() {
		boolean isValidRow = ps.isValidRow(-1);  //0 To 8
		assertEquals(false,isValidRow);
	}
	
	@Test
	void validNumber() {
		boolean isValidNumber = ps.isValidNumber(9); //1 TO 9
		assertEquals(true,isValidNumber);
	}
	
	@Test
	void numberInColumn() {
		boolean numberInColumn = ps.isNumberInColumn(1,9);
		assertEquals(true,numberInColumn);
	}
	
	@Test
	void numberInRow() {
		boolean numberInRow = ps.isNumberInRow(8,9);
		assertEquals(false,numberInRow);
	}
	
	@Test
	void numberInLocalGrid() {
		boolean numberInLocalGrid = ps.isNumberInLocalGrid(9,1,9);
		assertEquals(false,numberInLocalGrid);
	}
	
	@Test
	void inBuiltSlot() {
		ps.getInBuiltSlots();
		boolean inbuiltSlot = ps.isSlotInBuilt(2,2);
		assertEquals(true,inbuiltSlot);
	}

	
	
}
