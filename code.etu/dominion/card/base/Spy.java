package dominion.card.base;

import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Espion (Spy)
 * 
 * +1 Carte. +1 Action. Tous les joueurs (vous aussi) dévoilent la première
 * carte de leur deck. Vous décidez ensuite si chaque carte dévoilée est
 * défaussée ou replacée sur son deck.
 */
public class Spy extends AttackCard {

	public Spy() {
		super("Spy", 4);
	}

	public String toString() {
		return super.toString()
				+ " +1 Carte.\n"
				+ " +1 Action.\n"
				+ " Tous les joueurs (vous aussi) dévoilent la première carte de leur deck. "
				+ " Vous décidez ensuite si chaque carte dévoilée est défaussée ou replacée sur son deck.";
	}

	public void play(Player player) {
		player.add_hand(player.drawCard());
		player.incrementActions(1);

		Card first_card_player;
		List<Player> player_game = new ArrayList<Player>();
		List<String> choice = new ArrayList<String>();
		choice.add("y");
		choice.add("n");

		int i = 0;
		while (i < player.getGame().numberOfPlayers()) {
			player_game.add(player.getGame().getPlayer(i));
			if (!player_game.get(i).get_draw().isEmpty()) {
				first_card_player = player_game.get(i).get_draw().get(0);
			} else {
				first_card_player = player_game.get(i).get_discard().get(0);
				player_game.get(i).remove_discard(first_card_player.getName());
			}

			String decision;
			if (player_game.get(i).cardsInHand().getCard("Moat") != null) {
				if (!((Moat) player_game.get(i).cardsInHand().getCard("Moat"))
						.reaction(player_game.get(i))) {
					decision = player
							.choose("Voulez vous défaussez cette carte ou la replacer sur son deck ?",
									choice, false);
					if (decision.equalsIgnoreCase("y")) {
						player_game.get(i).remove_draw(
								first_card_player.getName());
						player_game.get(i).add_discard(first_card_player);
					}
					if (decision.equalsIgnoreCase("n")
							&& !player_game.get(i).equals(player_game.get(0))) {
						player_game.get(i).add_draw(first_card_player);
					}
				}
			} else {
				decision = player
						.choose("Voulez vous défaussez cette carte ou la replacer sur son deck ?",
								choice, false);

				if (decision.equalsIgnoreCase("y")) {
					player_game.get(i).remove_draw(first_card_player.getName());
					player_game.get(i).add_discard(first_card_player);
				}

				if (decision.equalsIgnoreCase("n")
						&& !player_game.get(i).equals(player_game.get(0))) {
					player_game.get(i).add_draw(first_card_player);
				}
			}
			i++;
		}

	}

}