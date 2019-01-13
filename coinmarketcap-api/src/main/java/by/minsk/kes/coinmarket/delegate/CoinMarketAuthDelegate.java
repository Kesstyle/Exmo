package by.minsk.kes.coinmarket.delegate;

import by.minsk.kes.coinmarket.legacy.CoinMarketCapRestAdapter;
import by.minsk.kes.coinmarket.model.CoinMarketCryptoInfo;
import by.minsk.kes.coinmarket.model.CoinMarketTicker;
import by.minsk.kes.coinmarket.model.response.CoinMarketSpecificTickerResponse;
import by.minsk.kes.coinmarket.model.response.CoinMarketTickerResponse;
import by.minsk.kes.coinmarket.repository.CoinMarketOperationsRepository;
import by.minsk.kes.coinmarket.repository.CoinMarketResourceRepository;
import by.minsk.kes.coinmarket.transform.parser.CoinMarketParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toMap;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service("coinMarketAuthDelegate")
public class CoinMarketAuthDelegate implements CoinMarketDelegate {

    private static final String API_KEY_HEADER = "X-CMC_PRO_API_KEY";
    private static final String DEFAULT_CURRENCY = "BTC";
    private static final String CONVERT_DEFAULT_CURRENCY_TO = "USD";

    @Value("${coinmarket.authorised.cryptocurrency.baseUrl}")
    private String baseUrl;

    @Value("${coinmarket.api.key}")
    private String apiKey;

    private Map<String, String> authHeader;

    @Autowired
    private CoinMarketOperationsRepository coinMarketOperationsRepository;

    @Autowired
    private CoinMarketResourceRepository coinMarketResourceRepository;

    @Autowired
    private CoinMarketCapRestAdapter adapter;

    @Autowired
    private CoinMarketParser parser;

    public Map<String, CoinMarketTicker> getCoinMarketTicker() {
        final String json = adapter.get(baseUrl, coinMarketOperationsRepository.getTickerAuthOperation(), null, new HashMap<String, String>() {{
            put("convert", "BTC");
        }}, authHeader);
        final CoinMarketTickerResponse tickerResponse = parser.buildTickerDataFromJson(json);
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
        final Optional<String> requestIds = currencies.stream()
                .filter(cryptoIdMap::containsKey)
                .map(cryptoIdMap::get)
                .reduce((v1, v2) -> v1 + "," + v2);
        if (!requestIds.isPresent()) {
            return null;
        }
        final CompletableFuture<Map<String, CoinMarketTicker>> btcFuture = CompletableFuture.supplyAsync(
                () -> getCoinMarketTicker(cryptoIdMap.get(DEFAULT_CURRENCY), CONVERT_DEFAULT_CURRENCY_TO));
        final String json = adapter.get(baseUrl, coinMarketOperationsRepository.getTickerAuthOperation(), null,
                new HashMap<String, String>() {{
                    put("convert", "BTC");
                    put("id", requestIds.get());
                }}, authHeader);
        final CoinMarketTickerResponse tickerResponse = parser.buildTickerDataFromJson(json);
        final Map<String, CoinMarketTicker> result = tickerResponse.getTicker();
        try {
            result.putAll(btcFuture.get());
        } catch (final Exception e) {
        }
        return result;
    }

    private Map<String, CoinMarketTicker> getCoinMarketTicker(final String id, final String convertTo) {
        final String json = adapter.get(baseUrl, coinMarketOperationsRepository.getTickerAuthOperation(), null,
                new HashMap<String, String>() {{
                    put("convert", convertTo);
                    put("id", id);
                }}, authHeader);
        final CoinMarketTickerResponse tickerResponse = parser.buildTickerDataFromJson(json);
        return tickerResponse.getTicker();
    }

    private Map<String, String> getCoinMarketMapping() {
        if (coinMarketResourceRepository.getCryptoInfoList() != null &&
                coinMarketResourceRepository.getCurrencyIdMap() != null) {
            return coinMarketResourceRepository.getCurrencyIdMap();
        }
        final String json = adapter.get(baseUrl, coinMarketOperationsRepository.getMapAuthOperation(), null,
                null, getAuthHeader());
        final List<CoinMarketCryptoInfo> cryptoInfoList = parser.buildListingsResponseFromJson(json).getInfo();
        final Map<String, String> cryptoIdMap = cryptoInfoList.stream()
                .collect(toMap(k -> k.getCode(), k -> k.getId(), (v1, v2) -> v1, HashMap::new));
        coinMarketResourceRepository.setCryptoInfoList(cryptoInfoList);
        coinMarketResourceRepository.setCurrencyIdMap(cryptoIdMap);
        return cryptoIdMap;
    }

    private Map<String, String> getAuthHeader() {
        if (authHeader == null) {
            authHeader = new HashMap<>();
            authHeader.put(API_KEY_HEADER, apiKey);
        }
        return authHeader;
    }
}
