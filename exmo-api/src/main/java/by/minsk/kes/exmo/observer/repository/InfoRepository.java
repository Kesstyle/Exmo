package by.minsk.kes.exmo.observer.repository;

import by.minsk.kes.exmo.model.domain.KesOrder;
import by.minsk.kes.exmo.model.domain.KesTickerInfo;
import by.minsk.kes.exmo.model.domain.KesTradingStatistics;
import by.minsk.kes.exmo.model.domain.KesUserOrder;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class InfoRepository {

    private Map<String, KesUserOrder> kesUserOrderMap;
    private Map<String, KesTickerInfo> kesTickerMap;
    private Map<String, List<KesOrder>> kesOrders;
    private List<KesTradingStatistics> statistics;

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

    public Map<String, List<KesOrder>> getKesOrders() {
        return MapUtils.unmodifiableMap(kesOrders);
    }

    public void setKesOrders(final Map<String, List<KesOrder>> kesOrders) {
        this.kesOrders = new ConcurrentHashMap<>(kesOrders);
    }

    public List<KesTradingStatistics> getStatistics() {
        return ListUtils.unmodifiableList(statistics);
    }

    public void setStatistics(final List<KesTradingStatistics> statistics) {
        this.statistics = new CopyOnWriteArrayList<>(statistics);
    }
}
