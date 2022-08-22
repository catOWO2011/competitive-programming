import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

public class p40 {
  public static void main(String[] args) {
    new p40().solve();
  }

  public void solve() {

    System.out.println(combinationSum2(new int[] { 10, 1, 2, 7, 6, 1, 5 }, 8));
    System.out.println(combinationSum2(new int[] { 2, 5, 2, 1, 2 }, 5));
    System.out.println(combinationSum2(
        new int[] {
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        },
        30));
    System.out.println(
        combinationSum2(new int[] {
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1 },
            26));
  }

  class Trie {
    HashMap<Integer, Trie> child;
    Integer root = null;

    public Trie() {
      root = null;
      child = new HashMap<>();
    }

    public Trie(int newRoot) {
      root = newRoot;
      child = new HashMap<>();
    }

    public boolean insert(List<Integer> keyList) {
      return insert(0, keyList);
    }

    private boolean insert(int level, List<Integer> keyList) {
      int key = keyList.get(level);
      boolean isLastKey = keyList.size() - 1 == level;

      if (child.containsKey(key)) {
        if (isLastKey) {
          return false;
        } else {
          Trie trie = child.get(key);
          return trie.insert(level + 1, keyList);
        }
      } else {
        Trie newTrie = new Trie(key);
        child.put(key, newTrie);
        if (isLastKey) {
          return true;
        } else {
          return newTrie.insert(level + 1, keyList);
        }
      }
    }

    public boolean hasKeyList(List<Integer> keyList) {
      return hasKeyList(0, keyList);
    }

    private boolean hasKeyList(int level, List<Integer> keyList) {
      int key = keyList.get(level);
      boolean isLastKey = keyList.size() - 1 == level;

      if (child.containsKey(key)) {
        if (isLastKey) {
          return true;
        } else {
          return (child.get(key)).hasKeyList(level + 1, keyList);
        }
      } else {
        return false;
      }
    }
  }

  class CandidateCombination extends LinkedList<Integer> {
    private int sum;

    public CandidateCombination() {
      sum = 0;
    }

    public void addCandidate(int candidate) {
      sum += candidate;
      super.add(candidate);
    }

    public CandidateCombination clone() {
      CandidateCombination clone = new CandidateCombination();

      for (Integer candidate : this) {
        clone.addCandidate(candidate);
      }

      return clone;
    }

    public void addCandidates(CandidateCombination otherCandidate) {
      for (Integer candidate : otherCandidate) {
        this.addCandidate(candidate);
      }
    }

    public int getSum() {
      return sum;
    }
  }

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> combinationsSumEqualsToTarget = new ArrayList<>();
    Arrays.sort(candidates);
    Integer sum = Arrays.stream(candidates).reduce(0, (a, b) -> a + b);

    // long startTime = System.nanoTime();
    // if (sum >= target) {
    // findCombinations(
    // 0,
    // target,
    // new Trie(),
    // new LinkedList<Integer>(),
    // candidates,
    // combinationsSumEqualsToTarget);
    // }
    // long endTime = System.nanoTime();
    // long duration = (endTime - startTime) / 1000000;
    // System.out.println("Duration " + duration);
    if (sum >= target) {
      findCombinations(
          0,
          target,
          new Trie(),
          new LinkedList<Integer>(),
          candidates,
          combinationsSumEqualsToTarget);
    }

    return combinationsSumEqualsToTarget;
  }

  private void findCombinations(int from,
      int target,
      Trie trie,
      LinkedList<Integer> candidateCombination,
      int[] candidates,
      List<List<Integer>> combinationsSumEqualsToTarget) {

    if (target == 0 && trie.insert(candidateCombination)) {
      combinationsSumEqualsToTarget.add(new ArrayList<>(candidateCombination));
    } else {
      if (target > 0) {
        int currentIndex = from;
        boolean searchCandidates = currentIndex < candidates.length;
        while (searchCandidates) {
          int currentCandidate = candidates[currentIndex];
          boolean addCandidate = target - currentCandidate >= 0;
          if (addCandidate) {
            candidateCombination.offerLast(currentCandidate);

            if (!trie.hasKeyList(candidateCombination)) {
              findCombinations(
                  currentIndex + 1,
                  target - currentCandidate,
                  trie,
                  candidateCombination,
                  candidates,
                  combinationsSumEqualsToTarget);
            }

            candidateCombination.pollLast();
          }
          currentIndex++;
          searchCandidates = (currentIndex < candidates.length) && addCandidate;
        }
      }
    }
  }

  public List<List<Integer>> combinationSum2s(int[] candidates, int target) {
    List<List<Integer>> combinationsSumTarget = new ArrayList<>();
    Arrays.sort(candidates);
    int numberOfCombinations = 1 << candidates.length;
    HashSet<String> set = new HashSet<>();

    for (int subset = 1; subset < numberOfCombinations; subset++) {
      int sumSubset = 0;
      List<Integer> candidateList = new ArrayList<>();
      boolean validCandidate = true;
      int bit = 0;

      while (validCandidate && bit < candidates.length) {
        // The bit is on
        if ((subset & (1 << bit)) > 0) {
          candidateList.add(candidates[bit]);
          sumSubset += candidates[bit];
        }

        validCandidate = sumSubset <= target;
        bit++;
      }

      if (validCandidate && sumSubset == target) {
        if (!set.contains(candidateList.toString())) {
          combinationsSumTarget.add(candidateList);
          set.add(candidateList.toString());
        }
      }
    }

    return combinationsSumTarget;
  }
}