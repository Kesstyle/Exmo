package src.by.minsk.kes.exmo.transform;

import org.springframework.stereotype.Service;
import src.by.minsk.kes.exmo.model.ExOrder;
import src.by.minsk.kes.exmo.model.KesOrder;
import src.by.minsk.kes.exmo.model.KesOrderType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class KesOrderConverter {

  public KesOrder build(final ExOrder exOrder) {
    if (exOrder == null) {
      return null;
    }
    final KesOrder kesOrder = new KesOrder();
    kesOrder.setId(exOrder.getOrderId());
    kesOrder.setOrderType(KesOrderType.getTypeFromString(exOrder.getOrderType()));
    kesOrder.setPair(exOrder.getPair());
    kesOrder.setPrice(exOrder.getPrice());
    kesOrder.setQuantity(exOrder.getQuantity());
    kesOrder.setTotalAmount(exOrder.getAmount());
    kesOrder.setDate(getDateFromUnix(exOrder.getDateLong()));
    return kesOrder;
  }

  public List<KesOrder> build(final Collection<ExOrder> exOrderList) {
    if (exOrderList == null) {
      return null;
    }
    final List<KesOrder> kesOrderList = new ArrayList<>();
    for (final ExOrder exOrder : exOrderList) {
      kesOrderList.add(build(exOrder));
    }
    return kesOrderList;
  }

  private Date getDateFromUnix(final Long unixDate) {
    if (unixDate == null) {
      return null;
    }
    final Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(unixDate * 1000L);
    calendar.add(Calendar.HOUR_OF_DAY, new Date().getTimezoneOffset() / 60);
    return calendar.getTime();
  }
}
