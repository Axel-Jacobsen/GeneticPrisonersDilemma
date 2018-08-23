package GameTheory;

import GameTheory.Strategies.Strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Game {

	/**
	 * Game Class
	 * <p>
	 * Hold games with strategies s1 and s2, where the rewards for cooperating and defecting are below.
	 * Different games with different weights should be explored!
	 * <p>
	 *
	 *
	 * Rewards
	 * ----------| Cooperate        | Defect
	 * ----------|------------------|-----------------
	 * Cooperate | 1                | 0, 2 to defector
	 * Defect    | 2 to defector, 0 | 0
	 */

	private int numIters;
	private Strategy s1;
	private Strategy s2;

	private final int bothDefect = 0;
	private final int successDefect = 2;
	private final int failureCooperate = 0;

	Game(int numIters, Strategy s1, Strategy s2) {

		this.s1 = s1;
		this.s2 = s2;

		if (numIters > 0) {
			this.numIters = numIters;
		} else {
			throw new RuntimeException("number of iterations of a game must be greater than 0");
		}
	}

	List<Integer> executeGame() {
		for (int i = 0; i < numIters; i++) {
			List<Boolean> battleOutcome = battle(s1, s2);
		}

		int s1Points = s1.getOutcomes().stream().reduce(0, (a, b) -> a + b);
		int s2Points = s2.getOutcomes().stream().reduce(0, (a, b) -> a + b);

		System.out.println("Strategy " + s1.getStrategyName() + " had final outcome of " + s1Points + " points.");
		System.out.println("Strategy " + s2.getStrategyName() + " had final outcome of " + s2Points + " points.");

		return new ArrayList<>(
				Arrays.asList(s1Points, s2Points)
		);
	}

	private List<Boolean> battle(Strategy s1, Strategy s2) {
		// Make your moves
		boolean s1Move = s1.makeMove();
		boolean s2Move = s2.makeMove();

		// Give the opponents moves to each player
		s1.addOpponentMove(s2Move);
		s2.addOpponentMove(s1Move);

		// Both cooperate
		if (s1Move && s2Move) {
			int bothCooperate = 1;
			s1.addOutcome(bothCooperate);
			s2.addOutcome(bothCooperate);
		}
		// s1 cooperates, s2 defects
		else if (s1Move) {
			s1.addOutcome(failureCooperate);
			s2.addOutcome(successDefect);
		}
		// s2 cooperates, s1 defects
		else if (s2Move) {
			s1.addOutcome(successDefect);
			s2.addOutcome(failureCooperate);
		}
		// both defect
		else {
			s1.addOutcome(bothDefect);
			s2.addOutcome(bothDefect);
		}

		return new ArrayList<>(
				Arrays.asList(s1Move, s2Move)
		);
	}
}
