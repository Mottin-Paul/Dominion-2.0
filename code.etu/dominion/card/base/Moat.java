package dominion.card.base;

import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Douves (Moat)
 * 
 * +2 Cartes. Lorsqu’un adversaire joue une carte Attaque, vous pouvez dévoiler
 * cette carte de votre main. Dans ce cas, l’Attaque n’a pas d’effet sur vous.
 */
public class Moat extends ReactionCard {

	public Moat() {
		super("Moat", 2);
	}

	public String toString() {
		return super.toString()
				+ "+2 Cartes. \n"
				+ " Lorsqu’un adversaire joue une carte Attaque, vous pouvez dévoiler cette carte de votre main."
				+ " Dans ce cas, l’Attaque n’a pas d’effet sur vous.";
	}

	public boolean reaction(Player player) {
		List<String> choice = new ArrayList<String>();
		choice.add("y");
		choice.add("n");
		String decision;
		decision = player.choose("Voulez-vous dévoiler votre carte Réaction ?",
				choice, false);

		if (decision.equalsIgnoreCase("y")) {
			play(player);
			return true;
		}

		else {
			return false;

		}

	}

	public void play(Player player) {
		int i = 0;
		while (i < 2) {
			player.add_hand(player.drawCard());
			i++;
		}
	}
}