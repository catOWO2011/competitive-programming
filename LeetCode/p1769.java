import java.util.Arrays;

public class p1769 {
  public int[] minOperations(String boxes) {
    int boxesAmount = boxes.length();

    int[] operationsToLeft = new int[boxesAmount];
    int ballToLeft = 0;
    for (int index = 1; index < boxesAmount; index++) {
      boolean hasABall = boxes.charAt(index - 1) == '1';
      if (hasABall) {
        ballToLeft++;
      }
      operationsToLeft[index] = operationsToLeft[index - 1] + ballToLeft;
    }

    int[] operationsToRight = new int[boxesAmount];
    int ballsToRight = 0;
    for (int index = boxesAmount - 2; index > -1; index--) {
      boolean hasABall = boxes.charAt(index + 1) == '1';
      if (hasABall) {
        ballsToRight++;
      }
      operationsToRight[index] = operationsToRight[index + 1] + ballsToRight;
    }

    // System.out.println(Arrays.toString(operationsToLeft) + " Left");
    // System.out.println(Arrays.toString(operationsToRight) + " Right");

    int[] totalBallsAmount = new int[boxesAmount];

    for (int index = 0; index < totalBallsAmount.length; index++) {
      totalBallsAmount[index] = operationsToLeft[index] + operationsToRight[index];
    }

    return totalBallsAmount;
  }

  public void solve() {
    System.out.println(Arrays.toString(minOperations("110")));
    System.out.println(Arrays.toString(minOperations("001011")));
  }

  public static void main(String[] args) {
    new p1769().solve();
  }
}