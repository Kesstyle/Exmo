package by.minsk.kes.exmo.observer.task;

import by.minsk.kes.exmo.legacy.ExmoRestAdapter;
import by.minsk.kes.exmo.model.api.ExTicker;
import by.minsk.kes.exmo.model.domain.KesTickerInfo;
import by.minsk.kes.exmo.transform.converter.KesTickerConverter;
import by.minsk.kes.exmo.transform.parser.ExParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@ManagedResource("Manages ticker functionality")
public class TickerTimerTask extends KesTimerTask {

    @Value("${exmo.ticket.operation}")
    private String operation;

    @Value("${exmo.ticker.pairs}")
    private String pairs;

    @Autowired
    private ExmoRestAdapter exmoRestAdapter;

    @Autowired
    private ExParser exParser;

    @Autowired
    private KesTickerConverter kesTickerConverter;

    @Override
    public void run() {
        final String json = exmoRestAdapter.get(getBaseUrl(), operation, getParamsMap());
        final Map<String, ExTicker> exTickerMap = exParser.buildTickerFromJson(json);
        final Map<String, KesTickerInfo> kesTickerMap = kesTickerConverter.convertMap(exTickerMap);

    }

    @Override
    protected Map<String, String> getParamsMap() {
        return null;
    }
}
