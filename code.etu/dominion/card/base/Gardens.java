package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Jardins (Gardens)
 * 
 * Vaut 1VP pour chaque 10 cartes dans votre deck (arrondi à l'unité
 * inférieure).
 */
public class Gardens extends VictoryCard {

	private int nbVictory;

	public Gardens() {
		super("Jardins", 4);
	}

	public String toString() {
		return super.toString()
				+ " Vaut 1VP pour chaque 10 cartes dans votre deck (arrondi à l'unité inférieure).";
	}

	public int victoryValue(Player player) {
		play(player);
		return this.nbVictory;
	}

	public void play(Player player) {
		this.nbVictory = player.totalCards().size() / 10;
	}
}