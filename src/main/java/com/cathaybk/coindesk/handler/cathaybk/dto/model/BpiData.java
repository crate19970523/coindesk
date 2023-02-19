package com.cathaybk.coindesk.handler.cathaybk.dto.model;

import java.math.BigDecimal;

public class BpiData {
    private String code;
    private String symbol;
    private String description;
    private BigDecimal rate;

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

    public String getDescription() {
        return description;
    }

    public BpiData setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BpiData setRate(BigDecimal rate) {
        this.rate = rate;
        return this;
    }
}
