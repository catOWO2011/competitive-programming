import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  public long totalCost(int[] costs, int k, int candidates) {
    long totalCost = 0;

    class Candidate implements Comparable<Candidate> {
      int cost;
      int index;

      public Candidate(int index, int cost) {
        this.cost = cost;
        this.index = index;
      }

      public int compareTo(Candidate other) {
        return cost != other.cost ? cost - other.cost : index - other.index;
      }

      @Override
      public String toString() {
        return "Candidate{ index: " + index + ", cost: " + cost + "}";
      }
    }

    if (costs.length >= candidates * 2) {
      PriorityQueue<Candidate> firstCandidatesQueue = new PriorityQueue<>();
      PriorityQueue<Candidate> lastCandidatesQueue = new PriorityQueue<>();

      int leftIndex = 0;
      int rightIndex = costs.length - 1;

      while (leftIndex < rightIndex && firstCandidatesQueue.size() < candidates) {
        firstCandidatesQueue.offer(new Candidate(leftIndex, costs[leftIndex]));
        lastCandidatesQueue.offer(new Candidate(rightIndex, costs[rightIndex]));
        leftIndex++;
        rightIndex--;
      }

      if (firstCandidatesQueue.size() < candidates) {
        firstCandidatesQueue.offer(new Candidate(costs[leftIndex], leftIndex));
        leftIndex++;
      }
      // System.out.println(leftIndex);

      while (k > 0) {
        if (!firstCandidatesQueue.isEmpty() && !lastCandidatesQueue.isEmpty()) {

          Candidate firsCandidate = firstCandidatesQueue.peek();
          Candidate lastCandidate = lastCandidatesQueue.peek();
          // System.out.println(firstCandidatesQueue);
          // System.out.println(lastCandidatesQueue);
          boolean firstSelected = false;

          if (firsCandidate.compareTo(lastCandidate) < 0) {
            // System.out.println("Selected first " + firsCandidate);
            firstCandidatesQueue.poll();
            totalCost += firsCandidate.cost;
            firstSelected = true;
          } else {
            // System.out.println("Selected last " + lastCandidate);
            lastCandidatesQueue.poll();
            totalCost += lastCandidate.cost;
          }
          // System.out.println("################################## " + rightIndex);

          if (firstSelected) {
            if (leftIndex <= rightIndex) {
              firstCandidatesQueue.offer(new Candidate(leftIndex, costs[leftIndex]));
              leftIndex++;
            }
          } else {
            if (rightIndex >= leftIndex) {
              lastCandidatesQueue.offer(new Candidate(rightIndex, costs[rightIndex]));
              rightIndex--;
            }
          }
        } else {
          Candidate best = firstCandidatesQueue.isEmpty() ? lastCandidatesQueue.poll() : firstCandidatesQueue.poll();
          totalCost += best.cost;
        }

        k--;
      }
    } else {
      PriorityQueue<Candidate> bestCandidatesQueue = new PriorityQueue<>();
      for (int index = 0; index < costs.length; index++) {
        int cost = costs[index];
        bestCandidatesQueue.offer(new Candidate(index, cost));
      }

      while (k > 0) {
        Candidate bestCandidate = bestCandidatesQueue.poll();
        totalCost += bestCandidate.cost;
        k--;
      }
    }

    return totalCost;
  }

  public int[] findErrorNums(int[] nums) {
    int[] pair = null;

    // Sort the nums array using sorting in place algorithm
    int index = 0;
    while (index < nums.length) {
      int correctPosition = nums[index] - 1;
      if (nums[index] != nums[correctPosition]) {
        swap(nums, index, correctPosition);
      } else {
        index++;
      }
    }

    // Sorted now we check every number is in its correct position
    index = 0;
    while (index < nums.length && pair == null) {
      if (nums[index] != index + 1) {
        pair = new int[] { nums[index], index + 1 };
      }
      index++;
    }

    return pair;
  }

  public void swap(int[] nums, int posA, int posB) {
    int tmp = nums[posA];
    nums[posA] = nums[posB];
    nums[posB] = tmp;
  }

  public int largestPerimeter(int[] nums) {
    int largestPerimeter = 0;

    Arrays.sort(nums);
    int index = nums.length - 3;
    while (index > -1 && largestPerimeter == 0) {
      if (nums[index] + nums[index + 1] > nums[index + 2]) {
        largestPerimeter = nums[index] + nums[index + 1] + nums[index + 2];
      }
      index--;
    }

    return largestPerimeter;
  }

  public int[] productExceptSelf(int[] nums) {
    int[] answer = new int[nums.length];

    answer[0] = 1;
    for (int left = 1; left < nums.length; left++) {
      answer[left] = answer[left - 1] * nums[left - 1];
    }

    int rightTotalProduct = 1;
    for (int right = nums.length - 2; right > -1; right--) {
      rightTotalProduct *= nums[right + 1];
      answer[right] = rightTotalProduct * answer[right];
    }

    return answer;
  }

  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    List<List<int[]>> graph = new ArrayList<>();

    // Create a adjacent list for each node
    for (int node = 0; node < n; node++) {
      graph.add(new ArrayList<>());
    }

    // Build the graph
    for (int[] flight : flights) {
      int from = flight[0];
      int to = flight[1];
      int price = flight[2];

      graph.get(from).add(new int[] { to, price });
    }

    // Set the costs
    int[] totalCost = new int[n];
    Arrays.fill(totalCost, Integer.MAX_VALUE);

    totalCost[src] = 0;

    ArrayDeque<int[]> tripQueue = new ArrayDeque<>();
    tripQueue.offer(new int[] { src, 0, 0 });// Set the {source, totalCost, stopsNumber}

    while (!tripQueue.isEmpty()) {
      int[] trip = tripQueue.poll();
      // System.out.println(Arrays.toString(trip));
      int from = trip[0];
      int currentCost = trip[1];
      int stops = trip[2];

      if (stops <= k) {
        for (int[] adjCity : graph.get(from)) {
          int to = adjCity[0];
          int price = adjCity[1];
          if (currentCost + price < totalCost[to]) {
            totalCost[to] = currentCost + price;
            tripQueue.offer(new int[] { to, totalCost[to], stops + 1 });
          }
        }
      }
    }

    return totalCost[dst] == Integer.MAX_VALUE ? -1 : totalCost[dst];
  }

  public void solve() {
    System.out.println(findCheapestPrice(4,
        new int[][] { { 0, 1, 100 }, { 1, 2, 100 }, { 2, 0, 100 }, { 1, 3, 600 }, {
            2, 3, 200 } },
        0, 3, 1));
    // System.out.println(Arrays.toString(productExceptSelf(new int[] { 1, 2, 3, 4
    // })));
    // System.out.println(Arrays.toString(productExceptSelf(new int[] { -1, 1, 0,
    // -3, 3 })));
    // System.out.println(largestPerimeter(new int[] { 2, 1, 2 }));
    // System.out.println(Arrays.toString(findErrorNums(new int[] { 1, 3, 2, 2 })));
    // System.out.println(totalCost(new int[] { 17, 12, 10, 2, 7, 2, 11, 20, 8 }, 3,
    // 4));
    // System.out.println(totalCost(new int[] { 17, 12, 1, 2, 7 }, 3, 3));
  }

  public static void main(String[] args) {
    new Solution().solve();
  }
}