package AtCoder;

import java.io.InputStreamReader;
import java.util.Scanner;

public class abc263_b {
  public int countGenerations(int[] parents, int currentPerson) {
    int numberOfgenerations = 0;

    if (currentPerson > 1) {
      numberOfgenerations = 1 + countGenerations(parents, parents[currentPerson]);
    }

    return numberOfgenerations;
  }

  public void solve() {
    Scanner in = new Scanner(new InputStreamReader(System.in));

    int N = in.nextInt();
    int[] parents = new int[N + 1];

    for (int i = 2; i < parents.length; i++) {
      parents[i] = in.nextInt();
    }

    System.out.println(countGenerations(parents, N));
  }

  public static void main(String[] args) {
    new abc263_b().solve();
  }
}