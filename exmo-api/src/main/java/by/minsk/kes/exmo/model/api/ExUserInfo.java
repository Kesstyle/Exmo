package by.minsk.kes.exmo.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * {"uid":1368213,"server_date":1530614800,"balances":
 * {"USD":"0.00000089","EUR":"0","RUB":"0.27974921","PLN":"0","UAH":"0","BTC":"0.00007809",
 * "LTC":"0","DOGE":"0.94909436","DASH":"0","ETH":"0","WAVES":"0.00000001","ZEC":"0","USDT":"0",
 * "XMR":"0","XRP":"755.31713865","KICK":"8.1960091","ETC":"19.69713282","BCH":"0","BTG":"10.13204687",
 * "EOS":"0","HBZ":"44452.02174216","BTCZ":"0","DXT":"2181.42902273"},
 * "reserved":{"USD":"0","EUR":"0","RUB":"0","PLN":"0","UAH":"0","BTC":"0","LTC":"0","DOGE":"0",
 * "DASH":"0","ETH":"0","WAVES":"0","ZEC":"0","USDT":"0","XMR":"0","XRP":"0","KICK":"0",
 * "ETC":"0","BCH":"0","BTG":"0","EOS":"0","HBZ":"54759.42614","BTCZ":"29940","DXT":"0"}}
 */
public class ExUserInfo implements Serializable {

    @JsonProperty("uid")
    private Long uid;

    @JsonProperty("server_date")
    private Long serverDate;

    @JsonProperty("balances")
    private Map<String, BigDecimal> balances;

    @JsonProperty("reserved")
    private Map<String, BigDecimal> orders;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getServerDate() {
        return serverDate;
    }

    public void setServerDate(Long serverDate) {
        this.serverDate = serverDate;
    }

    public Map<String, BigDecimal> getBalances() {
        return balances;
    }

    public void setBalances(Map<String, BigDecimal> balances) {
        this.balances = balances;
    }

    public Map<String, BigDecimal> getOrders() {
        return orders;
    }

    public void setOrders(Map<String, BigDecimal> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "ExUserInfo{" +
                "uid=" + uid +
                ", serverDate=" + serverDate +
                ", balances=" + balances +
                ", orders=" + orders +
                '}';
    }
}
