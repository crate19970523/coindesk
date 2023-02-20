package com.cathaybk.coindesk.dao.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@IdClass(ChartRateId.class)
@Table(name = "TB_CHART_RATE")
public class TbChartRate {
    @Id
    @Column(name = "CHART_ID")
    private int chartId;
    @Id
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;
    @Column(name = "RATE")
    private BigDecimal rate;
    @Column(name = "VERSION")
    private Long version;
    @Column(name = "UPDATE_USER")
    private String updateUser;

    public int getChartId() {
        return chartId;
    }

    public TbChartRate setChartId(int chartId) {
        this.chartId = chartId;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public TbChartRate setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public TbChartRate setRate(BigDecimal rate) {
        this.rate = rate;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public TbChartRate setVersion(Long version) {
        this.version = version;
        return this;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public TbChartRate setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }
}
