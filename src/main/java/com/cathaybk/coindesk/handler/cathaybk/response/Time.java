package com.cathaybk.coindesk.handler.cathaybk.response;

public class Time {
    private String updated;
    private String updatedISO;
    private String updateduk;

    public String getUpdated() {
        return updated;
    }

    public Time setUpdated(String updated) {
        this.updated = updated;
        return this;
    }

    public String getUpdatedISO() {
        return updatedISO;
    }

    public Time setUpdatedISO(String updatedISO) {
        this.updatedISO = updatedISO;
        return this;
    }

    public String getUpdateduk() {
        return updateduk;
    }

    public Time setUpdateduk(String updateduk) {
        this.updateduk = updateduk;
        return this;
    }
}
