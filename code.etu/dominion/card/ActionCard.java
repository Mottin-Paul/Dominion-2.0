package dominion.card;

import java.util.*;

/**
 * Les cartes Action
 */
public abstract class ActionCard extends Card {

	public ActionCard(String name, int cost) {
		super(name, cost);
	}

	public String toString() {
		return super.toString();
	}

	public List<CardType> getTypes() {
		List<CardType> type_list = super.getTypes();
		type_list.add(CardType.Action);
		return type_list;
	}
}