package cpsc2150.extendedConnects;

import cpsc2150.extendedConnectX.models.*;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
Titus Ahlborn, tjahlborn
Jake Lunski, JakeLunski
Tyler Kriney, tylerkriney
Eric Vien, evien
 */

import java.util.Scanner;

public class GameScreen {

    public static void main(String[] args)
    {
        //initial variable declarations
        IGameBoard gameBoard = new GameBoard(0, 0, 0);
        char players[] = new char[10];
        for(int i = 0; i < 10; i++){
            players[i] = ' ';
        }
        boolean playing = true;
        boolean valid = false;
        boolean win = false;
        boolean tie = false;
        boolean found = false;
        boolean needSetup = true;
        int MAX_COL = 100;
        int MIN_COL = 3;
        int MAX_ROW = 100;
        int MIN_ROW = 3;
        int MAX_WIN = 25;
        int userChoice, numPlayers, numRows, numColumns, numToWin, maxNumToWin, counter;
        numPlayers = numRows = numColumns = numToWin = maxNumToWin = counter = 0;
        String strChoice;
        String memoryFlag = "Mm";
        String fastMemFlag = "FfMm";
        char charChoice;
        Scanner keyboard = new Scanner(System.in);

        //while loop that runs the game
        while(playing){
            //preliminary setup questions:
            if(needSetup){
                //decide number of players
                do{
                    System.out.println("How many players?");
                    userChoice = keyboard.nextInt();
                    if(userChoice < 2){
                        System.out.println("Must be at least 2 players");
                    }else if(userChoice > 10){
                        System.out.println("Must be 10 players or fewer");
                    }else{
                        numPlayers = userChoice;
                        valid = true;
                    }
                }while(!valid);
                valid = false;

                //decide characters to represent them
                for(int i = 0; i < numPlayers; i++){
                    do{
                        System.out.println("Enter the character to represent player " + (i+1));
                        charChoice = Character.toUpperCase(keyboard.next().charAt(0));
                        for(int j = 0; j < i; j++){
                            if(players[j] == charChoice){
                                found = true;
                            }
                        }
                        if(found){
                            System.out.println(charChoice + " is already taken as a player token!");
                            found = false;
                        }else{
                            players[i] = charChoice;
                            valid = true;
                        }
                    }while(!valid);
                    valid = false;
                }

                //decide number of rows
                do{
                    System.out.println("How many rows should be on the board?");
                    userChoice = keyboard.nextInt();
                    if(userChoice < MIN_ROW){
                        System.out.println("Must be at least " + MIN_ROW + " rows");
                    }else if(userChoice > MAX_ROW){
                        System.out.println("Must be " + MAX_ROW + " rows or fewer");
                    }else{
                        numRows = userChoice;
                        valid = true;
                    }
                }while(!valid);
                valid = false;

                //decide number of columns
                do{
                    System.out.println("How many columns should be on the board?");
                    userChoice = keyboard.nextInt();
                    if(userChoice < MIN_COL){
                        System.out.println("Must be at least " + MIN_COL + " columns");
                    }else if(userChoice > MAX_COL){
                        System.out.println("Must be " + MAX_COL + " columns or fewer");
                    }else{
                        numColumns = userChoice;
                        valid = true;
                    }
                }while(!valid);
                valid = false;

                //decide max number of tokens in a row to win
                if(numColumns > MAX_WIN && numRows > MAX_WIN){
                    maxNumToWin = MAX_WIN;
                }else{
                    if(numColumns < numRows){
                        maxNumToWin = numColumns;
                    }else{
                        maxNumToWin = numRows;
                    }
                }

                //decide number to win
                do{
                    System.out.println("How many in a row to win?");
                    userChoice = keyboard.nextInt();
                    if(userChoice < MIN_COL){
                        System.out.println("Must be at least " + MIN_COL + " in a row");
                    }else if(userChoice > maxNumToWin){
                        System.out.println("Must be " + maxNumToWin + " in a row or fewer");
                    }else{
                        numToWin = userChoice;
                        valid = true;
                    }
                }while(!valid);
                valid = false;

                //decide which type of container to use for the gameboard, fast or memory efficient
                do{
                    System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                    strChoice = keyboard.next();
                    if(!fastMemFlag.contains(strChoice)){
                        System.out.println("Please enter F or M");
                    }else{
                        valid = true;
                    }
                }while(!valid);
                valid = false;

                if(memoryFlag.contains(strChoice)){
                    gameBoard = new GameBoardMem(numRows, numColumns, numToWin);
                }else{
                    gameBoard = new GameBoard(numRows, numColumns, numToWin);
                }

                needSetup = false;
            }

            //initial gameboard print
            System.out.println(gameBoard.toString());

            //do-while loop that validates user input for their column choice
            do{
                System.out.println("Player " + players[counter] + ", what column do you want to place your marker in?");
                userChoice = keyboard.nextInt();
                if(userChoice < 0){
                    System.out.println("Column cannot be less than 0");
                }else if(userChoice > numColumns-1){
                    System.out.println("Column cannot be greater than " + (numColumns-1));
                }else if(!gameBoard.checkIfFree(userChoice)){
                    System.out.println("Column is full");
                }else{
                    valid = true;
                }
            }while(!valid);

            //once input has been validated, drop the token
            gameBoard.dropToken(players[counter], userChoice);

            //after dropping token, check win conditions: a player has won or there is a tie
            win = gameBoard.checkForWin(userChoice);
            tie = gameBoard.checkTie();

            //if won or tie, do post game tasks
            if(win || tie){
                //print winning gameboard
                System.out.println(gameBoard.toString());

                //output message depending on win or tie
                if(win){
                    System.out.println("Player " + players[counter] + " Won!");
                }else if(tie){
                    System.out.println("The game is a tie!");
                }

                //do-while loop to validate the play again question
                do{
                    System.out.println("Would you like to play again? Y/N");
                    strChoice = keyboard.next();
                }while(!strChoice.equalsIgnoreCase("Y") && !strChoice.equalsIgnoreCase("N"));

                //handle variables and other data depending on choice to play again
                if(strChoice.equalsIgnoreCase("N")){
                    //player has chosen no, close scanner, exit without error
                    keyboard.close();
                    System.exit(0);
                }else{
                    //player has chosen yes: reset players array, and various status flags
                    for(int i = 0; i < numPlayers; i++){
                        players[i] = ' ';
                    }
                    counter = 0;
                    valid = false;
                    win = false;
                    tie = false;
                    needSetup = true;
                }
            }else{
                //if no player has won, reset input validation flag, change current player
                valid = false;
                if(counter == (numPlayers-1)){
                    counter = 0;
                }else{
                    counter++;
                }
            }
        }
    }
}