package GameTheory.Strategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlwaysDefect extends Strategy {

	private final String strategyName = "AlwaysNotCooperate";
	private List<Boolean> moveHistory;
	private List<Boolean> opponentMoveHistory;
	private List<Integer> outcomes;

	public AlwaysDefect() {
		this.moveHistory = new ArrayList<>();
		this.opponentMoveHistory = new ArrayList<>();
		this.outcomes = new ArrayList<>();
	}

	@Override
	public boolean makeMove() {
		this.moveHistory.add(false);
		return false;
	}

	@Override
	public void addOpponentMove(boolean opponentPrevMove) {
		this.opponentMoveHistory.add(opponentPrevMove);
	}

	@Override
	public void addOutcome(int outcome) {
		this.outcomes.add(outcome);
	}

	@Override
	public String getStrategyName() {
		return strategyName;
	}

	@Override
	public List<Integer> getOutcomes() {
		return Collections.unmodifiableList(outcomes);
	}
}
