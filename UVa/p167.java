import java.util.*;
import java.lang.*;
import java.io.*;

public class p167 {

  private boolean availablePosition(int row, int col, int[][] queenPositions) {
    boolean available = true;

    for (int prevRow = 0; prevRow < row && available; prevRow++) {
      int otherRow = queenPositions[prevRow][0];
      int otherCol = queenPositions[prevRow][1];
      available = !(otherRow == row ||
          otherCol == col ||
          Math.abs(otherCol - col) == Math.abs(otherRow - row));
    }

    return available;
  }

  private void fillPositions(int row, int[][] queenPositions, ArrayList<int[][]> positions) {
    if (row == 8) {
      int[][] newQueenPositions = new int[8][2];
      for (int i = 0; i < queenPositions.length; i++) {
        newQueenPositions[i][0] = queenPositions[i][0];
        newQueenPositions[i][1] = queenPositions[i][1];
      }
      positions.add(newQueenPositions);
    } else if (row < 8) {
      for (int col = 0; col < 8; col++) {
        if (availablePosition(row, col, queenPositions)) {
          queenPositions[row][0] = row;
          queenPositions[row][1] = col;
          fillPositions(row + 1, queenPositions, positions);
        }
      }
    }
  }

  public ArrayList<int[][]> getPossibleQueensPositionsByGame() {
    ArrayList<int[][]> queenPositionsByGame = new ArrayList<>();

    fillPositions(0, new int[8][2], queenPositionsByGame);

    return queenPositionsByGame;
  }

  public int highestScore(int[][] board, ArrayList<int[][]> queenPositionsByGame) {
    int highest = 0;

    for (int[][] positions : queenPositionsByGame) {
      int score = 0;
      for (int[] position : positions) {
        // System.out.println(Arrays.toString(position));
        score += board[position[0]][position[1]];
      }
      // System.out.println("......" + score);
      highest = Math.max(score, highest);
    }

    return highest;
  }

  public void solve() {
    ArrayList<int[][]> queenPositionsByGame = getPossibleQueensPositionsByGame();

    Scanner in = new Scanner(System.in);

    int k = in.nextInt();
    while (k-- > 0) {
      int[][] board = new int[8][8];

      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          board[i][j] = in.nextInt();
        }
      }

      String formattedNumber = String.format("%5d", highestScore(board, queenPositionsByGame));
      System.out.println(formattedNumber);
    }

    in.close();
  }

  public static void main(String[] args) {
    new p167().solve();
  }
}