import java.io.FileWriter;
import java.io.IOException;

public class InOutCreator {
  private String buildPalindromeOutput(int number) {
    number++;
    boolean palindromeFound = false;
    String palindrome = "" + number;
    while (!palindromeFound) {
      String s = "" + number;
      boolean isEvenSize = s.length() % 2 == 0;
      int middle = s.length() / 2;
      if (isEvenSize) {
        String leftSide = s.substring(0, middle - 1);
        StringBuilder rightSide = new StringBuilder(s.substring(middle));
        palindromeFound = leftSide.equals(rightSide.reverse().toString());
      } else {
        String leftSide = s.substring(0, middle);
        StringBuilder rightSide = new StringBuilder(s.substring(middle + 1));
        palindromeFound = leftSide.equals(rightSide.reverse().toString());
      }

      if (palindromeFound) {
        palindrome = "" + number;
      }

      number++;
    }

    return palindrome;
  }

  private void buildPalindromeInputOutput(FileWriter inWriter, FileWriter outWriter) {
    int t = 20;
    int range = 100;
    try {
      inWriter.write(t + "\n");
      for (int i = 0; i < t; i++) {
        int number = (int) (Math.random() * range);
        inWriter.write(number + "\n");
        outWriter.write(buildPalindromeOutput(number) + "\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void solve() {
    try {
      FileWriter inWriter = new FileWriter("in.txt");
      FileWriter outWriter = new FileWriter("out.txt");

      buildPalindromeInputOutput(inWriter, outWriter);

      inWriter.close();
      outWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new InOutCreator().solve();
  }
}