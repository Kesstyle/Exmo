package by.minsk.kes.exmo.transform.converter;

import by.minsk.kes.exmo.model.ExCancelledOrder;
import by.minsk.kes.exmo.model.KesOrder;
import by.minsk.kes.exmo.model.KesOrderType;
import org.springframework.stereotype.Service;

@Service
public class KesCancelledOrderConverter extends GenericStringKeyConverter<KesOrder, ExCancelledOrder> {

  public KesOrder convert(final ExCancelledOrder exCancelledOrder) {
    if (exCancelledOrder == null) {
      return null;
    }
    final KesOrder kesOrder = new KesOrder();
    kesOrder.setId(exCancelledOrder.getOrderId());
    kesOrder.setOrderType(KesOrderType.getTypeFromString(exCancelledOrder.getOrderType()));
    kesOrder.setPair(exCancelledOrder.getPair());
    kesOrder.setPrice(exCancelledOrder.getPrice());
    kesOrder.setQuantity(exCancelledOrder.getQuantity());
    kesOrder.setTotalAmount(exCancelledOrder.getAmount());
    kesOrder.setDate(getDateFromUnix(exCancelledOrder.getDateLong(), true));
    return kesOrder;
  }
}
