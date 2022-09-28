/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class p993 {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public boolean isCousins(TreeNode root, int x, int y) {
    if (root != null) {
      Integer[] depths = new Integer[101];
      Integer[] parents = new Integer[101];

      depths[root.val] = 0;
      calculateDepths(root, depths, parents);

      if (depths[x] != null) {
        return depths[x] == depths[y] && parents[x] != parents[y];
      }
      return Boolean.FALSE;
    }

    return Boolean.FALSE;
  }

  private void calculateDepths(TreeNode root, Integer[] depths, Integer... parents) {
    if (root != null) {
      TreeNode left = root.left;
      TreeNode right = root.right;
      if (left != null) {
        depths[left.val] = 1 + depths[root.val];
        parents[left.val] = root.val;
        calculateDepths(left, depths, parents);
      }
      if (right != null) {
        depths[right.val] = 1 + depths[root.val];
        parents[right.val] = root.val;
        calculateDepths(right, depths, parents);
      }
    }
  }
}