package com.cathaybk.coindesk.service;

import com.cathaybk.coindesk.bean.dto.CurrencyDto;
import com.cathaybk.coindesk.bean.dto.GetCurrencyDto;

import java.util.List;

public interface CurrencyDbMaintainService {
    List<GetCurrencyDto> getCurrency();
    GetCurrencyDto getCurrency(String currencyCode);
    void updateCurrency(CurrencyDto currencyDto);
    void insertNewCurrency(CurrencyDto currencyDto);
    void deleteCurrency(String currencyCode);
}
