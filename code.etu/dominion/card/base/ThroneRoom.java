package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Salle du trône (Throne Room)
 * 
 * Choisissez 1 carte Action de votre main. Jouez-la deux fois.
 */
public class ThroneRoom extends ActionCard {

	public ThroneRoom() {
		super("Throne Room", 4);
	}

	public String toString() {
		return super.toString() + " Choisissez une carte action de votre main."
				+ " Jouez cette carte action deux fois.";
	}

	public void play(Player player) {
		Card card_played;
		String decision;

		if (!player.getActionCards().isEmpty()) {
			if (player.getActionCards().size() >= 2) {
				decision = player.chooseCard(
						"Choisissez une carte Action de votre main",
						player.getActionCards(), false);
			} else {
				player.getActionCards().remove("Throne Room");
				decision = player.chooseCard(
						"Choisissez une carte Action de votre main",
						player.getActionCards(), false);
			}
			card_played = player.getActionCards().getCard(decision);
			player.playCard(card_played);
			player.remove_in_play(card_played.getName());
			player.playCard(card_played);
		}
	}
}