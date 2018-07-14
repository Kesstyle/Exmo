package by.minsk.kes.coinmarket.transform.parser;

import by.minsk.kes.coinmarket.model.CoinMarketTicker;
import by.minsk.kes.coinmarket.model.CoinMarketTickerResponse;
import by.minsk.kes.coinmarket.transform.parser.exception.CoinMarketException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CoinMarketParser {

    private static final Logger LOG = LoggerFactory.getLogger(CoinMarketParser.class);

    private static final String EMPTY_JSON_ERROR = "Empty json!";

    @Autowired
    private ObjectMapper mapper;

    public Map<String, CoinMarketTicker> buildTickerFromJson(final String json) {
        if (StringUtils.isBlank(json)) {
            throw new CoinMarketException(EMPTY_JSON_ERROR);
        }
        try {
            final TypeReference<Map<String, CoinMarketTicker>> ticker = new TypeReference<Map<String, CoinMarketTicker>>() {
            };
            return mapper.readValue(json, ticker);
        } catch (final IOException e) {
            LOG.error(json);
            throw new CoinMarketException(json);
        }
    }

    public CoinMarketTickerResponse buildTickerDataFromJson(final String json) {
        if (StringUtils.isBlank(json)) {
            throw new CoinMarketException(EMPTY_JSON_ERROR);
        }
        try {
            final TypeReference<CoinMarketTickerResponse> tickerResponse = new TypeReference<CoinMarketTickerResponse>() {
            };
            return mapper.readValue(json, tickerResponse);
        } catch (final IOException e) {
            LOG.error(json);
            throw new CoinMarketException(json);
        }
    }

}
