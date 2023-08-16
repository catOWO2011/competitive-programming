import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p438 {
  class AnagramMap {
    int[] anagramCount;

    public AnagramMap(String p) {
      anagramCount = new int[26];

      for (int pIndex = 0; pIndex < p.length(); pIndex++) {
        char letter = p.charAt(pIndex);
        anagramCount[letter - 'a']++;
      }
    }

    public AnagramMap() {
      anagramCount = new int[26];
    }

    public void addLetter(char letter) {
      anagramCount[letter - 'a']++;
    }

    public void removeLetter(char letter) {
      anagramCount[letter - 'a']--;
    }

    public boolean compare(AnagramMap otherAnagramMap) {
      boolean similar = true;

      int index = 0;
      while (index < 26 && similar) {
        similar = anagramCount[index] == otherAnagramMap.anagramCount[index];
        index++;
      }

      return similar;
    }
  }

  public List<Integer> findAnagrams(String s, String p) {
    List<Integer> startIndexs = new ArrayList<>();

    if (s.length() >= p.length()) {
      AnagramMap anagramMap = new AnagramMap(p);
      AnagramMap anagramMapWindow = new AnagramMap();

      for (int sIndex = 0; sIndex < s.length(); sIndex++) {
        char letter = s.charAt(sIndex);
        anagramMapWindow.addLetter(letter);

        int startIndex = sIndex - p.length();
        if (sIndex >= p.length()) {
          anagramMapWindow.removeLetter(s.charAt(startIndex));
        }

        if (anagramMapWindow.compare(anagramMap)) {
          startIndexs.add(startIndex + 1);
        }
      }
    }

    return startIndexs;
  }

  public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
    ArrayList<int[]> nonOverlappingIntervals = new ArrayList<>();

    int[] currentInterval = new int[] { intervals[0][0], intervals[0][1] };
    for (int index = 1; index < intervals.length; index++) {
      int left = intervals[index][0];
      int right = intervals[index][1];

      if (left > currentInterval[1]) {
        nonOverlappingIntervals.add(currentInterval);
        currentInterval = new int[] { left, right };
      } else {
        if (right > currentInterval[1]) {
          currentInterval[1] = right;
        }
      }
    }
    nonOverlappingIntervals.add(currentInterval);

    int[][] nonOIArray = new int[nonOverlappingIntervals.size()][];
    for (int index = 0; index < nonOverlappingIntervals.size(); index++) {
      nonOIArray[index] = nonOverlappingIntervals.get(index);
    }

    return nonOIArray;
  }

  public void solve() {
    System.out.println(findAnagrams("cbaebabacd", "abc"));
    System.out.println(findAnagrams("abab", "ab"));
  }

  public static void main(String[] args) {
    new p438().solve();
  }
}