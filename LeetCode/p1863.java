import java.util.function.BiFunction;

public class p1863 {
  public int subsetXORSum(int[] nums) {
    int totalSubsetXORSum = 0;

    int lastSet = (1 << nums.length);
    for (int set = 1; set < lastSet; set++) {
      int setXORSum = 0;
      for (int itemIndex = 0; itemIndex < nums.length; itemIndex++) {
        boolean selected = (set & (1 << itemIndex)) != 0;
        if (selected) {
          setXORSum ^= nums[itemIndex];
        }
      }

      totalSubsetXORSum += setXORSum;
    }

    return totalSubsetXORSum;
  }

  public int subsetXORSumRecursive(int[] nums) {
    return subsetXORSumRecursive(nums, 0, 0);
  }

  public int subsetXORSumRecursive(int[] nums, int index, int setXORSum) {
    if (index == nums.length)
      return setXORSum;

    int pickedSetXORSum = subsetXORSumRecursive(nums, index + 1, setXORSum ^ nums[index]);
    int notpickedSetXORSum = subsetXORSumRecursive(nums, index + 1, setXORSum);
    return pickedSetXORSum + notpickedSetXORSum;
  }

  public void solve() {
    System.out.println(subsetXORSum(new int[] { 5, 1, 6 }));
    System.out.println(subsetXORSumRecursive(new int[] { 5, 1, 6 }));
  }

  public static void main(String[] args) {
    new p1863().solve();
  }
}