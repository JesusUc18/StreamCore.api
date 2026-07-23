package com.mx.edu.tecdesoftware.StreamCore.api.domain;

import java.math.BigDecimal;

public class Plan {

    private Integer planId;
    private String name;
    private String description;
    private BigDecimal monthlyPrice;
    private Integer simultaneousScreens;
    private String videoQuality;
    private Boolean state;

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(BigDecimal monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public Integer getSimultaneousScreens() {
        return simultaneousScreens;
    }

    public void setSimultaneousScreens(Integer simultaneousScreens) {
        this.simultaneousScreens = simultaneousScreens;
    }

    public String getVideoQuality() {
        return videoQuality;
    }

    public void setVideoQuality(String videoQuality) {
        this.videoQuality = videoQuality;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}