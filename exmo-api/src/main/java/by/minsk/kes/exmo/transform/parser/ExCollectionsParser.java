package by.minsk.kes.exmo.transform.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;

@Service
public class ExCollectionsParser<T> {

    private static final Logger LOG = LoggerFactory.getLogger(ExCollectionsParser.class);

    private ObjectMapper mapper = new ObjectMapper();

    public Map<String, T> parseMap(final String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            final TypeReference<Map<String, T>> exUserOrdersRef = new TypeReference<Map<String, T>>() {
            };
            return mapper.readValue(json, exUserOrdersRef);
        } catch (final IOException e) {            return null;
        }
    }

    public T parseCollection(final String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            final TypeReference<T> exUserOrdersRef = new TypeReference<T>() {
            };
            return mapper.readValue(json, exUserOrdersRef);
        } catch (final IOException e) {
            return null;
        }
    }
}
