package by.minsk.kes.exmo.observer;

import by.minsk.kes.exmo.legacy.ExmoRestAdapter;
import by.minsk.kes.exmo.model.ExTrade;
import by.minsk.kes.exmo.model.KesOrder;
import by.minsk.kes.exmo.model.KesTradingStatistics;
import by.minsk.kes.exmo.transform.converter.KesTradeConverter;
import by.minsk.kes.exmo.transform.parser.ExOrderParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("tradesTask")
@Scope("prototype")
public class TradesObserveTimerTask implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(TradesObserveTimerTask.class);

    private static final String TRADES_PARAMETER_PAIR = "pair";

    @Value("${exmo.baseUrl}")
    private String baseUrl;

    @Value("${exmo.trades.operation}")
    private String tradesOperation;

    @Value("${exmo.trades." + TRADES_PARAMETER_PAIR + "}")
    private String tradePairs;

    @Autowired
    private TradesObserver tradesObserver;

    @Autowired
    private ExmoRestAdapter exmoRestAdapter;

    @Autowired
    private ExOrderParser exOrderParser;

    @Autowired
    private KesTradeConverter kesTradeConverter;

    @Override
    public void run() {
        String result = exmoRestAdapter.get(baseUrl, tradesOperation, getParamsMap());
        final Map<String, List<ExTrade>> exTrades = exOrderParser.buildTradeFromJson(result);
        final Map<String, List<KesOrder>> kesOrders = kesTradeConverter.convert(exTrades);
        final List<KesTradingStatistics> statistics = tradesObserver.analyze(kesOrders, new Date());
        if (statistics != null) {
            LOG.debug(statistics.toString() + "\r\n");
        }
    }

    private Map<String, String> getParamsMap() {
        final Map<String, String> params = new HashMap<>();
        params.put(TRADES_PARAMETER_PAIR, tradePairs);
        return params;
    }

}
