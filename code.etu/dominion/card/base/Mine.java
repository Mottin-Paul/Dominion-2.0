package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Mine
 * 
 * Écartez une carte Trésor de votre main. Recevez une carte Trésor coûtant jusqu'à 3 Pièces de plus ; ajoutez cette carte à votre main.
 */
public class Mine extends ActionCard {
	
	public Mine() {
		super("Mine",5);
	}
	
	public String toString() {
		return super.toString() + " " + " Écartez une carte Trésor de votre main."
				+ " Recevez une carte Trésor coûtant jusqu'à 3 Pièces de plus "
				+ " et ajoutez cette carte à votre main.";
	}

	public void play(Player player) {
		
		// Initialisation de la décision, de la carte trésor à écarter de la liste des cartes trésors valables
		CardList card_treasure_available = new CardList();
		String decision;
		Card card_trash;
		
		// Si le joueur a au moins une carte trésor dans sa main
		if(!player.getTreasureCards().isEmpty()) {
			decision = player.chooseCard("Choisissez la carte trésor que vous voulez écarter", player.getTreasureCards(), false);		
			card_trash = player.getTreasureCards().getCard(decision); // On récupere la carte que le joueur veut écarter
			player.remove_hand(card_trash.getName()); // On l'a retire de sa main
			player.getGame().addTrashCard(card_trash); // On l'écarte du jeu

			
			// Pour chaque carte disponible dans la réserve
			int i = 0;
			while(i<player.getGame().availableSupplyCards().size()) {
				// Si la carte est de type trésor et elle a un coût inférieur ou égal à 3 pièces de plus que celle écarter
				if(player.getGame().availableSupplyCards().get(i).getTypes().get(0) == CardType.Treasure && player.getGame().availableSupplyCards().get(i).getCost() <= card_trash.getCost() + 3) {
					card_treasure_available.add(player.getGame().availableSupplyCards().get(i)); // On stocke cette carte dans la liste des cartes valables
				}
				i++;
			}	
			
			
			decision = player.chooseCard("Choisissez la carte trésor que vous voulez recevoir",card_treasure_available, false);
			player.add_hand(player.getGame().removeFromSupply(decision));	 // On ajoute dans la main la carte que le joueur a choisit et on la retire de la réserve
		}
		
	}
	
}