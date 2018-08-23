package GameTheory.Strategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlwaysDefect extends Strategy {

	private List<Integer> outcomes;

	public AlwaysDefect() {
		this.outcomes = new ArrayList<>();
	}

	/**
	 * Always defect; therefore, always return false
	 *
	 * @return false
	 */
	@Override
	public boolean makeMove() {
		return false;
	}

	@Override
	public String getStrategyName() {
		return "AlwaysDefect";
	}

}
