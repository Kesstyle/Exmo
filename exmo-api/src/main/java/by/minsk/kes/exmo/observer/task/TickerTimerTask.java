package by.minsk.kes.exmo.observer.task;

import by.minsk.kes.exmo.legacy.ExmoRestAdapter;
import by.minsk.kes.exmo.model.api.ExTicker;
import by.minsk.kes.exmo.model.domain.KesTickerInfo;
import by.minsk.kes.exmo.model.domain.Pair;
import by.minsk.kes.exmo.transform.converter.KesTickerConverter;
import by.minsk.kes.exmo.transform.parser.ExParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("tickerTask")
@ManagedResource(description = "Manages ticker functionality")
public class TickerTimerTask extends KesTimerTask {

    private static final Logger LOG = LoggerFactory.getLogger("Ticker");

    @Value("${exmo.ticket.operation}")
    private String operation;

    @Value("${exmo.ticker.pairs}")
    private String pairs;

    @Autowired
    private KesTickerConverter kesTickerConverter;

    @Override
    public void run() {
        final String json = exmoRestAdapter.get(getBaseUrl(), operation, getParamsMap());
        final Map<String, ExTicker> exTickerMap = exParser.buildTickerFromJson(json);
        final Map<String, KesTickerInfo> kesTickerMap = filter(kesTickerConverter.convertMap(exTickerMap));
        printTickers(kesTickerMap);
    }

    @Override
    protected Map<String, String> getParamsMap() {
        return null;
    }

    private Map<String, KesTickerInfo> filter(final Map<String, KesTickerInfo> kesTickerMap) {
        if (kesTickerMap == null) {
            return null;
        }
        final Map<String, KesTickerInfo> filteredResult = new HashMap<>();
        final List<Pair> pairsList = pairConverter.getListFromString(pairs);
        if (CollectionUtils.isEmpty(pairsList)) {
            return filteredResult;
        }
        for (final Map.Entry<String, KesTickerInfo> entry : kesTickerMap.entrySet()) {
            if (pairsList.contains(entry.getKey())) {
                filteredResult.put(entry.getKey(), entry.getValue());
            }
        }
        return filteredResult;
    }

    private void printTickers(final Map<String, KesTickerInfo> kesTickerMap) {
        LOG.debug("=============================================================================");
        for (final Map.Entry<String, KesTickerInfo> entry : kesTickerMap.entrySet()) {
            final KesTickerInfo ticker = entry.getValue();
            final Pair pair = pairConverter.getFromString(entry.getKey());
            LOG.debug(String.format("%s, %s ==> %s %s - %s %s (High: %s %s, Low: %s %s). Volatility = %s %%", ticker.getLastUpdated(), pair.toString(),
                    ticker.getBuyPrice(), pair.getSecondCurrency(), ticker.getSellPrice(), pair.getSecondCurrency(), ticker.getHighPrice(),
                    pair.getSecondCurrency(), ticker.getLowPrice(), pair.getSecondCurrency(), ticker.getVolatilePercent().multiply(BigDecimal.valueOf(100))));
        }
        LOG.debug("=============================================================================");
    }

    @ManagedAttribute
    public String getPairs() {
        return pairs;
    }

    @ManagedAttribute
    public void setPairs(String pairs) {
        this.pairs = pairs;
    }
}
