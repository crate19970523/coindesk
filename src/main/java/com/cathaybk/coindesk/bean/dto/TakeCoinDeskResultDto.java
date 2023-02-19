package com.cathaybk.coindesk.bean.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TakeCoinDeskResultDto {
    private LocalDateTime updateDate;
    private String chartName;
    List<RateData> rateData;

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public TakeCoinDeskResultDto setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public String getChartName() {
        return chartName;
    }

    public TakeCoinDeskResultDto setChartName(String chartName) {
        this.chartName = chartName;
        return this;
    }

    public List<RateData> getBpiDatas() {
        return rateData;
    }

    public TakeCoinDeskResultDto setBpiDatas(List<RateData> rateData) {
        this.rateData = rateData;
        return this;
    }
}
