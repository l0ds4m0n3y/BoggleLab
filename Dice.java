import java.util.Random;
import java.util.Scanner;

public class Dice {
  String[] values;
  Random rand = new Random();

  Dice(int size, String s){
    values = new String[size];
    Scanner scan = new Scanner(s);
    int i = 0;

    while(scan.hasNext()){
      values[i] = scan.next();
      i++;
    }
    scan.close();
  }

  public String roll(){
    return values[rand.nextInt(values.length)];
  }

  @Override
  public String toString() {
    String str = "";
    for(String s : values){
      str += s;
    }
    return str;
  }
}
