package by.minsk.kes.exmo.observer.task;

import by.minsk.kes.exmo.legacy.ExmoRestAdapter;
import by.minsk.kes.exmo.transform.converter.KesUserOrderConverter;
import by.minsk.kes.exmo.transform.converter.PairConverter;
import by.minsk.kes.exmo.transform.parser.ExParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

public abstract class KesTimerTask implements Runnable {

    @Value("${exmo.baseUrl}")
    private String baseUrl;

    @Autowired
    protected ExmoRestAdapter exmoRestAdapter;

    @Autowired
    protected ExParser exParser;

    @Autowired
    protected PairConverter pairConverter;

    protected abstract Map<String, String> getParamsMap();

    public String getBaseUrl() {
        return baseUrl;
    }
}
