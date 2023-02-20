package com.cathaybk.coindesk.dao;

import com.cathaybk.coindesk.dao.model.TbChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbChartDao extends JpaRepository<TbChart, Integer> {
        TbChart findById(int id);
        TbChart findByChartName(String chartName);
}
