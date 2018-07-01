package by.minsk.kes.exmo.main;

import by.minsk.kes.exmo.configuration.AppConfig;
import by.minsk.kes.exmo.legacy.ExmoRestAdapter;
import by.minsk.kes.exmo.model.api.ExCancelledOrder;
import by.minsk.kes.exmo.model.domain.KesOrder;
import by.minsk.kes.exmo.observer.task.KesTimerTask;
import by.minsk.kes.exmo.observer.task.OrderBookObserveTimerTask;
import by.minsk.kes.exmo.observer.task.TickerTimerTask;
import by.minsk.kes.exmo.observer.task.TradesObserveTimerTask;
import by.minsk.kes.exmo.transform.converter.KesCancelledOrderConverter;
import by.minsk.kes.exmo.transform.parser.ExParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    public static void main(String[] args) {
        try {
            ExmoRestAdapter e = new ExmoRestAdapter("key", "secret");
            userInfo(e);
            cancelledOrders(e);
            trades();
            orders();
            ticker();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private static void ticker() {
        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(getTickerTask(), 10, 30, TimeUnit.SECONDS);
    }

    private static void orders() {
        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(getOrdersTask(), 0, 30, TimeUnit.SECONDS);
    }

    private static void trades() {
        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(getTradesTask(), 5, 30, TimeUnit.SECONDS);
    }

    private static void userInfo(final ExmoRestAdapter e) {
        String result = e.post("user_info", null);
        System.out.println(result);
    }

    private static KesTimerTask getTradesTask() {
        return (TradesObserveTimerTask) context.getBean("tradesTask");
    }

    private static KesTimerTask getOrdersTask() {
        return (OrderBookObserveTimerTask) context.getBean("ordersTask");
    }

    private static TickerTimerTask getTickerTask() {
        return (TickerTimerTask) context.getBean("tickerTask");
    }

    private static void cancelledOrders(final ExmoRestAdapter e) {
        String result = e.post("user_cancelled_orders", new HashMap<String, String>() {{
            put("limit", "2");
            put("offset", "0");
        }});
        System.out.println(result);
        ExParser parser = new ExParser();
        final List<ExCancelledOrder> exCancelledOrderList = parser.buildCancelledOrderFromJson(result);
        System.out.println(exCancelledOrderList);
        KesCancelledOrderConverter converter = new KesCancelledOrderConverter();
        final List<KesOrder> kesOrderList = converter.convert(exCancelledOrderList);
        System.out.println(kesOrderList);
    }
}
