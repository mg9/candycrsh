import java.util.ArrayList;

import user.userActions;
import game.BoardCell;
import game.GameEngine;
import game.Lokum;

public class UnitTester {

	public static void main(String[] args) {
		GameEngine sampleEngine = GameEngine.getInstance();
		sampleEngine.getPainter().setGameBoardVisible(true);
		sampleEngine.getPainter().setGameScreenVisible(true);
		;

		userActions.loadLevel(4);
		for (int i = 0; i < GameEngine.getGameBoard().getBoardMap().length; i++) {
			for (int j = 0; j < GameEngine.getGameBoard().getBoardMap().length; j++) {
				//System.out.println(GameEngine.getGameBoard().getBoardMap()[i][j].toString());
				System.out.println("at "+ i+","+j+" coordinates "+ GameEngine.getGameBoard().getBoardMap()[i][j].getCellLokum().TimeBasedLokumtoString());
			}

		}
		GameEngine.setTimeBasedGame(300);
		GameEngine.increaseTime(15);
		System.out.println(GameEngine.TimeBasedGameToString());
		System.out.println(GameEngine.specialSwapToString());
	}
}