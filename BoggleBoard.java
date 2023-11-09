import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class BoggleBoard {
    String[][] board;
    Boolean[][] tempBoard;
    LinkedList<String> possibleWords;
    int boardSize;
    int numDice;
    ArrayList<Dice> diceArray;
    Dictionary dictionary;

    BoggleBoard() {
        dictionary = new Dictionary();
        load();
        findAllPossibleWords();
    }

    BoggleBoard(int size) {
        dictionary = new Dictionary();
        randomBoard(size);
        findAllPossibleWords();
    }

    public void loadDice() {
        diceArray = new ArrayList<>();
        try {
            Scanner diceScanner = new Scanner(new File("Dice.txt"));
            while (diceScanner.hasNext()) {
                String diceValues = diceScanner.nextLine();
                Scanner lineScanner = new Scanner(diceValues);
                int size = 0;
                for (int i = 0; i < diceValues.length(); i++) {
                    if (diceValues.charAt(i) != ' ') {
                        size++;
                    }
                }

                if (diceValues.contains("Qu"))
                    size--;

                diceArray.add(new Dice(size, diceValues));
                lineScanner.close();
            }
            diceScanner.close();
        } catch (Exception e) {
        }
    }

    public void randomBoard(int size) {
        loadDice();
        boardSize = size;
        int die = 0;
        board = new String[boardSize][boardSize];
        tempBoard = new Boolean[boardSize][boardSize];
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (die >= diceArray.size())
                    die = 0;
                board[row][col] = diceArray.get(die).roll();
                tempBoard[row][col] = false;
                die++;
            }
        }

    }

    public void load() {
        try {
            File boardFile = new File("testBoard.txt"); // TODO change to Board.txt
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

        if (word.length() > 2 && word.length() < 29 && dictionary.isValidWord(word)) { // 28 is the longest word
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

    public Dictionary getDictionary() {
        return dictionary;
    }

    public String[][] getBoard() {
      return board;
    }

}