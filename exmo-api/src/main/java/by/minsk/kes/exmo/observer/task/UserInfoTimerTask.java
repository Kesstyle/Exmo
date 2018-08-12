package by.minsk.kes.exmo.observer.task;

import by.minsk.kes.exmo.model.api.ExUserInfo;
import by.minsk.kes.exmo.model.domain.KesUserInfo;
import by.minsk.kes.exmo.transform.converter.KesUserInfoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("userInfoTask")
public class UserInfoTimerTask extends KesTimerTask {

    @Autowired
    private KesUserInfoConverter kesUserInfoConverter;

    protected Map<String, String> getParamsMap() {
        return null;
    }

    @Override
    public void startTask() {
        final ExUserInfo exUserInfo = delegate.getUserInfo(getParamsMap());
        final KesUserInfo kesUserInfo = kesUserInfoConverter.convert(exUserInfo);
        repository.setKesUserInfo(kesUserInfo);
    }
}
