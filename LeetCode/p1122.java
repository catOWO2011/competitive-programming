import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

// jose.lucan@turing.com
public class p1122 {
  public static void main(String[] args) {
    new p1122().solve();
  }

  public void solve() {
    System.out.println(Arrays
        .toString(relativeSortArray(new int[] { 2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19 }, new int[] { 2, 1, 4, 3, 9, 6 })));
  }

  public int[] relativeSortArray(int[] arr1, int[] arr2) {
    HashMap<Integer, Integer> frequencyMap = new HashMap<>();

    for (int element : arr2) {
      frequencyMap.put(element, 0);
    }

    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
    for (int element : arr1) {
      if (frequencyMap.containsKey(element)) {
        frequencyMap.put(element, frequencyMap.get(element) + 1);
      } else {
        priorityQueue.add(element);
      }
    }

    int index = 0;
    for (Integer element : arr2) {
      int frequency = frequencyMap.get(element);
      while (frequency > 0) {
        arr1[index] = element;
        frequency--;
        index++;
      }
    }

    while (!priorityQueue.isEmpty()) {
      arr1[index] = priorityQueue.poll();
      index++;
    }

    return arr1;
  }
}