package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
Titus Ahlborn, tjahlborn
Tyler Kriney, tylerkriney
Jake Lunski, JakeLunski
Eric Vien, evien
 */

public class GameBoard extends AbsGameBoard{
    /**
     * This GameBoard class holds data related to a 
     * 
     * @invariant [board is a 2-D array of characters] AND MinRow <= numRows <= MaxRow AND MinCol <= numColumns <= MaxCol
     *         MinWin <= numToWin <= MaxWin
     * 
     * @corresponds: self.BOARD = board AND self.ROWS = numRows AND self.COLUMNS = numColumns
     *      AND self.NumToWin = numToWin
     * 
     */
    private char board[][];
    private int numRows;
    private int numColumns;
    private int numToWin;

    /**
    * Initializes a new GameBoard object with default settings.
    *
    * @pre 3 < aRow < 100 AND 3 < aColumn < 100 AND 3 < aNumToWin < 25
    *
    * @post numRows = aRow AND numColumns = aColumn AND numToWin = aNumToWin AND
                [A gameboard of size numRows by numColumns is initialized with each position empty.]
    */
    public GameBoard(int aRow, int aColumn, int aNumToWin)
    {
        this.numRows = aRow;
        this.numColumns = aColumn;
        this.numToWin = aNumToWin;
        /*Initializing a new 9X7 board array that has blank spots in each spot.*/
        board = new char[numRows][numColumns];
        for(int r = 0; r < numRows; r++){
            for(int c = 0; c < numColumns; c++){
                board[r][c] = ' ';
            }
        }
    }

    
    public int getNumRows(){
        return numRows;
    }

    public int getNumColumns(){
        return numColumns;
    }

    public int getNumToWin(){
        return numToWin;
    }

    public void dropToken(char p, int c)
    {
        /*Initializing an occupied boolean variable to be true and a counter variable r that is for the rows of the board.*/
        boolean occupied = true;
        int r = 0;
        
        /*Places the character p in column c. The token will be placed in the lowest available row in column c.*/
        while(occupied){
            if(board[r][c] == ' '){
                occupied = false;
            }else{
                r++;
            }
        }
        board[r][c] = p;
    }

    public char whatsAtPos(BoardPosition pos)
    {
        /*Returns what is in the GameBoard at position pos If no marker is there, it returns a blank space char.*/
        return board[pos.getRow()][pos.getColumn()];
    }
}
