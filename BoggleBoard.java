import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class BoggleBoard {
    String[][] board;
    String[][] tempBoard;
    LinkedList<String> possibleWords;
    int boardSize;

    BoggleBoard() {
        load();
        findAllPossibleWords();
    }

    public void load() {
        try {
            File boardFile = new File("testBoard.txt");
            Scanner fileScan = new Scanner(boardFile);
            boardSize = fileScan.nextInt();

            board = new String[boardSize][boardSize];
            tempBoard = new String[boardSize][boardSize];

            for (int row = 0; row < boardSize; row++) {
                for (int col = 0; col < boardSize; col++) {
                    String letter = fileScan.next();
                    board[row][col] = letter;
                    tempBoard[row][col] = letter;
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
                LetterCell currenCell = new LetterCell(row, col, tempBoard[row][col]);
                createWordFromCell("", currenCell);
            }
        }
    }

    private void createWordFromCell(String word, LetterCell cell) {
        int row = cell.getRow();
        int col = cell.getCol();

        if (row < 0 || row >= boardSize || col < 0 || col >= boardSize)
            return;

        word += cell.getCharacter();
        tempBoard[cell.getRow()][cell.getCol()] = "0";

        if (Dictionary.isValidWord(word, 0, Dictionary.getSize())){
            possibleWords.add(word);
        }

        createWordFromCell(word, new LetterCell(row - 1, col + 1, tempBoard[row - 1][col + 1], cell)); 
        // top right
        createWordFromCell(word, new LetterCell(row - 1, col, tempBoard[row - 1][col], cell)); 
        // left
        createWordFromCell(word, new LetterCell(row - 1, col - 1, tempBoard[row - 1][col - 1], cell)); 
        // top left
        createWordFromCell(word, new LetterCell(row, col + 1, tempBoard[row][col + 1], cell)); 
        // left
        createWordFromCell(word, new LetterCell(row, col - 1, tempBoard[row][col - 1], cell)); 
        // right
        createWordFromCell(word, new LetterCell(row + 1, col + 1, tempBoard[row + 1][col + 1], cell)); 
        // bottom right
        createWordFromCell(word, new LetterCell(row + 1, col, tempBoard[row + 1][col], cell)); 
        // bottom 
        createWordFromCell(word, new LetterCell(row + 1, col - 1, tempBoard[row + 1][col], cell)); 
        // bottom left
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
