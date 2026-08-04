package com.mx.edu.tecdesoftware.StreamCore.api.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Category {

    private Integer categoryId;

    @NotBlank(message = "la descripción es obligatoria")
    private String description;

    @NotNull(message = "el estado es obligatorio")
    private Boolean state;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}