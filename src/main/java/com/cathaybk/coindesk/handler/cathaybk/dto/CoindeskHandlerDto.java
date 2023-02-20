package com.cathaybk.coindesk.handler.cathaybk.dto;

import com.cathaybk.coindesk.handler.cathaybk.dto.model.BpiData;

import java.time.LocalDateTime;
import java.util.List;

public class CoindeskHandlerDto {
    private LocalDateTime updatedTime;
    private String chartName;
    private List<BpiData> bpiDatas;

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public CoindeskHandlerDto setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
        return this;
    }

    public String getChartName() {
        return chartName;
    }

    public CoindeskHandlerDto setChartName(String chartName) {
        this.chartName = chartName;
        return this;
    }

    public List<BpiData> getBpiDatas() {
        return bpiDatas;
    }

    public CoindeskHandlerDto setBpiDatas(List<BpiData> bpiDatas) {
        this.bpiDatas = bpiDatas;
        return this;
    }
}
