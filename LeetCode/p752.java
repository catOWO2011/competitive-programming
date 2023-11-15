import java.util.HashSet;
import java.util.LinkedList;

public class p752 {
  public int openLock(String[] deadends, String target) {
    int minimumTotalTurns = -1;

    // Add the not allowed
    HashSet<String> deadendSet = new HashSet<>();
    for (String string : deadends) {
      deadendSet.add(string);
    }

    StringBuilder lockStatus = new StringBuilder("0000");
    if (!deadendSet.contains(lockStatus.toString())) {
      LinkedList<StringBuilder> queue = new LinkedList<>();
      queue.add(lockStatus);
      LinkedList<Integer> queueTurns = new LinkedList<>();
      queueTurns.add(0);
      deadendSet.add(lockStatus.toString());

      while (!queue.isEmpty() && minimumTotalTurns == -1) {
        lockStatus = queue.pollFirst();
        int totalTurns = queueTurns.pollFirst();

        if (lockStatus.toString().equals(target)) {
          minimumTotalTurns = totalTurns;
        } else {
          for (int i = 0; i < lockStatus.length(); i++) {
            int digit = lockStatus.charAt(i) - '0';
            for (int increment = -1; increment < 2; increment += 2) {
              StringBuilder newLockStatus = new StringBuilder(lockStatus);
              newLockStatus.setCharAt(i, (char) ((digit + increment + 10) % 10 + '0'));
              // System.out.println(newLockStatus);
              if (!deadendSet.contains(newLockStatus.toString())) {
                queue.add(newLockStatus);
                queueTurns.add(totalTurns + 1);
                deadendSet.add(newLockStatus.toString());
              }
            }
          }
        }
      }
    }

    return minimumTotalTurns;
  }

  public void solve() {
    System.out.println(openLock(new String[] { "0201", "0101", "0102", "1212",
        "2002" }, "0202"));
    // System.out.println(openLock(new String[] { "8888" }, "0009"));
    // System.out
    // .println(openLock(new String[] { "8887", "8889", "8878", "8898", "8788",
    // "8988", "7888", "9888" }, "8888"));
  }

  public static void main(String[] args) {
    new p752().solve();
  }
}