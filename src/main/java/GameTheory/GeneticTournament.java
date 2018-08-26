package GameTheory;

import GameTheory.Strategies.GeneticOneMove;

import java.util.*;

public class GeneticTournament {


	private List<GeneticOneMove> strategies;
	private HashMap<GeneticOneMove, Integer> points;

	GeneticTournament(List<GeneticOneMove> strategies) {
		this.strategies = strategies;
		this.points = new HashMap<>();
	}

	public HashMap<GeneticOneMove, Integer> executeGeneticTournamentRounds(int numRounds) {
		HashMap<GeneticOneMove, Integer> save = new HashMap<>();
		for (int i = 0; i < numRounds; i++) {

			// Battle!
			addNewPoints(tournamentRound(10));

			// Sort the entries
			ArrayList<Map.Entry<GeneticOneMove, Integer>> sortedEntries = sortEntries(this.points.entrySet());

			// Kill the bottom 50%
			List<Map.Entry<GeneticOneMove, Integer>> merked = sortedEntries.subList(sortedEntries.size() / 2 - 1, sortedEntries.size());
			merked.forEach(s -> this.points.remove(s.getKey()));

			if (i == numRounds - 1) {
				save = new HashMap<>(this.points);
			}

			// Mutate, Mate, whatever else
			HashMap<GeneticOneMove, Integer> weeLittleBabies = new HashMap<>();
			for (int j = 0; j < (this.points.keySet().size() - 1); j++) {
				// This horrible line mates one GeneticOneMove to another
				GeneticOneMove g = ((GeneticOneMove) (this.points.keySet().toArray()[j]))
						.mate((GeneticOneMove) (this.points.keySet().toArray()[j + 1]));
				weeLittleBabies.put(g, 0);
			}
			this.points.forEach((s, v) -> {
				s.mutate();
				this.points.put(s, 0);
			});
			this.points.putAll(weeLittleBabies);
		}

		return save;
	}

	/**
	 * One tournament round, where each strategy competes with each other strategy n times
	 *
	 * @param n number of battles each game holds
	 * @return hashmap of each strategy to the number of points it won during the tournament
	 */
	private HashMap<GeneticOneMove, Integer> tournamentRound(int n) {
		HashMap<GeneticOneMove, Integer> tournamentPoints = new HashMap<>();
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
	private void addNewPoints(HashMap<GeneticOneMove, Integer> newPoints) {
		for (GeneticOneMove s : newPoints.keySet()) {
			int prevPts = this.points.getOrDefault(s, 0);
			this.points.put(s, prevPts + newPoints.get(s));
		}
	}

	public ArrayList<Map.Entry<GeneticOneMove, Integer>> sortEntries(Set<Map.Entry<GeneticOneMove, Integer>> entrySet) {
		ArrayList<Map.Entry<GeneticOneMove, Integer>> sortedEntries = new ArrayList<>(entrySet);
		sortedEntries.sort((e_last, e_now) -> e_now.getValue() - e_last.getValue());

		return sortedEntries;
	}
}
