public class p2400 {
  public int numberOfWays(int startPos, int endPos, int k) {
    int distance = Math.abs(endPos - startPos);

    if (distance != 0 && (distance > k || ((k - distance) % 2 != 0)))
      return 0;

    int ways = 0;
    if (k > distance) {
      int leftSteps = (k - distance) / 2;
      // System.out.println("k, " + distance);
      ways = binomialCoeff(k, leftSteps);
    } else {
      ways = 1;
    }

    return ways;
  }

  public int binomialCoeff(int n, int r) {

    if (r > n)
      return 0;

    long m = 1000000007;

    long inv[] = new long[r + 1];

    inv[0] = 1;
    if (r + 1 >= 2)
      inv[1] = 1;

    // Getting the modular inversion
    // for all the numbers
    // from 2 to r with respect to m
    // here m = 1000000007
    for (int i = 2; i <= r; i++) {
      inv[i] = m - (m / i) * inv[(int) (m % i)] % m;
    }

    int ans = 1;

    // for 1/(r!) part
    for (int i = 2; i <= r; i++) {
      ans = (int) (((ans % m) * (inv[i] % m)) % m);
    }

    // for (n)*(n-1)*(n-2)*...*(n-r+1) part
    for (int i = n; i >= (n - r + 1); i--) {
      ans = (int) (((ans % m) * (i % m)) % m);
    }

    return ans;
  }

  public void solve() {
    System.out.println(numberOfWays(1, 2, 3));
    System.out.println(numberOfWays(2, 5, 10));
    System.out.println(numberOfWays(3, 4, 5));
    System.out.println(numberOfWays(669, 671, 4));
    System.out.println(numberOfWays(401, 406, 7));
  }

  public static void main(String[] args) {
    new p2400().solve();
  }
}