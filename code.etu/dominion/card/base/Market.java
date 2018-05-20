package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Marché (Market)
 *
 * +1 Carte.
 * +1 Action.
 * +1 Achat.
 * +1 Pièce.
 */
public class Market extends ActionCard {
  
	public Market() { super("Market", 5);	}
	@Override
  public void play(Player player) {
		player.get_hand().add(player.drawCard());
		player.incrementActions(1);
		player.incrementBuys(1);
		player.incrementMoney(1);
}

}