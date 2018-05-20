package dominion.card;

import java.util.ArrayList;
import java.util.List;

import dominion.Player;

/**
 * Repr�sentation des cartes du jeu Dominion
 */
public abstract class Card {
	/**
	 * Le nom de la carte
	 */
	private String name;

	/**
	 * Le co�t de la carte � l'achat
	 */
	private int cost;

	/**
	 * Constructeur simple
	 * 
	 * @param name
	 *            le nom de la carte
	 * @param cost
	 *            le co�t de la carte
	 */
	public Card(String name, int cost) {
		this.name = name;
		this.cost = cost;
	}

	/**
	 * Getters et setters
	 */
	public int getCost() {
		return this.cost;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * Renvoie une liste des types de la carte (�l�ments de {@code CardType})
	 *
	 * Le type d'une carte d�pend de la sous-classe de {@code Card} � laquelle
	 * la carte appartient. Ici, la m�thode se contente donc de renvoyer un
	 * {@code ArrayList} vide, auquel les sous-classes ajouteront les types.
	 */
	public List<CardType> getTypes() {

		List<CardType> typeList;
		typeList = new ArrayList<CardType>();
		return typeList;
	}

	/**
	 * Renvoie une repr�sentation de la carte sous forme de cha�ne de caract�res
	 * (ici la fonction renvoie le nom de la carte)
	 */
	public String toString() {
		return this.name;
	}

	/**
	 * Ex�cute l'effet de la carte, jou�e par le joueur {@code p}
	 *
	 * @param p
	 *            joueur qui ex�cute l'effet de la carte
	 *
	 *            L'action de cette m�thode d�pend de la classe de la carte.
	 */
	public abstract void play(Player p);

	/**
	 * Renvoie la valeur de la carte en points de victoire (c'est cette m�thode
	 * qui est appel�e sur toutes les cartes du deck d'un joueur pour d�terminer
	 * le score du joueur en fin de partie)
	 *
	 * @param p
	 *            joueur qui poss�de la carte (la valeur d'une carte peut
	 *            d�pendre du joueur qui la poss�de, c'est le cas des cartes
	 *            Gardens)
	 *
	 *            Toutes les cartes qui ne sont pas de type Victoire ont une
	 *            valeur de 0 (la m�thode devra donc �tre red�finie pour les
	 *            cartes ayant une valeur non nulle).
	 */
	public int victoryValue(Player p) {
		return 0;
	}

}