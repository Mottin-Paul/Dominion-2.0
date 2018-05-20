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
	 * Constructeur � partir d'une liste de cartes
	 */
	public CardList(List<Card> l) {
		super(l);
	}

	/**
	 * M�lange la liste
	 */
	public void shuffle() {
		Collections.shuffle(this);
	}

	/**
	 * Retire une carte de la liste dont le nom est �gal � l'argument pass�
	 * 
	 * @param cardName
	 *            le nom de la carte � retirer
	 * @return la carte retir�e si elle a �t� trouv�e, {@code null} sinon
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
	 * Renvoie une carte de la liste dont le nom est �gal � l'argument
	 * 
	 * @param cardName
	 *            le nom de la carte � chercher
	 * @return une carte de la liste ayant le nom cherch� si elle existe,
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
	 * Repr�sentation de la liste sous forme d'une cha�ne de caract�res
	 * 
	 * Cette fonction renvoie une cha�ne de caract�res constitu�e des noms des
	 * cartes de la liste s�par�es par ", ". Par exemple, si la liste contient
	 * une carte Village et une carte Copper, la fonction renvoie la cha�ne
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
	 * Transf�re toutes les cartes de cette instance vers la CardList cible
	 * 
	 * @param cl
	 *            Liste de carte cible (o� les cartes seront transf�r�s)
	 */
	public void transferTo(CardList cl) {
		cl.addAll(this);
		this.clear();
	}

	/**
	 * Transf�re la carte c de cette instance vers la CardList cible
	 * 
	 * @param cl
	 *            Liste de carte cible (o� les cartes seront transf�r�s)
	 */
	public void transferTo(Card c, CardList cl) {
		if (this.contains(c)) {
			this.remove(c);
			cl.add(c);
		} else {
			System.err
					.println("transferTo(card c,CardList cl) : carte non-pr�sente");
		}
	}

	/**
	 * Transf�re la carte c de cette instance vers la CardList cible
	 * 
	 * @param cl
	 *            Liste de carte cible (o� les cartes seront transf�r�s)
	 * @param index
	 *            Index de l'emplacement cibl�
	 */
	public void transferTo(Card c, CardList cl, int index) {
		if (this.contains(c)) {
			this.remove(c);
			cl.add(index, c);
		} else {
			System.err
					.println("transferTo(card c,CardList cl) : carte non-pr�sente");
		}
	}
}