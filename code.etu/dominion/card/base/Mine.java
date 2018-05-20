package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Mine
 * 
 * Écartez une carte Trésor de votre main. Recevez une carte Trésor coûtant
 * jusqu'à 3 Pièces de plus ; ajoutez cette carte à votre main.
 */
public class Mine extends ActionCard {

	public Mine() {
		super("Mine", 5);
	}

	public String toString() {
		return super.toString() + " "
				+ " Écartez une carte Trésor de votre main."
				+ " Recevez une carte Trésor coûtant jusqu'à 3 Pièces de plus "
				+ " et ajoutez cette carte à votre main.";
	}

	public void play(Player player) {
		CardList card_treasure_available = new CardList();
		String decision;
		Card card_trash;

		if (!player.getTreasureCards().isEmpty()) {
			decision = player.chooseCard(
					"Choisissez la carte trésor que vous voulez écarter",
					player.getTreasureCards(), false);
			card_trash = player.getTreasureCards().getCard(decision);
			player.remove_hand(card_trash.getName());
			player.getGame().addTrashCard(card_trash);

			int i = 0;
			while (i < player.getGame().availableSupplyCards().size()) {

				if (player.getGame().availableSupplyCards().get(i).getTypes()
						.get(0) == CardType.Treasure
						&& player.getGame().availableSupplyCards().get(i)
								.getCost() <= card_trash.getCost() + 3) {
					card_treasure_available.add(player.getGame()
							.availableSupplyCards().get(i));
				}
				i++;
			}

			decision = player.chooseCard(
					"Choisissez la carte trésor que vous voulez recevoir",
					card_treasure_available, false);
			player.add_hand(player.getGame().removeFromSupply(decision));
		}

	}

}