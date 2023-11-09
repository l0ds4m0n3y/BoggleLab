import java.io.File;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class BoggleBoard {
    String[][] board;
    Boolean[][] tempBoard;
    LinkedList<String> possibleWords;
    int boardSize;

    BoggleBoard() {
        load();
        findAllPossibleWords();
    }

    public void randomBoard(){
        board = new String[4][4];
        Random rand = new Random();
        try {
            File diceFile = new File("Dice.txt");
            Scanner scan = new Scanner(diceFile);
            String ptr = "";

            for(int row = 0; row < 4; row++){
                for(int col = 0; col < 4; col++){
                    for(int i = 0; i < rand.nextInt(6); i++){
                        ptr = scan.next();
                    }
                    board[row][col] = ptr;
                    scan.nextLine();
                }
            }
        } catch (Exception e) {}
    }

    public void load() {
        try {
            File boardFile = new File("testBoard.txt");
            Scanner fileScan = new Scanner(boardFile);
            boardSize = fileScan.nextInt();

            board = new String[boardSize][boardSize];
            tempBoard = new Boolean[boardSize][boardSize];

            for (int row = 0; row < boardSize; row++) {
                for (int col = 0; col < boardSize; col++) {
                    String letter = fileScan.next();
                    board[row][col] = letter;
                    tempBoard[row][col] = false;
                }
            }
            fileScan.close();
        } catch (Exception e) {
        }
    }

    private void findAllPossibleWords() {
        possibleWords = new LinkedList<>();
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                createWordFromCell("", row, col);
                for (int row2 = 0; row2 < boardSize; row2++) {
                    for (int col2 = 0; col2 < boardSize; col2++) {
                        tempBoard[row2][col2] = false;
                    }
                }
            }
        }
    }

    private void createWordFromCell(String word, int row, int col) {
        if (row < 0 || row >= boardSize || col < 0 || col >= boardSize)
            return;

        if (tempBoard[row][col])
            return;

        word += board[row][col].toLowerCase();
        tempBoard[row][col] = true;

        if (word.length() > 2 && Dictionary.isValidWord(word)) {
            possibleWords.add(word);
        }

        createWordFromCell(word, row - 1, col + 1);
        // top right
        createWordFromCell(word, row - 1, col);
        // left
        createWordFromCell(word, row - 1, col - 1);
        // top left
        createWordFromCell(word, row, col + 1);
        // left
        createWordFromCell(word, row, col - 1);
        // right
        createWordFromCell(word, row + 1, col + 1);
        // bottom right
        createWordFromCell(word, row + 1, col);
        // bottom
        createWordFromCell(word, row + 1, col - 1);
        // bottom left

        tempBoard[row][col] = false;
    }

    public void printBoard() {
        for (int row = 0; row < boardSize; row++) {
            System.out.print("| ");
            for (int col = 0; col < boardSize; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println("|");
        }
    }

    public LinkedList<String> getPossibleWords() {
        return possibleWords;
    }

}
