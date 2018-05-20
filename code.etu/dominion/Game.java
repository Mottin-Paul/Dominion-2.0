package dominion;
import java.util.*;
import dominion.card.*;
import dominion.card.common.*;

/**
 * Class repr�sentant une partie de Dominion
 */
public class Game {
	/**
	 * Tableau contenant les joueurs de la partie
	 */
	private Player[] players;
	
	/**
	 * Index du joueur dont c'est actuellement le tour
	 */
	private int currentPlayerIndex;
	
	/**
	 * Liste des piles dans la réserve du jeu.
	 * 
	 * On suppose ici que toutes les listes contiennent des copies de la même
	 * carte.
	 * Ces piles peuvent être vides en cours de partie si toutes les cartes de 
	 * la pile ont été achetées ou gagnées par les joueurs.
	 */
	private List<CardList> supplyStacks;
	
	/**
	 * Liste des cartes qui ont été écartées (trash)
	 */
	private CardList trashedCards;
	
	/**
	 * Scanner permettant de lire les entrées au clavier
	 */
	private Scanner scanner;
	
	/**
	 * Constructeur
	 * 
	 * @param playerNames liste des noms des joueurs qui participent à la 
	 * partie. Le constructeur doit créer les objets correspondant aux joueurs
	 * @param kingdomStacks liste de piles de réserve à utiliser correspondant 
	 * aux cartes "royaume" à utiliser dans la partie, auxquelles le 
	 * constructeur doit ajouter les piles "communes":
	 * - 60 Copper
	 * - 40 Silver
	 * - 30 Gold
	 * - 8 (si 2 joueurs) ou 12 (si 3 ou 4 joueurs) Estate, Duchy et Province 	 * - 10 * (n-1) Curse où n est le nombre de joueurs dans la partie
	 */
	
	// Cr�ation du stock de base des cartes
	private void Base_carte_stock(String[] player_names, List<CardList> kingdom_stacks) {
		
		CardList stock_base_copper = new CardList();
		int i = 0;
		while (i<60) {
			stock_base_copper.add(new Copper());
			i++;
		}
		
		CardList stock_base_silver = new CardList();
		int j = 0;
		while (j<40) {
			stock_base_silver.add(new Silver());
			j++;
		}
		
		CardList stock_base_gold = new CardList();
		int k = 0;
		while (k<30) {
			stock_base_gold.add(new Gold());
			k++;
		}
		
		CardList stock_base_estate = new CardList();
		CardList stock_base_duchy = new CardList();
		CardList stock_base_province = new CardList();
		int stock_base_carte_victoire = 0;
		
		if (player_names.length > 2 )
			stock_base_carte_victoire = 12;
		else 
			stock_base_carte_victoire = 8;
		
		int l = 0;
		while (l < stock_base_carte_victoire) {
			stock_base_estate.add(new Estate());
			stock_base_duchy.add(new Duchy());
			stock_base_province.add(new Province());
			l++;
		}
		
		CardList stock_base_curse = new CardList();
		int m = 0;
		while (m < (player_names.length-1)*10) {
			stock_base_curse.add(new Curse());
			m++;
		}
		
		this.supplyStacks = new ArrayList<CardList>();
		this.supplyStacks.add(stock_base_copper);
		this.supplyStacks.add(stock_base_silver);
		this.supplyStacks.add(stock_base_gold);
		this.supplyStacks.add(stock_base_estate);
		this.supplyStacks.add(stock_base_duchy);
		this.supplyStacks.add(stock_base_province);
		this.supplyStacks.add(stock_base_curse);
		for(CardList cl : kingdom_stacks)
			this.supplyStacks.add(cl);
		this.trashedCards = new CardList();
		
	}
	
	public Game(String[] player_names, List<CardList> kingdom_stacks) {
		
		this.players = new Player [player_names.length];
		this.currentPlayerIndex = 0;
		int i = 0;
		while (i < player_names.length) {
			Player player = new Player (player_names[i], this);
			this.players[i] = player;
			i++;
		}
		this.Base_carte_stock(player_names, kingdom_stacks);
		this.scanner = new Scanner (System.in);
	}
	
	/**
	 * Renvoie le joueur correspondant à l'indice passé en argument
	 * On suppose {@code index} est un indice valide du tableau 
	 * {@code this.players}
	 * 
	 * @param index indice dans le tableau des joueurs du joueur à renvoyer
	 */
	public Player getPlayer(int index) {
		
		return this.players[index];
	}
	
	/**
	 * Renvoie le nombre de joueurs participant à la partie
	 */
	public int numberOfPlayers() {
		
		return this.players.length;
	}
	
	/**
	 * Renvoie l'indice du joueur passé en argument dans le tableau des 
	 * joueurs, ou -1 si le joueur n'est pas dans le tableau.
	 */
	private int indexOfPlayer(Player player) {
		
		int i = 0;
		while (i < players.length) {
			if (this.players[i] == player)
				return i;
			i++;
		}
		return -1;
	}
	
	/**
	 * Renvoie la liste des adversaires du joueur passé en argument, dans 
	 * l'ordre dans lequel ils apparaissent à partir du joueur {@code p}.
	 * 
	 * @param p joueur dont on veut renvoyer la liste des adversaires. On 
	 * suppose que {@code p} est bien dans le tableau des joueurs.
	 * @return un {@code ArrayList} contenant les autres joueurs de la partie 
	 * en commençant par celui qui se trouve juste après {@code p} et en 
	 * terminant par celui qui se trouve juste avant (le tableau est considéré 
	 * comme cyclique c'est-à-dire qu'après le premier élément on revient au 
	 * premier).
	 */
	public List<Player> otherPlayers(Player p) {
		
		List<Player> players = new ArrayList<Player>();
		int index_player = indexOfPlayer(p);
		int i = index_player;
		int j = 0;
		
		while (i < this.players.length - 1) {
			players.add(this.players[i+1]);
			i++;
		}

		while (j < index_player) {
			players.add(this.players[j]);
			j++;
		}
		
		return players;
	}
	
	/**
	 * Renvoie la liste des cartes qui sont disponibles à l'achat dans la 
	 * réserve.
	 * 
	 * @return une liste de cartes contenant la première carte de chaque pile 
	 * non-vide de la réserve (cartes royaume et cartes communes)
	 */
	public CardList availableSupplyCards() {
		
		CardList cards_available = new CardList();
		int i = 0;
		while (i < this.supplyStacks.size()) {
			if (!this.supplyStacks.get(i).isEmpty())
				cards_available.add(this.supplyStacks.get(i).get(0));
			i++;
		}
		
		return cards_available;
		}
	
	/**
	 * Renvoie une représentation de l'état de la partie sous forme d'une chaîne
	 * de caractères.
	 * 
	 * Cette représentation comporte
	 * - le nom du joueur dont c'est le tour
	 * - la liste des piles de la réserve en indiquant pour chacune :
	 *   - le nom de la carte
	 *   - le nombre de copies disponibles
	 *   - le prix de la carte
	 *   si la pile n'est pas vide, ou "Empty stack" si la pile est vide
	 */
	public String toString() {
		Player current_player = this.players[this.currentPlayerIndex];
		String r = String.format("     -- %s's Turn --\n", current_player.getName());
		for (List<Card> stack: this.supplyStacks) {
			if (stack.isEmpty()) {
				r += "[Empty stack]   ";
			} else {
				Card c = stack.get(0);
				r += String.format("%s x%d(%d)   ", c.getName(), stack.size(), c.getCost());
			}
		}
		r += "\n";
		return r;
	}
	
	/**
	 * Renvoie une carte de la réserve dont le nom est passé en argument.
	 * 
	 * @param cardName nom de la carte à trouver dans la réserve
	 * @return la carte trouvée dans la réserve ou {@code null} si aucune carte 
	 * ne correspond
	 */
	public Card getFromSupply(String card_name) {
		
		for(int i = 0; i<this.supplyStacks.size();i++) {
			for(int j = 0; j<this.supplyStacks.get(i).size(); j++){
				if(this.supplyStacks.get(i).get(j).getName().equals(card_name)){
					return this.supplyStacks.get(i).get(j);
				}
			}
		}
		return null;
	}
	
	/**
	 * Retire et renvoie une carte de la réserve
	 * 
	 * @param cardName nom de la carte à retirer de la réserve
	 * @return la carte retirée de la réserve ou {@code null} si aucune carte
	 * ne correspond au nom passé en argument
	 */
	public Card removeFromSupply(String card_name) {
		
		Card card_removed;
		for(int i = 0; i<this.supplyStacks.size();i++) {
			for(int j = 0; j<this.supplyStacks.get(i).size();j++){
				if(this.supplyStacks.get(i).get(j).getName().equals(card_name)) {
					card_removed = this.supplyStacks.get(i).get(j);
					this.supplyStacks.get(i).remove(this.supplyStacks.get(i).get(j));
					if(this.supplyStacks.get(i).size() == 0){
						this.supplyStacks.remove(i);
					}
					return card_removed;
				}
			}
		}
		return null;
	}
	
	/**
	 * Teste si la partie est terminée
	 * 
	 * @return un booléen indiquant si la partie est terminée, c'est-à-dire si
	 * au moins l'unedes deux conditions de fin suivantes est vraie
	 *  - 3 piles ou plus de la réserve sont vides
	 *  - la pile de Provinces de la réserve est vide
	 * (on suppose que toute partie contient une pile de Provinces, et donc si 
	 * aucune des piles non-vides de la réserve n'est une pile de Provinces, 
	 * c'est que la partie est terminée)
	 */
	public boolean isFinished() {
		
		if(this.supplyStacks.size() <= 4)
			return true;
		int i = 0;
		
		while (i < this.supplyStacks.size()) {						
			if(this.supplyStacks.get(i).get(0).getName().equals("Province"))
				return false;		
			i++;
		}
		return true;	
	}
	
	/**
	 * Boucle d'exécution d'une partie.
	 * 
	 * Cette méthode exécute les tours des joueurs jusqu'à ce que la partie soit
	 * terminée. Lorsque la partie se termine, la méthode affiche le score 
	 * final et les cartes possédées par chacun des joueurs.
	 */
	public void run() {
		while (! this.isFinished()) {
			// joue le tour du joueur courant
			this.players[this.currentPlayerIndex].playTurn();
			// passe au joueur suivant
			this.currentPlayerIndex += 1;
			if (this.currentPlayerIndex >= this.players.length) {
				this.currentPlayerIndex = 0;
			}
		}
		System.out.println("Game over.");
		// Affiche le score et les cartes de chaque joueur
		for (int i = 0; i < this.players.length; i++) {
			Player p = this.players[i];
			System.out.println(String.format("%s: %d Points.\n%s\n", p.getName(), p.victoryPoints(), p.totalCards().toString()));
		}
	}
	
	/**
	 * Lit une ligne de l'entrée standard
	 * 
	 * C'est cette méthode qui doit être appelée à chaque fois qu'on veut lire
	 * l'entrée clavier de l'utilisateur (par exemple dans Player.choose), ce
	 * qui permet de n'avoir qu'un seul Scanner pour tout le programme
	 * 
	 * @return une chaîne de caractères correspondant à la ligne suivante de
	 * l'entrée standard (sans le retour à la ligne final)
	 */
	public String readLine() {
		return this.scanner.nextLine();
	}
	public void setTrashCard(Card card) {
		this.trashedCards.add(card);
	}
	public CardList getTrashedCards() {
		return trashedCards;
	}
	public void addTrashCard(Card card) {
		this.trashedCards.add(card);
	}
}