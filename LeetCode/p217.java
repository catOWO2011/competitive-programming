package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class p217 {
  public static void main(String[]args) {
    new p217().solve();
  }

  public void solve() {
    // System.out.println(containsDuplicate(new int[]{1, 2, 3, 13, 5, 1}));
    // System.out.println(countPairs(7, new int[][]{{0,2},{0,5},{2,4},{1,6},{5,4}}));
    System.out.println(maximalRectangle(
      new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}}
    ));
  }

  public boolean containsDuplicate(int[] nums) {
    Arrays.sort(nums);

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == nums[i - 1]) {
        return true;
      }
    }

    return false;
  }

  public long countPairs(int n, int[][] edges) {
    long numberUnreachablePair = 0;
    List<List<Integer>> graph = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }

    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];

      graph.get(u).add(v);
      graph.get(v).add(u);
    }
    // System.out.println(graph + " graph");

    //Count nodes
    boolean[]visited = new boolean[n];
    long currentNodes = 0;
    for (int node = 0; node < n; node++) {
      if (!visited[node]) {
        Stack<Integer> stack = new Stack<>();
        stack.push(node);
        long connectedNodes = 0;
        while (!stack.empty()) {
          int currentNode = stack.pop();
          if (!visited[currentNode]) {
            visited[currentNode] = true;
            connectedNodes++;
            for (Integer neighbor : graph.get(currentNode)) {
              if (!visited[neighbor]) {
                stack.push(neighbor);
              }
            }
          }
        }
        // System.out.println(connectedNodes + "...");
        if (currentNodes > 0) {
          numberUnreachablePair += (currentNodes * connectedNodes);
        }
        currentNodes += connectedNodes;
      }
    }

    return numberUnreachablePair;
  }

  public int maximalRectangle(char[][] matrix) {
    int maximalRectangle = 0;

    int rows = matrix.length;
    int cols = matrix[0].length;
    int[][]sums = new int[rows][cols];

    for (int row = 0; row < sums.length; row++) {
      for (int col = 0; col < sums[0].length; col++) {
        sums[row][col] = Integer.MIN_VALUE;
        if (matrix[row][col] == '1') {
          sums[row][col] = 1;
        }

        if (row > 0 ) {
          sums[row][col] += sums[row - 1][col];
        }

        if (col > 0 ) {
          sums[row][col] += sums[row][col - 1];
        }

        if (row > 0 && col > 0 ) {
          sums[row][col] -= sums[row - 1][col - 1];
        }
      }
    }

    for (int[] sum : sums) {
      System.out.println(Arrays.toString(sum));
    }

    maximalRectangle = Integer.MIN_VALUE;

    for (int row = 0; row < sums.length; row++) {
      for (int col = 0; col < sums[0].length; col++) {
        for (int width = row; width < sums.length; width++) {
          for (int height = col; height < sums.length; height++) {
            int subRectangle = sums[width][height];
            if (row > 0) {
              subRectangle -= sums[row - 1][height];
            }
    
            if (col > 0) {
              subRectangle -= sums[width][col - 1];
            }
    
            if (row > 0 && col > 0) {
              subRectangle += sums[row - 1][col - 1];
            }

            maximalRectangle = Math.max(subRectangle, maximalRectangle);
          }
        }
      }
    }

    return maximalRectangle;
  }
}