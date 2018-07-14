package by.minsk.kes.exmo.observer.task;

import by.minsk.kes.exmo.controller.delegate.ExmoDelegate;
import by.minsk.kes.exmo.observer.repository.InfoRepository;
import by.minsk.kes.exmo.observer.repository.ResourceRepository;
import by.minsk.kes.exmo.transform.converter.PairConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

public abstract class KesTimerTask implements Runnable {

    @Autowired
    protected ExmoDelegate delegate;

    @Autowired
    protected PairConverter pairConverter;

    @Autowired
    protected InfoRepository repository;

    @Autowired
    protected ResourceRepository resourceRepository;

    @ExceptionHandler(Exception.class)
    protected void fallback(final Exception exception) {
        exception.printStackTrace();
    }

    public String getPairs() {
        return resourceRepository.getPairs();
    }
}
