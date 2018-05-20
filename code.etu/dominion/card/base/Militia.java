package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Milice (Militia)
 * 
 * 2 Pi�ces.
 * Tous vos adversaires d�faussent leurs cartes de fa�on � n'avoir que 3 cartes en main.
 */
public class Militia extends AttackCard {
	public Militia() { super("Militia", 4);	}

	@Override
	public void play(Player player) {
		player.incrementMoney(2);
		for(Player loop_p : player.otherPlayers()) {
			while(loop_p.getHand().size()>3) {
				String choosed_card_name=loop_p.chooseCard("Choisissez une carte � d�fausser", loop_p.getHand(), false);
				loop_p.getHand().transferTo(loop_p.getHand().getCard(choosed_card_name), loop_p.getDiscard());
			}
		}		
	}
}