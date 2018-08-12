package by.minsk.kes.coinmarket.repository;

import by.minsk.kes.coinmarket.model.CoinMarketCryptoInfo;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class CoinMarketResourceRepository {

    private List<CoinMarketCryptoInfo> cryptoInfoList;
    private Map<String, String> currencyIdMap;

    public List<CoinMarketCryptoInfo> getCryptoInfoList() {
        if (cryptoInfoList == null) {
            return null;
        }
        return ListUtils.unmodifiableList(cryptoInfoList);
    }

    public void setCryptoInfoList(List<CoinMarketCryptoInfo> cryptoInfoList) {
        this.cryptoInfoList = new CopyOnWriteArrayList<>(cryptoInfoList);
    }

    public Map<String, String> getCurrencyIdMap() {
        if (currencyIdMap == null) {
            return null;
        }
        return MapUtils.unmodifiableMap(currencyIdMap);
    }

    public void setCurrencyIdMap(Map<String, String> currencyIdMap) {
        this.currencyIdMap = new ConcurrentHashMap<>(currencyIdMap);
    }
}
