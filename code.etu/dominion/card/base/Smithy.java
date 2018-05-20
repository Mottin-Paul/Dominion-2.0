package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Forgeron (Smithy)
 * 
 * +3 Cartes.
 */
public class Smithy extends ActionCard {
	public Smithy() {
		super("Smithy", 4);
	}

	@Override
	public void play(Player player) {
		int i = 0;
		while (i < 3) {
			player.get_hand().add(player.drawCard());
			i++;
		}
	}
}