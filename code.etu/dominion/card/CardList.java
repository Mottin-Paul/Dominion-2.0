package dominion.card;

import java.util.*;

/**
 * Liste de cartes
 */
@SuppressWarnings("serial")
public class CardList extends ArrayList<Card> {

	/**
	 * Constructeur vide
	 */
	public CardList() {
		super();
	}

	/**
	 * Constructeur à partir d'une liste de cartes
	 */
	public CardList(List<Card> l) {
		super(l);
	}

	/**
	 * Mélange la liste
	 */
	public void shuffle() {
		Collections.shuffle(this);
	}

	/**
	 * Retire une carte de la liste dont le nom est égal à l'argument passé
	 * 
	 * @param cardName
	 *            le nom de la carte à retirer
	 * @return la carte retirée si elle a été trouvée, {@code null} sinon
	 */
	public Card remove(String card_name) {
		for (Card c : this) {
			if (c.getName().equals(card_name)) {
				this.remove(c);
				return c;
			}
		}
		return null;
	}

	/**
	 * Renvoie une carte de la liste dont le nom est égal à l'argument
	 * 
	 * @param cardName
	 *            le nom de la carte à chercher
	 * @return une carte de la liste ayant le nom cherché si elle existe,
	 *         {@code null} sinon
	 */
	public Card getCard(String card_name) {
		for (Card c : this) {
			if (c.getName().equals(card_name)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Représentation de la liste sous forme d'une chaîne de caractères
	 * 
	 * Cette fonction renvoie une chaîne de caractères constituée des noms des
	 * cartes de la liste séparées par ", ". Par exemple, si la liste contient
	 * une carte Village et une carte Copper, la fonction renvoie la chaîne
	 * "Village, Copper"
	 */
	public String toString() {
		if (this.size() == 0) {
			return "";
		}

		String r = "";
		for (Card card : this) {
			r += card.toString() + ", ";
		}
		return r.substring(0, r.length() - 2);
	}

	/**
	 * Transfére toutes les cartes de cette instance vers la CardList cible
	 * 
	 * @param cl
	 *            Liste de carte cible (où les cartes seront transférés)
	 */
	public void transferTo(CardList cl) {
		cl.addAll(this);
		this.clear();
	}

	/**
	 * Transfére la carte c de cette instance vers la CardList cible
	 * 
	 * @param cl
	 *            Liste de carte cible (où les cartes seront transférés)
	 */
	public void transferTo(Card c, CardList cl) {
		if (this.contains(c)) {
			this.remove(c);
			cl.add(c);
		} else {
			System.err
					.println("transferTo(card c,CardList cl) : carte non-présente");
		}
	}

	/**
	 * Transfére la carte c de cette instance vers la CardList cible
	 * 
	 * @param cl
	 *            Liste de carte cible (où les cartes seront transférés)
	 * @param index
	 *            Index de l'emplacement ciblé
	 */
	public void transferTo(Card c, CardList cl, int index) {
		if (this.contains(c)) {
			this.remove(c);
			cl.add(index, c);
		} else {
			System.err
					.println("transferTo(card c,CardList cl) : carte non-présente");
		}
	}
}