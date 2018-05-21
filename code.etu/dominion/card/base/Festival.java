package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Festival
 *
 * +2 Actions. +1 Achat. +2 Pi�ces.
 */
public class Festival extends ActionCard {

	public Festival() {
		super("Festival", 5);
	}

	public String toString() {
		return super.toString() + " +2 Actions. +1 Achat. +2 Pi�ces.";
	}

	@Override
	public void play(Player player) {
		player.incrementActions(2);
		player.incrementBuys(1);
		player.incrementMoney(2);
	}
}