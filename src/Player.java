/*
 * @(#)Player.java  6.04.02
 *
 * A base class for a draughts player
 *
 * Dr. Mike Spann
 *
 *
*/

public class Player {
	
	//Lack of comments due to self-explanatory nature of code.
	
	protected boolean isWhite=false;

	public Player() {}
 	
	public Player(char colour) {
		isWhite = colour==DraughtBoard.WHITE;
	}

	public int movePiece(DraughtBoard board) {
		System.out.println("No Player Chosen.");
		return 0;
	}

	protected boolean doesOwnPiece(DraughtBoard board, int x, int y) {
		return (board.getPiece(x,y)==(isWhite?DraughtBoard.WHITE:DraughtBoard.BLACK));
	}

	protected boolean isValidCapture(DraughtBoard board, int x, int y, char dir) {
		boolean flag=false;
		try {
			flag =(board.getPiece(x+(dir==DraughtBoard.RIGHT?1:-1), y+(isWhite?1:-1))==(isWhite?DraughtBoard.BLACK:DraughtBoard.WHITE))
					&&(board.getPiece(x+(dir==DraughtBoard.RIGHT?2:-2), y+(isWhite?2:-2))==DraughtBoard.EMPTY);
		} catch (ArrayIndexOutOfBoundsException e) {return false;}
		return flag;
	}
	
	protected boolean isValidMove(DraughtBoard board, int x, int y, char dir) {
		boolean flag=false;
		try {
			flag =(board.getPiece(x+(dir==DraughtBoard.RIGHT?1:-1), y+(isWhite?1:-1))==DraughtBoard.EMPTY);
		} catch (ArrayIndexOutOfBoundsException e) {return false;}
		return flag;
	}
}
