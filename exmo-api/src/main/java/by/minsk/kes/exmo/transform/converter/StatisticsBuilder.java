package by.minsk.kes.exmo.transform.converter;

import by.minsk.kes.exmo.model.KesOrderType;
import by.minsk.kes.exmo.model.KesTradingStatisticsInfo;
import by.minsk.kes.exmo.model.Pair;
import org.springframework.stereotype.Service;

@Service
public class StatisticsBuilder {

    public KesTradingStatisticsInfo initStatistics(final KesOrderType orderType, final Pair pair) {
        final KesTradingStatisticsInfo info = new KesTradingStatisticsInfo();
        info.setOrdersType(orderType);
        info.setPair(pair);
        return info;
    }

}
