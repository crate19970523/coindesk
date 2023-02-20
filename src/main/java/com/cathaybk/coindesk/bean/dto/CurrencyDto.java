package com.cathaybk.coindesk.bean.dto;

public class CurrencyDto {
    private String apiKey;
    private String currencyCode;
    private String twCurrencyName;
    private String enCurrencyName;

    public String getApiKey() {
        return apiKey;
    }

    public CurrencyDto setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public CurrencyDto setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public String getTwCurrencyName() {
        return twCurrencyName;
    }

    public CurrencyDto setTwCurrencyName(String twCurrencyName) {
        this.twCurrencyName = twCurrencyName;
        return this;
    }

    public String getEnCurrencyName() {
        return enCurrencyName;
    }

    public CurrencyDto setEnCurrencyName(String enCurrencyName) {
        this.enCurrencyName = enCurrencyName;
        return this;
    }
}
