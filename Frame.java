import java.util.ArrayList;

/** Provides basic actions for a card deck, involving a 53-card deck, a main deck, a discard pile, and  shuffling mechanics*/
public class Frame {
    
    private static ArrayList<Card> deck = new ArrayList<>();
    private static ArrayList<Card> discardPile = new ArrayList<>();
    //Note: These decks remain inside the Frame class and are never returned outside except through hands.
    //The top of the deck is denoted by index 0

    /** Creates a new, unshuffled basic deck of 32 cards, 13 of each suit from ace to king, as well as one joker 
     * and stores this into the deck instance variable
     */
    public static void newDeck() {
        deck = new ArrayList<>();
        for(int i = 1; i <= 13; i ++) {
            deck.add(new Card(i, "diamonds"));
        }
        for(int i = 1; i <= 13; i ++) {
            deck.add(new Card(i, "clubs"));
        }
        for(int i = 1; i <= 13; i ++) {
            deck.add(new Card(i, "hearts"));
        }
        for(int i = 1; i <= 13; i ++) {
            deck.add(new Card(i, "spades"));
        }
        deck.add(new Card());
    }

    /** Replaces the instance variable deck with a shuffled version*/
    public static void shuffle() {
        int index;
        ArrayList<Card> oldDeck = copyDeck();
        while(oldDeck.size() > 0) {
            index = (int) (Math.random() * oldDeck.size());
            deck.add(oldDeck.remove(index));
        }
    }

    /** Creates and returns a copy of the instance variable deck
     * 
     * @return - A copy of the current deck
     */
    private static ArrayList<Card> copyDeck() {
        ArrayList<Card> newDeck = new ArrayList<>();
        for(int i = 0; i < deck.size(); i++) {
            newDeck.add(deck.get(i));
        }
        return deck;
    }

    /** Gives 1 player the specified number of cards from the top of the deck; if there are not enough cards to drawm from the 
     * main deck, reshuffles the discard pile into the main deck and continues drawing.
     * 
     * @param count -  int variable to specify the number of cards for that player to draw.
     * @param player - Player variable to denote who draws the cards.
     * 
     * Precondition: count <= deck.size + discard.size.
     * Postcondition: The top count cards are removed from the deck, and the ArrayList<Card> instance variable hand belonging to 
     * the specified Player object receives the removed cards. If not enough cards exist in the main deck, discardPile is reshuffled and
     * replaces the deck until drawing completes.
    */
    public static void draw(int count, Player player){
        for(int i = 0; i < count; i++) {
            if(deck.isEmpty()) {
                returnDiscard();
            }
            player.getHand().add(deck.remove(0));
        }
    }

    /** Deals the specified number of cards to each player, giving each player 1 card from the
     * top of the main deck in order until each player has drawn the specified number of cards. If there are
     * not enough cards to draw from the main deck, reshuffles the discard pile into the main deck and continues drawing
     * 
     * @param dount - int to specify the number of cards for each player to draw.
     * @param players - ArrayList<Player> variable holding all of the players to draw cards.
     * 
     * Precondition: count <= 4 * (deck.size + discard.size), at least one player is in the players ArrayList.
     * Postcondition: The top 4 * count cards are removed from the deck. If there are not enough cards, discardPile
     * is reshuffled into the deck and drawing continues until complete. The players will, in order of the ArrayList,
     * receive one card at a time until each player has received count cards, and the ArrayList<Card> instance variable hand
     * of each player will be updated accordingly.
    */
    public static void deal(int count, ArrayList<Player> players) {
        for(int i = 0; i < count; i++) {
            for(int j = 0; j < players.size(); j++) {
                if(deck.isEmpty()) {
                    returnDiscard();
                }
                players.get(j).addCard(deck.remove(0));
            }
        }
    }

    /** Places the given card into the discard pile
     * @param discardedCard - a Card variable of the card to be discarded
     * 
     * Postcondition: discardedCard will be added to the bottom ArrayList<Card> variable discardPile. 
     */
    public static void discard(Card discardedCard) {
        discardPile.add(discardedCard);
    }

    /** Returns the ArrayList<Card> variable discardPile.
     * @return discardPile - an ArrayList<Card> variable containing the cards in the discard pile
     */
    public static ArrayList<Card> getDiscardPile() {
        return discardPile;
    }

    /** Adds the discard pile to the main deck, and then reshuffles the main deck
     * 
     * Postcondition: The ArrayList<Card> variable discardPile is replaced with an empty ArrayList. The elements of the
     * discardPile ArrayList are added to the ArrayList deck. Then, the cards in deck are shuffled.
     */
    public static void returnDiscard() {
        deck.addAll(discardPile);
        shuffle();
        discardPile = new ArrayList<>();
    }
}

