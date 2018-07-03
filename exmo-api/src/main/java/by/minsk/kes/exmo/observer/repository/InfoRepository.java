package by.minsk.kes.exmo.observer.repository;

import by.minsk.kes.exmo.model.domain.*;
import by.minsk.kes.exmo.observer.helper.model.Trading;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class InfoRepository {

    private volatile Map<String, KesUserOrder> kesUserOrderMap;
    private volatile Map<String, KesTickerInfo> kesTickerMap;
    private volatile Map<String, List<KesOrder>> kesUserTrades;
    private volatile List<KesTradingStatistics> kesUserTradingStatistics;
    private volatile KesUserInfo kesUserInfo;
    private volatile Map<String, Trading> potentialSells;

    public Map<String, KesUserOrder> getKesUserOrderMap() {
        return MapUtils.unmodifiableMap(kesUserOrderMap);
    }

    public void setKesUserOrderMap(final Map<String, KesUserOrder> kesUserOrderMap) {
        this.kesUserOrderMap = new ConcurrentHashMap<>(kesUserOrderMap);
    }

    public Map<String, KesTickerInfo> getKesTickerMap() {
        return MapUtils.unmodifiableMap(kesTickerMap);
    }

    public void setKesTickerMap(final Map<String, KesTickerInfo> kesTickerMap) {
        this.kesTickerMap = new ConcurrentHashMap<>(kesTickerMap);
    }

    public Map<String, List<KesOrder>> getKesUserTrades() {
        return MapUtils.unmodifiableMap(kesUserTrades);
    }

    public void setKesUserTrades(final Map<String, List<KesOrder>> kesUserTrades) {
        this.kesUserTrades = new ConcurrentHashMap<>(kesUserTrades);
    }

    public List<KesTradingStatistics> getKesUserTradingStatistics() {
        return ListUtils.unmodifiableList(kesUserTradingStatistics);
    }

    public void setKesUserTradingStatistics(final List<KesTradingStatistics> kesUserTradingStatistics) {
        this.kesUserTradingStatistics = new CopyOnWriteArrayList<>(kesUserTradingStatistics);
    }

    public KesUserInfo getKesUserInfo() {
        if (kesUserInfo == null) {
            return null;
        }
        try {
            return kesUserInfo.clone();
        } catch (CloneNotSupportedException e) {
            return kesUserInfo;
        }
    }

    public void setKesUserInfo(KesUserInfo kesUserInfo) {
        this.kesUserInfo = kesUserInfo;
    }

    public Map<String, Trading> getPotentialSells() {
        return MapUtils.unmodifiableMap(potentialSells);
    }

    public void setPotentialSells(final Map<String, Trading> potentialSells) {
        this.potentialSells = new ConcurrentHashMap<>(potentialSells);
    }
}
