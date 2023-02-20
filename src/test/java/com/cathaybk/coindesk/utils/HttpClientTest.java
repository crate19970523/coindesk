package com.cathaybk.coindesk.utils;

import com.cathaybk.coindesk.CoindeskApplication;
import com.cathaybk.coindesk.exception.HandlerException;
import com.cathaybk.coindesk.handler.cathaybk.enums.CathaybkApiPath;
import com.cathaybk.coindesk.handler.cathaybk.response.CoindeskResponse;
import com.cathaybk.coindesk.properties.CoinDeskProperties;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

@SpringBootTest(classes = CoindeskApplication.class)
@WebAppConfiguration
public class HttpClientTest {
    private CoinDeskProperties coinDeskProperties;

    @Test
    void name() {
        String fullUrl = CathaybkApiPath.getPrice.generateFullUrl(Optional.ofNullable(coinDeskProperties.getUrl())
                .orElseThrow(() -> new HandlerException("coinDeskProperties.getUrl() is null please check setting")));
        CoindeskResponse response = HttpClient.get(fullUrl, CoindeskResponse.class);
        System.out.println(new Gson().toJson(response));
    }

    @Autowired
    public HttpClientTest setCoinDeskProperties(CoinDeskProperties coinDeskProperties) {
        this.coinDeskProperties = coinDeskProperties;
        return this;
    }
}
