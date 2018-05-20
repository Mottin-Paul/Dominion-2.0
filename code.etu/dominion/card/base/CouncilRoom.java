package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Chambre du conseil (Council Room)
 * 
 * +4 Cartes.
 * +1 Achat.
 * Tous vos adversaires piochent 1 carte.
 */
public class CouncilRoom extends ActionCard {
	
	
	public CouncilRoom() { 
		super("Council Room", 2);		
	}
	
	public String toString() {
		return super.toString() + " +4 Cartes." + 
				" +1 Achat.\n" + 
				" Tous vos adversaires piochent 1 carte.";
	}
	
	public void play(Player player) {
		// Incrémente le nombre d'achat de 1
		player.incrementBuys(1);
		
		// Le joueur pioche 4 fois
		int i = 0;
		while( i<4 ){
			player.add_hand(player.drawCard());
				i++;
		}	
		
		// Tous les adversaires du joueur pioche une carte
		int i2=0;
		while(i2<player.getGame().otherPlayers(player).size()){
			player.getGame().otherPlayers(player).get(i2).add_hand(player.getGame().otherPlayers(player).get(i2).drawCard());
		i2++;
		}
	}
}