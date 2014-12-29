package UnitTest;

import java.util.ArrayList;

import game.BoardCell;
import game.GameEngine;
import game.Lokum;

public class Test {
	private BoardCell[][] sampleMap;
	private BoardCell[][] sampleMapForSpecial1;
	private static RemoveTester remove = RemoveTester.getInstance();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Construct the sample map
		BoardCell[][] sampleMap = new BoardCell[100][100];
		for (int x = 0; x < sampleMap.length; x++) {
			for (int y = 0; y < sampleMap[x].length; y++) {
				sampleMap[x][y] = new BoardCell(x, y, "free");
			}
		}

		// Filling sample map
		// In this code snippet, we test addLokum and getSingleRandomLokum methods.
		for (int x = 0; x < sampleMap.length; x++) {
			for (int y = 0; y < sampleMap[x].length; y++) {
				Lokum temp = GameEngine.getGameController().gameActions
						.getSingleRandomLokum();
				System.out.println("Target Cell:");
				System.out.println(sampleMap[x][y].toString());
				System.out.println("Assigned Lokum to Cell:");
				System.out.println(temp.toString());
				sampleMap[x][y].addLokum(temp);
				System.out.println("");
			}
		}

		// Remove testing progress
		// Applying Remove Method for each single cell
		// With this code snippet, we test all simple Remove Methods
		// in Remove Class.
		// (removeThreeVertical, removeThreeHorizontal, removeFourVertical,
		// removeFourHorizontal,removeFive,removeL, removeT)
		

		for (int x = 0; x < sampleMap.length; x++) {
			for (int y = 0; y < sampleMap[x].length; y++) {
				remove.setRemoveLocalMap(sampleMap);
				remove.removeforSingleCell(sampleMap[x][y]);
			}
		}
		
		
		
		

		//With this code snippet, we test special ColorBomb vs. ColorBomb remove method.
		
		BoardCell[][] sampleMapForSpecial1 = new BoardCell[3][3];
		for (int x = 0; x < sampleMapForSpecial1.length; x++) {
			for (int y = 0; y < sampleMapForSpecial1[x].length; y++) {
				sampleMapForSpecial1[x][y] = new BoardCell(x, y, "free");
			}
		}
		Lokum l1=new Lokum("Red","ColorBomb");
		Lokum l2=new Lokum("Red","ColorBomb");
		sampleMapForSpecial1[0][0].addLokum(l1);
		sampleMapForSpecial1[0][1].addLokum(l2);
		for (int x = 0; x < sampleMapForSpecial1.length; x++) {
			for (int y = 0; y < sampleMapForSpecial1[x].length; y++) {
				Lokum temp = GameEngine.getGameController().gameActions
						.getSingleRandomLokum();
				if (!sampleMapForSpecial1[x][y].hasLokum()) {
					sampleMapForSpecial1[x][y].addLokum(temp);
					
				}
				System.out.println("Target Cell:");
				System.out.println(sampleMapForSpecial1[x][y].toString());
				System.out.println("Assigned Lokum to Cell:");
				System.out.println(sampleMapForSpecial1[x][y].getCellLokum().toString());
				System.out.println("");
			}
		}
		remove.setRemoveLocalMap(sampleMapForSpecial1);
		remove.removeFromTwoPair(sampleMapForSpecial1, sampleMapForSpecial1[0][0], sampleMapForSpecial1[0][1]);
		
		
		//With this code snippet, we test special Striped vs. Wrapped remove method.

		BoardCell[][] sampleMapForSpecial2 = new BoardCell[3][3];
		for (int x = 0; x < sampleMapForSpecial2.length; x++) {
			for (int y = 0; y < sampleMapForSpecial2[x].length; y++) {
				sampleMapForSpecial2[x][y] = new BoardCell(x, y, "free");
			}
		}
		Lokum l3=new Lokum("Blue","Striped");
		Lokum l4=new Lokum("Blue","Wrapped");
		sampleMapForSpecial2[1][1].addLokum(l3);
		sampleMapForSpecial2[1][2].addLokum(l4);
		for (int x = 0; x < sampleMapForSpecial2.length; x++) {
			for (int y = 0; y < sampleMapForSpecial2[x].length; y++) {
				Lokum temp = GameEngine.getGameController().gameActions
						.getSingleRandomLokum();
				if (!sampleMapForSpecial2[x][y].hasLokum()) {
					sampleMapForSpecial2[x][y].addLokum(temp);
					
				}
				System.out.println("Target Cell:");
				System.out.println(sampleMapForSpecial2[x][y].toString());
				System.out.println("Assigned Lokum to Cell:");
				System.out.println(sampleMapForSpecial2[x][y].getCellLokum().toString());
				System.out.println("");
			}
		}
		remove.setRemoveLocalMap(sampleMapForSpecial2);
		remove.removeFromTwoPair(sampleMapForSpecial2, sampleMapForSpecial2[1][1], sampleMapForSpecial2[1][2]);
		
			

	}

}
