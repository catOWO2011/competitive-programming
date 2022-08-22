public class p53 {
  public static void main(String[] args) {
    new p53().solve();
  }

  public void solve() {
    System.out.println(maxSubArray(new int[] { -2, 1, -3, 4, -1, 2, 1, 5, 4 }));
    System.out.println(maxSubArray(new int[] { 8, -19, 5, -4, 20 }));
    System.out.println(maxSubArray(new int[] { 5, 4, -1, 7, 8 }));
  }

  public int maxSubArray(int[] nums) {

    int currentSum = nums[0];
    int largestSum = currentSum;

    for (int index = 1; index < nums.length; index++) {
      int num = nums[index];

      if (currentSum + num < num) {
        currentSum = 0;
      }

      currentSum += num;

      largestSum = Math.max(currentSum, largestSum);
    }

    return largestSum;
  }
}