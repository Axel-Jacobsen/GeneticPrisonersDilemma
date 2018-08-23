package GameTheory;

import GameTheory.Strategies.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TournamentTest {

	@Test
	public void executeGenericTournamentRoundsTest() {

		HashMap<String, Integer> truthMap = new HashMap<>();
		truthMap.put("AlwaysDefect", 570);
		truthMap.put("AlwaysCooperate", 700);
		truthMap.put("Grudger", 790);
		truthMap.put("Simpleton", 750);
		truthMap.put("TitForTat", 790);
		truthMap.put("TitForTatTat", 780);

		Strategy s1 = new AlwaysCooperate();
		Strategy s2 = new AlwaysDefect();
		Strategy s3 = new Grudger();
		Strategy s4 = new Simpleton();
		Strategy s5 = new TitForTat();
		Strategy s6 = new TitForTatTat();

		ArrayList<Strategy> strategies = new ArrayList<>(
				Arrays.asList(
						s1, s2, s3, s4, s5, s6
				)
		);

		Tournament t = new Tournament(strategies);
		HashMap<Strategy, Integer> results = t.executeTournamentRounds(10);

		results.forEach((s, v) ->
				assertEquals(v, truthMap.get(s.getStrategyName()))
		);
	}

	@Test
	public void executeRepeatedTournamentRoundsTest() {

		HashMap<String, Integer> truthMap = new HashMap<>();
		truthMap.put("AlwaysDefect", 570);
		truthMap.put("AlwaysCooperate", 700);
		truthMap.put("Grudger", 790);
		truthMap.put("Simpleton", 750);
		truthMap.put("TitForTat", 790);
		truthMap.put("TitForTatTat", 780);

		Strategy s1 = new AlwaysCooperate();
		Strategy s2 = new AlwaysCooperate();
		Strategy s3 = new AlwaysCooperate();
		Strategy s4 = new AlwaysDefect();
		Strategy s5 = new AlwaysDefect();
		Strategy s6 = new TitForTat();

		ArrayList<Strategy> strategies = new ArrayList<>(
				Arrays.asList(
						s1, s2, s3, s4, s5, s6
				)
		);

		Tournament t = new Tournament(strategies);
		HashMap<Strategy, Integer> results = t.executeTournamentRounds(1);

		results.forEach((s, v) ->
				System.out.println(s.getStrategyName() + ": " + v)
		);
	}
}