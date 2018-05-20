import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.base.*;

/**
 * Classe pour l'ex�cution d'une partie de Dominion
 */
class Main {
	public static void main(String[] args) {
		// Noms des joueurs de la partie
		// (le nombre total de joueurs correspond au nombre de noms dans le 
		// tableau)
		String[] playerNames = new String[]{"Marco", "Polo"};
		// Pr�pare les piles "royaume" de la r�serve (hors cartes communes)
		List<CardList> kingdomStacks = new ArrayList<CardList>();
		CardList stack;
		// Ajouter un bloc pour chaque carte royaume � utiliser
		
		stack = new CardList();
		for (int i = 0; i < 10; i++) {
			stack.add(new Village());
		}
		kingdomStacks.add(stack);
		// Instancie et ex�cute une partie
		Game g = new Game(playerNames, kingdomStacks);
		System.out.println(g.isFinished());
		g.run();
	}
}