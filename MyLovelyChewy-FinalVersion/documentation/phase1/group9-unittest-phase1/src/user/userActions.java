package user;

import game.BoardCell;
import game.GameEngine;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class userActions {
private Document doc;
	
	public userActions(){
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

	public Integer getUserLevel(){
	return Integer.parseInt(doc.getElementsByTagName("userLevel").item(0).getTextContent());
	}
	public Integer getMaxLevel(){ 
		NodeList nList = doc.getElementsByTagName("gameLevel");
		return nList.getLength();  
	}
	public ArrayList<String> getSavedGameNames(){ 
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
	public void loadSavedGame(String savedGameName){
		
	}
	
	public void loadLevel(int level){
		NodeList nodeList = doc.getElementsByTagName("gameLevel");
        for (int i = 0; i < nodeList.getLength(); i++) {
        	Element element2 = (Element) nodeList.item(i);
        	if (element2.getAttribute("id").equals(Integer.toString(level))) {
        		NodeList nls=nodeList.item(i).getChildNodes();
        		int boardDimX=Integer.parseInt(element2.getAttribute("boardDimX"));
        		int boardDimY=Integer.parseInt(element2.getAttribute("boardDimY"));
        		GameEngine.getGameBoard().setBoardDimXandDimy(boardDimX,boardDimY);
        	
                for (int j = 0; j < nls.getLength(); j++) {
              	  Node nNode=nls.item(j);
              	  ArrayList<BoardCell> boardCellsTemp = new ArrayList<BoardCell>();
              	  if (nNode.getNodeType() == Node.ELEMENT_NODE) {
              		  
            			Element eElement = (Element) nNode;
            			
//            			System.out.println("Cell id : " + eElement.getAttribute("id"));
//            			System.out.println("Cell dimx : " + eElement.getAttribute("dimx"));
//            			System.out.println("Cell dimy : " + eElement.getAttribute("dimy"));
//            			System.out.println("Cell cellType : " + eElement.getAttribute("cellType"));
//            			System.out.println("Cell content : " + eElement.getTextContent());
            			BoardCell tempCell= new BoardCell(Integer.parseInt(eElement.getAttribute("dimx")),Integer.parseInt(eElement.getAttribute("dimy")),eElement.getAttribute("cellType"));
            			boardCellsTemp.add(tempCell);
            			GameEngine.setLvl(level);
            			GameEngine.getGameBoard().addCells(boardCellsTemp);
//            			System.out.println("dsfsdx:"+ GameEngine.getGameBoard().boardMap.length);
//            			for (int k = 0; k < GameEngine.getGameBoard().getBoardDimX(); k++) {
//							for (int k2 = 0; k2 < GameEngine.getGameBoard().getBoardDimy(); k2++) {
//								System.out.println(GameEngine.getGameBoard().boardMap[k][k2].toString());
//							}
//						}
            			GameEngine.getPainter().setGameBoardVisible(true);
            			GameEngine.getPainter().setGameScreenVisible(true);
            			
            			
            		}
			}
          
          }
        	i=nodeList.getLength();
        }
	}
}
