import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Scanner scan = new Scanner(System.in);
        // BoggleBoard board;

        // System.out.println("Loading...");
        // new Dictionary();
        // board = new BoggleBoard();
        // board.printBoard();
        // LinkedList<String> answers = board.getPossibleWords();
        // System.out.print("Enter word: ");

        // while (true) {
        //     boolean wasAnswer = false;
        //     String input = scan.nextLine();
        //     if (input.equals("EXIT"))
        //         break;

        //     for (String s : answers) {
        //         if (input.equals(s)) {
        //             System.out.println(s + " is a valid word + " + s.length() + " points");
        //             answers.remove(s);
        //             wasAnswer = true;
        //             break;
        //         }
        //     }

        //     if (Dictionary.isValidWord(input) && !wasAnswer) {
        //         System.out.println(input + " is a word, but is not in the board or was already answered");
        //     } else if (!wasAnswer) {
        //         System.out.println(input + " is not a word");
        //     }

        //     board.printBoard();
        //     System.out.print("Enter word: ");
        // }

        // scan.close();

        new BoogleGame();
    }
}
