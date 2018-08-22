package GameTheory;

import java.util.Collections;
import java.util.List;

public abstract class Strategy {

	private List<Boolean> moveHistory;
	private List<Boolean> opponentMoveHistory;
	private List<Integer> outcomes;

	/**
	 * Gives the move of the strategy at that turn; either
	 * cooperate (true) or don't (false)
	 *
	 * @return true if cooperating, false if not
	 */
	public abstract boolean makeMove();

	/**
	 * Adds the previous move by the opponent to the strategy
	 */
	public abstract void addOpponentMove(boolean opponentPrevMove);


	/**
	 * Adds the previous outcome to the strategy
	 */
	public abstract void addOutcome(int outcome);

	/**
	 * Gives name of strategy
	 *
	 * @return name of strategy
	 */
	public abstract String getStrategyName();

	/**
	 * Returns list of outcomes for strategy
	 *
	 * @return list of outcomes
	 */
	public abstract List<Integer> getOutcomes();

}
