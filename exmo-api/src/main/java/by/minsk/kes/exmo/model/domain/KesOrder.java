package by.minsk.kes.exmo.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class KesOrder implements Serializable {

    private Date date;
    private Long id;
    private KesOrderType orderType;
    private String pair;
    private BigDecimal price;
    private BigDecimal quantity;
    // It may be actually calculated by unusual logic on ExmoRestAdapter, so leave this field independent for now
    private BigDecimal totalAmount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KesOrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(KesOrderType orderType) {
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String toString() {
        return String.format("Date: %s, %s, %s, Price: %s", date, orderType, pair, price);
    }
}
