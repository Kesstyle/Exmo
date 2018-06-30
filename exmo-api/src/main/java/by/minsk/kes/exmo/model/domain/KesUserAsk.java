package by.minsk.kes.exmo.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class KesUserAsk implements Serializable {

    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal amount;

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
        return "KesUserAsk{" +
                "price=" + price +
                ", quantity=" + quantity +
                ", amount=" + amount +
                '}';
    }
}
