package com.cathaybk.coindesk.handler.cathaybk.response;

import java.math.BigDecimal;

public class BpiData {
    private String code;
    private String symbol;
    private String rate;
    private String description;
    private Float rate_float;

    public String getCode() {
        return code;
    }

    public BpiData setCode(String code) {
        this.code = code;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public BpiData setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public String getRate() {
        return rate;
    }

    public BpiData setRate(String rate) {
        this.rate = rate;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BpiData setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getRate_float() {
        return new BigDecimal(rate_float);
    }

    public BpiData setRate_float(Float rate_float) {
        this.rate_float = rate_float;
        return this;
    }
}
