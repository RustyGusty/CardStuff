import java.util.Scanner;
public class Game {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        System.out.println(name);
        input.close();
    }
}
