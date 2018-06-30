package by.minsk.kes.exmo.transform.converter;

import by.minsk.kes.exmo.model.ExTrade;
import by.minsk.kes.exmo.model.KesOrder;
import by.minsk.kes.exmo.model.KesOrderType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KesTradeConverter extends GenericStringKeyConverter<KesOrder, ExTrade> {

  final Logger LOG = LoggerFactory.getLogger(KesTradeConverter.class);

  @Override
  public KesOrder convert(final ExTrade source) {
    if (source == null) {
      return null;
    }
    final KesOrder kesOrder = new KesOrder();
    kesOrder.setId(source.getTradeId());
    kesOrder.setOrderType(KesOrderType.getTypeFromString(source.getOrderType()));
    kesOrder.setPrice(source.getPrice());
    kesOrder.setQuantity(source.getQuantity());
    kesOrder.setTotalAmount(source.getAmount());
    kesOrder.setDate(getDateFromUnix(source.getUnixDate(), false));
    return kesOrder;
  }

  @Override
  public Map<String, List<KesOrder>> convert(final Map<String, List<ExTrade>> source) {
    final Map<String, List<KesOrder>> result = super.convert(source);
    if (result != null) {
      for (final Map.Entry<String, List<KesOrder>> entry : result.entrySet()) {
        final List<KesOrder> orderList = entry.getValue();
        final String pair = entry.getKey();
        if (orderList != null) {
          for (final KesOrder order : orderList) {
            order.setPair(pair);
          }
        }
      }
    }
    return result;
  }

  private void updateElementsWithPairs(final Map<String, List<KesOrder>> source) {
    if (source != null) {
      for (final Map.Entry<String, List<KesOrder>> entry : source.entrySet()) {
        final List<KesOrder> orderList = entry.getValue();
        final String pair = entry.getKey();
        if (orderList != null) {
          for (final KesOrder order : orderList) {
            order.setPair(pair);
          }
        }
      }
    }
  }
}
