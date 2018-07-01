package by.minsk.kes.exmo.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * "BTC_USD": {
 * "buy_price": "589.06",
 * "sell_price": "592",
 * "last_trade": "591.221",
 * "high": "602.082",
 * "low": "584.51011695",
 * "avg": "591.14698808",
 * "vol": "167.59763535",
 * "vol_curr": "99095.17162071",
 * "updated": 1470250973
 * }
 */
public class ExTicker implements Serializable {

    @JsonProperty("buy_price")
    private BigDecimal buyPrice;

    @JsonProperty("sell_price")
    private BigDecimal sellPrice;

    @JsonProperty("last_trade")
    private BigDecimal lastTrade;

    @JsonProperty("high")
    private BigDecimal highPrice;

    @JsonProperty("low")
    private BigDecimal lowPrice;

    @JsonProperty("avg")
    private BigDecimal avgPrice;

    @JsonProperty("vol")
    private BigDecimal volume;

    @JsonProperty("vol_curr")
    private BigDecimal volumeSellCurrency;

    @JsonProperty("updated")
    private Long updated;

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public BigDecimal getLastTrade() {
        return lastTrade;
    }

    public void setLastTrade(BigDecimal lastTrade) {
        this.lastTrade = lastTrade;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getVolumeSellCurrency() {
        return volumeSellCurrency;
    }

    public void setVolumeSellCurrency(BigDecimal volumeSellCurrency) {
        this.volumeSellCurrency = volumeSellCurrency;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "ExTicker{" +
                "buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                ", lastTrade=" + lastTrade +
                ", highPrice=" + highPrice +
                ", lowPrice=" + lowPrice +
                ", avgPrice=" + avgPrice +
                ", volume=" + volume +
                ", volumeSellCurrency=" + volumeSellCurrency +
                ", updated=" + updated +
                '}';
    }
}
