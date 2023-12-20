package cpsc2150.extendedConnectX.models;

public abstract class AbsGameBoard implements IGameBoard{
    
    /**
     * This function converts the array that holds the GameBoard into a string that may be printed to the screen.
     *
     * @return string that holds the entire game board for printing to the screen
     * 
     * @pre None
     * 
     * @Post [returns a string representation of the gameboard that may be printed to the screen, self = #self]
     */
    @Override
    public String toString(){
        /*Creating a string variable for the print. */
        String print = "";

        /*Top row of the game board grid.*/
        for(int i = 0; i < getNumColumns(); i++){
            if(i < 10){
                print += ("|" + " " + i);    
            }else{
                print += ("|" + i);
            }
        }

        /*Adding next line.*/
        print += ("|\n");

        /*Will continue until it has gone through entire board. Starting at the top and working down.*/
        for(int r = getNumRows()-1; r >= 0; r--){

            for(int c = 0; c < getNumColumns(); c++){
                //Adding components to game board grid.
                BoardPosition pos = new BoardPosition(r, c);
                print += ("|" + whatsAtPos(pos) + " ");
            }

            /*Going to next row in the game board.*/
            print += ("|\n");
        }

        return print;
    }

}
