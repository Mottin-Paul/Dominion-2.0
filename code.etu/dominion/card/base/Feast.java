package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Festin (Feast)
 * 
 * �cartez cette carte. Recevez une carte co�tant jusqu'� 5 Pi�ces.
 */
public class Feast extends ActionCard {
	public Feast() {
		super("Feast", 4);
	}

	@Override
	public void play(Player player) {
		player.get_hand().transferTo(this, player.getGame().getTrashedCards());
		CardList supply_cards = player.getGame().availableSupplyCards();
		Card choosed = null;
		while (true) {
			String card_name = player.chooseCard(
					"Choissisez une Carte Coutant jusqu'� 5 pi�ces",
					supply_cards, false);
			choosed = supply_cards.getCard(card_name);
			if (choosed.getCost() <= 5)
				break;
		}
		if (choosed != null) {
			player.get_discard().add(choosed);
		}

	}
}