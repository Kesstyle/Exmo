package by.minsk.kes.exmo.transform.converter;

import by.minsk.kes.exmo.model.domain.KesUserAsk;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

@Service
public class KesUserAskConverter extends GenericStringKeyConverter<KesUserAsk, List<BigDecimal>> {

    @Override
    public KesUserAsk convert(List<BigDecimal> userOrderAsk) {
        if (CollectionUtils.isEmpty(userOrderAsk) || userOrderAsk.size() != 3) {
            return null;
        }
        final KesUserAsk kesUserAsk = new KesUserAsk();
        kesUserAsk.setPrice(userOrderAsk.get(0));
        kesUserAsk.setQuantity(userOrderAsk.get(1));
        kesUserAsk.setAmount(userOrderAsk.get(2));
        return kesUserAsk;
    }
}
