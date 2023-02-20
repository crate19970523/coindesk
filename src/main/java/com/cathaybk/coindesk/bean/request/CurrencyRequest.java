package com.cathaybk.coindesk.bean.request;

public class CurrencyRequest {
    private String currencyCode;
    private String twCurrencyName;
    private String enCurrencyName;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public CurrencyRequest setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public String getTwCurrencyName() {
        return twCurrencyName;
    }

    public CurrencyRequest setTwCurrencyName(String twCurrencyName) {
        this.twCurrencyName = twCurrencyName;
        return this;
    }

    public String getEnCurrencyName() {
        return enCurrencyName;
    }

    public CurrencyRequest setEnCurrencyName(String enCurrencyName) {
        this.enCurrencyName = enCurrencyName;
        return this;
    }
}
