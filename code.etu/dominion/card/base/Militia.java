package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Milice (Militia)
 * 
 * 2 Pi�ces. Tous vos adversaires d�faussent leurs cartes de fa�on � n'avoir que
 * 3 cartes en main.
 */
public class Militia extends AttackCard {
	public Militia() {
		super("Militia", 4);
	}

	public String toString() {
		return super.toString()
				+ " +1 Carte.\n +1 Action.\n +1 Achat.\n +1 Pi�ce.\n"
				+ " Chaque joueur d�fausse des cartes jusqu'� ce qu'il ne reste plus que trois cartes dans sa main.";
	}

	@Override
	public void play(Player player) {
		player.incrementMoney(2);
		for (Player loop_p : player.otherPlayers()) {
			while (loop_p.get_hand().size() > 3) {
				String choosed_card_name = loop_p.chooseCard(
						"Choisissez une carte � d�fausser", loop_p.get_hand(),
						false);
				loop_p.get_hand().transferTo(
						loop_p.get_hand().getCard(choosed_card_name),
						loop_p.get_discard());
			}
		}
	}
}