package by.minsk.kes.coinmarket.delegate;

import by.minsk.kes.coinmarket.model.*;
import by.minsk.kes.coinmarket.repository.CoinMarketOperationsRepository;
import by.minsk.kes.coinmarket.legacy.CoinMarketCapRestAdapter;
import by.minsk.kes.coinmarket.repository.CoinMarketResourceRepository;
import by.minsk.kes.coinmarket.transform.parser.CoinMarketParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("coinMarketDelegate")
public class CoinMarketDelegate {

    @Value("${coinmarket.baseUrl}")
    private String baseUrl;

    @Autowired
    private CoinMarketOperationsRepository coinMarketOperationsRepository;

    @Autowired
    private CoinMarketResourceRepository coinMarketResourceRepository;

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

    public Map<String, CoinMarketTicker> getCoinMarketTicker(final List<String> currencies) {
        if (CollectionUtils.isEmpty(currencies)) {
            return null;
        }
        Map<String, String> cryptoIdMap = coinMarketResourceRepository.getCurrencyIdMap();
        if (cryptoIdMap == null) {
            cryptoIdMap = getCoinMarketMapping();
            if (cryptoIdMap == null) {
                return null;
            }
        }
        final Map<String, CoinMarketTicker> coinMarketTickerMap = new HashMap<>();
        for (final String currency : currencies) {
            if (!cryptoIdMap.containsKey(currency)) {
                continue;
            }
            final CoinMarketTicker ticker = getCoinMarketTicker(cryptoIdMap.get(currency));
            if (ticker != null) {
                coinMarketTickerMap.put(currency, ticker);
            }
        }
        return coinMarketTickerMap;
    }

    public Map<String, String> getCoinMarketMapping() {
        final String json = adapter.get(baseUrl, coinMarketOperationsRepository.getListingsOperation(), null,
                null);
        final CoinMarketListingsResponse listingsResponse = parser.buildListingsResponseFromJson(json);
        final List<CoinMarketCryptoInfo> cryptoInfoList = listingsResponse.getInfo();
        coinMarketResourceRepository.setCryptoInfoList(cryptoInfoList);
        final Map<String, String> cryptoIdMap = new HashMap<>();
        for (final CoinMarketCryptoInfo cryptoInfo : cryptoInfoList) {
            cryptoIdMap.put(cryptoInfo.getCode(), cryptoInfo.getId());
        }
        coinMarketResourceRepository.setCurrencyIdMap(cryptoIdMap);
        return cryptoIdMap;
    }
}
