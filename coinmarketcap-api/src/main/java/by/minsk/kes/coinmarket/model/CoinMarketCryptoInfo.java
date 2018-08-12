package by.minsk.kes.coinmarket.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoinMarketCryptoInfo {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("symbol")
    private String code;

    @JsonProperty("website_slug")
    private String websiteSlug;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getWebsiteSlug() {
        return websiteSlug;
    }

    public void setWebsiteSlug(String websiteSlug) {
        this.websiteSlug = websiteSlug;
    }
}
