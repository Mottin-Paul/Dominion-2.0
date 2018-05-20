package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte R�novation (Remodel)
 * 
 * �cartez une carte de votre main.
 * Recevez une carte co�tant jusqu'� 2 Pi�ces de plus que la carte �cart�e.
 */
public class Remodel extends ActionCard {
	
	public Remodel() {
		super("Remodel",4);
	}
	
	public String toStrin() {
		return super.toString() + " �cartez une carte de votre main.\n" + 
				" Recevez une carte co�tant jusqu'� 2 Pi�ces de plus que la carte �cart�e.";
	}
	
	public void play(Player player) {
		// Initialisation de la d�cision, de la carte a �cart� et des cartes de la r�serve valables
		String decision;
		Card card_trash;
	
		CardList card_available = new CardList();
		
		// Si la main du joueur n'est pas vide
		if(!player.cardsInHand().isEmpty()) {
			decision = player.chooseCard("Choisissez la carte que vous voulez �carter", player.cardsInHand(), false);
			card_trash = player.cardsInHand().getCard(decision); // On r�cupere la carte que l'on veut �carter
			player.removeHand(card_trash.getName()); // On la retire de la main
			player.getGame().addTrashCard(card_trash); // On l'�carte
			
					
			// Pour chaque carte disponible dans la r�serve
			int i = 0;
			while( i<player.getGame().availableSupplyCards().size()) {
				// Si la carte a au moins un prix qui est sup�rieur de 2 pi�ces au moins � celle �carter
				if(player.getGame().availableSupplyCards().get(i).getCost() <= card_trash.getCost() + 2 )   
					card_available.add(player.getGame().availableSupplyCards().get(i)); // On stocke cette carte dans la liste des cartes valables				
			i++;
			}
			
			
			decision = player.chooseCard("Choisissez la carte que vous d�sirez", card_available, false);		
			player.gain(decision); // Le joueur gagne la carte de son choix	
		}
	}
}
