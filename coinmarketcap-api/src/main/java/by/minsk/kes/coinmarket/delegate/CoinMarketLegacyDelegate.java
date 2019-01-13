package by.minsk.kes.coinmarket.delegate;

import by.minsk.kes.coinmarket.model.*;
import by.minsk.kes.coinmarket.model.response.CoinMarketSpecificTickerResponse;
import by.minsk.kes.coinmarket.model.response.CoinMarketTickerResponse;
import by.minsk.kes.coinmarket.repository.CoinMarketOperationsRepository;
import by.minsk.kes.coinmarket.legacy.CoinMarketCapRestAdapter;
import by.minsk.kes.coinmarket.repository.CoinMarketResourceRepository;
import by.minsk.kes.coinmarket.transform.parser.CoinMarketParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service("coinMarketLegacyDelegate")
public class CoinMarketLegacyDelegate implements CoinMarketDelegate {

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
        final String json = adapter.get(baseUrl, coinMarketOperationsRepository.getTickerOperation(), asList(id),
                new HashMap<String, String>() {{
                    put("convert", "BTC");
                }});
        final CoinMarketSpecificTickerResponse tickerResponse = parser.buildSpecificTickerDataFromJson(json);
        return tickerResponse.getTicker();
    }

    public Map<String, CoinMarketTicker> getCoinMarketTicker(final List<String> currencies) {
        if (isEmpty(currencies)) {
            return null;
        }
        final Map<String, String> cryptoIdMap = getCoinMarketMapping();
        if (cryptoIdMap == null) {
            return null;
        }
        final Map<String, CoinMarketTicker> coinMarketTickerMap = currencies.stream().filter(cryptoIdMap::containsKey)
                .map(v -> new SimpleEntry<>(v, getCoinMarketTicker(cryptoIdMap.get(v))))
                .filter(v -> v.getValue() != null)
                .collect(toMap(v -> v.getKey(), v -> v.getValue(), (v1, v2) -> v1, HashMap::new));
        return coinMarketTickerMap;
    }

    private Map<String, String> getCoinMarketMapping() {
        if (coinMarketResourceRepository.getCryptoInfoList() != null &&
                coinMarketResourceRepository.getCurrencyIdMap() != null) {
            return coinMarketResourceRepository.getCurrencyIdMap();
        }
        final String json = adapter.get(baseUrl, coinMarketOperationsRepository.getListingsOperation(), null,
                null);
        final List<CoinMarketCryptoInfo> cryptoInfoList = parser.buildListingsResponseFromJson(json).getInfo();
        final Map<String, String> cryptoIdMap = cryptoInfoList.stream()
                .collect(toMap(k -> k.getCode(), k -> k.getId(), (v1, v2) -> v1, HashMap::new));
        coinMarketResourceRepository.setCryptoInfoList(cryptoInfoList);
        coinMarketResourceRepository.setCurrencyIdMap(cryptoIdMap);
        return cryptoIdMap;
    }
}
