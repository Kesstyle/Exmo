package src.by.minsk.kes.exmo.transform;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import src.by.minsk.kes.exmo.model.ExOrder;

import java.io.IOException;
import java.util.List;

@Service
public class ExOrderParser {

  private ObjectMapper mapper = new ObjectMapper();

  public List<ExOrder> buildFromJson(final String json) {
    if (StringUtils.isBlank(json)) {
      return null;
    }
    try {
      final TypeReference<List<ExOrder>> exOrderRef = new TypeReference<List<ExOrder>>() {
      };
      return mapper.readValue(json, exOrderRef);
    } catch (final IOException e) {
      return null;
    }
  }
}
