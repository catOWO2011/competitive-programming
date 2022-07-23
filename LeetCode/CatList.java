import java.util.ArrayList;

public class CatList<E> extends ArrayList<E> {
  public CatList(E[] elements) {
    super(elements.length);

    for (E e : elements) {
      super.add(e);
    }
  }

  public int searchElementWithBinarySearch(int from, int to) {
    return -1;
  }

  public static int findByBinarySearch(int key, int from, int to, int[] arr) {
    int low = from;
    int high = to;
    int mid;

    while (low <= high) {
      mid = (low + high) >>> 1;
      if (arr[mid] == key) {
        return mid;
      } else if (arr[mid] > key) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }

    return -1;
  }
}