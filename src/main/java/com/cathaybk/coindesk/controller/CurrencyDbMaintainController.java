package com.cathaybk.coindesk.controller;

import com.cathaybk.coindesk.bean.dto.CurrencyDto;
import com.cathaybk.coindesk.bean.dto.GetCurrencyDto;
import com.cathaybk.coindesk.bean.request.CurrencyRequest;
import com.cathaybk.coindesk.bean.response.BasicResponse;
import com.cathaybk.coindesk.bean.response.CurrencyData;
import com.cathaybk.coindesk.bean.response.CurrencyResponse;
import com.cathaybk.coindesk.bean.response.ErrorMessage;
import com.cathaybk.coindesk.constant.Header;
import com.cathaybk.coindesk.exception.CoindeskException;
import com.cathaybk.coindesk.service.CurrencyDbMaintainService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CurrencyDbMaintainController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private CurrencyDbMaintainService currencyDbMaintainService;

    @RequestMapping(value = "/currency/getInfo", method = RequestMethod.GET)
    public CurrencyResponse getAllCurrency(@RequestHeader(Header.API_KEY)
                                           @Schema(description = "api-key", example = "demo_for_test") String apiKey) {
        try {
            List<GetCurrencyDto> dto = currencyDbMaintainService.getCurrency();
            return getCurrencyDtos2Response(dto);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    @RequestMapping(value = "/currency/getInfo/currencyCode/{currencyCode}", method = RequestMethod.GET)
    public CurrencyResponse getCurrency(@RequestHeader(Header.API_KEY)
                                        @Schema(description = "api-key", example = "demo_for_test") String apiKey,
                                        @PathVariable("currencyCode") String currencyCode) {
        try {
            GetCurrencyDto dto = currencyDbMaintainService.getCurrency(currencyCode);
            return getCurrencyDtos2Response(dto);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    private CurrencyResponse getCurrencyDtos2Response(List<GetCurrencyDto> dto) {
        try {
            return new CurrencyResponse()
                    .setCurrencyData(dto.stream().map(d -> new CurrencyData().setCurrencyCode(d.getCurrencyCode())
                                    .setTwCurrencyName(d.getTwCurrencyName()).setEnCurrencyName(d.getEnCurrencyName())
                                    .setUpdateDate(d.getUpdateDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))))
                            .collect(Collectors.toList()));
        } catch (Exception e) {
            throw new CoindeskException("dto to response fail", e);
        }
    }

    private CurrencyResponse getCurrencyDtos2Response(GetCurrencyDto dto) {
        try {
            List<CurrencyData> currencyDatas = new ArrayList<>();
            currencyDatas.add(new CurrencyData().setCurrencyCode(dto.getCurrencyCode())
                    .setTwCurrencyName(dto.getTwCurrencyName()).setEnCurrencyName(dto.getEnCurrencyName())
                    .setUpdateDate(dto.getUpdateDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))));
            return new CurrencyResponse().setCurrencyData(currencyDatas);
        } catch (Exception e) {
            throw new CoindeskException("dto to response fail", e);
        }
    }

    @RequestMapping(value = "/currency/insert", method = RequestMethod.POST)
    public BasicResponse insertCurrency(@RequestHeader(Header.API_KEY)
                                        @Schema(description = "api-key", example = "demo_for_test") String apiKey,
                                        @RequestBody CurrencyRequest request) {
        try {
            currencyDbMaintainService.insertNewCurrency(currencyRequest2Dto(request, apiKey));
            return new BasicResponse().setErrorMessage(new ErrorMessage().setErrorMessage(null));
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    @RequestMapping(value = "/currency/update", method = RequestMethod.POST)
    public BasicResponse updateCurrency(@RequestHeader(Header.API_KEY)
                                        @Schema(description = "api-key", example = "demo_for_test") String apiKey,
                                        @RequestBody CurrencyRequest request) {
        try {
            currencyDbMaintainService.updateCurrency(currencyRequest2Dto(request, apiKey));
            return new BasicResponse().setErrorMessage(new ErrorMessage().setErrorMessage(null));
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    private CurrencyDto currencyRequest2Dto(CurrencyRequest request, String apiKey) {
        return new CurrencyDto().setApiKey(apiKey).setCurrencyCode(request.getCurrencyCode())
                .setTwCurrencyName(request.getTwCurrencyName()).setEnCurrencyName(request.getEnCurrencyName());
    }

    @RequestMapping(value = "/currency/delete/currencyCode/{currencyCode}", method = RequestMethod.DELETE)
    public BasicResponse deleteCurrency(@PathVariable("currencyCode") String currencyCode) {
        try {
            currencyDbMaintainService.deleteCurrency(currencyCode);
            return new BasicResponse().setErrorMessage(new ErrorMessage().setErrorMessage(null));
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    @Autowired
    public CurrencyDbMaintainController setDbMaintainService(CurrencyDbMaintainService currencyDbMaintainService) {
        this.currencyDbMaintainService = currencyDbMaintainService;
        return this;
    }
}
