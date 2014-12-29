package game;

import java.util.ArrayList;

public class Controller {
public static GameActions gameActions=new GameActions();

public void checkBoardandInitialize(){
	boolean fillStatus=true;
	int neededLokumNumber=0;
	if (GameEngine.getGameBoard()==null) {
		fillStatus=false;
	}else {
		
		System.out.println(GameEngine.getGameBoard().toString());
		for (int i = 0; i < GameEngine.getGameBoard().boardMap.length; i++) {
			for (int j = 0; j < GameEngine.getGameBoard().boardMap[i].length; j++) {
				BoardCell temp=GameEngine.getGameBoard().boardMap[i][j];
				
				
				if (temp.canTakeLokum() && !temp.hasLokum()) {
					fillStatus=false;
				}else if(temp.canTakeLokum() && temp.getCellLokum()==null){
					neededLokumNumber+=1;
				}
			}
		}
		
		if (fillStatus) {
			ArrayList<Lokum> randomLokums=new ArrayList<Lokum>();
			
			randomLokums=gameActions.fillBoardRandomly(neededLokumNumber);
			
			for (Lokum lokum : randomLokums) {
				for (int i = 0; i < GameEngine.getGameBoard().getBoardDimX(); i++) {
					for (int j = 0; j < GameEngine.getGameBoard().getBoardDimy(); j++) {
						BoardCell temp2=GameEngine.getGameBoard().boardMap[i][j];
						if (temp2.canTakeLokum() && !temp2.hasLokum()) {
							temp2.addLokum(lokum);
						}
					}
				}
			}
			
			GameEngine.getPainter().repaint();
		}
		
		
		
	}
}
}
