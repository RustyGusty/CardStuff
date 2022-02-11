import java.util.ArrayList;

/** Class containing basic player functions, including a hand and a name */
public class Player {
    private ArrayList<Card> hand;
    private String name;

    /** Constructor accpeting one String parameter that initializes a new Player object with
     * an empty hand and the given name
     * 
     * @param name - the name of the player to be created
     */
    public Player(String name) {
        hand = new ArrayList<>();
        this.name = name;
    }

    /** Getter method for the hand of the player
     * 
     * @return hand - an ArrayList<Card> containing all the cards in the player's hand
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /** Getter method for the name of the player 
     * 
     * @return name - a String of the player's name
    */
    public String getName() {
        return name;
    }

    /** Adds a card to the ArrayList<Card> variable hand
     * 
     * @param newCard - the card to be added to the hand
     */
    public void addCard(Card newCard) {
        hand.add(newCard);
    }

    /** Removes a card from the player's hand at the given index and returns it
     * 
     * @param index - the index containing the card to be discarded from the player's hand
     * @return - a Card variable of the card that was removed from the player's hand
     * 
     * Precondition: 0 <= index < hand.size()
     * Postcondition: The card is removed from the ArrayList<Card> variable hand and returned.
     */
    public Card playCard(int index) {
        return hand.remove(index);
    }

    /** Returns true if the hand contains no cards, but false otherwise 
     * 
     * @return - a boolean that is true if the hand contains no cards, but false otherwise.
    */
    public boolean handEmpty() {
        return hand.size() == 0;
    }
}
