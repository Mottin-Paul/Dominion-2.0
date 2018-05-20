package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Festival
 *
 * +2 Actions.
 * +1 Achat.
 * +2 Pi�ces.
 */
public class Festival extends ActionCard {

	public Festival() { super("Festival", 5);	}
  @Override
  public void play(Player player) {
    player.incrementActions(2);
    player.incrementBuys(1);
    player.incrementMoney(2);
  }
}