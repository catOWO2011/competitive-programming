package CodeForces;

/**

#########################
[1, -2, 3, -4, 5] decreases 3 prefix
-2 - (1) = -3

[-2, -2, 3, -4, 5] (-5) decreases 5 suffix

[-2, -2, -2, -9, 0] (-5) decreases 7 prefix

[-9, -9, -9, -9, 0] (-5) decreases 9 suffix

[-9, -9, -9, -9, -9] increases 9

number of actions(decreases and increases) = 3 + 5 + 7 + 9 + 9 = 33

[-1, -4, -3]
-4 - (-1) = -3

[-4, -1, -3]
-1 - (-4) = 3

[-4, 6, -3]
6 - (-4) = 10

[-4, 2, -3]
2 - (-4) = 6

*/
import java.util.Scanner;

public class p1700C {
  public static void main(String[]args) {
    new p1700C().solve();
  }

  public void solve() {
    Scanner in = new Scanner(System.in);

    int t = in.nextInt();

    while (t-- > 0) {
      int n = in.nextInt();

      long[]a = new long[n];

      for (int i = 0; i < a.length; i++) {
        a[i] = in.nextLong();
      }

      long minimumActionsNumber = 0;
      long carry = 0; // this takes the decreases to the left (suffix)
      
      for (int i = 1; i < a.length; i++) {
        a[i] += carry;
        // System.out.println(Arrays.toString(a) + " current carry " + carry);
        if (a[i] != a[i- 1]) {
          long diff = a[i] - a[i- 1];

          if (diff < 0) {
            // prefix
            minimumActionsNumber += Math.abs(diff);
          } else {
            // suffix
            carry -= diff;
            // System.out.println("carry " + carry);
            // carry += diff;
            a[i] = a[i - 1];
            minimumActionsNumber += Math.abs(diff);
          }
        }
      }

      System.out.println(minimumActionsNumber + Math.abs(a[a.length - 1]));
    }
  }

}