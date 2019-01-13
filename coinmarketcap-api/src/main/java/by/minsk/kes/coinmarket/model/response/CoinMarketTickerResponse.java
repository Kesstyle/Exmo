package by.minsk.kes.coinmarket.model.response;

import by.minsk.kes.coinmarket.model.CoinMarketTicker;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class CoinMarketTickerResponse extends CoinMarketBasicResponse {

    @JsonProperty("data")
    private Map<String, CoinMarketTicker> ticker;

    public Map<String, CoinMarketTicker> getTicker() {
        return ticker;
    }

    public void setTicker(Map<String, CoinMarketTicker> ticker) {
        this.ticker = ticker;
    }
}
