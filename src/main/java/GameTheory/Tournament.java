package GameTheory;

import GameTheory.Strategies.Strategy;

import java.util.*;

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

				Game g = new Game(strategies.get(i), strategies.get(j));
				List<Integer> gameOutcome = g.executeGame(n);

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

	/**
	 * Sorts the entries in an entry set by their values and throw
	 * them into an Array list
	 *
	 * @param entrySet Entryset of a map you would like to sort
	 * @return Array list of Entries sorted by value
	 */
	public ArrayList<Map.Entry<Strategy, Integer>> sortEntries(Set<Map.Entry<Strategy, Integer>> entrySet) {
		ArrayList<Map.Entry<Strategy, Integer>> sortedEntries = new ArrayList<>(entrySet);
		sortedEntries.sort((e_last, e_now) -> e_now.getValue() - e_last.getValue());

		return sortedEntries;
	}
}
