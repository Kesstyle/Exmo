package by.minsk.kes.coinmarket.model.response;

import by.minsk.kes.coinmarket.model.CoinMarketModel;
import by.minsk.kes.coinmarket.model.CoinMarketStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class CoinMarketBasicResponse extends CoinMarketModel {

    @JsonProperty("status")
    private CoinMarketStatus status;

    public CoinMarketStatus getStatus() {
        return status;
    }

    public void setStatus(final CoinMarketStatus status) {
        this.status = status;
    }
}
