import java.util.Random;

public class BasicPlayer extends Player{

	public BasicPlayer(char colour) {
		super(colour);
	}

	public int movePiece(DraughtBoard board) {
		/*
		 * BasicPlayer uses a defensive algorithm that is destined to lose
		 * although preserving more pieces as the farmost pieces are moved first closing existing gaps.
		 * The selection of a piece to move is sequential and directions are random.
		 * Furthermore, BasicPlayer does not check if a move is safe.
		 */
		Random rand = new Random();
		//Find first possible capture.
		for (int y=isWhite?0:7;isWhite?y<8:y>=0;y+=isWhite?1:-1) //Invert the order if white.
			for (int x=isWhite?0:7;isWhite?x<8:x>=0;x+=isWhite?1:-1) //Invert the order if white.
				if(doesOwnPiece(board, x, y)) {
					//Randomise the order of directions checked.
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
		//Find first possible move.
		for (int y=isWhite?0:7;isWhite?y<8:y>=0;y+=isWhite?1:-1) //Invert the order if white.
			for (int x=isWhite?0:7;isWhite?x<8:x>=0;x+=isWhite?1:-1) //Invert the order if white.
				if(doesOwnPiece(board, x, y)) {
					//Randomise the order of directions checked.
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
}
