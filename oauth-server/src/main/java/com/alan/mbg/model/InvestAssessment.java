package com.alan.mbg.model;

import java.io.Serializable;

public class InvestAssessment implements Serializable {
    private Long id;

    private Long siteId;

    private String contaminant;

    private Integer level1;

    private Integer level2;

    private Integer level3;

    private Integer level4;

    private Integer level5;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getContaminant() {
        return contaminant;
    }

    public void setContaminant(String contaminant) {
        this.contaminant = contaminant;
    }

    public Integer getLevel1() {
        return level1;
    }

    public void setLevel1(Integer level1) {
        this.level1 = level1;
    }

    public Integer getLevel2() {
        return level2;
    }

    public void setLevel2(Integer level2) {
        this.level2 = level2;
    }

    public Integer getLevel3() {
        return level3;
    }

    public void setLevel3(Integer level3) {
        this.level3 = level3;
    }

    public Integer getLevel4() {
        return level4;
    }

    public void setLevel4(Integer level4) {
        this.level4 = level4;
    }

    public Integer getLevel5() {
        return level5;
    }

    public void setLevel5(Integer level5) {
        this.level5 = level5;
    }
}