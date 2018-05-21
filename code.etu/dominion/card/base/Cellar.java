package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Cave (Cellar)
 *
 * +1 Action. Défaussez autant de cartes que vous voulez. +1 Carte par carte
 * défaussée.
 */
public class Cellar extends ActionCard {

	public Cellar() {
		super("Cellar", 2);
	}

	public String toString() {
		return super.toString()
				+ "+1 Action.\n"
				+ " Posez n'importe quel nombre de cartes."
				+ " Piochez une carte pour chaque carte que vous avez mise au rebut.";
	}

	@Override
	public void play(Player player) {
		player.incrementActions(1);
		String choix = "y";

		while (choix != "") {
			CardList card_list = player.get_hand();
			choix = player.chooseCard("Quelle carte voulez vous défausser?",
					card_list, true);
			if (choix != "") {
				card_list.transferTo(card_list.getCard(choix),
						player.get_discard());
				card_list.add(player.drawCard());
			} else {
				break;
			}

		}
	}
}