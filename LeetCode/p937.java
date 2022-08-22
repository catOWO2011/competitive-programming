import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class p937 {

  class Log {
    protected String identifier;
    protected String content;

    public Log(String identifier, String content) {
      this.identifier = identifier;
      this.content = content;
    }

    @Override
    public String toString() {
      return identifier + " " + content;
    }
  }

  class LetterLog extends Log implements Comparable<Log> {
    public LetterLog(String identifier, String content) {
      super(identifier, content);
    }

    // @Override
    public int compareTo(Log other) {
      if (other instanceof DigitLog) {
        return -1;
      } else {
        LetterLog otherLetterLog = (LetterLog) (other);
        if (content.compareTo(otherLetterLog.content) == 0) {
          return identifier.compareTo(otherLetterLog.identifier);
        } else {
          return content.compareTo(otherLetterLog.content);
        }
      }
    }
  }

  class DigitLog extends Log implements Comparable<Log> {
    Integer relativeOrder;

    public DigitLog(String identifier, String content, Integer relativeOrder) {
      super(identifier, content);
      this.relativeOrder = relativeOrder;
    }

    // @Override
    public int compareTo(Log other) {
      if (other instanceof DigitLog) {
        DigitLog otherDigitLog = (DigitLog) (other);
        return relativeOrder.compareTo(otherDigitLog.relativeOrder);
      } else {
        return 1;
      }
    }
  }

  class LogFactory {

    public Log create(String stringLog, Integer relativeOrder) {
      String[] tokens = stringLog.split(" ", 2);
      String identifier = tokens[0];
      String content = tokens[1];
      boolean isLetterLogToken = content.split(" ", 2)[0].replaceAll("[a-zA-Z]", "").isEmpty();

      Log newLog = null;

      if (isLetterLogToken) {
        newLog = new LetterLog(identifier, content);
      } else {
        newLog = new DigitLog(identifier, content, relativeOrder);
      }

      return newLog;
    }
  }

  public String[] reorderLogFiles(String[] logs) {
    Log[] logFiles = new Log[logs.length];
    LogFactory logfactory = new LogFactory();

    for (int i = 0; i < logs.length; i++) {
      logFiles[i] = logfactory.create(logs[i], i);
    }

    Arrays.sort(logFiles);

    for (int i = 0; i < logFiles.length; i++) {
      logs[i] = logFiles[i].toString();
    }

    return logs;
  }

  public List<List<Integer>> minimumAbsDifference(int[] arr) {
    Arrays.sort(arr);
    int minimumDiff = Math.abs(arr[1] - arr[0]);
    List<List<Integer>> pairsWithMinimumDiff = new LinkedList<>();

    for (int i = 1; i < arr.length; i++) {
      List<Integer> pair = new LinkedList<>();
      int first = arr[i - 1];
      int second = arr[i];
      pair.add(first);
      pair.add(second);
      int diff = Math.abs(second - first);
      if (diff == minimumDiff) {
        pairsWithMinimumDiff.add(pair);
      } else if (diff < minimumDiff) {
        minimumDiff = diff;
        pairsWithMinimumDiff = new LinkedList<>();
        pairsWithMinimumDiff.add(pair);
      }
    }

    return pairsWithMinimumDiff;
  }

  public void solve() {
    System.out.println(Arrays.toString(reorderLogFiles(
        new String[] { "dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero" })));
  }

  public static void main(String[] args) {
    new p937().solve();
  }
}