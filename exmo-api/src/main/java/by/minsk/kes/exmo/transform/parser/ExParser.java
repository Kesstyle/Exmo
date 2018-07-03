package by.minsk.kes.exmo.transform.parser;

import by.minsk.kes.exmo.model.api.ExCancelledOrder;
import by.minsk.kes.exmo.model.api.ExOrderBook;
import by.minsk.kes.exmo.model.api.ExTicker;
import by.minsk.kes.exmo.model.api.ExTrade;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ExParser {

    @Autowired
    private ObjectMapper mapper;

    public List<ExCancelledOrder> buildCancelledOrderFromJson(final String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            final TypeReference<List<ExCancelledOrder>> exUserOrdersRef = new TypeReference<List<ExCancelledOrder>>() {
            };
            return mapper.readValue(json, exUserOrdersRef);
        } catch (final IOException e) {
            return null;
        }
    }

    public Map<String, List<ExTrade>> buildTradeFromJson(final String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            final TypeReference<Map<String, List<ExTrade>>> exUserOrdersRef = new TypeReference<Map<String, List<ExTrade>>>() {
            };
            return mapper.readValue(json, exUserOrdersRef);
        } catch (final IOException e) {
            return null;
        }
    }

    public Map<String, ExOrderBook> buildUserOrdersFromJson(final String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            final TypeReference<Map<String, ExOrderBook>> exUserOrdersRef = new TypeReference<Map<String, ExOrderBook>>() {
            };
            return mapper.readValue(json, exUserOrdersRef);
        } catch (final IOException e) {
            return null;
        }
    }

    public Map<String, ExTicker> buildTickerFromJson(final String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            final TypeReference<Map<String, ExTicker>> exUserOrdersRef = new TypeReference<Map<String, ExTicker>>() {
            };
            return mapper.readValue(json, exUserOrdersRef);
        } catch (final IOException e) {
            return null;
        }
    }

    public <T> T buildFromJson(final String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            final TypeReference<T> ref = new TypeReference<T>() {
            };
            return mapper.readValue(json, ref);
        } catch (final IOException e) {
            return null;
        }
    }
}
