package by.minsk.kes.exmo.transform.converter;

import by.minsk.kes.exmo.model.api.ExHistoryTrade;
import by.minsk.kes.exmo.model.domain.KesOrderType;
import by.minsk.kes.exmo.model.domain.KesUserTradesHistory;
import org.springframework.stereotype.Service;

@Service
public class KesTradesHistoryConverter extends GenericStringKeyConverter<KesUserTradesHistory, ExHistoryTrade> {

    @Override
    public KesUserTradesHistory convert(final ExHistoryTrade source) {
        if (source == null) {
            return null;
        }
        final KesUserTradesHistory kesHistoryTrade = new KesUserTradesHistory();
        kesHistoryTrade.setId(source.getTradeId());
        kesHistoryTrade.setOrderType(KesOrderType.getTypeFromString(source.getOrderType()));
        kesHistoryTrade.setPrice(source.getPrice());
        kesHistoryTrade.setQuantity(source.getQuantity());
        kesHistoryTrade.setTotalAmount(source.getAmount());
        kesHistoryTrade.setOrderId(source.getOrderId());
        kesHistoryTrade.setDate(getDateFromUnix(source.getUnixDate(), false));
        return kesHistoryTrade;
    }
}
