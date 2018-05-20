package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Salle du trône (Throne Room)
 * 
 * Choisissez 1 carte Action de votre main.
 * Jouez-la deux fois.
 */
public class ThroneRoom extends ActionCard {
	
	public ThroneRoom() {	
		super("Throne Room",4);
	}
	
	public String toString() {
		return super.toString() + " Choisissez 1 carte Action de votre main." + 
				" Jouez-la deux fois.";
	}
	
	public void play(Player player) {
		// Initialisation de la carte à jouer et de la décision	
		Card card_played;
		String decision;
		
		// Si le joueur a une carte action dans sa main
		if(!player.getActionCards().isEmpty()) {
			// Si il a au moins 2 cartes actions dans sa main, il aura la possibilité de rejouer la carte Throne Room
			if(player.getActionCards().size()>=2) {
				decision = player.chooseCard("Choisissez une carte Action de votre main", player.getActionCards(), false);
			}
			// Sinon on retire la carte Throne Room de sa main pour éviter qu'il puisse la rejouer 2 fois (il doit avoir au moins 2 cartes actions dans sa main pour la rejouer)
			else {
				player.getActionCards().remove("Throne Room");
				decision = player.chooseCard("Choisissez une carte Action de votre main", player.getActionCards(), false);
			}
			
			card_played = player.getActionCards().getCard(decision);
			
			player.playCard(card_played); // On joue la carte choisit
			player.remove_in_play(card_played.getName()); // On la retire du jeu
			player.playCard(card_played); // Et on la rejoue encore une fois
		}
		
		
	}
	
}