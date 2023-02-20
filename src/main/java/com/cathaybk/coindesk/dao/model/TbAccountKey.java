package com.cathaybk.coindesk.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ACCOUNT_KEY")
public class TbAccountKey {
    @Id
    @Column(name = "ACCOUNT_ID")
    private String accountId;
    @Column(name = "API_KEY")
    private String apiKey;

    public String getAccountId() {
        return accountId;
    }

    public TbAccountKey setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public TbAccountKey setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }
}
