package dominion.card;

import java.util.*;

/**
 * Les cartes Trésor
 */
public abstract class TreasureCard extends Card {

	public CardType type;
	public List<CardList> Treasure_list;

	public TreasureCard(String card_name, int cost) {
		super(card_name, cost);
		this.type = CardType.Treasure;

	}

	public String toString() {
		return super.toString();
	}

	public List<CardType> getTypes() {
		List<CardType> type_liste = super.getTypes();
		type_liste.add(CardType.Treasure);
		return type_liste;
	}
}