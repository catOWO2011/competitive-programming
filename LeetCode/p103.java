import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class p103 {
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

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> zigzagLevelOrder = new ArrayList<>();

    if (root != null) {
      LinkedList<TreeNode> treeQueue = new LinkedList<>();
      int currentTreeHeight = 0;

      treeQueue.add(root);

      while (!treeQueue.isEmpty()) {
        LinkedList<TreeNode> newTreeQueue = new LinkedList<>();

        while (!treeQueue.isEmpty()) {
          TreeNode treeNode = treeQueue.pollFirst();

          if (currentTreeHeight == zigzagLevelOrder.size()) {
            zigzagLevelOrder.add(new ArrayList<>());
          }

          zigzagLevelOrder.get(currentTreeHeight).add(treeNode.val);

          if (currentTreeHeight % 2 == 0) {
            if (treeNode.left != null) {
              newTreeQueue.addFirst(treeNode.left);
            }
            if (treeNode.right != null) {
              newTreeQueue.addFirst(treeNode.right);
            }
          } else {
            if (treeNode.right != null) {
              newTreeQueue.addFirst(treeNode.right);
            }
            if (treeNode.left != null) {
              newTreeQueue.addFirst(treeNode.left);
            }
          }
        }

        treeQueue = newTreeQueue;

        currentTreeHeight++;
      }

    }

    return zigzagLevelOrder;
  }

  public static void main(String[] args) {

  }
}