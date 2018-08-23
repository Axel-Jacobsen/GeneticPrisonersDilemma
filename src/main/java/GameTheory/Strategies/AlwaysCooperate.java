package GameTheory.Strategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlwaysCooperate extends Strategy {

	private List<Boolean> moveHistory;
	private List<Boolean> opponentMoveHistory;
	private List<Integer> outcomes;

	public AlwaysCooperate() {
		this.moveHistory = new ArrayList<>();
		this.opponentMoveHistory = new ArrayList<>();
		this.outcomes = new ArrayList<>();
	}

	@Override
	public boolean makeMove() {
		this.moveHistory.add(true);
		return true;
	}

	@Override
	public String getStrategyName() {
		return "AlwaysCooperate";
	}

}

