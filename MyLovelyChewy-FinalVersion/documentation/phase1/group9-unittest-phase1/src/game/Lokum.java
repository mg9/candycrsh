package game;


import java.awt.Image;

import javax.swing.ImageIcon;

public class Lokum {
	public String type;
	public String color;
	Image pic;
	ImageIcon image;
    public Lokum(String color ,String type){
    	this.type=type;
		this.color=color;

		switch (color) {
		case "Red":
			image = new ImageIcon(this.getClass().getResource("../images/Red_lokum.png"));
			;
			break;
		case "Blue":
			image = new ImageIcon(this.getClass().getResource("../images/Blue_lokum.png"));
			;
			break;
		case "Orange":
			image = new ImageIcon(this.getClass().getResource(
					"../images/Orange_lokum.png"));
			;
			break;
		case "Green":
			image = new ImageIcon(this.getClass()
					.getResource("../images/Green_lokum.png"));
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

		pic = image.getImage();

	
    }
}
