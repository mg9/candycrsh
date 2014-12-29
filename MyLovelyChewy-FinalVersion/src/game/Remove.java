package game;

public class Remove {
	private static Remove instance = new Remove();
	private static BoardCell[][] localBoardMap = new BoardCell[0][0];
	private static String removeType;

	private Remove() {

	}

	public static Remove getInstance() {
		return instance;
	}

	public static void removeFromTwoPair(BoardCell[][] map, BoardCell cell1,
			BoardCell cell2) {
		localBoardMap = map;

		// ColorBomb vs ColorBomb
		if (cell1.getCellLokum().type.equals("ColorBomb")
				&& cell2.getCellLokum().type.equals("ColorBomb")) {
			if (!(cell1.getCellX() == cell2.getCellX() && cell1.getCellY() == cell2
					.getCellY())) {

				removeColorBombvsColorBomb(cell1, cell2);
				GameEngine.getGameBoard().setGameBoardMap(localBoardMap);
				GameEngine.getGameController().checkAndFill();
				localBoardMap = GameEngine.getGameBoard().getBoardMap();
			}
		}

		// Wrapped vs Wrapped
		if (cell1.getCellLokum().type.equals("Wrapped")
				&& cell2.getCellLokum().type.equals("Wrapped")) {
			if (!(cell1.getCellX() == cell2.getCellX() && cell1.getCellY() == cell2
					.getCellY())) {

				removeWrappedvsWrapped(cell1, cell2);
				GameEngine.getGameBoard().setGameBoardMap(localBoardMap);
				GameEngine.getGameController().checkAndFill();
				localBoardMap = GameEngine.getGameBoard().getBoardMap();
			}
		}

		// Striped vs Striped
		if (cell1.getCellLokum().type.equals("Striped")
				&& cell2.getCellLokum().type.equals("Striped")) {
			if (!(cell1.getCellX() == cell2.getCellX() && cell1.getCellY() == cell2
					.getCellY())) {

				removeStripedvsStriped(cell1, cell2);
				GameEngine.getGameBoard().setGameBoardMap(localBoardMap);
				GameEngine.getGameController().checkAndFill();
				localBoardMap = GameEngine.getGameBoard().getBoardMap();
			}
		}

		// Striped vs Wrapped
		if (cell1.getCellLokum().type.equals("Striped")
				&& cell2.getCellLokum().type.equals("Wrapped")) {
			removeStripedvsWrapped(cell1, cell2);
			GameEngine.getGameBoard().setGameBoardMap(localBoardMap);
			GameEngine.getGameController().checkAndFill();
			localBoardMap = GameEngine.getGameBoard().getBoardMap();
		}

		if (cell1.getCellLokum().type.equals("Wrapped")
				&& cell2.getCellLokum().type.equals("Striped")) {
			removeStripedvsWrapped(cell2, cell1);
			GameEngine.getGameBoard().setGameBoardMap(localBoardMap);
			GameEngine.getGameController().checkAndFill();
			localBoardMap = GameEngine.getGameBoard().getBoardMap();
		}

		// Striped vs ColorBomb
		if (cell1.getCellLokum().type.equals("Striped")
				&& cell2.getCellLokum().type.equals("ColorBomb")) {
			removeStripedvsColorBomb(cell1, cell2);
			GameEngine.getGameBoard().setGameBoardMap(localBoardMap);
			GameEngine.getGameController().checkAndFill();
			localBoardMap = GameEngine.getGameBoard().getBoardMap();
		}

		if (cell1.getCellLokum().type.equals("ColorBomb")
				&& cell2.getCellLokum().type.equals("Striped")) {
			removeStripedvsColorBomb(cell2, cell1);
			GameEngine.getGameBoard().setGameBoardMap(localBoardMap);
			GameEngine.getGameController().checkAndFill();
			localBoardMap = GameEngine.getGameBoard().getBoardMap();
		}

		// Standard vs ColorBomb
		if (cell1.getCellLokum().type.equals("standart")
				&& cell2.getCellLokum().type.equals("ColorBomb")) {
			removeColorBombvsStandard(cell2, cell1);
			GameEngine.getGameBoard().setGameBoardMap(localBoardMap);
			GameEngine.getGameController().checkAndFill();
			localBoardMap = GameEngine.getGameBoard().getBoardMap();
		}

		if (cell1.getCellLokum().type.equals("ColorBomb")
				&& cell2.getCellLokum().type.equals("standart")) {
			removeColorBombvsStandard(cell1, cell2);
			GameEngine.getGameBoard().setGameBoardMap(localBoardMap);
			GameEngine.getGameController().checkAndFill();
			localBoardMap = GameEngine.getGameBoard().getBoardMap();
		}

		// Wrapped vs ColorBomb
		if (cell1.getCellLokum().type.equals("Wrapped")
				&& cell2.getCellLokum().type.equals("ColorBomb")) {
			removeWrappedvsColorBomb(cell1, cell2);
			GameEngine.getGameBoard().setGameBoardMap(localBoardMap);
			GameEngine.getGameController().checkAndFill();
			localBoardMap = GameEngine.getGameBoard().getBoardMap();
		}

		if (cell1.getCellLokum().type.equals("ColorBomb")
				&& cell2.getCellLokum().type.equals("Wrapped")) {
			removeWrappedvsColorBomb(cell2, cell1);
			GameEngine.getGameBoard().setGameBoardMap(localBoardMap);
			GameEngine.getGameController().checkAndFill();
			localBoardMap = GameEngine.getGameBoard().getBoardMap();
		}

		// Standart vs Striped or Wrapped

		if ((cell1.getCellLokum().type.equals("Striped") && cell2
				.getCellLokum().type.equals("standart"))
				|| (cell1.getCellLokum().type.equals("standart") && cell2
						.getCellLokum().type.equals("Striped"))
				|| (cell1.getCellLokum().type.equals("Wrapped") && cell2
						.getCellLokum().type.equals("standart"))
				|| (cell1.getCellLokum().type.equals("standart") && cell2
						.getCellLokum().type.equals("Wrapped"))
				|| (cell1.getCellLokum().type.equals("standart") && cell2
						.getCellLokum().type.equals("standart"))) {
			removeforSingleCell(cell1);
			removeforSingleCell(cell2);
			GameEngine.getGameBoard().setGameBoardMap(localBoardMap);
			GameEngine.getGameController().checkAndFill();
			localBoardMap = GameEngine.getGameBoard().getBoardMap();
			Boolean helperBool = false;
			Boolean whileBool = true;
			while (whileBool) {
				helperBool = false;
				for (int i = 0; i < GameEngine.getGameBoard().getBoardDimX(); i++) {
					for (int j = 0; j < GameEngine.getGameBoard()
							.getBoardDimy(); j++) {
						if (removeforSingleCell(localBoardMap[i][j])) {
							helperBool = true;
							GameEngine.getGameBoard();
							Board.setGameBoardMap(localBoardMap);
							GameEngine.getGameController();
							Controller.checkAndFill();
							localBoardMap = GameEngine.getGameBoard()
									.getBoardMap();
						}
					}
				}

				if (helperBool) {
					whileBool = true;
				} else {
					whileBool = false;
				}
			}

		}

	}

	private static Boolean removeforSingleCell(BoardCell cell) {
		Boolean result = true;
		Boolean forFive = removeFive(cell);

		if (!forFive) {
			Boolean forl = removeL(cell);
			if (!forl) {
				Boolean forT = removeT(cell);
				if (!forT) {
					Boolean forFourh = removeFourHorizontal(cell);
					if (!forFourh) {
						Boolean forFourv = removeFourVertical(cell);
						if (!forFourv) {
							Boolean forThreeh = removeThreeHorizontal(cell);
							if (!forThreeh) {
								Boolean forThreev = removeThreeVertical(cell);
								if (!forThreev) {
									result = false;
								}
							}
						}
					}
				}
			}
		}
		return result;

	}

	private static int absoluteValue(int x) {
		int result = 0;
		if (x < 0) {
			result = x * (-1);
		} else {
			result = x;
		}
		return result;
	}

	private static void removeColorBombvsColorBomb(BoardCell cell1,
			BoardCell cell2) {

		for (int i = 0; i < GameEngine.getGameBoard().getBoardDimX(); i++) {
			for (int j = 0; j < GameEngine.getGameBoard().getBoardDimy(); j++) {
				removeAccordingtoLokumType(localBoardMap[i][j]);
			}
			GameEngine.increaseScore(GameEngine.getGameBoard().getBoardDimX()
					* GameEngine.getGameBoard().getBoardDimy()
					* GameEngine.getGameBoard().getBoardDimX()
					* GameEngine.getGameBoard().getBoardDimy() * 100);

		}
	}

	private static void removeStripedvsStriped(BoardCell cell1, BoardCell cell2) {

		for (int i = 0; i < GameEngine.getGameBoard().getBoardDimX(); i++) {
			removeAccordingtoLokumType(localBoardMap[i][cell2.getCellY()]);
		}
		for (int j = 0; j < GameEngine.getGameBoard().getBoardDimy(); j++) {
			removeAccordingtoLokumType(localBoardMap[cell2.getCellX()][j]);
		}
		localBoardMap[cell1.getCellX()][cell1.getCellY()].removeLokum();
		localBoardMap[cell2.getCellX()][cell2.getCellY()].removeLokum();
		GameEngine.increaseScore(2 * 120);
	}

	private static void removeStripedvsWrapped(BoardCell striped,
			BoardCell wrapped) {

		int x = striped.getCellX();
		int y = striped.getCellY();

		removeSingleStriped(striped);
		removeSingleWrapped(wrapped);

		// for (int i = 0; i < GameEngine.getGameBoard().getBoardDimX(); i++) {
		// for (int j = -1; j < 2; j++) {
		// if (!(0 <= striped.getCellY() + j && striped.getCellY() + j <
		// GameEngine
		// .getGameBoard().getBoardDimy())) {
		// continue;
		// } else {
		// removeAccordingtoLokumType(localBoardMap[i][striped
		// .getCellY() + j]);
		//
		// }
		// }
		// }
		// for (int j = 0; j < GameEngine.getGameBoard().getBoardDimy(); j++) {
		// for (int i = -1; i < 2; i++) {
		// if (!(0 <= striped.getCellX() + i && striped.getCellX() + i <
		// GameEngine
		// .getGameBoard().getBoardDimX())) {
		// continue;
		// } else {
		// removeAccordingtoLokumType(localBoardMap[striped.getCellX()
		// + i][j]);
		// }
		// }
		//
		// GameEngine.increaseScore(360);
		// }
	}

	private static void removeColorBombvsStandard(BoardCell colorBomb,
			BoardCell standard) {
		int counter = 0;

		if (colorBomb != null && standard != null && colorBomb.hasLokum()
				&& standard.hasLokum()) {
			String standardColor = standard.getCellLokum().color;

			for (int i = 0; i < GameEngine.getGameBoard().getBoardDimX(); i++) {
				for (int j = 0; j < GameEngine.getGameBoard().getBoardDimy(); j++) {
					if (localBoardMap[i][j] != null
							&& localBoardMap[i][j].hasLokum()
							&& localBoardMap[i][j].getCellLokum().color
									.equals(standardColor)) {
						// localBoardMap[i][j].removeLokum();
						removeAccordingtoLokumType(localBoardMap[i][j]);
						counter++;
					}
				}

			}
			localBoardMap[colorBomb.getCellX()][colorBomb.getCellY()]
					.removeLokum();
			GameEngine.increaseScore(counter * counter * 2);

		}

	}

	private static void removeWrappedvsWrapped(BoardCell cell1, BoardCell cell2) {

		removeSingleWrapped(cell1);
		removeSingleWrapped(cell2);
		GameEngine.increaseScore(3600);

	}

	private static void removeStripedvsColorBomb(BoardCell stripedCell,
			BoardCell colorBombcell) {
		int n = 0;
		if (stripedCell.getCellLokum().type.equals("horizontal")) {
			n = GameEngine.getGameBoard().getBoardDimX();
		} else if (stripedCell.getCellLokum().type.equals("vertical")) {
			n = GameEngine.getGameBoard().getBoardDimy();
		}

		String stripcolor = stripedCell.getCellLokum().color;
		for (int i = 0; i < GameEngine.getGameBoard().getBoardDimX(); i++) {
			for (int j = 0; j < GameEngine.getGameBoard().getBoardDimy(); j++) {
				if (localBoardMap[i][j].getCellLokum().color.equals(stripcolor)) {

					// removeAccordingtoLokumType(localBoardMap[i][j]);
					localBoardMap[i][j].removeLokum();
					localBoardMap[i][j].addLokum(new Lokum(stripcolor,
							"Striped"));
				}

			}
		}
		for (int i = 0; i < GameEngine.getGameBoard().getBoardDimX(); i++) {
			for (int j = 0; j < GameEngine.getGameBoard().getBoardDimy(); j++) {
				if (localBoardMap[i][j].hasLokum()
						&& localBoardMap[i][j] != null) {

					if ((localBoardMap[i][j].getCellLokum().type
							.equals("Striped"))
							&& (localBoardMap[i][j].getCellLokum().color
									.equals(stripcolor))) {

						removeAccordingtoLokumType(localBoardMap[i][j]);

					}
				}

			}
		}
		GameEngine.increaseScore(n * 120);
		localBoardMap[stripedCell.getCellX()][stripedCell.getCellY()]
				.removeLokum();
		localBoardMap[colorBombcell.getCellX()][colorBombcell.getCellY()]
				.removeLokum();
	}

	private static void removeWrappedvsColorBomb(BoardCell wrapped,
			BoardCell colorBomb) {

		String wrapcolor = wrapped.getCellLokum().color;
		for (int i = 0; i < GameEngine.getGameBoard().getBoardDimX(); i++) {
			for (int j = 0; j < GameEngine.getGameBoard().getBoardDimy(); j++) {
				if (localBoardMap[i][j].getCellLokum().color.equals(wrapcolor)) {
					localBoardMap[i][j].removeLokum();
					localBoardMap[i][j]
							.addLokum(new Lokum(wrapcolor, "Wrapped"));

					// removeAccordingtoLokumType(localBoardMap[i][j]);
				}

			}
		}
		for (int i = 0; i < GameEngine.getGameBoard().getBoardDimX(); i++) {
			for (int j = 0; j < GameEngine.getGameBoard().getBoardDimy(); j++) {
				if (localBoardMap[i][j].getCellLokum() != null
						&& localBoardMap[i][j].getCellLokum().color
								.equals(wrapcolor)
						&& localBoardMap[i][j].getCellLokum().type
								.equals("Wrapped")) {

					removeAccordingtoLokumType(localBoardMap[i][j]);
				}

			}
		}

		GameEngine.increaseScore(2 * 200);
		localBoardMap[wrapped.getCellX()][wrapped.getCellY()].removeLokum();
		localBoardMap[colorBomb.getCellX()][colorBomb.getCellY()].removeLokum();
	}

	private static boolean removeThreeVertical(BoardCell cell) {

		boolean canRemoveThreeVertical = false;

		if (cell != null && cell.canTakeLokum() && cell.hasLokum()) {
			String cellColor = cell.getCellLokum().color;
			int x = cell.getCellX();
			int y = cell.getCellY();

			// en ustte olma case'i
			if (y + 2 < GameEngine.getGameBoard().getBoardDimy()
					&& localBoardMap[x][y + 1] != null
					&& localBoardMap[x][y + 1].hasLokum()
					&& localBoardMap[x][y + 2] != null
					&& localBoardMap[x][y + 2].hasLokum()
					&& localBoardMap[x][y + 1].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y + 2].getCellLokum().color
							.equals(cellColor)) { // cell en ustte 3lu yakaladi.

				removeAccordingtoLokumType(localBoardMap[x][y]);// .removeLokum();
				removeAccordingtoLokumType(localBoardMap[x][y + 1]);// removeLokum();
				removeAccordingtoLokumType(localBoardMap[x][y + 2]);// .removeLokum();
				canRemoveThreeVertical = true;
			} else if (y - 2 >= 0
					&& localBoardMap[x][y - 1] != null
					&& localBoardMap[x][y - 1].hasLokum()
					&& localBoardMap[x][y - 2] != null
					&& localBoardMap[x][y - 2].hasLokum()
					&& localBoardMap[x][y - 1].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y - 2].getCellLokum().color
							.equals(cellColor)) { // cell en allta 3lu yakaladi.

				removeAccordingtoLokumType(localBoardMap[x][y - 2]);// .removeLokum();
				removeAccordingtoLokumType(localBoardMap[x][y - 1]);// .removeLokum();
				removeAccordingtoLokumType(localBoardMap[x][y]);// .removeLokum();
				canRemoveThreeVertical = true;
			} else if (y - 1 >= 0
					&& y + 1 < GameEngine.getGameBoard().getBoardDimy()
					&& localBoardMap[x][y - 1] != null
					&& localBoardMap[x][y - 1].hasLokum()
					&& localBoardMap[x][y + 1] != null
					&& localBoardMap[x][y + 1].hasLokum()
					&& localBoardMap[x][y - 1].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y + 1].getCellLokum().color
							.equals(cellColor)) { // cell ortada 3lu yakaladi.

				localBoardMap[x][y - 1].removeLokum();
				localBoardMap[x][y].removeLokum();
				localBoardMap[x][y + 1].removeLokum();
				canRemoveThreeVertical = true;
			}
		}
		if (canRemoveThreeVertical) {
			GameEngine.increaseScore(60);
		}
		return canRemoveThreeVertical;

	}

	private static boolean removeThreeHorizontal(BoardCell cell) {

		boolean canRemoveThreeHorizontal = false;

		if (cell != null && cell.canTakeLokum() && cell.hasLokum()) {
			String cellColor = cell.getCellLokum().color;
			int x = cell.getCellX();
			int y = cell.getCellY();

			if (x + 2 < GameEngine.getGameBoard().getBoardDimX()
					&& localBoardMap[x + 1][y] != null
					&& localBoardMap[x + 1][y].hasLokum()
					&& localBoardMap[x + 2][y] != null
					&& localBoardMap[x + 2][y].hasLokum()
					&& localBoardMap[x + 1][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x + 2][y].getCellLokum().color
							.equals(cellColor)) { // cell en solda, 3lu
				// yakaladi.

				removeAccordingtoLokumType(localBoardMap[x + 2][y]);
				removeAccordingtoLokumType(localBoardMap[x + 1][y]);
				removeAccordingtoLokumType(localBoardMap[x][y]);
				canRemoveThreeHorizontal = true;
			} else if (x - 2 >= 0
					&& localBoardMap[x - 1][y] != null
					&& localBoardMap[x - 1][y].hasLokum()
					&& localBoardMap[x - 2][y] != null
					&& localBoardMap[x - 2][y].hasLokum()
					&& localBoardMap[x - 1][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x - 2][y].getCellLokum().color
							.equals(cellColor)) { // cell en sagda 3lu yakaladi.

				removeAccordingtoLokumType(localBoardMap[x - 2][y]);
				removeAccordingtoLokumType(localBoardMap[x - 1][y]);
				removeAccordingtoLokumType(localBoardMap[x][y]);

				canRemoveThreeHorizontal = true;
			} else if (x - 1 >= 0
					&& x + 1 < GameEngine.getGameBoard().getBoardDimX()
					&& localBoardMap[x - 1][y] != null
					&& localBoardMap[x - 1][y].hasLokum()
					&& localBoardMap[x + 1][y] != null
					&& localBoardMap[x + 1][y].hasLokum()
					&& localBoardMap[x - 1][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x + 1][y].getCellLokum().color
							.equals(cellColor)) { // cell ortada 3lu yakaladi.

				removeAccordingtoLokumType(localBoardMap[x - 1][y]);
				removeAccordingtoLokumType(localBoardMap[x][y]);
				removeAccordingtoLokumType(localBoardMap[x + 1][y]);
				canRemoveThreeHorizontal = true;
			}
		}
		if (canRemoveThreeHorizontal) {
			GameEngine.increaseScore(60);
		}
		return canRemoveThreeHorizontal;

	}

	private static boolean removeFourVertical(BoardCell cell) {
		boolean canRemoveFourVertical = false;

		if (cell != null && cell.canTakeLokum() && cell.hasLokum()) {
			String cellColor = cell.getCellLokum().color;
			int x = cell.getCellX();
			int y = cell.getCellY();

			// en ustte olma case'i
			if (y - 1 >= 0
					&& y + 2 < GameEngine.getGameBoard().getBoardDimy()
					&& localBoardMap[x][y - 1] != null
					&& localBoardMap[x][y - 1].hasLokum()
					&& localBoardMap[x][y + 1] != null
					&& localBoardMap[x][y + 1].hasLokum()
					&& localBoardMap[x][y + 2] != null
					&& localBoardMap[x][y + 2].hasLokum()
					&& localBoardMap[x][y - 1].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y + 1].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y + 2].getCellLokum().color
							.equals(cellColor)) { // cell en ustte 3lu yakaladi.

				removeAccordingtoLokumType(localBoardMap[x][y - 1]);
				removeAccordingtoLokumType(localBoardMap[x][y]);
				removeAccordingtoLokumType(localBoardMap[x][y + 1]);
				removeAccordingtoLokumType(localBoardMap[x][y + 2]);
				Lokum temp = new Lokum(cellColor, "Striped");
				temp.setDirection("horizontal");
				localBoardMap[x][y].addLokum(temp);
				canRemoveFourVertical = true;
			}

			else if (y - 2 >= 0
					&& y + 1 < GameEngine.getGameBoard().getBoardDimy()
					&& localBoardMap[x][y - 2] != null
					&& localBoardMap[x][y - 2].hasLokum()
					&& localBoardMap[x][y - 1] != null
					&& localBoardMap[x][y - 1].hasLokum()
					&& localBoardMap[x][y + 1] != null
					&& localBoardMap[x][y + 1].hasLokum()
					&& localBoardMap[x][y - 2].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y - 1].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y + 1].getCellLokum().color
							.equals(cellColor)) { // cell ortada 3lu yakaladi.

				removeAccordingtoLokumType(localBoardMap[x][y - 2]);
				removeAccordingtoLokumType(localBoardMap[x][y - 1]);
				removeAccordingtoLokumType(localBoardMap[x][y]);
				removeAccordingtoLokumType(localBoardMap[x][y + 1]);
				Lokum temp2 = new Lokum(cellColor, "Striped");
				temp2.setDirection("horizontal");
				localBoardMap[x][y].addLokum(temp2);
				canRemoveFourVertical = true;
			}
		}
		if (canRemoveFourVertical) {
			GameEngine.increaseScore(120);
		}
		return canRemoveFourVertical;

	}

	private static boolean removeFourHorizontal(BoardCell cell) {
		boolean canRemoveFourHorizontal = false;

		if (cell != null && cell.canTakeLokum() && cell.hasLokum()) {
			String cellColor = cell.getCellLokum().color;
			int x = cell.getCellX();
			int y = cell.getCellY();

			if (x - 1 >= 0
					&& x + 2 < GameEngine.getGameBoard().getBoardDimX()
					&& localBoardMap[x - 1][y] != null
					&& localBoardMap[x - 1][y].hasLokum()
					&& localBoardMap[x + 1][y] != null
					&& localBoardMap[x + 1][y].hasLokum()
					&& localBoardMap[x + 2][y] != null
					&& localBoardMap[x + 2][y].hasLokum()
					&& localBoardMap[x - 1][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x + 1][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x + 2][y].getCellLokum().color
							.equals(cellColor)) {

				removeAccordingtoLokumType(localBoardMap[x + 2][y]);
				removeAccordingtoLokumType(localBoardMap[x + 1][y]);
				removeAccordingtoLokumType(localBoardMap[x][y]);
				removeAccordingtoLokumType(localBoardMap[x - 1][y]);
				Lokum temp = new Lokum(cellColor, "Striped");
				temp.setDirection("vertical");
				localBoardMap[x][y].addLokum(temp);
				canRemoveFourHorizontal = true;
			}

			else if (x - 2 >= 0
					&& x + 1 < GameEngine.getGameBoard().getBoardDimX()
					&& localBoardMap[x - 2][y] != null
					&& localBoardMap[x - 2][y].hasLokum()
					&& localBoardMap[x - 1][y] != null
					&& localBoardMap[x - 1][y].hasLokum()
					&& localBoardMap[x + 1][y] != null
					&& localBoardMap[x + 1][y].hasLokum()
					&& localBoardMap[x - 2][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x - 1][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x + 1][y].getCellLokum().color
							.equals(cellColor)) {

				removeAccordingtoLokumType(localBoardMap[x - 2][y]);
				removeAccordingtoLokumType(localBoardMap[x - 1][y]);
				removeAccordingtoLokumType(localBoardMap[x][y]);
				removeAccordingtoLokumType(localBoardMap[x + 1][y]);
				Lokum temp2 = new Lokum(cellColor, "Striped");
				temp2.setDirection("vertical");
				localBoardMap[x][y].addLokum(temp2);

				canRemoveFourHorizontal = true;
			}
		}
		if (canRemoveFourHorizontal) {
			GameEngine.increaseScore(120);
		}

		return canRemoveFourHorizontal;

	}

	private static boolean removeFive(BoardCell cell) {
		boolean canRemoveFive = false;

		if (cell != null && cell.canTakeLokum() && cell.hasLokum()) {
			String cellColor = cell.getCellLokum().color;
			int x = cell.getCellX();
			int y = cell.getCellY();
			if (y - 2 >= 0
					&& y + 2 < GameEngine.getGameBoard().getBoardDimy()
					&& localBoardMap[x][y - 2] != null
					&& localBoardMap[x][y - 2].hasLokum()
					&& localBoardMap[x][y - 1] != null
					&& localBoardMap[x][y - 1].hasLokum()
					&& localBoardMap[x][y + 1] != null
					&& localBoardMap[x][y + 1].hasLokum()
					&& localBoardMap[x][y + 2] != null
					&& localBoardMap[x][y + 2].hasLokum()
					&& localBoardMap[x][y - 2].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y - 1].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y + 2].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y + 1].getCellLokum().color
							.equals(cellColor)) { // cell ortada 3lu yakaladi.

				removeAccordingtoLokumType(localBoardMap[x][y - 2]);
				removeAccordingtoLokumType(localBoardMap[x][y - 1]);
				removeAccordingtoLokumType(localBoardMap[x][y]);
				removeAccordingtoLokumType(localBoardMap[x][y + 1]);
				removeAccordingtoLokumType(localBoardMap[x][y + 2]);
				localBoardMap[x][y]
						.addLokum(new Lokum("ColorBomb", "ColorBomb"));
				canRemoveFive = true;
			} else if (x - 2 >= 0
					&& x + 2 < GameEngine.getGameBoard().getBoardDimX()
					&& localBoardMap[x - 2][y] != null
					&& localBoardMap[x - 2][y].hasLokum()
					&& localBoardMap[x - 1][y] != null
					&& localBoardMap[x - 1][y].hasLokum()
					&& localBoardMap[x + 1][y] != null
					&& localBoardMap[x + 1][y].hasLokum()
					&& localBoardMap[x + 2][y] != null
					&& localBoardMap[x + 2][y].hasLokum()
					&& localBoardMap[x - 2][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x - 1][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x + 1][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x + 2][y].getCellLokum().color
							.equals(cellColor)) {

				removeAccordingtoLokumType(localBoardMap[x - 2][y]);
				removeAccordingtoLokumType(localBoardMap[x - 1][y]);
				removeAccordingtoLokumType(localBoardMap[x][y]);
				removeAccordingtoLokumType(localBoardMap[x + 1][y]);
				removeAccordingtoLokumType(localBoardMap[x + 2][y]);
				localBoardMap[x][y]
						.addLokum(new Lokum("ColorBomb", "ColorBomb"));

				canRemoveFive = true;
			}
		}
		if (canRemoveFive) {
			GameEngine.increaseScore(200);
		}

		return canRemoveFive;

	}

	private static boolean removeL(BoardCell cell) {

		boolean canRemoveL = false;

		if (cell != null && cell.canTakeLokum() && cell.hasLokum()) {
			String cellColor = cell.getCellLokum().color;
			int x = cell.getCellX();
			int y = cell.getCellY();

			// Duz L'ye girme case'i

			if (y - 2 >= 0
					&& x + 2 < GameEngine.getGameBoard().getBoardDimX()
					&& localBoardMap[x][y - 2] != null
					&& localBoardMap[x][y - 2].hasLokum()
					&& localBoardMap[x][y - 1] != null
					&& localBoardMap[x][y - 1].hasLokum()
					&& localBoardMap[x + 1][y] != null
					&& localBoardMap[x + 1][y].hasLokum()
					&& localBoardMap[x + 2][y] != null
					&& localBoardMap[x + 2][y].hasLokum()
					&&

					localBoardMap[x][y - 2].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y - 1].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x + 1][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x + 2][y].getCellLokum().color
							.equals(cellColor)) { // cell duz L'ye girdi.

				removeAccordingtoLokumType(localBoardMap[x][y - 2]);
				removeAccordingtoLokumType(localBoardMap[x][y - 1]);
				removeAccordingtoLokumType(localBoardMap[x + 1][y]);
				removeAccordingtoLokumType(localBoardMap[x + 2][y]);
				removeAccordingtoLokumType(localBoardMap[x][y]);

				localBoardMap[x][y].addLokum(new Lokum(cellColor, "Wrapped"));
				canRemoveL = true;
			}

			else if (x + 2 < GameEngine.getGameBoard().getBoardDimX()
					&& y + 2 < GameEngine.getGameBoard().getBoardDimy()
					&& localBoardMap[x + 1][y] != null
					&& localBoardMap[x + 1][y].hasLokum()
					&& localBoardMap[x + 2][y] != null
					&& localBoardMap[x + 2][y].hasLokum()
					&& localBoardMap[x][y + 1] != null
					&& localBoardMap[x][y + 1].hasLokum()
					&& localBoardMap[x][y + 2] != null
					&& localBoardMap[x][y + 2].hasLokum()
					&&

					localBoardMap[x + 1][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x + 2][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y + 1].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y + 2].getCellLokum().color
							.equals(cellColor)) { // cell saga 90derece dondu.

				removeAccordingtoLokumType(localBoardMap[x + 2][y]);
				removeAccordingtoLokumType(localBoardMap[x + 1][y]);
				removeAccordingtoLokumType(localBoardMap[x][y]);
				removeAccordingtoLokumType(localBoardMap[x][y + 1]);
				removeAccordingtoLokumType(localBoardMap[x][y + 2]);

				localBoardMap[x][y].addLokum(new Lokum(cellColor, "Wrapped"));
				canRemoveL = true;
			}

			else if (x - 2 >= 0
					&& y + 2 < GameEngine.getGameBoard().getBoardDimy()
					&& localBoardMap[x - 2][y] != null
					&& localBoardMap[x - 2][y].hasLokum()
					&& localBoardMap[x - 1][y] != null
					&& localBoardMap[x - 1][y].hasLokum()
					&& localBoardMap[x][y + 1] != null
					&& localBoardMap[x][y + 1].hasLokum()
					&& localBoardMap[x][y + 2] != null
					&& localBoardMap[x][y + 2].hasLokum()
					&&

					localBoardMap[x - 2][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x - 1][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y + 1].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y + 2].getCellLokum().color
							.equals(cellColor)) { // cell alttan T'ye girdi.

				removeAccordingtoLokumType(localBoardMap[x - 2][y]);
				removeAccordingtoLokumType(localBoardMap[x - 1][y]);
				removeAccordingtoLokumType(localBoardMap[x][y + 1]);
				removeAccordingtoLokumType(localBoardMap[x][y + 2]);
				removeAccordingtoLokumType(localBoardMap[x][y]);

				localBoardMap[x][y].addLokum(new Lokum(cellColor, "Wrapped"));
				canRemoveL = true;
			} else if (y - 2 >= 0
					&& x - 2 >= 0
					&& localBoardMap[x - 2][y] != null
					&& localBoardMap[x - 2][y].hasLokum()
					&& localBoardMap[x - 1][y] != null
					&& localBoardMap[x - 1][y].hasLokum()
					&& localBoardMap[x][y - 2] != null
					&& localBoardMap[x][y - 2].hasLokum()
					&& localBoardMap[x][y - 1] != null
					&& localBoardMap[x][y - 1].hasLokum()
					&&

					localBoardMap[x - 2][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x - 1][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y - 2].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y - 1].getCellLokum().color
							.equals(cellColor)) { // cell alttan T'ye girdi.

				removeAccordingtoLokumType(localBoardMap[x - 2][y]);
				removeAccordingtoLokumType(localBoardMap[x - 1][y]);
				removeAccordingtoLokumType(localBoardMap[x][y - 2]);
				removeAccordingtoLokumType(localBoardMap[x][y - 1]);
				removeAccordingtoLokumType(localBoardMap[x][y]);

				localBoardMap[x][y].addLokum(new Lokum(cellColor, "Wrapped"));
				canRemoveL = true;
			}
		}
		if (canRemoveL) {
			GameEngine.increaseScore(200);
		}

		return canRemoveL;

	}

	private static boolean removeT(BoardCell cell) {
		boolean canRemoveT = false;

		if (cell != null && cell.canTakeLokum() && cell.hasLokum()) {
			String cellColor = cell.getCellLokum().color;
			int x = cell.getCellX();
			int y = cell.getCellY();

			// Duz T'ye girme case'i

			if (x - 1 >= 0
					&& x + 1 < GameEngine.getGameBoard().getBoardDimX()
					&& y + 2 < GameEngine.getGameBoard().getBoardDimy()
					&& localBoardMap[x - 1][y] != null
					&& localBoardMap[x - 1][y].hasLokum()
					&& localBoardMap[x + 1][y] != null
					&& localBoardMap[x + 1][y].hasLokum()
					&& localBoardMap[x][y + 1] != null
					&& localBoardMap[x][y + 1].hasLokum()
					&& localBoardMap[x][y + 2] != null
					&& localBoardMap[x][y + 2].hasLokum()
					&&

					localBoardMap[x - 1][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x + 1][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y + 1].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y + 2].getCellLokum().color
							.equals(cellColor)) { // cell duz T'ye girdi.

				removeAccordingtoLokumType(localBoardMap[x - 1][y]);
				removeAccordingtoLokumType(localBoardMap[x + 1][y]);
				removeAccordingtoLokumType(localBoardMap[x][y]);
				removeAccordingtoLokumType(localBoardMap[x][y + 1]);
				removeAccordingtoLokumType(localBoardMap[x][y + 2]);

				localBoardMap[x][y].addLokum(new Lokum(cellColor, "Wrapped"));
				canRemoveT = true;
			}

			else if (x - 2 >= 0
					&& y + 1 < GameEngine.getGameBoard().getBoardDimy()
					&& y - 1 >= 0
					&& localBoardMap[x - 2][y] != null
					&& localBoardMap[x - 2][y].hasLokum()
					&& localBoardMap[x - 1][y] != null
					&& localBoardMap[x - 1][y].hasLokum()
					&& localBoardMap[x][y - 1] != null
					&& localBoardMap[x][y - 1].hasLokum()
					&& localBoardMap[x][y + 1] != null
					&& localBoardMap[x][y + 1].hasLokum()
					&&

					localBoardMap[x - 2][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x - 1][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y - 1].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y + 1].getCellLokum().color
							.equals(cellColor)) { // cell sol yatik T'ye girdi.

				removeAccordingtoLokumType(localBoardMap[x - 2][y]);
				removeAccordingtoLokumType(localBoardMap[x - 1][y]);
				removeAccordingtoLokumType(localBoardMap[x][y]);
				removeAccordingtoLokumType(localBoardMap[x][y - 1]);
				removeAccordingtoLokumType(localBoardMap[x][y + 1]);

				localBoardMap[x][y].addLokum(new Lokum(cellColor, "Wrapped"));
				canRemoveT = true;
			}

			else if (x - 1 >= 0
					&& y - 2 >= 0
					&& x + 1 < GameEngine.getGameBoard().getBoardDimX()
					&& localBoardMap[x - 1][y] != null
					&& localBoardMap[x - 1][y].hasLokum()
					&& localBoardMap[x + 1][y] != null
					&& localBoardMap[x + 1][y].hasLokum()
					&& localBoardMap[x][y - 2] != null
					&& localBoardMap[x][y - 2].hasLokum()
					&& localBoardMap[x][y - 1] != null
					&& localBoardMap[x][y - 1].hasLokum()
					&&

					localBoardMap[x - 1][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x + 1][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y - 2].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y - 1].getCellLokum().color
							.equals(cellColor)) { // cell alttan T'ye girdi.

				removeAccordingtoLokumType(localBoardMap[x - 1][y]);
				removeAccordingtoLokumType(localBoardMap[x + 1][y]);
				removeAccordingtoLokumType(localBoardMap[x][y - 2]);
				removeAccordingtoLokumType(localBoardMap[x][y - 1]);
				removeAccordingtoLokumType(localBoardMap[x][y]);

				localBoardMap[x][y].addLokum(new Lokum(cellColor, "Wrapped"));
				canRemoveT = true;
			} else if (y - 1 >= 0
					&& x + 2 < GameEngine.getGameBoard().getBoardDimX()
					&& y + 1 < GameEngine.getGameBoard().getBoardDimy()
					&& localBoardMap[x][y - 1] != null
					&& localBoardMap[x][y - 1].hasLokum()
					&& localBoardMap[x][y + 1] != null
					&& localBoardMap[x][y + 1].hasLokum()
					&& localBoardMap[x + 1][y] != null
					&& localBoardMap[x + 1][y].hasLokum()
					&& localBoardMap[x + 2][y] != null
					&& localBoardMap[x + 2][y].hasLokum()
					&&

					localBoardMap[x][y - 1].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x][y + 1].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x + 1][y].getCellLokum().color
							.equals(cellColor)
					&& localBoardMap[x + 2][y].getCellLokum().color
							.equals(cellColor)) { // cell alttan T'ye girdi.

				removeAccordingtoLokumType(localBoardMap[x][y - 1]); // localBoardMap[x][y-1].removeLokum();
				removeAccordingtoLokumType(localBoardMap[x][y + 1]); // localBoardMap[x][y+1].removeLokum();
				removeAccordingtoLokumType(localBoardMap[x + 1][y]); // localBoardMap[x+1][y].removeLokum();
				removeAccordingtoLokumType(localBoardMap[x + 2][y]); // localBoardMap[x+2][y].removeLokum();
				removeAccordingtoLokumType(localBoardMap[x][y]); // localBoardMap[x][y].removeLokum();

				localBoardMap[x][y].addLokum(new Lokum(cellColor, "Wrapped"));
				canRemoveT = true;
			}
		}
		if (canRemoveT) {
			GameEngine.increaseScore(200);
		}
		return canRemoveT;
	}

	private static void removeSingleStriped(BoardCell cell) {
		if (cell != null) {
			int counter = 0;
			int cellx = cell.getCellX();
			int celly = cell.getCellY();

			if (cell.hasLokum() && cell.getCellLokum().type.equals("Striped")
					&& cell.getCellLokum().getDirection().equals("horizontal")) {
				for (int i = 0; i < GameEngine.getGameBoard().getBoardDimX(); i++) {
					localBoardMap[i][celly].removeLokum();
					// counter++;
					// GameEngine.increaseScore(counter*60);
				}
				GameEngine.increaseScore(GameEngine.getGameBoard()
						.getBoardDimX() * 60);
			}
			if (cell.hasLokum()
					&& cell.getCellLokum().getDirection().equals("vertical")) {
				for (int j = 0; j < GameEngine.getGameBoard().getBoardDimy(); j++) {
					localBoardMap[cellx][j].removeLokum();
					// counter++;
					// GameEngine.increaseScore(counter*60);
				}
				GameEngine.increaseScore(GameEngine.getGameBoard()
						.getBoardDimy() * 60);
			}
		}

	}

	private static void removeSingleWrapped(BoardCell cell) {
		if (cell != null) {

			int cellx = cell.getCellX();
			int celly = cell.getCellY();

			if (cell.hasLokum() && cell.getCellLokum().type.equals("Wrapped")) {

				if (cellx - 1 >= 0 && localBoardMap[cellx - 1][celly] != null
						&& localBoardMap[cellx - 1][celly].hasLokum()) {
					localBoardMap[cellx - 1][celly].removeLokum();
				}
				if (cellx - 1 >= 0 && celly - 1 >= 0
						&& localBoardMap[cellx - 1][celly - 1] != null
						&& localBoardMap[cellx - 1][celly - 1].hasLokum()) {
					localBoardMap[cellx - 1][celly - 1].removeLokum();
				}
				if (celly - 1 >= 0 && localBoardMap[cellx][celly - 1] != null
						&& localBoardMap[cellx][celly - 1].hasLokum()) {
					localBoardMap[cellx][celly - 1].removeLokum();
				}
				if (celly - 1 >= 0
						&& cellx + 1 < GameEngine.getGameBoard().getBoardDimX()
						&& localBoardMap[cellx + 1][celly - 1] != null
						&& localBoardMap[cellx + 1][celly - 1].hasLokum()) {
					localBoardMap[cellx + 1][celly - 1].removeLokum();
				}

				if (cellx + 1 < GameEngine.getGameBoard().getBoardDimX()
						&& localBoardMap[cellx + 1][celly] != null
						&& localBoardMap[cellx + 1][celly].hasLokum()) {
					localBoardMap[cellx + 1][celly].removeLokum();
				}
				if (cellx + 1 < GameEngine.getGameBoard().getBoardDimX()
						&& celly + 1 < GameEngine.getGameBoard().getBoardDimy()
						&& localBoardMap[cellx + 1][celly + 1] != null
						&& localBoardMap[cellx + 1][celly + 1].hasLokum()) {
					localBoardMap[cellx + 1][celly + 1].removeLokum();
				}
				if (celly + 1 < GameEngine.getGameBoard().getBoardDimy()
						&& localBoardMap[cellx][celly + 1] != null
						&& localBoardMap[cellx][celly + 1].hasLokum()) {
					localBoardMap[cellx][celly + 1].removeLokum();
				}
				if (cellx - 1 >= 0
						&& celly + 1 < GameEngine.getGameBoard().getBoardDimy()
						&& localBoardMap[cellx - 1][celly + 1] != null
						&& localBoardMap[cellx - 1][celly + 1].hasLokum()) {
					removeAccordingtoLokumType(localBoardMap[cellx - 1][celly + 1]);
				}
				localBoardMap[cellx][celly].removeLokum();
			}
		}
		GameEngine.increaseScore(1080);
	}

	private static void removeSingleColorBomb(BoardCell cell) {

	}

	private static void removeAccordingtoLokumType(BoardCell cell) {

		if (cell != null && cell.hasLokum()) {
			if (cell.getCellLokum().type.equals("Striped")) {
				removeSingleStriped(cell);
			} else if (cell.getCellLokum().type.equals("Wrapped")) {
				removeSingleWrapped(cell);
			} else if (cell.getCellLokum().type.equals("standart")) {
				if(GameEngine.isTimeBasedGame&& cell.getCellLokum().isTimerLokum()) {
					System.out.println("A timer lokum exploded. It added "+ cell.getCellLokum().getTimeOfLokum() +"seconds to the game");
					GameEngine.increaseTime(cell.getCellLokum().getTimeOfLokum());
				
				}
				cell.removeLokum();
			} else if(cell.getCellLokum().type.equals("ColorBomb")){
				cell.removeLokum();
			}
		}

	}

}