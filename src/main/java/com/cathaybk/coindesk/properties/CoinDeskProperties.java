package com.cathaybk.coindesk.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "integrate.cathaybk")
public class CoinDeskProperties {
    private String url;

    public String getUrl() {
        return url;
    }

    public CoinDeskProperties setUrl(String url) {
        this.url = url;
        return this;
    }
}
