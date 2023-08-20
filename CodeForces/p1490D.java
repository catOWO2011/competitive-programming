package CodeForces;

import java.util.Scanner;

class p1490D {
  private void setEdgesByVertex(int[] a, int from, int to, int edges, int[] edgesByVertex) {
    if (from < to) {
      int maxIndex = from;
      for (int i = from + 1; i <= to; i++) {
        if (a[i] > a[maxIndex]) {
          maxIndex = i;
        }
      }

      edgesByVertex[maxIndex] = edges;
      if (maxIndex > from) {
        setEdgesByVertex(a, from, maxIndex - 1, edges + 1, edgesByVertex);
      }
      if (maxIndex < to) {
        setEdgesByVertex(a, maxIndex + 1, to, edges + 1, edgesByVertex);
      }
    } else {
      edgesByVertex[from] = edges;
    }
  }

  private int[] findEdgesByVertex(int[] a, int n) {
    int[] edgesByVertex = new int[n];

    setEdgesByVertex(a, 0, n - 1, 0, edgesByVertex);

    return edgesByVertex;
  }

  public void solve() {
    Scanner in = new Scanner(System.in);

    int t = in.nextInt();
    while (t-- > 0) {
      int n = in.nextInt();
      int[] a = new int[n];

      for (int i = 0; i < a.length; i++) {
        a[i] = in.nextInt();
      }

      int[] d = findEdgesByVertex(a, n);
      for (int i = 0; i < d.length; i++) {
        if (i > 0) {
          System.out.print(" ");
        }
        System.out.print(d[i]);
      }
      System.out.println();
    }

    in.close();
  }

  public static void main(String[] args) {
    new p1490D().solve();
  }
}