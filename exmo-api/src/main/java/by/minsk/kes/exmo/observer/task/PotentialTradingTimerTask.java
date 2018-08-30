package by.minsk.kes.exmo.observer.task;

import by.minsk.kes.exmo.model.domain.KesUserAsk;
import by.minsk.kes.exmo.model.domain.KesUserInfo;
import by.minsk.kes.exmo.model.domain.KesUserOrder;
import by.minsk.kes.exmo.model.domain.Pair;
import by.minsk.kes.exmo.model.domain.coinmarket.KesCoinMarketTickerQuote;
import by.minsk.kes.exmo.observer.helper.TradingDecisionHelper;
import by.minsk.kes.exmo.observer.helper.model.Trading;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("potentialTradingTask")
@ManagedResource(description = "Manages potential trading functionality")
public class PotentialTradingTimerTask extends KesTimerTask {

    private static final Logger LOG = LoggerFactory.getLogger("PotentialTrades");

    @Value("${exmo.potential.trading.countorders:true}")
    private boolean countOrders;

    @Value("${exmo.potential.trading.percent:100}")
    private int percent;

    @Autowired
    private TradingDecisionHelper tradingHelper;

    @Override
    public void startTask() {
        final KesUserInfo userInfo = repository.getKesUserInfo();
        if (userInfo == null) {
            return;
        }
        final Map<String, BigDecimal> balances = userInfo.getBalances();
        if (MapUtils.isEmpty(balances)) {
            return;
        }
        final Map<String, BigDecimal> orders = userInfo.getOrders();
        final Map<String, BigDecimal> orderedBalances = addOrders(balances, orders);

        final Map<String, KesUserOrder> userOrders = repository.getKesUserOrderMap();
        if (MapUtils.isEmpty(userOrders)) {
            return;
        }
        final Map<String, Trading> potentialSells = new HashMap<>();
        userOrders.entrySet().stream().forEach(entry -> {
            final Pair pair = pairConverter.getFromString(entry.getKey());
            final List<KesUserAsk> buyAsks = entry.getValue().getBuyOrders();
            if (CollectionUtils.isNotEmpty(buyAsks)) {
                potentialSells.put(pair.toString(), tradingHelper.getTrading(orderedBalances.get(pair.getFirstCurrency()), buyAsks, pair, BigDecimal.valueOf(percent)));
            }
        });

        // Add coin market value
        final Map<String, Map<String, KesCoinMarketTickerQuote>> coinMarketTicker = repository.getCoinMarketTickerInfo();
        potentialSells.entrySet().stream().forEach(sell -> sell.getValue().setValueToCompare(getCoinMarketPrice(coinMarketTicker, sell.getKey())));

        final Map<String, Trading> sorterMap = potentialSells.entrySet().stream().sorted(Map.Entry.<String, Trading>comparingByValue((v1, v2) ->
                {
                    if (v2.percentDiff() == null) return -1;
                    if (v1.percentDiff() == null) return 1;
                    return v1.percentDiff().compareTo(v2.percentDiff());
                }
        )).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
        repository.setPotentialSells(sorterMap);
        logPotentialTrades(sorterMap, coinMarketTicker);
    }

    private void logPotentialTrades(final Map<String, Trading> potentialSells, final Map<String, Map<String, KesCoinMarketTickerQuote>> coinMarketTicker) {
        if (MapUtils.isEmpty(potentialSells)) {
            return;
        }
        LOG.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        try {
            potentialSells.entrySet().stream().forEach(sell -> LOG.debug(String.format("%s - %s", sell.getKey(), sell.getValue().toString())));
        } catch (final Exception e) {
            e.printStackTrace();
        }
        LOG.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    }

    private BigDecimal getCoinMarketPrice(final Map<String, Map<String, KesCoinMarketTickerQuote>> coinMarketTicker, final String pairStr) {
        if (coinMarketTicker == null) {
            return null;
        }
        final Pair pair = pairConverter.getFromString(pairStr);
        if (pair == null || StringUtils.isBlank(pair.getFirstCurrency()) || StringUtils.isBlank(pair.getSecondCurrency())) {
            return null;
        }
        final String currency = pair.getFirstCurrency();
        final String target = pair.getSecondCurrency();
        if (!coinMarketTicker.containsKey(currency)) {
            return null;
        }
        final Map<String, KesCoinMarketTickerQuote> coinMarketTickerForCurrency = coinMarketTicker.get(currency);
        if (coinMarketTickerForCurrency == null || !coinMarketTickerForCurrency.containsKey(target)) {
            return null;
        }
        final KesCoinMarketTickerQuote quote = coinMarketTickerForCurrency.get(target);
        if (quote == null || quote.getPrice() == null) {
            return null;
        }
        return quote.getPrice();
    }


    private Map<String, BigDecimal> addOrders(final Map<String, BigDecimal> balances, final Map<String, BigDecimal> orders) {
        Map<String, BigDecimal> resultBalances = new HashMap<>();
        if (countOrders && MapUtils.isNotEmpty(orders)) {
            balances.keySet().stream().forEach(key -> {
                if (orders.containsKey(key)) {
                    BigDecimal balance = balances.get(key);
                    resultBalances.put(key, balance.add(orders.get(key)));
                }
            });
        } else {
            return balances;
        }
        return resultBalances;
    }

    @ManagedAttribute
    public boolean isCountOrders() {
        return countOrders;
    }

    @ManagedAttribute
    public void setCountOrders(boolean countOrders) {
        this.countOrders = countOrders;
    }

    @ManagedAttribute
    public int getPercent() {
        return percent;
    }

    @ManagedAttribute
    public void setPercent(int percent) {
        this.percent = percent;
    }
}
