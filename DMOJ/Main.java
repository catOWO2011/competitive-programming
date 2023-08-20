package DMOJ;

import java.io.*;
import java.util.*;

public class Main {
  public void smallestSumIncoveniences(int[] a) {
    long sum = 0;

    List<Integer> odds = new ArrayList<>();
    List<Integer> evens = new ArrayList<>();

    for (int i = 0; i < a.length; i++) {
      if ((a[i] & 1) == 1) {
        odds.add(i);
      } else {
        evens.add(i);
      }
    }

    List<Integer> indexes = new ArrayList<>();
    int left = 1;
    if (odds.size() > 0) {
      int index = odds.get(0);
      sum += 1;
      if (a[index] > 1) {
        sum += (a[index] / 2) - 1;
      }
      indexes.add(index);
      left = 0;
    }

    for (int i = 0; i < evens.size(); i++) {
      int index = evens.get(i);
      if (left == 0) {
        sum += (a[index] / 2) - 1;
      } else {
        sum += (a[index] / 2);
      }
      indexes.add(index);
    }

    for (int i = 1; i < odds.size(); i++) {
      int index = odds.get(i);
      if (a[index] > 1) {
        sum += (a[index] / 2);
      }
      indexes.add(index);
    }

    System.out.println(sum);
    for (int i = 0; i < indexes.size(); i++) {
      if (i > 0) {
        System.out.print(" ");
      }
      System.out.print(indexes.get(i) + 1);
    }
    System.out.println();
  }

  public void solve() {
    Scanner in = new Scanner(System.in);

    int N = in.nextInt();
    int[] a = new int[N];

    for (int i = 0; i < N; i++) {
      a[i] = in.nextInt();
    }

    smallestSumIncoveniences(a);
    // 2147483647
    in.close();
  }

  public static void main(String[] args) {
    new Main().solve();
  }
}