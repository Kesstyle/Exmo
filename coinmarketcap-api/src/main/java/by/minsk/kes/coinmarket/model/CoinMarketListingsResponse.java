package by.minsk.kes.coinmarket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties({"metadata", "attention"})
public class CoinMarketListingsResponse {

    @JsonProperty("data")
    private List<CoinMarketCryptoInfo> info;

    public List<CoinMarketCryptoInfo> getInfo() {
        return info;
    }

    public void setInfo(List<CoinMarketCryptoInfo> info) {
        this.info = info;
    }
}
