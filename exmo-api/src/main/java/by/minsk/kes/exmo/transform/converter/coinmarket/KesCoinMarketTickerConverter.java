package by.minsk.kes.exmo.transform.converter.coinmarket;

import by.minsk.kes.coinmarket.model.CoinMarketTicker;
import by.minsk.kes.exmo.model.domain.coinmarket.KesCoinMarketTicker;
import by.minsk.kes.exmo.transform.converter.GenericStringKeyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KesCoinMarketTickerConverter extends GenericStringKeyConverter<KesCoinMarketTicker, CoinMarketTicker> {

    @Autowired
    private KesCoinMarketTickerQuoteConverter quoteConverter;

    @Override
    public KesCoinMarketTicker convert(final CoinMarketTicker source) {
        if (source == null) {
            return null;
        }
        final KesCoinMarketTicker kesTicker = new KesCoinMarketTicker();
        kesTicker.setCirculatingVolume(source.getCirculatingSupply());
        kesTicker.setCode(source.getCode());
        kesTicker.setId(source.getId());
        kesTicker.setMaxSupply(source.getMaxSupply());
        kesTicker.setName(source.getName());
        kesTicker.setRank(source.getRank());
        kesTicker.setTotalSupply(source.getTotalSupply());
        kesTicker.setMaxSupply(source.getMaxSupply());
        kesTicker.setLastUpdated(getDateFromString(source.getLastUpdated()));
        kesTicker.setQuotes(quoteConverter.convertMap(source.getQuotes()));
        return kesTicker;
    }
}
