package by.minsk.kes.exmo.transform.converter.coinmarket;

import by.minsk.kes.coinmarket.model.CoinMarketTickerQuote;
import by.minsk.kes.exmo.model.domain.coinmarket.KesCoinMarketTickerQuote;
import by.minsk.kes.exmo.transform.converter.GenericStringKeyConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KesCoinMarketTickerQuoteConverter extends GenericStringKeyConverter<KesCoinMarketTickerQuote, CoinMarketTickerQuote> {

    @Override
    public KesCoinMarketTickerQuote convert(final CoinMarketTickerQuote source) {
        if (source == null) {
            return null;
        }
        final KesCoinMarketTickerQuote kesQuote = new KesCoinMarketTickerQuote();
        BeanUtils.copyProperties(source, kesQuote);
        return kesQuote;
    }

    public List<KesCoinMarketTickerQuote> convert(final Iterable<CoinMarketTickerQuote> source) {
        if (source == null) {
            return null;
        }
        final List<KesCoinMarketTickerQuote> kesQuoteList = new ArrayList<>();
        for (final CoinMarketTickerQuote sourceQuote : source) {
            kesQuoteList.add(convert(sourceQuote));
        }
        return kesQuoteList;
    }
}
