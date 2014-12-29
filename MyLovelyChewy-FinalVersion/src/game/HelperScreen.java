package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import user.userActions;

public class HelperScreen extends JPanel {
	private static HelperScreen instance= new HelperScreen();
	private static JLabel currentScoreLabel;
	private static JLabel desiredScoreLabel;
	private static JLabel moveLimitLabel;
	private static JLabel levelLabel;
	private static JLabel timeLabel;
	private static JButton saveButton;
	private static JButton specialSwap;
	public static void destroyHelperScreen(){
		instance= null;
	}
	private HelperScreen(){
		super();
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(Calendar.getInstance().getTime());
		desiredScoreLabel=new JLabel("Target Score: "+ GameEngine.getDesiredScore());
		currentScoreLabel = new JLabel("Current Score: " + GameEngine.getCurrentScore());
		levelLabel=new JLabel("Level: "+GameEngine.getLvl());
		saveButton=new JButton("Save");
		timeLabel=new JLabel("Remaining Time: " + Integer.toString(GameEngine.getRemainingTime()));
		specialSwap=new JButton("Special Swap: "+Integer.toString(GameEngine.getRemainingSpecialSwap()));
		
	
		saveButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				   try {
					userActions.SaveGame();
				} catch (ParserConfigurationException | SAXException
						| IOException | TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  } 
				} );
		specialSwap.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (GameEngine.getRemainingSpecialSwap()>0) {
					if (GameEngine.isSpecialSwapMove==false) {
						specialSwap.setOpaque(true);
						specialSwap.setBackground(Color.green);
						GameEngine.isSpecialSwapMove=true;
					} else {
						setSpecialSwapButtonPassive();
					}
					
				} else {
					System.out.println("Special Swap cannot happen");
				}
			}
		});
		moveLimitLabel=new JLabel("Move: " + GameEngine.getMoveLimit());
		timeLabel.setVisible(false);
		this.add(moveLimitLabel);
		this.add(currentScoreLabel);
		this.add(desiredScoreLabel);
		this.add(levelLabel);
		this.add(saveButton);
		this.add(specialSwap);
		this.add(timeLabel);
		
	}
	
	public static void activateTimeLabel(){
		timeLabel.setVisible(true);
	}
	
	public static void setTimeLabel(int time){
		timeLabel.setText("Remaining Time: " + Integer.toString(time));
	}
	
	public static void deactivateTimeLabel(){
		timeLabel.setVisible(false);
	}
	
	public static void setSpecialSwapButtonPassive(){
		specialSwap.setOpaque(false);
		specialSwap.setBackground(Color.gray);
		GameEngine.isSpecialSwapMove=false;
	}
	
	
	public static HelperScreen getInstance (){
		if (instance==null) {
			instance = new HelperScreen();
		}
			return instance;
		
	}
	
	public static void setLevelLabel(int level){
		levelLabel.setText("Level: "+level);
		instance.repaint();
	}
	
	public static void setRemainingSpeacialSwap(int x){
		specialSwap.setText("Special Swap: "+ Integer.toString(x));
		instance.repaint();
	}
	public static void setMoveLimitLabel(int limit){
		moveLimitLabel.setText("Move: " +limit);
		instance.repaint();
	}
	
	
	public static void setCurrentScoreLabel(int numberScore){
		currentScoreLabel.setText("Current Score:"+Integer.toString(numberScore));
		instance.repaint();
	}
	
	public static void setDesiredScoreLabel(int numberScore){
		desiredScoreLabel.setText("Required Score:"+Integer.toString(numberScore));
		instance.repaint();
	}
	
	
	
}
