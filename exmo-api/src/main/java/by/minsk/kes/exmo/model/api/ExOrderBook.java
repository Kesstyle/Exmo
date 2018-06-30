package by.minsk.kes.exmo.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ExOrderBook implements Serializable {

    @JsonProperty("ask_quantity")
    private BigDecimal sellQuantity;

    @JsonProperty("ask_amount")
    private BigDecimal sellAmount;

    @JsonProperty("ask_top")
    private BigDecimal sellTop;

    @JsonProperty("bid_quantity")
    private BigDecimal buyQuantity;

    @JsonProperty("bid_amount")
    private BigDecimal buyAmount;

    @JsonProperty("bid_top")
    private BigDecimal buyTop;

    @JsonProperty("ask")
    private List<List<BigDecimal>> sellOrders;

    @JsonProperty("bid")
    private List<List<BigDecimal>> buyOrders;

    public BigDecimal getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(BigDecimal sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public BigDecimal getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(BigDecimal sellAmount) {
        this.sellAmount = sellAmount;
    }

    public BigDecimal getSellTop() {
        return sellTop;
    }

    public void setSellTop(BigDecimal sellTop) {
        this.sellTop = sellTop;
    }

    public BigDecimal getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(BigDecimal buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public BigDecimal getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(BigDecimal buyAmount) {
        this.buyAmount = buyAmount;
    }

    public BigDecimal getBuyTop() {
        return buyTop;
    }

    public void setBuyTop(BigDecimal buyTop) {
        this.buyTop = buyTop;
    }

    public List<List<BigDecimal>> getSellOrders() {
        return sellOrders;
    }

    public void setSellOrders(List<List<BigDecimal>> sellOrders) {
        this.sellOrders = sellOrders;
    }

    public List<List<BigDecimal>> getBuyOrders() {
        return buyOrders;
    }

    public void setBuyOrders(List<List<BigDecimal>> buyOrders) {
        this.buyOrders = buyOrders;
    }

    @Override
    public String toString() {
        return "ExOrderBook{" +
                "sellQuantity=" + sellQuantity +
                ", sellAmount=" + sellAmount +
                ", sellTop=" + sellTop +
                ", buyQuantity=" + buyQuantity +
                ", buyAmount=" + buyAmount +
                ", buyTop=" + buyTop +
                ", sellOrders=" + sellOrders +
                ", buyOrders=" + buyOrders +
                '}';
    }
}
