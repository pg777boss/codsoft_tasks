import java.util.Scanner;
import java.util.concurrent.*;
public class QuizApplication {
    // Quiz questions
    static String[] questions = {
            "Which language is used for Android development?",
            "Which keyword is used to create a class in Java?",
            "Which method is the starting point of a Java program?",
            "Which data type is used to store decimal numbers?"
    };
    // Options for each question
    static String[][] options = {
            {"A. Java", "B. HTML", "C. SQL", "D. CSS"},
            {"A. object", "B. class", "C. new", "D. create"},
            {"A. start()", "B. run()", "C. main()", "D. begin()"},
            {"A. int", "B. char", "C. boolean", "D. double"}
    };
    // Correct answers
    static char[] correctAnswers = {'A', 'B', 'C', 'D'};
    // Time allowed for each question
    static final int TIME_LIMIT = 10;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        int correct = 0;
        int incorrect = 0;
        System.out.println("======================================");
        System.out.println("       QUIZ APPLICATION WITH TIMER");
        System.out.println("======================================");
        System.out.println("You have " + TIME_LIMIT + " seconds for each question.\n");
        for (int i = 0; i < questions.length; i++) {
            System.out.println("--------------------------------------");
            System.out.println("Question " + (i + 1) + ": " + questions[i]);
            for (String option : options[i]) {
                System.out.println(option);
            }
            System.out.print("\nEnter your answer: ");
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<String> answer = executor.submit(() -> scanner.nextLine());
            try {
                String userAnswer = answer.get(
                        TIME_LIMIT,
                        TimeUnit.SECONDS
                );
                char selectedAnswer =
                        Character.toUpperCase(userAnswer.trim().charAt(0));
                if (selectedAnswer == correctAnswers[i]) {
                    System.out.println("Correct answer!");
                    score++;
                    correct++;
                } else {
                    System.out.println("Incorrect answer!");
                    System.out.println("Correct answer is: " + correctAnswers[i]);
                    incorrect++;
                }
            } catch (TimeoutException e) {
                System.out.println("\nTime's up!");
                System.out.println("Correct answer is: " + correctAnswers[i]);
                incorrect++;
                answer.cancel(true);
            } catch (Exception e) {
                System.out.println("Invalid input.");
                incorrect++;
            } finally {
                executor.shutdownNow();
            }
        }
        // Final Result
        System.out.println("\n======================================");
        System.out.println("             QUIZ RESULT");
        System.out.println("======================================");
        System.out.println("Total Questions : " + questions.length);
        System.out.println("Correct Answers : " + correct);
        System.out.println("Incorrect       : " + incorrect);
        System.out.println("Final Score     : " + score + "/" + questions.length);
        System.out.println("======================================");
    }
}