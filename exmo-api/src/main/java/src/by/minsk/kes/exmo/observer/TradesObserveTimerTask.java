package src.by.minsk.kes.exmo.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import src.by.minsk.kes.exmo.legacy.ExmoRestAdapter;
import src.by.minsk.kes.exmo.model.ExTrade;
import src.by.minsk.kes.exmo.model.KesOrder;
import src.by.minsk.kes.exmo.model.KesTradingStatistics;
import src.by.minsk.kes.exmo.transform.converter.KesTradeConverter;
import src.by.minsk.kes.exmo.transform.parser.ExOrderParser;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component("tradesTask")
@Scope("prototype")
public class TradesObserveTimerTask implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(TradesObserveTimerTask.class);

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
    String result = exmoRestAdapter.get();
    final Map<String, List<ExTrade>> exTrades = exOrderParser.buildTradeFromJson(result);
    final Map<String, List<KesOrder>> kesOrders = kesTradeConverter.convert(exTrades);
    final List<KesTradingStatistics> statistics = tradesObserver.analyze(kesOrders, new Date());
    if (statistics != null) {
      LOG.debug(statistics.toString() + "\r\n");
    }
  }

}
