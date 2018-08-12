package by.minsk.kes.exmo.observer.helper.model;

import by.minsk.kes.exmo.model.domain.Pair;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Trading implements Serializable {

    private Pair pair;
    private BigDecimal quantity = BigDecimal.ZERO;
    private BigDecimal amount = BigDecimal.ZERO;
    private BigDecimal avgPrice = BigDecimal.ZERO;
    private BigDecimal valueToCompare;

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

    public BigDecimal getValueToCompare() {
        return valueToCompare;
    }

    public void setValueToCompare(BigDecimal valueToCompare) {
        this.valueToCompare = valueToCompare;
    }

    public BigDecimal percentDiff() {
        if (valueToCompare == null || avgPrice.doubleValue() == 0.0d) {
            return null;
        }
        return valueToCompare.divide(avgPrice, 4, RoundingMode.HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100));
    }

    @Override
    public String toString() {
        final BigDecimal percent = percentDiff();
        if (percent == null) {
            return String.format("[%s <CoinMarket: --- (---%%)>] (%s %s totally for %s %s)", avgPrice, quantity, pair.getFirstCurrency(), amount, pair.getSecondCurrency());
        } else {
            final String sign = percent.doubleValue() > 0? "+" : "-";
            return String.format("[%s <CoinMarket: %s (%s%s%%)>] (%s %s totally for %s %s)",
                    avgPrice, valueToCompare, sign, percent.abs(), quantity, pair.getFirstCurrency(), amount, pair.getSecondCurrency());
        }
    }
}
