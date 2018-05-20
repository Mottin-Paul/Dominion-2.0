package dominion.card;

import java.util.*;

/**
 * Les cartes R�action Rmq: les cartes R�action sont toutes des cartes Action
 */

public abstract class ReactionCard extends ActionCard {

	public ReactionCard(String name, int cost) {
		super(name, cost);

	}

	public List<CardType> getTypes() {
		List<CardType> type_list = super.getTypes();
		type_list.add(CardType.Reaction);
		return type_list;
	}
}