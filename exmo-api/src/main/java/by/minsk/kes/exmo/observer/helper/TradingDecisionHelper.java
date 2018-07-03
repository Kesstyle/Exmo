package by.minsk.kes.exmo.observer.helper;

import by.minsk.kes.exmo.model.domain.KesUserAsk;
import by.minsk.kes.exmo.model.domain.Pair;
import by.minsk.kes.exmo.observer.helper.model.Trading;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class TradingDecisionHelper {

    public Trading getTrading(final BigDecimal quantity, final List<KesUserAsk> orders, final Pair pair) {
        final Trading trading = new Trading();
        if (CollectionUtils.isEmpty(orders)) {
            return trading;
        }
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal quantityCollected = BigDecimal.ZERO;
        BigDecimal quantityRemaining = quantity;
        for (final KesUserAsk order : orders) {
            final BigDecimal orderQuantity = order.getQuantity();
            if (orderQuantity.compareTo(quantityRemaining) >= 0) {
                quantityCollected = quantityCollected.add(quantityRemaining);
                totalAmount = totalAmount.add(quantityRemaining.multiply(order.getPrice()));
                break;
            } else {
                quantityCollected = quantityCollected.add(orderQuantity);
                totalAmount = totalAmount.add(order.getAmount());
                quantityRemaining = quantityRemaining.subtract(orderQuantity);
            }
        }
        trading.setAmount(totalAmount);
        trading.setQuantity(quantityCollected);
        trading.setPair(pair);
        if (quantityCollected.doubleValue() == 0) {
            trading.setAvgPrice(BigDecimal.ZERO);
        } else {
            trading.setAvgPrice(totalAmount.divide(quantityCollected, 10, RoundingMode.HALF_UP));
        }
        return trading;
    }

    public Trading getTrading(final BigDecimal quantity, final List<KesUserAsk> orders, final Pair pair, final BigDecimal percent) {
        return getTrading(quantity.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP).multiply(percent), orders, pair);
    }
}
