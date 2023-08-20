import java.util.Scanner;

public class p167 {

  public int findHighestScore(int[][] board, int[][] queenPositions) {

    return 0;
  }

  public int highestScore(int[][] board) {
    return 0;
  }

  public void solve() {
    Scanner in = new Scanner(System.in);

    int k = in.nextInt();
    while (k-- > 0) {
      int[][] board = new int[8][8];

      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          board[i][j] = in.nextInt();
        }
      }
    }

    in.close();
  }

  public static void main(String[] args) {
    new p167().solve();
  }
}