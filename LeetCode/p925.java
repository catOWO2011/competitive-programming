import java.util.function.BiFunction;

public class p925 {
  public boolean isLongPressedName(String name, String typed) {
    boolean is = true && name.length() <= typed.length();

    if (!is)
      return is;

    int nameIndex = 0;
    int typedIndex = 0;
    BiFunction<Integer, String, Boolean> validIndex = (index, string) -> index < string.length();

    while (validIndex.apply(nameIndex, name) && validIndex.apply(typedIndex, typed) && is) {
      if (name.charAt(nameIndex) == typed.charAt(typedIndex)) {
        nameIndex++;
        typedIndex++;
      } else if (nameIndex > 0 && name.charAt(nameIndex - 1) == typed.charAt(typedIndex)) {
        typedIndex++;
      } else {
        is = false;
      }
    }

    if (is) {
      is = nameIndex == name.length();
      while (is && validIndex.apply(typedIndex, typed) && name.charAt(nameIndex - 1) == typed.charAt(typedIndex)) {
        typedIndex++;
      }
      is = is && typedIndex == typed.length();
    }

    return is;
  }

  public void solve() {
    System.out.println(isLongPressedName("alex", "aaleex"));
    System.out.println(isLongPressedName("saeed", "ssaaedd"));
  }

  public static void main(String[] args) {
    new p925().solve();
  }
}