package dominion.card.base;

import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Bureaucrate (Bureaucrat)
 * 
 * Recevez une carte Argent, placez-la sur votre deck. Tous vos adversaires
 * dévoilent une carte Victoire et la placent sur leur deck (sinon ils dévoilent
 * leur main afin que vous puissiez voir qu'ils n'ont pas de cartes Victoire).
 */
public class Bureaucrat extends AttackCard {

	public Bureaucrat() {
		super("Bureaucrat", 4);
	}

	public String toString() {
		return super.toString()
				+ "Recevez une carte Argent, placez-la sur votre deck."
				+ " Tous vos adversaires dévoilent une carte Victoire et la placent sur leur deck,"
				+ " (sinon ils dévoilent leur main afin que vous puissiez voir qu'ils n'ont pas de cartes Victoire).";
	}

	@Override
	public void play(Player player) {
		Card card = player.getGame().getFromSupply("Silver");
		if (card != null) {
			player.get_draw().add(0, card);
		}
		List<Player> op;
		op = player.getGame().otherPlayers(player);
		for (Player loop_p : op) {
			CardList hand = loop_p.get_hand();
			CardList victory = new CardList();
			for (Card c_loop : hand) {
				if (c_loop.getTypes().contains(CardType.Victory)) {
					victory.add(c_loop);
				}
			}
			if (victory.size() > 0) {
				String choosed_card_name = loop_p
						.chooseCard(
								"Quelle carte voulez vous dévoiler et placer sur votre deck?",
								victory, false);
				Card choosed_card = victory.getCard(choosed_card_name);
				System.out.println(choosed_card.toString());
				hand.transferTo(choosed_card, loop_p.get_draw(), 0);
			} else {
				for (Card c_loop : hand) {
					System.out.println(c_loop.toString());
				}
			}
		}
	}
}