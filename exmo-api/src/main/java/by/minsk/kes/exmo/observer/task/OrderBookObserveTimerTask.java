package by.minsk.kes.exmo.observer.task;

import by.minsk.kes.exmo.model.api.ExOrderBook;
import by.minsk.kes.exmo.model.domain.KesUserOrder;
import by.minsk.kes.exmo.model.domain.Pair;
import by.minsk.kes.exmo.transform.converter.KesUserOrderConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("ordersTask")
@ManagedResource(description = "Manages orders observation")
public class OrderBookObserveTimerTask extends KesTimerTask {

    private static final Logger LOG = LoggerFactory.getLogger("UserOrders");

    private static final String ORDER_PARAMETER_PAIR = "pair";
    private static final String ORDER_PARAMETER_LIMIT = "limit";
    private static final String ORDER_PARAMETER_LIMIT_DEFAULT = "100";

    @Value("${exmo.currency.target:USD}")
    private String targetCurrency;

    @Value("${exmo.orders." + ORDER_PARAMETER_LIMIT + ":" + ORDER_PARAMETER_LIMIT_DEFAULT + "}")
    private int limit;

    @Autowired
    private KesUserOrderConverter kesUserOrderConverter;

    @Override
    public void startTask() {
        try {
            final Map<String, ExOrderBook> ordersMap = delegate.getOrders(getParamsMap());
            final Map<String, KesUserOrder> kesUserOrderMap = kesUserOrderConverter.convertMap(ordersMap);
            repository.setKesUserOrderMap(kesUserOrderMap);
            logOrders(kesUserOrderMap);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    protected Map<String, String> getParamsMap() {
        final Map<String, String> params = new HashMap<>();
        params.put(ORDER_PARAMETER_PAIR, getPairs());
        params.put(ORDER_PARAMETER_LIMIT, String.valueOf(getLimit()));
        return params;
    }

    private void logOrders(final Map<String, KesUserOrder> kesUserOrderMap) {
        LOG.debug("=============================================================================");
        for (final Map.Entry<String, KesUserOrder> entry : kesUserOrderMap.entrySet()) {
            final KesUserOrder userOrder = entry.getValue();
            final Pair pair = pairConverter.getFromString(entry.getKey());
            LOG.debug(String.format(" [%s] ==> %s %s (%s %s) - %s %s (%s %s)", pair.toString(), userOrder.getBuyTopPrice(), pair.getSecondCurrency(),
                    userOrder.getBuyOrders().get(0).getQuantity(), pair.getFirstCurrency(),
                    userOrder.getSellTopPrice(), pair.getSecondCurrency(), userOrder.getSellOrders().get(0).getQuantity(), pair.getFirstCurrency()));
        }
        LOG.debug("=============================================================================");
    }

    @ManagedAttribute
    public String getTargetCurrency() {
        return targetCurrency;
    }

    @ManagedAttribute
    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    @ManagedAttribute
    public int getLimit() {
        return limit;
    }

    @ManagedAttribute
    public void setLimit(int limit) {
        this.limit = limit;
    }

}
