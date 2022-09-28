package AtCoder;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

public class abc263_c {
  final static Character EMPTY_SPACE = ' ';

  public void printMonotonicallyIncreasing(LinkedList<Integer> sequence, int from, int N, int M) {
    if (sequence.size() == N) {
      boolean printSpace = false;
      for (Integer element : sequence) {
        if (printSpace) {
          System.out.print(EMPTY_SPACE);
        }
        printSpace = !printSpace ? true : printSpace;
        System.out.print(element);
      }
      System.out.println();
    } else {
      for (int element = from; element <= M; element++) {
        sequence.addLast(element);
        printMonotonicallyIncreasing(sequence, element + 1, N, M);
        sequence.removeLast();
      }
    }
  }

  public void solve() {
    Scanner in = new Scanner(new InputStreamReader(System.in));
    int N = in.nextInt();
    int M = in.nextInt();

    printMonotonicallyIncreasing(new LinkedList<Integer>(), 1, N, M);
  }

  public static void main(String[] args) {
    new abc263_c().solve();
  }
}