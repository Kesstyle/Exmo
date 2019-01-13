package by.minsk.kes.coinmarket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;

@JsonIgnoreProperties({"metadata", "attention"})
public class CoinMarketTickerResponse implements Serializable {

    @JsonProperty("data")
    private Map<String, CoinMarketTicker> ticker;

    public Map<String, CoinMarketTicker> getTicker() {
        return ticker;
    }

    public void setTicker(Map<String, CoinMarketTicker> ticker) {
        this.ticker = ticker;
    }
}
