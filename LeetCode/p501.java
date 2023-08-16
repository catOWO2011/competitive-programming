import java.util.LinkedList;

class p501 {
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

  TreeNode prev;
  LinkedList<Integer> modes;
  Integer count;
  Integer mode;

  public int[] findMode(TreeNode root) {
    prev = null;
    modes = new LinkedList<>();
    count = 0;
    mode = 0;

    findModeInorder(root);

    // return modes.stream().mapToInt(i -> i).toArray();
    int[] a = new int[modes.size()];

    for (int i = 0; i < modes.size(); i++) {
      a[i] = modes.get(i);
    }

    return a;
  }

  private void findModeInorder(TreeNode tree) {
    if (tree != null) {
      findModeInorder(tree.left);

      count = prev != null && prev.val == tree.val ? count + 1 : 1;

      if (mode < count) {
        mode = count;
        modes.clear();
        modes.add(tree.val);
      } else if (mode == count) {
        modes.add(tree.val);
      }

      prev = tree;

      findModeInorder(tree.right);
    }
  }

  public void solve() {

  }

  public static void main(String[] args) {
    new p501().solve();
  }
}