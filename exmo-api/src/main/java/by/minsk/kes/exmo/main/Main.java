package by.minsk.kes.exmo.main;

import by.minsk.kes.exmo.configuration.AppConfig;
import by.minsk.kes.exmo.controller.delegate.ExmoDelegate;
import by.minsk.kes.exmo.observer.task.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
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
            requiredAmount();
            trades();
            orders();
            ticker();
            potentialTrades();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private static void ticker() {
        executor.scheduleAtFixedRate(getTickerTask(), 0, 30, TimeUnit.SECONDS);
    }

    private static void userInfo() {
        executor.scheduleAtFixedRate(getUserInfoTask(), 0, 30, TimeUnit.SECONDS);
    }

    private static void orders() {
        executor.scheduleAtFixedRate(getOrdersTask(), 0, 30, TimeUnit.SECONDS);
    }

    private static void trades() {
        executor.scheduleAtFixedRate(getTradesTask(), 0, 30, TimeUnit.SECONDS);
    }

    private static void potentialTrades() {
        executor.scheduleAtFixedRate(getPotentialTradingTask(), 5, 30, TimeUnit.SECONDS);
    }

    private static void requiredAmount() {
        String result = delegate.getRequiredAmount("BTC_USD", BigDecimal.ONE);
        System.out.println(result);

    }

    private static TradesObserveTimerTask getTradesTask() {
        return (TradesObserveTimerTask) context.getBean("tradesTask");
    }

    private static OrderBookObserveTimerTask getOrdersTask() {
        return (OrderBookObserveTimerTask) context.getBean("ordersTask");
    }

    private static TickerTimerTask getTickerTask() {
        return (TickerTimerTask) context.getBean("tickerTask");
    }

    private static UserInfoTimerTask getUserInfoTask() {
        return (UserInfoTimerTask) context.getBean("userInfoTask");
    }

    private static PotentialTradingTimerTask getPotentialTradingTask() {
        return (PotentialTradingTimerTask) context.getBean("potentialTradingTask");
    }

    private static ExmoDelegate getExmoDelegate() {
        return (ExmoDelegate) context.getBean("exmoDelegate");
    }

    private static ScheduledExecutorService getExecutor() {
        return Executors.newScheduledThreadPool(15);
    }
}
