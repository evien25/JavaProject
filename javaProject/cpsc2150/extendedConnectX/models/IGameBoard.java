package cpsc2150.extendedConnectX.models;

/**
 * IGameBoard is an interface which contains the methods and functions implemented by GameBoard, GameBoardMem, and AbsGameBoard.
 * 
 * @initialization Ensures: [2-D array, BOARD, is created of size ROWS X COLUMNS.] 
 *          [Number of characters needed to be in a row is initialized.]
 *
 * @defines:
 *      - BOARD: A 2-D array representing the game's current state.
 *      - ROWS: Number of rows for the BOARD.
 *      - COLUMNS: Number of rows for the BOARD.
 *      - NumToWin: Number of characters needed to be in a row in BOARD to end the game as a win.
 *      - MaxRow: Maximum number of rows for BOARD.
 *      - MinRow: Minimum number of rows for BOARD.
 *      - MaxCol: Maximum number of columns for BOARD.
 *      - MinCol: Minimum number of columns for BOARD.
 *      - MaxWin: Maximum number of characters allowed to be in a row to signify a win BOARD.
 *      - MinWin: Maximum number of characters allowed to be in a row to signify a win BOARD.
 * 
 * @constraints:
 *      - [BOARD size is intialized to be of size ROWS x COLUMNS.]
 *      - MinRow <= ROWS <= MaxRow
 *      - MinCol <= COLUMNS <= MaxCol
 *      - MinWin <= NumToWin <= MaxWin
 */
public interface IGameBoard {
    
    /**
     * A get function that will return the number of rows.
     * 
     * @return number of rows
     * 
     * @pre none
     * 
     * @post self = #self AND [return number of rows for the game board]
     */
    public int getNumRows();

    /**
     * A get function that will return the number of columns.
     * 
     * @return number of columns
     * 
     * @pre none
     * 
     * @post self = #self AND [return number of columns for the game board]
     */
    public int getNumColumns();

    /**
     * A get function that will return number of characters needed in a row to win.
     * 
     * @return number to win
     * 
     * @pre none
     * 
     * @post self = #self AND [return number of characters needed in a row to win]
     */
    public int getNumToWin();

    /**
     * Returns true if the column can accept another token; false otherwise.
     *
     * @param c column to be checked
     *
     * @return if the column is free return true, otherwise return false
     *
     * @pre 0 <= c < COLUMNS
     *
     * @post self = #self AND [If the top-most row for column c is blank] return true : return false [A column is free if the top-most position in that column is unoccupied.]
     */
    default public boolean checkIfFree(int c){
        /*If there is no player on the top row of the checked column then the column is free.*/
        BoardPosition pos = new BoardPosition(getNumRows()-1,c);

        if(whatsAtPos(pos) == ' '){
            return true;
        }else{
            return false;
        }

    }

    /**
     * Places token for player p in top open position.
     * 
     * @param p player which is placing the token
     * @param c the column in which the token is to be placed
     * 
     * @pre 0 <= c < MaxCol && checkIfFree(c) == true
     * 
     * @post COLUMNS = #COLUMNS AND ROWS = #ROWS AND NumToWin = #NumToWin 
     *         And [The token is placed in the top-most available position of column c.]
     */
    public void dropToken(char p, int c);

    /**
     * This function will check to see if the last token placed in column c resulted in the player winning the game.
     * If so it will return true, otherwise false. Note: this is not checking the entire board for a win, it is just
     * checking if the last token placed results in a win.
     *
     * @param c the column where the last token was placed
     *
     * @return return true if the placed token completes the maximum number of consecutive pieces needed for a win (horizontally, vertically, or diagonally); : false.
     *
     * @pre 0 <= c < COLUMNS
     *
     * @post self = #self AND [This method returns true if the placed token is the last to make up the maximum number of consecutive same markers needed to win either vertically, horizontally, or diagonally.]
     *
     */
    default public boolean checkForWin(int c){
        /*Goes through each row from the top until it reaches the most current placed token.*/
        for(int r = getNumRows()-1; r >= 0; r--){
            
            BoardPosition pos = new BoardPosition(r, c);
            /*When it reaches the top token it checks each type of possible win.*/
            if(whatsAtPos(pos) != ' '){

                if(checkDiagWin(pos, whatsAtPos(pos)) == true || checkHorizWin(pos, whatsAtPos(pos)) == true ||
                        checkVertWin(pos, whatsAtPos(pos)) == true){

                    return true;

                }else{
                    /*If all the types of wins return false then player didn't win and false is returned.*/
                    return false;
                }

                    
            }

        }
        return false;
    }
    
    /**
     * This function will check to see if the game has resulted in a tie. A game is tied if there are no free board
     * positions remaining. You do not need to check for any potential wins because we can assume that the checkForWin()
     * is checking for win conditions as the game is played. It will return true if the game is tied and
     * false otherwise.
     * 
     * @return return true if the game has been tied (no free board positions AND no player has won), otherwise return false
     * 
     * @pre none
     * 
     * @post self = #self AND [if no spaces left and no winner return true, else return false]
     */
    default public boolean checkTie(){

        /*Goes through the gameboard and if any column on the top row is empty then there is no tie.*/
        for(int c = 0; c < getNumColumns(); c++){

            BoardPosition temp = new BoardPosition(getNumRows()-1, c);

            if(whatsAtPos(temp) == ' '){
                return false;
            }  

        }
        /*If it goes throught the entire for loop that means every location is filled meaning there is a tie 
            assuming the wins were already checked.*/
        return true;

    }

    /**
     * Checks to see if the last token placed (which was placed in position pos by player p) resulted in NumToWin in
     * a row horizontally. Returns true if it does, otherwise false.
     *
     * @param pos position of the last token placed
     * @param p player that placed the token
     *
     * @return return true if a win occured horizontally by that player, otherwise return false
     *
     * @pre p != ' '
     *
     * @post self = #self AND [if last token placed results in NumToWin in a row horizontally return true, else return false]
     */
    default public boolean checkHorizWin(BoardPosition pos, char p){
        /*checks to see if the last token placed (which was placed in position pos by player p) resulted in 5 in
        a row horizontally. Returns true if it does, otherwise false*/

        int columnIndexLeft = pos.getColumn(), columnIndexRight = pos.getColumn(), totalInARow = 0;
        char atPosChar = p;

        /* checks all tiles to the left of the starting position. note: this while loop will account for the initial
        * spot that is being checked, hence the totalInARow being incremented at the beginning*/
        while (columnIndexLeft > -1 && atPosChar == p) {
            totalInARow++;
            columnIndexLeft--;

            if (columnIndexLeft > -1) {
                BoardPosition tempPos = new BoardPosition(pos.getRow(), columnIndexLeft);
                atPosChar = whatsAtPos(tempPos);
            }

            if (totalInARow == getNumToWin()) {
                return true;
            }
        }
        atPosChar = p;

        /*checks all tiles to the right of the starting position*/
        do {
            columnIndexRight++;

            if (columnIndexRight < getNumColumns()) {
                BoardPosition tempPos = new BoardPosition(pos.getRow(), columnIndexRight);
                atPosChar = whatsAtPos(tempPos);
                if (atPosChar == p) {
                    totalInARow++;
                }
            }

            if (totalInARow == getNumToWin()) {
                return true;
            }

        } while (columnIndexRight < getNumColumns() && atPosChar == p);

        
        return false;
    }

    /**
     * Checks to see if the last token placed (which was placed in position pos by player p) resulted in NumToWin in a row
     * vertically. Returns true if it does, otherwise false.
     *
     * @param pos position of the last token placed
     * @param p player that placed the token
     *
     * @return return true if a win occurred vertically by that player, otherwise return false
     *
     * @pre p != ' '
     *
     * @post self = #self AND [if last token placed results in NumToWin in a row vertically return true, else return false]
     */
    default public boolean checkVertWin(BoardPosition pos, char p){
        /*checks to see if the last token placed (which was placed in position pos by player p) resulted in 5 in a row
        vertically. Returns true if it does, otherwise false*/

        int rowIndexBelow = pos.getRow(), totalInARow = 0;
        char atPosChar = p;

        /* checks all tiles below the starting position. note: this while loop will account for the initial
         * spot that is being checked, hence the totalInARow being incremented at the beginning*/
        while (rowIndexBelow > -1 && atPosChar == p) {
            totalInARow++;
            rowIndexBelow--;

            if (rowIndexBelow > -1) {
                BoardPosition tempPos = new BoardPosition(rowIndexBelow, pos.getColumn());
                atPosChar = whatsAtPos(tempPos);
            }
        }
        
        if (totalInARow == getNumToWin()) {
            return true;
        }
        return false;
    }

    /**
     * checks to see if the last token placed (which was placed in position pos by player p) resulted in NumToWin in a row
     * diagonally. Returns true if it does, otherwise false.
     *
     * @param pos position of the last token placed
     * @param p player that placed the token
     *
     * @return return true if a win occured diagonally by that player, otherwise return false
     *
     * @pre p != ' '
     *
     * @post self = #self AND [if last token results in NumToWin in a row diagonally (left or right) return TRUE, else return FALSE.]
     */
    default public boolean checkDiagWin(BoardPosition pos, char p){
        /*checks to see if the last token placed (which was placed in position pos by player p) resulted in 5 in a row
        diagonally. Returns true if it does, otherwise false Note: there are two diagonals to check*/

        int consecutiveTokens = 0;
        int row = pos.getRow();
        int col = pos.getColumn();
        
        // Adjust the starting point for top-left to bottom-right diagonal
        if (row > col) {
            row -= col;
            col = 0;
        } 
        else {
            col -= row;
            row = 0;
        }
        
        // Check top-left to bottom-right diagonal
        while (row < getNumRows() && col < getNumColumns() && consecutiveTokens != getNumToWin()) {
            
            BoardPosition temp = new BoardPosition(row, col);

            if (whatsAtPos(temp) == p) {
                consecutiveTokens++;
            } 
            else {
                consecutiveTokens = 0;
            }
            row++;
            col++;
            
            if (consecutiveTokens == getNumToWin()){
                return true;
            }
        }
        
        // If no win is found in the first diagonal, check the other one
        if (consecutiveTokens != getNumToWin()) {
            consecutiveTokens = 0;
            row = pos.getRow();
            col = pos.getColumn();
        
            // Adjust the starting point for bottom-right to top-left diagonal
            while (row < getNumRows()-1 && col > 0) {
                row++;
                col--;
            }
        
            // Check bottom-right to top-left diagonal
            while (row >= 0 && col < getNumColumns() && consecutiveTokens != getNumToWin()) {
                
                BoardPosition temp = new BoardPosition(row, col);
                
                if (whatsAtPos(temp) == p) {
                    consecutiveTokens++;
                } 
                else {
                    consecutiveTokens = 0;
                }
                row--;
                col++;
                
                if (consecutiveTokens == getNumToWin()){
                    return true;
                }
            }
        } 
        
        if (consecutiveTokens == getNumToWin()){
            return true;
        }
    
        return false;
        
    }
    
    /**
     * Returns what is in the given position in BOARD.
     * 
     * @param pos position of gameboard to check
     * 
     * @return the player whose piece is in that spot or ' ' if the position is empty
     *
     * @pre None
     *
     * @post self = #self AND [returns the character found at BoardPosition pos in BOARD]
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * Returns true if the player is at pos; otherwise, it returns false.
     *
     * @param pos position to check
     * @param player player to check on behalf of
     *
     * @return return true if that player is in that position, otherwise return false
     *
     * @pre p != ' ' AND [p is a valid player character]
     *
     * @post self  = #self AND [if player == pos return true, else return false]
     */
    default public boolean isPlayerAtPos(BoardPosition pos, char player){
        /*Checks if the player from the parameter is the same at the player at the position in the parameter. */
        if(whatsAtPos(pos) == player){
            return true;
        }else{
            return false;
        }   
    }
}
