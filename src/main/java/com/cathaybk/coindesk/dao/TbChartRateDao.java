package com.cathaybk.coindesk.dao;

import com.cathaybk.coindesk.dao.model.TbChartRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbChartRateDao extends JpaRepository<TbChartRate, Integer> {

}
