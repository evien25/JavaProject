default: cpsc2150/extendedConnects/GameScreen.java cpsc2150/extendedConnectX/models/IGameBoard.java cpsc2150/extendedConnectX/models/AbsGameBoard.java cpsc2150/extendedConnectX/models/BoardPosition.java cpsc2150/extendedConnectX/models/GameBoard.java cpsc2150/extendedConnectX/models/GameBoardMem.java
	javac cpsc2150/extendedConnects/GameScreen.java cpsc2150/extendedConnectX/models/IGameBoard.java cpsc2150/extendedConnectX/models/AbsGameBoard.java cpsc2150/extendedConnectX/models/BoardPosition.java cpsc2150/extendedConnectX/models/GameBoard.java cpsc2150/extendedConnectX/models/GameBoardMem.java
	
run: cpsc2150/extendedConnects/GameScreen.class cpsc2150/extendedConnectX/models/IGameBoard.class cpsc2150/extendedConnectX/models/AbsGameBoard.class cpsc2150/extendedConnectX/models/BoardPosition.class cpsc2150/extendedConnectX/models/GameBoard.class cpsc2150/extendedConnectX/models/GameBoardMem.class
	java cpsc2150.extendedConnects.GameScreen

testGameBoard: cpsc2150/extendedConnectX/Test/TestGameBoard.java cpsc2150/extendedConnectX/models/IGameBoard.class cpsc2150/extendedConnectX/models/AbsGameBoard.class cpsc2150/extendedConnectX/models/BoardPosition.class cpsc2150/extendedConnectX/models/GameBoard.class cpsc2150/extendedConnectX/models/GameBoardMem.class
	javac -cp .:/usr/share/java/junit4.jar cpsc2150/extendedConnectX/Test/TestGameBoard.java
	
runtestGameBoard: cpsc2150/extendedConnectX/Test/TestGameBoard.java cpsc2150/extendedConnectX/models/IGameBoard.class cpsc2150/extendedConnectX/models/AbsGameBoard.class cpsc2150/extendedConnectX/models/BoardPosition.class cpsc2150/extendedConnectX/models/GameBoard.class cpsc2150/extendedConnectX/models/GameBoardMem.class
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedConnectX.Test.TestGameBoard

testGameBoardMem: cpsc2150/extendedConnectX/Test/TestGameBoardMem.java cpsc2150/extendedConnectX/models/IGameBoard.class cpsc2150/extendedConnectX/models/AbsGameBoard.class cpsc2150/extendedConnectX/models/BoardPosition.class cpsc2150/extendedConnectX/models/GameBoard.class cpsc2150/extendedConnectX/models/GameBoardMem.class
	javac -cp .:/usr/share/java/junit4.jar cpsc2150/extendedConnectX/Test/TestGameBoardMem.java

runtestGameBoardMem: cpsc2150/extendedConnectX/Test/TestGameBoardMem.java cpsc2150/extendedConnectX/models/IGameBoard.class cpsc2150/extendedConnectX/models/AbsGameBoard.class cpsc2150/extendedConnectX/models/BoardPosition.class cpsc2150/extendedConnectX/models/GameBoard.class cpsc2150/extendedConnectX/models/GameBoardMem.class
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedConnectX.Test.TestGameBoardMem

clean: 
	rm -f cpsc2150/extendedConnectX/models/*.class
	rm -f cpsc2150/extendedConnects/*.class