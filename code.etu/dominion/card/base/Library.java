package dominion.card.base;

import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Biblioth�que (Library)
 * 
 * Piochez jusqu'� ce que vous ayez 7 cartes en main. Chaque carte Action
 * pioch�e peut �tre mise de c�t�. D�faussez les cartes mises de c�t� lorsque
 * vous avez termin� de piocher.
 */
public class Library extends ActionCard {
	public Library() {
		super("Library", 5);
	}

	public String toString() {
		return super.toString()
				+ " Piochez jusqu'� ce que vous ayez 7 cartes en main."
				+ " Chaque carte action pioch�e peut �tre mise de c�t�."
				+ " D�faussez les cartes mises de c�t� lorsque vous avez termin� de piocher.";
	}

	@Override
	public void play(Player player) {
		CardList apart_cards = new CardList();
		while (player.get_hand().size() <= 7) {
			Card card = player.drawCard();
			if (card != null) {

				if (card.getTypes().contains(CardType.Action)) {
					String choice = player.choose(
							"Voulez vous mettre" + card.toString()
									+ "de cot�? (y/n)",
							Arrays.asList("y", "n"), false);
					if (choice == "y") {
						apart_cards.add(card);
					} else {
						player.get_hand().add(card);
					}
				} else {
					player.get_hand().add(card);
				}
			} else {
				break;
			}
		}
		for (Card c_loop : apart_cards) {
			player.get_discard().add(c_loop);
		}

	}

}