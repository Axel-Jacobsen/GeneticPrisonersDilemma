package GameTheory.Strategies;

import java.util.Random;

public class GeneticOneMove extends Strategy {

	/**
	 * This class is a genetic algorithm that acts on no
	 * knowledge of previous moves.
	 */

	private double weight;
	private Random generator;

	public GeneticOneMove(double n) {
		weight = n;
		generator = new Random();
	}

	/**
	 * Mutate this genetic strategy by modifying it's weight
	 */
	public void mutate() {
		boolean pm = generator.nextDouble() > 0.5;
		double val = generator.nextDouble() * 0.005;
		weight = pm && weight + val < 1 ? weight + val : weight - val > 0 ? weight - val : weight;
	}

	/**
	 * Create a new GeneticOneMove by mating this strategy to strategy s by taking
	 * the average of their weights
	 *
	 * @param s the second strategy that you would like to mate with
	 * @return new GeneticOneMove strategy that is the offspring of this and s
	 */
	public GeneticOneMove mate(GeneticOneMove s) {
		return new GeneticOneMove((this.weight + s.getWeight()) / 2);
	}

	public double getWeight() {
		return weight;
	}

	/**
	 * make a move probabilistically; i.e. generate a random number
	 * between 0 or 1, and return if it is less than this.weight.
	 * Therefore, if this.weight is low, false will be returned more often
	 * and this strategy will display a pseudo-always-defect strategy.
	 *
	 * @return defect / cooperate depending on if the random number is less than
	 * weight
	 */
	@Override
	public boolean makeMove() {
		return generator.nextDouble() < this.weight;
	}

	@Override
	public String getStrategyName() {
		return "GeneticOneMove";
	}
}
