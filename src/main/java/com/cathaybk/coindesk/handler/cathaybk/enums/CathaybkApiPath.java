package com.cathaybk.coindesk.handler.cathaybk.enums;

public enum CathaybkApiPath {
    getPrice("v1/bpi/currentprice.json");
    private final String path;

    CathaybkApiPath(String path) {
        this.path = path;
    }

    public String generateFullUrl(String url) {
        return url.endsWith("/")? url + path : url + "/" + path;
    }
}
