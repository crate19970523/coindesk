package com.cathaybk.coindesk.utils;

import com.cathaybk.coindesk.exception.HandlerException;
import com.google.gson.Gson;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpClient {
    private static final Logger log = LoggerFactory.getLogger(HttpClient.class);

    public static <T> T get(String url, Class<T> responseClass) {
        String responseJson;
        HttpGet httpGet = new HttpGet(url);
        log.info("will call Api: " + url);
        try (CloseableHttpClient httpclient = HttpClients.createDefault();
             CloseableHttpResponse response = httpclient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            if (response.getCode() < 200 && response.getCode() > 300) {
                throw new HandlerException("call api fail, http code: " + response.getCode());
            } else {
                responseJson = EntityUtils.toString(entity);
            }
            log.info("call api success, response: " + responseJson);
            return new Gson().fromJson(responseJson, responseClass);
        } catch (IOException | ParseException e) {
            throw new HandlerException("call api fail", e);
        }
    }
}
