package com.cathaybk.coindesk.dao.model;

import javax.persistence.*;

@Entity
@Table(name = "TB_CHART")
public class TbChart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    @Column(name = "CHART_NAME")
    private String chartName;
    @Column(name = "VERSION")
    private Long version;
    @Column(name = "UPDATE_USER")
    private String updateUser;

    public int getId() {
        return id;
    }

    public TbChart setId(int id) {
        this.id = id;
        return this;
    }

    public String getChartName() {
        return chartName;
    }

    public TbChart setChartName(String chartName) {
        this.chartName = chartName;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public TbChart setVersion(Long version) {
        this.version = version;
        return this;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public TbChart setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    @Override
    public String toString() {
        return "TbChart{" +
                "id=" + id +
                ", chartName='" + chartName + '\'' +
                ", version=" + version +
                ", updateUser='" + updateUser + '\'' +
                '}';
    }
}
