import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.base.*;

/**
 * Classe pour l'exécution d'une partie de Dominion
 */
class Main {
	public static void main(String[] args) {
		// Noms des joueurs de la partie
		// (le nombre total de joueurs correspond au nombre de noms dans le 
		// tableau)
		String[] player_names = new String[]{"Marco", "Polo"};
		
		// Prépare les piles "royaume" de la réserve (hors cartes communes)
		List<CardList> kingdom_stacks = new ArrayList<CardList>();
		CardList Adventurer = new CardList();
		CardList Bureaucrat = new CardList();
		CardList Chancellor = new CardList();
		CardList Feast = new CardList();
		CardList Gardens = new CardList();
		CardList Laboratory = new CardList();	
		CardList Moneylender = new CardList();
		CardList Spy = new CardList();
		CardList Village = new CardList();
		CardList Workshop = new CardList();
		
		// Ajouter un bloc pour chaque carte royaume à utiliser
		// kingdomStacks.add(stack);
		// stack = new CardList();
		for (int i = 0; i < 10; i++) {
			Adventurer.add(new Adventurer());
			Bureaucrat.add(new Bureaucrat());
			Chancellor.add(new Chancellor());
			Feast.add(new Feast());
			Gardens.add(new Gardens());
			Laboratory.add(new Laboratory());
			Moneylender.add(new Moneylender());
			Spy.add(new Spy());
			Village.add(new Village());
			Workshop.add(new Workshop());
			
			kingdom_stacks.add(Adventurer);
			kingdom_stacks.add(Bureaucrat);
			kingdom_stacks.add(Chancellor);
			kingdom_stacks.add(Feast);
			kingdom_stacks.add(Gardens);
			kingdom_stacks.add(Laboratory);
			kingdom_stacks.add(Moneylender);
			kingdom_stacks.add(Spy);
			kingdom_stacks.add(Village);
			kingdom_stacks.add(Workshop);
		}
		
		// Instancie et exécute une partie
		Game g = new Game(player_names, kingdom_stacks);
		g.run();
	}
}