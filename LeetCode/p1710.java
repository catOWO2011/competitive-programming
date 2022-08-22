import java.util.Arrays;

public class p1710 {
  public static void main(String[] args) {
    new p1710().solve();
  }

  public void solve() {
    System.out.println(maximumUnits(new int[][] { { 1, 3 }, { 2, 2 }, { 3, 1 } }, 4));
    System.out.println(maximumUnits(new int[][] { { 5, 10 }, { 2, 5 }, { 4, 7 }, { 3, 9 } }, 10));
  }

  /**
   * Return the maximum total number of units that can be put on the track
   *
   * The idea is sort the elements taking as sort criterian
   *
   * @param boxTypes  the box with the following information for each { boxAmount,
   *                  unitsForEachBox }
   * @param truckSize the maximum numbers of boxes that can put on the truck
   * @return
   */
  public int maximumUnits(int[][] boxTypes, int truckSize) {
    // Sort the boxes by the units in descending order so the box that has many
    // units go first and so on
    Arrays.sort(boxTypes, (boxTypeA, boxTypeB) -> {
      int unitsBoxTypeA = boxTypeA[1];
      int unitsBoxTypeB = boxTypeB[1];
      return unitsBoxTypeB - unitsBoxTypeA;
    });

    int boxIndex = 0;
    int totalMaximumUnits = 0;

    while (boxIndex < boxTypes.length && truckSize > 0) {
      int numberOfBoxes = boxTypes[boxIndex][0];
      int numberOfUnitsPerBox = boxTypes[boxIndex][1];
      int boxesToTake = Math.min(truckSize, numberOfBoxes);

      totalMaximumUnits += (boxesToTake * numberOfUnitsPerBox);
      truckSize -= boxesToTake;
      boxIndex++;
    }

    return totalMaximumUnits;
  }
}