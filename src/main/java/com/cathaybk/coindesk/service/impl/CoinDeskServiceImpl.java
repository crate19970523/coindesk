package com.cathaybk.coindesk.service.impl;

import com.cathaybk.coindesk.dao.TbCurrencyDao;
import com.cathaybk.coindesk.dao.model.TbCurrency;
import com.cathaybk.coindesk.exception.CoindeskException;
import com.cathaybk.coindesk.exception.DbException;
import com.cathaybk.coindesk.exception.HandlerException;
import com.cathaybk.coindesk.exception.ThirdPartyResponseException;
import com.cathaybk.coindesk.handler.cathaybk.CathaybkHandler;
import com.cathaybk.coindesk.handler.cathaybk.dto.CoindeskHandlerDto;
import com.cathaybk.coindesk.service.CoinDeskService;
import com.cathaybk.coindesk.bean.dto.RateData;
import com.cathaybk.coindesk.bean.dto.TakeCoinDeskResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoinDeskServiceImpl implements CoinDeskService {
    private CathaybkHandler cathaybkHandler;
    private TbCurrencyDao tbCurrencyDao;

    @Override
    public TakeCoinDeskResultDto takeCoinDesk() {
        List<TbCurrency> allCurrencyInfo = queryAllCurrency();
        CoindeskHandlerDto coindeskHandlerResult = callGetCoinDeskApi();
        List<RateData> rateDatas = generateRateDatas(allCurrencyInfo, coindeskHandlerResult);
        return generateCoinDeskResultDto(rateDatas, coindeskHandlerResult);
    }

    private List<TbCurrency> queryAllCurrency() {
        try {
            return tbCurrencyDao.findAll();
        } catch (Exception e) {
            throw new DbException("query all TB_CURRENCY fail", e);
        }
    }

    private CoindeskHandlerDto callGetCoinDeskApi() {
        try {
            return cathaybkHandler.getCoindesk();
        } catch (ThirdPartyResponseException | HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new CoindeskException("call coinDeslk api have unknown error", e);
        }
    }

    private TakeCoinDeskResultDto generateCoinDeskResultDto(List<RateData> rateDatas, CoindeskHandlerDto coindeskHandlerResult) {
        try {
            return new TakeCoinDeskResultDto().setUpdateDate(coindeskHandlerResult.getUpdatedTime())
                    .setChartName(coindeskHandlerResult.getChartName()).setBpiDatas(rateDatas);
        } catch (Exception e) {
            throw new CoindeskException("generate coin Desk Result Dto fail", e);
        }
    }

    private List<RateData> generateRateDatas(List<TbCurrency> allCurrencyInfo, CoindeskHandlerDto coindeskHandlerResult) {
        try {
            return coindeskHandlerResult.getBpiDatas().stream()
                    .filter(b -> allCurrencyInfo.stream().anyMatch(a -> b.getCode().equals(a.getCode())))
                    .map(b -> {
                        TbCurrency c = allCurrencyInfo.stream().filter(a -> a.getCode().equals(b.getCode())).findAny().get();
                        return new RateData().setCurrencyCode(b.getCode()).setRate(b.getRate())
                                .setEnCurrencyName(c.getNameEn()).setTwCurrencyName(c.getNameTw());
                    }).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CoindeskException("generate rate list fail", e);
        }
    }

    @Autowired
    public CoinDeskServiceImpl setCathaybkHandler(CathaybkHandler cathaybkHandler) {
        this.cathaybkHandler = cathaybkHandler;
        return this;
    }

    @Autowired
    public CoinDeskServiceImpl setTbCurrencyDao(TbCurrencyDao tbCurrencyDao) {
        this.tbCurrencyDao = tbCurrencyDao;
        return this;
    }
}
