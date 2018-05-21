package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Aventurier (Adventurer)
 * 
 * Dévoilez des cartes de votre deck jusqu'à ce que 2 cartes Trésor soient
 * dévoilées. Ajoutez ces cartes Trésor à votre main et défaussez les autres
 * cartes dévoilées.
 */
public class Adventurer extends ActionCard {

	public Adventurer() {
		super("Adventurer", 6);
	}

	public String toString() {
		return super.toString()
				+ " Piochez dans votre deck jusqu'à ce que 2 cartes trésor soient dévoilées."
				+ " Ajoutez ces cartes trésor à votre main et défaussez les autres cartes dévoilées.";
	}

	@Override
	public void play(Player player) {
		int nb_card_revealed = 0;
		while (nb_card_revealed < 2) {
			Card card = player.drawCard();
			if (card != null) {
				card.toString();
				if (card.getTypes().contains(CardType.Treasure)) {
					player.get_hand().add(card);
					nb_card_revealed++;
				} else {
					player.gain(card);
				}
			}
		}
	}
}