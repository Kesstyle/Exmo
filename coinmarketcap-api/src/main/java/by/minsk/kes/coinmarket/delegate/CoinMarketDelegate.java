package by.minsk.kes.coinmarket.delegate;

import by.minsk.kes.coinmarket.model.CoinMarketTicker;

import java.util.List;
import java.util.Map;

public interface CoinMarketDelegate {
    Map<String, CoinMarketTicker> getCoinMarketTicker();
    Map<String, CoinMarketTicker> getCoinMarketTicker(final List<String> currencies);
}
