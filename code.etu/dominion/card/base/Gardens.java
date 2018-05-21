package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Jardins (Gardens)
 * 
 * Vaut 1VP pour chaque 10 cartes dans votre deck (arrondi � l'unit�
 * inf�rieure).
 */
public class Gardens extends VictoryCard {

	private int nbVictory;

	public Gardens() {
		super("Jardins", 4);
	}

	public String toString() {
		return super.toString()
				+ " 1 point de victoire pour chaque s�rie de 10 cartes dans le jeu complet (arrondi � l'inf�rieur).";
	}

	public int victoryValue(Player player) {
		play(player);
		return this.nbVictory;
	}

	public void play(Player player) {
		this.nbVictory = player.totalCards().size() / 10;
	}
}