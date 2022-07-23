public class p2125 {
  public static void main(String[] args) {
    new p2125().solve();
  }

  public void solve() {

  }

  public int numberOfBeams(String[] bank) {

    int laserBeams = 0;
    int deviceNumberPrevRow = -1;

    for (int row = 0; row < bank.length; row++) {
      int numberOfDevices = 0;
      // Count devices by row
      for (int col = 0; col < bank[row].length(); col++) {
        if (bank[row].charAt(col) == '1') {
          numberOfDevices++;
        }
      }

      if (numberOfDevices > 0) {
        if (deviceNumberPrevRow != -1) {
          laserBeams += deviceNumberPrevRow * numberOfDevices;
        }
        deviceNumberPrevRow = numberOfDevices;
      }
    }

    return laserBeams;
  }

}