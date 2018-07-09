package by.minsk.kes.coinmarket.controller.delegate;

import by.minsk.kes.coinmarket.controller.delegate.repository.CoinMarketOperationsRepository;
import by.minsk.kes.coinmarket.legacy.CoinMarketCapRestAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("coinMarketDelegate")
public class CoinMarketDelegate {

    @Autowired
    private CoinMarketOperationsRepository coinMarketOperationsRepository;

    @Autowired
    private CoinMarketCapRestAdapter adapter;
}
