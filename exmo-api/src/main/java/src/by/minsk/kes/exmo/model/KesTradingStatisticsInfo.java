package src.by.minsk.kes.exmo.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class KesTradingStatisticsInfo implements Serializable {

  private KesOrderType ordersType;
  private int newTradesCount = 0;
  private BigDecimal tradeAmount = BigDecimal.ZERO;
  private BigDecimal tradeQuantity = BigDecimal.ZERO;

  public int getNewTradesCount() {
    return newTradesCount;
  }

  public void setNewTradesCount(int newTradesCount) {
    this.newTradesCount = newTradesCount;
  }

  public BigDecimal getTradeAmount() {
    return tradeAmount;
  }

  public void setTradeAmount(BigDecimal tradeAmount) {
    this.tradeAmount = tradeAmount;
  }

  public BigDecimal getTradeQuantity() {
    return tradeQuantity;
  }

  public void setTradeQuantity(BigDecimal tradeQuantity) {
    this.tradeQuantity = tradeQuantity;
  }

  public KesOrderType getOrdersType() {
    return ordersType;
  }

  public void setOrdersType(KesOrderType ordersType) {
    this.ordersType = ordersType;
  }

  @Override
  public String toString() {
    if (newTradesCount == 0) {
      return "NO TRADES";
    }
    return
        "amount of trades: " + newTradesCount +
            ", quantity of crypto involved: " + tradeQuantity +
            ", total amount: " + tradeAmount;
  }
}
