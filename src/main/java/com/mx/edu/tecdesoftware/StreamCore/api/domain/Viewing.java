package com.mx.edu.tecdesoftware.StreamCore.api.domain;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class Viewing {

    @NotNull(message = "el contentId de la visualización es obligatorio")
    private Integer contentId;

    private LocalDateTime viewedAt;
    private Integer minutesWatched;

    @NotNull(message = "el estado (active) de la visualización es obligatorio")
    private Boolean active;

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public LocalDateTime getViewedAt() {
        return viewedAt;
    }

    public void setViewedAt(LocalDateTime viewedAt) {
        this.viewedAt = viewedAt;
    }

    public Integer getMinutesWatched() {
        return minutesWatched;
    }

    public void setMinutesWatched(Integer minutesWatched) {
        this.minutesWatched = minutesWatched;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}