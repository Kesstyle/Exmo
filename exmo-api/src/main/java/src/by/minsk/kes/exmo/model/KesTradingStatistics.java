package src.by.minsk.kes.exmo.model;

import java.io.Serializable;

public class KesTradingStatistics implements Serializable {

  private KesTradingStatisticsInfo sell;
  private KesTradingStatisticsInfo buy;
  private Pair pair;

  public KesTradingStatisticsInfo getSell() {
    return sell;
  }

  public void setSell(KesTradingStatisticsInfo sell) {
    this.sell = sell;
  }

  public KesTradingStatisticsInfo getBuy() {
    return buy;
  }

  public void setBuy(KesTradingStatisticsInfo buy) {
    this.buy = buy;
  }

  public Pair getPair() {
    return pair;
  }

  public void setPair(Pair pair) {
    this.pair = pair;
  }

  @Override
  public String toString() {
    return String.format("\r\n=================%s====================\r\n", pair) +
        " SELL: " + sell +
        "\r\n BUY: " + buy;
  }
}
