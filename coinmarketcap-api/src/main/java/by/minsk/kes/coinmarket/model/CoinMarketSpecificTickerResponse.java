package by.minsk.kes.coinmarket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;

@JsonIgnoreProperties("metadata")
public class CoinMarketSpecificTickerResponse implements Serializable {

    @JsonProperty("data")
    private CoinMarketTicker ticker;

    public CoinMarketTicker getTicker() {
        return ticker;
    }

    public void setTicker(CoinMarketTicker ticker) {
        this.ticker = ticker;
    }
}
