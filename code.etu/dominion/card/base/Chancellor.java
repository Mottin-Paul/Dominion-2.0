package dominion.card.base;

import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Chancellier (Chancellor)
 *
 * +2 Pièces. Vous pouvez immédiatement défausser votre deck.
 */
public class Chancellor extends ActionCard {

	public Chancellor() {
		super("Chancellor", 3);
	}

	public String toString() {
		return super.toString()
				+ "+2 Pièces. Vous pouvez immédiatement défausser votre deck.";
	}

	@Override
	public void play(Player player) {
		player.incrementMoney(2);
		String decision = player.choose("Voulez-vous défausser votre deck?",
				Arrays.asList("y", "n"), false);
		if (decision.equals("y")) {
			player.get_draw().transferTo(player.get_discard());
		}
	}

}