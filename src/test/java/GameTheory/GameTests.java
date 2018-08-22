package GameTheory;

import org.junit.Test;

import java.util.List;

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


}