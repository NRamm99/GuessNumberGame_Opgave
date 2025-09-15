import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        clearConsole();
        System.out.println("---------- VELKOMMEN ----------");
        System.out.println("Velkommen til tal-spillet!");
        System.out.println("Instruktioner:");
        System.out.println(" - Gæt tallet som jeg tænker på!");
        System.out.println(" - jeg hjælper dig på vej.");
        System.out.println("-------------------------------");

        System.out.print("\nTryk enter når du er klar til at spille!");
        input.nextLine();
        clearConsole();

        do {
            playGame(input);
        } while (handlePlayAgain(input));

    }

    public static void clearConsole() {
        for (int n = 0; n < 20; n++) {
            System.out.println();
        }
    }

    public static void playGame(Scanner input) {
        int max = getDifficulty(input);
        int answer = getRandomNumber(max);
        int guess = 0;
        int counter = 0;
        int lastGuess = 0;

        while (guess != answer) {
            counter++;
            clearConsole();
            if (lastGuess != 0) {
                System.out.println("Sidste gæt: " + lastGuess);
                System.out.println("Mit tal er " + checkGuess(guess, answer));
                System.out.println();
            }
            System.out.print("Gæt #" + counter + ": ");
            guess = input.nextInt();
            lastGuess = guess;
        }
        System.out.println(checkGuess(guess, answer));

    }

    public static int getDifficulty(Scanner input) {
        clearConsole();
        System.out.println("---------- Sværhedsgrad ----------");
        System.out.println("1... nemt");
        System.out.println("2... middel");
        System.out.println("3... svært\n");
        int difficulty = 1;
        do {
            if (difficulty <= 0 || difficulty >= 4) {
                System.out.println("FEJL: Indtastet tal ikke mellem 1-3.");
                System.out.println("Tast en gyldig sværhedsgrad.");
            }
            System.out.print("Jeg vælger: ");
            difficulty = input.nextInt();
        } while (difficulty <= 0 || difficulty >= 4);
        if (difficulty == 1) {
            return 10;
        } else if (difficulty == 2) {
            return 50;
        } else if (difficulty == 3) {
            return 100;
        }
        return difficulty;
    }

    public static int getRandomNumber(int max) {
        return (int) (Math.random() * max) + 1;
    }

    public static String checkGuess(int guess, int answer) {
        if (guess < answer) {
            return "Højere...";
        } else if (guess > answer) {
            return "Lavere...";
        } else {
            return "Korrekt!";
        }
    }

    public static boolean handlePlayAgain(Scanner input) {
        System.out.println("\nVil du spille igen?");
        System.out.println("1... Ja");
        System.out.println("2... Nej");
        int userInput = input.nextInt();
        return userInput == 1;
    }
}