import java.util.Scanner;
import java.io.*;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN_BG = "\u001B[46m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_GREEN_BG = "\u001B[42m";


    public static void main(String[] args) {
        System.out.println("******************************");
        System.out.println(ANSI_BLACK + ANSI_CYAN_BG + "VÄLKOMMEN TILL TÄRNINGSSPELET!" + ANSI_RESET);
        System.out.println("******************************");

        intro();
    }

    public static void intro() {
        int rounds = 0;
        int numberOfDices = 0;
        int requestedRounds = 1;
        String player1Name;
        String player2Name;

        // Objects
        Scanner scanner = new Scanner(System.in);
        Dice myDice = new Dice();
        Player player1 = new Player();
        Player player2 = new Player();
        Game myGame = new Game();

        // Introduction

        System.out.print(ANSI_PURPLE + "Hur många tärningar vill ni använda? (1-5 stycken) " + ANSI_RESET);

        // Get requested number of dices
        int requestedNumberOfDices = scanner.nextInt();

        // Check number of dices
        if (myDice.checkNumberOfDices(requestedNumberOfDices)) {
            // Number is OK
            numberOfDices = requestedNumberOfDices;
            System.out.println("Ni har valt: " + numberOfDices + " tärningar.");
            System.out.println("-----------------------------------");

        } else {
            // Number is not OK
            System.out.println("Det önskade antalet tärningar (" + requestedNumberOfDices + ") är inte tillåtet, ange ett antal mellan 1-5.");
            intro();
        }

        System.out.print(ANSI_PURPLE + "Hur många omgångar vill ni köra? " + ANSI_RESET);
        requestedRounds = scanner.nextInt();
        System.out.println("Ni har valt " + requestedRounds + " omgångar.");
        System.out.println("-----------------------------------");

        System.out.print(ANSI_PURPLE + "Ange namnet för spelare 1: " + ANSI_RESET);
        player1Name = scanner.next();
        System.out.println("Spelare 1: " + player1Name);
        System.out.println("-----------------------------------");


        System.out.print(ANSI_PURPLE + "Ange namnet för spelare 2: " + ANSI_RESET);
        player2Name = scanner.next();
        System.out.println("Spelare 2: " + player2Name);
        System.out.println("-----------------------------------");


        // Set player names
        player1.setPlayerName(player1Name);
        player2.setPlayerName(player2Name);

        // Play requested rounds
        for(int i = 1; rounds < requestedRounds; i++) {
            System.out.println(ANSI_BLACK + ANSI_CYAN_BG + "POÄNG" + ANSI_RESET);
            System.out.println(player1.getPlayerName() + ": " + player1.getPlayerScores());
            System.out.println(player2.getPlayerName() + ": " + player2.getPlayerScores());
            System.out.println("-----------------------------------");
            System.out.println("Det är " + player1.getPlayerName() + "s tur.");

            int result = myGame.play(numberOfDices);

            if (result == 0) {
                while (myGame.play(numberOfDices) == 0) {
                    myGame.play(numberOfDices);
                }
            } else {
                int player1OldScore = player1.getPlayerScores();
                int player1NewScore = player1OldScore + result;
                player1.setPlayerScores(player1NewScore);

                System.out.println(ANSI_BLACK + ANSI_CYAN_BG + "POÄNG" + ANSI_RESET);
                System.out.println(player1.getPlayerName() + ": " + player1.getPlayerScores());
                System.out.println(player2.getPlayerName() + ": " + player2.getPlayerScores());
                System.out.println("-----------------------------------");
                System.out.println("Det är " + player2.getPlayerName() + "s tur.");

                result = 0;

                result = myGame.play(numberOfDices);

                if (result == 0) {
                    while (myGame.play(numberOfDices) == 0) {
                        myGame.play(numberOfDices);
                    }
                } else {
                    int player2OldScore = player2.getPlayerScores();
                    int player2NewScore = player2OldScore + result;
                    player2.setPlayerScores(player2NewScore);
                    rounds++;
                }
            }
        }

        if(rounds == requestedRounds) {
            System.out.println("-----------------------------------");
            System.out.println(ANSI_BLACK + ANSI_CYAN_BG + "SPELET AVSLUTAS" + ANSI_RESET);
            System.out.println("Poängställningen:");
            System.out.println(player1.getPlayerName() + ": " + player1.getPlayerScores());
            System.out.println(player2.getPlayerName() + ": " + player2.getPlayerScores());
            if(player1.getPlayerScores() == player2.getPlayerScores()) {
                System.out.println("SPELET BLEV OAVGJORT:");
            } else if(player1.getPlayerScores() > player2.getPlayerScores()) {
                System.out.println(ANSI_GREEN_BG + ANSI_BLACK + "VINNAREN ÄR: " + player1.getPlayerName() + ANSI_RESET);
            } else{
                System.out.println(ANSI_GREEN_BG + ANSI_BLACK + "VINNAREN ÄR: " + player2.getPlayerName() + ANSI_RESET);
            }
        }

    }

}