package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte R�novation (Remodel)
 * 
 * �cartez une carte de votre main. Recevez une carte co�tant jusqu'� 2 Pi�ces
 * de plus que la carte �cart�e.
 */
public class Remodel extends ActionCard {

	public Remodel() {
		super("Remodel", 4);
	}

	public String toStrin() {
		return super.toString()
				+ " �cartez une carte de votre main.\n"
				+ " Recevez une carte co�tant jusqu'� 2 Pi�ces de plus que la carte �cart�e.";
	}

	public void play(Player player) {
		String decision;
		Card card_trash;

		CardList card_available = new CardList();

		if (!player.cardsInHand().isEmpty()) {
			decision = player.chooseCard(
					"Choisissez la carte que vous voulez �carter",
					player.cardsInHand(), false);
			card_trash = player.cardsInHand().getCard(decision);
			player.remove_hand(card_trash.getName());
			player.getGame().addTrashCard(card_trash);

			int i = 0;
			while (i < player.getGame().availableSupplyCards().size()) {

				if (player.getGame().availableSupplyCards().get(i).getCost() <= card_trash
						.getCost() + 2)
					card_available.add(player.getGame().availableSupplyCards()
							.get(i));
				i++;
			}

			decision = player.chooseCard(
					"Choisissez la carte que vous d�sirez", card_available,
					false);
			player.gain(decision);
		}
	}
}
