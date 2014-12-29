package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Painter extends JPanel {

    CopyOnWriteArrayList<BoardCell> painted_Cells = new CopyOnWriteArrayList<BoardCell>();
    CopyOnWriteArrayList<Lokum> painted_Lokums = new CopyOnWriteArrayList<Lokum>();
    //	Graphics2D g2;
	JFrame jfr;	

	public Painter() {
		this.jfr = new JFrame("Chewy Lokum");
		jfr.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		jfr.setBackground(Color.WHITE);
		jfr.getContentPane().add("Center", this);
		jfr.pack();
		jfr.setSize(300,300); 
		//jfr.setSize(new Dimension((int) (GameBoard.dimX*GameBoard.GamePixel),
			//	(int) (GameBoard.dimY * GameBoard.GamePixel )));
	//	jfr.setResizable(false);
		jfr.setVisible(false);
		this.setVisible(false);

	}

	public void paint(Graphics g) { // PAINT'in bu design'i bozulmamali. sadece
									// graphics alacak.
//
//		g2 = (Graphics2D) g;
		BoardCell whichCell;
//
//		System.out.println(" Painter'in paint metoduna girdim");

		Iterator<BoardCell> itr = painted_Cells.iterator();
	
		
		while (itr.hasNext()) {
			whichCell = itr.next();

			whichCell.paint(g);
			
			if (whichCell.hasLokum()) {
				System.out.println("resimli cell"+ whichCell.toString());
				g.drawImage(whichCell.getCellLokum().pic,whichCell.getCellX()*GameEngine.defaultSizePX,
						whichCell.getCellY()*GameEngine.defaultSizePX,GameEngine.defaultSizePX,
						GameEngine.defaultSizePX, this);
			}
		}
//		g2.setPaint(Color.RED);
	
	}
	
	public void setGameScreenVisible(boolean p){
		jfr.setVisible(p);
	}
	
	public void setGameBoardVisible(boolean p){
		this.setVisible(p);
	}
	public void updateFrontEnd(){
		
	}

	// gameBoard.fr.getContentPane().add(this);
	// System.out.println("shape was painted");

	/**
	 * Returns void
	 * Takes the BoardCell object and adds it to the arrayList of the class.
	 * Then in the paint method, which provides to show the object in GUI.
	 * @param  BoardCell  a boardcell object,
	 * @prerequest BoardCell cannot be null.
	 * @return      void
	 * @see        BoardCell
	 */
	public void takeCelltoPaint(BoardCell l) {
		if(l!=null) {
			painted_Cells.add(l);
			repaint();
		}
	
	}

	public void takeCelltoRemove(BoardCell l) {
		for (int i = 0; i < painted_Cells.size(); i++) {
			if (painted_Cells.get(i) == l) {
				this.painted_Cells.remove(i);
			}
		}
		this.repaint();
	}
	public void takeLokumtoPaint(Lokum l) {

		this.painted_Lokums.add(l);
		this.repaint();
	}

	public void takeLokumtoRemove(Lokum l) {
		for (int i = 0; i < painted_Lokums.size(); i++) {
			if (painted_Lokums.get(i) == l) {
				this.painted_Lokums.remove(i);
			}
		}
		this.repaint();
	}
	public void takeComponenttoPaint(JComponent jp) {
		//this.add(jp);
		jfr.add(jp);
		jfr.repaint();
		jfr.setVisible(true);
		//this.repaint();
	}
	
	

}
