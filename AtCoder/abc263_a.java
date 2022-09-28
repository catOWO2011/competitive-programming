package AtCoder;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class abc263_a {

  final static int CARD_SET_SIZE = 5;

  class CardSet extends HashMap<Integer, Integer> {
    public boolean isFullHouse() {
      Collection<Integer> values = values();
      return values.size() == 2 && (values.contains(2) && values.contains(3));
    }

    public void addCard(Integer card) {
      if (!containsKey(card)) {
        put(card, 0);
      }

      Integer frequency = get(card);
      put(card, frequency + 1);
    }
  }

  public void solve() {
    Scanner in = new Scanner(new InputStreamReader(System.in));

    CardSet cardSet = new CardSet();

    for (int i = 0; i < CARD_SET_SIZE; i++) {
      cardSet.addCard(in.nextInt());
    }

    System.out.println(cardSet.isFullHouse() ? "Yes" : "No");
  }

  public static void main(String[] args) {
    new abc263_a().solve();
  }
}