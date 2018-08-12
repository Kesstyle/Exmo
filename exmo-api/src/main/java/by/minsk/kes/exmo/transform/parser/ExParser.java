package by.minsk.kes.exmo.transform.parser;

import by.minsk.kes.exmo.model.api.*;
import by.minsk.kes.exmo.transform.parser.exception.ExParserException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ExParser {

    private static final Logger LOG = LoggerFactory.getLogger(ExParser.class);

    private static final String EMPTY_JSON_ERROR = "Empty json!";

    @Autowired
    private ObjectMapper mapper;

    public List<ExCancelledOrder> buildCancelledOrderFromJson(final String json) {
        if (StringUtils.isBlank(json)) {
            throw new ExParserException(EMPTY_JSON_ERROR);
        }
        try {
            final TypeReference<List<ExCancelledOrder>> exUserOrdersRef = new TypeReference<List<ExCancelledOrder>>() {
            };
            return mapper.readValue(json, exUserOrdersRef);
        } catch (final IOException e) {
            LOG.error(json);
            throw new ExParserException(json);
        }
    }

    public Map<String, List<ExTrade>> buildTradeFromJson(final String json) {
        if (StringUtils.isBlank(json)) {
            throw new ExParserException(EMPTY_JSON_ERROR);
        }
        try {
            final TypeReference<Map<String, List<ExTrade>>> exUserOrdersRef = new TypeReference<Map<String, List<ExTrade>>>() {
            };
            return mapper.readValue(json, exUserOrdersRef);
        } catch (final IOException e) {
            LOG.error(json);
            throw new ExParserException(json);
        }
    }

    public Map<String, ExOrderBook> buildUserOrdersFromJson(final String json) {
        if (StringUtils.isBlank(json)) {
            throw new ExParserException(EMPTY_JSON_ERROR);
        }
        try {
            final TypeReference<Map<String, ExOrderBook>> exUserOrdersRef = new TypeReference<Map<String, ExOrderBook>>() {
            };
            return mapper.readValue(json, exUserOrdersRef);
        } catch (final IOException e) {
            LOG.error(json);
            throw new ExParserException(json);
        }
    }

    public Map<String, ExTicker> buildTickerFromJson(final String json) {
        if (StringUtils.isBlank(json)) {
            throw new ExParserException(EMPTY_JSON_ERROR);
        }
        try {
            final TypeReference<Map<String, ExTicker>> exUserOrdersRef = new TypeReference<Map<String, ExTicker>>() {
            };
            return mapper.readValue(json, exUserOrdersRef);
        } catch (final IOException e) {
            LOG.error(json);
            throw new ExParserException(json);
        }
    }

    public Map<String, List<ExHistoryTrade>> buildTradesHistoryFromJson(final String json) {
        if (StringUtils.isBlank(json)) {
            throw new ExParserException(EMPTY_JSON_ERROR);
        }
        try {
            final TypeReference<Map<String, List<ExHistoryTrade>>> exUserOrdersRef = new TypeReference<Map<String, List<ExHistoryTrade>>>() {
            };
            return mapper.readValue(json, exUserOrdersRef);
        } catch (final IOException e) {
            LOG.error(json);
            LOG.error(e.getMessage());
            throw new ExParserException(json);
        }
    }

    public ExUserInfo buildUserInfoFromJson(final String json) {
        if (StringUtils.isBlank(json)) {
            throw new ExParserException(EMPTY_JSON_ERROR);
        }
        try {
            return mapper.readValue(json, ExUserInfo.class);
        } catch (final IOException e) {
            LOG.error(json);
            throw new ExParserException(json);
        }
    }
}
