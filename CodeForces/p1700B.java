package CodeForces;

import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;

public class p1700B {
  class Number {
    private int[] digits;
    private int leftIndex;

    public Number(int startNum) {
      digits = new int[100003];
      leftIndex = digits.length - 1;

      while (startNum > 0) {
        int digit = startNum % 10;
        startNum /= 10;
        digits[leftIndex] = digit;
        leftIndex--;
      }
    }

    public Number(String startNum) {
      digits = new int[startNum.length() + 1];
      leftIndex = digits.length - 1;

      for (int idx = startNum.length() - 1; idx > -1; idx--) {
        digits[leftIndex] = startNum.charAt(idx) - '0';
        leftIndex--;
      }
    }

    public void convertPalindrome() {
      int li = leftIndex + 1;
      int ri = digits.length - 1; 

      while (!isPalindrome()) {
        if (digits[li] != digits[ri]) {
          int carry = (digits[ri] + 1) / 10;
          digits[ri] = (digits[ri] + 1) % 10;

          if (carry > 0) {
            int rip = ri - 1;
            while (carry > 0) {
              carry = (digits[rip] + 1) / 10;
              digits[rip] = (digits[rip] + 1) % 10;
              rip--;
            }

            if (digits[leftIndex] != 0) {
              ri = leftIndex;
              li = digits.length - 1;
              leftIndex--;
            }
          }

          if (digits[li] == digits[ri]) {
            li++;
            ri--;
          }
        }
      }
    }

    public boolean isPalindrome() {
      int li = leftIndex + 1;
      int ri = digits.length - 1;

      boolean is = true;

      while(is && li < ri) {
        is = is && digits[li] == digits[ri];
        li++;
        ri--;
      }

      return is;
    }

    @Override
    public String toString() {
      String s = "";
      int li = leftIndex + 1;
      int ri = digits.length - 1;

      while (li <= ri) {
        s += digits[li];
        li++;
      }

      return s;
    }

    public int toInt() {
      int number = 0;
      int li = leftIndex + 1;

      while (li < digits.length) {
        number = number * 10 + digits[li];
        li++;
      }

      return number;
    }
  }

  public static void main(String[]args) {
    new p1700B().solve();
  }

  public void solve() {
    // Number number = new Number(1234);
    // Number numberA = new Number(1230);
    // Number numberB = new Number(3329);
    // Number numberC = new Number(1999);

    // number.convertPalindrome();
    // System.out.println(number);
    // numberA.convertPalindrome();
    // System.out.println(numberA);
    // numberB.convertPalindrome();
    // System.out.println(numberB);
    // numberC.convertPalindrome();
    // System.out.println(numberC);
    Scanner in = new Scanner(new InputStreamReader(System.in));

    int t = in.nextInt();

    while (t-- > 0) {
      int n = in.nextInt();
      BigInteger m = new BigInteger(in.next());
      BigInteger min = new BigInteger("10");
      
      if (m.toString().charAt(0) == '9') {
        min = min.pow(n + 1);
        min = min.subtract(BigInteger.ONE);
        min = min.divide(new BigInteger("9"));
      } else {
        min = min.pow(n);
        min = min.subtract(BigInteger.ONE);
      }
      // Number number = new Number((m.add(min)).toString());

      // if (!number.isPalindrome()) {
      //   number.convertPalindrome();
      //   min = (new BigInteger(number.toString())).subtract(m);
      // }

      System.out.println(min.subtract(m));
    }
  }
}