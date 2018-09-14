package by.minsk.kes.exmo.analyzer.task;

import by.minsk.kes.coinmarket.delegate.CoinMarketDelegate;
import by.minsk.kes.coinmarket.model.CoinMarketTicker;
import by.minsk.kes.exmo.model.domain.Pair;
import by.minsk.kes.exmo.model.domain.coinmarket.KesCoinMarketTicker;
import by.minsk.kes.exmo.model.domain.coinmarket.KesCoinMarketTickerQuote;
import by.minsk.kes.exmo.transform.converter.coinmarket.KesCoinMarketTickerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component("coinMarketTickerTask")
public class CoinMarketTickerTimerTask extends KesTimerTask {

    @Autowired
    private CoinMarketDelegate coinMarketDelegate;

    @Autowired
    private KesCoinMarketTickerConverter kesCoinMarketTickerConverter;

    @Override
    public void startTask() {
        try {
            final Map<String, CoinMarketTicker> coinMarketTickerMap = coinMarketDelegate.getCoinMarketTicker(pairConverter.getFirstCurrencies(getPairs()));
            final Map<String, KesCoinMarketTicker> kesCoinMarketTickerMap = kesCoinMarketTickerConverter.convertMap(coinMarketTickerMap);
            final Map<String, Map<String, KesCoinMarketTickerQuote>> coinMarketPrices = filterAndAlign(kesCoinMarketTickerMap);
            repository.setCoinMarketTickerInfo(coinMarketPrices);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, Map<String, KesCoinMarketTickerQuote>> filterAndAlign(final Map<String, KesCoinMarketTicker> kesCoinMarketTickerMap) {
        if (kesCoinMarketTickerMap != null) {
            final List<Pair> allowedPairs = pairConverter.getListFromString(resourceRepository.getPairs());
            final Set<String> currencies = getExmoCurrenciesToTrade(allowedPairs);
            final Map<String, Map<String, KesCoinMarketTickerQuote>> resultMap = new HashMap<>();
            kesCoinMarketTickerMap.entrySet().stream().forEach(entry -> {
                final KesCoinMarketTicker ticker = entry.getValue();
                final String code = ticker.getCode();
                if (currencies.contains(code)) {
                    resultMap.put(code, ticker.getQuotes());
                }
            });
            return resultMap;
        }
        return null;
    }

    private Set<String> getExmoCurrenciesToTrade(final List<Pair> allowedPairs) {
        if (allowedPairs == null) {
            return null;
        }
        return allowedPairs.stream().map(Pair::getFirstCurrency).collect(Collectors.toSet());
    }
}
