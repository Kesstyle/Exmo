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

    public static void main(String[] args) {
        try {
            delegate = getExmoDelegate();
            regularMonitoring();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private static void regularMonitoring() {
        userInfo();
        requiredAmount();
        trades();
        orders();
        ticker();
        potentialTrades();
        coinMarketTicker();
        userTradesHistory();
    }

    private static void ticker() {
        getExecutor().scheduleAtFixedRate(getTickerTask(), 0, 30, TimeUnit.SECONDS);
    }

    private static void userInfo() {
        getExecutor().scheduleAtFixedRate(getUserInfoTask(), 1, 30, TimeUnit.SECONDS);
    }

    private static void orders() {
        getExecutor().scheduleAtFixedRate(getOrdersTask(), 2, 30, TimeUnit.SECONDS);
    }

    private static void trades() {
        getExecutor().scheduleAtFixedRate(getTradesTask(), 3, 30, TimeUnit.SECONDS);
    }

    private static void userTradesHistory() {
        getExecutor().scheduleAtFixedRate(getUserTradesHistoryTask(), 4, 90, TimeUnit.SECONDS);
    }

    private static void potentialTrades() {
        getExecutor().scheduleAtFixedRate(getPotentialTradingTask(), 5, 30, TimeUnit.SECONDS);
    }

    private static void coinMarketTicker() {
        getExecutor().scheduleAtFixedRate(getCoinMarketTickerTask(), 0, 20, TimeUnit.SECONDS);
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

    private static UserTradesHistoryTask getUserTradesHistoryTask() {
        return (UserTradesHistoryTask) context.getBean("userTradesHistoryTask");
    }

    private static CoinMarketTickerTimerTask getCoinMarketTickerTask() {
        return (CoinMarketTickerTimerTask) context.getBean("coinMarketTickerTask");
    }

    private static ExmoDelegate getExmoDelegate() {
        return (ExmoDelegate) context.getBean("exmoDelegate");
    }

    private static ScheduledExecutorService getExecutor() {
        return Executors.newScheduledThreadPool(1);
    }
}
