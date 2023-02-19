package com.cathaybk.coindesk.handler.cathaybk;

import com.cathaybk.coindesk.exception.HandlerException;
import com.cathaybk.coindesk.exception.ThirdPartyResponseException;
import com.cathaybk.coindesk.handler.cathaybk.dto.CoindeskHandlerDto;
import com.cathaybk.coindesk.handler.cathaybk.dto.model.BpiData;
import com.cathaybk.coindesk.handler.cathaybk.enums.CathaybkApiPath;
import com.cathaybk.coindesk.handler.cathaybk.response.CoindeskResponse;
import com.cathaybk.coindesk.properties.CoinDeskProperties;
import com.cathaybk.coindesk.utils.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class CathaybkHandler {
    private CoinDeskProperties coinDeskProperties;

    public CoindeskHandlerDto getCoindesk() {
        try {
            String fullUrl = CathaybkApiPath.getPrice.generateFullUrl(Optional.ofNullable(coinDeskProperties.getUrl())
                    .orElseThrow(() -> new HandlerException("coinDeskProperties.getUrl() is null please check setting")));
            CoindeskResponse response = HttpClient.get(fullUrl, CoindeskResponse.class);
            checkResponseHaveError(response);
            return coindeskResponse2Dto(response);
        } catch (ThirdPartyResponseException | HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException("have unknown when call Coindesk api", e);
        }
    }

    private void checkResponseHaveError(CoindeskResponse response) {
        // TODO: need check fail response with cathaybk
        if (Objects.isNull(response)) {
            throw new ThirdPartyResponseException("call api success, but response is empty, please check whith Cathaybk");
        }
    }

    private CoindeskHandlerDto coindeskResponse2Dto(CoindeskResponse response) {
        try {
            return new CoindeskHandlerDto().setUpdatedTime(LocalDateTime.parse(response.getTime().getUpdatedISO(),
                            DateTimeFormatter.ISO_DATE_TIME)).setChartName(response.getChartName())
                    .setBpiDatas(takeBpiDataFromResponse(response));
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException("generate coindeskDto fail", e);
        }
    }

    private List<BpiData> takeBpiDataFromResponse(CoindeskResponse response) {
        try {
            List<BpiData> result = new ArrayList<>();
            com.cathaybk.coindesk.handler.cathaybk.response.BpiData usd = response.getBpi().getUSD();
            com.cathaybk.coindesk.handler.cathaybk.response.BpiData gbp = response.getBpi().getGBP();
            com.cathaybk.coindesk.handler.cathaybk.response.BpiData eur = response.getBpi().getEUR();
            result.add(new BpiData().setCode(usd.getCode()).setSymbol(usd.getSymbol())
                    .setDescription(usd.getDescription()).setRate(usd.getRate_float()));
            result.add(new BpiData().setCode(gbp.getCode()).setSymbol(gbp.getSymbol())
                    .setDescription(gbp.getDescription()).setRate(gbp.getRate_float()));
            result.add(new BpiData().setCode(eur.getCode()).setSymbol(eur.getSymbol())
                    .setDescription(eur.getDescription()).setRate(eur.getRate_float()));
            return result;
        } catch (Exception e) {
            throw new HandlerException("take bpi data have error", e);
        }
    }

    @Autowired
    public CathaybkHandler setCoinDeskProperties(CoinDeskProperties coinDeskProperties) {
        this.coinDeskProperties = coinDeskProperties;
        return this;
    }
}
