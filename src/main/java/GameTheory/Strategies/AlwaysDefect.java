package GameTheory.Strategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlwaysDefect extends Strategy {

	public AlwaysDefect() {
		super();
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
