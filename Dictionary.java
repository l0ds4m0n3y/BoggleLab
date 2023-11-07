import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary{
  private static ArrayList<String> dictionary = new ArrayList<>();
  private static File file = new File("Dictionary.txt");
  
  Dictionary(){
    try { load(); } catch (Exception e) {}
  }

  public void load() throws FileNotFoundException{
    Scanner scan = new Scanner(file);
    while(scan.hasNextLine()){
      dictionary.add(scan.nextLine());
    }
    System.out.println(dictionary.size());
    // for(String s : dictionary){
    //   System.out.println(s);
    // }
    scan.close();
  }
  public static Boolean isValidWord(String word, int start, int end){
    int index = (start + end) / 2;

    if(end <= start) return false;

    String wordAtIndex = dictionary.get(index);
    if(word.equals(wordAtIndex)){
      return true;
    }else if(word.compareTo(wordAtIndex) < 0){
      return isValidWord(word, start, index - 1);
    }else{
      return isValidWord(word, index + 1, end);
    }
  }

  public int getDictionarySize() {
    return dictionary.size();
  }
  
  public static void main(String[] args) {
    Dictionary dict = new Dictionary();
    System.out.println(Dictionary.isValidWord("nincompoop", 0, dict.getDictionarySize()));
  }
}
