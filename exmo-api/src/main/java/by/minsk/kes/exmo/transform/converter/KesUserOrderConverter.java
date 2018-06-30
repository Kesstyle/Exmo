package by.minsk.kes.exmo.transform.converter;

import by.minsk.kes.exmo.model.api.ExOrderBook;
import by.minsk.kes.exmo.model.domain.KesUserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KesUserOrderConverter extends GenericStringKeyConverter<KesUserOrder, ExOrderBook> {

    @Autowired
    private KesUserAskConverter kesUserAskConverter;

    @Override
    public KesUserOrder convert(final ExOrderBook source) {
        if (source == null) {
            return null;
        }
        final KesUserOrder kesUserOrder = new KesUserOrder();
        kesUserOrder.setSellTopPrice(source.getSellTop());
        kesUserOrder.setSellTotalAmount(source.getSellAmount());
        kesUserOrder.setSellTotalQuantity(source.getSellQuantity());
        kesUserOrder.setBuyTopPrice(source.getBuyTop());
        kesUserOrder.setBuyTotalAmount(source.getBuyAmount());
        kesUserOrder.setBuyTotalQuantity(source.getBuyQuantity());
        kesUserOrder.setSellOrders(kesUserAskConverter.convert(source.getSellOrders()));
        kesUserOrder.setBuyOrders(kesUserAskConverter.convert(source.getBuyOrders()));
        return kesUserOrder;
    }

}
