package com.cathaybk.coindesk.bean.response;

public class CurrencyData {
    private String currencyCode;
    private String twCurrencyName;
    private String enCurrencyName;
    private String updateDate;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public CurrencyData setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public String getTwCurrencyName() {
        return twCurrencyName;
    }

    public CurrencyData setTwCurrencyName(String twCurrencyName) {
        this.twCurrencyName = twCurrencyName;
        return this;
    }

    public String getEnCurrencyName() {
        return enCurrencyName;
    }

    public CurrencyData setEnCurrencyName(String enCurrencyName) {
        this.enCurrencyName = enCurrencyName;
        return this;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public CurrencyData setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
        return this;
    }
}
