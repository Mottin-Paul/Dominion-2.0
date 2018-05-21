package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Laboratoire (Laboratory)
 *
 * +2 Cartes. +1 Action.
 */
public class Laboratory extends ActionCard {
	public Laboratory() {
		super("Laboratory", 5);
	}
	
	public String toString() {
		return super.toString()
				+ " +2 Cartes. +1 Action.";
	}

	@Override
	public void play(Player player) {
		for (int i = 0; i < 2; i++) {
			player.get_hand().add(player.drawCard());
		}
		player.incrementActions(1);
	}
}