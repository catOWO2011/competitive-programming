import java.util.Scanner;

public class p11332 {
  public int summingDigits(int n) {

    do {
      int np = 0;
      while (n > 0) {
        np += n % 10;
        n /= 10;
      }
      n = np;
    } while (n > 9);

    return n;
  }

  public void solve() {
    Scanner in = new Scanner(System.in);

    int n = 0;
    do {
      n = in.nextInt();
      if (n > 0) {
        System.out.println(summingDigits(n));
      }
    } while (n > 0);

    in.close();
  }

  public static void main(String[] args) {
    new p11332().solve();
  }
}