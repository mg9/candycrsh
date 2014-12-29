import java.util.ArrayList;

import game.BoardCell;
import game.GameEngine;
import game.Lokum;


public class UnitTester {

	public static void main(String[] args) {
		GameEngine sampleEngine=GameEngine.getInstance();
		sampleEngine.getPainter().setGameBoardVisible(true);
		sampleEngine.getPainter().setGameScreenVisible(true);;
		
		//Cell Create Unit Test
		BoardCell bc=new BoardCell(1,2,"free");
		
		// TakeCelltoPaint Unit Test
		sampleEngine.getPainter().takeCelltoPaint(bc);
		System.out.println(bc.toString());
		
		BoardCell wall=new BoardCell(2,2,"wall");
		sampleEngine.getPainter().takeCelltoPaint(wall);
		
		Lokum lkm=new Lokum("Blue","standard");
		bc.addLokum(lkm);
		sampleEngine.getPainter().takeCelltoPaint(wall);
		
		
		//FillBoardRandomly Unit Test
		ArrayList<Lokum> lokums=sampleEngine.getGameController().gameActions.fillBoardRandomly(3);
		
		bc.addLokum(lokums.get(0));
		sampleEngine.getPainter().takeCelltoPaint(bc);
		
		BoardCell bc2=new BoardCell(4,3,"free");
		
		bc2.addLokum(lokums.get(1));
		sampleEngine.getPainter().takeCelltoPaint(bc2);
		
		BoardCell wall2=new BoardCell(0,0,"wall");
		wall2.addLokum(lokums.get(2));
		sampleEngine.getPainter().takeCelltoPaint(wall2);
		
		System.out.println("Tostringtester"+wall2.toString());
		
	//	sampleEngine.getPainter().takeLokumtoPaint(bc.getCellLokum());
		
		
	}
}
