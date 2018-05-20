package dominion.card;

import java.util.*;

/**
 * Les cartes Victoire
 */
public abstract class VictoryCard extends Card {

	public VictoryCard(String name, int cost) {
		super(name, cost);
	}

	public String toString() {
		return super.toString();
	}

	public List<CardType> getTypes() {
		List<CardType> type_list = super.getTypes();
		type_list.add(CardType.Victory);
		return type_list;
	}
}