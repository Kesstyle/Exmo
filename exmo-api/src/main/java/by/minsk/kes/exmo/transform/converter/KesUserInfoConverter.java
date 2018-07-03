package by.minsk.kes.exmo.transform.converter;

import by.minsk.kes.exmo.model.api.ExUserInfo;
import by.minsk.kes.exmo.model.domain.KesUserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class KesUserInfoConverter extends GenericStringKeyConverter<KesUserInfo, ExUserInfo> {

    @Override
    public KesUserInfo convert(final ExUserInfo source) {
        if (source == null) {
            return null;
        }
        final KesUserInfo target = new KesUserInfo();
        BeanUtils.copyProperties(source, target);
        target.setServerDate(getDateFromUnix(source.getServerDate(), false));
        return target;
    }
}
