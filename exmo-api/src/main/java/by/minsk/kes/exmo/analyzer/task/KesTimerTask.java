package by.minsk.kes.exmo.analyzer.task;

import by.minsk.kes.exmo.controller.delegate.ExmoDelegate;
import by.minsk.kes.exmo.analyzer.repository.InfoRepository;
import by.minsk.kes.exmo.analyzer.repository.ResourceRepository;
import by.minsk.kes.exmo.transform.converter.PairConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    public final void run() {
        try {
            startTask();
        } catch (final Exception e) {
            fallback(e);
        }
    }

    public String getPairs() {
        return resourceRepository.getPairs();
    }

    public abstract void startTask();
}
