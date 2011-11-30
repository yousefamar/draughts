/*
 * @(#)Player.java  6.04.02
 *
 * A base class for a draughts player
 *
 * Dr. Mike Spann
 *
 *
*/

public class Player
{

	public int movePiece(DraughtBoard board)
	{
	  	System.out.println("Not implemented");

	  	return 0;
	}

	protected boolean doesPieceBelongToPlayer(DraughtBoard board, int x, int y) {
		return (board.getPiece(x,y)==(board.isWhitesTurn?DraughtBoard.WHITE:DraughtBoard.BLACK));
	}

	protected boolean isValidCapture(DraughtBoard board, int x, int y, char dir) { //1 is right, -1 is left.
		boolean flag=false;
		try {
			flag =(board.getPiece(x+(dir==DraughtBoard.RIGHT?1:-1), y+(board.isWhitesTurn?1:-1))==(board.isWhitesTurn?DraughtBoard.BLACK:DraughtBoard.WHITE))
					&&(board.getPiece(x+(dir==DraughtBoard.RIGHT?2:-2), y+(board.isWhitesTurn?2:-2))==DraughtBoard.EMPTY);
		} catch (ArrayIndexOutOfBoundsException e) {return false;}
		return flag;
	}
	
	protected boolean isValidMove(DraughtBoard board, int x, int y, char dir) { //1 is right, -1 is left.
		boolean flag=false;
		try {
			flag =(board.getPiece(x+(dir==DraughtBoard.RIGHT?1:-1), y+(board.isWhitesTurn?1:-1))==DraughtBoard.EMPTY);
		} catch (ArrayIndexOutOfBoundsException e) {return false;}
		return flag;
	}
}
