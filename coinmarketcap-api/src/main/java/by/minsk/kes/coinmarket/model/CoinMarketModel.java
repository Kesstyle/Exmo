package by.minsk.kes.coinmarket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CoinMarketModel implements Serializable {
}
