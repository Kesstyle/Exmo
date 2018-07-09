package by.minsk.kes.coinmarket.controller.delegate.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class CoinMarketOperationsRepository {

    @Value("${coinmarket.ticket.operation}")
    private String tickerOperation;

    public String getTickerOperation() {
        return tickerOperation;
    }

    public void setTickerOperation(String tickerOperation) {
        this.tickerOperation = tickerOperation;
    }
}
