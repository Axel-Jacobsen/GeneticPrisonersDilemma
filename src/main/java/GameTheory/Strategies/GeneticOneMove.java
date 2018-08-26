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

	public void mutate() {
		boolean pm = generator.nextDouble() > 0.5;
		double val = generator.nextDouble() * 0.05;
		weight = pm && weight + val < 1 ? weight + val : weight - val > 0 ? weight - val : weight;
	}

	public GeneticOneMove mate(GeneticOneMove s) {
		return new GeneticOneMove((this.weight + s.getWeight()) / 2);
	}

	public double getWeight() {
		return weight;
	}

	@Override
	public boolean makeMove() {
		return generator.nextDouble() < this.weight;
	}

	@Override
	public String getStrategyName() {
		return "GeneticOneMove";
	}
}
