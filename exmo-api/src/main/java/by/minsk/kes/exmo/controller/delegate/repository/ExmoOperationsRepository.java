package by.minsk.kes.exmo.controller.delegate.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class ExmoOperationsRepository {

    @Value("${exmo.ticket.operation}")
    private String tickerOperation;

    @Value("${exmo.orders.operation}")
    private String ordersOperation;

    @Value("${exmo.user.order.cancelled.operation}")
    private String cancelledOrdersOperation;

    @Value("${exmo.trades.operation}")
    private String tradesOperation;

    @Value("${exmo.user.info.operation}")
    private String userInfoOperation;

    @Value("${exmo.user.required.amount.operation}")
    private String requiredAmountOperation;

    public String getCancelledOrdersOperation() {
        return cancelledOrdersOperation;
    }

    public String getTickerOperation() {
        return tickerOperation;
    }

    public String getOrdersOperation() {
        return ordersOperation;
    }

    public String getTradesOperation() {
        return tradesOperation;
    }

    public String getUserInfoOperation() {
        return userInfoOperation;
    }

    public String getRequiredAmountOperation() {
        return requiredAmountOperation;
    }
}
