package src.by.minsk.kes.exmo.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import src.by.minsk.kes.exmo.legacy.ExmoRestAdapter;
import src.by.minsk.kes.exmo.main.config.AppConfig;
import src.by.minsk.kes.exmo.model.ExCancelledOrder;
import src.by.minsk.kes.exmo.model.KesOrder;
import src.by.minsk.kes.exmo.observer.TradesObserveTimerTask;
import src.by.minsk.kes.exmo.transform.converter.KesCancelledOrderConverter;
import src.by.minsk.kes.exmo.transform.parser.ExOrderParser;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) {

    ExmoRestAdapter e = new ExmoRestAdapter("key", "secret");
    userInfo(e);
    cancelledOrders(e);
    trades(e);
  }

  private static void trades(final ExmoRestAdapter e) {
    final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    executor.scheduleAtFixedRate(getTradesTask(), 1, 30, TimeUnit.SECONDS);
  }

  private static void userInfo(final ExmoRestAdapter e) {
    String result = e.post("user_info", null);
    System.out.println(result);
  }

  private static TradesObserveTimerTask getTradesTask() {
    final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    final TradesObserveTimerTask task = (TradesObserveTimerTask) context.getBean("tradesTask");
    return task;
  }

  private static void cancelledOrders(final ExmoRestAdapter e) {
    String result = e.post("user_cancelled_orders", new HashMap<String, String>() {{
      put("limit", "2");
      put("offset", "0");
    }});
    System.out.println(result);
    ExOrderParser parser = new ExOrderParser();
    final List<ExCancelledOrder> exCancelledOrderList = parser.buildCancelledOrderFromJson(result);
    System.out.println(exCancelledOrderList);
    KesCancelledOrderConverter converter = new KesCancelledOrderConverter();
    final List<KesOrder> kesOrderList = converter.convert(exCancelledOrderList);
    System.out.println(kesOrderList);
  }
}
