package by.minsk.kes.coinmarket.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoinMarketStatus extends CoinMarketModel {

    @JsonProperty("timestamp")
    private String lastUpdated;

    @JsonProperty("error_code")
    private Long errorCode;

    @JsonProperty("error_message")
    private String errorMsg;

    private Short elapsed;

    @JsonProperty("credit_count")
    private Short creditCount;

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(final String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(final Long errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(final String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Short getElapsed() {
        return elapsed;
    }

    public void setElapsed(final Short elapsed) {
        this.elapsed = elapsed;
    }

    public Short getCreditCount() {
        return creditCount;
    }

    public void setCreditCount(final Short creditCount) {
        this.creditCount = creditCount;
    }
}
