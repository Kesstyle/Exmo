package by.minsk.kes.exmo.observer.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

public abstract class KesTimerTask implements Runnable {

    @Value("${exmo.baseUrl}")
    private String baseUrl;

    protected abstract Map<String, String> getParamsMap();

    public String getBaseUrl() {
        return baseUrl;
    }
}
