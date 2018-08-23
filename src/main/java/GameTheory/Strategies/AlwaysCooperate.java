package GameTheory.Strategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlwaysCooperate extends Strategy {

	public AlwaysCooperate() {
		super();
	}

	/**
	 * Always cooperate; therefore, always return true
	 *
	 * @return true
	 */
	@Override
	public boolean makeMove() {
		return true;
	}

	@Override
	public String getStrategyName() {
		return "AlwaysCooperate";
	}

}

