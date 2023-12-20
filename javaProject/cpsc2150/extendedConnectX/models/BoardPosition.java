package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
Tyler Kriney, tylerkriney
Jake Lunski, Jakelunski
Titus Ahlborn, tjahlborn
Eric Vien, evien

 */

public class BoardPosition
{
    /**
     * This BoardPosition class holds values relating to coordinates for a connect four game board.
     *
     * @invariant Row >= 0 AND Column >= 0
     *
     */
    private int Row;
    private int Column;

    /**
     * Constructor for BoardPosition. Takes 2 params relating to Row and Column fields.
     *
     * @pre aRow >= 0 AND aColumn >= 0 AND aRow <= 9 AND aColumn <= 7
     *
     * @post Row = aRow AND Column = aColumn
     *
     * @param aRow [row position on the game board]
     * @param aColumn [column position on the game board]
     */
    public BoardPosition(int aRow, int aColumn)
    {
        this.Row = aRow;
        this.Column = aColumn;
    }

    /**
     * Returns the value for Row.
     *
     * @return [Row value]
     *
     * @pre None
     *
     * @post getRow = Row AND Row = #Row AND Column = #Column
     *
     */
    public int getRow()
    {
        return this.Row;
    }

    /**
     * Returns the vlaue for Column.
     *
     * @return [Column value]
     *
     * @pre None
     *
     * @post getColumn = Column AND Column = #Column AND Row = #Row
     *
     */
    public int getColumn()
    {
        return this.Column;
    }

    /**
     * Equals compares two BoardPositions to see if they have the same row and column.
     *
     * @param obj is a BoardPositon object
     *            
     * @return If BoardPosition object 1 = BoardPosition object 2 return TRUE
     * @return cont. If BoardPosition object 1 != BoardPosition object 2 return FALSE
     *
     * @pre None
     *
     * @post if BoardPosition obj1 Row = BoardPosition obj2 Row AND Board Position obj1 Column = BoardPositon obj2
     * @post cont. Column BoardPositon obj2 Column return TRUE. Otherwise return FALSE
     */
    @Override
    public boolean equals(Object obj)
    {
        BoardPosition temp = (BoardPosition)obj;
        if(this.Row == temp.getRow() && this.Column == temp.getColumn()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * This function converts the numerical values for Row and Column to a string in "<Row>,<Column>" format
     *
     * @return string with row and column position in "<Row>,<Column>" format
     *
     * @pre None
     *
     * @Post String = "<Row>,<Column>" AND Column = #Column AND Row = #Row
     *
     */
    @Override
    public String toString()
    {
        String output = Row + "," + Column;
        return output;
    }
}