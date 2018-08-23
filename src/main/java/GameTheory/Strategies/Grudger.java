package GameTheory.Strategies;

public class Grudger extends Strategy {

	/**
	 * Strategy from the great Evolution of Trust (https://ncase.me/trust/)
	 *
	 * Player cooperates until the opponent cheats; then they always cheat
	 */

	private boolean opponentCheated;

	Grudger() {
		super();
		opponentCheated = false;
	}


	@Override
	public boolean makeMove() {
		return !opponentCheated;
	}

	@Override
	public String getStrategyName() {
		return "Grudger";
	}

	/**
	 * If the opponent did not
	 * @param opponentMove the opponent's previous move
	 */
	@Override
	public void addOpponentMove(boolean opponentMove) {
		if (!opponentCheated && !opponentMove) {
			opponentCheated = true;
		}
	}

	@Override
	public void clearStrategy() {
		super.clearStrategy();
		opponentCheated = false;
	}
}
