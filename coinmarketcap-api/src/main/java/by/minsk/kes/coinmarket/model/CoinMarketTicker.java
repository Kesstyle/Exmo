package by.minsk.kes.coinmarket.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * "1720": {
 *             "id": 1720,
 *             "name": "IOTA",
 *             "symbol": "MIOTA",
 *             "website_slug": "iota",
 *             "rank": 10,
 *             "circulating_supply": 2779530283.0,
 *             "total_supply": 2779530283.0,
 *             "max_supply": 2779530283.0,
 *             "quotes": {
 *                 "USD": {
 *                     "price": 0.972983,
 *                     "volume_24h": 32880200.0,
 *                     "market_cap": 2704435713.0,
 *                     "percent_change_1h": -1.57,
 *                     "percent_change_24h": 2.17,
 *                     "percent_change_7d": -8.05
 *                 }
 *             },
 *             "last_updated": 1531509691
 *         },
 */
public class CoinMarketTicker implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("symbol")
    private String code;

    @JsonProperty("website_slug")
    private String websiteSlug;

    @JsonProperty("rank")
    private int rank;

    @JsonProperty("circulating_supply")
    private BigDecimal circulatingSupply;

    @JsonProperty("total_supply")
    private BigDecimal totalSupply;

    @JsonProperty("max_supply")
    private BigDecimal maxSupply;

    @JsonProperty("quotes")
    private Map<String, CoinMarketTickerQuote> quotes;

    @JsonProperty("last_updated")
    private Long lastUpdated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getWebsiteSlug() {
        return websiteSlug;
    }

    public void setWebsiteSlug(String websiteSlug) {
        this.websiteSlug = websiteSlug;
    }

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

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
