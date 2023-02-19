package com.cathaybk.coindesk.dao;

import com.cathaybk.coindesk.dao.model.TbCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TbCurrencyDao extends JpaRepository<TbCurrency, String> {
    TbCurrency findByCode(String code);
    @Modifying
    @Query(value = "UPDATE TB_CURRENCY " +
            "SET NAME_EN = :#{#tbCurrency.nameEn}, " +
            "    NAME_TW = :#{#tbCurrency.nameTw}," +
            "    UPD_DT = :#{#tbCurrency.updateTime}," +
            "    UPDATE_USER = :#{#tbCurrency.updateUser}," +
            "    VERSION = VERSION + 1" +
            " WHERE VERSION = :#{#tbCurrency.version} " +
            "AND CODE = :#{#tbCurrency.code}", nativeQuery = true)
    int updateByCodeAndVersion(@Param("tbCurrency") TbCurrency tbCurrency);

    int deleteAllByCode(String code);
}
