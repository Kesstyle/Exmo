package by.minsk.kes.exmo.observer.task;

import by.minsk.kes.exmo.legacy.ExmoRestAdapter;
import by.minsk.kes.exmo.model.api.ExTrade;
import by.minsk.kes.exmo.model.domain.KesOrder;
import by.minsk.kes.exmo.model.domain.KesTradingStatistics;
import by.minsk.kes.exmo.observer.TradesObserver;
import by.minsk.kes.exmo.transform.converter.KesTradeConverter;
import by.minsk.kes.exmo.transform.parser.ExParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("tradesTask")
@ManagedResource(description = "Manages trades observation")
public class TradesObserveTimerTask extends KesTimerTask {

    private static final Logger LOG = LoggerFactory.getLogger("Trades");

    private static final String TRADES_PARAMETER_PAIR = "pair";

    @Value("${exmo.trades.operation}")
    private String tradesOperation;

    @Value("${exmo.trades." + TRADES_PARAMETER_PAIR + "}")
    private String tradePairs;

    @Autowired
    private TradesObserver tradesObserver;

    @Autowired
    private KesTradeConverter kesTradeConverter;

    @Override
    public void run() {
        final String json = exmoRestAdapter.get(getBaseUrl(), tradesOperation, getParamsMap());
        final Map<String, List<ExTrade>> exTrades = exParser.buildTradeFromJson(json);
        final Map<String, List<KesOrder>> kesOrders = kesTradeConverter.convertMapCollection(exTrades);
        final List<KesTradingStatistics> statistics = tradesObserver.analyze(kesOrders, new Date());
        if (statistics != null) {
            LOG.debug(statistics.toString() + "\r\n");
        }
    }

    protected Map<String, String> getParamsMap() {
        final Map<String, String> params = new HashMap<>();
        params.put(TRADES_PARAMETER_PAIR, getTradePairs());
        return params;
    }

    @ManagedAttribute
    public String getTradePairs() {
        return tradePairs;
    }

    @ManagedAttribute
    public void setTradePairs(String tradePairs) {
        this.tradePairs = tradePairs;
    }
}
