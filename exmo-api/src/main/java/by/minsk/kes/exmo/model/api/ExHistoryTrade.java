package by.minsk.kes.exmo.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExHistoryTrade extends ExTrade {

    @JsonProperty("pair")
    private String pair;

    @JsonProperty("order_id")
    private Long orderId;

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
