package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Village
 *
 * +1 Carte. +2 Actions.
 */
public class Village extends ActionCard {
	public Village() {
		super("Village", 3);
	}

	public String toString() {
		return super.toString() + " +1 Carte.\n +2 Actions.";
	}

	@Override
	public void play(Player player) {
		player.incrementActions(2);
		player.get_hand().add(player.drawCard());

	}
}