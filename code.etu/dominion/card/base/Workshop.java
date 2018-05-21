package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Atelier (Workshop)
 * 
 * Recevez une carte co�tant jusqu'� 4 Pi�ces.
 */
public class Workshop extends ActionCard {
	public Workshop() {
		super("Workshop", 3);
	}

	public String toString() {
		return super.toString()
				+ " Recevez une carte co�tant jusqu'� 4 Pi�ces.";
	}

	@Override
	public void play(Player player) {
		CardList supply_cards = player.getGame().availableSupplyCards();
		Card choosed = null;
		while (true) {
			String card_name = player.chooseCard(
					"Choissisez une Carte Coutant jusqu'� 4 pi�ces",
					supply_cards, false);
			choosed = supply_cards.getCard(card_name);
			if (choosed.getCost() <= 4)
				break;
		}
		if (choosed != null) {
			player.get_discard().add(choosed);
		}
	}
}