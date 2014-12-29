package game;

import java.util.ArrayList;

public class Board {
	private int boardDimX;
	private int boardDimy;
	private static Board instance= new Board();
	private Lokum[][] lokumArray;
	public static BoardCell[][] boardMap;
	private Board(){
		this.boardDimX=1;
		this.boardDimy=1;
	}
	
	public static Board getInstance(){
		
			return instance;
		
	}
	
	
	public int getBoardDimX() {
		return boardDimX;
	}
	
	public BoardCell[][] getBoardMap(){
		return boardMap;
	}
	

	public void setBoardDimXandDimy(int boardDimX , int boardDimy) {
		this.boardDimX = boardDimX;
		this.boardDimy=boardDimy;
		BoardCell[][] temp=new BoardCell[boardDimX][boardDimy];
		this.boardMap=temp;
	}

	public int getBoardDimy() {
		return boardDimy;
	}


	
	public void addCells(ArrayList<BoardCell> cells){
		for (BoardCell cell : cells) {
			
			boardMap[cell.getCellX()][cell.getCellY()]=cell;
			
			
		}
		for (BoardCell cell : cells) {
			GameEngine.getPainter().takeCelltoPaint(cell);;
		}
		
	}
	
	public String toString (){
		String result = "";
		
		if (boardMap==null) {
			result="Board Map is empty";
		}else{
		for (int i = 0; i < boardMap.length; i++) {
			for (int j = 0; j < boardMap[i].length; j++) {
				if (boardMap[i][j]==null) {
					result+="x="+i+" y="+j+"is null";
				}else {
					result+="\n"+boardMap[i][j].toString();
				}
			}
		}
		}
		return result;
	}

	
}
