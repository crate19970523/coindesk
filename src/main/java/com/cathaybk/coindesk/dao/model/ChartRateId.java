package com.cathaybk.coindesk.dao.model;

import java.io.Serializable;

public class ChartRateId implements Serializable {
    private int chartId;
    private String currencyCode;

    public int getChartId() {
        return chartId;
    }

    public ChartRateId setChartId(int chartId) {
        this.chartId = chartId;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public ChartRateId setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }
}
