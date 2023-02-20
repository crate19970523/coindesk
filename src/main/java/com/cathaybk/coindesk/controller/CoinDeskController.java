package com.cathaybk.coindesk.controller;

import com.cathaybk.coindesk.bean.dto.TakeCoinDeskResultDto;
import com.cathaybk.coindesk.bean.response.Bpi;
import com.cathaybk.coindesk.bean.response.CoinDeskResponse;
import com.cathaybk.coindesk.constant.Header;
import com.cathaybk.coindesk.exception.CoindeskException;
import com.cathaybk.coindesk.service.CoinDeskService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@RestController
public class CoinDeskController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private CoinDeskService coinDeskService;

    @RequestMapping(value = "/getCoinDesk", method = RequestMethod.GET)
    public CoinDeskResponse getCoinDesk(@RequestHeader(Header.API_KEY)
                                        @Schema(description = "api-key", example = "demo_for_test") String apiKey) {
        try {
            TakeCoinDeskResultDto takeCoinDeskResultDto = coinDeskService.takeCoinDesk();
            return takeCoinDeskResultDto2Response(takeCoinDeskResultDto);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    private CoinDeskResponse takeCoinDeskResultDto2Response(TakeCoinDeskResultDto takeCoinDeskResultDto) {
        try {
            return new CoinDeskResponse().setChartName(takeCoinDeskResultDto.getChartName())
                    .setUpdateTime(takeCoinDeskResultDto.getUpdateDate()
                            .format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                    .setBpi(takeCoinDeskResultDto.getBpiDatas().stream().map(b ->
                                    new Bpi().setTwCurrencyName(b.getTwCurrencyName())
                                            .setCurrencyCode(b.getCurrencyCode())
                                            .setEnCurrencyName(b.getEnCurrencyName())
                                            .setRate(new DecimalFormat("#,###.000000000").format(b.getRate())))
                            .collect(Collectors.toList()));
        } catch (Exception e) {
            throw new CoindeskException("generate response fail", e);
        }
    }

    @Autowired
    public CoinDeskController setCoinDeskService(CoinDeskService coinDeskService) {
        this.coinDeskService = coinDeskService;
        return this;
    }
}
