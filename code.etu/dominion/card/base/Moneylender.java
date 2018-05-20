package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Prêteur sur gages (Moneylender)
 * 
 * Écartez une carte Cuivre de votre main. Dans ce cas, +3 Pièces.
 */
public class Moneylender extends ActionCard {

	public Moneylender() {
		super("Moneylender", 4);
	}

	@Override
	public void play(Player player) {
		Card copper = player.get_hand().getCard("Copper");
		if (copper != null) {
			player.incrementMoney(3);
			player.get_hand().transferTo(copper,
					player.getGame().getTrashedCards());
		}
	}
}