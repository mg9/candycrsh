package game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class BoardCell {
	private String cellType;
	private int cellX;
	private int cellY;
	private Lokum cellLokum;
	private boolean canTakeALokum;
	public BoardCell(int dimX,int dimY, String cellType){
		this.cellX=dimX;
		this.cellY=dimY;
		this.cellType=cellType;
		
		if(cellType.equals("free")){
			canTakeALokum=true;
		}
		
		if(cellType.equals("wall")){
			canTakeALokum=false;
		}
		
	}
	
	public void removeLokum(){
		
		cellLokum=null;
		
	}
	
	/**
	 * Returns void Takes the Lokum object, if the board cell is suitable for
	 * adding Lokum in itself, puts the Lokum in the cell.
	 * 
	 * @param Lokum
	 *            a Lokum object
	 * @prerequest This cell's type should support the taking Lokum in itself.
	 * @return void
	 * @see Lokum
	 */
	public void addLokum (Lokum l){
		if (canTakeALokum) {
			l.setXpositionForGui(cellX*GameEngine.defaultSizePX);
			l.setYpositionForGui(cellY*GameEngine.defaultSizePX);
			cellLokum=l;
		//	GameEngine.getPainter().repaint(); 
		}
		
		
	}
	
	public boolean hasLokum(){
		if (cellLokum!=null) {
		
			return true;
		
		} else {
			return false;
		}
	}
	
	public boolean canTakeLokum(){
		return canTakeALokum;
	}
	
	public Lokum getCellLokum (){
		return cellLokum;
	}
	public String getCellType() {
		return cellType;
	}

	public void setCellType(String cellType) {
		this.cellType = cellType;
	}
	
	public int getCellX(){
		return cellX;
		
	}
	
	public int getCellY(){
		return cellY;
	}
	
	public void paint(Graphics g) {
		if (cellType.equals("free")) {
			g.setColor(Color.white);
			g.fillRect(cellX*GameEngine.defaultSizePX, cellY*GameEngine.defaultSizePX, GameEngine.defaultSizePX, GameEngine.defaultSizePX);
		}
		
		if (cellType.equals("wall")) {
			g.setColor(Color.darkGray);
			g.fillRect(cellX*GameEngine.defaultSizePX, cellY*GameEngine.defaultSizePX, GameEngine.defaultSizePX, GameEngine.defaultSizePX);
		}
		
		

	}
	
	public String toString(){
		String result = "This Cells XPosition:"+ cellX + " YPosition " +cellY +" Pixel" + GameEngine.defaultSizePX + " CellType: " + cellType;
		return result;
	}
	

}
