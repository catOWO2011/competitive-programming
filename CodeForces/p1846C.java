package CodeForces;

import java.io.*;
import java.util.*;

public class p1846C {
  class Player implements Comparable<Player> {
    long totalPenality;
    int index;
    int points;

    public Player(int index, long totalPenality, int points) {
      this.index = index;
      this.totalPenality = totalPenality;
      this.points = points;
    }

    @Override
    public String toString() {
      return "{ index = " + index + ", points = " + points + ", " + totalPenality + "}";
    }

    public int compareTo(Player playerB) {
      if (points == playerB.points) {
        if (totalPenality == playerB.totalPenality) {
          return index - playerB.index;
        } else
          return totalPenality < playerB.totalPenality ? -1 : 1;
      } else
        return playerB.points - points;
    }
  }

  public void solve() throws IOException {
    Scanner in = new Scanner(System.in);

    int T = in.nextInt();

    while (T-- > 0) {
      int n = in.nextInt();
      int m = in.nextInt();
      int h = in.nextInt();

      // Player[] players = new Player[n];

      Player rudolf = null;
      int position = 1;
      for (int i = 0; i < n; i++) {
        Long[] t = in.nextLongArray(m);
        Arrays.sort(t);

        long penalityTime = 0;
        boolean keepSolving = true;
        long timer = 0;
        int points = 0;

        for (int j = 0; j < m && keepSolving; j++) {
          if (timer + t[j] <= h) {
            penalityTime = penalityTime + (t[j] + timer);
            timer += t[j];
            points++;
          } else {
            keepSolving = false;
          }

        }

        if (i == 0) {
          rudolf = new Player(i, penalityTime, points);
        } else {
          Player other = new Player(i, penalityTime, points);
          if (other.compareTo(rudolf) < 0) {
            position++;
          }
        }
      }

      System.out.println(position);
    }

    // in.close();
  }

  class Scanner {
    StringTokenizer st;
    BufferedReader br;

    public Scanner(InputStream s) {
      br = new BufferedReader(new InputStreamReader(s));
    }

    public Scanner(FileReader r) {
      br = new BufferedReader(r);
    }

    public String next() throws IOException {
      while (st == null || !st.hasMoreTokens())
        st = new StringTokenizer(br.readLine());
      return st.nextToken();
    }

    public int nextInt() throws IOException {
      return Integer.parseInt(next());
    }

    public long nextLong() throws IOException {
      return Long.parseLong(next());
    }

    public String nextLine() throws IOException {
      return br.readLine();
    }

    public double nextDouble() throws IOException {
      String x = next();
      StringBuilder sb = new StringBuilder("0");
      double res = 0, f = 1;
      boolean dec = false, neg = false;
      int start = 0;
      if (x.charAt(0) == '-') {
        neg = true;
        start++;
      }
      for (int i = start; i < x.length(); i++)
        if (x.charAt(i) == '.') {
          res = Long.parseLong(sb.toString());
          sb = new StringBuilder("0");
          dec = true;
        } else {
          sb.append(x.charAt(i));
          if (dec)
            f *= 10;
        }
      res += Long.parseLong(sb.toString()) / f;
      return res * (neg ? -1 : 1);
    }

    public long[] nextlongArray(int n) throws IOException {
      long[] a = new long[n];
      for (int i = 0; i < n; i++)
        a[i] = nextLong();
      return a;
    }

    public Long[] nextLongArray(int n) throws IOException {
      Long[] a = new Long[n];
      for (int i = 0; i < n; i++)
        a[i] = nextLong();
      return a;
    }

    public int[] nextIntArray(int n) throws IOException {
      int[] a = new int[n];
      for (int i = 0; i < n; i++)
        a[i] = nextInt();
      return a;
    }

    public Integer[] nextIntegerArray(int n) throws IOException {
      Integer[] a = new Integer[n];
      for (int i = 0; i < n; i++)
        a[i] = nextInt();
      return a;
    }

    public boolean ready() throws IOException {
      return br.ready();
    }

  }

  public static void main(String[] args) throws IOException {
    new p1846C().solve();
  }
}