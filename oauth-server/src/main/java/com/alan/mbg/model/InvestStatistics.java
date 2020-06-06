package com.alan.mbg.model;

import java.io.Serializable;

public class InvestStatistics implements Serializable {
    private Long id;

    private Long siteId;

    private String contaminant;

    private Double maxVal;

    private Double minVal;

    private Double detectionRatio;

    private Double exceedRatio;

    private Double maxExceedMultiple;

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

    public Double getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(Double maxVal) {
        this.maxVal = maxVal;
    }

    public Double getMinVal() {
        return minVal;
    }

    public void setMinVal(Double minVal) {
        this.minVal = minVal;
    }

    public Double getDetectionRatio() {
        return detectionRatio;
    }

    public void setDetectionRatio(Double detectionRatio) {
        this.detectionRatio = detectionRatio;
    }

    public Double getExceedRatio() {
        return exceedRatio;
    }

    public void setExceedRatio(Double exceedRatio) {
        this.exceedRatio = exceedRatio;
    }

    public Double getMaxExceedMultiple() {
        return maxExceedMultiple;
    }

    public void setMaxExceedMultiple(Double maxExceedMultiple) {
        this.maxExceedMultiple = maxExceedMultiple;
    }
}