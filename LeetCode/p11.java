public class p11 {
  public int maxArea(int[] height) {
    int maximumAmountWater = 0;

    int leftIndex = 0;
    int rightIndex = height.length - 1;

    while (leftIndex < rightIndex) {
      int distance = rightIndex - leftIndex;

      int amountWater = Math.min(height[leftIndex], height[rightIndex]) * distance;
      maximumAmountWater = Math.max(maximumAmountWater, amountWater);

      if (height[leftIndex] > height[rightIndex]) {
        rightIndex--;
      } else {
        leftIndex++;
      }
    }

    return maximumAmountWater;
  }

  public void solve() {
    System.out.println(maxArea(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 }));
  }

  public static void main(String[] args) {
    new p11().solve();
  }
}