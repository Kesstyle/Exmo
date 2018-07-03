package by.minsk.kes.exmo.observer.task;

import by.minsk.kes.exmo.controller.delegate.ExmoDelegate;
import by.minsk.kes.exmo.transform.converter.PairConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public abstract class KesTimerTask implements Runnable {

    @Autowired
    protected ExmoDelegate delegate;

    @Autowired
    protected PairConverter pairConverter;

    protected abstract Map<String, String> getParamsMap();
}
