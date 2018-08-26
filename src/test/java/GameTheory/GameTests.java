package GameTheory;

import GameTheory.Strategies.AlwaysCooperate;
import GameTheory.Strategies.AlwaysDefect;
import GameTheory.Strategies.Strategy;
import GameTheory.Strategies.TitForTat;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameTests {

	@Test
	public void testCooperateDefect() {
		Strategy s1 = new AlwaysCooperate();
		Strategy s2 = new AlwaysDefect();

		Game war = new Game(s1, s2);

		List<Integer> warOutcomes = war.executeGame(100);
		assertEquals(-100, (int) warOutcomes.get(0));
		assertEquals(300, (int) warOutcomes.get(1));
	}

	@Test
	public void testCooperateCooperate() {
		Strategy s1 = new AlwaysCooperate();
		Strategy s2 = new AlwaysCooperate();

		Game war = new Game(s1, s2);

		List<Integer> warOutcomes = war.executeGame(100);
		assertEquals(200, (int) warOutcomes.get(0));
		assertEquals(200, (int) warOutcomes.get(1));
	}

	@Test
	public void testDefectDefect() {
		Strategy s1 = new AlwaysDefect();
		Strategy s2 = new AlwaysDefect();

		Game war = new Game(s1, s2);

		List<Integer> warOutcomes = war.executeGame(100);
		assertEquals(0, (int) warOutcomes.get(0));
		assertEquals(0, (int) warOutcomes.get(1));
	}


	/**
	 * Tit-for-tat should always cooperate with the 'Always Cooperate' strategy.
	 * Therefore, this pairing should be equivalent to Always Cooperate vs. Always Cooperate
	 */
	@Test
	public void testTitForTatCooperate() {
		Strategy s1 = new TitForTat();
		Strategy s2 = new AlwaysCooperate();

		Game war = new Game(s1, s2);

		List<Integer> warOutcomes = war.executeGame(100);
		assertEquals(200, (int) warOutcomes.get(0));
		assertEquals(200, (int) warOutcomes.get(1));
	}

	/**
	 * Tit-for-tat should always cooperate with the 'Always Cooperate' strategy.
	 * Therefore, this pairing should be equivalent to Always Cooperate vs. Always Cooperate
	 */
	@Test
	public void testTitForTatDefect() {
		Strategy s1 = new TitForTat();
		Strategy s2 = new AlwaysDefect();

		Game war = new Game(s1, s2);

		List<Integer> warOutcomes = war.executeGame(100);
		assertEquals(-1, (int) warOutcomes.get(0));
		assertEquals(3, (int) warOutcomes.get(1));
	}
}