import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class BoggleBoard {
  String[][] board;
  LinkedList<String> possibleWords;
  int boardSize;

  BoggleBoard() throws FileNotFoundException{
    load();
    findAllPossibleWords();
  }

  public void load() throws FileNotFoundException{
    File boardFile = new File("testBoard.txt");
    Scanner fileScan = new Scanner(boardFile);
    boardSize = fileScan.nextInt();

    board = new String[boardSize][boardSize];

    for(int row = 0; row < boardSize; row++){
        for(int col = 0; col < boardSize; col++){
            board[row][col] = fileScan.next();
        }
    }
    fileScan.close();
}

private void findAllPossibleWords(){
    possibleWords = new LinkedList<>();
    for(int row = 0; row < boardSize; row++){
        for(int col = 0; col < boardSize; col++){
            LetterCell currenCell = new LetterCell(row, col, board[row][col]);
        }
    }
}

private void createWordFromCell(String[][] borad, String word, LetterCell cell){
    word = "";
    String[][] tempBoard = board.clone();
    if(word.length() >= boardSize * boardSize)
        return;
    if(Dictionary.isValidWord(word, 0, Dictionary.getDictionarySize())){
        possibleWords.add(word);
        word += cell.getCharacter();
        
    }else{
        word += cell.getCharacter();
    }
}

public void printBoard(){
    for(int row = 0; row < boardSize; row++){
        System.out.print("| ");
        for(int col = 0; col < boardSize; col++){
            System.out.print(board[row][col] + " ");
        }
        System.out.println("|");
    }
  }

public LinkedList<String> getPossibleWords() {
      return possibleWords;
  }

}
