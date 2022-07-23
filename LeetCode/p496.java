import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class p496 {
  public static void main(String[] args) {
    new p496().solve();
  }

  public void solve() {
    System.out.println(Arrays.toString(nextGreaterElement(new int[] { 4, 1, 2 }, new int[] { 1, 3, 4, 2 })));
    System.out.println(Arrays.toString(nextGreaterElement(new int[] { 2, 4 }, new int[] { 1, 2, 3, 4 })));
  }

  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    int[] nextGreaterElements = new int[nums1.length];

    // Build a hashMap to save the number and its first greater element
    HashMap<Integer, Integer> nextGreaterMap = new HashMap<>();
    // Build a monotonic stack, the stack is decreasing from the bottom to top
    Stack<Integer> monotonicStack = new Stack<>();
    // Loop through nums2 using number as interator variable
    for (Integer number : nums2) {
      // Loop to check if the stack isn't empty, in case if not empty take the top
      // and compares the top against the number if the top is less than the number
      while (!monotonicStack.isEmpty() && monotonicStack.peek() < number) {
        // Set the element variable with the top removed from the stack
        int element = monotonicStack.pop();
        // Add to nextGreaterMap the following pair { element, number }
        nextGreaterMap.put(element, number);
      }
      // Add the element number to the stack
      monotonicStack.push(number);
    }
    // Loop the nums2 with the number value
    for (int i = 0; i < nums1.length; i++) {
      int number = nums1[i];
      // Verify if the number exist on nextGreaterMap
      if (nextGreaterMap.containsKey(number)) {
        nextGreaterElements[i] = nextGreaterMap.get(number);
      } else {
        nextGreaterElements[i] = -1;
      }
    }

    return nextGreaterElements;
  }
}