package com.cathaybk.coindesk.dao;

import com.cathaybk.coindesk.dao.model.TbAccountKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbAccountKeyDao extends JpaRepository<TbAccountKey, String> {
    TbAccountKey findByApiKey(String apiKey);
}
