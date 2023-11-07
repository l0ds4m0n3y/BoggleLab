import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BoggleBoard board;
        try {
            board = new BoggleBoard();
            board.printBoard(); 
        } catch (FileNotFoundException e) {}
        scan.close();
    }
}
