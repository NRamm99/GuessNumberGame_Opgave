import java.util.Scanner;

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
        int[] range = getDifficulty(input);
        int min = range[0];
        int max = range[1];
        int answer = getRandomNumber(min, max);
        int guess = -10;
        int counter = 0;
        int lastGuess = 0;
        boolean gameWin = false;

        while (!gameWin) {
            counter++;
            clearConsole();

            if ((checkGuess(guess, answer) == -1) && (counter > 1)) {
                System.out.println("Sidste gæt: " + lastGuess);
                System.out.println("Mit tal er lavere...\n");
                System.out.println("Interval: (1 - " + max + ")");
                System.out.print("Gæt #" + counter + ": ");
            } else if ((checkGuess(guess, answer) == 0) && (counter >= 1)) {
                System.out.println("Korrekt!");
                System.out.println("Du gættede rigtigt på " + (counter - 1) + ". forsøg!");
                gameWin = true;
            } else if ((checkGuess(guess, answer) == 1) && (counter > 1)) {
                System.out.println("Sidste gæt: " + lastGuess);
                System.out.println("Mit tal er højere...\n");
                System.out.println("Interval: (1 - " + max + ")");
                System.out.print("Gæt #" + counter + ": ");
            } else {
                System.out.println("Interval: (1 - " + max + ")");
                System.out.print("Gæt #" + counter + ": ");
            }

            if (!gameWin) {

                while (!input.hasNextInt()) {
                    System.out.println("Fejl: Du skal indtaste et tal!");
                    input.next();
                }
                guess = input.nextInt();
                lastGuess = guess;
            }
        }
    }

    public static int[] getDifficulty(Scanner input) {
        clearConsole();
        System.out.println("""
                ---------- Sværhedsgrad ----------
                1... nemt (1-10)
                2... middel (1-50)
                3... svært (1-100)
                """);

        int userInput;

        do {
            System.out.print("Jeg vælger: ");
            while (!input.hasNextInt()) {
                System.out.println("Fejl: Du skal indtaste et tal!");
                input.next();
            }
            userInput = input.nextInt();

            if (userInput < 1 || userInput > 3) {
                System.out.println("FEJL: Indtastet tal ikke mellem 1-3. Prøv igen.");
            }

        } while (userInput < 1 || userInput > 3);

        int min = 1;
        int max = switch (userInput) {
            case 1 -> 10;
            case 2 -> 50;
            case 3 -> 100;
            default -> 0;
        };

        return new int[]{min, max};
    }

    public static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static int checkGuess(int guess, int answer) {

        return Integer.compare(answer, guess);
    }

    public static boolean handlePlayAgain(Scanner input) {
        System.out.println("""
                \nVil du spille igen?
                1... Ja
                2... Nej""");
        while (!input.hasNextInt()) {
            System.out.println("Fejl: Du skal indtaste et tal!");
            input.next();
        }
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