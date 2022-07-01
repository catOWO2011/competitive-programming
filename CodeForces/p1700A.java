package CodeForces;

import java.io.InputStreamReader;
import java.util.Scanner;

public class p1700A {
  public static void main(String[]args) {
    new p1700A().solve();
  }

  public void solve() {
    Scanner in = new Scanner(new InputStreamReader(System.in));

    int t = in.nextInt();

    while (t-- > 0) {
      long n, m;

      n = in.nextInt();
      m = in.nextInt();

      m--;

      long minimalPossibleCost = ((m * (m + 1)) / 2) + ((m + 1) * ((n * (n + 1)) / 2));

      System.out.println(minimalPossibleCost);
    }
  }
}