import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BoggleBoard board;

        new Dictionary();
        Dictionary.isValidWord("sex", 0, Dictionary.getSize());
        board = new BoggleBoard();
        board.printBoard(); 
        for(String s : board.getPossibleWords()){
            System.out.println(s);
        }
        
        scan.close();
    }
}
