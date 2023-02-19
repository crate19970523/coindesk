package com.cathaybk.coindesk.handler.cathaybk.response;

public class CoindeskResponse {
    private String disclaimer;
    private String chartName;
    private Time time;
    private Bpi bpi;

    public String getDisclaimer() {
        return disclaimer;
    }

    public CoindeskResponse setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
        return this;
    }

    public String getChartName() {
        return chartName;
    }

    public CoindeskResponse setChartName(String chartName) {
        this.chartName = chartName;
        return this;
    }

    public Time getTime() {
        return time;
    }

    public CoindeskResponse setTime(Time time) {
        this.time = time;
        return this;
    }

    public Bpi getBpi() {
        return bpi;
    }

    public CoindeskResponse setBpi(Bpi bpi) {
        this.bpi = bpi;
        return this;
    }
}
