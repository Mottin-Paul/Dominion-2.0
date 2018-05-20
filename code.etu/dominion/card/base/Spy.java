package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Espion (Spy)
 * 
 * +1 Carte.
 * +1 Action.
 * Tous les joueurs (vous aussi) dévoilent la première carte de leur deck. Vous décidez ensuite si chaque carte dévoilée est défaussée ou replacée sur son deck.
 */
public class Spy extends AttackCard {
	
	public Spy() {
		super("Spy",4);
	}
	
	public String toString() {
		return super.toString() + " +1 Carte.\n" 
				+ " +1 Action.\n" 
				+ " Tous les joueurs (vous aussi) dévoilent la première carte de leur deck. "
				+ " Vous décidez ensuite si chaque carte dévoilée est défaussée ou replacée sur son deck.";
	}
	
	public void play(Player player) {
		// Le joueur pioche une fois
		player.add_hand(player.drawCard());
		// Incrémente le nombre d'action de 1
		player.incrementActions(1);
		
		// Initialisation de la 1ere carte du joueur, des joueurs et des choix
		Card first_card_player;
		List<Player> player_game = new ArrayList<Player>();
		List<String> choice = new ArrayList<String>();
		choice.add("y");
		choice.add("n");
			
		// Pour chaque joueur présent dans la partie
		int i = 0; 
		while(i<player.getGame().numberOfPlayers()) {	
			
			// On ajoute le joueur i dans la liste
			player_game.add(player.getGame().getPlayer(i));
			
			// Si la pioche du joueur n'est pas vide
			if(!player_game.get(i).get_draw().isEmpty()){
				first_card_player = player_game.get(i).get_draw().get(0); // On récupere la 1ere carte de sa pioche
			}
			// Sinon
			else{
				first_card_player = player_game.get(i).get_discard().get(0); // On récupere la 1ere carte de sa défausse
				player_game.get(i).remove_discard(first_card_player.getName()); // On retire cette carte de sa défausse
			}
				
			String decision;
			// Si l'adversaire a dans sa main une carte Moat
			if(player_game.get(i).cardsInHand().getCard("Moat") != null) {
				// On appelle la fonction reaction de la carte qui va demander a l'adversaire si il veut utiliser sa carte Moat
				if(!((Moat) player_game.get(i).cardsInHand().getCard("Moat")).reaction(player_game.get(i))) {
					decision = player.choose("Voulez vous défaussez cette carte ou la replacer sur son deck ?", choice, false);
					// Si le joueur souhaite défausser cette carte
					if(decision.equalsIgnoreCase("y")) {
						player_game.get(i).remove_draw(first_card_player.getName()); // On enleve la carte de la pioche de l'adversaire
						player_game.get(i).add_discard(first_card_player);	// On ajoute la carte dans sa défausse
					}
					
					// Si le joueur choisit non et que ce n'est pas le joueur qui a joué la carte Spy
					if(decision.equalsIgnoreCase("n") && !player_game.get(i).equals(player_game.get(0))){
						player_game.get(i).add_draw(first_card_player); // On remet la carte dans la pioche
					}
				}
			}
			else {
				decision = player.choose("Voulez vous défaussez cette carte ou la replacer sur son deck ?", choice, false);
				// Si le joueur souhaite défausser cette carte
				if(decision.equalsIgnoreCase("y")) {
					player_game.get(i).remove_draw(first_card_player.getName()); // On enleve la carte de la pioche de l'adversaire
					player_game.get(i).add_discard(first_card_player);	// On ajoute la carte dans sa défausse
				}
				
				// Si le joueur choisit non et que ce n'est pas le joueur qui a joué la carte Spy
				if(decision.equalsIgnoreCase("n") && !player_game.get(i).equals(player_game.get(0))){
					player_game.get(i).add_draw(first_card_player); // On remet la carte dans la pioche
				}
			}			
		i++;		
		}
					
	}
	
}