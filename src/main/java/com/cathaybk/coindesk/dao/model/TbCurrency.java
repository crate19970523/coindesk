package com.cathaybk.coindesk.dao.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_CURRENCY")
public class TbCurrency {
    @Id
    @Column(name = "CODE")
    private String code;
    @Column(name = "NAME_EN")
    private String nameEn;
    @Column(name = "NAME_TW")
    private String nameTw;
    @Column(name = "VERSION")
    private Long version;
    @Column(name = "UPDATE_USER")
    private String updateUser;
    @Column(name = "UPD_DT")
    private LocalDateTime updateTime;

    public String getCode() {
        return code;
    }

    public TbCurrency setCode(String code) {
        this.code = code;
        return this;
    }

    public String getNameEn() {
        return nameEn;
    }

    public TbCurrency setNameEn(String nameEn) {
        this.nameEn = nameEn;
        return this;
    }

    public String getNameTw() {
        return nameTw;
    }

    public TbCurrency setNameTw(String nameTw) {
        this.nameTw = nameTw;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public TbCurrency setVersion(Long version) {
        this.version = version;
        return this;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public TbCurrency setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public TbCurrency setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
