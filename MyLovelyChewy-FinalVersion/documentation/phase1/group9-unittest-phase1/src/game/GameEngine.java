package game;

public class GameEngine {
	private static Board gameBoard=Board.getInstance();
	public static int defaultSizePX=50;
	private static int score;
	private static int lvl;
	private static GameEngine instance= new GameEngine();
	private static Painter painter=new Painter();
	private static Controller gameController =new Controller();

	private GameEngine() {
		
		
	}
	
	public static Controller getGameController(){
		return gameController;
	}
	
	public static GameEngine getInstance(){
		
		return instance;
	}

	public static Board getGameBoard() {
		return gameBoard;
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		GameEngine.score = score;
	}

	public static Painter getPainter() {
		return painter;
	}

	public static int getLvl() {
		return GameEngine.lvl;
	}

	public static void setLvl(int lvl) {
		GameEngine.lvl = lvl;
	}

}
