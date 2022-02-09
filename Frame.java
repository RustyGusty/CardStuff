import java.util.ArrayList;

public class Frame {
    public static ArrayList<Card> newDeck() {
        ArrayList<Card> initDeck = new ArrayList<>();
        for(int i = 1; i <= 13; i ++) {
            initDeck.add(new Card(i, "diamonds"));
        }
        for(int i = 1; i <= 13; i ++) {
            initDeck.add(new Card(i, "clubs"));
        }
        for(int i = 1; i <= 13; i ++) {
            initDeck.add(new Card(i, "hearts"));
        }
        for(int i = 1; i <= 13; i ++) {
            initDeck.add(new Card(i, "spades"));
        }
        return initDeck;
    }

    public static ArrayList<Card> shuffle(ArrayList<Card> theDeck) {
        int index;
        ArrayList<Card> shuffledDeck = new ArrayList<>();
        while(theDeck.size() > 0) {
            index = (int) (Math.random() * theDeck.size());
            shuffledDeck.add(theDeck.get(index));
            theDeck.remove(index);
        }
        return shuffledDeck;
    }

    public static ArrayList<Card> copyDeck(ArrayList<Card> theDeck) {
        ArrayList<Card> deck = new ArrayList<>();
        for(int i = 0; i < theDeck.size(); i++) {
            deck.add(theDeck.get(i));
        }
        return deck;
    }

    public static ArrayList<Card> draw(int count, ArrayList<Card> deck){
        ArrayList<Card> hand = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            hand.add(deck.get(0));
            deck.remove(0);
        }
        return hand;
    }
}

