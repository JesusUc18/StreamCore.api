package com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "suscripciones_contenidos")
public class SuscripcionContenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visualizacion")
    private Integer idVisualizacion;

    @ManyToOne
    @JoinColumn(name = "id_suscripcion", nullable = false)
    private Suscripcion suscripcion;

    @Column(name = "id_contenido", nullable = false)
    private Integer idContenido;

    @Column(name = "fecha_visualizacion")
    private LocalDateTime fechaVisualizacion;

    @Column(name = "minutos_vistos")
    private Integer minutosVistos;

    @Column(name = "estado")
    private Boolean estado;

    public SuscripcionContenido() {
    }

    // Getters y Setters
    public Integer getIdVisualizacion() {
        return idVisualizacion;
    }

    public void setIdVisualizacion(Integer idVisualizacion) {
        this.idVisualizacion = idVisualizacion;
    }

    public Suscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(Suscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public Integer getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(Integer idContenido) {
        this.idContenido = idContenido;
    }

    public LocalDateTime getFechaVisualizacion() {
        return fechaVisualizacion;
    }

    public void setFechaVisualizacion(LocalDateTime fechaVisualizacion) {
        this.fechaVisualizacion = fechaVisualizacion;
    }

    public Integer getMinutosVistos() {
        return minutosVistos;
    }

    public void setMinutosVistos(Integer minutosVistos) {
        this.minutosVistos = minutosVistos;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}