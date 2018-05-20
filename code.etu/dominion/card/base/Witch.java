package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Sorcière (Witch)
 *
 * +2 Cartes.
 * Tous vos adversaires recoivent une carte Curse.
 */
public class Witch extends AttackCard {
	public Witch() { super("Witch", 5);	}
  @Override
  public void play(Player player) {
	  int i=0;
    while(i<2){
    	player.get_hand().add(player.drawCard());
    	i++;
    }
    List<Player> op;
    op=player.getGame().otherPlayers(player);   
    for (Player loop_p : op){
      loop_p.get_discard().add(loop_p.getGame().getFromSupply("Curse"));
      loop_p.getGame().removeFromSupply("Curse");
    }
  }
}