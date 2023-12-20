package cpsc2150.extendedConnectX.models;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * @invariant 3 <= numRows <= 100 and 3 <= numColumns <= 100 and 3 <= numToWin <= min(numRows, numColumns)
 * @invariant [No blank space between two player tokens in the columns]
 *
 * @corresponds self = hashmap where each key is a player character and value is a list of BoardPositions for that player
 * @corresponds [number of rows] = numRows
 * @corresponds [number of columns] = numColumns
 * @corresponds [number of characters needed to win] = numToWin
 * @corresponds [game board state] = boardMap
 */
public class GameBoardMem extends AbsGameBoard {
    
    private int numRows;
    private int numColumns;
    private int numToWin;
    private Map<Character, List<BoardPosition>> boardMap;

    /**
     * @param aRow Number of rows for the game board
     * @param aColumn Number of columns for the game board
     * @param aNumToWin Number of tokens in a row required to win
     * @pre 3 <= aRow <= 100, 3 <= aColumn <= 100, 3 <= aNumToWin <= min(aRow, aColumn)
     * @post numRows = aRow, numColumns = aColumn, numToWin = aNumToWin, boardMap is initialized as an empty map
     */
    public GameBoardMem(int aRow, int aColumn, int aNumToWin) {
        this.numRows = aRow;
        this.numColumns = aColumn;
        this.numToWin = aNumToWin;
        boardMap = new HashMap<>();
    }

    /**
     * Places a token for player p in the top-most open position of column c.
     *
     * @param p The player who is placing the token.
     * @param c The column where the token is placed.
     *
     * @pre 0 <= c < numColumns, checkIfFree(c) == true, [no blank space below the top-most token in column c]
     * @post self = #self AND [The token is placed in the top-most available position of column c.]
     */
    public void dropToken(char p, int c) {
        // Creates a list to store players positions
        boardMap.putIfAbsent(p, new ArrayList<>());
    
        int r = 0;
        BoardPosition place = new BoardPosition(r, c);
        
        // Finds the top-most available position in the column
        while (whatsAtPos(place) != ' ' && r < numRows) {
            r++;
            place = new BoardPosition(r, c);
        }
        
        // Adds the position to the players list if its within the boards boundaries
        if (r < numRows) 
            boardMap.get(p).add(place);
    }

    /**
     * Returns the character at the specific position on the game board.
     *
     * @param pos The position on the game board to check.
     * @return The player whose piece is in that spot, or ' ' if the position is empty.
     *
     * @pre None
     * @post self = #self AND [Returns the character found at the specified BoardPosition pos on the game board.]
     */
    public char whatsAtPos(BoardPosition pos) {
        // Gets the set of tokens from the gameMap
        Set<Character> playerTokens = boardMap.keySet();

        // Loops through the player tokens to find if the position is in their list
        for (char token : playerTokens) {
            List<BoardPosition> positions = boardMap.get(token);
            if (positions.contains(pos)) {
                return token;
            }
        }

        return ' ';
    }

    /**
     * Checks if the specified player is at the given position on the game board.
     *
     * @param pos The position to check.
     * @param player The player to check if their token is at the position.
     * @return True if the player is in that position, otherwise false.
     *
     * @pre player != ' '
     * @post self = #self AND [If player is at the specified position pos, return true; else, return false.]
     */
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if(!boardMap.containsKey(player)) {
            return false;
        }

        return boardMap.get(player).contains(pos);
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public int getNumToWin() {
        return numToWin;
    }
}
