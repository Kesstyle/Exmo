package by.minsk.kes.exmo.model.domain;

import org.apache.commons.collections.MapUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class KesUserInfo implements Serializable, Cloneable {

    private Long uid;
    private Date serverDate;
    private Map<String, BigDecimal> balances;
    private Map<String, BigDecimal> orders;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Date getServerDate() {
        return serverDate;
    }

    public void setServerDate(Date serverDate) {
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
    public KesUserInfo clone() throws CloneNotSupportedException {
        final KesUserInfo newObj = new KesUserInfo();
        newObj.setServerDate(serverDate);
        newObj.setUid(uid);
        newObj.setBalances(MapUtils.unmodifiableMap(balances));
        newObj.setOrders(MapUtils.unmodifiableMap(orders));
        return newObj;
    }

    @Override
    public String toString() {
        return "KesUserInfo{" +
                "uid=" + uid +
                ", serverDate=" + serverDate +
                ", balances=" + balances +
                ", orders=" + orders +
                '}';
    }
}
