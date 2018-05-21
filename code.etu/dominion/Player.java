package dominion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dominion.card.Card;
import dominion.card.CardList;
import dominion.card.CardType;

import dominion.card.common.Copper;
import dominion.card.common.Estate;

/**
 * Un joueur de Dominion
 */
public class Player {
	/**
	 * Nom du joueur
	 */
	private String name;

	/**
	 * Nombre d'actions disponibles
	 */
	private int actions;

	/**
	 * Nombre de pièces disponibles pour acheter des cartes
	 */
	private int money;

	/**
	 * Nombre d'achats disponibles
	 */
	private int buys;

	/**
	 * Référence vers la partie en cours
	 */
	private Game game;

	/**
	 * Liste des cartes dans la main du joueur
	 */
	private CardList hand;

	/**
	 * Liste des cartes dans la défausse du joueur
	 */
	private CardList discard;

	/**
	 * Liste des cartes dans la pioche du joueur
	 */
	private CardList draw;

	/**
	 * Listes des cartes qui ont été jouées pendant le tour courant
	 */
	private CardList inPlay;

	/**
	 * Constructeur
	 *
	 * Initialise les différentes piles de cartes du joueur, place 3 cartes
	 * Estate et 7 cartes Copper dans la défausse du joueur puis fait piocher 5
	 * cartes en main au joueur.
	 *
	 * @param name
	 *            : le nom du joueur
	 * @param game
	 *            : le jeu en cours
	 *
	 *            Indications: On peut utiliser la méthode
	 *            {@code this.endTurn()} pour préparer la main du joueur après
	 *            avoir placé les cartes dans la défausse.
	 */
	public Player(String name, Game game) {

		this.name = name;
		this.game = game;
		this.draw = new CardList();
		this.inPlay = new CardList();
		this.discard = new CardList();
		this.hand = new CardList();

		int i = 0;
		while (i < 3) {
			this.gain(new Estate());
			i++;
		}

		int j = 0;
		while (j < 7) {
			this.gain(new Copper());
			j++;
		}

		int k = 0;
		while (k < 5) {
			this.hand.add(drawCard());
			k++;
		}
	}

	/**
	 * Getters et setters
	 */
	public String getName() {
		return this.name;
	}

	public int getActions() {
		return this.actions;
	}

	public int getMoney() {
		return this.money;
	}

	public int getBuys() {
		return this.buys;
	}

	public Game getGame() {
		return this.game;
	}

	/**
	 * Incrémente le nombre d'actions du joueur
	 *
	 * @param n
	 *            nombre d'actions à ajouter (ce nombre peut être négatif si
	 *            l'on souhaite diminuer le nombre d'actions)
	 */
	public void incrementActions(int n) {
		this.actions = this.actions + n;
	}

	/**
	 * Incrémente le nombre de pièces du joueur
	 *
	 * @param n
	 *            nombre de pièces à ajouter (ce nombre peut être négatif si
	 *            l'on souhaite diminuer le nombre de pièces)
	 */
	public void incrementMoney(int n) {
		this.money = this.money + n;
	}

	/**
	 * Incrémente le nombre d'achats disponibles du joueur
	 *
	 * @param n
	 *            nombre d'achats à ajouter (ce nombre peut être négatif si l'on
	 *            souhaite diminuer le nombre d'achats)
	 */
	public void incrementBuys(int n) {
		this.buys = this.buys + n;
	}

	/**
	 * Renvoie une liste des cartes que le joueur a en main. La liste renvoyée
	 * doit être une nouvelle {@code CardList} dont les éléments sont les mêmes
	 * que ceux de {@code this.hand}.
	 */
	public CardList cardsInHand() {
		CardList cards_in_hand = new CardList();
		cards_in_hand.addAll(this.hand);
		return cards_in_hand;
	}

	/**
	 * Renvoie une liste de toutes les cartes possédées par le joueur (le deck
	 * complet c'est-à-dire toutes les cartes dans la main, la défausse, la
	 * pioche et en jeu)
	 */
	public CardList totalCards() {
		CardList deck_player = new CardList();
		deck_player.addAll(this.inPlay);
		deck_player.addAll(this.draw);
		deck_player.addAll(this.hand);
		deck_player.addAll(this.discard);

		return deck_player;
	}

	/**
	 * Renvoie le nombre total de points de victoire du joueur
	 *
	 * Ce total est calculé en ajoutant les valeurs individuelles de toutes les
	 * cartes dans le deck du joueur (en utilisant la méthode
	 * {@code victoryValue()}) des cartes
	 */
	public int victoryPoints() {
		int victory_points = 0;
		CardList deck_player = this.totalCards();
		int i = 0;

		while (i < deck_player.size()) {
			victory_points = victory_points
					+ deck_player.get(i).victoryValue(this);
			i++;
		}
		return victory_points;
	}

	/**
	 * Renvoie une liste des autres joueurs de la partie.
	 *
	 * Les adversaires sont listés dans l'ordre de jeu, c'est-à-dire que le
	 * premier de la liste est celui qui joue immédiatement après le joueur,
	 * puis le suivant, et ainsi de suite jusqu'au joueur qui joue immédiatement
	 * avant le joueur.
	 *
	 * Rmq: Cette méthode fait appel à la méthode {@code otherPlayers(Player p)}
	 * de la classe {@code Game}.
	 */
	public List<Player> otherPlayers() {
		return this.game.otherPlayers(this);
	}

	/**
	 * Pioche une carte dans la pioche du joueur.
	 *
	 * Si la pioche du joueur est vide, on commence par mélanger la défausse et
	 * transférer toutes les cartes de la défausse dans la pioche. On retire et
	 * renvoie ensuite la première carte de la pioche si elle n'est pas vide
	 * (sinon la méthode ne fait rien et renvoie {@code null})
	 *
	 * @return la carte piochée, {@code null} si aucune carte disponible
	 */
	public Card drawCard() {
		if (this.draw.isEmpty() && this.discard.isEmpty()) {
			return null;
		}
		if (this.draw.isEmpty()) {
			this.discard.shuffle();
			this.draw.addAll(discard);
			this.discard.clear();
		}
		Card card = this.draw.get(0);
		this.draw.remove(0);
		return card;
	}

	/**
	 * Renvoie une représentation de l'état du joueur sous forme d'une chaîne de
	 * caractères.c
	 *
	 * Cette représentation comporte - le nom du joueur - le nombre d'actions,
	 * de pièces et d'achats du joueur - le nombre de cartes dans la pioche et
	 * dans la défausse du joueur - la liste des cartes en jeu du joueur - la
	 * liste des cartes dans la main du joueur
	 */
	public String toString() {
		String r = String.format("     -- %s --\n", this.name);
		r += String
				.format("Actions: %d     Money: %d     Buys: %d     Draw: %d     Discard: %d\n",
						this.actions, this.money, this.buys, this.draw.size(),
						this.discard.size());
		r += String.format("In play: %s\n", this.inPlay.toString());
		r += String.format("Hand: %s\n", this.hand.toString());
		return r;
	}

	/**
	 * Renvoie la liste de toutes les cartes Trésor dans la main du joueur
	 */
	public CardList getTreasureCards() {
		CardList treasure_card_hand = new CardList();
		List<CardType> type_treasure = new ArrayList<CardType>();
		int i = 0;

		while (i < this.cardsInHand().size()) {
			type_treasure = this.cardsInHand().get(i).getTypes();
			int j = 0;

			while (j < type_treasure.size()) {
				if (type_treasure.get(j) == CardType.Treasure)
					treasure_card_hand.add(cardsInHand().get(i));
				j++;
			}
			i++;
		}
		return treasure_card_hand;

	}

	/**
	 * Renvoie la liste de toutes les cartes Action dans la main du joueur
	 */
	public CardList getActionCards() {
		CardList action_card_hand = new CardList();
		List<CardType> type_action = new ArrayList<CardType>();
		int i = 0;

		while (i < this.cardsInHand().size()) {
			type_action = this.cardsInHand().get(i).getTypes();
			int j = 0;

			while (j < type_action.size()) {
				if (type_action.get(j) == CardType.Treasure)
					action_card_hand.add(cardsInHand().get(i));
				j++;
			}
			i++;
		}
		return action_card_hand;

	}

	/**
	 * Renvoie la liste de toutes les cartes Victoire dans la main du joueur
	 */
	public CardList getVictoryCards() {
		CardList victory_card_hand = new CardList();
		List<CardType> type_victory = new ArrayList<CardType>();
		int i = 0;

		while (i < this.cardsInHand().size()) {
			type_victory = this.cardsInHand().get(i).getTypes();
			int j = 0;

			while (j < type_victory.size()) {
				if (type_victory.get(j) == CardType.Treasure)
					victory_card_hand.add(cardsInHand().get(i));
				j++;
			}
			i++;
		}
		return victory_card_hand;

	}

	/**
	 * Joue une carte de la main du joueur.
	 *
	 * @param c
	 *            carte à jouer
	 *
	 *            Cette méthode ne vérifie pas que le joueur a le droit de jouer
	 *            la carte, ni même que la carte se trouve effectivement dans sa
	 *            main. La méthode retire la carte de la main du joueur, la
	 *            place dans la liste {@code inPlay} et exécute la méthode
	 *            {@code play(Player p)} de la carte.
	 */
	public void playCard(Card carte) {
		this.hand.remove(carte);
		this.inPlay.add(carte);
		carte.play(this);
	}

	/**
	 * Joue une carte de la main du joueur.
	 *
	 * @param cardName
	 *            nom de la carte à jouer
	 *
	 *            S'il existe une carte dans la main du joueur dont le nom est
	 *            égal au paramètre, la carte est jouée à l'aide de la méthode
	 *            {@code playCard(Card c)}. Si aucune carte ne correspond, la
	 *            méthode ne fait rien.
	 */
	public void playCard(String cardName) {
		Card carte = hand.getCard(cardName);
		if (carte != null) {
			this.playCard(carte);
		}
	}

	/**
	 * Le joueur gagne une carte.
	 *
	 * @param c
	 *            carte à gagner (éventuellement {@code null})
	 *
	 *            Si la carte n'est pas {@code null}, elle est placée sur la
	 *            défausse du joueur. On suppose que la carte a correctement été
	 *            retirée de son emplacement précédent au préalable.
	 */
	public void gain(Card carte) {
		if (carte != null) {
			this.discard.add(carte);
		}
	}

	/**
	 * Le joueur gagne une carte de la réserve
	 *
	 * @param cardName
	 *            nom de la carte à gagner. S'il existe une carte dans la
	 *            réserve ayant ce nom, cette carte est retirée de la réserve et
	 *            placée sur la défausse du joueur.
	 * @return la carte qui a été ajoutée à la défausse du joueur, ou
	 *         {@code null} si aucune carte n'a été prise dans la réserve.
	 */
	public Card gain(String cardName) {

		if (this.game.getFromSupply(cardName) == null) {
			return null;
		} else {
			Card carte = this.game.removeFromSupply(cardName);
			this.gain(carte);
			return carte;
		}
	}

	/**
	 * Le joueur achète une carte de la réserve
	 *
	 * La méthode cherche une carte dans la réserve dont le nom est égal au
	 * paramètre, puis vérifie que le joueur a assez de pièces pour l'acheter et
	 * au moins un achat disponible. Si le joueur peut acheter la carte, le coût
	 * de la carte est soustrait à l'argent du joueur, le nombre d'achats
	 * disponibles est décrémenté de 1 et la carte est gagnée par le joueur.
	 *
	 * @param cardName
	 *            nom de la carte à acheter
	 * @return la carte qui a été gagnée ou {@code null} si l'achat n'a pas eu
	 *         lieu
	 */
	public Card buyCard(String cardName) {

		Card card_buy = this.game.getFromSupply(cardName);
		if (card_buy == null)
			return null;
		else {
			if (this.money >= card_buy.getCost() && this.buys >= 1) {
				this.incrementMoney(-card_buy.getCost());
				this.incrementBuys(-1);
				return this.gain(cardName);
			}
		}
		return null;
	}

	/**
	 * Attend une entrée de la part du joueur (au clavier) et renvoie le choix
	 * du joueur.
	 * 
	 * @param instruction
	 *            message à afficher à l'écran pour indiquer au joueur la nature
	 *            du choix qui est attendu
	 * @param choices
	 *            une liste de chaînes de caractères correspondant aux choix
	 *            valides attendus du joueur (la liste sera convertie en
	 *            ensemble par la fonction pour éliminer les doublons, ce qui
	 *            permet de compter correctement le nombre d'options
	 *            disponibles)
	 * @param canPass
	 *            booléen indiquant si le joueur a le droit de passer sans faire
	 *            de choix. S'il est autorisé à passer, c'est la chaîne de
	 *            caractères vide ("") qui signifie qu'il désire passer.
	 * 
	 * @return la méthode lit l'entrée clavier jusqu'à ce qu'un choix valide
	 *         soit entré par l'utilisateur (un élément de {@code choices} ou
	 *         éventuellement la chaîne vide si l'utilisateur est autorisé à
	 *         passer). Lorsqu'un choix valide est obtenu, il est renvoyé.
	 * 
	 *         Si l'ensemble {@code choices} ne comporte qu'un seul élément et
	 *         que {@code canPass} est faux, l'unique choix valide est
	 *         automatiquement renvoyé sans lire l'entrée de l'utilisateur.
	 * 
	 *         Si l'ensemble des choix est vide, la chaîne vide ("") est
	 *         automatiquement renvoyée par la méthode (indépendamment de la
	 *         valeur de {@code canPass}).
	 * 
	 *         Exemple d'utilisation pour demander à un joueur de répondre à une
	 *         question :
	 * 
	 *         <pre>
	 * {
	 * 	&#064;code
	 * 	List&lt;String&gt; choices = Arrays.asList(&quot;y&quot;, &quot;n&quot;);
	 * 	String input = p.choose(&quot;Do you want to ...? (y/n)&quot;, choices, false);
	 * }
	 * </pre>
	 */
	public String choose(String instruction, List<String> choix, boolean canPass) {

		Set<String> choixSet = new HashSet<String>();
		for (String c : choix) {
			choixSet.add(c);
		}
		if (choixSet.isEmpty()) {

			return "";
		} else if (choixSet.size() == 1 && !canPass) {

			return choixSet.iterator().next();
		} else {
			String input;

			while (true) {
				System.out.print("\n\n");

				System.out.print(this.game);
				System.out.print("\n");

				System.out.print(this);
				System.out.print("\n");

				System.out.println(">>> " + instruction);
				System.out.print("> ");

				input = this.game.readLine();
				if (choixSet.contains(input) || (canPass && input.equals(""))) {

					return input;
				}
			}
		}
	}

	/**
	 * Attend une entrée de la part du joueur et renvoie le choix du joueur.
	 * Dans cette méthode, la liste des choix est donnée sous la forme d'une
	 * liste de cartes et le joueur doit choisir le nom d'une de ces cartes.
	 *
	 * @param instruction
	 *            message à afficher à l'écran pour indiquer au joueur la nature
	 *            du choix qui est attendu
	 * @param choices
	 *            liste de cartes parmi lesquelles il faut en choisir une parmi
	 *            lesquelles l'utilisateur doit choisir
	 * @param canPass
	 *            booléen indiquant si le joueur a le droit de passer sans faire
	 *            de choix. S'il est autorisé à passer, c'est la chaîne de
	 *            caractères vide ("") qui signifie qu'il désire passer.
	 *
	 *            La méthode commence par construire une liste de tous les noms
	 *            des cartes dans {@code choices} puis appelle la méthode
	 *            précédente pour faire choisir un nom parmi cette liste à
	 *            l'utilisateur.
	 *
	 *            Exemple d'utilisation pour faire choisir le nom d'une carte
	 *            Action de sa main à un joueur (dans cet exemple le joueur n'a
	 *            pas le droit de passer s'il a au moins une carte Action en
	 *            main, mais la méthode peut quand même renvoyer {@code ""} s'il
	 *            n'a aucune carte Action en main) :
	 * 
	 *            <pre>
	 * {@code
	 * CardList choices = new CardList();
	 * for (Card c: p.cardsInHand()) {
	 *   if (c.getTypes().contains(CardType.Action)) {
	 *     choices.add(c);
	 *   }
	 * }
	 * String input = p.chooseCard("Choose an Action card.", choices, false);
	 * </pre>
	 */
	public String chooseCard(String instruction, CardList choix, boolean canPass) {

		List<String> stringChoices = new ArrayList<String>();
		for (Card c : choix) {

			stringChoices.add(c.getName());
		}

		return this.choose(instruction, stringChoices, canPass);
	}

	/**
	 * Démarre le tour du joueur
	 *
	 * Les compteurs d'actions et achats sont mis à 1
	 */
	public void startTurn() {
		this.actions = 1;
		this.buys = 1;
	}

	/**
	 * Termine le tour du joueur
	 *
	 * - Les compteurs d'actions, argent et achats du joueur sont remis à 0 -
	 * Les cartes en main et en jeu sont défaussées - Le joueur pioche 5 cartes
	 * en main
	 */
	public void endTurn() {

		this.money = 0;
		this.buys = 0;
		this.actions = 0;

		this.discard.addAll(this.inPlay);
		this.discard.addAll(this.hand);
		this.inPlay.clear();
		this.hand.clear();
		int i = 0;

		while (i < 5) {
			add_hand(drawCard());
			i++;
		}
	}

	/**
	 * Exécute le tour d'un joueur
	 *
	 * Cette méthode exécute successivement les 5 phases du tour d'un joueur:
	 *
	 * 1. (Préparation) la méthode {@code startTurn()} est appelée
	 *
	 * 2. (Action) Tant que le joueur a des actions disponibles, on lui demande
	 * de choisir le nom d'une carte Action de sa main à jouer. Il peut passer à
	 * tout moment à la phase suivante (soit de manière forcée s'il n'a plus de
	 * carte Action en main soit volontairement en entrant la chaîne vide).
	 * Lorsqu'il joue une carte Action, la fonction décrémente son nombre
	 * d'actions puis joue la carte.
	 *
	 * 3. (Trésor) Le joueur joue toutes les cartes Trésor de sa main
	 * automatiquement (dans le jeu de base il n'y a aucune raison de ne pas
	 * jouer tous les trésors automatiquement).
	 *
	 * 4. (Achat) Tant que le joueur a au moins un achat disponible, on lui
	 * demande de choisir le nom d'une carte de la réserve qu'il veut acheter.
	 * Il ne peut acheter que des cartes dont le prix est inférieur à l'argent
	 * dont il dispose. Le joueur peut passer (et terminer son tour) à tout
	 * moment pendant cette phase.
	 *
	 * 5. (Fin) La méthode {@code endTurn()} est appelée pour terminer le tour
	 * du joueur
	 */
	public void playTurn() {

		CardList cards_action = getActionCards();
		CardList cards_treasure = new CardList();
		boolean end = false;
		String choix_player;

		startTurn();

		while (actions > 0 && end == false && cards_action.size() > 0) {
			choix_player = chooseCard(
					"Veuillez choisir une carte action de votre main",
					cards_action, true);
			if (choix_player != "") {
				playCard(choix_player);
				actions--;
			} else
				end = true;
		}

		cards_treasure = getTreasureCards();
		int i = 0;
		while (i < cards_treasure.size()) {
			playCard(cards_treasure.get(i).getName());
			i++;
		}

		end = false;
		while (buys > 0 && end == false) {
			choix_player = chooseCard(
					"Veuillez choisir une carte que vous voulez acheter",
					game.availableSupplyCards(), true);
			if (choix_player != "") {
				buyCard(choix_player);
				buys--;
			} else
				end = true;
		}

		endTurn();
	}

	// Méthodes ajoutées

	public void add_hand(Card card) {
		this.hand.add(card);
	}

	public CardList get_hand() {
		return hand;
	}

	public CardList get_discard() {
		return discard;
	}

	public void remove_hand(String card_name) {
		this.hand.remove(card_name);
	}

	public CardList get_draw() {
		return draw;
	}

	public void remove_discard(String card_name) {
		this.discard.remove(card_name);
	}

	public void remove_draw(String card_name) {
		this.draw.remove(card_name);
	}

	public void add_discard(Card card) {
		this.discard.add(card);
	}

	public void add_draw(Card card) {
		this.draw.add(card);
	}

	public void remove_in_play(String card_name) {
		this.inPlay.remove(card_name);
	}
}