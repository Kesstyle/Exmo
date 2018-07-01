package by.minsk.kes.exmo.transform.converter;

import by.minsk.kes.exmo.model.api.ExTicker;
import by.minsk.kes.exmo.model.domain.KesTickerInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class KesTickerConverter extends GenericStringKeyConverter<KesTickerInfo, ExTicker> {

    @Override
    public KesTickerInfo convert(ExTicker source) {
        if (source == null) {
            return null;
        }
        final KesTickerInfo result = new KesTickerInfo();
        BeanUtils.copyProperties(source, result);
        result.setLastUpdated(getDateFromUnix(source.getUpdated(), true));
        return result;
    }
}
