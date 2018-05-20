package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Chapelle (Chapel)
 * 
 * Écartez jusqu'à 4 cartes de votre main.
 */
public class Chapel extends ActionCard {
	
	public Chapel() {
		super("Chapel",2);
	}
	
	public String toString() {
		return super.toString() + "Écartez jusqu'à 4 cartes de votre main.";
	}
	
	public void play(Player player) {
		
		int nb_remove = 0;
		String decision = "noChoice";
		CardList card_in_hand = new CardList();
		for(Card card : player.cardsInHand()){
			if(card != null){
				card_in_hand.add(card);
			}
		}
		CardList card_to_remove = new CardList();
		
		while(nb_remove < 4 && decision != "") {
			decision = player.chooseCard("Choisissez la carte que vous voulez écarter, pour arreter appuyez sur entrée directement", card_in_hand, true);
			if(!decision.equalsIgnoreCase("")) {	
				card_to_remove.add(card_in_hand.getCard(decision));
				nb_remove++;
			}
		}
		if(card_to_remove.size() != 0){
			int i = 0;
			while( i<card_to_remove.size()) {
				player.getGame().setTrashCard(card_to_remove.get(i));
				player.removeHand(card_to_remove.get(i).getName());
				i++;
			}
		}
	}
}
