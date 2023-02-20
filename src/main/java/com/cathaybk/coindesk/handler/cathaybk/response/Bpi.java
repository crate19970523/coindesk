package com.cathaybk.coindesk.handler.cathaybk.response;

public class Bpi {
    private BpiData USD;
    private BpiData GBP;
    private BpiData EUR;

    public BpiData getUSD() {
        return USD;
    }

    public Bpi setUSD(BpiData USD) {
        this.USD = USD;
        return this;
    }

    public BpiData getGBP() {
        return GBP;
    }

    public Bpi setGBP(BpiData GBP) {
        this.GBP = GBP;
        return this;
    }

    public BpiData getEUR() {
        return EUR;
    }

    public Bpi setEUR(BpiData EUR) {
        this.EUR = EUR;
        return this;
    }
}
