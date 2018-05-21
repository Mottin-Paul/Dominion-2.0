package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte B�cheron (Woodcutter)
 *
 * +1 Achat. +2 Pi�ces.
 */
public class Woodcutter extends ActionCard {
	public Woodcutter() {
		super("Woodcutter", 3);
	}

	public String toString() {
		return super.toString() + " +1 Achat. +2 Pi�ces";
	}

	@Override
	public void play(Player player) {
		player.incrementBuys(1);
		player.incrementMoney(2);
	}
}