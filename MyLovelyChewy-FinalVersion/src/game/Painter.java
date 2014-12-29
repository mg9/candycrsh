package game;

import java.awt.BorderLayout;
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

public class Painter {
	JFrame jfr;	
	LokumsScreen lkmscreen;
	static HelperScreen hs = HelperScreen.getInstance();
	public static HelperScreen getHelperScreen(){
		return hs;
	}
	
	public Painter() {
		this.jfr = new JFrame("Chewy Lokum");
		jfr.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		lkmscreen=LokumsScreen.getInstance();
		lkmscreen.setVisible(false);
		
		BorderLayout lay=new BorderLayout();
		jfr.setLayout(lay);
		jfr.add(lkmscreen,BorderLayout.CENTER);
		jfr.setBackground(Color.WHITE);
		jfr.pack();
		jfr.add(hs, BorderLayout.NORTH);
		//jfr.setSize(500,500); 
		jfr.setVisible(false);

	}

    public void setScreenSolution(int x  , int y){
    	jfr.setSize(x, y);
    }
	public LokumsScreen getLokumsScreen(){
		return lkmscreen;
	}
	public void setGameScreenVisible(boolean p){
		jfr.setVisible(p);
	}
	
	public void setGameBoardVisible(boolean p){
		lkmscreen.setVisible(p);
	}
	public void updateFrontEnd(){
		
	}

	// gameBoard.fr.getContentPane().add(this);
	// System.out.println("shape was painted");

	public void takeComponenttoPaint(JComponent jp) {
		//this.add(jp);
		jfr.add(jp);
		jfr.repaint();
		jfr.setVisible(true);
		//this.repaint();
	}
	
	

}
