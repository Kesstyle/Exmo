package by.minsk.kes.exmo.model.domain;

import java.io.Serializable;

public class Pair implements Serializable {

  public static final String PAIR_SEPARATOR = "_";

  private String firstCurrency;
  private String secondCurrency;

  public String getFirstCurrency() {
    return firstCurrency;
  }

  public void setFirstCurrency(String firstCurrency) {
    this.firstCurrency = firstCurrency;
  }

  public String getSecondCurrency() {
    return secondCurrency;
  }

  public void setSecondCurrency(String secondCurrency) {
    this.secondCurrency = secondCurrency;
  }

  public String toString() {
    return firstCurrency + PAIR_SEPARATOR + secondCurrency;
  }
}
