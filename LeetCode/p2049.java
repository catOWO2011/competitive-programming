import java.util.Arrays;

public class p2049 {

  class BinaryTree {
    int value;
    BinaryTree left;
    BinaryTree right;
    int size;

    BinaryTree(int value) {
      this.value = value;
      left = null;
      right = null;
      size = 1;
    }

    @Override
    public String toString() {
      return "tree{\n" +
          "value: " + value +
          "\nsize: " + size +
          "\n}";
    }
  }

  public int countHighestScoreNodes(int[] parents) {
    int nodesWithHighestScore = 0;

    BinaryTree[] trees = buildBinaryTree(parents);

    buildSize(trees[0]);

    long highestScore = 0;

    for (BinaryTree binaryTree : trees) {
      long currentScore = 0;
      if (parents[binaryTree.value] == -1) {
        currentScore = (binaryTree.right != null ? binaryTree.right.size : 1);
        currentScore *= (binaryTree.left != null ? binaryTree.left.size : 1);
      } else {
        currentScore = trees[0].size - binaryTree.size;
        currentScore *= (binaryTree.right != null ? binaryTree.right.size : 1);
        currentScore *= (binaryTree.left != null ? binaryTree.left.size : 1);
      }

      if (highestScore < currentScore) {
        highestScore = currentScore;
        nodesWithHighestScore = 1;
      } else if (highestScore == currentScore) {
        nodesWithHighestScore++;
      }
    }

    return nodesWithHighestScore;
  }

  private BinaryTree[] buildBinaryTree(int... parents) {
    BinaryTree[] trees = new BinaryTree[parents.length];

    for (int node = 0; node < trees.length; node++) {
      trees[node] = new BinaryTree(node);
    }

    for (int node = 1; node < trees.length; node++) {
      BinaryTree parentTree = trees[parents[node]];
      if (parentTree.right == null) {
        parentTree.right = trees[node];
      } else {
        parentTree.left = trees[node];
      }
    }

    return trees;
  }

  public void buildSize(BinaryTree tree) {
    int newSize = 0;

    if (tree.right != null) {
      buildSize(tree.right);
      newSize += tree.right.size;
    }

    if (tree.left != null) {
      buildSize(tree.left);
      newSize += tree.left.size;
    }

    tree.size += newSize;
  }

  public void solve() {
    System.out.println(countHighestScoreNodes(new int[] { -1, 2, 0, 2, 0 }));
  }

  public static void main(String[] args) {
    new p2049().solve();
  }
}