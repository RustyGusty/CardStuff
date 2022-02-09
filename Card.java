public class Card {
    private int value;
    private String suit;
    private boolean joker;

    public Card(int val, String st) {
        value = val;
        suit = st;
        joker = false;
    }

    public Card() {
        value = 0;
        suit = "Joker";
        joker = true;
    }

    public boolean equals(Card other) {
        if(this.isJoker() || other.isJoker()) {
            return true;
        }
        return this.getValue() == other.getValue() || this.getSuit() == other.getSuit();
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public boolean isJoker() {
        return joker;
    }

    public String toString() {
        String rank = Integer.toString(getValue());
        if(this.isJoker()) {
            return "Joker";
        }
        if(rank.equals("1")) {
            rank = "A";
        } else if (rank.equals("11")) {
            rank = "J";
        } else if (rank.equals("12")) {
            rank = "Q";
        } else if (rank.equals("13")) {
            rank = "K";
        }
        return rank + " of " + suit;
    }
}
