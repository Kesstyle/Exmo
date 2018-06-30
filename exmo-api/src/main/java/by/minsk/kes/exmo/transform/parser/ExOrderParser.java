package by.minsk.kes.exmo.transform.parser;

import by.minsk.kes.exmo.model.ExCancelledOrder;
import by.minsk.kes.exmo.model.ExTrade;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ExOrderParser {

  private ObjectMapper mapper = new ObjectMapper();

  public List<ExCancelledOrder> buildCancelledOrderFromJson(final String json) {
    if (StringUtils.isBlank(json)) {
      return null;
    }
    try {
      final TypeReference<List<ExCancelledOrder>> exOrderRef = new TypeReference<List<ExCancelledOrder>>() {
      };
      return mapper.readValue(json, exOrderRef);
    } catch (final IOException e) {
      return null;
    }
  }

  public Map<String, List<ExTrade>> buildTradeFromJson(final String json) {
    if (StringUtils.isBlank(json)) {
      return null;
    }
    try {
      final TypeReference<Map<String, List<ExTrade>>> exTradeRef = new TypeReference<Map<String, List<ExTrade>>>() {
      };
      return mapper.readValue(json, exTradeRef);
    } catch (final IOException e) {
      return null;
    }
  }
}
