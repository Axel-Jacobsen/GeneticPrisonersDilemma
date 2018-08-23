package GameTheory;

import GameTheory.Strategies.Strategy;

import java.util.HashMap;
import java.util.List;

public class Tournament {

	/**
	 * Tournament Class
	 *
	 * This class is the place to hold tournaments of games! It will
	 * run the tournament, hold the total scores of each player from the games,
	 * and do other wacky stuff.
	 */

	private List<Strategy> strategies;
	private HashMap<Strategy, Integer> points;


	Tournament (List<Strategy> strategies) {
		this.strategies = strategies;
		this.points = new HashMap<>();
	}

	/**
	 * We need to pair every strategy against every other strategy;
	 */
	public void executeTournamentRounds(int numRounds) {
		for (int i = 0; i < numRounds; i++) {

		}
	}

	public HashMap<Strategy, Integer> tournamentRound() {
		HashMap<Strategy, Integer> points = new HashMap<>();
		for (int i = 0; i < strategies.size(); i++) {
			for (int j = i + 1; j < strategies.size(); j++) {
				Game g = new Game(10, strategies.get(i), strategies.get(j));
				List<Integer> gameOutcome = g.executeGame();
				points.put(strategies.get(i), gameOutcome.get(0));
				points.put(strategies.get(j), gameOutcome.get(1));
			}
		}
		return points;
	}
}
