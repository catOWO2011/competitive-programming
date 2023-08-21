import java.util.Scanner;

public class p11687 {
  public void solve() {
    Scanner in = new Scanner(System.in);

    String google = "";
    do {
      google = in.next();
      if (!google.equals("END")) {
        int x1 = 0;
        int x2 = google.length();
        x1 = (x2 + "").length() == google.length() ? Integer.parseInt(google) : x1;
        int i = 1;
        while (x1 != x2) {
          x1 = x2;
          x2 = ("" + x1).length();
          i++;
        }

        System.out.println(i);
      }
    } while (!google.equals("END"));

    in.close();

  }

  public static void main(String[] args) {
    new p11687().solve();
  }
}