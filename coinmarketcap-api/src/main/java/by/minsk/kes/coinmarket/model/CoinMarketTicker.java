package by.minsk.kes.coinmarket.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

/**
 * "2": {
 * "id": 2,
 * "name": "Litecoin",
 * "symbol": "LTC",
 * "slug": "litecoin",
 * "circulating_supply": 60002350.2148228,
 * "total_supply": 60002350.2148228,
 * "max_supply": 84000000,
 * "date_added": "2013-04-28T00:00:00.000Z",
 * "num_market_pairs": 724,
 * "tags": [
 * "mineable"
 * ],
 * "platform": null,
 * "cmc_rank": 8,
 * "last_updated": "2019-01-13T15:08:04.000Z",
 * "quote": {
 * "BTC": {
 * "price": 0.008875119974835047,
 * "volume_24h": 143069.74501689558,
 * "percent_change_1h": 0.1303,
 * "percent_change_24h": 0.7358,
 * "percent_change_7d": -9.7063,
 * "market_cap": 532528.0569286217,
 * "last_updated": "2019-01-13T15:08:23.000Z"
 * }
 * }
 * },
 */
public class CoinMarketTicker extends CoinMarketCryptoInfo {

    @JsonProperty("cmc_rank")
    private int rank;

    @JsonProperty("circulating_supply")
    private BigDecimal circulatingSupply;

    @JsonProperty("total_supply")
    private BigDecimal totalSupply;

    @JsonProperty("max_supply")
    private BigDecimal maxSupply;

    @JsonProperty("quote")
    private Map<String, CoinMarketTickerQuote> quotes;

    @JsonProperty("last_updated")
    private String lastUpdated;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public BigDecimal getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(BigDecimal circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public BigDecimal getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(BigDecimal totalSupply) {
        this.totalSupply = totalSupply;
    }

    public BigDecimal getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(BigDecimal maxSupply) {
        this.maxSupply = maxSupply;
    }

    public Map<String, CoinMarketTickerQuote> getQuotes() {
        return quotes;
    }

    public void setQuotes(Map<String, CoinMarketTickerQuote> quotes) {
        this.quotes = quotes;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
