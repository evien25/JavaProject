package cpsc2150.extendedConnectX.Test;

import org.junit.Test;
import static org.junit.Assert.*;

import cpsc2150.extendedConnectX.models.BoardPosition;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.IGameBoard;

public class TestGameBoard {
    //factory to create the correct gameboard type
    private IGameBoard IGameBoardFactory(int aRow, int aColumn, int aNumToWin){
        return new GameBoard(aRow, aColumn, aNumToWin);
    }

    @Test
    public void testConstructor_rows3_cols3_numToWin3(){
        //test that constructor properly creates gameboard with 3 rows, 3 columns, and 3 number to win
        int ROWS = 3;
        int COLS = 3;
        int NUMTOWIN = 3;
        Character expected[][] = new Character[ROWS][COLS];
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                expected[i][j] = ' ';
            }
        }
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUMTOWIN);

        assertEquals(toString(expected, ROWS, COLS), gb.toString());
        assertEquals(COLS, gb.getNumColumns());
        assertEquals(ROWS, gb.getNumRows());
        assertEquals(NUMTOWIN, gb.getNumToWin());
    }

    @Test
    public void testConstructor_rows100_cols100_numToWin25(){
        //test that constructor properly creates gameboard with 100 rows, 100 columns, and 16 number to win
        int ROWS = 100;
        int COLS = 100;
        int NUMTOWIN = 25;
        Character expected[][] = new Character[ROWS][COLS];
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                expected[i][j] = ' ';
            }
        }
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUMTOWIN);

        assertEquals(toString(expected, ROWS, COLS), gb.toString());
        assertEquals(COLS, gb.getNumColumns());
        assertEquals(ROWS, gb.getNumRows());
        assertEquals(NUMTOWIN, gb.getNumToWin());
    }

    @Test
    public void testConstructor_rows35_cols20_numToWin20(){
        //test that constructor properly creates gameboard with 35 rows, 20 columns, and 20 number to win
        int ROWS = 35;
        int COLS = 20;
        int NUMTOWIN = 20;
        Character expected[][] = new Character[ROWS][COLS];
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                expected[i][j] = ' ';
            }
        }
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUMTOWIN);

        assertEquals(toString(expected, ROWS, COLS), gb.toString());
        assertEquals(COLS, gb.getNumColumns());
        assertEquals(ROWS, gb.getNumRows());
        assertEquals(NUMTOWIN, gb.getNumToWin());
    }

    @Test
    public void testHorizWin_Rows5_Cols5_NumToWin4_TokenAtCol_1_2_3_PlaceTokenAtCol_0_True() {
        //test that checkHorizWin will return true when a token is placed in the first column (0) when 3 tokens are placed in columns 1 2 and 3
        //left boundary check
        int ROWS = 5;
        int COLS = 5;
        int DROP_ONE = 1;
        int DROP_TWO = 2;
        int DROP_THREE = 3;
        int NUM_TO_WIN = 4;
        int FINAL_ROW = 0;
        int FINAL_COL = 0;
        char PLAYER = 't';
        BoardPosition BP = new BoardPosition(FINAL_ROW, FINAL_COL);
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUM_TO_WIN);

        gb.dropToken(PLAYER, DROP_ONE);
        gb.dropToken(PLAYER, DROP_TWO);
        gb.dropToken(PLAYER, DROP_THREE);
        gb.dropToken(PLAYER, FINAL_COL);

        assertTrue(gb.checkHorizWin(BP, PLAYER));
    }

    @Test
    public void testHorizWin_Rows5_Cols5_NumToWin4_TokenAtCol_1_2_3_PlaceTokenAtCol_4_True() {
        //test that checkHorizWin will return true when a token is placed in the last column (4) when 3 tokens are placed in columns 1 2 and 3
        //right boundary check
        int ROWS = 5;
        int COLS = 5;
        int DROP_ONE = 1;
        int DROP_TWO = 2;
        int DROP_THREE = 3;
        int NUM_TO_WIN = 4;
        int FINAL_ROW = 0;
        int FINAL_COL = 4;
        char PLAYER = 't';
        BoardPosition BP = new BoardPosition(FINAL_ROW, FINAL_COL);
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUM_TO_WIN);

        gb.dropToken(PLAYER, DROP_ONE);
        gb.dropToken(PLAYER, DROP_TWO);
        gb.dropToken(PLAYER, DROP_THREE);
        gb.dropToken(PLAYER, FINAL_COL);

        assertTrue(gb.checkHorizWin(BP, PLAYER));
    }

    @Test
    public void testHorizWin_Rows3_Cols3_NumToWin3_PlaceTokenAtCol_2_True() {
        //test that checkHorizWin will return true when a token is placed in the last column (2) while numToWin is 1
        //challenge test: check if one token to win bugs the game
        int ROWS = 3;
        int COLS = 3;
        int NUM_TO_WIN = 3;
        int FINAL_ROW = 0;
        int FINAL_COL = 2;
        char PLAYER = 't';
        BoardPosition BP = new BoardPosition(FINAL_ROW, FINAL_COL);
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUM_TO_WIN);

        for (int t = 0; t < FINAL_COL; t++) {
            gb.dropToken(PLAYER, t);
        }
        gb.dropToken(PLAYER, FINAL_COL);

        assertTrue(gb.checkHorizWin(BP, PLAYER));
    }

    @Test
    public void testHorizWin_Rows2_Cols25_NumToWin25_TokenAtCol_0_To_11_And_13_To_24_PlaceTokenAtCol_12_True() {
        //test that checkHorizWin will return true when a token is placed in the thirteenth column (12) while numToWin is 25
        //challenge test: check if one token to win bugs the game
        int ROWS = 2;
        int COLS = 25;
        int NUM_TO_WIN = 25;
        int FINAL_ROW = 0;
        int FINAL_COL = 12;
        char PLAYER = 't';
        BoardPosition BP = new BoardPosition(FINAL_ROW, FINAL_COL);
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUM_TO_WIN);

        for (int t = 0; t < COLS; t++) {
            if (t == FINAL_COL) continue;

            gb.dropToken(PLAYER, t);
        }

        gb.dropToken(PLAYER, FINAL_COL);

        assertTrue(gb.checkHorizWin(BP, PLAYER));
    }

    @Test
    public void testVertWin_Rows3_Cols3_NumToWin3_PlaceTokenAtRow_0_True() {
        //test that checkVertWin will return true when a token is placed in the first column (0) at row 3 (2) when numToWin is 3
        //upper bound check
        int ROWS = 3;
        int COLS = 3;
        int NUM_TO_WIN = 3;
        int FINAL_ROW = 2;
        int FINAL_COL = 0;
        char PLAYER = 't';
        BoardPosition BP = new BoardPosition(FINAL_ROW, FINAL_COL);
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUM_TO_WIN);

        for (int t = 0; t < ROWS; t++) {
            gb.dropToken(PLAYER, FINAL_COL);
        }

        assertTrue(gb.checkVertWin(BP, PLAYER));
    }

    @Test
    public void testVertWin_Rows25_Cols2_NumToWin25_TokenAtRow_0_to_23_PlaceTokenAtRow_24_True() {
        //test that checkVertWin will return true when a token is placed in the first column (0) at row 25 (24) when 24 tokens are placed in rows 1 to 24 (0 to 23)
        //upper boundary check
        int ROWS = 25;
        int COLS = 2;
        int DROP_COL = 0;
        int NUM_TO_WIN = 25;
        int FINAL_ROW = 24;
        int FINAL_COL = 0;
        char PLAYER = 't';
        BoardPosition BP = new BoardPosition(FINAL_ROW, FINAL_COL);
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUM_TO_WIN);

        for (int t = 0; t < FINAL_ROW; t++) {
            gb.dropToken(PLAYER, DROP_COL);
        }

        gb.dropToken(PLAYER, FINAL_COL);

        assertTrue(gb.checkVertWin(BP, PLAYER));
    }

    @Test
    public void testVertWin_Rows15_Cols2_NumToWin3_Alternating_Different_Tokens_Every_2_Tokens_Until_PlaceTokenAtRow_14_True() {
        //test that checkVertWin will return true when numToWin is 3 and for 12 token slots the tokens alternate between t and p until 12-15 (11-14) are 3 t's in a row
        //challenge boundary check
        int ROWS = 15;
        int COLS = 2;
        int DROP_COL = 0;
        int NUM_TO_WIN = 3;
        int FINAL_ROW = 14;
        int FINAL_COL = 0;
        char PLAYER_ONE = 't';
        char PLAYER_TWO = 'e';
        int ticker = 0;
        BoardPosition BP = new BoardPosition(FINAL_ROW, FINAL_COL);
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUM_TO_WIN);

        for (int t = 0; t < FINAL_ROW; t++) {
            if (ticker < 2) gb.dropToken(PLAYER_ONE, DROP_COL);
            else gb.dropToken(PLAYER_TWO, DROP_COL);

            ticker++;
            if (ticker > 3) ticker = 0;
        }

        gb.dropToken(PLAYER_ONE, FINAL_COL);

        assertTrue(gb.checkVertWin(BP, PLAYER_ONE));
    }

    @Test
    public void testVertWin_Rows5_Cols5_NumToWin3_FullRow_With_OpposingToken_OnTop_And_OnBottom_3InARow_InMiddle_True() {
        //test that checkVertWin will return true on a game where the 3 tokens in a row are sandwhiched by 2 other tokens
        //routine test
        int ROWS = 5;
        int COLS = 5;
        int NUM_TO_WIN = 3;
        int FINAL_ROW = 3;
        int FINAL_COL = 2;
        char PLAYER_ONE = 't';
        char PLAYER_TWO = 'e';
        BoardPosition BP = new BoardPosition(FINAL_ROW, FINAL_COL);
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUM_TO_WIN);

        gb.dropToken(PLAYER_TWO, FINAL_COL);
        gb.dropToken(PLAYER_ONE, FINAL_COL);
        gb.dropToken(PLAYER_ONE, FINAL_COL);
        gb.dropToken(PLAYER_ONE, FINAL_COL);
        gb.dropToken(PLAYER_ONE, FINAL_COL);

        assertTrue(gb.checkVertWin(BP, PLAYER_ONE));
    }

    @Test
    public void testDiagWin_Rows3_Cols3_NumToWin3_TokenAt_Row_0_1_Col_0_1_PlaceTokenAt_Row_2_Col_2_True(){
        //test that checks diagonal win on a 3x3 gameboard where the diagonal is bottom left to top right
        //final token placed top right
        int ROWS = 3;
        int COLS = 3;
        int NUMTOWIN = 3;
        char PLAYER = 't';
        int FINAL_ROW = 2;
        int FINAL_COL = 2;
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUMTOWIN);
        BoardPosition FINAL = new BoardPosition(FINAL_ROW, FINAL_COL);

        for(int i = 0; i < FINAL_COL+1; i++){
            for(int j = 0; j < i; j++){
                gb.dropToken('n', i);
            }
            gb.dropToken(PLAYER, i);
        }

        assertTrue(gb.checkDiagWin(FINAL, PLAYER));
    }

    @Test
    public void testDiagWin_Rows3_Cols3_NumToWin3_TokenAt_Row_0_2_Col_0_2_PlaceTokenAt_Row_1_Col_1_True(){
        //test that checks diagonal win on a 3x3 gameboard where the diagonal is bottom left to top right
        //final token placed middle
        int ROWS = 3;
        int COLS = 3;
        int NUMTOWIN = 3;
        char PLAYER = 't';
        int FINAL_ROW = 1;
        int FINAL_COL = 1;
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUMTOWIN);
        BoardPosition FINAL = new BoardPosition(FINAL_ROW, FINAL_COL);

        for(int i = 0; i < COLS; i++){
            for(int j = 0; j < i; j++){
                gb.dropToken('n', i);
            }
            gb.dropToken(PLAYER, i);
        }

        assertTrue(gb.checkDiagWin(FINAL, PLAYER));
    }

    @Test
    public void testDiagWin_Rows3_Cols3_NumToWin3_TokenAt_Row_1_2_Col_1_2_PlaceTokenAt_Row_0_Col_0_True(){
        //test that checks diagonal win on a 3x3 gameboard where the diagonal is bottom left to top right
        //final token placed bottom left
        int ROWS = 3;
        int COLS = 3;
        int NUMTOWIN = 3;
        char PLAYER = 't';
        int FINAL_ROW = 0;
        int FINAL_COL = 0;
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUMTOWIN);
        BoardPosition FINAL = new BoardPosition(FINAL_ROW, FINAL_COL);

        for(int i = 0; i < COLS; i++){
            for(int j = 0; j < i; j++){
                gb.dropToken('n', i);
            }
            gb.dropToken(PLAYER, i);
        }

        assertTrue(gb.checkDiagWin(FINAL, PLAYER));
    }

    @Test
    public void testDiagWin_Rows5_Cols5_NumToWin5_TokenAt_Row_4_3_2_1_Col_0_1_2_3_PlaceTokenAt_Row_0_Col_4_True(){
        //test that checks diagonal win on a 5x5 gameboard where the diagonal is top left to bottom right
        //final token placed bottom right
        int ROWS = 5;
        int COLS = 5;
        int NUMTOWIN = 5;
        char PLAYER = 't';
        int FINAL_ROW = 0;
        int FINAL_COL = 4;
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUMTOWIN);
        BoardPosition FINAL = new BoardPosition(FINAL_ROW, FINAL_COL);

        for(int i = 0; i < FINAL_COL+1; i++){
            for(int j = 0; j < 4-i; j++){
                gb.dropToken('n', i);
            }
            gb.dropToken(PLAYER, i);
        }

        assertTrue(gb.checkDiagWin(FINAL, PLAYER));
    }

    @Test
    public void testDiagWin_Rows5_Cols5_NumToWin5_TokenAt_Row_4_3_1_0_Col_0_1_3_4_PlaceTokenAt_Row_2_Col_2_True(){
        //test that checks diagonal win on a 5x5 gameboard where the diagonal is top left to bottom right
        //final token placed middle
        int ROWS = 5;
        int COLS = 5;
        int NUMTOWIN = 5;
        char PLAYER = 't';
        int FINAL_ROW = 2;
        int FINAL_COL = 2;
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUMTOWIN);
        BoardPosition FINAL = new BoardPosition(FINAL_ROW, FINAL_COL);

        for(int i = 0; i < COLS; i++){
            for(int j = 0; j < 4-i; j++){
                gb.dropToken('n', i);
            }
            gb.dropToken(PLAYER, i);
        }

        assertTrue(gb.checkDiagWin(FINAL, PLAYER));
    }

    @Test
    public void testDiagWin_Rows5_Cols5_NumToWin5_TokenAt_Row_3_2_1_0_Col_1_2_3_4_PlaceTokenAt_Row_4_Col_0_True(){
        //test that checks diagonal win on a 5x5 gameboard where the diagonal is top left to bottom right
        //final token placed top left
        int ROWS = 5;
        int COLS = 5;
        int NUMTOWIN = 5;
        char PLAYER = 't';
        int FINAL_ROW = 4;
        int FINAL_COL = 0;
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUMTOWIN);
        BoardPosition FINAL = new BoardPosition(FINAL_ROW, FINAL_COL);

        for(int i = 0; i < COLS; i++){
            for(int j = 0; j < 4-i; j++){
                gb.dropToken('n', i);
            }
            gb.dropToken(PLAYER, i);
        }

        assertTrue(gb.checkDiagWin(FINAL, PLAYER));
    }

    @Test
    public void testDiagWin_Rows3_Cols3_NumToWin3_PlaceTokenAtRow_0_False(){
        //test that checks diagonal win on a 3x3 gameboard where we have a valid vertical win condition
        //this should return false for the diagonal win test
        int ROWS = 3;
        int COLS = 3;
        int NUM_TO_WIN = 3;
        int FINAL_ROW = 2;
        int FINAL_COL = 0;
        char PLAYER = 't';
        BoardPosition BP = new BoardPosition(FINAL_ROW, FINAL_COL);
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUM_TO_WIN);

        for (int t = 0; t < ROWS; t++) {
            gb.dropToken(PLAYER, FINAL_COL);
        }

        assertFalse(gb.checkDiagWin(BP, PLAYER));
    }

    @Test
    public void testCheckIfFree_Rows3_Cols3_NumToWin3_NoPositionsFilled_ColToCheck_0_True() {
        //test that checkIfFree works with nothing in the board
        //boundary/routine check
        int ROWS = 3;
        int COLS = 3;
        int NUM_TO_WIN = 3;
        int CHECK_COL = 0;

        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUM_TO_WIN);

        assertTrue(gb.checkIfFree(CHECK_COL));
    }

    @Test
    public void testCheckIfFree_AllPositionsFilled_Except_TopRow_At_Col_1_ColToCheck_1_True() {
        //test that checkIfFree will return true with a board filled except for one position at the top row of the column to check
        //challenging test
        int ROWS = 3;
        int COLS = 3;
        int NUM_TO_WIN = 3;
        int CHECK_COL = 1;
        char PLAYER = 't';

        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUM_TO_WIN);

        for (int t = 0; t < ROWS; t++) {
            if (t == CHECK_COL) {
                gb.dropToken(PLAYER, t);
                gb.dropToken(PLAYER, t);

                continue;
            }
            gb.dropToken(PLAYER, t);
            gb.dropToken(PLAYER, t);
            gb.dropToken(PLAYER, t);
        }

        assertTrue(gb.checkIfFree(CHECK_COL));
    }

    @Test
    public void testCheckIfFree_BoardFull_ColToCheck_0_False() {
        //test that checkIfFree returns false with a full board
        //boundary test
        int ROWS = 3;
        int COLS = 3;
        int NUM_TO_WIN = 3;
        int CHECK_COL = 0;
        char PLAYER = 't';

        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUM_TO_WIN);

        for (int c = 0; c < COLS; c++) {
            gb.dropToken(PLAYER, c);
            gb.dropToken(PLAYER, c);
            gb.dropToken(PLAYER, c);
        }

        assertFalse(gb.checkIfFree(CHECK_COL));
    }

    @Test
    public void testCheckTie_AllPositionsFilled_NoWin() {
        int ROWS = 3;
        int COLS = 3;
        int NUMTOWIN = 3;
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUMTOWIN);

        // Fill the board without creating a win condition
        for (int c = 0; c < COLS; c++) {
            for (int r = 0; r < ROWS; r++) {
                char player = (r % 2 == 0) ? 'X' : 'O';
                gb.dropToken(player, c);
            }
        }

        assertTrue(gb.checkTie());
    }

    @Test
    public void testCheckTie_BoardEmpty() {
        int ROWS = 3;
        int COLS = 3;
        int NUMTOWIN = 3;
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUMTOWIN);

        assertFalse(gb.checkTie());
    }
    
    @Test
    public void testCheckTie_OneEmptyPosition_NoWin() {
        int ROWS = 3;
        int COLS = 3;
        int NUMTOWIN = 3;
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUMTOWIN);

        // Fill the board with alternating tokens without creating a win condition
        char[][] tokens = {
            {'X', 'O', 'X'},
            {'X', 'X', 'O'},
            {'O', ' ', 'O'} // Leave one position empty
        };

        // Drop tokens according to the board
        for (int c = 0; c < COLS; c++) {
            for (int r = 0; r < ROWS; r++) {
                if (tokens[r][c] != ' ') {
                    gb.dropToken(tokens[r][c], c);
                }
            }
        }

        // The game should not be a tie since theres one empty space and no win
        assertFalse(gb.checkTie());
    }


    @Test
    public void testCheckTie_SecondToLastMoveLeavesOneSpot() {
        int ROWS = 3;
        int COLS = 3;
        int NUMTOWIN = 3;
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUMTOWIN);

        // Fill the board in a way that avoids a win and leaves one spot open until the second-to-last move
        char[] players = {'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O'};
        int playerIndex = 0;
        for (int c = 0; c < COLS; c++) {
            for (int r = 0; r < ROWS; r++) {
                if (!(c == 2 && r == 1)) { // Skip the second-to-last position
                    gb.dropToken(players[playerIndex++ % 2], c);
                }
            }
        }

        // Assert that the board is not in a tie state yet
        assertFalse(gb.checkTie());

        // Place the last token in the second-to-last position
        gb.dropToken(players[playerIndex % 2], 2);

        // Now the board should be in a tie state
        assertTrue(gb.checkTie());
    }


    @Test
    public void testWhatsAtPos_EmptyPosition() {
        int ROWS = 3;
        int COLS = 3;
        int NUMTOWIN = 3;
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUMTOWIN);

        BoardPosition pos = new BoardPosition(0, 0);
        assertEquals(' ', gb.whatsAtPos(pos));
    }

    @Test
    public void testWhatsAtPos_PositionFilledByPlayerX() {
        int ROWS = 3;
        int COLS = 3;
        int NUMTOWIN = 3;
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUMTOWIN);

        gb.dropToken('X', 0);
        BoardPosition pos = new BoardPosition(0, 0);

        assertEquals('X', gb.whatsAtPos(pos));
    }

    @Test
    public void testWhatsAtPos_PositionFilledByPlayerO() {
        int ROWS = 3;
        int COLS = 3;
        int NUMTOWIN = 3;
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUMTOWIN);

        gb.dropToken('O', 0);
        BoardPosition pos = new BoardPosition(0, 0);

        assertEquals('O', gb.whatsAtPos(pos));
    }

    @Test
    public void testWhatsAtPos_MultipleTokensInColumn() {
        int ROWS = 4;
        int COLS = 3;
        int NUMTOWIN = 3;
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUMTOWIN);

        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);

        assertEquals('X', gb.whatsAtPos(new BoardPosition(0, 1)));
        assertEquals('O', gb.whatsAtPos(new BoardPosition(1, 1)));
        assertEquals('X', gb.whatsAtPos(new BoardPosition(2, 1)));
    }

    @Test
    public void testWhatsAtPos_HigherEmptyPositionInPartiallyFilledColumn() {
        int ROWS = 6;
        int COLS = 3;
        int NUMTOWIN = 3;
        IGameBoard gb = IGameBoardFactory(ROWS, COLS, NUMTOWIN);

        // Filling the second column (index 1) with alternating tokens, but not filling it completely
        gb.dropToken('X', 1);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);

        // Checking a position in the same column that's above the highest token
        BoardPosition pos = new BoardPosition(3, 1); // Position above the last placed token

        assertEquals(' ', gb.whatsAtPos(pos));
    }


    @Test
    public void testisPlayerAtPos_BottomCorner() {
        //Test the first board position to match the inputted token when it is placed in the first position (0,0).

        int Row = 5;
        int Col = 5;
        int numToWin = 3;
        char player_One = 'X';

        int testRow = 0;
        int testCol = 0;

        IGameBoard gb = IGameBoardFactory(Row, Col, numToWin);

        gb.dropToken('X', 0);
        BoardPosition pos = new BoardPosition(testRow,testCol);

        assertTrue(gb.isPlayerAtPos(pos, player_One));

    }

    @Test
    public void testisPlayerAtPos_PlayerDifferentAtPos() {
        //Tests to see if the searched player is not the same as the player in the checked position (0,0).

        int Row = 5;
        int Col = 5;
        int numToWin = 3;
        char player_One = 'X';
        char player_Two = 'O';

        int testRow = 0;
        int testCol = 0;

        IGameBoard gb = IGameBoardFactory(Row, Col, numToWin);

        gb.dropToken(player_Two, 0);
        BoardPosition pos = new BoardPosition(testRow,testCol);

        assertFalse(gb.isPlayerAtPos(pos, player_One));

    }

    @Test
    public void testisPlayerAtPos_Largest_Top_Right_Corner() {
        //Tests the top most corner posibility to match the placed token (99,99).

        int Row = 100;
        int Col = 100;
        int numToWin = 3;
        char player_One = 'X';
        char player_Two = 'O';

        int testRow = 99;
        int testCol = 99;

        IGameBoard gb = IGameBoardFactory(Row, Col, numToWin);

        for(int i = 0; i < Row; i++) {
            if(i % 2 == 0) {
                gb.dropToken(player_Two,Col-1);
            }else {
                gb.dropToken(player_One,Col-1);
            }
        }
        BoardPosition pos = new BoardPosition(testRow,testCol);

        assertTrue(gb.isPlayerAtPos(pos, player_One));

    }

    @Test
    public void testisPlayerAtPos_Multiple_Tokens_on_Top_of_tested_position(){
        //Test when different tokens are placed on top of checked token in column 1.

        int Row = 5;
        int Col = 5;
        int numToWin = 3;
        char player_One = 'X';
        char player_Two = 'O';
        
        int testRow = 0;
        int testCol = 0;

        IGameBoard gb = IGameBoardFactory(Row, Col, numToWin);

        gb.dropToken(player_One, testCol);
        gb.dropToken(player_Two, testCol);
        gb.dropToken(player_Two, testCol);

        BoardPosition pos = new BoardPosition(testRow, testCol);

        assertTrue(gb.isPlayerAtPos(pos, player_One));
    }

    @Test
    public void testisPlayerAtPos_when_board_is_empty(){
        //Test when board is empty should return false for any checked token.

        int Row = 5;
        int Col = 5;
        int numToWin = 3;
        char player_One = 'X';

        int testRow = 0;
        int testCol = 0;

        IGameBoard gb = IGameBoardFactory(Row, Col, numToWin);
        BoardPosition pos = new BoardPosition(testRow, testCol);

        assertFalse(gb.isPlayerAtPos(pos, player_One));

    }

    @Test
    public void testDropToken_Dropped_Same_Token_On_Top_And_To_Right_And_To_Left_Of_Starting_Token_In_Middle(){
        //Test when a token is dropped to left, right, and on top of the same token type.

        int Row = 10;
        int Col = 10;
        int numToWin = 5;
        char player = 'A';

        Character expectedBoard[][] = new Character[Row][Col];

        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Col; c++){
                expectedBoard[r][c] = ' ';
            }
        }

        int testCol = 5;
        int testRow = 0;

        IGameBoard gb = IGameBoardFactory(Row, Col, numToWin);

        gb.dropToken(player, testCol);
        expectedBoard[testRow][testCol] = player;

        testCol--;
        gb.dropToken(player, testCol);
        expectedBoard[testRow][testCol] = player;

        testCol += 2;
        gb.dropToken(player, testCol);
        expectedBoard[testRow][testCol] = player;

        testCol--;
        testRow++;
        gb.dropToken(player, testCol);
        expectedBoard[testRow][testCol] = player;

        assertEquals(toString(expectedBoard, Row, Col), gb.toString());

    }

    @Test
    public void testDropToken_One_Token_Dropped_First_Column(){
        //Test when token is dropped in first position.

        int Row = 5;
        int Col = 5;
        int numToWin = 3;
        char player_One = 'X';

        int testRow = 0;
        int testCol = 0;

        Character expectedBoard[][] = new Character[Row][Col];
        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Col; c++){
                expectedBoard[r][c] = ' ';
            }
        }

        IGameBoard gb = IGameBoardFactory(Row, Col, numToWin);
        gb.dropToken(player_One, testCol);
        expectedBoard[testRow][testCol] = player_One;

        assertEquals(toString(expectedBoard, Row, Col), gb.toString());

    }

    @Test
    public void testDropToken_One_Token_Dropped_in_Largest_Column(){
        //Test when token is dropped in largest column

        int Row = 100;
        int Col = 100;
        int numToWin = 5;
        char player_One = 'X';

        Character expectedBoard[][] = new Character[Row][Col];
        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Col; c++){
                expectedBoard[r][c] = ' ';
            }
        }

        int testRow = 0;
        int testCol = 99;

        IGameBoard gb = IGameBoardFactory(Row, Col, numToWin);
        gb.dropToken(player_One, testCol);
        expectedBoard[testRow][testCol] = player_One;

        assertEquals(toString(expectedBoard, Row, Col), gb.toString());

    }


    @Test
    public void testDropToken_Dropped_Max_Number_Player_Ten_In_Same_Column(){
        //Test when max tokens are dropped on top of each other.

        int Row = 10;
        int Col = 10;
        int numToWin = 5;
        Character players[] = new Character[10];
        char play = 'A';
        
        for(int i = 0; i < players.length; i++){
            players[i] = play;
            play++;
        }

        Character expectedBoard[][] = new Character[Row][Col];

        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Col; c++){
                expectedBoard[r][c] = ' ';
            }
        }

        int testCol = 0;
        int testRow = 0;

        IGameBoard gb = IGameBoardFactory(Row, Col, numToWin);
        for(int r = 0; r < players.length; r++){

            gb.dropToken(players[r], testCol);
            expectedBoard[testRow][testCol] = players[r];
            testRow++;
            
        }

        assertEquals(toString(expectedBoard, Row, Col), gb.toString());

    }

    @Test
    public void testDropToken_Dropped_Max_Number_Player_Ten_In_Same_Row(){
        //Test when max tokens are dropped in same row.

        int Row = 10;
        int Col = 10;
        int numToWin = 5;
        Character players[] = new Character[10];
        char play = 'A';
        
        for(int i = 0; i < players.length; i++){
            players[i] = play;
            play++;
        }

        Character expectedBoard[][] = new Character[Row][Col];

        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Col; c++){
                expectedBoard[r][c] = ' ';
            }
        }

        int testCol = 0;
        int testRow = 0;

        IGameBoard gb = IGameBoardFactory(Row, Col, numToWin);
        for(int c = 0; c < players.length; c++){

            gb.dropToken(players[c], testCol);
            expectedBoard[testRow][testCol] = players[c];
            testRow++;

        }

        assertEquals(toString(expectedBoard, Row, Col), gb.toString());

    }
    
    private String toString(Character board[][], int numRows, int numCols){
        /*Creating a string variable for the print. */
        String print = "";

        /*Top row of the game board grid.*/
        for(int i = 0; i < numCols; i++){
            if(i < 10){
                print += ("|" + " " + i);    
            }else{
                print += ("|" + i);
            }
        }

        /*Adding next line.*/
        print += ("|\n");

        /*Will continue until it has gone through entire board. Starting at the top and working down.*/
        for(int r = numRows-1; r >= 0; r--){

            for(int c = 0; c < numCols; c++){
                //Adding components to game board grid.
                print += ("|" + board[r][c] + " ");
            }

            /*Going to next row in the game board.*/
            print += ("|\n");
        }

        return print;
    }
}
