package by.minsk.kes.coinmarket.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class CoinMarketOperationsRepository {

    @Value("${coinmarket.ticket.operation}")
    private String tickerOperation;

    @Value("${coinmarket.listings.operation}")
    private String listingsOperation;

    public String getTickerOperation() {
        return tickerOperation;
    }

    public void setTickerOperation(String tickerOperation) {
        this.tickerOperation = tickerOperation;
    }

    public String getListingsOperation() {
        return listingsOperation;
    }

    public void setListingsOperation(String listingsOperation) {
        this.listingsOperation = listingsOperation;
    }
}
