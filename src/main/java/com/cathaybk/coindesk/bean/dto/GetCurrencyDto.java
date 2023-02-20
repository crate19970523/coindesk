package com.cathaybk.coindesk.bean.dto;

import java.time.LocalDateTime;

public class GetCurrencyDto {
    private String currencyCode;
    private String twCurrencyName;
    private String enCurrencyName;
    private LocalDateTime updateDate;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public GetCurrencyDto setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public String getTwCurrencyName() {
        return twCurrencyName;
    }

    public GetCurrencyDto setTwCurrencyName(String twCurrencyName) {
        this.twCurrencyName = twCurrencyName;
        return this;
    }

    public String getEnCurrencyName() {
        return enCurrencyName;
    }

    public GetCurrencyDto setEnCurrencyName(String enCurrencyName) {
        this.enCurrencyName = enCurrencyName;
        return this;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public GetCurrencyDto setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
        return this;
    }
}
