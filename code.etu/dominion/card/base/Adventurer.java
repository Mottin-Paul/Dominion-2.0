package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Aventurier (Adventurer)
 * 
 * D�voilez des cartes de votre deck jusqu'� ce que 2 cartes Tr�sor soient d�voil�es. Ajoutez ces cartes Tr�sor � votre main et d�faussez les autres cartes d�voil�es.
 */
public class Adventurer extends ActionCard {
	
	public Adventurer() { super("Adventurer", 6);	}
	@Override
	public void play(Player player) {
		int nb_card_revealed=0;
		while (nb_card_revealed<2) {
			Card card = player.drawCard();
			if(card !=null) {
				card.toString();
				if(card.getTypes().contains(CardType.Treasure)) {
					player.get_hand().add(card);
					nb_card_revealed++;
				}
				else {
					player.gain(card);
				}
			}
			
		}
		
	}
}