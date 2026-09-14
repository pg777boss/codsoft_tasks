import java.util.Random;
import java.util.Scanner;
public class numberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        char playAgain;
        System.out.println("============ NUMBER GUESSING GAME ============");
        do {
            int number = random.nextInt(100) + 1;
            int attempts = 7;
            boolean guessed = false;
            System.out.println("\nI have selected a number between 1 and 100.");
            System.out.println("You have " + attempts + " attempts.");
            for (int i = 1; i <= attempts; i++) {
                System.out.print("\nAttempt " + i + " - Enter your guess: ");
                int guess = scanner.nextInt();
                if (guess < 1 || guess > 100) {
                    System.out.println("Please enter a number between 1 and 100.");
                    i--;
                    continue;
                }
                if (guess == number) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    guessed = true;
                    break;
                }
                else if (guess > number) {
                    System.out.println("Too high! Try a smaller number.");
                }
                else {
                    System.out.println("Too low! Try a larger number.");
                }
            }
            if (!guessed) {
                System.out.println("\nGame Over!");
                System.out.println("The correct number was: " + number);
            }
            System.out.print("\nDo you want to play again? (Y/N): ");
            playAgain = scanner.next().charAt(0);
        } while (playAgain == 'Y' || playAgain == 'y');
        System.out.println("Thank you for playing!");
    }
}