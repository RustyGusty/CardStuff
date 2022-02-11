/** Contains all mechanisms related to the individual card, including value and suit as well as comparisons between two cards */

public class Card {
    private int value; //0 if the card is a joker
    private String suit; //"joker" if the card is a joker
    private boolean joker; //true if the card is a joker

    /** Constructor for a normal card with a face value and a suit, taking in two parameters: an int and a String.
     * 
     * @param val - an int variable with the face value of the card. J is 11, Q is 12, and K is 13
     * @param st - a String variable with the suit of the card
     * 
     * Precondition: 1 <= val <= 13 and st is either of "diamonds", "clubs", "hearts", "spades"
     * Postcondition: A Card object is initialied with a face value of val and a suit of st. the boolean variable
     * joker is set to false.
     */
    public Card(int val, String st) { 
        value = val;
        suit = st;
        joker = false;
    }

    /** Constructor for a joker, taking no parameters
     * 
     * Postcondition: A Card object is initialized with a face value of 0 and a suit of "joker". The boolean
     * variable joker is set to true.
     */
    public Card() {
        value = 0;
        suit = "Joker";
        joker = true;
    }

    /** Returns whether the calling object matches the given object in either suit or value. Joker will always return true
     * 
     * @param other - Card variable of the card to compare
     * @return boolean - true if the cards match in either suit or value or if any card is a joker, false otherwise
     * 
     * Precondition: The calling object and the other object have been initialized properly
     * Postcondition: A boolean is returned as true if the cards match in either suit or
     * value or if any card is a joker, false otherwise
     */
    public boolean equals(Card other) {
        if(this.isJoker() || other.isJoker()) { //Check if either card is a joker, return true if so
            return true;
        }
        return this.getValue() == other.getValue() || this.getSuit() == other.getSuit();
    }

    /** Getter method for the value of the Card
     * 
     * @return value - int of the value of the Card
     */
    public int getValue() {
        return value;
    }
    
    /** Getter method for the suit of the Card
     * 
     * @return suit - String of the suit of the Card
     */
    public String getSuit() {
        return suit;
    }

    /** Getter method for if the card is a joker
     * 
     * @return joker - boolean that is true if the card is a joker.
     */
    public boolean isJoker() {
        return joker;
    }

    /** Returns a String representation of the card in the form: "<value> of <suit>" (values of 1, 11, 12, and 13 are
     * updated to A, J, Q, and K respectively), or "joker" if the card is a joker
     * 
     * @return - a String representation of the card. If the card is a normal card, then it is in the form  "<value> of <suit>".
     * If the card is a joker, then it is "joker"
     */
    public String toString() {
        String rank = Integer.toString(getValue()); 
        if(this.isJoker()) {
            return "Joker";
        }
        if(rank.equals("1")) { //1 converts to "A"
            rank = "A";
        } else if (rank.equals("11")) { //11 converts to "J"
            rank = "J";
        } else if (rank.equals("12")) { //12 converts to "Q"
            rank = "Q";
        } else if (rank.equals("13")) { //13 converts to "K"
            rank = "K";
        }
        return rank + " of " + suit;
    }
}
