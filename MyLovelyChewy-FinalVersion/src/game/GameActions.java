package game;

import java.util.ArrayList;
import java.util.Random;

public class GameActions {


	/**
	 * Returns Lokum,  Creates a lokum object which its type is standart,
	 * and if the level is timeBasedLevel, it creates a lokum object with additional 
	 * time property (4 seconds) with probability 1/10.
	
	 * @param 
	 * @return Lokum
	 * @see Lokum
	 */
	public Lokum getSingleRandomLokum() {
		Lokum lm = new Lokum(randomColor(), "standart");
		Random rand=new Random();
		int x= rand.nextInt(10);
		if (x==1) {
			if (GameEngine.isTimeBasedGame) {
				lm.setTimeOfLokum(4);
			//	System.out.println("time based lokum olustu");
			}
		}
		
		return lm;
	}

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
	public BoardCell[][] checkForSameColor(BoardCell[][] someBoard){

		int repeatedHorizontal=1;
		int repeatedVertical=1;
		boolean isStripped=false;
		boolean isColorBomb=false;
		boolean isWrapped= false;

		for(int i=0; i<someBoard.length;i++){
			for (int j = 0; j < someBoard[i].length; j++) {
				if(j<someBoard[i].length-1){
					if(someBoard[i][j].getCellLokum()!=null&&someBoard[i][j+1].getCellLokum()!=null){
						while(someBoard[i][j].getCellLokum().color.equals(someBoard[i][j+1].getCellLokum().color)){
							j++;
							repeatedHorizontal++;
							
							if(j >= someBoard[i].length-1){
								
								break;
							}
						}
					}
					else{
						repeatedHorizontal=1;
					}
				}
				if(repeatedHorizontal==4) isStripped= true;
				if(repeatedHorizontal==5) isColorBomb= true;
				isWrapped=true;

				if(repeatedHorizontal>2){
					while(repeatedHorizontal>0){
					
						String middleRow= someBoard[i][j-repeatedHorizontal+1].getCellLokum().color;
						while(true){



							if(2<=i && i<=someBoard.length-3
									&& (someBoard[i+2][j-repeatedHorizontal+1].getCellLokum()!=null && someBoard[i-2][j-repeatedHorizontal+1].getCellLokum()!=null && someBoard[i+1][j-repeatedHorizontal+1].getCellLokum()!=null && someBoard[i-1][j-repeatedHorizontal+1].getCellLokum() 
									!=null)
									){
								
								if(someBoard[i+2][j-repeatedHorizontal+1].getCellLokum().color.equals(middleRow) && someBoard[i-2][j-repeatedHorizontal+1].getCellLokum().color.equals
										(middleRow) && someBoard[i+1][j-repeatedHorizontal+1].getCellLokum().color.equals(middleRow) && someBoard[i-1][j-repeatedHorizontal+1].getCellLokum().color.equals 
										(middleRow)) // 
								{
									//pos1= true;
									for(int a=0; a<5; a++){
										String temp3 = someBoard[i-2+a][j-repeatedHorizontal+1].getCellLokum().color;
										someBoard[i-2+a][j-repeatedHorizontal+1].removeLokum();
										if(isWrapped){
											someBoard[i-2+a][j-repeatedHorizontal+1].addLokum(new Lokum(temp3,"Wrapped"));
											isWrapped=false;
										}
									}
									break;
								}
							}

							if(1<=i && i<=someBoard.length-3
									&& (someBoard[i+2][j-repeatedHorizontal+1].getCellLokum()!=null && someBoard[i+1][j-repeatedHorizontal+1].getCellLokum()!=null && someBoard[i-1][j-repeatedHorizontal+1].getCellLokum()!=null )
									){
								if(someBoard[i+2][j-repeatedHorizontal+1].getCellLokum().color.equals(middleRow) && someBoard[i+1][j-repeatedHorizontal+1].getCellLokum().color.equals(middleRow) && someBoard[i-1][j-repeatedHorizontal+1].getCellLokum().color.equals 
										(middleRow)){
									//pos2=true;
									for(int a=0; a<4; a++){
										String temp4 = someBoard[i-1+a][j-repeatedHorizontal+1].getCellLokum().color;
										someBoard[i-1+a][j-repeatedHorizontal+1].removeLokum();
										if(isWrapped){
											someBoard[i-1+a][j-repeatedHorizontal+1].addLokum(new Lokum(temp4,"Wrapped"));
											isWrapped=false;
										}
									}
									break;
								}
							}
							if(2<=i && i<=someBoard.length-2
									&& (someBoard[i-2][j-repeatedHorizontal+1].getCellLokum()!=null && someBoard[i-1][j-repeatedHorizontal+1].getCellLokum()!=null && someBoard[i+1][j-repeatedHorizontal+1].getCellLokum()!=null)
									){
								if(someBoard[i-2][j-repeatedHorizontal+1].getCellLokum().color.equals(middleRow) && someBoard[i-1][j-repeatedHorizontal+1].getCellLokum().color.equals(middleRow) && someBoard[i+1][j-repeatedHorizontal+1].getCellLokum().color.equals 
										(middleRow)){
									//pos3=true;
									for(int a=0; a<4; a++){
										String temp5 = someBoard[i-2+a][j-repeatedHorizontal+1].getCellLokum().color;
										someBoard[i-2+a][j-repeatedHorizontal+1].removeLokum();
										if(isWrapped){
											someBoard[i-2+a][j-repeatedHorizontal+1].addLokum(new Lokum(temp5,"Wrapped"));
											isWrapped=false;
										}
									}
									break;
								}
							}

							if(2<=i &&
									(someBoard[i-2][j-repeatedHorizontal+1].getCellLokum()!=null && someBoard[i-1][j-repeatedHorizontal+1].getCellLokum()!=null)
									){
								if(someBoard[i-2][j-repeatedHorizontal+1].getCellLokum().color.equals(middleRow) && someBoard[i-1][j-repeatedHorizontal+1].getCellLokum().color.equals(middleRow)){
									//pos4=true;
									for(int a=0; a<3; a++){
										String temp6 = someBoard[i-2+a][j-repeatedHorizontal+1].getCellLokum().color;
										someBoard[i-2+a][j-repeatedHorizontal+1].removeLokum();
										if(isWrapped){
											someBoard[i-2+a][j-repeatedHorizontal+1].addLokum(new Lokum(temp6,"Wrapped"));
											isWrapped=false;
										}
									}
									break;
								}
							}

							if(i<= someBoard.length-3
									&& (someBoard[i+2][j-repeatedHorizontal+1].getCellLokum()!=null && someBoard[i+1][j-repeatedHorizontal+1].getCellLokum()!=null)
									){
								if(someBoard[i+2][j-repeatedHorizontal+1].getCellLokum().color.equals(middleRow)&& someBoard[i+1][j-repeatedHorizontal+1].getCellLokum().color.equals(middleRow)){
									//pos5=true;
									for(int a=0; a<3; a++){
										String temp7= someBoard[i+a][j-repeatedHorizontal+1].getCellLokum().color;
										someBoard[i+a][j-repeatedHorizontal+1].removeLokum();
										if(isWrapped){
											someBoard[i+a][j-repeatedHorizontal+1].addLokum(new Lokum(temp7,"Wrapped"));
											isWrapped=false;
										}
									}
									break;
								}
							}
							if(1<=i && i<=someBoard.length-2
									&& (someBoard[i-1][j-repeatedHorizontal+1].getCellLokum()!=null && someBoard[i+1][j-repeatedHorizontal+1].getCellLokum()!=null)
									){
								if(someBoard[i-1][j-repeatedHorizontal+1].getCellLokum().color.equals(middleRow) && someBoard[i+1][j-repeatedHorizontal+1].getCellLokum().color.equals(middleRow)){
									//pos6=true;
									for(int a=0; a<3; a++){
										String temp8 = someBoard[i-1+a][j-repeatedHorizontal+1].getCellLokum().color;
										someBoard[i-1+a][j-repeatedHorizontal+1].removeLokum();
										if(isWrapped){
											someBoard[i-1+a][j-repeatedHorizontal+1].addLokum(new Lokum(temp8,"Wrapped"));
											isWrapped=false;
										}
									}
									break;
								}
							}

							break;
						}

						String temp1 =someBoard[i-repeatedVertical+1][j].getCellLokum().color;

						someBoard[i][j-repeatedHorizontal+1].removeLokum();
						if(isStripped){
							someBoard[i][j-repeatedHorizontal+1].addLokum(new Lokum(temp1,"Striped"));
							isStripped=false;
						}
						if(isColorBomb){
							someBoard[i][j-repeatedHorizontal+1].addLokum(new Lokum(temp1,"ColorBomb"));
							isColorBomb=false;
						}

						repeatedHorizontal--;

					}
				}

				repeatedHorizontal=1;
			}

		}

		for(int j=0; j<someBoard[0].length;j++){
			for (int i = 0; i < someBoard.length; i++) {
				if(i<someBoard.length-1){
					if(someBoard[i][j].getCellLokum()!=null&&someBoard[i+1][j].getCellLokum()!=null){
						while(someBoard[i][j].getCellLokum().color.equals(someBoard[i+1][j].getCellLokum().color)){
							i++;

							repeatedVertical++;
							
							if(i>=someBoard.length-1){
								break;
							}
							if(someBoard[i+1][j].getCellLokum()==null) break;
						}


					}
					else {
						repeatedVertical = 1;
					}
				}
				if(repeatedVertical == 4 ) isStripped= true;
				if(repeatedVertical>2){
					while(repeatedVertical>0){
						
						String temp2 =someBoard[i-repeatedVertical+1][j].getCellLokum().color;
						someBoard[i-repeatedVertical+1][j].removeLokum();
						if(isStripped){

							someBoard[i-repeatedVertical+1][j].addLokum(new Lokum(temp2,"Striped"));
							isStripped=false;
						}
						if(isColorBomb){
							someBoard[i-repeatedVertical+1][j].addLokum(new Lokum(temp2,"ColorBomb"));
							isColorBomb=false;
						}
						repeatedVertical--;
					}
				}

				repeatedVertical=1;
			}

		}






		return someBoard;
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
		int x = rand.nextInt(6);
		return colors[x];

	}
}
