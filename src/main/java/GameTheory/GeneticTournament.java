package GameTheory;

import GameTheory.Strategies.GeneticOneMove;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class GeneticTournament {


	private HashMap<GeneticOneMove, Integer> points;

	GeneticTournament(List<GeneticOneMove> strategies) {
		this.points = new HashMap<>();
		strategies.forEach(s -> this.points.put(s, 0));
	}

	/**
	 * Genetic tournament
	 * <p>
	 * Holds a tournament between all strategies in the tournament,
	 * while cutting down the poor performers, mating, and mutating
	 * the successful strategies.
	 *
	 * @param numRounds number of rounds of tournaments
	 * @return Map of strategies to the scores that they achieved in the final round
	 */
	public HashMap<GeneticOneMove, Integer> executeGeneticTournamentRounds(int numRounds) throws IOException  {
		HashMap<GeneticOneMove, Integer> save = new HashMap<>();
		FileWriter fileWriter = new FileWriter("geneticRes.txt");
		PrintWriter printWriter = new PrintWriter(fileWriter);

		for (int i = 0; i < numRounds; i++) {

			// Battle!
			addNewPoints(tournamentRound(100));

			// Sort the entries
			ArrayList<Map.Entry<GeneticOneMove, Integer>> sortedEntries = sortEntries(this.points.entrySet());

			// Kill the bottom 50%

			List<Map.Entry<GeneticOneMove, Integer>> merked = sortedEntries.subList(sortedEntries.size() / 2, sortedEntries.size());
			merked.forEach(s -> this.points.remove(s.getKey()));

			if (i == numRounds - 1) {
				save = new HashMap<>(this.points);
			}

			this.points.forEach((s, v) ->
				printWriter.printf("%f %d\n", s.getWeight(), v)
			);

			// Mutate, Mate, whatever else
			HashMap<GeneticOneMove, Integer> weeLittleBabies = new HashMap<>();
//
//			for (int j = 0; j < (this.points.keySet().size() - 1); j++) {
//				// This horrible line mates one GeneticOneMove to another
//				GeneticOneMove g = ((GeneticOneMove) (this.points.keySet().toArray()[j]))
//						.mate((GeneticOneMove) (this.points.keySet().toArray()[j + 1]));
////				GeneticOneMove h = ((GeneticOneMove) (this.points.keySet().toArray()[j])).mutateNew();
//
//				weeLittleBabies.put(g, 0);
//			}
//			GeneticOneMove g = ((GeneticOneMove) (this.points.keySet().toArray()[0]))
//					.mate((GeneticOneMove) (this.points.keySet().toArray()[this.points.keySet().size() - 1]));
//			weeLittleBabies.put(g, 0);

			for (int j = 0; j < (this.points.keySet().size()); j++) {
				// This horrible line mates one GeneticOneMove to another
				GeneticOneMove h = ((GeneticOneMove) (this.points.keySet().toArray()[j])).mutateNew();
				weeLittleBabies.put(h, 0);
			}

			this.points.forEach((s, v) -> {
				s.mutate();
				this.points.put(s, 0);
			});

			this.points.putAll(weeLittleBabies);

			printWriter.printf("-------\n");
		}
		printWriter.close();

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
		Object[] strategies = this.points.keySet().toArray();

		for (int i = 0; i < this.points.keySet().size(); i++) {
			for (int j = i + 1; j < strategies.length; j++) {

				Game g = new Game((GeneticOneMove) strategies[i], (GeneticOneMove) strategies[j]);
				List<Integer> gameOutcome = g.executeGame(n);

				int s1PrevPts = tournamentPoints.getOrDefault(strategies[i], 0);
				int s2PrevPts = tournamentPoints.getOrDefault(strategies[j], 0);

				tournamentPoints.put((GeneticOneMove) strategies[i], s1PrevPts + gameOutcome.get(0));
				tournamentPoints.put((GeneticOneMove) strategies[j], s2PrevPts + gameOutcome.get(1));
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

	/**
	 * Sorts the entries in an entry set by their values and throw
	 * them into an Array list
	 *
	 * @param entrySet Entryset of a map you would like to sort
	 * @return Array list of Entries sorted by value
	 */
	public ArrayList<Map.Entry<GeneticOneMove, Integer>> sortEntries(Set<Map.Entry<GeneticOneMove, Integer>> entrySet) {
		ArrayList<Map.Entry<GeneticOneMove, Integer>> sortedEntries = new ArrayList<>(entrySet);
		sortedEntries.sort((e_last, e_now) -> e_now.getValue() - e_last.getValue());

		return sortedEntries;
	}
}
