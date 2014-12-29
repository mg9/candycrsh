package game;

import java.util.ArrayList;
import java.util.Random;

public class GameActions {

	/**
	 * Returns ArrayList<Lokum> Takes an integer, then creates an arrayList of
	 * Lokums with the size of this integer. Then, creates random Lokum objects
	 * and puts them in the arrayList.
	 * 
	 * @param int the size of arrayList.It means that needed lokums number
	 * @prerequest i should be greater than 0.
	 * @return ArrayList<Lokum>
	 * @see Lokum
	 */
	public ArrayList<Lokum> fillBoardRandomly(int i) {
		ArrayList<Lokum> lokums = new ArrayList<Lokum>();
		if (i > 0) {
			for (int j = 0; j < i; j++) {
				Lokum lm = new Lokum(randomColor(), "standart");
				lokums.add(lm);
			}
		}

		return lokums;

	}

	public String randomColor() {
		Random rand = new Random();
		String colors[] = new String[6];
		colors[0] = "Red";
		colors[1] = "Blue";
		colors[2] = "Green";
		colors[3] = "Purple";
		colors[4] = "Orange";
		colors[5] = "Yellow";
		int x = rand.nextInt(5);
		return colors[x];

	}
}
