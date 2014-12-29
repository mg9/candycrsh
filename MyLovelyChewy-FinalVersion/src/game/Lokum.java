package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Lokum {
	public String type;
	public String color;
	private String direction;
	private int XpositionForGui;
	private int YpositionForGui;
	private boolean isTimerLokum=false;
	private int timeOfLokum=0;
	Image pic;
	ImageIcon image;
	

	public Lokum(String color, String type) {
		this.type = type;
		this.color = color;
		direction="nodirection";

		if (type.equals("standart")) {

			switch (color) {
			case "Red":
				image = new ImageIcon(this.getClass().getResource(
						"../images/Red_lokum.png"));
				;
				break;
			case "Blue":
				image = new ImageIcon(this.getClass().getResource(
						"../images/Blue_lokum.png"));
				;
				break;
			case "Orange":
				image = new ImageIcon(this.getClass().getResource(
						"../images/Orange_lokum.png"));
				;
				break;
			case "Green":
				image = new ImageIcon(this.getClass().getResource(
						"../images/Green_lokum.png"));
				;
				break;
			case "Yellow":
				image = new ImageIcon(this.getClass().getResource(
						"../images/Yellow_lokum.png"));
				;
				break;
			case "Purple":
				image = new ImageIcon(this.getClass().getResource(
						"../images/Purple_lokum.png"));
				;
				break;

			}
		}
		if (type.equals("Striped")) {
			this.setDirection("horizontal");
			switch (color) {
			case "Red":
				image = new ImageIcon(this.getClass().getResource(
						"../images/Striped_red_lokum.png"));
				;
				break;
			case "Blue":
				image = new ImageIcon(this.getClass().getResource(
						"../images/Striped_blue_lokum.png"));
				;
				break;
			case "Orange":
				image = new ImageIcon(this.getClass().getResource(
						"../images/Striped_orange_lokum.png"));
				;
				break;
			case "Green":
				image = new ImageIcon(this.getClass().getResource(
						"../images/Striped_green_lokum.png"));
				;
				break;
			case "Yellow":
				image = new ImageIcon(this.getClass().getResource(
						"../images/Striped_yellow_lokum.png"));
				;
				break;
			case "Purple":
				image = new ImageIcon(this.getClass().getResource(
						"../images/Striped_purple_lokum.png"));
				;
				break;

			}

		}
		if (type.equals("Wrapped")) {

			switch (color) {
			case "Red":
				image = new ImageIcon(this.getClass().getResource(
						"../images/Wrapped_red_lokum.png"));
				;
				break;
			case "Blue":
				image = new ImageIcon(this.getClass().getResource(
						"../images/Wrapped_blue_lokum.png"));
				;
				break;
			case "Orange":
				image = new ImageIcon(this.getClass().getResource(
						"../images/Wrapped_orange_lokum.png"));
				;
				break;
			case "Green":
				image = new ImageIcon(this.getClass().getResource(
						"../images/Wrapped_green_lokum.png"));
				;
				break;
			case "Yellow":
				image = new ImageIcon(this.getClass().getResource(
						"../images/Wrapped_yellow_lokum.png"));
				;
				break;
			case "Purple":
				image = new ImageIcon(this.getClass().getResource(
						"../images/Wrapped_purple_lokum.png"));
				;
				break;

			}
		}
		if (type.equals("ColorBomb")) {
			image = new ImageIcon(this.getClass().getResource(
					"../images/Color_bomb_lokum.png"));
			;
		}
		pic = image.getImage();

	}

	/**
	 * Returns void
	 * Takes an integer, and sets the timeOfLokum field to this integer.
	 * It also makes the isTimerLokum boolean true.
	 * 
	 * @param an integer
	 * @prerequest --
	 * @return void
	 */
	public void setTimeOfLokum(int x){
		isTimerLokum=true;
		timeOfLokum=x;
	}
	
	/**
	 * Returns int,
	 *  Asks the timeOfLokum field, if it was modified
	 * it returns the new value, otherwise it returns 0.
	 * 
	 * @param 
	 * @prerequest
	 * @return int
	 */	
	public int getTimeOfLokum(){
		return timeOfLokum;
	}
	
	
	public boolean isTimerLokum(){
		return isTimerLokum;
	}
	
	public String TimeBasedLokumtoString() {
		if(isTimerLokum) {
			return "this lokum is timeBasedLokum and its time is " + timeOfLokum;
		}
		else {
			return "this lokum is not timeBasedLokum";
		}
	
	}
	

	public int getXpositionForGui() {
		return XpositionForGui;
	}

	public void setXpositionForGui(int xpositionForGui) {
		XpositionForGui = xpositionForGui;
	}

	public int getYpositionForGui() {
		return YpositionForGui;
	}

	public void setYpositionForGui(int ypositionForGui) {
		YpositionForGui = ypositionForGui;
	}

	public void setDirection(String direct){
		this.direction=direct;
	}
	public String getDirection(){
		return this.direction;
	}
	public Boolean isEqual(Lokum l) {
		Boolean result = false;
		if (l.type.equals(type) && l.color.equals(color)) {
			result = true;
		}
		return result;
	}

	public String toString() {
		
		return "Color : " + color + " is Timer? " + Boolean.toString(isTimerLokum) + " Type : " +type;
	}

}