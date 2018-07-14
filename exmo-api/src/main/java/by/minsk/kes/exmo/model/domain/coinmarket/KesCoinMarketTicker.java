package by.minsk.kes.exmo.model.domain.coinmarket;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class KesCoinMarketTicker implements Serializable {

    private String id;
    private String name;
    private String code;
    private int rank;
    private BigDecimal circulatingVolume;
    private BigDecimal totalSupply;
    private BigDecimal maxSupply;
    private Map<String, KesCoinMarketTickerQuote> quotes;
    private Date lastUpdated;

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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public BigDecimal getCirculatingVolume() {
        return circulatingVolume;
    }

    public void setCirculatingVolume(BigDecimal circulatingVolume) {
        this.circulatingVolume = circulatingVolume;
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

    public Map<String, KesCoinMarketTickerQuote> getQuotes() {
        return quotes;
    }

    public void setQuotes(Map<String, KesCoinMarketTickerQuote> quotes) {
        this.quotes = quotes;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
