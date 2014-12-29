package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import javax.xml.transform.TransformerException;

import user.userActions;

public class GameEngine {
	private static Board gameBoard=Board.getInstance();
	public static int defaultSizePX=50;
	private static int desiredScore=0;
	private static int currentScore=0;
	private static int moveLimit=0;
	private static int remainingSpecialSwap=1;
	public static boolean isTimeBasedGame=false;
	private static int remainingTime=0;
	public static Boolean isSpecialSwapMove=false;
	private static int lvl;
	private static GameEngine instance= new GameEngine();
	private static Painter painter=new Painter();
	private static Controller gameController =new Controller();
	private static Timer timer;
	private GameEngine() {
		
	}
	public static String isTimeBased(){
		if (isTimeBasedGame) {
			return "true";
		} else {
			return "false";
		}
	}
	
	public static int getRemainingSpecialSwap(){
		return remainingSpecialSwap;
	}
	public static String specialSwapToString() {
		return "this game has " + remainingSpecialSwap+ " special Swap moves";
	}
	
	public static void removeTimeBasedGame(){
		isTimeBasedGame=false;
		setRemainingTime(0);
		timer.stop();
		painter.getHelperScreen().deactivateTimeLabel();
	}
	
	
	public static void increaseTime(int time){
		remainingTime+=time;
		painter.getHelperScreen().setTimeLabel(remainingTime);
		System.out.println("The time of game is increased" + time+ " seconds");
	}

	public static void gameOver(int targetLevel) throws TransformerException{
		userActions.UpdateMaxLevel(targetLevel);
		GameEngine.setCurrentScore(0);
		userActions.loadLevel(targetLevel);;
	}
	
	public static void setTimeBasedGame(int time){
		isTimeBasedGame=true;
		remainingTime=time;
		painter.getHelperScreen().activateTimeLabel();
		setRemainingTime(time);
		timer=new Timer(1000 ,new ActionListener(){
			  public void actionPerformed(ActionEvent event){
				  if (remainingTime>0) {
					  setRemainingTime(remainingTime-1);
				}else{
					try {
						removeTimeBasedGame();
						gameOver(lvl);
					} catch (TransformerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				   
				  
				}});
		timer.start();
		
	}
	
	public static String TimeBasedGameToString() {
		if(isTimeBasedGame) {
			return "this game is timeBasedGame and its time is: "+ getRemainingTime();
		}
		else {
			return "this game is not timeBasedGame. ";
		}
	}
	
	public static void setRemainingTime(int x){
		remainingTime=x;
		painter.getHelperScreen().setTimeLabel(x);
	}
	
	
	
	public static int getRemainingTime(){
		return remainingTime;
	}
	
	public static void setRemainingSpecialSwap(int x){
		if (x>=0) {
			remainingSpecialSwap=x;
			painter.getHelperScreen().setRemainingSpeacialSwap(x);
		} else {
			System.out.println("Remaining Swap cannot be less than 0");
		}
		
	}
	
	public static void setMoveLimit(int x){
		moveLimit=x;
		GameEngine.getPainter().getHelperScreen().setMoveLimitLabel(moveLimit);
		
	}
	
	public static int getMoveLimit(){
		return moveLimit;
	}
	
	
	
	public static void decreaseMoveLimit() throws TransformerException{
		moveLimit-=1;
		if ((currentScore>=desiredScore )&&moveLimit>=0) {
			
			if (userActions.getMaxLevel()>=lvl+1) {
				gameOver(lvl+1);
				
			}else{
				gameOver(lvl);
			}
		}else if((currentScore<desiredScore )&&moveLimit<=0){
			gameOver(lvl+1);
		}
		
		
		setMoveLimit(moveLimit);
		
	}
	
	public static void increaseScore(int x){
		setCurrentScore(currentScore + x);
	}
	public static void setCurrentScore(int newscore){
		currentScore=newscore;
		GameEngine.getPainter().getHelperScreen().setCurrentScoreLabel(currentScore);;
	}
	
	public static int getCurrentScore(){
		return currentScore;
	}
	public static Controller getGameController(){
		return gameController;
	}
	
	public static GameEngine getInstance(){
		if (instance==null) {
			instance=new GameEngine();
		}
		return instance;
	}

	public static Board getGameBoard() {
		return gameBoard;
	}

	public static int getDesiredScore() {
		return desiredScore;
	}

	public static void setDesiredScore(int score) {
		GameEngine.desiredScore = score;
		GameEngine.getPainter().getHelperScreen().setDesiredScoreLabel(score);;
	}

	public static Painter getPainter() {
		return painter;
	}

	public static int getLvl() {
		return GameEngine.lvl;
	}

	public static void setLvl(int lvl) {
		GameEngine.lvl = lvl;
		GameEngine.getPainter().getHelperScreen().setLevelLabel(GameEngine.getLvl());
	}

}
