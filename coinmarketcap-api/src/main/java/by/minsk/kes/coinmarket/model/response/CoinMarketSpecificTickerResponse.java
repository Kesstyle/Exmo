package by.minsk.kes.coinmarket.model.response;

import by.minsk.kes.coinmarket.model.CoinMarketTicker;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CoinMarketSpecificTickerResponse extends CoinMarketBasicResponse {

    @JsonProperty("data")
    private CoinMarketTicker ticker;

    public CoinMarketTicker getTicker() {
        return ticker;
    }

    public void setTicker(CoinMarketTicker ticker) {
        this.ticker = ticker;
    }
}
