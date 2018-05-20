package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Chancellier (Chancellor)
 *
 * +2 Pi�ces.
 * Vous pouvez imm�diatement d�fausser votre deck.
 */
public class Chancellor extends ActionCard {

  public Chancellor() { super("Chancellor", 3);	}
  @Override
  public void play(Player player) {
	player.incrementMoney(2);
    String decision = player.choose("Voulez-vous d�fausser votre deck?", Arrays.asList("y","n"), false);
    if (decision.equals("y")){    	
    	player.getDraw().transferTo(player.getDiscard());    	
    } 
}
  
}