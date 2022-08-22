import java.util.LinkedList;

class p1504 {
  public static void main(String[] args) {
    new p1504().solve();
  }

  public void solve() {
    System.out.println(numSubmat(new int[][] {
        { 0, 1, 1, 1 },
        { 1, 1, 1, 1 },
        { 1, 1, 1, 1 }
    }) + " result must be 48");
    System.out.println(numSubmat(new int[][] {
        { 1, 0, 1 },
        { 1, 1, 0 },
        { 1, 1, 0 }
    }) + " result must be 13");
    System.out.println(numSubmat(new int[][] {
        { 0, 1, 1, 0 },
        { 0, 1, 1, 1 },
        { 1, 1, 1, 0 }
    }) + " result must be 24");
  }

  public int numSubmat(int[][] mat) {
    // Declare totalSubmatrices variable
    int totalSubmatrices = 0;
    /**
     * Count the submatrices in a vertical way like 1x2 1x1 1x3 and so on
     * ..........._
     * ._........|_|
     * |_|..._...|_|
     * |_|..|_|..|_|
     */
    // We are goint to save this for each row(level) as a current height
    // Declare the heights collection
    int numberOfColumns = mat[0].length;
    int[] heights = new int[numberOfColumns];

    // Loop for each row
    for (int[] row : mat) {
      // Loop for each col
      for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
        // Check if the row on the colIndex contains a square and increase
        // set to zero otherwise
        heights[colIndex] = row[colIndex] == 1 ? heights[colIndex] + 1 : 0;
      }
      // Get the submatrices for the current row and add to the total submatrices
      totalSubmatrices += getSubmatrices(heights);
    }

    return totalSubmatrices;
  }

  public int getSubmatrices(int[] heights) {
    // Declare the total sum of submatrices
    int totalSubmatrices = 0;
    // Declare an array to save the accumulated submatrices count for each height
    int[] submatricesCount = new int[heights.length];
    // Using monotonic stack (monoincreasing stack)
    LinkedList<Integer> monoIncresingStack = new LinkedList<>();
    // Loop heights
    for (int currentIndex = 0; currentIndex < heights.length; currentIndex++) {
      // Remove all the elements that are greater or equals than the current height
      // (heights[currentIndex])
      while (!monoIncresingStack.isEmpty() && (heights[monoIncresingStack.getLast()] >= heights[currentIndex])) {
        monoIncresingStack.pollLast();
      }

      // Now count the submatrices that has in a vertical way and horizontal way
      // Check if the mono increasing stack is empty
      if (monoIncresingStack.isEmpty()) {
        submatricesCount[currentIndex] = heights[currentIndex] * (currentIndex + 1);
      } else {
        // Get the previous index height
        int previousIndexHeight = monoIncresingStack.getLast();
        // Add the previous count to the current
        submatricesCount[currentIndex] = submatricesCount[previousIndexHeight];
        // Add the current height by the width(currentIndex - previousIndexHeight)
        submatricesCount[currentIndex] += (heights[currentIndex] * (currentIndex - previousIndexHeight));
      }

      // Increase total submatrices
      totalSubmatrices += submatricesCount[currentIndex];

      // Add the currentIndex to the mono increasing stack
      monoIncresingStack.addLast(currentIndex);
    }

    return totalSubmatrices;
  }

  /**
   * We're using an equation remember combinatorial equation C(x, y) means choose
   * y on
   * (( x! ) / ((x - y)! * y!)) ways
   * 
   * We need to apply that equation on the following way, using the following
   * example
   * ._ _ _ _
   * |_|_|_|_| This is a rectangle of 2 x 4
   * |_|_|_|_|
   * 
   * We have 3 horizontal and 4 vertical lines, we can combine lines we need
   * two horizontal lines
   * two vertical lines
   *
   * The lines can be name
   * _ _ _ _ a | | | | |
   * _ _ _ _ b | | | | |
   * _ _ _ _ c u v x y z
   * 
   * ._ _ _ _a.................._ _ _ _a.................._ _ _ _a
   * |_|_ _ _b..1x1 rectangle..|...|......2x2 rectangle..|_ _ _|_b 1x3 rectangle
   * | |.......................|_ _|_ _c.................|.....|
   * u v.......................u...x.....................u.....x
   * ._ _ _ _
   * |_|_ _ _.................|.......|
   * |_|_ _ _..1x1 rectangle..|_ _ _ _| 2x4 rectangle
   * 
   * |_ _ _|_ b
   * |_ _ _|_ c..1x3 rectangle
   * u.....y
   * 
   * For the three lines we need to combine two
   * {a, b} {a, c} {b, c} ...
   * For the five lines we need to combine two also
   * {u, v} {u, x} {u, y} {u, z} ...
   * 
   * So for all the combinations we need to multiply
   * C(height + 1, 2) * C(base + 1, 2)
   * remember C means combination
   * (((height + 1)! / (height + 1 - 2)! * 2!) * ((base + 1)! / (base + 1 - 2)! *
   * 2!)
   * this can be resume as ( ( ( heigh + 1 ) * height ) / 2 ) * ( ( ( base + 1 ) *
   * base ) / 2 )
   * 
   * @param base
   * @param height
   * @return
   */
  // public int countRectangles(int base, int height) {
  // return (((base + 1) * base) / 2) * (((height + 1) * height) / 2);
  // }
}