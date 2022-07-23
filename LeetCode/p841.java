import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class p841 {
  public static void main(String[] args) {
    new p841().solve();
  }

  public void solve() {
    // System.out.println(canVisitAllRooms());
  }

  public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    int NUMBER_OF_ROOMS = rooms.size();

    List<List<Integer>> graph = new ArrayList<>();
    for (List<Integer> neighborList : rooms) {
      graph.add(new ArrayList<>());
      for (Integer neighbor : neighborList) {
        graph.get(graph.size() - 1).add(neighbor);
      }
    }

    Stack<Integer> nodes = new Stack<>();

    nodes.push(0);
    int numberOfVisitedRooms = 0;
    boolean[] visistedRooms = new boolean[NUMBER_OF_ROOMS];

    while (!nodes.isEmpty()) {
      int node = nodes.pop();
      if (!visistedRooms[node]) {
        visistedRooms[node] = true;
        numberOfVisitedRooms++;
        for (Integer neighbor : graph.get(node)) {
          if (!visistedRooms[neighbor]) {
            nodes.push(neighbor);
          }
        }
      }
    }

    return numberOfVisitedRooms == NUMBER_OF_ROOMS;
  }
}