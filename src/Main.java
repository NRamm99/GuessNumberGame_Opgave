import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        welcomeSequence(input);

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
                System.out.println("Mit tal er " + checkGuess(guess, answer) + "\n");
            }
            System.out.print("Gæt #" + counter + ": ");
            guess = input.nextInt();
            lastGuess = guess;
        }
        System.out.println(checkGuess(guess, answer));

    }

    public static int getDifficulty(Scanner input) {
        clearConsole();
        System.out.println("""
                ---------- Sværhedsgrad ----------
                1... nemt (1-10)
                2... middel (1-50)
                3... svært (1-100)
                """);

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
        } else {
            return 100;
        }
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
        System.out.println("""
                \nVil du spille igen?
                1... Ja
                2... Nej""");
        int userInput = input.nextInt();
        return userInput == 1;
    }

    public static void welcomeSequence(Scanner input) {
        clearConsole();
        System.out.println("""
                ---------- VELKOMMEN ----------
                Velkommen til tal-spillet!
                Instruktioner:
                 - Gæt tallet som jeg tænker på!
                 - jeg hjælper dig på vej.
                -------------------------------""");

        System.out.print("\nTryk enter når du er klar til at spille!");
        input.nextLine();
        clearConsole();

    }
}