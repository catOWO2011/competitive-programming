import java.io.InputStreamReader;
import java.util.Scanner;

public class PALIN {
  class PalindromeFactory {
    /**
     * Returns the next palindrome taking number as the starting point to seek
     * 
     * @param number
     * @return
     */
    public StringBuilder nextPalindrome(String stringNumber) {
      StringBuilder sbNumber = new StringBuilder(stringNumber);

      int leftIndex = 0;
      int rightIndex = sbNumber.length() - 1;
      boolean hasIncreased = false;

      // Tranform the number to palindrome
      while (leftIndex < rightIndex) {
        char leftDigit = sbNumber.charAt(leftIndex);
        char rightDigit = sbNumber.charAt(rightIndex);

        if (leftDigit > rightDigit) {
          hasIncreased = true;
        } else if (leftDigit < rightDigit) {
          hasIncreased = false;
        }
        sbNumber.setCharAt(rightIndex, leftDigit);

        leftIndex++;
        rightIndex--;
      }

      if (!hasIncreased) {
        int middle = stringNumber.length() / 2;
        boolean stringSizeIsEven = stringNumber.length() % 2 == 0;
        boolean existCarry = false;

        if (sbNumber.charAt(middle) == '9') {
          existCarry = true;
        }

        rightIndex = middle;
        leftIndex = middle;
        if (stringSizeIsEven) {
          leftIndex = stringSizeIsEven ? middle - 1 : middle;
        }

        if (existCarry) {
          // Increase the digits from the middle to the corners
          while (leftIndex > -1 && existCarry) {
            char leftDigit = sbNumber.charAt(leftIndex);

            if (leftDigit == '9') {
              leftDigit = '0';
              existCarry = true;
            } else {
              leftDigit++;
              existCarry = false;
            }

            sbNumber.setCharAt(leftIndex, leftDigit);
            sbNumber.setCharAt(rightIndex, leftDigit);

            leftIndex--;
            rightIndex++;
          }

          if (existCarry) {
            sbNumber.setCharAt(0, '1');
            sbNumber.append('1');
          }

        } else {

          char digit = sbNumber.charAt(middle);
          digit++;
          if (stringSizeIsEven) {
            sbNumber.setCharAt(leftIndex, digit);
            sbNumber.setCharAt(rightIndex, digit);
          } else {
            sbNumber.setCharAt(rightIndex, digit);
          }
        }
      }

      return sbNumber;
    }
  }

  public void solve() {
    PalindromeFactory palindromeFactory = new PalindromeFactory();

    Scanner in = new Scanner(new InputStreamReader(System.in));
    int t = in.nextInt();

    while (t > 0) {
      String stringNumber = in.next();
      System.out.println(palindromeFactory.nextPalindrome(stringNumber));
      t--;
    }
  }

  public static void main(String[] args) {
    new PALIN().solve();
  }
}