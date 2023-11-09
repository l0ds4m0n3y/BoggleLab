import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {
  private static ArrayList<String> dictionary = new ArrayList<>();

  Dictionary() {
    load();
  }

  public void load() {
    try {
      File file = new File("Dictionary.txt");
      Scanner scan = new Scanner(file);

      while (scan.hasNextLine()) {
        dictionary.add(scan.nextLine());
      }
      scan.close();
    } catch (Exception e) {
    }
  }

  public static Boolean isValidWord(String word) {
    return isValidWord(word, 0, dictionary.size());
  }

  public static Boolean isValidWord(String word, int start, int end) {
    if (end < start)
      return false;

    int index = start + (end - start) / 2;
    String wordAtIndex = dictionary.get(index);

    if (word.equals(wordAtIndex)) {
      return true;
    } else if (word.compareTo(wordAtIndex) < 0) {
      return isValidWord(word, start, index - 1);
    } else {
      return isValidWord(word, index + 1, end);
    }
  }

  public static int getSize() {
    return dictionary.size();
  }
}
