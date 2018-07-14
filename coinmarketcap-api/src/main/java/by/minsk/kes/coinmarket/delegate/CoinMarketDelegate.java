package by.minsk.kes.coinmarket.controller.delegate;

import by.minsk.kes.coinmarket.controller.delegate.repository.CoinMarketOperationsRepository;
import by.minsk.kes.coinmarket.legacy.CoinMarketCapRestAdapter;
import by.minsk.kes.coinmarket.model.CoinMarketSpecificTickerResponse;
import by.minsk.kes.coinmarket.model.CoinMarketTicker;
import by.minsk.kes.coinmarket.model.CoinMarketTickerResponse;
import by.minsk.kes.coinmarket.transform.parser.CoinMarketParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service("coinMarketDelegate")
public class CoinMarketDelegate {

    @Value("${coinmarket.baseUrl}")
    private String baseUrl;

    @Autowired
    private CoinMarketOperationsRepository coinMarketOperationsRepository;

    @Autowired
    private CoinMarketCapRestAdapter adapter;

    @Autowired
    private CoinMarketParser parser;

    public Map<String, CoinMarketTicker> getCoinMarketTicker() {
        final String json = adapter.get(baseUrl, coinMarketOperationsRepository.getTickerOperation(), null, new HashMap<String, String>() {{
            put("convert", "BTC");
        }});
        final CoinMarketTickerResponse tickerResponse = parser.buildTickerDataFromJson(json);
        return tickerResponse.getTicker();
    }

    public CoinMarketTicker getCoinMarketTicker(final String id) {
        final String json = adapter.get(baseUrl, coinMarketOperationsRepository.getTickerOperation(), Arrays.asList(id),
                new HashMap<String, String>() {{
                    put("convert", "BTC");
                }});
        final CoinMarketSpecificTickerResponse tickerResponse = parser.buildSpecificTickerDataFromJson(json);
        return tickerResponse.getTicker();
    }
}
