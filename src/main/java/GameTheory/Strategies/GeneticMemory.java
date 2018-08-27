package GameTheory.Strategies;

import java.util.Random;

public class GeneticMemory extends Strategy {

	private double weight;
	private boolean previousOpponentMove;
	private Random generator;

	public GeneticMemory(double n) {
		weight = n;
		generator = new Random();
	}


	/**
	 * Mutate this genetic strategy by modifying it's weight
	 */
	public void mutate() {
		boolean pm = generator.nextDouble() > 0.5;
		double val = generator.nextDouble() * 0.002;
		weight = pm && weight + val < 1 ? weight + val : weight - val > 0 ? weight - val : weight;
	}

	/**
	 * Mutate this genetic strategy by modifying it's weight
	 */
	public GeneticMemory mutateNew() {
		boolean pm = generator.nextDouble() > 0.5;
		double val = generator.nextDouble() * 0.005;
		double w = pm && (weight + val < 1) ? weight + val : weight - val > 0 ? weight - val : weight;
		return new GeneticMemory(w);
	}

	/**
	 * Create a new GeneticMemory by mating this strategy to strategy s by taking
	 * the average of their weights
	 *
	 * @param s the second strategy that you would like to mate with
	 * @return new GeneticMemory strategy that is the offspring of this and s
	 */
	public GeneticMemory mate(GeneticMemory s) {
		return new GeneticMemory((this.weight + s.getWeight()) / 2);
	}

	public double getWeight() {
		return weight;
	}


	@Override
	public boolean makeMove() {
		return previousOpponentMove && this.weight < generator.nextDouble();
	}

	@Override
	public String getStrategyName() {
		return null;
	}
}
