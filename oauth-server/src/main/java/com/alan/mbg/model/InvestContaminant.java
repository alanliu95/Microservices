package com.alan.mbg.model;

import java.io.Serializable;

public class InvestContaminant implements Serializable {
    private Long id;

    private Long pointid;

    private String name;

    private String cas;

    private Float concentration;

    private Float controlVal;

    private Float screeningVal;

    private String unit;

    private String type;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPointid() {
        return pointid;
    }

    public void setPointid(Long pointid) {
        this.pointid = pointid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public Float getConcentration() {
        return concentration;
    }

    public void setConcentration(Float concentration) {
        this.concentration = concentration;
    }

    public Float getControlVal() {
        return controlVal;
    }

    public void setControlVal(Float controlVal) {
        this.controlVal = controlVal;
    }

    public Float getScreeningVal() {
        return screeningVal;
    }

    public void setScreeningVal(Float screeningVal) {
        this.screeningVal = screeningVal;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}