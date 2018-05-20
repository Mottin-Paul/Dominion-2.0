package dominion.card.common;
import dominion.*;
import dominion.card.*;

/**
 * Carte Cuivre (Copper)
 * 
 * 1 Pièce
 */
public class Copper extends TreasureCard {
	public Copper() { super("Copper", 0);	}
	
	public int treasureValue() {
		return 1;
	}

	@Override
	public void play(Player player) {
		player.incrementMoney(this.treasureValue());
		
	}
}