import java.util.LinkedList;

public class p402 {
  public static void main(String[] args) {
    new p402().solve();
  }

  public void solve() {
    System.out.println(removeKdigits("1432219", 3)); // 1219
    System.out.println(removeKdigits("10200", 1)); // 200
    System.out.println(removeKdigits("10", 2)); // 0
    System.out.println(removeKdigits("1234567890", 9)); // 0
  }

  /**
   * The solution for this problem is using a mono-increasing stack (Because we
   * keep popping greater elements out
   * and keep smaller elements)
   * 
   * @param num
   * @param k
   * @return
   */
  public String removeKdigits(String num, int k) {
    // Define a mono-increasing stack as monoIncStack, in java using an anonymous
    // class
    LinkedList<Character> monoIncStack = new LinkedList<>() {
      @Override
      public String toString() {
        // Declare number as empty String Builder to decrease some time, this is just
        // time improvment
        StringBuilder sb = new StringBuilder();
        // Remove zeros at the start but leave at least one
        while (!isEmpty() && getFirst() == '0') {
          removeFirst();
        }

        while (!isEmpty()) {
          sb.append(removeFirst());
        }

        if (sb.length() == 0) {
          sb.append('0');
        }

        return sb.toString();
      }
    };
    // Loop characters from num as digit
    for (int numIndex = 0; numIndex < num.length(); numIndex++) {
      char digit = num.charAt(numIndex);
      // Loop and check if MonoIncStack is not empty and verify if k > 0 and verify if
      // the top
      // is greater/equal than the digit
      while (!monoIncStack.isEmpty() && k > 0 && monoIncStack.getLast() > digit) {
        // Removed top
        monoIncStack.removeLast();
        // Decrease k
        k--;
      }
      // Add the digit to the stack
      monoIncStack.addLast(digit);
    }

    while (k > 0 && !monoIncStack.isEmpty()) {
      monoIncStack.removeLast();
      k--;
    }
    // System.out.println(monoIncStack + "...");

    return monoIncStack + "";
  }

  public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
}