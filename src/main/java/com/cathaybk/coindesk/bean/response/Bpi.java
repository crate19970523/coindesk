package com.cathaybk.coindesk.bean.response;

public class Bpi {
    private String currencyCode;
    private String twCurrencyName;
    private String enCurrencyName;
    private String rate;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Bpi setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public String getTwCurrencyName() {
        return twCurrencyName;
    }

    public Bpi setTwCurrencyName(String twCurrencyName) {
        this.twCurrencyName = twCurrencyName;
        return this;
    }

    public String getEnCurrencyName() {
        return enCurrencyName;
    }

    public Bpi setEnCurrencyName(String enCurrencyName) {
        this.enCurrencyName = enCurrencyName;
        return this;
    }

    public String getRate() {
        return rate;
    }

    public Bpi setRate(String rate) {
        this.rate = rate;
        return this;
    }
}
