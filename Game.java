import java.util.Scanner;
import java.lang.invoke.CallSite;
import java.util.ArrayList;

/** Contains the game itself, including a player-addition system, a turn-based player rotation, and player actions */
public class Game {

    private static ArrayList<Player> playerList = new ArrayList<>(); // Contains all the players
    private static Scanner input = new Scanner(System.in); // Reads player input
    public static void main(String[] args)
    {
        System.out.println("Welcome to the game!");
        addPlayer();
        System.out.println();
        
        //Randomly choose a starting player's index to take the first turn
        int startingIndex = (int) (Math.random() * playerList.size());
        System.out.println(playerList.get(startingIndex).getName() + " has randomly been chosen to go first!\n");
        gameCycle(startingIndex);
    }

    /** Where the game runs, including swapping turns and actions for each player, taking in an initial player's index
     * 
     * @param ind - The index of the starting player in the playerList variable
     * 
     * Precondition - playerList has at least one Player and 0 <= ind < playerList.length()
     */
    public static void gameCycle(int ind) {
        Player currentPlayer = playerList.get(ind);
        int x;

        //Make a new deck, shuffle it, then deal 7 cards to each player
        Frame.newDeck();
        Frame.shuffle();
        Frame.deal(7, playerList);

        while(true) {
            //Print the name of the player whose turn it is and their hand
            System.out.println(currentPlayer.getName() + "'s turn!");
            System.out.println("Your current hand is: " + currentPlayer.getHand());

            //Ask for an action, requiring int inputs.
            System.out.println("Actions: 1. Draw a card. 2. Discard a card");
            while(true) { //Will loop if an invalid value is input
                x = input.nextInt();
                if(x == 1) {
                    // Draws a card and prints the card out
                    System.out.println("The cards you drew were: " + Frame.draw(1, currentPlayer));
                    break;
                } else if (x == 2) {
                    //Discards a chosen card
                    Frame.discard(chooseCard(currentPlayer), currentPlayer);
                    break;
                }
                System.out.println("Invalid input");
            //Reprints the new hand
            System.out.println("Your new hand is: " + currentPlayer.getHand());
            System.out.println();

            //Swap current player, moving across the ArrayList and looping back at the end
            ind++;
            if(ind >= playerList.size()) {
                ind = 0;
            }
            currentPlayer = playerList.get(ind);
        }
    }

    /** Asks the player to select a card from their hand, returning the selected card
     * 
     * @param player - Player variable of the player who is choosing the card.
     * @return index - index of the chosen card in the player's hand
     * 
     * Precondition: player has been properly initialized, at least one card is in the player's hand
     * Postcondition: 1 Card variable found in the player's hand is returned
     */
    public static int chooseCard(Player player) {
        String str;
        Card theCard;
        int index;
        while(true) { // Will loop if the confirmation to choose the card is denied
            System.out.println("Which card would you like to choose?");
            index = input.nextInt(); // 0 <= index < player.getHand.size()
            
            theCard = player.getHand().get(index); 
            System.out.println("Do you wish to choose the " + theCard + "? Y to agree, N to decline");
            input.nextLine();
            while(true) { // Will loop if an invalid String is input
                str = input.nextLine();
                if(str.equalsIgnoreCase("y")) {
                    return(index);
                } else if (str.equalsIgnoreCase("n")) {
                    break; // Will cause the outer while loop to loop, restarting the card selection process
                }
                System.out.println("Invalid response.");
            }
        }
    }

    /** Adds up to a maximum of 6 players to the ArrayList<Card> variable playerList, with the user
     * inputting the names of each player
     * 
     * Postcondition: Up to 6 players are initialized and added to the playerList ArrayList<Card> variable
     */
    public static void addPlayer() {
        String name;
        for(int i = 0; i < 6; i++) {
            System.out.println("Enter a player's name. Enter \"finished\" to cancel.");
            name = input.nextLine();
            if(name.equalsIgnoreCase("finished")) {
                break;
            }
            playerList.add(new Player(name));
            System.out.println(name + " has been added!\n");
            
        }
        System.out.print("Your players are: ");
        printList(playerList);
    }

    /** Prints all the names of the players in the ArrayList<Player> variable arr
     * 
     * @param arr - the ArrayList<PLayer> containing the players to print out
     * 
     * Precondition: arr has at least one Player, and all Player variables are properly initialized
     * Postcondition: A single line contianing all the players names separated by commas is printed.
     */
    public static void printList(ArrayList<Player> arr) {
        System.out.print(arr.get(0).getName());
        for(int i = 1; i < arr.size(); i++) {
            System.out.print(", " + arr.get(i).getName());
        }
        System.out.println();
    }
}
