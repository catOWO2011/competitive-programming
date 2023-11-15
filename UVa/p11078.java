import java.util.Scanner;

public class p11078 {
  public int maximumScoreSeniorJunior(int[] scores, int numberOfStudents) {
    int maximumScore = -1;

    int seniorPos = 0;
    for (int i = 1; i < numberOfStudents; i++) {
      if (scores[seniorPos] > scores[i]) {
        maximumScore = Math.max(maximumScore, scores[seniorPos] - scores[i]);
      } else {
        seniorPos = i;
      }
    }

    return maximumScore;
  }

  public void solve() {
    Scanner in = new Scanner(System.in);
    int T = in.nextInt();
    while (T-- > 0) {
      int numberOfStudents = in.nextInt();
      int[] scores = new int[numberOfStudents];

      for (int i = 0; i < numberOfStudents; i++) {
        scores[i] = in.nextInt();
      }

      System.out.println(maximumScoreSeniorJunior(scores, numberOfStudents));
    }

    in.close();
  }

  public static void main(String[] args) {
    new p11078().solve();
  }
}