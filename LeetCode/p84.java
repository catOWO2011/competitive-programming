import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class p84 {
  public static void main(String[] args) {
    new p84().solve();
  }

  public void solve() {
    System.out.println(largestRectangleArea(new int[] { 2, 1, 5, 6, 2, 3 }));
    System.out.println(largestRectangleArea(new int[] { 2, 4 }));
    System.out.println(largestRectangleArea(new int[] { 4, 7, 20 }));
    System.out.println(largestRectangleArea(new int[] { 2, 1, 2 }));
    System.out.println(largestRectangleArea(new int[] { 4, 4, 4, 4 }));
    System.out.println(largestRectangleArea(new int[] { 4, 2, 0, 3, 2, 5 }));
  }

  /**
   * Solve using monotonic stack using indexs as references for the width of the
   * rectangle and
   * get the heights, the stack is going to save heights in a increasing way, if
   * this is no possible
   * the stack is going to pop his elements until we get that order, meanwhile is
   * poping we're going
   * to caculate the possible areas
   */
  public int largestRectangleArea(int[] heights) {
    int largestRectangleArea = 0;

    // Create the stack is increasing from bottom to top
    Stack<Integer> monoIncreasingStack = new Stack<>();

    // Convert the heights array into a List
    List<Integer> heightList = Arrays.stream(heights).boxed().collect(Collectors.toList());
    // Add an element that break the increasing order by default in this case zero,
    // to evaluate with last value
    heightList.add(0);
    // Loop through the height list
    for (int currenIndex = 0; currenIndex < heightList.size(); currenIndex++) {
      int newHeight = heightList.get(currenIndex);
      // Check if the stack is not empty then take the top value and verify is greater
      // or equals than the new height to start taking out the values and computing
      // the area for these heights
      while (!monoIncreasingStack.isEmpty() &&
          newHeight <= heightList.get(monoIncreasingStack.peek())) {
        // Get index at the top of mono increasing stack
        int lastIndex = monoIncreasingStack.pop();
        // Set the height variable using the index from previus step
        int height = heightList.get(lastIndex);
        // Set the width's value using the current index minus
        // the top value on the stack, if the stack is empty take as -1
        int currentIndexStack = monoIncreasingStack.isEmpty() ? -1 : monoIncreasingStack.peek();
        int width = currenIndex - currentIndexStack - 1;
        int rectangleArea = height * width;
        largestRectangleArea = Math.max(rectangleArea, largestRectangleArea);
      }
      // insert the new value's index
      monoIncreasingStack.push(currenIndex);
    }

    return largestRectangleArea;
  }
}