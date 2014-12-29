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
		Board.boardMap= new BoardCell[1][1];
	}
	
	public static void destroyBoard(){
		instance=null;
	}
	
	public static Board getInstance(){
		if (instance==null) {
			instance=new Board();
		}
			return instance;
		
	}
	
	public static void setGameBoardMap(BoardCell[][] newMap){
		boardMap=null;
	boardMap=newMap;
	GameEngine.getPainter().getLokumsScreen().importBoardMapToGUI(boardMap);
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
		Board.boardMap=temp;
	}

	public int getBoardDimy() {
		return boardDimy;
	}


	
	public void addCell(BoardCell cell){

			
			boardMap[cell.getCellX()][cell.getCellY()]=cell;
			
			
		

		
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
