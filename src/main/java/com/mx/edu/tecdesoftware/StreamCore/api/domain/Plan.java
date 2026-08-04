package com.mx.edu.tecdesoftware.StreamCore.api.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class Plan {

    private Integer planId;

    @NotBlank(message = "el nombre es obligatorio")
    private String name;

    private String description;

    @NotNull(message = "el precio mensual es obligatorio")
    @Positive(message = "el precio mensual debe ser mayor a 0")
    private BigDecimal monthlyPrice;

    @NotNull(message = "las pantallas simultáneas son obligatorias")
    @Positive(message = "las pantallas simultáneas deben ser mayor a 0")
    private Integer simultaneousScreens;

    @NotBlank(message = "la calidad de video es obligatoria")
    private String videoQuality;

    @NotNull(message = "el estado es obligatorio")
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