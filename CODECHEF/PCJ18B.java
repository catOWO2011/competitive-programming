package CODECHEF;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Solving using deduction
 * for:
 * N = 1 -> 1 square with odd side length
 * {1}
 * N = 2 -> 4 squares with odd side length, remember is N * N
 * {4}
 * N = 3 -> 10 squares with odd side length, there are 9 squares with 1 side length and 1 with 3 side length
 * {1, 9}
 * N = 4 -> 20 squares with odd side length, there are 16 squares with 1 side length and 4 with 3 side length
 * {4, 16}
 * N = 5 -> 25 squares with odd side length, there are 9 squares with 3 side length and 1 with 5 side length
 * {1, 9, 25}
 * N = 6 -> 36 squares with odd side length, there are 16 squares with 3 side length and 4 with 5 side length
 * {4, 16, 36}
 * The pattern is showed above so the solution is for even numbers:
 * 2*2 + 4*4 + 6*6 + ... + and so on
 * and for the odd numbers:
 * 1*1 + 3*3 + 5*5 + ... + and so on
 */

public class PCJ18B {
  private ArrayList<Long> countOddSquares() {
    ArrayList<Long> oddSquaresSum = new ArrayList<>(1000);
    long value = 1;
    oddSquaresSum.add(value);
    value++;
    oddSquaresSum.add(value * value);

    for (int i = 2; i <= 1000; i++) {
      value = oddSquaresSum.get(i - 2);
      oddSquaresSum.add(value + ((i + 1) * (i + 1)));
    }

    return oddSquaresSum;
  }



  public void solve() {
    ArrayList<Long> oddSquaresSum = countOddSquares();

    Scanner scanner = new Scanner(System.in);

    int T = scanner.nextInt();

    while (T-- > 0) {
      int N = scanner.nextInt();

      System.out.println(oddSquaresSum.get(N - 1));
    }

    scanner.close();
  }

  public static void main(String[]args) {
    new PCJ18B().solve();
  }
}