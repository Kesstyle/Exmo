package by.minsk.kes.exmo.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class KesTickerInfo implements Serializable {

    private BigDecimal buyPrice;
    private BigDecimal sellPrice;
    private BigDecimal lastTrade;
    private BigDecimal highPrice;
    private BigDecimal lowPrice;
    private BigDecimal avgPrice;
    private BigDecimal volume;
    private BigDecimal volumeSellCurrency;
    private Date lastUpdated;

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

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public BigDecimal getVolatilePercent() {
        return highPrice.divide(lowPrice, 4, RoundingMode.HALF_DOWN).subtract(BigDecimal.ONE);
    }

    @Override
    public String toString() {
        return "KesTickerInfo{" +
                "buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                ", lastTrade=" + lastTrade +
                ", highPrice=" + highPrice +
                ", lowPrice=" + lowPrice +
                ", avgPrice=" + avgPrice +
                ", volume=" + volume +
                ", volumeSellCurrency=" + volumeSellCurrency +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
