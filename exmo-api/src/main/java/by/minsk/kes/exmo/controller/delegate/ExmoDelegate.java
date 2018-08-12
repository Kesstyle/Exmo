package by.minsk.kes.exmo.controller.delegate;

import by.minsk.kes.exmo.controller.delegate.repository.ExmoOperationsRepository;
import by.minsk.kes.exmo.legacy.ExmoRestAdapter;
import by.minsk.kes.exmo.model.api.*;
import by.minsk.kes.exmo.transform.parser.ExParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("exmoDelegate")
public class ExmoDelegate {

    @Value("${exmo.baseUrl}")
    private String baseUrl;

    @Autowired
    private ExmoOperationsRepository exmoOperationsRepository;

    @Autowired
    private ExmoRestAdapter exmo;

    @Autowired
    private ExParser parser;

    public Map<String, ExOrderBook> getOrders(final Map<String, String> params) {
        final String json = exmo.get(baseUrl, exmoOperationsRepository.getOrdersOperation(), params);
        return parser.buildUserOrdersFromJson(json);
    }

    public Map<String, ExTicker> getTicker(final Map<String, String> params) {
        final String json = exmo.get(baseUrl, exmoOperationsRepository.getTickerOperation(), params);
        return parser.buildTickerFromJson(json);
    }

    public Map<String, List<ExTrade>> getTrades(final Map<String, String> params) {
        final String json = exmo.get(baseUrl, exmoOperationsRepository.getTradesOperation(), params);
        return parser.buildTradeFromJson(json);
    }

    public Map<String, List<ExHistoryTrade>> getUserTradesHistory(final Map<String, String> params) {
        final String json = exmo.post(exmoOperationsRepository.getTradesHistoryOperation(), params);
        return parser.buildTradesHistoryFromJson(json);
    }

    public List<ExCancelledOrder> getCancelledOrders(final int limit, final int offset) {
        final String result = exmo.post(exmoOperationsRepository.getCancelledOrdersOperation(), new HashMap<String, String>() {{
            put("limit", String.valueOf(limit));
            put("offset", String.valueOf(offset));
        }});
        return parser.buildCancelledOrderFromJson(result);
    }

    public ExUserInfo getUserInfo(final Map<String, String> params) {
        final String json = exmo.post(exmoOperationsRepository.getUserInfoOperation(), params);
        return parser.buildUserInfoFromJson(json);
    }

    @Deprecated
    public String getRequiredAmount(final String pair, final BigDecimal quantity) {
        final String result = exmo.post(exmoOperationsRepository.getRequiredAmountOperation(), new HashMap<String, String>() {{
            put("pair", pair);
            put("quantity", String.valueOf(quantity));
        }});
        return result;
    }

}
