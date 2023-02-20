package com.cathaybk.coindesk.bean.response;

import java.util.List;

public class CurrencyResponse extends BasicResponse {
    private List<CurrencyData> currencyData;

    public CurrencyResponse() {
        this.setErrorMessage(new ErrorMessage().setErrorMessage(null));
    }

    public List<CurrencyData> getCurrencyData() {
        return currencyData;
    }

    public CurrencyResponse setCurrencyData(List<CurrencyData> currencyData) {
        this.currencyData = currencyData;
        return this;
    }
}
