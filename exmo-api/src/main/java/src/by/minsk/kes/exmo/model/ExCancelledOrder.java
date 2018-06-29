package src.by.minsk.kes.exmo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class ExCancelledOrder implements Serializable {

  @JsonProperty("date")
  private Long dateLong;

  @JsonProperty("order_id")
  private Long orderId;

  @JsonProperty("order_type")
  private String orderType;

  @JsonProperty("pair")
  private String pair;

  @JsonProperty("price")
  private BigDecimal price;

  @JsonProperty("quantity")
  private BigDecimal quantity;

  @JsonProperty("amount")
  private BigDecimal amount;

  public Long getDateLong() {
    return dateLong;
  }

  public void setDateLong(Long dateLong) {
    this.dateLong = dateLong;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

  public String getPair() {
    return pair;
  }

  public void setPair(String pair) {
    this.pair = pair;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

  public void setQuantity(BigDecimal quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "ExCancelledOrder{" +
        "dateLong=" + dateLong +
        ", orderId=" + orderId +
        ", orderType='" + orderType + '\'' +
        ", pair='" + pair + '\'' +
        ", price=" + price +
        ", quantity=" + quantity +
        ", amount=" + amount +
        '}';
  }
}
