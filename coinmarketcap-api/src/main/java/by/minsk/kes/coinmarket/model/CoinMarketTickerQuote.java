package by.minsk.kes.coinmarket.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CoinMarketTickerQuote extends CoinMarketModel {

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("volume_24h")
    private BigDecimal volume24h;

    @JsonProperty("market_cap")
    private BigDecimal marketCap;

    @JsonProperty("percent_change_1h")
    private BigDecimal percentChange1h;

    @JsonProperty("percent_change_24h")
    private BigDecimal percentChange24h;

    @JsonProperty("percent_change_7d")
    private BigDecimal percentChange7d;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getVolume24h() {
        return volume24h;
    }

    public void setVolume24h(BigDecimal volume24h) {
        this.volume24h = volume24h;
    }

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }

    public BigDecimal getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(BigDecimal percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public BigDecimal getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(BigDecimal percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public BigDecimal getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(BigDecimal percentChange7d) {
        this.percentChange7d = percentChange7d;
    }
}
