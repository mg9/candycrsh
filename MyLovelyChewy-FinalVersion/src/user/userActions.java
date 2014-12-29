package user;

import game.BoardCell;
import game.GameEngine;
import game.Lokum;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class userActions {
	private static Document doc;
	private static userActions instance=new userActions();

	private userActions(){
		try {
			File fXmlFile = new File("src/gameFiles/sample.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			this.doc=doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void SaveGame() throws ParserConfigurationException, SAXException, IOException, TransformerException {

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse("src/gameFiles/sample.xml");
		Element root = document.getDocumentElement();
		
		Element rootElement = document.getDocumentElement();
		NodeList nrm= rootElement.getElementsByTagName("savedGames");

		//  Element r2= nrm.item(0);
		Collection<SavedGame> svg = new ArrayList<SavedGame>();
		svg.add(new SavedGame());

		for (SavedGame s : svg) {

			Element savedGame = document.createElement("savedGame");
			Attr name=document.createAttribute("name");
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmSS").format(Calendar.getInstance().getTime());
			name.setValue(timeStamp);
			savedGame.setAttributeNode(name);
			nrm.item(0).appendChild(savedGame);

			//  Element name = document.createElement("name");
			// name.appendChild(document.createTextNode(i.getName()));
			// server.appendChild(name);

			Element gameLevel=document.createElement("gameLevel");
			Attr id= document.createAttribute("id"); // disardan
			Attr boardDimX=document.createAttribute("boardDimX"); 
			Attr boardDimY=document.createAttribute("boardDimY");
			Attr desiredScore=document.createAttribute("desiredScore");
			Attr currentScore=document.createAttribute("currentScore");
			Attr moveCount=document.createAttribute("moveCount");
			Attr isTimeBased=document.createAttribute("isTimeBased");
			Attr time=document.createAttribute("time");
			Attr specialSwapCount=document.createAttribute("specialSwapCount");

			id.setValue(Integer.toString((GameEngine.getLvl())));
			boardDimX.setValue(Integer.toString(GameEngine.getGameBoard().getBoardDimX()));
			boardDimY.setValue(Integer.toString(GameEngine.getGameBoard().getBoardDimy()));
			desiredScore.setValue(Integer.toString(GameEngine.getDesiredScore()));
			currentScore.setValue(Integer.toString(GameEngine.getCurrentScore()));
			moveCount.setValue(Integer.toString(GameEngine.getMoveLimit()));
			isTimeBased.setValue(GameEngine.isTimeBased());
			specialSwapCount.setValue(Integer.toString(GameEngine.getRemainingSpecialSwap()));
			
			if(GameEngine.isTimeBasedGame) {
				time.setValue(Integer.toString(GameEngine.getRemainingTime()));
			} else {
				time.setValue(Integer.toString(0));
			}
			

			gameLevel.setAttributeNode(id);
			gameLevel.setAttributeNode(boardDimX);
			gameLevel.setAttributeNode(boardDimY);
			gameLevel.setAttributeNode(desiredScore);
			gameLevel.setAttributeNode(currentScore);
			gameLevel.setAttributeNode(moveCount);
			gameLevel.setAttributeNode(isTimeBased);
			gameLevel.setAttributeNode(time);
			gameLevel.setAttributeNode(specialSwapCount);
			
			savedGame.appendChild(gameLevel);
			for (int x = 0; x < GameEngine.getGameBoard().getBoardDimX(); x++) {
				for (int y = 0; y < GameEngine.getGameBoard().getBoardDimy(); y++) {

					Element  boardCell=document.createElement("boardCell");
					//Attr id1= document.createAttribute("id");
					Attr canTakeALokum=document.createAttribute("canTakeALokum");	        	
					Attr dimx=document.createAttribute("dimx");
					Attr dimy=document.createAttribute("dimy");
					Attr cellType=document.createAttribute("cellType");
					
					



					dimx.setValue(Integer.toString(x));
					dimy.setValue(Integer.toString(y));
					cellType.setValue(GameEngine.getGameBoard().getBoardMap()[x][y].getCellType());
					if(GameEngine.getGameBoard().getBoardMap()[x][y].canTakeLokum()) {
						canTakeALokum.setValue("true");
					}else {
						canTakeALokum.setValue("false");
					}



					//boardCell.setAttributeNode(id1);
					boardCell.setAttributeNode(dimx);
					boardCell.setAttributeNode(dimy);
					boardCell.setAttributeNode(cellType);
					boardCell.setAttributeNode(canTakeALokum);

					gameLevel.appendChild(boardCell);  

					if(GameEngine.getGameBoard().getBoardMap()[x][y].hasLokum()) {
						Element  Lokum=document.createElement("Lokum");

						Attr type=document.createAttribute("type");	        	
						Attr color=document.createAttribute("color");
						Attr direction=document.createAttribute("direction");
						Attr isTimerLokum=document.createAttribute("isTimerLokum");
						Attr LokumTime=document.createAttribute("LokumTime");

						type.setValue(GameEngine.getGameBoard().getBoardMap()[x][y].getCellLokum().type);
						color.setValue(GameEngine.getGameBoard().getBoardMap()[x][y].getCellLokum().color);
						direction.setValue(GameEngine.getGameBoard().getBoardMap()[x][y].getCellLokum().getDirection());
						if(GameEngine.getGameBoard().getBoardMap()[x][y].getCellLokum().isTimerLokum()) {
							isTimerLokum.setValue("true");
							LokumTime.setValue(Integer.toString(GameEngine.getGameBoard().getBoardMap()[x][y].getCellLokum().getTimeOfLokum()));
				
						}
						else {
							isTimerLokum.setValue("false");
							LokumTime.setValue("0");
						}
						//	isTimerLokum.setValue(value);
						Lokum.setAttributeNode(type);
						Lokum.setAttributeNode(color);
						Lokum.setAttributeNode(direction);
						Lokum.setAttributeNode(isTimerLokum);
						Lokum.setAttributeNode(LokumTime);

						boardCell.appendChild(Lokum);
					}

				}
			}


			nrm.item(0).appendChild(savedGame);
			// root.appendChild(savedGame);

		}

		DOMSource source = new DOMSource(document);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		StreamResult result = new StreamResult("src/gameFiles/sample.xml");
		transformer.transform(source, result);
		Transformer tf = transformerFactory.newTransformer();
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		tf.setOutputProperty(OutputKeys.METHOD, "xml");
		tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	}

	public static userActions getInstance(){
		return instance;
	}

	public static Integer getUserLevel(){
		Element x=(Element)doc.getElementsByTagName("userLevel").item(0);
		return Integer.parseInt(x.getAttribute("id"));
		//return Integer.parseInt(doc.getElementsByTagName("userLevel").item(0).getTextContent());
	}

	public static Integer getMaxLevel(){ 
		NodeList nList = doc.getElementsByTagName("gameLevel");
		return nList.getLength();  
	}
	public static ArrayList<String> getSavedGameNames(){ 
		ArrayList<String> savedGames=new ArrayList<String>();

		NodeList nList = doc.getElementsByTagName("savedGame");


		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				savedGames.add(eElement.getAttribute("name"));


			}
		}
		return savedGames;
	}
	public static class SavedGame {
		//    public String getName() { return "foo"; }
		//   public Integer getPort() { return 12345; }
	}

	
	public static void LoadGame(String name) throws ParserConfigurationException,
	SAXException, IOException {
		int level;
		BoardCell[][] boardMap =new BoardCell[0][0];
		int currentScore;
		int desiredScore;
		int moveCount;
		String name2;
		int boardDimx = 0;
		int boardDimy=0;
		String isTimeBased="";
		int time=0;
		int specialSwapCount=0;
		
		name2 = name;
		DocumentBuilderFactory factory =

				DocumentBuilderFactory.newInstance();

		// Get the DOM Builder

		DocumentBuilder builder = factory.newDocumentBuilder();

		// Load and Parse the XML document

		// document contains the complete XML as a Tree.

//		Document document = builder.parse(ClassLoader
//				.getSystemResourceAsStream("src/gameFiles/sample.xml"));
		Document document=doc;
		// NodeList nodeList = document.getElementsByTagName("gameLevel");
		// System.out.println("Selected Level "+ Integer.toString(level));

		// Element savedGames=
		Element root = document.getDocumentElement();
		Element rootElement = document.getDocumentElement();
		NodeList nrm = rootElement.getElementsByTagName("savedGame");

		for (int i = 0; i < nrm.getLength(); i++) {
			Element savedGame = (Element) nrm.item(i);

			if (savedGame.getAttribute("name").equals(name2)) {
				Element gameLevel = (Element) savedGame.getFirstChild();

				level = Integer.parseInt(gameLevel.getAttribute("id"));
				boardDimx = Integer.parseInt(gameLevel
						.getAttribute("boardDimX"));
				boardDimy = Integer.parseInt(gameLevel
						.getAttribute("boardDimY"));
				boardMap=new BoardCell[boardDimx][boardDimy];
				currentScore = Integer.parseInt(gameLevel
						.getAttribute("currentScore"));
				desiredScore = Integer.parseInt(gameLevel
						.getAttribute("desiredScore"));
				moveCount= Integer.parseInt(gameLevel.getAttribute("moveCount"));
				isTimeBased= gameLevel.getAttribute("isTimeBased");
				time=Integer.parseInt(gameLevel.getAttribute("time"));
				specialSwapCount=Integer.parseInt(gameLevel.getAttribute("specialSwapCount"));
				
				
				GameEngine.setMoveLimit(moveCount);
				GameEngine.setLvl(level);
				GameEngine.getGameBoard().setBoardDimXandDimy(boardDimx,
						boardDimy);
				GameEngine.setCurrentScore(currentScore);
				GameEngine.setDesiredScore(desiredScore);
				GameEngine.setRemainingSpecialSwap(specialSwapCount);
				
				if(isTimeBased.equals("true")) {
					GameEngine.setTimeBasedGame(time);
				}
				

				NodeList nls = gameLevel.getElementsByTagName("boardCell");

				int c = 0;
				if(c<nls.getLength()) {
					for (int k = 0; k < boardDimx; k++) {
						for (int j = 0; j < boardDimy; j++) {
							Element boardCell = (Element) nls.item(c);
									
							BoardCell bc = new BoardCell(
									Integer.parseInt(boardCell.getAttribute("dimx")),
									Integer.parseInt(boardCell.getAttribute("dimy")),
									boardCell.getAttribute("cellType"));
							// boardMap[k][j]=bc;
							Element Lokum = (Element) boardCell.getFirstChild();
							Lokum l = new Lokum(Lokum.getAttribute("color"),
									Lokum.getAttribute("type"));
							if(Lokum.getAttribute("isTimerLokum").equals("true")) {
								l.setTimeOfLokum(Integer.parseInt(Lokum.getAttribute("LokumTime")));
							}
							l.setDirection(Lokum.getAttribute("direction"));
							bc.addLokum(l);
							boardMap[k][j] = bc;
							c++;
						}

					}
				}


			}

		}

		GameEngine.getGameBoard().setGameBoardMap(boardMap);
		GameEngine.getPainter().setGameBoardVisible(true);
		GameEngine.getPainter().setGameScreenVisible(true);
		GameEngine.getPainter().setScreenSolution(boardDimx*GameEngine.defaultSizePX, boardDimy*GameEngine.defaultSizePX);
		GameEngine.getPainter().setScreenSolution((GameEngine.defaultSizePX*(GameEngine.getGameBoard().getBoardDimX()+8)),  (GameEngine.defaultSizePX*(8+GameEngine.getGameBoard().getBoardDimy())));
		//	GameEngine.getGameBoard().addCell(tempCell);



	}
	

	public static void loadLevel(int level){
		NodeList nodeList = doc.getElementsByTagName("gameLevel");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element2 = (Element) nodeList.item(i);
			if (element2.getAttribute("id").equals(Integer.toString(level))) {
				NodeList nls=nodeList.item(i).getChildNodes();
				int boardDimX=Integer.parseInt(element2.getAttribute("boardDimX"));
				int boardDimY=Integer.parseInt(element2.getAttribute("boardDimY"));
				int moveLimit=Integer.parseInt(element2.getAttribute("moveLimit"));
				int specialSwapCount=Integer.parseInt(element2.getAttribute("specialSwapCount"));
				String isTimeBased=element2.getAttribute("isTimeBased");
				int time=Integer.parseInt(element2.getAttribute("time"));

				if(isTimeBased.equals("true")) {
					GameEngine.setTimeBasedGame(time);
				}
				
				GameEngine.setMoveLimit(moveLimit);
				GameEngine.getGameBoard().setBoardDimXandDimy(boardDimX,boardDimY);
				GameEngine.setDesiredScore(Integer.parseInt(element2.getAttribute("desiredScore")));
				GameEngine.setRemainingSpecialSwap(specialSwapCount);
				BoardCell tempCell;
				for (int j = 0; j < nls.getLength(); j++) {
					Node nNode=nls.item(j);

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;

						tempCell= new BoardCell(Integer.parseInt(eElement.getAttribute("dimx")),Integer.parseInt(eElement.getAttribute("dimy")),eElement.getAttribute("cellType"));

						GameEngine.setLvl(level);
						GameEngine.getGameBoard().addCell(tempCell);
						



					}
				}
				GameEngine.getPainter().setGameBoardVisible(true);
				GameEngine.getPainter().setGameScreenVisible(true);
				GameEngine.getPainter().setScreenSolution(boardDimX*GameEngine.defaultSizePX, boardDimY*GameEngine.defaultSizePX);
				GameEngine.getPainter().setScreenSolution((GameEngine.defaultSizePX*(GameEngine.getGameBoard().getBoardDimX()+8)),  (GameEngine.defaultSizePX*(8+GameEngine.getGameBoard().getBoardDimy())));
						
				GameEngine.getGameController().checkBoardAndInitialize();

				i=nodeList.getLength();
			}

		}
	}
	public static void UpdateMaxLevel(int maxLevel) throws TransformerException {
		NodeList nodeList = doc.getElementsByTagName("userLevel");
		Element userLevel=(Element)nodeList.item(0);
		
		String id2=userLevel.getAttribute("id");
		userLevel.setAttribute("id", Integer.toString(maxLevel));
		DOMSource source = new DOMSource(doc);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		StreamResult result = new StreamResult("src/gameFiles/sample.xml");
		transformer.transform(source, result);
		Transformer tf = transformerFactory.newTransformer();
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		tf.setOutputProperty(OutputKeys.METHOD, "xml");
		tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			//	nodeList.item(0).setTextContent(Integer.toString(maxLevel));
	}
}
