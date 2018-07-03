package by.minsk.kes.exmo.observer.helper.model;

import by.minsk.kes.exmo.model.domain.Pair;

import java.io.Serializable;
import java.math.BigDecimal;

public class Trading implements Serializable {

    private Pair pair;
    private BigDecimal quantity = BigDecimal.ZERO;
    private BigDecimal amount = BigDecimal.ZERO;
    private BigDecimal avgPrice = BigDecimal.ZERO;

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

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Pair getPair() {
        return pair;
    }

    public void setPair(Pair pair) {
        this.pair = pair;
    }

    @Override
    public String toString() {
        return String.format("[%s] (%s %s totally for %s %s)", avgPrice, quantity, pair.getFirstCurrency(), amount, pair.getSecondCurrency());
    }
}
