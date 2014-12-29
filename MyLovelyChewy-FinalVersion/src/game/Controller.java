package game;

import java.util.ArrayList;

import javax.xml.transform.TransformerException;

public class Controller {
	public static GameActions gameActions = new GameActions();
    public static void checkAndRemove(){
    	GameEngine.getGameBoard().setGameBoardMap(gameActions.checkForSameColor(GameEngine.getGameBoard().getBoardMap()));
    	GameEngine.setCurrentScore(GameEngine.getCurrentScore()+10);
    	checkAndFill();
    	
    	
    }
    
    public static void checkAndFill(){
    	fallDown();
    	while(hasNullElements()){
    		fillTopRow();
    		fallDown(); 		
    		//checkAndRemove();
    	}
    }
	
    /**
	 * Returns void,  Takes 2 boardCell objects and swaps their lokums regardless 
	 * of any suitable removing operation.
	
	 * @param BoardCell c1, BoardCell c2 which will be swapped.
	 * @return void
	 * @see CanSwapThem method
	 */
    public static void swapSpecialMove(BoardCell c1 ,BoardCell c2) throws TransformerException{
    	Lokum temp1=GameEngine.getGameBoard().getBoardMap()[c1.getCellX()][c1.getCellY()].getCellLokum();
    	GameEngine.getGameBoard().getBoardMap()[c1.getCellX()][c1.getCellY()].addLokum(c2.getCellLokum());
    	GameEngine.getGameBoard().getBoardMap()[c2.getCellX()][c2.getCellY()].addLokum(temp1);
    	GameEngine.getPainter().lkmscreen.importBoardMapToGUI(GameEngine.getGameBoard().getBoardMap());
    	GameEngine.setRemainingSpecialSwap(GameEngine.getRemainingSpecialSwap()-1);
    	GameEngine.getPainter().getHelperScreen().setSpecialSwapButtonPassive();
    	GameEngine.decreaseMoveLimit();
    }
    
    public static void fallDown(){
		BoardCell[][] theBoard = GameEngine.getGameBoard().getBoardMap();
		
		
		
	 	for (int i= 1; i<theBoard[0].length;i++) {
			for (int j = 0; j < theBoard.length; j++) {
				
				int x= j;
				int y= i;
				
				while(theBoard[x][y].getCellLokum()==null && theBoard[x][y-1].getCellLokum()!=null) {
					theBoard[x][y].addLokum(theBoard[x][y-1].getCellLokum());
					theBoard[x][y-1].removeLokum();
					y--;
					if(y==0) break;
				}
				
				
				
			}
		}
	}
    
    public static void fillTopRow(){
		BoardCell[][] theBoard = GameEngine.getGameBoard().getBoardMap();
		for (int j = 0; j < theBoard.length; j++) {
			if(theBoard[j][0].getCellLokum() == null){
				
				theBoard[j][0].addLokum(gameActions.getSingleRandomLokum());;
			}
		}
	}
	
	public static boolean hasNullElements(){
		BoardCell[][] theBoard = GameEngine.getGameBoard().getBoardMap();
		for (int i = 0; i < theBoard.length; i++) {
			for (int j = 0; j < theBoard[i].length; j++) {
				if(theBoard[i][j].getCellLokum()==null){
					return true;
				}
				
				
			}
		}
		
		
		return false;
	}
	public static void checkBoardAndInitialize() {
		// GameEngine.getGameBoard().getBoardMap();
		for (int i = 0; i < GameEngine.getGameBoard().getBoardMap().length; i++) {
			for (int j = 0; j < GameEngine.getGameBoard().getBoardMap()[i].length; j++) {
				Lokum randomLokum = gameActions.getSingleRandomLokum();
				if (GameEngine.getGameBoard().getBoardMap()[i][j]
						.canTakeLokum()) {

					if (i > 1 && j > 1) {
						if ((GameEngine.getGameBoard().getBoardMap()[i][j - 1]
								.getCellLokum().color.equals(GameEngine
								.getGameBoard().getBoardMap()[i][j - 2]
								.getCellLokum().color))
								&& (GameEngine.getGameBoard().getBoardMap()[i - 1][j]
										.getCellLokum().color.equals(GameEngine
										.getGameBoard().getBoardMap()[i - 2][j]
										.getCellLokum().color))) {
							String color1 = GameEngine.getGameBoard()
									.getBoardMap()[i][j - 1].getCellLokum().color;
							String color2 = GameEngine.getGameBoard()
									.getBoardMap()[i - 1][j].getCellLokum().color;
							// randomLokum=gameActions.getSingleRandomLokum();
							while ((randomLokum.color.equals(color1))
									|| (randomLokum.color.equals(color2))) {
								randomLokum = gameActions
										.getSingleRandomLokum();
							}
							GameEngine.getGameBoard().getBoardMap()[i][j]
									.addLokum(randomLokum);
						} else if (GameEngine.getGameBoard().getBoardMap()[i][j - 1]
								.getCellLokum().color.equals(GameEngine
								.getGameBoard().getBoardMap()[i][j - 2]
								.getCellLokum().color)) {

							while (GameEngine.getGameBoard().getBoardMap()[i][j - 1]
									.getCellLokum().color
									.equals(randomLokum.color)) {

								randomLokum = gameActions
										.getSingleRandomLokum();

							}
							GameEngine.getGameBoard().getBoardMap()[i][j]
									.addLokum(randomLokum);
						} else if (GameEngine.getGameBoard().getBoardMap()[i - 1][j]
								.getCellLokum().color.equals(GameEngine
								.getGameBoard().getBoardMap()[i - 2][j]
								.getCellLokum().color)) {
							while (GameEngine.getGameBoard().getBoardMap()[i - 1][j]
									.getCellLokum().color
									.equals(randomLokum.color)) {
								randomLokum = gameActions
										.getSingleRandomLokum();
							}
							GameEngine.getGameBoard().getBoardMap()[i][j]
									.addLokum(randomLokum);
						}

					}

					else if (j > 1) {
						if (GameEngine.getGameBoard().getBoardMap()[i][j - 1]
								.getCellLokum().color.equals(GameEngine
								.getGameBoard().getBoardMap()[i][j - 2]
								.getCellLokum().color)) {
							while (GameEngine.getGameBoard().getBoardMap()[i][j - 1]
									.getCellLokum().color
									.equals(randomLokum.color)) {
								
								randomLokum = gameActions
										.getSingleRandomLokum();
							}
						}
						GameEngine.getGameBoard().getBoardMap()[i][j]
								.addLokum(randomLokum);
					} else if (i > 1) {
						if (GameEngine.getGameBoard().getBoardMap()[i - 1][j]
								.getCellLokum().color.equals(GameEngine
								.getGameBoard().getBoardMap()[i - 2][j]
								.getCellLokum().color)) {
							while (GameEngine.getGameBoard().getBoardMap()[i - 1][j]
									.getCellLokum().color
									.equals(randomLokum.color)) {
								randomLokum = gameActions
										.getSingleRandomLokum();
							}
						}
						GameEngine.getGameBoard().getBoardMap()[i][j]
								.addLokum(randomLokum);
					}

					GameEngine.getGameBoard().getBoardMap()[i][j]
							.addLokum(randomLokum);

				}
			}
		}

		GameEngine.getPainter().getLokumsScreen()
				.importBoardMapToGUI(GameEngine.getGameBoard().getBoardMap());
	}

	public boolean CanSwapThem(BoardCell bc1, BoardCell bc2) {
		BoardCell left = null;
		BoardCell right = null;
		BoardCell up = null;
		BoardCell down = null;

		boolean canSwap = false;

		String tempColor = "";
		String swapType = "";

		int boardLimitX = GameEngine.getGameBoard().getBoardDimX();
		int boardLimitY = GameEngine.getGameBoard().getBoardDimy();

		if (bc1.getCellX() == bc2.getCellX()) {
		if (bc1.getCellY() > bc2.getCellY()) {
		up = bc2;
		down = bc1;
		} else {
		up = bc1;
		down = bc2;
		}
		swapType = "vertical";
		}

		else if (bc1.getCellY() == bc2.getCellY()) {
		if (bc1.getCellX() > bc2.getCellX()) {
		right = bc1;
		left = bc2;
		} else {
		right = bc2;
		left = bc1;
		}
		swapType = "horizontal";
		} else {
		swapType = "cross";
		}

		int bc1x = bc1.getCellX();
		int bc1y = bc1.getCellY();

		int bc2x = bc2.getCellX();
		int bc2y = bc2.getCellY();

		if (bc1.canTakeLokum() && bc2.canTakeLokum()) {
		
		if(bc1.getCellLokum().type.equals("ColorBomb") || bc2.getCellLokum().type.equals("ColorBomb")) {
		canSwap= true;
		}
		}	

		if (swapType.equals("horizontal")) {

		// bc1 is the left one, bc2 is
		// the
		// right one.

		// /Bc1'S CHECKS

		// ust check
		if ((right.getCellY() - 2) >= 0
		&& GameEngine.getGameBoard().getBoardMap()[right
		.getCellX()][right.getCellY() - 1] != null
		&& GameEngine.getGameBoard().getBoardMap()[right
		.getCellX()][right.getCellY() - 1]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[right
		.getCellX()][right.getCellY() - 2] != null
		&& (GameEngine.getGameBoard().getBoardMap()[right
		.getCellX()][right.getCellY() - 2]
		.canTakeLokum())
		&& GameEngine.getGameBoard().getBoardMap()[right
		.getCellX()][right.getCellY() - 1]
		.getCellLokum().color
		.equals(GameEngine.getGameBoard().getBoardMap()[right
		.getCellX()][right.getCellY() - 2]
		.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[right
		.getCellX()][right.getCellY() - 1].getCellLokum().color;

		if (left.getCellLokum().color.equals(tempColor)) {
		canSwap = true;
		}
		}

		// alt check
		if ((right.getCellY() + 2 < boardLimitY)
		&& GameEngine.getGameBoard().getBoardMap()[right
		.getCellX()][right.getCellY() + 1] != null
		&& GameEngine.getGameBoard().getBoardMap()[right
		.getCellX()][right.getCellY() + 1]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[right
		.getCellX()][right.getCellY() + 2] != null
		&& GameEngine.getGameBoard().getBoardMap()[right
		.getCellX()][right.getCellY() + 2]
		.canTakeLokum()

		&& GameEngine.getGameBoard().getBoardMap()[right
		.getCellX()][right.getCellY() + 1]
		.getCellLokum().color
		.equals(GameEngine.getGameBoard().getBoardMap()[right
		.getCellX()][right.getCellY() + 2]
		.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[right
		.getCellX()][right.getCellY() + 1].getCellLokum().color;

		if (left.getCellLokum().color.equals(tempColor)) {
		canSwap = true;
		}
		}
		// sag check
		if ((right.getCellX() + 2 < boardLimitX)
		&& GameEngine.getGameBoard().getBoardMap()[right
		.getCellX() + 1][right.getCellY()] != null
		&& GameEngine.getGameBoard().getBoardMap()[right
		.getCellX() + 1][right.getCellY()]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[right
		.getCellX() + 2][right.getCellY()] != null
		&& GameEngine.getGameBoard().getBoardMap()[right
		.getCellX() + 2][right.getCellY()]
		.canTakeLokum()

		&& GameEngine.getGameBoard().getBoardMap()[right
		.getCellX() + 1][right.getCellY()]
		.getCellLokum().color
		.equals(GameEngine.getGameBoard().getBoardMap()[right
		.getCellX() + 2][right.getCellY()]
		.getCellLokum().color)) {
		
		tempColor = GameEngine.getGameBoard().getBoardMap()[right
		.getCellX() + 1][right.getCellY()].getCellLokum().color;

		if (left.getCellLokum().color.equals(tempColor)) {
		canSwap = true;
		}
		}
		
		// dikeyde ortaya gelme check 
		
		if(right.getCellY()-1 >=0 && right.getCellY()+1<boardLimitY 
		&& GameEngine.getGameBoard().getBoardMap()[right
		           	.getCellX()][right.getCellY()-1] != null
		           	&& GameEngine.getGameBoard().getBoardMap()[right
		           	.getCellX()][right.getCellY()-1]
		           	.canTakeLokum()
		           	&& GameEngine.getGameBoard().getBoardMap()[right
		           	.getCellX()][right.getCellY()+1] != null
		           	&& GameEngine.getGameBoard().getBoardMap()[right
		           	.getCellX()][right.getCellY()+1]
		           	.canTakeLokum()

		
		&&GameEngine.getGameBoard().getBoardMap()[right
		           	.getCellX()][right.getCellY()-1]
		           	.getCellLokum().color
		           	.equals(GameEngine.getGameBoard().getBoardMap()[right
		           	.getCellX()][right.getCellY()+1]
		           	.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[right
		                        	.getCellX()][right.getCellY()-1].getCellLokum().color;
		if(left.getCellLokum().color.equals(tempColor)) {
		canSwap=true;
		}
		
		}
		

		// /Bc2'S CHECKS

		// sol check
		if ((left.getCellX() - 2) >= 0
		&& GameEngine.getGameBoard().getBoardMap()[left
		.getCellX() - 1][left.getCellY()] != null
		&& GameEngine.getGameBoard().getBoardMap()[left
		.getCellX() - 1][left.getCellY()]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[left
		.getCellX() - 2][left.getCellY()] != null
		&& GameEngine.getGameBoard().getBoardMap()[left
		.getCellX() - 2][left.getCellY()]
		.canTakeLokum()

		&& GameEngine.getGameBoard().getBoardMap()[left
		.getCellX() - 1][left.getCellY()]
		.getCellLokum().color
		.equals(GameEngine.getGameBoard().getBoardMap()[left
		.getCellX() - 2][left.getCellY()]
		.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[left
		.getCellX() - 1][left.getCellY()].getCellLokum().color;

		if (right.getCellLokum().color.equals(tempColor)) {
		canSwap = true;
		}
		}

		// ust check
		if (left.getCellY() - 2 >= 0
		&& GameEngine.getGameBoard().getBoardMap()[left
		.getCellX()][left.getCellY() - 1] != null
		&& GameEngine.getGameBoard().getBoardMap()[left
		.getCellX()][left.getCellY() - 1]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[left
		.getCellX()][left.getCellY() - 2] != null
		&& GameEngine.getGameBoard().getBoardMap()[left
		.getCellX()][left.getCellY() - 2]
		.canTakeLokum()

		&& GameEngine.getGameBoard().getBoardMap()[left
		.getCellX()][left.getCellY() - 1]
		.getCellLokum().color
		.equals(GameEngine.getGameBoard().getBoardMap()[left
		.getCellX()][left.getCellY() - 2]
		.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[left
		.getCellX()][left.getCellY() - 1].getCellLokum().color;

		if (right.getCellLokum().color.equals(tempColor)) {
		canSwap = true;
		}
		}

		// alt check
		if (left.getCellY() + 2 < boardLimitY
		&& GameEngine.getGameBoard().getBoardMap()[left
		.getCellX()][left.getCellY() + 1] != null
		&& GameEngine.getGameBoard().getBoardMap()[left
		.getCellX()][left.getCellY() + 1]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[left
		.getCellX()][left.getCellY() + 2] != null
		&& GameEngine.getGameBoard().getBoardMap()[left
		.getCellX()][left.getCellY() + 2]
		.canTakeLokum()

		&& GameEngine.getGameBoard().getBoardMap()[left
		.getCellX()][left.getCellY() + 1]
		.getCellLokum().color
		.equals(GameEngine.getGameBoard().getBoardMap()[left
		.getCellX()][left.getCellY() + 2]
		.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[left
		.getCellX()][left.getCellY() + 1].getCellLokum().color;

		if (right.getCellLokum().color.equals(tempColor)) {
		canSwap = true;
		}
		}
		// dikeyde ortaya gelme check 
		
		if(left.getCellY()-1 >= 0 && left.getCellY()+1<boardLimitY 
		&& GameEngine.getGameBoard().getBoardMap()[left
		           	.getCellX()][left.getCellY()-1] != null
		           	&& GameEngine.getGameBoard().getBoardMap()[left
		           	.getCellX()][left.getCellY()-1]
		           	.canTakeLokum()
		           	&& GameEngine.getGameBoard().getBoardMap()[left
		           	.getCellX()][left.getCellY()+1] != null
		           	&& GameEngine.getGameBoard().getBoardMap()[left
		           	.getCellX()][left.getCellY()+1]
		           	.canTakeLokum()

		
		&&GameEngine.getGameBoard().getBoardMap()[left
		           	.getCellX()][left.getCellY()-1]
		           	.getCellLokum().color
		           	.equals(GameEngine.getGameBoard().getBoardMap()[left
		           	.getCellX()][left.getCellY()+1]
		           	.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[left
		                        	.getCellX()][left.getCellY()-1].getCellLokum().color;
		if(right.getCellLokum().color.equals(tempColor)) {
		canSwap=true;
		}
		
		}
		
		}

		else if (swapType.equals("vertical")) {// bc1 is the upper one, bc2
		// is
		// the bottom one.

		// /Bc1'S CHECKS

		// sol check
		if (down.getCellX() - 2 >= 0
		&& GameEngine.getGameBoard().getBoardMap()[down
		.getCellX() - 1][down.getCellY()] != null
		&& GameEngine.getGameBoard().getBoardMap()[down
		.getCellX() - 1][down.getCellY()]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[down
		.getCellX() - 2][down.getCellY()] != null
		&& GameEngine.getGameBoard().getBoardMap()[down
		.getCellX() - 2][down.getCellY()]
		.canTakeLokum()

		&& GameEngine.getGameBoard().getBoardMap()[down
		.getCellX() - 1][down.getCellY()]
		.getCellLokum().color
		.equals(GameEngine.getGameBoard().getBoardMap()[down
		.getCellX() - 2][down.getCellY()]
		.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[down
		.getCellX() - 1][down.getCellY()].getCellLokum().color;

		if (up.getCellLokum().color.equals(tempColor)) {
		canSwap = true;
		}
		}

		// alt check
		if (down.getCellY() + 2 < boardLimitY
		&& GameEngine.getGameBoard().getBoardMap()[down
		.getCellX()][down.getCellY() + 1] != null
		&& GameEngine.getGameBoard().getBoardMap()[down
		.getCellX()][down.getCellY() + 1]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[down
		.getCellX()][down.getCellY() + 2] != null
		&& GameEngine.getGameBoard().getBoardMap()[down
		.getCellX()][down.getCellY() + 2]
		.canTakeLokum()

		&& GameEngine.getGameBoard().getBoardMap()[down
		.getCellX()][down.getCellY() + 1]
		.getCellLokum().color
		.equals(GameEngine.getGameBoard().getBoardMap()[down
		.getCellX()][down.getCellY() + 2]
		.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[down
		.getCellX()][down.getCellY() + 1].getCellLokum().color;

		if (up.getCellLokum().color.equals(tempColor)) {
		canSwap = true;
		}
		}
		// sag check
		if (down.getCellX() + 2 < boardLimitX
		&& GameEngine.getGameBoard().getBoardMap()[down
		.getCellX() + 1][down.getCellY()] != null
		&& GameEngine.getGameBoard().getBoardMap()[down
		.getCellX() + 1][down.getCellY()]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[down
		.getCellX() + 2][down.getCellY()] != null
		&& GameEngine.getGameBoard().getBoardMap()[down
		.getCellX() + 2][down.getCellY()]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[down
		.getCellX() + 1][down.getCellY()]
		.getCellLokum().color
		.equals(GameEngine.getGameBoard().getBoardMap()[down
		.getCellX() + 2][down.getCellY()]
		.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[down
		.getCellX() + 1][down.getCellY()].getCellLokum().color;

		if (up.getCellLokum().color.equals(tempColor)) {
		canSwap = true;
		}
		}

		// dikeyde ortaya gelme check 
		
		if(down.getCellX()-1 >= 0&& down.getCellX()+1<boardLimitX 
		&& GameEngine.getGameBoard().getBoardMap()[down
		           	.getCellX()-1][down.getCellY()] != null
		           	&& GameEngine.getGameBoard().getBoardMap()[down
		           	.getCellX()-1][down.getCellY()]
		           	.canTakeLokum()
		           	&& GameEngine.getGameBoard().getBoardMap()[down
		           	.getCellX()+1][down.getCellY()] != null
		           	&& GameEngine.getGameBoard().getBoardMap()[down
		           	.getCellX()+1][down.getCellY()]
		           	.canTakeLokum()

		
		&&GameEngine.getGameBoard().getBoardMap()[down
		           	.getCellX()-1][down.getCellY()]
		           	.getCellLokum().color
		           	.equals(GameEngine.getGameBoard().getBoardMap()[down
		           	.getCellX()+1][down.getCellY()]
		           	.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[down
		                        	.getCellX()+1][down.getCellY()].getCellLokum().color;
		if(up.getCellLokum().color.equals(tempColor)) {
		canSwap=true;
		}
		
		}

		
		// /Bc2'S CHECKS

		// ust check
		if (up.getCellY() - 2 >= 0
		&& GameEngine.getGameBoard().getBoardMap()[up
		.getCellX()][up.getCellY() - 1] != null
		&& GameEngine.getGameBoard().getBoardMap()[up
		.getCellX()][up.getCellY() - 1].canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[up
		.getCellX()][up.getCellY() - 2] != null
		&& GameEngine.getGameBoard().getBoardMap()[up
		.getCellX()][up.getCellY() - 2].canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[up
		.getCellX()][up.getCellY() - 1].getCellLokum().color
		.equals(GameEngine.getGameBoard().getBoardMap()[up
		.getCellX()][up.getCellY() - 2]
		.getCellLokum().color)) {

		tempColor = GameEngine.getGameBoard().getBoardMap()[up
		.getCellX()][up.getCellY() - 1].getCellLokum().color;

		if (down.getCellLokum().color.equals(tempColor)) {
		canSwap = true;
		}
		}
		// sol check
		if (up.getCellX() - 2 >= 0
		&& GameEngine.getGameBoard().getBoardMap()[up
		.getCellX() - 1][up.getCellY()] != null
		&& GameEngine.getGameBoard().getBoardMap()[up
		.getCellX() - 1][up.getCellY()].canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[up
		.getCellX() - 2][up.getCellY()] != null
		&& GameEngine.getGameBoard().getBoardMap()[up
		.getCellX() - 2][up.getCellY()].canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[up
		.getCellX() - 1][up.getCellY()].getCellLokum().color
		.equals(GameEngine.getGameBoard().getBoardMap()[up
		.getCellX() - 2][up.getCellY()]
		.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[up
		.getCellX() - 1][up.getCellY()].getCellLokum().color;

		if (down.getCellLokum().color.equals(tempColor)) {
		canSwap = true;
		}
		}

		// sag check
		if (up.getCellX() + 2 < boardLimitX
		&& GameEngine.getGameBoard().getBoardMap()[up
		.getCellX() + 1][up.getCellY()] != null
		&& GameEngine.getGameBoard().getBoardMap()[up
		.getCellX() + 1][up.getCellY()].canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[up
		.getCellX() + 2][up.getCellY()] != null
		&& GameEngine.getGameBoard().getBoardMap()[up
		.getCellX() + 2][up.getCellY()].canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[up
		.getCellX() + 1][up.getCellY()].getCellLokum().color
		.equals(GameEngine.getGameBoard().getBoardMap()[up
		.getCellX() + 2][up.getCellY()]
		.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[up
		.getCellX() + 1][up.getCellY()].getCellLokum().color;

		if (down.getCellLokum().color.equals(tempColor)) {
		canSwap = true;
		}
		}
		// dikeyde ortaya gelme check 
		
		if(up.getCellX()-1 >= 0 && up.getCellX()+1<boardLimitX 
		&& GameEngine.getGameBoard().getBoardMap()[up
		           	.getCellX()-1][up.getCellY()] != null
		           	&& GameEngine.getGameBoard().getBoardMap()[up
		           	.getCellX()-1][up.getCellY()]
		           	.canTakeLokum()
		           	&& GameEngine.getGameBoard().getBoardMap()[up
		           	.getCellX()+1][up.getCellY()] != null
		           	&& GameEngine.getGameBoard().getBoardMap()[up
		           	.getCellX()+1][up.getCellY()]
		           	.canTakeLokum()

		
		&&GameEngine.getGameBoard().getBoardMap()[up
		           	.getCellX()-1][up.getCellY()]
		           	.getCellLokum().color
		           	.equals(GameEngine.getGameBoard().getBoardMap()[up
		           	.getCellX()+1][up.getCellY()]
		           	.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[up
		                        	.getCellX()+1][up.getCellY()].getCellLokum().color;
		if(down.getCellLokum().color.equals(tempColor)) {
		canSwap=true;
		}
		
		}

		}

		else if (swapType.equals("cross")) {
		// /Bc1'S CHECKS

		// sol check
		if (bc2x - 2 >= 0
		&& GameEngine.getGameBoard().getBoardMap()[bc2x - 1][bc2y] != null
		&& GameEngine.getGameBoard().getBoardMap()[bc2x - 1][bc2y]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[bc2x - 2][bc2y] != null
		&& GameEngine.getGameBoard().getBoardMap()[bc2x - 2][bc2y]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[bc2x - 1][bc2y]
		.getCellLokum().color.equals(GameEngine
		.getGameBoard().getBoardMap()[bc2x - 2][bc2y]
		.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[bc2x - 1][bc2y]
		.getCellLokum().color;

		if (bc1.getCellLokum().color.equals(tempColor)) {
		canSwap = true;
		}
		}

		// alt check
		if (bc2y + 2 < boardLimitY
		&& GameEngine.getGameBoard().getBoardMap()[bc2x][bc2y + 1] != null
		&& GameEngine.getGameBoard().getBoardMap()[bc2x][bc2y + 1]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[bc2x][bc2y + 2] != null
		&& GameEngine.getGameBoard().getBoardMap()[bc2x][bc2y + 2]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[bc2x][bc2y + 1]
		.getCellLokum().color.equals(GameEngine
		.getGameBoard().getBoardMap()[bc2x][bc2y + 2]
		.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[bc2x][bc2y + 1]
		.getCellLokum().color;

		if (bc1.getCellLokum().color.equals(tempColor)) {
		canSwap = true;
		}
		}
		// sag check
		if (bc2x + 2 < boardLimitX
		&& GameEngine.getGameBoard().getBoardMap()[bc2x + 1][bc2y] != null
		&& GameEngine.getGameBoard().getBoardMap()[bc2x + 1][bc2y]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[bc2x + 2][bc2y] != null
		&& GameEngine.getGameBoard().getBoardMap()[bc2x + 2][bc2y]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[bc2x + 1][bc2y]
		.getCellLokum().color.equals(GameEngine
		.getGameBoard().getBoardMap()[bc2x + 2][bc2y]
		.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[bc2x + 1][bc2y]
		.getCellLokum().color;

		if (bc1.getCellLokum().color.equals(tempColor)) {
		canSwap = true;
		}
		}
		// ust check
		if (bc2y - 2 >= 0
		&& GameEngine.getGameBoard().getBoardMap()[bc2x][bc2y - 1] != null
		&& GameEngine.getGameBoard().getBoardMap()[bc2x][bc2y - 1]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[bc2x][bc2y - 2] != null
		&& GameEngine.getGameBoard().getBoardMap()[bc2x][bc2y - 2]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[bc2x][bc2y - 1]
		.getCellLokum().color.equals(GameEngine
		.getGameBoard().getBoardMap()[bc2x][bc2y - 2]
		.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[bc2x][bc2y - 1]
		.getCellLokum().color;

		if (bc1.getCellLokum().color.equals(tempColor)) {
		canSwap = true;
		}
		}

	// yatayda ortaya gelme check 
		
		if(bc2.getCellX()-1 >= 0&& bc2.getCellX()+1<boardLimitX 
		&& GameEngine.getGameBoard().getBoardMap()[bc2
		           	.getCellX()-1][bc2.getCellY()] != null
		           	&& GameEngine.getGameBoard().getBoardMap()[bc2
		           	.getCellX()-1][bc2.getCellY()]
		           	.canTakeLokum()
		           	&& GameEngine.getGameBoard().getBoardMap()[bc2
		           	.getCellX()+1][bc2.getCellY()] != null
		           	&& GameEngine.getGameBoard().getBoardMap()[bc2
		           	.getCellX()+1][bc2.getCellY()]
		           	.canTakeLokum()

		
		&&GameEngine.getGameBoard().getBoardMap()[bc2
		           	.getCellX()-1][bc2.getCellY()]
		           	.getCellLokum().color
		           	.equals(GameEngine.getGameBoard().getBoardMap()[bc2
		           	.getCellX()+1][bc2.getCellY()]
		           	.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[bc2
		                        	.getCellX()+1][bc2.getCellY()].getCellLokum().color;
		if(bc1.getCellLokum().color.equals(tempColor)) {
		canSwap=true;
		}
		
		}
		

		// yatayda ortaya gelme check 
		
		if(bc2.getCellY()-1 >= 0&& bc2.getCellY()+1<boardLimitY 
		&& GameEngine.getGameBoard().getBoardMap()[bc2
		           	.getCellX()][bc2.getCellY()-1] != null
		           	&& GameEngine.getGameBoard().getBoardMap()[bc2
		           	.getCellX()][bc2.getCellY()-1]
		           	.canTakeLokum()
		           	&& GameEngine.getGameBoard().getBoardMap()[bc2
		           	.getCellX()][bc2.getCellY()+1] != null
		           	&& GameEngine.getGameBoard().getBoardMap()[bc2
		           	.getCellX()][bc2.getCellY()+1]
		           	.canTakeLokum()

		
		&&GameEngine.getGameBoard().getBoardMap()[bc2
		           	.getCellX()][bc2.getCellY()-1]
		           	.getCellLokum().color
		           	.equals(GameEngine.getGameBoard().getBoardMap()[bc2
		           	.getCellX()][bc2.getCellY()+1]
		           	.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[bc2
		                        	.getCellX()][bc2.getCellY()-1].getCellLokum().color;
		if(bc1.getCellLokum().color.equals(tempColor)) {
		canSwap=true;
		}
		
		}
		
		
		
		
		// /Bc2'S CHECKS

		// ust check
		if (bc1y - 2 >= 0
		&& GameEngine.getGameBoard().getBoardMap()[bc1x][bc1y - 1] != null
		&& GameEngine.getGameBoard().getBoardMap()[bc1x][bc1y - 1]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[bc1x][bc1y - 2] != null
		&& GameEngine.getGameBoard().getBoardMap()[bc1x][bc1y - 2]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[bc1x][bc1y - 1]
		.getCellLokum().color.equals(GameEngine
		.getGameBoard().getBoardMap()[bc1x][bc1y - 2]
		.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[bc1x][bc1y - 1]
		.getCellLokum().color;

		if (bc2.getCellLokum().color.equals(tempColor)) {
		canSwap = true;
		}
		}
		// sol check
		if (bc1x - 2 >= 0
		&& GameEngine.getGameBoard().getBoardMap()[bc1x - 1][bc1y] != null
		&& GameEngine.getGameBoard().getBoardMap()[bc1x - 1][bc1y]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[bc1x - 2][bc1y] != null
		&& GameEngine.getGameBoard().getBoardMap()[bc1x - 2][bc1y]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[bc1x - 1][bc1y]
		.getCellLokum().color.equals(GameEngine
		.getGameBoard().getBoardMap()[bc1x - 2][bc1y]
		.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[bc1x - 1][bc1y]
		.getCellLokum().color;

		if (bc2.getCellLokum().color.equals(tempColor)) {
		canSwap = true;
		}
		} 

		// sag check
		if (bc1x + 2 < boardLimitX
		&& GameEngine.getGameBoard().getBoardMap()[bc1x + 1][bc1y] != null
		&& GameEngine.getGameBoard().getBoardMap()[bc1x + 1][bc1y]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[bc1x + 2][bc1y] != null
		&& GameEngine.getGameBoard().getBoardMap()[bc1x + 2][bc1y]
		.canTakeLokum()

		&& GameEngine.getGameBoard().getBoardMap()[bc1x + 1][bc1y]
		.getCellLokum().color.equals(GameEngine
		.getGameBoard().getBoardMap()[bc1x + 2][bc1y]
		.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[bc1x + 1][bc1y]
		.getCellLokum().color;

		if (bc2.getCellLokum().color.equals(tempColor)) {
		canSwap = true;
		}
		}
		// alt check
		if (bc1y + 2 < boardLimitY
		&& GameEngine.getGameBoard().getBoardMap()[bc1x][bc1y + 1] != null
		&& GameEngine.getGameBoard().getBoardMap()[bc1x][bc1y + 1]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[bc1x][bc1y + 2] != null
		&& GameEngine.getGameBoard().getBoardMap()[bc1x][bc1y + 2]
		.canTakeLokum()
		&& GameEngine.getGameBoard().getBoardMap()[bc1x][bc1y + 1]
		.getCellLokum().color.equals(GameEngine
		.getGameBoard().getBoardMap()[bc1x][bc1y + 2]
		.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[bc1x][bc1y + 1]
		.getCellLokum().color;

		if (bc2.getCellLokum().color.equals(tempColor)) {
		canSwap = true;
		}
		}

	// dikeyde ortaya gelme check 
		
		if(bc1.getCellX()-1 >= 0&& bc1.getCellX()+1<boardLimitX 
		&& GameEngine.getGameBoard().getBoardMap()[bc1
		           	.getCellX()-1][bc1.getCellY()] != null
		           	&& GameEngine.getGameBoard().getBoardMap()[bc1
		           	.getCellX()-1][bc1.getCellY()]
		           	.canTakeLokum()
		           	&& GameEngine.getGameBoard().getBoardMap()[bc1
		           	.getCellX()+1][bc1.getCellY()] != null
		           	&& GameEngine.getGameBoard().getBoardMap()[bc1
		           	.getCellX()+1][bc1.getCellY()]
		           	.canTakeLokum()

		
		&&GameEngine.getGameBoard().getBoardMap()[bc1
		           	.getCellX()-1][bc1.getCellY()]
		           	.getCellLokum().color
		           	.equals(GameEngine.getGameBoard().getBoardMap()[bc1
		           	.getCellX()+1][bc1.getCellY()]
		           	.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[bc1
		                        	.getCellX()+1][bc1.getCellY()].getCellLokum().color;
		if(bc2.getCellLokum().color.equals(tempColor)) {
		canSwap=true;
		}
		
		}
		
		// yatayda ortaya gelme check 
		
		if(bc1.getCellY()-1 >= 0&& bc1.getCellY()+1<boardLimitY 
		&& GameEngine.getGameBoard().getBoardMap()[bc1
		           	.getCellX()][bc1.getCellY()-1] != null
		           	&& GameEngine.getGameBoard().getBoardMap()[bc1
		           	.getCellX()][bc1.getCellY()-1]
		           	.canTakeLokum()
		           	&& GameEngine.getGameBoard().getBoardMap()[bc1
		           	.getCellX()][bc1.getCellY()+1] != null
		           	&& GameEngine.getGameBoard().getBoardMap()[bc1
		           	.getCellX()][bc1.getCellY()+1]
		           	.canTakeLokum()

		
		&&GameEngine.getGameBoard().getBoardMap()[bc1
		           	.getCellX()][bc1.getCellY()-1]
		           	.getCellLokum().color
		           	.equals(GameEngine.getGameBoard().getBoardMap()[bc1
		           	.getCellX()][bc1.getCellY()+1]
		           	.getCellLokum().color)) {
		tempColor = GameEngine.getGameBoard().getBoardMap()[bc1
		                        	.getCellX()][bc1.getCellY()-1].getCellLokum().color;
		if(bc2.getCellLokum().color.equals(tempColor)) {
		canSwap=true;
		}
		
		}

		
		}
		
		
		if(bc1.getCellLokum().type.equals("Striped") || bc1.getCellLokum().type.equals("Wrapped")) {
		if(bc2.getCellLokum().type.equals("Striped") ||bc2.getCellLokum().type.equals("Wrapped")) {
		canSwap=true;
		}
		}
		

		
		return canSwap;
		}




}
