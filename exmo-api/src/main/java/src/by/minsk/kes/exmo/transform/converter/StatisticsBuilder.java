package src.by.minsk.kes.exmo.transform.converter;

import org.springframework.stereotype.Service;
import src.by.minsk.kes.exmo.model.KesOrderType;
import src.by.minsk.kes.exmo.model.KesTradingStatisticsInfo;

@Service
public class StatisticsBuilder {

  public KesTradingStatisticsInfo initStatistics(final KesOrderType orderType) {
    final KesTradingStatisticsInfo info = new KesTradingStatisticsInfo();
    info.setOrdersType(orderType);
    return info;
  }

}
