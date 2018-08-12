package by.minsk.kes.exmo.observer.task;

import by.minsk.kes.exmo.model.api.ExHistoryTrade;
import by.minsk.kes.exmo.model.domain.KesUserTradesHistory;
import by.minsk.kes.exmo.model.domain.Pair;
import by.minsk.kes.exmo.observer.helper.model.Trading;
import by.minsk.kes.exmo.transform.converter.KesTradesHistoryConverter;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.lang.String.format;

@Component("userTradesHistoryTask")
@ManagedResource(description = "Manages trading history functionality")
public class UserTradesHistoryTask extends KesTimerTask {

    private static final Logger LOG = LoggerFactory.getLogger("TradesHistory");

    private static final String TRADES_HISTORY_PARAMETER_PAIR = "pair";
    private static final String TRADES_HISTORY_PARAMETER_LIMIT = "limit";
    private static final String TRADES_HISTORY_PARAMETER_LIMIT_DEFAULT = "10000";
    private static final String TRADES_HISTORY_PARAMETER_OFFSET = "offset";
    private static final String TRADES_HISTORY_PARAMETER_OFFSET_DEFAULT = "0";

    @Value("${exmo.trading.history.minimum.BTC:0.004}")
    private double minimumBtcConsider;

    @Value("${exmo.trading.history.maximum.records:8}")
    private int maximumRecords;

    @Autowired
    private KesTradesHistoryConverter kesTradesHistoryConverter;

    @Override
    public void startTask() {
        final Map<String, List<ExHistoryTrade>> exHistoryTradeList = delegate.getUserTradesHistory(getParamsMap());
        final Map<String, List<KesUserTradesHistory>> kesUserTradesHistories = filter(kesTradesHistoryConverter.convertMapCollection(exHistoryTradeList));

        logTradesHistory(kesUserTradesHistories);
    }

    protected Map<String, String> getParamsMap() {
        final Map<String, String> params = new HashMap<>();
        params.put(TRADES_HISTORY_PARAMETER_LIMIT, TRADES_HISTORY_PARAMETER_LIMIT_DEFAULT);
        params.put(TRADES_HISTORY_PARAMETER_OFFSET, TRADES_HISTORY_PARAMETER_OFFSET_DEFAULT);
        params.put(TRADES_HISTORY_PARAMETER_PAIR, getPairs());
        return params;
    }

    private Map<String, List<KesUserTradesHistory>> filter(final Map<String, List<KesUserTradesHistory>> kesUserTradesHistories) {
        final Map<String, Trading> potentialTradings = repository.getPotentialSells();
        final Map<String, List<KesUserTradesHistory>> filteredHistory = new HashMap<>();
        if (MapUtils.isEmpty(potentialTradings)) {
            return filteredHistory;
        }
        for (final Entry<String, List<KesUserTradesHistory>> historyEntry : kesUserTradesHistories.entrySet()) {
            final String pair = historyEntry.getKey();
            if (potentialTradings.containsKey(pair)) {
                final Trading trading = potentialTradings.get(pair);
                final BigDecimal amount = trading.getAmount();
                if (amount.compareTo(BigDecimal.valueOf(minimumBtcConsider)) > 0) {
                    filteredHistory.put(historyEntry.getKey(), historyEntry.getValue());
                }
                continue;
            }
            filteredHistory.put(historyEntry.getKey(), historyEntry.getValue());
        }
        return filteredHistory;
    }

    private void logTradesHistory(final Map<String, List<KesUserTradesHistory>> tradesMap) {
        if (MapUtils.isEmpty(tradesMap)) {
            return;
        }
        final Map<String, Trading> potentialTradings = repository.getPotentialSells();
        LOG.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        try {
            for (final Entry<String, List<KesUserTradesHistory>> tradesEntry : tradesMap.entrySet()) {
                final String pair = tradesEntry.getKey();
                final Pair pairObj = pairConverter.getFromString(pair);
                final Trading trading = potentialTradings.get(pair);
                LOG.debug(format("#########  %s (%s %s currently, that is %s %s ) #########", pair, trading.getQuantity(), pairObj.getFirstCurrency(),
                        trading.getAmount(), pairObj.getSecondCurrency()));
                int index = 0;
                for (final KesUserTradesHistory trade : tradesEntry.getValue()) {
                    if (index++ >= getMaximumRecords()) {
                        LOG.debug(format("And others.... (%s records totally)", tradesEntry.getValue().size()));
                        break;
                    }
                    LOG.debug(format("(!! %s !!) %s %s - %s %s for %s %s", trade.getOrderType(), trade.getPrice(), pairObj.getSecondCurrency(), trade.getQuantity(),
                            pairObj.getFirstCurrency(), trade.getTotalAmount(), pairObj.getSecondCurrency()));
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        LOG.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    }

    @ManagedAttribute
    public double getMinimumBtcConsider() {
        return minimumBtcConsider;
    }

    @ManagedAttribute
    public void setMinimumBtcConsider(final double minimumBtcConsider) {
        this.minimumBtcConsider = minimumBtcConsider;
    }

    @ManagedAttribute
    public int getMaximumRecords() {
        return maximumRecords;
    }

    @ManagedAttribute
    public void setMaximumRecords(int maximumRecords) {
        this.maximumRecords = maximumRecords;
    }
}
