import java.util.Random;

public class AdvancedPlayer extends Player{

	public AdvancedPlayer(char colour) {
		super(colour);
	}

	public int movePiece(DraughtBoard board) {
		/*
		 * AdvancedPlayer uses an offensive algorithm that is more likely to win
		 * although losing more pieces as the foremost pieces are moved first exposing gaps.
		 * The selection of a piece to move is sequential and directions are random.
		 * Furthermore, AdvancedPlayer checks whether a move is safe one level deep before pursuing it.
		 */
		//TODO: Randomise x values for unpredictable element. 
		Random rand = new Random();
		boolean shouldBeSafe = true;
		while(true) { 
			//Find optimal capture.
			for (int y=isWhite?7:0;isWhite?y>=0:y<8;y+=isWhite?-1:1) //Invert the order if white.
				for (int x=isWhite?7:0;isWhite?x>=0:x<8;x+=isWhite?-1:1) //Invert the order if white.
					if(doesOwnPiece(board, x, y)) {
						//Randomise the order of directions checked.
						char firstDir = rand.nextBoolean()?DraughtBoard.RIGHT:DraughtBoard.LEFT;
						char secondDir = firstDir==DraughtBoard.RIGHT?DraughtBoard.LEFT:DraughtBoard.RIGHT;
						if(isValidCapture(board, x, y, firstDir) && (!shouldBeSafe || isSafeCapture(board, x, y, firstDir))) {
							if(isWhite)
								board.moveWhite(x, y, firstDir);
							else
								board.moveBlack(x, y, firstDir);
							return 0;
						}
						if(isValidCapture(board, x, y, secondDir) && (!shouldBeSafe || isSafeCapture(board, x, y, secondDir))) {
							if(isWhite)
								board.moveWhite(x, y, secondDir);
							else
								board.moveBlack(x, y, secondDir);
							return 0;
						}
					}
			//Find first optimal move.
			for (int y=isWhite?7:0;isWhite?y>=0:y<8;y+=isWhite?-1:1) //Invert the order if white.
				for (int x=isWhite?7:0;isWhite?x>=0:x<8;x+=isWhite?-1:1) //Invert the order if white.
					if(doesOwnPiece(board, x, y)) {
						//Randomise the order of directions checked.
						char firstDir = rand.nextBoolean()?DraughtBoard.RIGHT:DraughtBoard.LEFT;
						char secondDir = firstDir==DraughtBoard.RIGHT?DraughtBoard.LEFT:DraughtBoard.RIGHT;
						if(isValidMove(board, x, y, firstDir) && (!shouldBeSafe || isSafeMove(board, x, y, firstDir))) {
							if(isWhite)
								board.moveWhite(x, y, firstDir);
							else
								board.moveBlack(x, y, firstDir);
							return 0;
						}
						if(isValidMove(board, x, y, secondDir) && (!shouldBeSafe || isSafeMove(board, x, y, secondDir))) {
							if(isWhite)
								board.moveWhite(x, y, secondDir);
							else
								board.moveBlack(x, y, secondDir);
							return 0;
						}
					}
			if(!shouldBeSafe) //If no possible moves at all are found, skip turn.
				break;
			//If no safe moves are found, disable safety priority.
			shouldBeSafe = false;
		}
		return 0;
	}

	private boolean isSafeCapture(DraughtBoard board, int x, int y, char dir) {
		return isSafePos(board, x+(dir==DraughtBoard.RIGHT?1:-1), y+(isWhite?1:-1), x, y);
	}

	private boolean isSafeMove(DraughtBoard board, int x, int y, char dir) {
		return isSafePos(board, x+(dir==DraughtBoard.RIGHT?1:-1), y+(isWhite?1:-1), x, y);
	}
	
	/**
	 * Checks if a position can be jumped. Favours positions on edges.
	 * @param board
	 * @param destX
	 * @param destY
	 * @return Returns true if position can be jumped.
	 */
	private boolean isSafePos(DraughtBoard board, int destX, int destY, int origX, int origY) {
		boolean canBeCaptured = false;
		try {
			//Expanded out for readability.
			boolean isOpponentRight = board.getPiece(destX+1, destY+(isWhite?1:-1))==(isWhite?DraughtBoard.BLACK:DraughtBoard.WHITE);
			boolean isOpponentLeft = board.getPiece(destX-1, destY+(isWhite?1:-1))==(isWhite?DraughtBoard.BLACK:DraughtBoard.WHITE);
			boolean canRightCapture = board.getPiece(destX-1, destY+(isWhite?-1:1))==DraughtBoard.EMPTY
										||(destX-1==origX && destY+(isWhite?-1:1)==origY)
										||(destX-1==(destX+origX)/2 && destY+(isWhite?-1:1)==(destY+origY)/2);
			boolean canLeftCapture = board.getPiece(destX+1, destY+(isWhite?-1:1))==DraughtBoard.EMPTY
										||(destX+1==origX && destY+(isWhite?-1:1)==origY)
										||(destX+1==(destX+origX)/2 && destY+(isWhite?-1:1)==(destY+origY)/2);
			canBeCaptured = (isOpponentRight&&canRightCapture) || (isOpponentLeft&&canLeftCapture);
		} catch (ArrayIndexOutOfBoundsException e) {
			return true;
		}
		return !canBeCaptured;
	}
}
