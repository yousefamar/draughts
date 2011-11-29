import java.util.Random;

public class BasicPlayer extends Player{

	public int movePiece(DraughtBoard board)
	{
		board.isWhitesTurn=!board.isWhitesTurn;
		Random rand = new Random();
		for (int y=0;y<8;y++)
			for (int x=0;x<8;x++)
				if(doesPieceBelongToPlayer(board, x, y)) {
					char firstDir = rand.nextBoolean()?DraughtBoard.RIGHT:DraughtBoard.LEFT;
					char secondDir = firstDir==DraughtBoard.RIGHT?DraughtBoard.LEFT:DraughtBoard.RIGHT;
					if(isValidCapture(board, x, y, firstDir)) {
						if(board.isWhitesTurn)
							board.moveWhite(x, y, firstDir);
						else
							board.moveBlack(x, y, firstDir);
						return 0;
					}
					if(isValidCapture(board, x, y, secondDir)) {
						if(board.isWhitesTurn)
							board.moveWhite(x, y, secondDir);
						else
							board.moveBlack(x, y, secondDir);
						return 0;
					}
				}
		for (int y=0;y<8;y++)
			for (int x=0;x<8;x++)
				if(doesPieceBelongToPlayer(board, x, y)) {
					char firstDir = rand.nextBoolean()?DraughtBoard.RIGHT:DraughtBoard.LEFT;
					char secondDir = firstDir==DraughtBoard.RIGHT?DraughtBoard.LEFT:DraughtBoard.RIGHT;
					if(isValidMove(board, x, y, firstDir)) {
						if(board.isWhitesTurn)
							board.moveWhite(x, y, firstDir);
						else
							board.moveBlack(x, y, firstDir);
						return 0;
					}
					if(isValidMove(board, x, y, secondDir)) {
						if(board.isWhitesTurn)
							board.moveWhite(x, y, secondDir);
						else
							board.moveBlack(x, y, secondDir);
						return 0;
					}
				}
		return 0;
	}
	
	private boolean doesPieceBelongToPlayer(DraughtBoard board, int x, int y) {
		return (board.getPiece(x,y)==(board.isWhitesTurn?DraughtBoard.WHITE:DraughtBoard.BLACK));
	}

	private boolean isValidCapture(DraughtBoard board, int x, int y, char dir) { //1 is right, -1 is left.
		boolean flag=false;
		try {
			flag =(board.getPiece(x+(dir==DraughtBoard.RIGHT?1:-1), y+(board.isWhitesTurn?1:-1))==(board.isWhitesTurn?DraughtBoard.BLACK:DraughtBoard.WHITE))
					&&(board.getPiece(x+(dir==DraughtBoard.RIGHT?2:-2), y+(board.isWhitesTurn?2:-2))==DraughtBoard.EMPTY);
		} catch (ArrayIndexOutOfBoundsException e) {return false;}
		return flag;
	}
	
	private boolean isValidMove(DraughtBoard board, int x, int y, char dir) { //1 is right, -1 is left.
		boolean flag=false;
		try {
			flag =(board.getPiece(x+(dir==DraughtBoard.RIGHT?1:-1), y+(board.isWhitesTurn?1:-1))==DraughtBoard.EMPTY);
		} catch (ArrayIndexOutOfBoundsException e) {return false;}
		return flag;
	}
}
