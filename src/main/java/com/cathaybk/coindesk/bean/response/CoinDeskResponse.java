package com.cathaybk.coindesk.bean.response;

import java.util.List;

public class CoinDeskResponse extends BasicResponse {

    private String updateTime;
    private String chartName;
    private List<Bpi> bpi;

    public CoinDeskResponse() {
        this.setErrorMessage(new ErrorMessage().setErrorMessage(null));
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public CoinDeskResponse setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getChartName() {
        return chartName;
    }

    public CoinDeskResponse setChartName(String chartName) {
        this.chartName = chartName;
        return this;
    }

    public List<Bpi> getBpi() {
        return bpi;
    }

    public CoinDeskResponse setBpi(List<Bpi> bpi) {
        this.bpi = bpi;
        return this;
    }
}
