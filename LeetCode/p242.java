import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class p242 {
  public String currencyWithChosenLocalisation(double value, Locale locale) {
    NumberFormat numberFormat = NumberFormat.getInstance(locale);
    return numberFormat.format(value);
  }

  public void solve() {

    DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    try {
      Date date = simpleDateFormat.parse("2015-08-05");
      DateFormat formatter = new SimpleDateFormat("EEEE", Locale.UK);
      System.out.println(formatter.format(date).toUpperCase());
      System.out.println(String.format("%02d", 42));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println(currencyWithChosenLocalisation(32132.44, new Locale("en", "US")));
  }

  public static void main(String[] args) {
    new p242().solve();
  }
}