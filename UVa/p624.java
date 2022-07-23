package UVa;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class p624 {
  public static void main(String[]args) {
    new p624().solve();
  }

  public void solve() {
    Scanner in = new Scanner(new InputStreamReader(System.in));

    while (in.hasNextLine()) {
      StringTokenizer st = new StringTokenizer(in.nextLine());
      int minutesDuration = Integer.parseInt(st.nextToken());
      int numberOfTracks = Integer.parseInt(st.nextToken());

      int[]durationTracks = new int[numberOfTracks];

      for (int i = 0; i < durationTracks.length; i++) {
        durationTracks[i] = Integer.parseInt(st.nextToken());
      }

      System.out.println(buildBestTape(0, durationTracks, minutesDuration, new Tape()));
    }
    // System.out.println(buildBestTape(0, new int[]{1, 3, 4}, 5, new Tape()));
    // System.out.println(buildBestTape(0, new int[]{9, 8, 4, 2}, 10, new Tape()));
    // System.out.println(buildBestTape(0, new int[]{10, 5, 7, 4}, 20, new Tape()));
    // System.out.println(buildBestTape(0, new int[]{10, 23, 1, 2, 3, 4, 5, 7}, 90, new Tape()));
    // System.out.println(buildBestTape(0, new int[]{8, 4, 10, 44, 43, 12, 9, 8, 2}, 45, new Tape())); 
  }

  private Tape buildBestTape(int trackIndex, int[] durationTracks, int minutesDuration, Tape currentTape) {
    Tape tape = new Tape();

    if (trackIndex == durationTracks.length) {
      tape = currentTape.clone();
    } else {
      tape = currentTape.clone();
      for (int newIndexTrack = trackIndex; newIndexTrack < durationTracks.length; newIndexTrack++) {
        int durationTrack = durationTracks[newIndexTrack];
        Tape nextTap = new Tape();

        if (currentTape.getDuration() + durationTrack <= minutesDuration) {
          currentTape.addTrack(durationTrack);
          nextTap = buildBestTape(newIndexTrack + 1, durationTracks, minutesDuration, currentTape);
          currentTape.removeLastTrack();
        } else {
          nextTap = buildBestTape(newIndexTrack + 1, durationTracks, minutesDuration, currentTape);
        }

        if (nextTap.greaterThan(tape)) {
          tape = nextTap.clone();
        }
      }
    }

    return tape;
  }

  class Tape extends ArrayList<Integer> {
    private int duration;

    public Tape() {
      super();
      duration = 0;
    }

    public void addTrack(int trackDuration) {
      super.add(trackDuration);
      this.duration += trackDuration;
    }

    public Tape clone() {
      Tape tape = new Tape();

      for (Integer track : this) {
        tape.addTrack(track);
      }

      return tape;
    }

    public int getDuration() {
      return duration;
    }

    public boolean greaterThan(Tape other) {
      return duration > other.duration;
    }

    public void removeLastTrack() {
      int lastTrack = this.remove(this.size() - 1);
      this.duration -= lastTrack;
    }

    @Override
    public String toString() {
      String s = "";

      for (Integer track : this) {
        s += track + " ";
      }

      s += "sum:" + duration;

      return s;
    }
  }
}