package by.minsk.kes.coinmarket.model.response;

import by.minsk.kes.coinmarket.model.CoinMarketCryptoInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CoinMarketListingsResponse extends CoinMarketBasicResponse {

    @JsonProperty("data")
    private List<CoinMarketCryptoInfo> info;

    public List<CoinMarketCryptoInfo> getInfo() {
        return info;
    }

    public void setInfo(List<CoinMarketCryptoInfo> info) {
        this.info = info;
    }
}
