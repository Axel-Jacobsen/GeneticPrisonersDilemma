package GameTheory.Strategies;

public class Simpleton extends Strategy {

	private boolean prevMove;


	/**
	 * Strategy from the great Evolution of Trust (https://ncase.me/trust/)
	 * <p>
	 * If the opposite cooperates, I will copy my last move. If the opponent defects,
	 * I do the opposite of my last move.
	 */

	Simpleton() {
		super();
	}

	@Override
	public boolean makeMove() {
		boolean move;

		if (opponentMoveHistory.size() == 0) {
			move = true;
		} else if (opponentMoveHistory.get(opponentMoveHistory.size() - 1)) {
			move = prevMove;
		} else {
			move = !prevMove;
		}

		prevMove = move;
		return move;
	}

	@Override
	public String getStrategyName() {
		return null;
	}
}
