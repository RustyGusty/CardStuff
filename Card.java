public class Card {
    private int value;
    private String suit;

    public Card(int val, String st) {
        value = val;
        suit = st;
    }

    public boolean equals(Card other) {
        return (value == other.getValue() || suit == other.getSuit());
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public String toString() {
        String rank = Integer.toString(getValue());
        if(rank.equals("1")) {
            rank = "A";
        } else if (rank.equals("11")) {
            rank = "J";
        } else if (rank.equals("12")) {
            rank = "Q";
        } else if (rank.equals("13")) {
            rank = "K";
        }
        return (rank + " of " + suit);
    }
}
