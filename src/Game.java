import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    Dice myDice = new Dice();

    public int play(int numberOfDices) {
        int dices = numberOfDices;

        System.out.println("Skriv 1 för att kasta");

        int playerInput = scanner.nextInt();

        if(playerInput == 1) {
            int diceResult = 0;

            for(int i = 0; i < dices; i++) {
                int thisThrow = myDice.throwDice();
                System.out.println("Tärning " + (i+1) + ": " + thisThrow);

                diceResult += thisThrow;
            }

            System.out.println("Kastet gav: " + diceResult + " poäng. ");
            System.out.println("-----------------------------------");


            return diceResult;
        } else {
            return 0;
        }
    }
}
