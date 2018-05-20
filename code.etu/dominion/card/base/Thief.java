package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Voleur (Thief)
 * 
 * Tous vos adversaires dévoilent les 2 premières cartes de leur deck. 
 * S'ils dévoilent des cartes Trésor, ils en écartent 1 de votre choix. 
 * Parmi ces cartes Trésor écartées, recevez celles de votre choix. 
 * Les autres cartes dévoilées sont défaussées.
 */
public class Thief extends AttackCard {
	
	public Thief(){
		super("Thief",4);
	}
	
	public String toString() {
		return super.toString() + " Tous vos adversaires dévoilent les 2 premières cartes de leur deck.\n" + 
				" S'ils dévoilent des cartes Trésor, ils en écartent 1 de votre choix.\n" + 
				" Parmi ces cartes Trésor écartées, recevez celles de votre choix.\n" + 
				" Les autres cartes dévoilées sont défaussées.";
	}
	
	public void play(Player player){
		// Initialisation des adversaire, de la décision, des listes de cartes du joueur, à écarter et trésors
		List<Player> adversary = new ArrayList<Player>();
		adversary = player.getGame().otherPlayers(player);
		
		CardList card_player = new CardList();
		CardList card_trashed = new CardList();
		CardList treasure_card = new CardList();
		String decision;
		
		
		// Pour chaque adversaires
		int i = 0;
		while( i<adversary.size()){
			// Si l'adversaire a dans sa main une carte Moat
			if(player.otherPlayers().get(i).cardsInHand().getCard("Moat") != null) {
				// On appelle la fonction reaction de la carte qui va demander a l'adversaire si il veut utiliser sa carte Moat
				if(!((Moat) player.otherPlayers().get(i).cardsInHand().getCard("Moat")).reaction(player.otherPlayers().get(i))) {
					// On récupere la pioche de l'adversaire
					card_player = adversary.get(i).get_draw();
					
					// Pour les 2 premieres cartes de sa pioche
					int j = 0; 
					while( j<2 ){
						if(card_player.get(j).getTypes().get(0) == CardType.Treasure){ // Si la carte est de type trésor
							treasure_card.add(card_player.get(j)); // On stocke cette carte dans la liste des cartes trésors
						}
						else {
							adversary.get(i).add_discard(card_player.get(j)); // Sinon on défausse cette carte
						}
						j++;
					}
					
					if(treasure_card.size() == 1){ // Si il n'y a qu'une carte trésor
						card_trashed.add(treasure_card.get(0)); // On stocke cette carte dans la liste des cartes a écarter
						adversary.get(i).remove_draw(treasure_card.get(0).getName()); // On l'enve de la pioche de l'adversaire
					}
					
					else if(treasure_card.size() == 2){ // Si il y a 2 cartes trésors
						decision = player.chooseCard("Choisissez quelle carte vous voulez écarter", treasure_card, false);	
						card_trashed.add(treasure_card.getCard(decision)); // On stocke celle que l'on veut écarter
						adversary.get(i).remove_draw(treasure_card.getCard(decision).getName()); // On l'a retire de la pioche de l'adversaire
						treasure_card.remove(treasure_card.getCard(decision)); // On retire de la liste des cartes trésors celle que l'on veut écarter
						adversary.get(i).remove_draw(treasure_card.get(0).getName()); // On retire de la pioche la seule carte trésor qui reste dans la liste des cartes trésors
						adversary.get(i).add_discard(treasure_card.get(0)); // On défausse la carte restante
					}	
					treasure_card.clear(); // On vide la liste des cartes trésors
					card_player.clear(); // On vide la liste des cartes de l'adversaire
				}
			}
			else {
				// On récupere la pioche de l'adversaire
				card_player = adversary.get(i).get_draw();
				
				// Pour les 2 premieres cartes de sa pioche
				int j2 = 0;
				while( j2<2){
					if(card_player.get(j2).getTypes().get(0) == CardType.Treasure){ // Si la carte est de type trésor
						treasure_card.add(card_player.get(j2)); // On stocke cette carte dans la liste des cartes trésors
					}
					else {
						adversary.get(i).add_discard(card_player.get(j2)); // Sinon on défausse cette carte
					}
					j2++;
				}
				
				if(treasure_card.size() == 1){ // Si il n'y a qu'une carte trésor
					card_trashed.add(treasure_card.get(0)); // On stocke cette carte dans la liste des cartes a écarter
					adversary.get(i).remove_draw(treasure_card.get(0).getName()); // On l'enve de la pioche de l'adversaire
				}
				
				else if(treasure_card.size() == 2){ // Si il y a 2 cartes trésors
					decision = player.chooseCard("Choisissez quelle carte vous voulez écarter", treasure_card, false);	
					card_trashed.add(treasure_card.getCard(decision)); // On stocke celle que l'on veut écarter
					adversary.get(i).remove_draw(treasure_card.getCard(decision).getName()); // On l'a retire de la pioche de l'adversaire
					treasure_card.remove(treasure_card.getCard(decision)); // On retire de la liste des cartes trésors celle que l'on veut écarter
					adversary.get(i).remove_draw(treasure_card.get(0).getName()); // On retire de la pioche la seule carte trésor qui reste dans la liste des cartes trésors
					adversary.get(i).add_discard(treasure_card.get(0)); // On défausse la carte restante
				}	
				treasure_card.clear(); // On vide la liste des cartes trésors
				card_player.clear(); // On vide la liste des cartes de l'adversaire
				
			}
		i++;	
		}
		
		
		int i2 = 0;
		
		// Si la liste des cartes écartés n'est pas vide
		if(!card_trashed.isEmpty()) {
			// Tant qu'il reste des cartes dans cardTrashed
			while(i2<card_trashed.size()) {
				decision = player.chooseCard("Choisissez les cartes que vous voulez recevoir", card_trashed, false);
				// On ajoute dans la défausse du joueur la carte choisit
				player.add_discard(card_trashed.getCard(decision));
				// On retire de la liste des cartes écartés celle que l'on a choisit
				card_trashed.remove(card_trashed.getCard(decision));	
				i2++;
			}
		}
		
	}
}