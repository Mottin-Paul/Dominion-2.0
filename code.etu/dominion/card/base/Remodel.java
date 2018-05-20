package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Rénovation (Remodel)
 * 
 * Écartez une carte de votre main.
 * Recevez une carte coûtant jusqu'à 2 Pièces de plus que la carte écartée.
 */
public class Remodel extends ActionCard {
	
	public Remodel() {
		super("Remodel",4);
	}
	
	public String toStrin() {
		return super.toString() + " Écartez une carte de votre main.\n" + 
				" Recevez une carte coûtant jusqu'à 2 Pièces de plus que la carte écartée.";
	}
	
	public void play(Player player) {
		// Initialisation de la décision, de la carte a écarté et des cartes de la réserve valables
		String decision;
		Card card_trash;
	
		CardList card_available = new CardList();
		
		// Si la main du joueur n'est pas vide
		if(!player.cardsInHand().isEmpty()) {
			decision = player.chooseCard("Choisissez la carte que vous voulez écarter", player.cardsInHand(), false);
			card_trash = player.cardsInHand().getCard(decision); // On récupere la carte que l'on veut écarter
			player.removeHand(card_trash.getName()); // On la retire de la main
			player.getGame().addTrashCard(card_trash); // On l'écarte
			
					
			// Pour chaque carte disponible dans la réserve
			int i = 0;
			while( i<player.getGame().availableSupplyCards().size()) {
				// Si la carte a au moins un prix qui est supérieur de 2 pièces au moins à celle écarter
				if(player.getGame().availableSupplyCards().get(i).getCost() <= card_trash.getCost() + 2 )   
					card_available.add(player.getGame().availableSupplyCards().get(i)); // On stocke cette carte dans la liste des cartes valables				
			i++;
			}
			
			
			decision = player.chooseCard("Choisissez la carte que vous désirez", card_available, false);		
			player.gain(decision); // Le joueur gagne la carte de son choix	
		}
	}
}
