package by.minsk.kes.coinmarket.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class CoinMarketOperationsRepository {

    @Value("${coinmarket.ticket.operation}")
    private String tickerOperation;

    @Value("${coinmarket.listings.operation}")
    private String listingsOperation;

    @Value("${coinmarket.auth.quotes}")
    private String tickerAuthOperation;

    @Value("${coinmarket.auth.map}")
    private String mapAuthOperation;

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

    public String getTickerAuthOperation() {
        return tickerAuthOperation;
    }

    public void setTickerAuthOperation(final String tickerAuthOperation) {
        this.tickerAuthOperation = tickerAuthOperation;
    }

    public String getMapAuthOperation() {
        return mapAuthOperation;
    }

    public void setMapAuthOperation(final String mapAuthOperation) {
        this.mapAuthOperation = mapAuthOperation;
    }
}
