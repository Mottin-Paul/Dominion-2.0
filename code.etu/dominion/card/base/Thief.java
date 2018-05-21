package dominion.card.base;

import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Voleur (Thief)
 * 
 * Tous vos adversaires d�voilent les 2 premi�res cartes de leur deck. S'ils
 * d�voilent des cartes Tr�sor, ils en �cartent 1 de votre choix. Parmi ces
 * cartes Tr�sor �cart�es, recevez celles de votre choix. Les autres cartes
 * d�voil�es sont d�fauss�es.
 */
public class Thief extends AttackCard {

	public Thief() {
		super("Thief", 4);
	}

	public String toString() {
		return super.toString()
				+ " Chaque joueur adverse d�voile les deux premi�res cartes de son deck.\n"
				+ " Chaque joueur qui d�voile une ou plusieurs cartes tr�sor doit en �liminer une (que vous choisissez).\n"
				+ " Vous pouvez prendre autant de cartes �limin�es que vous le voulez pour les poser sur votre d�fausse."
				+ " Les cartes d�voil�es restantes retournent dans les d�fausses respectives des joueurs.";
	}

	public void play(Player player) {
		List<Player> adversary = new ArrayList<Player>();
		adversary = player.getGame().otherPlayers(player);

		CardList card_player = new CardList();
		CardList card_trashed = new CardList();
		CardList treasure_card = new CardList();
		String decision;

		int i = 0;
		while (i < adversary.size()) {
			if (player.otherPlayers().get(i).cardsInHand().getCard("Moat") != null) {
				if (!((Moat) player.otherPlayers().get(i).cardsInHand()
						.getCard("Moat"))
						.reaction(player.otherPlayers().get(i))) {
					card_player = adversary.get(i).get_draw();

					int j = 0;
					while (j < 2) {
						if (card_player.get(j).getTypes().get(0) == CardType.Treasure) {
							treasure_card.add(card_player.get(j));
						} else {
							adversary.get(i).add_discard(card_player.get(j));
						}
						j++;
					}

					if (treasure_card.size() == 1) {
						card_trashed.add(treasure_card.get(0));
						adversary.get(i).remove_draw(
								treasure_card.get(0).getName());
					}

					else if (treasure_card.size() == 2) {
						decision = player.chooseCard(
								"Choisissez quelle carte vous voulez �carter",
								treasure_card, false);
						card_trashed.add(treasure_card.getCard(decision));
						adversary.get(i).remove_draw(
								treasure_card.getCard(decision).getName());
						treasure_card.remove(treasure_card.getCard(decision));
						adversary.get(i).remove_draw(
								treasure_card.get(0).getName());
						adversary.get(i).add_discard(treasure_card.get(0));
					}
					treasure_card.clear();
					card_player.clear();
				}
			} else {
				card_player = adversary.get(i).get_draw();
				int j2 = 0;
				while (j2 < 2) {
					if (card_player.get(j2).getTypes().get(0) == CardType.Treasure) {
						treasure_card.add(card_player.get(j2));
					} else {
						adversary.get(i).add_discard(card_player.get(j2));
					}
					j2++;
				}

				if (treasure_card.size() == 1) {
					card_trashed.add(treasure_card.get(0));
					adversary.get(i)
							.remove_draw(treasure_card.get(0).getName());
				}

				else if (treasure_card.size() == 2) {
					decision = player.chooseCard(
							"Choisissez quelle carte vous voulez �carter",
							treasure_card, false);
					card_trashed.add(treasure_card.getCard(decision));
					adversary.get(i).remove_draw(
							treasure_card.getCard(decision).getName());
					treasure_card.remove(treasure_card.getCard(decision));
					adversary.get(i)
							.remove_draw(treasure_card.get(0).getName());
					adversary.get(i).add_discard(treasure_card.get(0));
				}
				treasure_card.clear();
				card_player.clear();

			}
			i++;
		}

		int i2 = 0;

		if (!card_trashed.isEmpty()) {
			while (i2 < card_trashed.size()) {
				decision = player.chooseCard(
						"Choisissez les cartes que vous voulez recevoir",
						card_trashed, false);
				player.add_discard(card_trashed.getCard(decision));
				card_trashed.remove(card_trashed.getCard(decision));
				i2++;
			}
		}

	}
}