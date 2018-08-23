package GameTheory;

import GameTheory.Strategies.Strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	public HashMap<Strategy, Integer> executeTournamentRounds(int numRounds) {
		for (int i = 0; i < numRounds; i++) {
			addNewPoints(tournamentRound(10));
//			// Sort the entries
//			Set<Map.Entry<Strategy, Integer>> entries = this.points.entrySet();
		}
		return this.points;
	}

	/**
	 * One tournament round, where each strategy competes with each other strategy n times
	 *
	 * @param n number of battles each game holds
	 *
	 * @return hashmap of each strategy to the number of points it won during the tournament
	 */
	private HashMap<Strategy, Integer> tournamentRound(int n) {
		HashMap<Strategy, Integer> tournamentPoints = new HashMap<>();
		for (int i = 0; i < strategies.size(); i++) {
			for (int j = i + 1; j < strategies.size(); j++) {

				Game g = new Game(n, strategies.get(i), strategies.get(j));
				List<Integer> gameOutcome = g.executeGame();

				int s1PrevPts = tournamentPoints.getOrDefault(strategies.get(i), 0);
				int s2PrevPts = tournamentPoints.getOrDefault(strategies.get(j), 0);

				tournamentPoints.put(strategies.get(i), s1PrevPts + gameOutcome.get(0));
				tournamentPoints.put(strategies.get(j), s2PrevPts + gameOutcome.get(1));
			}
		}
		return tournamentPoints;
	}

	/**
	 * Sum newPoints (i.e. points from a tournamentRound) to points
	 *
	 * @param newPoints points from a tournamentRound
	 */
	private void addNewPoints(HashMap<Strategy, Integer> newPoints) {
		for (Strategy s: newPoints.keySet()) {
			int prevPts = this.points.getOrDefault(s, 0);
			this.points.put(s, prevPts + newPoints.get(s));
		}
	}
}
