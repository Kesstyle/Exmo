package by.minsk.kes.exmo.main;

import by.minsk.kes.exmo.configuration.AppConfig;
import by.minsk.kes.exmo.controller.delegate.ExmoDelegate;
import by.minsk.kes.exmo.legacy.ExmoRestAdapter;
import by.minsk.kes.exmo.model.api.ExCancelledOrder;
import by.minsk.kes.exmo.model.domain.KesOrder;
import by.minsk.kes.exmo.observer.task.KesTimerTask;
import by.minsk.kes.exmo.observer.task.OrderBookObserveTimerTask;
import by.minsk.kes.exmo.observer.task.TickerTimerTask;
import by.minsk.kes.exmo.observer.task.TradesObserveTimerTask;
import by.minsk.kes.exmo.transform.converter.KesCancelledOrderConverter;
import by.minsk.kes.exmo.transform.parser.ExParser;
import by.minsk.model.AuthPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    private static ExmoDelegate delegate;
    private static ScheduledExecutorService executor;

    public static void main(String[] args) {
        try {
            delegate = getExmoDelegate();
            executor = getExecutor();
            userInfo();
            cancelledOrders();
            requiredAmount();
            trades();
            orders();
            ticker();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private static void ticker() {
        executor.scheduleAtFixedRate(getTickerTask(), 10, 30, TimeUnit.SECONDS);
    }

    private static void orders() {
        executor.scheduleAtFixedRate(getOrdersTask(), 0, 30, TimeUnit.SECONDS);
    }

    private static void trades() {
        executor.scheduleAtFixedRate(getTradesTask(), 5, 30, TimeUnit.SECONDS);
    }

    private static void userInfo() {
        String result = delegate.getUserInfo();
        System.out.println(result);
    }

    private static void cancelledOrders() {
        final List<ExCancelledOrder> exCancelledOrderList = delegate.getCancelledOrders(2, 0);
        System.out.println(exCancelledOrderList);
        KesCancelledOrderConverter converter = new KesCancelledOrderConverter();
        final List<KesOrder> kesOrderList = converter.convert(exCancelledOrderList);
        System.out.println(kesOrderList);
    }

    private static void requiredAmount() {
        String result = delegate.getRequiredAmount("BTC_USD", BigDecimal.ONE);
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

    private static ExmoDelegate getExmoDelegate() {
        return (ExmoDelegate) context.getBean("exmoDelegate");
    }

    private static ScheduledExecutorService getExecutor() {return Executors.newScheduledThreadPool(15);}
}
