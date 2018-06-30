package by.minsk.kes.exmo.observer;

import by.minsk.kes.exmo.model.domain.*;
import by.minsk.kes.exmo.transform.converter.PairConverter;
import by.minsk.kes.exmo.transform.converter.StatisticsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TradesObserver {

    private Date lastCheckDate;

    @Autowired
    private StatisticsBuilder statisticsBuilder;

    @Autowired
    private PairConverter pairConverter;

    public List<KesTradingStatistics> analyze(final Map<String, List<KesOrder>> kesOrders, final Date currentDate) {
        if (kesOrders == null) {
            return null;
        }
        if (lastCheckDate == null) {
            lastCheckDate = currentDate;
            return null;
        } else {
            final List<KesTradingStatistics> tradingList = new ArrayList<>();
            for (final Map.Entry<String, List<KesOrder>> entry : kesOrders.entrySet()) {
                final String pair = entry.getKey();
                final Pair pairObj = pairConverter.getFromString(pair);

                final KesTradingStatisticsInfo buyStatistics = statisticsBuilder.initStatistics(KesOrderType.BUY, pairObj);
                final KesTradingStatisticsInfo sellStatistics = statisticsBuilder.initStatistics(KesOrderType.SELL, pairObj);

                final List<KesOrder> orders = getOrdersBetweenDates(lastCheckDate, currentDate, entry.getValue());
                if (orders != null) {
                    for (final KesOrder order : orders) {
                        if (order.getOrderType().equals(KesOrderType.BUY)) {
                            addToStatistic(buyStatistics, 1, order.getQuantity(), order.getTotalAmount());
                        } else if (order.getOrderType().equals(KesOrderType.SELL)) {
                            addToStatistic(sellStatistics, 1, order.getQuantity(), order.getTotalAmount());
                        }
                    }
                }
                final KesTradingStatistics stats = new KesTradingStatistics();
                stats.setBuy(buyStatistics);
                stats.setSell(sellStatistics);
                stats.setPair(pairObj);
                tradingList.add(stats);
            }
            lastCheckDate = currentDate;
            return tradingList;
        }
    }

    private void addToStatistic(final KesTradingStatisticsInfo statistics,
                                final int count, final BigDecimal quantity, final BigDecimal amount) {
        statistics.setNewTradesCount(statistics.getNewTradesCount() + count);
        statistics.setTradeQuantity(statistics.getTradeQuantity().add(quantity));
        statistics.setTradeAmount(statistics.getTradeAmount().add(amount));
    }

    private List<KesOrder> getOrdersBetweenDates(final Date start, final Date end, final List<KesOrder> orders) {
        if (orders == null) {
            return null;
        }
        final List<KesOrder> result = new ArrayList<>();
        for (final KesOrder order : orders) {
            if (order.getDate().before(end) && order.getDate().after(start)) {
                result.add(order);
            }
        }
        return result;
    }

}
