package GameTheory.Strategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Strategy {

	protected List<Boolean> opponentMoveHistory;
	protected List<Integer> outcomes;

	Strategy () {
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

	/**
	 * For ease of keeping track of strategies
	 *
	 * @return name of strategy
	 */
	public abstract String getStrategyName();

	/**
	 * Add the opponent's previous move to this strategy's
	 * opponent history
	 *
	 * @param opponentMove the opponent's previous move
	 */
	public void addOpponentMove(boolean opponentMove) {
		this.opponentMoveHistory.add(opponentMove);
	}

	/**
	 * Add the outcome of the battle and add the opponent's
	 * previous move to this strategy's opponent history
	 *
	 * @param outcome the outcome of the battle
	 */
	public void addOutcome(int outcome) {
		this.outcomes.add(outcome);
	}

	public List<Integer> getOutcomes() {
		return Collections.unmodifiableList(outcomes);
	}

}
