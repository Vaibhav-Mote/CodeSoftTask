import java.util.*;
public class task1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int lowerBound = 1;
        int upperBound = 100;
        int numberOfAttempts = 10;
        int rounds = 0;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        do {
            int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            System.out.println("\nRound " + (rounds + 1) + " - Guess the number between " + lowerBound + " and " + upperBound);

            for (int attempts = 1; attempts <= numberOfAttempts; attempts++) {
                System.out.print("Attempt " + attempts + ": Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    score += numberOfAttempts - (attempts - 1);  
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Try again. Your guess is too low.");
                } else {
                    System.out.println("Try again. Your guess is too high.");
                }

                if (attempts == numberOfAttempts) {
                    System.out.println("Sorry, you've run out of attempts. The correct number was: " + targetNumber);
                }
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();

            if (playAgain.equals("yes")) {
                rounds++;
            } else if (playAgain.equals("no")) {
                break;
            } else {
                System.out.println("Invalid input. Exiting the game.");
                break;
            }
        } while (true);

        System.out.println("Game over! Your total score: " + score);
        scanner.close();
    }
}
