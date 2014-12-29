package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.xml.transform.TransformerException;

public class LokumsScreen extends JPanel implements MouseListener, MouseMotionListener{
	BoardCell[][] boardCells=new BoardCell[0][0];
	private int initialXForMouse;
	private BoardCell selectedCell;
	private int initialYforMouse;
	public int getInitialXForMouse() {
		return initialXForMouse;
	}

	public void setInitialXForMouse(int initialXForMouse) {
		this.initialXForMouse = initialXForMouse;
	}

	public int getInitialYforMouse() {
		return initialYforMouse;
	}

	public void setInitialYforMouse(int initialYforMouse) {
		this.initialYforMouse = initialYforMouse;
	}

	public static LokumsScreen instance =new LokumsScreen();
	private LokumsScreen(){
		super();
		addMouseMotionListener(this);
		addMouseListener(this);

	}
	public static void destroyLokumsScreen(){
		instance=null;
	}
	

	public static LokumsScreen getInstance(){
		if (instance==null) {
			instance=new LokumsScreen();
		} 
		return instance;
	}

	public void paint (Graphics g){
		if (boardCells==null) {
			System.out.println("Lokumscreen array is empty");
		}else{


			for (int i = 0; i < boardCells.length; i++) {
				for (int j = 0; j < boardCells[i].length; j++) {
					boardCells[i][j].paint(g);
					if (boardCells[i][j].hasLokum()) {
						
						if (selectedCell==null) {
							g.drawImage(boardCells[i][j].getCellLokum().pic,boardCells[i][j].getCellLokum().getXpositionForGui(),
									boardCells[i][j].getCellLokum().getYpositionForGui(),GameEngine.defaultSizePX,
									GameEngine.defaultSizePX, this);
							if (boardCells[i][j].getCellLokum().isTimerLokum()) {
								Border linebor = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
								linebor.paintBorder(this, g, i*GameEngine.defaultSizePX, j*GameEngine.defaultSizePX, GameEngine.defaultSizePX, GameEngine.defaultSizePX);
							}
						}else {
							if (!(selectedCell.getCellX()==boardCells[i][j].getCellX() && selectedCell.getCellY()==boardCells[i][j].getCellY())) {

								g.drawImage(boardCells[i][j].getCellLokum().pic,boardCells[i][j].getCellLokum().getXpositionForGui(),
										boardCells[i][j].getCellLokum().getYpositionForGui(),GameEngine.defaultSizePX,
										GameEngine.defaultSizePX, this);
								if (boardCells[i][j].getCellLokum().isTimerLokum()) {
									Border linebor = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
									linebor.paintBorder(this, g, i*GameEngine.defaultSizePX, j*GameEngine.defaultSizePX, GameEngine.defaultSizePX, GameEngine.defaultSizePX);
								}
							}
						}
					}
				}
			}
			if (selectedCell!=null){
				g.drawImage(selectedCell.getCellLokum().pic,selectedCell.getCellLokum().getXpositionForGui(),
						selectedCell.getCellLokum().getYpositionForGui(),GameEngine.defaultSizePX,
						GameEngine.defaultSizePX, this);
				if (selectedCell.getCellLokum().isTimerLokum()) {
					Border linebor = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
					linebor.paintBorder(this, g,selectedCell.getCellLokum().getXpositionForGui()*GameEngine.defaultSizePX, selectedCell.getCellLokum().getYpositionForGui()*GameEngine.defaultSizePX, GameEngine.defaultSizePX, GameEngine.defaultSizePX);
				}
				
			}


		}
	}

	public void importBoardMapToGUI(BoardCell[][] boardMap){
		boardCells=null;
		boardCells=boardMap;
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {



		if(e.getX()+(GameEngine.defaultSizePX-(getInitialXForMouse()%GameEngine.defaultSizePX))<=GameEngine.getGameBoard().getBoardDimX()*GameEngine.defaultSizePX &&e.getY()+(GameEngine.defaultSizePX-(getInitialYforMouse()%GameEngine.defaultSizePX))<=GameEngine.getGameBoard().getBoardDimy()*GameEngine.defaultSizePX){
			if (e.getY()-(this.getInitialYforMouse()%GameEngine.defaultSizePX)>0 && e.getX()-(this.getInitialXForMouse()%GameEngine.defaultSizePX)>0) {
				if ((e.getX()-(this.getInitialXForMouse()%GameEngine.defaultSizePX)>((this.getInitialXForMouse()/GameEngine.defaultSizePX)-1)*GameEngine.defaultSizePX)&&(e.getY()-(this.getInitialYforMouse()%GameEngine.defaultSizePX)>((this.getInitialYforMouse()/GameEngine.defaultSizePX)-1)*GameEngine.defaultSizePX)) {
					if ((e.getX()+(GameEngine.defaultSizePX-(getInitialXForMouse()%GameEngine.defaultSizePX))<((this.getInitialXForMouse()/GameEngine.defaultSizePX)+2)*GameEngine.defaultSizePX)&&(e.getY()+(GameEngine.defaultSizePX-(getInitialYforMouse()%GameEngine.defaultSizePX))<((this.getInitialYforMouse()/GameEngine.defaultSizePX)+2)*GameEngine.defaultSizePX)) {
						selectedCell.getCellLokum().setXpositionForGui(e.getX()-(this.getInitialXForMouse()%GameEngine.defaultSizePX));
						selectedCell.getCellLokum().setYpositionForGui(e.getY()-(this.getInitialYforMouse()%GameEngine.defaultSizePX));
						repaint();
					}
				}
			}


		};

	}



	@Override
	public void mouseMoved(MouseEvent e) {


	}
	void saySomething(String eventDescription, MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) { 
		if (e.getX()<GameEngine.defaultSizePX*boardCells.length && e.getY()<GameEngine.defaultSizePX*boardCells[0].length ) {
			setInitialXForMouse(e.getX());
			setInitialYforMouse(e.getY());
			selectedCell=boardCells[e.getX()/ GameEngine.defaultSizePX][e.getY() / GameEngine.defaultSizePX];
		}else {
			System.out.println("Disari tiklanamaz");
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Boolean canSwap=true;
		if (e.getX()<0 || e.getY() < 0 ||
				e.getX()>GameEngine.getGameBoard().getBoardDimX() *GameEngine.defaultSizePX ||
					e.getY()>GameEngine.getGameBoard().getBoardDimX() *GameEngine.defaultSizePX   || 
						(e.getX()>(((selectedCell.getCellX()+1)*GameEngine.defaultSizePX) +GameEngine.defaultSizePX)) ||
						(e.getY()>(((selectedCell.getCellY()+1)*GameEngine.defaultSizePX) +GameEngine.defaultSizePX))|| 
								(e.getX()<(selectedCell.getCellX()-1)*GameEngine.defaultSizePX) ||
									(e.getY()<(selectedCell.getCellY()-1)*GameEngine.defaultSizePX)) {
			
			Lokum selectedsLokum=selectedCell.getCellLokum();

			selectedCell.addLokum(selectedsLokum);
			selectedCell=null;
			repaint();
		}else{
			BoardCell targetCell=boardCells[e.getX()/GameEngine.defaultSizePX][e.getY()/GameEngine.defaultSizePX];
			if (GameEngine.getGameController().CanSwapThem(targetCell,selectedCell)) {
				Lokum selectedsLokum=selectedCell.getCellLokum();
				Lokum targetsLokum=targetCell.getCellLokum();
				targetCell.addLokum(selectedsLokum);
				selectedCell.addLokum(targetsLokum);
//				GameEngine.getGameBoard().getBoardMap()[selectedCell.getCellX()][selectedCell.getCellY()]=selectedCell;
//				GameEngine.getGameBoard().getBoardMap()[targetCell.getCellX()][targetCell.getCellY()]=targetCell;
//				GameEngine.getGameController().checkAndRemove();
				Remove.getInstance().removeFromTwoPair(GameEngine.getGameBoard().getBoardMap(), targetCell, selectedCell);
				try {
					GameEngine.decreaseMoveLimit();
				} catch (TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
				
				selectedCell=null;
				repaint();

			}else if(GameEngine.isSpecialSwapMove){
				try {
					GameEngine.getGameController().swapSpecialMove(targetCell, selectedCell);
				} catch (TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else {
				Lokum selectedsLokum=selectedCell.getCellLokum();

				selectedCell.addLokum(selectedsLokum);
				selectedCell=null;
				repaint();
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
