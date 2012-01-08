import java.util.Random;

public class BasicPlayer extends Player{
	
	// The original hierarchy was that BasicPlayer *and* AdvancedPlayer were
	// both subclasses of Player. The bottom three methods in this class were originally in Player;
	// they were copied over to not edit Player.java. The hierarchy is now
	// Player <- BasicPlayer <- AdvancedPlayer. The latter isn't what I'd do normally
	// but I guess it's a question of style.
	
	protected boolean isWhite;

	public BasicPlayer(char colour) {
		super(); // The player class has no constructor (anymore), but if it did it would make sense to call it.
		isWhite = colour==DraughtBoard.WHITE;
	}

	public int movePiece(DraughtBoard board) {
		/* BasicPlayer uses a defensive algorithm that is destined to lose,
		 * although preserving more pieces, as the farmost pieces are moved first closing existing gaps.
		 * The selection of a piece to move is sequential and directions are random.
		 * Furthermore, BasicPlayer does not check if a move is safe.
		 */
		Random rand = new Random();
		/* Find first possible capture */
		for (int y=isWhite?0:7;isWhite?y<8:y>=0;y+=isWhite?1:-1) /* Invert the order if white */
			for (int x=isWhite?0:7;isWhite?x<8:x>=0;x+=isWhite?1:-1) /* Invert the order if white */
				if(doesOwnPiece(board, x, y)) {
					/* Randomise the order of directions checked */
					char firstDir = rand.nextBoolean()?DraughtBoard.RIGHT:DraughtBoard.LEFT;
					char secondDir = firstDir==DraughtBoard.RIGHT?DraughtBoard.LEFT:DraughtBoard.RIGHT;
					if(isValidCapture(board, x, y, firstDir)) {
						if(isWhite)
							board.moveWhite(x, y, firstDir);
						else
							board.moveBlack(x, y, firstDir);
						return 0;
					}
					if(isValidCapture(board, x, y, secondDir)) {
						if(isWhite)
							board.moveWhite(x, y, secondDir);
						else
							board.moveBlack(x, y, secondDir);
						return 0;
					}
		}
		/* Find first possible move */
		for (int y=isWhite?0:7;isWhite?y<8:y>=0;y+=isWhite?1:-1) /* Invert the order if white */
			for (int x=isWhite?0:7;isWhite?x<8:x>=0;x+=isWhite?1:-1) /* Invert the order if white */
				if(doesOwnPiece(board, x, y)) {
					/* Randomise the order of directions checked */
					char firstDir = rand.nextBoolean()?DraughtBoard.RIGHT:DraughtBoard.LEFT;
					char secondDir = firstDir==DraughtBoard.RIGHT?DraughtBoard.LEFT:DraughtBoard.RIGHT;
					if(isValidMove(board, x, y, firstDir)) {
						if(isWhite)
							board.moveWhite(x, y, firstDir);
						else
							board.moveBlack(x, y, firstDir);
						return 0;
					}
					if(isValidMove(board, x, y, secondDir)) {
						if(isWhite)
							board.moveWhite(x, y, secondDir);
						else
							board.moveBlack(x, y, secondDir);
						return 0;
					}
		}
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