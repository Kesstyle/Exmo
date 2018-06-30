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

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class ExmoRestAdapter {

    private static final Logger LOG = LoggerFactory.getLogger("Adapter");

    private static final String URL_SEPARATOR = "/";

    private static long _nonce;
    private String _key;
    private String _secret;

    public ExmoRestAdapter(String key, String secret) {
        _nonce = System.nanoTime();
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

        arguments.put("nonce", "" + ++_nonce);  // Add the dummy nonce.

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

            RequestBody body = RequestBody.create(form, postData);
            Request request = new Request.Builder()
                    .url("https://api.exmo.com/v1/" + method)
                    .addHeader("Key", _key)
                    .addHeader("Sign", sign)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
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
            LOG.debug(String.format("Making a call to %s with parameters %s", url, parameters));
            final Response response = client.newCall(request).execute();
            return response.body().string();
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
}
