package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Chambre du conseil (Council Room)
 * 
 * +4 Cartes. +1 Achat. Tous vos adversaires piochent 1 carte.
 */
public class CouncilRoom extends ActionCard {

	public CouncilRoom() {
		super("Council Room", 2);
	}

	public String toString() {
		return super.toString() + " +4 Cartes.\n +1 Achat.\n"
				+ " Chaque joueur pioche immédiatement une carte.";
	}

	public void play(Player player) {
		player.incrementBuys(1);

		int i = 0;
		while (i < 4) {
			player.add_hand(player.drawCard());
			i++;
		}

		int i2 = 0;
		while (i2 < player.getGame().otherPlayers(player).size()) {
			player.getGame()
					.otherPlayers(player)
					.get(i2)
					.add_hand(
							player.getGame().otherPlayers(player).get(i2)
									.drawCard());
			i2++;
		}
	}
}