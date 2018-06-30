package by.minsk.kes.exmo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class ExTrade implements Serializable {

  @JsonProperty("trade_id")
  private Long tradeId;

  @JsonProperty("type")
  private String orderType;

  @JsonProperty("date")
  private Long unixDate;

  @JsonProperty("quantity")
  private BigDecimal quantity;

  @JsonProperty("price")
  private BigDecimal price;

  @JsonProperty("amount")
  private BigDecimal amount;

  public Long getTradeId() {
    return tradeId;
  }

  public void setTradeId(Long tradeId) {
    this.tradeId = tradeId;
  }

  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

  public Long getUnixDate() {
    return unixDate;
  }

  public void setUnixDate(Long unixDate) {
    this.unixDate = unixDate;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

  public void setQuantity(BigDecimal quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "ExTrade{" +
        "tradeId=" + tradeId +
        ", orderType='" + orderType + '\'' +
        ", unixDate=" + unixDate +
        ", quantity=" + quantity +
        ", price=" + price +
        ", amount=" + amount +
        '}';
  }
}
