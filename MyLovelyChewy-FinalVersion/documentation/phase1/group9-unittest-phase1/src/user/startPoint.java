package user;
import game.GameEngine;

import java.awt.CheckboxGroup;
import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class startPoint {
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;
    public static userActions uA;
    public static GameEngine engine;

	public static void main(String[] args) {
		
		new startPoint();
		engine= GameEngine.getInstance();
	}

	public startPoint() {
		final JFrame frame = new JFrame();
		this.uA=new userActions();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (RIGHT_TO_LEFT) {
			frame.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		JLabel label;
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
			// natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}

		
		
	    ButtonGroup bG = new ButtonGroup();
	    
		JRadioButton savedGame = new JRadioButton("Play Saved Game");
		c.fill = GridBagConstraints.HORIZONTAL;
		// c.ipady = 40; //make this component tall
		c.weightx = 0.5;
		savedGame.setSelected(false);
		// c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		if (uA.getSavedGameNames().size()!=0) {
			frame.add(savedGame,c);
		}

		bG.add(savedGame);
		JRadioButton levelGame = new JRadioButton("Choose Level");
		c.fill = GridBagConstraints.HORIZONTAL;
		// c.ipady = 40; //make this component tall
		c.weightx = 0.5;
		levelGame.setSelected(true);
		// c.gridwidth = 3;
		c.gridx = 1;
		c.gridy = 0;
		frame.add(levelGame, c);
		bG.add(levelGame);
		
		final JLabel labelforLevel = new JLabel("Level:");
		if (shouldWeightX) {
			c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		frame.add(labelforLevel, c);
		
		
		String[] lvlA = new String[uA.getUserLevel()];
		for (int i = 0; i < lvlA.length; i++) {
		lvlA[i]=Integer.toString(i+1);
			
		}
		final JComboBox levelList = new JComboBox(lvlA);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 1;
		frame.add(levelList, c);
		
		
		final JLabel labelForSaved = new JLabel("SavedGame:");
		if (shouldWeightX) {
			c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		labelForSaved.setVisible(false);
		frame.add(labelForSaved, c);
		
		
		String[] lvlAx = new String[uA.getSavedGameNames().size()];
		int counter=0;
		for (String string : uA.getSavedGameNames()) {
			lvlAx[counter]=string;
			counter++;
		};
		final JComboBox levelListx = new JComboBox(lvlAx);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 1;
		levelListx.setVisible(false);
		frame.add(levelListx, c);
		
		levelGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				levelListx.setVisible(false);
				labelForSaved.setVisible(false);
				levelList.setVisible(true);
				labelforLevel.setVisible(true);
				
				
			}
		});
		
		
		savedGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				levelListx.setVisible(true);
				labelForSaved.setVisible(true);
				levelList.setVisible(false);
				labelforLevel.setVisible(false);
				
				
			}
		});
		
		


		JButton button = new JButton("Play");

		c.fill = GridBagConstraints.HORIZONTAL;
		// c.ipady = 40; //make this component tall
		c.weightx = 0.5;

		c.gridwidth = 10;
		c.gridx = 0;
		c.gridy = 5;
		frame.add(button, c);
		// The ActionListener class is used to handle the
		// event that happens when the user clicks the button.
		// As there is not a lot that needs to happen we can
		// define an anonymous inner class to make the code simpler.
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				uA.loadLevel(Integer.parseInt(levelList.getSelectedItem().toString()));
				
				frame.setVisible(false);
				// listPanel.setVisible(!listPanel.isVisible());
				// comboPanel.setVisible(!comboPanel.isVisible());

			}
		});
		frame.getContentPane();

		frame.pack();
		frame.setVisible(true);

	}

}