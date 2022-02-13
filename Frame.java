import java.util.ArrayList;

/** Provides basic actions for a card deck, involving a 53-card deck, a main deck, a discard pile, and shuffling mechanics*/
public class Frame {
    
    private static ArrayList<Card> deck = new ArrayList<>();
    private static ArrayList<Card> discardPile = new ArrayList<>();

    //Note: These decks should remain in the Frame class and should not be altered outside of it.
    //The top of the deck is denoted by index 0

    /** Creates a new, unshuffled basic deck of 52 cards, 13 of each suit from ace to king, as well as one joker 
     * and stores this into the deck instance variable
     * 
     * Postcondition: To the ArrayList<Card> deck is added: Ace to king of diamonds, Ace to king of clubs, Ace to
     * king of hearts, Ace to king of spades, and a joker
     */
    public static void newDeck() {
        deck = new ArrayList<>();
        for(int i = 1; i <= 13; i ++) { //Adds all diamonds (ace to king)
            deck.add(new Card(i, "diamonds"));
        }
        for(int i = 1; i <= 13; i ++) { //Adds all clubs (ace to king)
            deck.add(new Card(i, "clubs"));
        }
        for(int i = 1; i <= 13; i ++) { //Adds all hearts (ace to king)
            deck.add(new Card(i, "hearts"));
        }
        for(int i = 1; i <= 13; i ++) { //Adds all spades (ace to king)
            deck.add(new Card(i, "spades"));
        }
        deck.add(new Card()); //Adds a joker
    }

    /** Replaces the instance variable deck with a shuffled version
     * 
     * Postcondition: the instance variable deck is randomly shuffled
    */
    public static void shuffle() {
        int index;
        ArrayList<Card> oldDeck = copyDeck(); //Creates a copy of the initial deck
        deck = new ArrayList<>(); //Resets the old deck
        while(oldDeck.size() > 0) { //The cards in the copied deck are added one by one to the instance variable deck in a random order 
            index = (int) (Math.random() * oldDeck.size()); //Generates a random index in the range of the oldDeck
            deck.add(oldDeck.remove(index));
        }
    }

    /** Creates and returns a copy of the instance variable deck
     * 
     * @return newDeck - A copy of the current deck
     */
    private static ArrayList<Card> copyDeck() {
        ArrayList<Card> newDeck = new ArrayList<>();
        for(int i = 0; i < deck.size(); i++) { //The elements of the instance variable deck are added in the same order to the newDeck
            newDeck.add(deck.get(i));
        }
        return newDeck;
    }

    /** Gives 1 player the specified number of cards from the top of the deck; if there are not enough cards to drawm from the 
     * main deck, reshuffles the discard pile into the main deck and continues drawing. Returns the cards drawn in an ArrayList
     * 
     * @param count -  int variable to specify the number of cards for that player to draw.
     * @param player - Player variable to denote who draws the cards.
     * @return cardsDrawn - ArrayList<Card> variable that contains all the cards drawn.
     * 
     * Precondition: count <= deck.size + discard.size.
     * Postcondition: The top count cards are removed from the deck, and the ArrayList<Card> instance variable hand belonging to 
     * the specified Player object receives the removed cards. If not enough cards exist in the main deck, discardPile is reshuffled and
     * replaces the deck until drawing completes. An ArrayList<Card> containing all the cards drawn is returned.
    */
    public static ArrayList<Card> draw(int count, Player player){
        Card temp;
        ArrayList<Card> cardsDrawn = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            if(deck.isEmpty()) { //If the deck is empty, shuffle and use the discard pile instead (added to the main deck)
                returnDiscard();
            }
            temp = deck.remove(0);
            player.getHand().add(temp);
            cardsDrawn.add(temp);
        }
        return cardsDrawn;
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

    /** Places the given card into the discard pile and then removes it from the given player's hand
     * @param index - an int variable containing the index of the card to be discarded
     * @param player - a Player variable of the player who is discarding the card
     * 
     * Postcondition: the card in the hand corresponding to the index will be added to the bottom of the 
     * ArrayList<Card> variable discardPile. Player's hand will lose the discarded card.
     */
    public static void discard(int index, Player player) {
        discardPile.add(player.playCard(index));
    }

    /** Returns the ArrayList<Card> variable discardPile.
     * @return discardPile - an ArrayList<Card> variable containing the cards in the discard pile
     */
    public static ArrayList<Card> getDiscardPile() {
        return discardPile;
    }

    /** Takes the selected card from the discard pile and adds it to the given player's hand
     * 
     * @param index - an int variable with the index of the card to draw form the discardPile.
     * @param player - a Player variable of the player who wishes to take a card
     * 
     * Precondition: 0 <= index < discardPile.length() and player has been properly initialized.
     * Postcondition: The card of the selected index is moved to the hand of the given player.
     */
    public static void drawDiscard(int index, Player player) {
        player.getHand().add(discardPile.remove(index));
    }

    /** Adds the discard pile to the main deck, and then reshuffles the main deck
     * 
     * Postcondition: The ArrayList<Card> variable discardPile is replaced with an empty ArrayList. The elements of the
     * discardPile ArrayList are added to the ArrayList deck. Then, the cards in deck are shuffled.
     */
    public static void returnDiscard() {
        deck.addAll(discardPile);
        shuffle();
        discardPile = new ArrayList<>(); //Resets the discardPile
    }
}

