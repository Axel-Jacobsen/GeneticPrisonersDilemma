package GameTheory.Strategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Strategy {

	private List<Boolean> moveHistory;
	private List<Boolean> opponentMoveHistory;
	private List<Integer> outcomes;

	Strategy () {
		this.moveHistory = new ArrayList<>();
		this.opponentMoveHistory = new ArrayList<>();
		this.outcomes = new ArrayList<>();
	}
	/**
	 * Gives the move of the strategy at that turn; either
	 * cooperate (true) or don't (false)
	 *
	 * @return true if cooperating, false if not
	 */
	public abstract boolean makeMove();

	public abstract String getStrategyName();

	public void addOpponentMove(boolean opponentPrevMove) {
		this.opponentMoveHistory.add(opponentPrevMove);
	}

	public void addOutcome(int outcome) {
		this.outcomes.add(outcome);
	}

	public List<Integer> getOutcomes() {
		return Collections.unmodifiableList(outcomes);
	}

}
