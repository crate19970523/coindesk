package com.cathaybk.coindesk.bean.dto;

import java.math.BigDecimal;

public class RateData {
    private String currencyCode;
    private String twCurrencyName;
    private String enCurrencyName;
    private BigDecimal rate;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public RateData setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public String getTwCurrencyName() {
        return twCurrencyName;
    }

    public RateData setTwCurrencyName(String twCurrencyName) {
        this.twCurrencyName = twCurrencyName;
        return this;
    }

    public String getEnCurrencyName() {
        return enCurrencyName;
    }

    public RateData setEnCurrencyName(String enCurrencyName) {
        this.enCurrencyName = enCurrencyName;
        return this;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public RateData setRate(BigDecimal rate) {
        this.rate = rate;
        return this;
    }
}
