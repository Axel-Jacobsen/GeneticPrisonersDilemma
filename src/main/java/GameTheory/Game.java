package GameTheory;

import GameTheory.Strategies.Strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Game {

	/**
	 * Game Class
	 *
	 * Hold games with strategies s1 and s2, where the rewards for cooperating and defecting are below.
	 * Different games with different weights should be explored!
	 *
	 * Rewards
	 * ----------| Cooperate         | Defect
	 * ----------|-------------------| -----------------
	 * Cooperate | 2                 | -1, 3 to defector
	 * Defect    | 3 to defector, -1 | 0
	 */

	private Strategy s1;
	private Strategy s2;

	private static final int bothCooperate = 2;
	private static final int bothDefect = 0;
	private static final int successDefect = 3;
	private static final int failureCooperate = -1;

	Game(Strategy s1, Strategy s2) {
		this.s1 = s1;
		this.s2 = s2;
	}

	/**
	 * Execute 1 game numIterations times
	 *
	 * @return a tuple of their scores
	 */
	List<Integer> executeGame(int numIterations) {

		if (numIterations < 0) {
			throw new RuntimeException("number of iterations of a game must be greater than 0");
		}

		for (int i = 0; i < numIterations; i++) {
			battle(s1, s2);
		}

		int s1Points = s1.getPoints();
		int s2Points = s2.getPoints();

		s1.clearStrategy();
		s2.clearStrategy();

		return new ArrayList<>(
				Arrays.asList(s1Points, s2Points)
		);
	}

	/**
	 * Hold a battle between s1 and s2; this is the classic
	 * prisoners dilemma cooperate / defect game.
	 *
	 * @param s1 first strategy
	 * @param s2 second strategy
	 * @return A tuple of moves of s1 and s2
	 */
	private void battle(Strategy s1, Strategy s2) {

		// Make your moves
		boolean s1Move = s1.makeMove();
		boolean s2Move = s2.makeMove();

		// Give the opponents moves to each player
		s1.addOpponentMove(s2Move);
		s2.addOpponentMove(s1Move);

		// Both cooperate
		if (s1Move && s2Move) {
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
	}
}
