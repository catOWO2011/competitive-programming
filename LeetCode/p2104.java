import java.util.LinkedList;
import java.util.function.BiFunction;

public class p2104 {
  /**
   * Returns the total subarray sum, that is the sum of the differences of the
   * largest minus the smallest one
   * 
   * @param nums
   * @return
   */
  public long subArrayRanges(int[] nums) {
    long sum = 0;

    final int TOTAL_NUMBER_ELEMENTS = nums.length;

    // To solve this one we are going to use monotonic stack, this will be for the
    // largest values
    LinkedList<Integer> monoDecreasingStack = new LinkedList<>();

    // To solve this one we are going to use monotonic stack, this will be for the
    // smallest values
    LinkedList<Integer> monoIncreasinStack = new LinkedList<>();

    for (int currentIndex = 0; currentIndex <= TOTAL_NUMBER_ELEMENTS; currentIndex++) {
      sum += sumCurrentSubArray(nums, currentIndex, monoDecreasingStack, (a, b) -> a < b)
          - sumCurrentSubArray(nums, currentIndex, monoIncreasinStack, (a, b) -> a > b);
    }

    return sum;
  }

  /**
   * Returns the sum of the elements that has the possible subarray using
   * multiplication principle e.g
   * 4, 3, [10], 7, 6, 11 (for the number 10 we have the following places to
   * set the two brackets)
   * 
   * [4 [3 [10] 7] 6] it will 3 lefts bracket and 4 right brackets so the total
   * subarrays with 10 as the largest element it will be 3 * 3 = 9
   * [10]
   * [3, 10]
   * [4, 3, 10]
   * [4, 3, 10, 7]
   * [4, 3, 10, 7, 6]
   * [3, 10, 7]
   * [3, 10, 7, 6]
   * [10, 7]
   * [10, 7, 6]
   * 
   * @param nums
   * @param currentIndex
   * @param monoStack
   * @param compare
   * @return
   */
  private long sumCurrentSubArray(int[] nums, int currentIndex, LinkedList<Integer> monoStack,
      BiFunction<Integer, Integer, Boolean> compare) {
    long sum = 0;

    while (!monoStack.isEmpty()
        && (currentIndex == nums.length || compare.apply(nums[monoStack.getLast()], nums[currentIndex]))) {
      int lastElementIndex = monoStack.pollLast();
      long leftBracketsCount = (lastElementIndex - (monoStack.isEmpty() ? -1 : monoStack.getLast()));
      long rightBracketsCount = (currentIndex - lastElementIndex);

      sum += (leftBracketsCount * rightBracketsCount) * nums[lastElementIndex];
    }

    monoStack.add(currentIndex);

    return sum;
  }

  public long subArrayRangesNaive(int[] nums) {
    long sum = 0;

    for (int i = 0; i < nums.length; i++) {
      int segmentSize = 2;

      while (i + segmentSize <= nums.length) {
        int min = nums[i];
        int max = nums[i];

        for (int j = i + 1; j < segmentSize + i; j++) {
          int currNum = nums[j];
          if (min > currNum) {
            min = currNum;
          }

          if (max < currNum) {
            max = currNum;
          }
        }

        sum += (max - min);

        segmentSize++;
      }
    }

    return sum;
  }

  public void solve() {
    // System.out.println(subArrayRangesNaive(new int[] { 1, 2, 3 }));
    // System.out.println(subArrayRangesNaive(new int[] { 1, 3, 3 }));
    // System.out.println(subArrayRangesNaive(new int[] { 4, -2, -3, 4, 1 }));
    // System.out.println(subArrayRangesNaive(new int[] { 4, 4, 4, 4 }));
    System.out.println(subArrayRanges(new int[] { 4, -2, -3, 4, 1 }));
    System.out.println(subArrayRanges(new int[] { 1, 3, 3 }));
  }

  public static void main(String[] args) {
    new p2104().solve();
  }
}