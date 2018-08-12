package by.minsk.kes.exmo.legacy; /**
 * Created by Admin on 2/18/2016.
 */

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.String.format;

@Component
@ManagedResource(description = "Manages REST functionality")
public class ExmoRestAdapter {

    private static final Logger LOG = LoggerFactory.getLogger("Adapter");
    private static final Logger LOG_NONCE = LoggerFactory.getLogger("NonceMonitoring");

    private static final String URL_SEPARATOR = "/";

    private static AtomicLong nonce;
    private String _key;
    private String _secret;

    @Value("${exmo.rest.adapter.log_responses:false}")
    private boolean logResponses;

    public ExmoRestAdapter(String key, String secret) {
        nonce = new AtomicLong(System.nanoTime());
        _key = key;
        _secret = secret;
    }

    public final String post(String method, Map<String, String> arguments) {
        Mac mac;
        SecretKeySpec key;
        String sign;

        if (arguments == null) {  // If the user provided no arguments, just create an empty argument array.
            arguments = new HashMap<>();
        }

        final Long nonceToUse = nonce.incrementAndGet();
        arguments.put("nonce", "" + nonceToUse);  // Add the dummy nonce.

        String postData = "";

        for (Map.Entry<String, String> stringStringEntry : arguments.entrySet()) {
            Map.Entry argument = (Map.Entry) stringStringEntry;

            if (postData.length() > 0) {
                postData += "&";
            }
            postData += argument.getKey() + "=" + argument.getValue();
        }

        // Create a new secret key
        try {
            key = new SecretKeySpec(_secret.getBytes("UTF-8"), "HmacSHA512");
        } catch (UnsupportedEncodingException uee) {
            System.err.println("Unsupported encoding exception: " + uee.toString());
            return null;
        }

        // Create a new mac
        try {
            mac = Mac.getInstance("HmacSHA512");
        } catch (NoSuchAlgorithmException nsae) {
            System.err.println("No such algorithm exception: " + nsae.toString());
            return null;
        }

        // Init mac with key.
        try {
            mac.init(key);
        } catch (InvalidKeyException ike) {
            System.err.println("Invalid key exception: " + ike.toString());
            return null;
        }


        // Encode the post data by the secret and encode the result as base64.
        try {
            sign = Hex.encodeHexString(mac.doFinal(postData.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException uee) {
            System.err.println("Unsupported encoding exception: " + uee.toString());
            return null;
        }

        // Now do the actual request
        MediaType form = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        try {
            final String url = "https://api.exmo.com/v1/" + method;
            RequestBody body = RequestBody.create(form, postData);
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Key", _key)
                    .addHeader("Sign", sign)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            final String responseJson = response.body().string();
            if (logResponses) {
                LOG.debug(format("%s result: %s", url, responseJson));
            }
            LOG_NONCE.debug(format("%s - %s", method, nonceToUse));
            return responseJson;
        } catch (IOException e) {
            System.err.println("post fail: " + e.toString());
            return null;  // An error occured...
        }
    }

    public final String get(final String baseUrl, final String operation, final Map<String, String> parameters) {
        final OkHttpClient client = new OkHttpClient();
        try {
            HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl + operation + URL_SEPARATOR).newBuilder();
            addQueryParameters(urlBuilder, parameters);
            final String url = urlBuilder.build().toString();
            final Request request = new Request.Builder().url(url).build();
            final Response response = client.newCall(request).execute();
            final String responseJson = response.body().string();
            if (logResponses) {
                LOG.debug(format("%s result: %s", url, responseJson));
            }
            return responseJson;
        } catch (IOException e) {
            System.err.println("get fail: " + e.toString());
            return null;  // An error occured...
        }
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
