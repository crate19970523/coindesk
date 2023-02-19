package com.cathaybk.coindesk.service.impl;

import com.cathaybk.coindesk.bean.dto.CurrencyDto;
import com.cathaybk.coindesk.bean.dto.GetCurrencyDto;
import com.cathaybk.coindesk.dao.TbAccountKeyDao;
import com.cathaybk.coindesk.dao.TbCurrencyDao;
import com.cathaybk.coindesk.dao.model.TbCurrency;
import com.cathaybk.coindesk.exception.DbException;
import com.cathaybk.coindesk.exception.HandlerException;
import com.cathaybk.coindesk.service.CurrencyDbMaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyDbMaintainServiceImpl implements CurrencyDbMaintainService {
    private TbCurrencyDao tbCurrencyDao;
    private TbAccountKeyDao tbAccountKeyDao;

    @Override
    public List<GetCurrencyDto> getCurrency() {
        try {
            List<TbCurrency> tbCurrencyModels = queryTbCurrency();
            return tbCurrencyModels.stream().map(this::tbCurrencyModel2Dto).collect(Collectors.toList());
        } catch (Exception e) {
            throw e;
        }
    }

    private List<TbCurrency> queryTbCurrency() {
        try {
            return tbCurrencyDao.findAll();
        } catch (Exception e) {
            throw new DbException("query TB_CURRENCY fail", e);
        }
    }

    @Override
    public GetCurrencyDto getCurrency(String currencyCode) {
        try {
            TbCurrency queryResult = Optional.ofNullable(queryTbCurrency(currencyCode))
                    .orElseThrow(() -> new DbException(currencyCode + " does not exist in DB, please use insert api."));
            return tbCurrencyModel2Dto(queryResult);
        } catch (Exception e) {
            throw e;
        }
    }

    private TbCurrency queryTbCurrency(String currencyCode) {
        try {
            return tbCurrencyDao.findByCode(currencyCode);
        } catch (Exception e) {
            throw new DbException("query TB_CURRENCY fail, currencyCode: " + currencyCode, e);
        }
    }

    private GetCurrencyDto tbCurrencyModel2Dto(TbCurrency currencyModel) {
        try {
            return new GetCurrencyDto().setCurrencyCode(currencyModel.getCode())
                    .setTwCurrencyName(currencyModel.getNameTw()).setEnCurrencyName(currencyModel.getNameEn())
                    .setUpdateDate(currencyModel.getUpdateTime());
        } catch (Exception e) {
            throw new HandlerException("currencyModel to getCurrencyDto fail", e);
        }
    }

    @Override
    @Transactional
    public void updateCurrency(CurrencyDto currencyDto) {
        try {
            String accountId = queryAccount(currencyDto.getApiKey());
            TbCurrency beforeUpdate = Optional.ofNullable(queryTbCurrency(currencyDto.getCurrencyCode()))
                    .orElseThrow(() ->
                            new DbException(currencyDto.getCurrencyCode() + " does not exist in DB, please use insert api."));
            updateCurrency(currencyDto2CurrencyModel(currencyDto, beforeUpdate.getVersion(), accountId));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public void insertNewCurrency(CurrencyDto currencyDto) {
        try {
            String accountId = queryAccount(currencyDto.getApiKey());
            TbCurrency tbCurrencyModel = currencyDto2CurrencyModel(currencyDto, 1L, accountId);
            insertCurrency(tbCurrencyModel);
        } catch (Exception e) {
            throw e;
        }
    }

    private String queryAccount(String apiKey) {
        try {
            return Optional.ofNullable(tbAccountKeyDao.findByApiKey(apiKey).getAccountId())
                    .orElseThrow(() -> new DbException("api-key does not exist, apiKey: " + apiKey));
        } catch (DbException e) {
            throw e;
        } catch (Exception e) {
            throw new DbException("query TB_ACCOUNT_KEY fail, api-key: " + apiKey, e);
        }
    }

    private TbCurrency currencyDto2CurrencyModel(CurrencyDto currencyDto, Long currentVersion, String updateUserId) {
        return new TbCurrency().setCode(currencyDto.getCurrencyCode()).setNameEn(currencyDto.getEnCurrencyName())
                .setNameTw(currencyDto.getTwCurrencyName()).setVersion(currentVersion)
                .setUpdateUser(updateUserId).setUpdateTime(LocalDateTime.now());
    }

    private void updateCurrency(TbCurrency tbCurrencyModel) {
        try {
            int updateCount = tbCurrencyDao.updateByCodeAndVersion(tbCurrencyModel);
            if (updateCount < 1) throw new DbException("Update has a version conflict, please try again");
        } catch (Exception e) {
            throw new DbException("update Currency data fail", e);
        }
    }

    private void insertCurrency(TbCurrency tbCurrencyModel) {
        try {
            tbCurrencyDao.save(tbCurrencyModel);
        } catch (Exception e) {
            throw new DbException("update Currency data fail", e);
        }
    }

    @Override
    @Transactional
    public void deleteCurrency(String currencyCode) {
        try {
            doDeleteCurrency(currencyCode);
        } catch (Exception e) {
            throw e;
        }
    }

    private void doDeleteCurrency(String currencyCode) {
        try {
            tbCurrencyDao.deleteAllByCode(currencyCode);
        } catch (Exception e) {
            throw new DbException("delete currency, currencyCode: " + currencyCode, e);
        }
    }

    @Autowired
    public CurrencyDbMaintainServiceImpl setTbCurrencyDao(TbCurrencyDao tbCurrencyDao) {
        this.tbCurrencyDao = tbCurrencyDao;
        return this;
    }

    @Autowired
    public CurrencyDbMaintainServiceImpl setTbAccountKeyDao(TbAccountKeyDao tbAccountKeyDao) {
        this.tbAccountKeyDao = tbAccountKeyDao;
        return this;
    }
}
