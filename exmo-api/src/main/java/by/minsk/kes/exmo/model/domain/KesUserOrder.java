package by.minsk.kes.exmo.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class KesUserOrder implements Serializable {

    private BigDecimal sellTotalQuantity;
    private BigDecimal sellTotalAmount;
    private BigDecimal sellTopPrice;
    private BigDecimal buyTotalQuantity;
    private BigDecimal buyTotalAmount;
    private BigDecimal buyTopPrice;
    private List<KesUserAsk> sellOrders;
    private List<KesUserAsk> buyOrders;

    public BigDecimal getSellTotalQuantity() {
        return sellTotalQuantity;
    }

    public void setSellTotalQuantity(BigDecimal sellTotalQuantity) {
        this.sellTotalQuantity = sellTotalQuantity;
    }

    public BigDecimal getSellTotalAmount() {
        return sellTotalAmount;
    }

    public void setSellTotalAmount(BigDecimal sellTotalAmount) {
        this.sellTotalAmount = sellTotalAmount;
    }

    public BigDecimal getSellTopPrice() {
        return sellTopPrice;
    }

    public void setSellTopPrice(BigDecimal sellTopPrice) {
        this.sellTopPrice = sellTopPrice;
    }

    public BigDecimal getBuyTotalQuantity() {
        return buyTotalQuantity;
    }

    public void setBuyTotalQuantity(BigDecimal buyTotalQuantity) {
        this.buyTotalQuantity = buyTotalQuantity;
    }

    public BigDecimal getBuyTotalAmount() {
        return buyTotalAmount;
    }

    public void setBuyTotalAmount(BigDecimal buyTotalAmount) {
        this.buyTotalAmount = buyTotalAmount;
    }

    public BigDecimal getBuyTopPrice() {
        return buyTopPrice;
    }

    public void setBuyTopPrice(BigDecimal buyTopPrice) {
        this.buyTopPrice = buyTopPrice;
    }

    public List<KesUserAsk> getSellOrders() {
        return sellOrders;
    }

    public void setSellOrders(List<KesUserAsk> sellOrders) {
        this.sellOrders = sellOrders;
    }

    public List<KesUserAsk> getBuyOrders() {
        return buyOrders;
    }

    public void setBuyOrders(List<KesUserAsk> buyOrders) {
        this.buyOrders = buyOrders;
    }

    @Override
    public String toString() {
        return "KesUserOrder{" +
                "sellTotalQuantity=" + sellTotalQuantity +
                ", sellTotalAmount=" + sellTotalAmount +
                ", sellTopPrice=" + sellTopPrice +
                ", buyTotalQuantity=" + buyTotalQuantity +
                ", buyTotalAmount=" + buyTotalAmount +
                ", buyTopPrice=" + buyTopPrice +
                ", sellOrders=" + sellOrders +
                ", buyOrders=" + buyOrders +
                '}';
    }
}
