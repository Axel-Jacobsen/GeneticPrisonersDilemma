package GameTheory;

import GameTheory.Strategies.AlwaysCooperate;
import GameTheory.Strategies.AlwaysDefect;
import GameTheory.Strategies.TitForTat;
import GameTheory.Strategies.Strategy;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GameTests {

	@Test
	public void testCooperateDefect() {
		Strategy s1 = new AlwaysCooperate();
		Strategy s2 = new AlwaysDefect();

		Game war = new Game(100, s1, s2);

		List<Integer> warOutcomes = war.executeGame();
		assertEquals(200, (int) warOutcomes.get(1));
		assertEquals(0, (int) warOutcomes.get(0));
	}

	@Test
	public void testCooperateCooperate() {
		Strategy s1 = new AlwaysCooperate();
		Strategy s2 = new AlwaysCooperate();

		Game war = new Game(100, s1, s2);

		List<Integer> warOutcomes = war.executeGame();
		assertEquals(100, (int) warOutcomes.get(0));
		assertEquals(100, (int) warOutcomes.get(1));
	}

	@Test
	public void testDefectDefect() {
		Strategy s1 = new AlwaysDefect();
		Strategy s2 = new AlwaysDefect();

		Game war = new Game(100, s1, s2);

		List<Integer> warOutcomes = war.executeGame();
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

		Game war = new Game(100, s1, s2);

		List<Integer> warOutcomes = war.executeGame();
		assertEquals(100, (int) warOutcomes.get(0));
		assertEquals(100, (int) warOutcomes.get(1));
	}

	/**
	 * Tit-for-tat should always cooperate with the 'Always Cooperate' strategy.
	 * Therefore, this pairing should be equivalent to Always Cooperate vs. Always Cooperate
	 */
	@Test
	public void testTitForTatDefect() {
		Strategy s1 = new TitForTat();
		Strategy s2 = new AlwaysDefect();

		Game war = new Game(3, s1, s2);

		List<Integer> warOutcomes = war.executeGame();
		assertEquals(0, (int) warOutcomes.get(0));
		assertEquals(2, (int) warOutcomes.get(1));
	}
}