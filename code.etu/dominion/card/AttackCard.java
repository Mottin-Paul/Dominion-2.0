package dominion.card;
import java.util.*;

import dominion.*;

/**
 * Les cartes Attaque
 * Rmq: les cartes Attaque sont toutes des cartes Action
 */


public abstract class AttackCard extends ActionCard {

  public AttackCard(String name, int cost){
    super(name, cost);
  }

  public String toString(){
    return super.toString();
  }
  
  public List<CardType> getTypes(){
	List<CardType> type_list = super.getTypes();
	type_list.add(CardType.Attack);
    return type_list;
  }
}