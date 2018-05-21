package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Bûcheron (Woodcutter)
 *
 * +1 Achat. +2 Pièces.
 */
public class Woodcutter extends ActionCard {
	public Woodcutter() {
		super("Woodcutter", 3);
	}

	public String toString() {
		return super.toString() + " +1 Achat. +2 Pièces";
	}

	@Override
	public void play(Player player) {
		player.incrementBuys(1);
		player.incrementMoney(2);
	}
}