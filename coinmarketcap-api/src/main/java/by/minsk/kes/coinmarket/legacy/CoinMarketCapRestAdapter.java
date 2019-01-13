package by.minsk.kes.coinmarket.legacy;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
@ManagedResource(description = "Manages Coin Market Adapter Functionality")
public class CoinMarketCapRestAdapter {

    private static final Logger LOG = LoggerFactory.getLogger("CoinMarketAdapter");

    private static final String URL_SEPARATOR = "/";

    @Value("${coinmarket.rest.adapter.log_responses:false}")
    private boolean logResponses;

    public final String get(final String baseUrl, final String operation, final List<String> pathVariables, final Map<String, String> parameters) {
        return get(baseUrl, operation, pathVariables, parameters, null);
    }

    public final String get(final String baseUrl, final String operation, final List<String> pathVariables, final Map<String, String> parameters,
                            final Map<String, String> headers) {
        final OkHttpClient client = new OkHttpClient();
        try {
            HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl + operation + getPathVariables(pathVariables)).newBuilder();
            addQueryParameters(urlBuilder, parameters);
            final String url = urlBuilder.build().toString();
            final Request request = addHeaders(new Builder().url(url), headers).build();
            final Response response = client.newCall(request).execute();
            final String responseJson = response.body().string();
            if (logResponses) {
                LOG.debug(String.format("%s result: %s", url, responseJson));
            }
            return responseJson;
        } catch (IOException e) {
            System.err.println("get fail: " + e.toString());
            return null;  // An error occured...
        }
    }

    private final Builder addHeaders(final Builder requestCallBuilder, final Map<String, String> headers) {
        if (MapUtils.isEmpty(headers)) {
            return requestCallBuilder;
        }
        headers.entrySet().stream().forEach(v -> requestCallBuilder.addHeader(v.getKey(), v.getValue()));
        return requestCallBuilder;
    }

    private final String getPathVariables(final List<String> pathVariables) {
        if (CollectionUtils.isEmpty(pathVariables)) {
            return "";
        }
        return pathVariables.stream().reduce(EMPTY, (s1, s2) -> s1 + s2 + URL_SEPARATOR);
    }

    private final void addQueryParameters(final HttpUrl.Builder urlBuilder, final Map<String, String> parameters) {
        if (MapUtils.isEmpty(parameters)) {
            return;
        }
        for (final Map.Entry<String, String> entry : parameters.entrySet()) {
            urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }
    }

    @ManagedAttribute
    public boolean isLogResponses() {
        return logResponses;
    }

    @ManagedAttribute
    public void setLogResponses(boolean logResponses) {
        this.logResponses = logResponses;
    }
}
