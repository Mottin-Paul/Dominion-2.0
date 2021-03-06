package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Pr�teur sur gages (Moneylender)
 * 
 * �cartez une carte Cuivre de votre main. Dans ce cas, +3 Pi�ces.
 */
public class Moneylender extends ActionCard {

	public Moneylender() {
		super("Moneylender", 4);
	}

	public String toString() {
		return super.toString()
				+ " Jetez un cuivre de votre main. Si vous faites cela, +3 d'argent.";
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