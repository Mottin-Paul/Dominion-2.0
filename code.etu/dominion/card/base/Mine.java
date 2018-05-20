package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Mine
 * 
 * �cartez une carte Tr�sor de votre main. Recevez une carte Tr�sor co�tant jusqu'� 3 Pi�ces de plus ; ajoutez cette carte � votre main.
 */
public class Mine extends ActionCard {
	
	public Mine() {
		super("Mine",5);
	}
	
	public String toString() {
		return super.toString() + " " + " �cartez une carte Tr�sor de votre main."
				+ " Recevez une carte Tr�sor co�tant jusqu'� 3 Pi�ces de plus "
				+ " et ajoutez cette carte � votre main.";
	}

	public void play(Player player) {
		
		// Initialisation de la d�cision, de la carte tr�sor � �carter de la liste des cartes tr�sors valables
		CardList card_treasure_available = new CardList();
		String decision;
		Card card_trash;
		
		// Si le joueur a au moins une carte tr�sor dans sa main
		if(!player.getTreasureCards().isEmpty()) {
			decision = player.chooseCard("Choisissez la carte tr�sor que vous voulez �carter", player.getTreasureCards(), false);		
			card_trash = player.getTreasureCards().getCard(decision); // On r�cupere la carte que le joueur veut �carter
			player.remove_hand(card_trash.getName()); // On l'a retire de sa main
			player.getGame().addTrashCard(card_trash); // On l'�carte du jeu

			
			// Pour chaque carte disponible dans la r�serve
			int i = 0;
			while(i<player.getGame().availableSupplyCards().size()) {
				// Si la carte est de type tr�sor et elle a un co�t inf�rieur ou �gal � 3 pi�ces de plus que celle �carter
				if(player.getGame().availableSupplyCards().get(i).getTypes().get(0) == CardType.Treasure && player.getGame().availableSupplyCards().get(i).getCost() <= card_trash.getCost() + 3) {
					card_treasure_available.add(player.getGame().availableSupplyCards().get(i)); // On stocke cette carte dans la liste des cartes valables
				}
				i++;
			}	
			
			
			decision = player.chooseCard("Choisissez la carte tr�sor que vous voulez recevoir",card_treasure_available, false);
			player.add_hand(player.getGame().removeFromSupply(decision));	 // On ajoute dans la main la carte que le joueur a choisit et on la retire de la r�serve
		}
		
	}
	
}