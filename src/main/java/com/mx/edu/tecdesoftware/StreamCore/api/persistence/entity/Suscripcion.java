package com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "suscripciones")
public class Suscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suscripcion")
    private Integer idSuscripcion;

    @Column(name = "id_usuario", nullable = false)
    private String idUsuario;

    @Column(name = "id_plan", nullable = false)
    private Integer idPlan;

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @Column(name = "medio_pago")
    private String medioPago;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "suscripcion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SuscripcionContenido> visualizaciones = new ArrayList<>();

    public Suscripcion() {
    }

    // Getters y Setters
    public Integer getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(Integer idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<SuscripcionContenido> getVisualizaciones() {
        return visualizaciones;
    }

    public void setVisualizaciones(List<SuscripcionContenido> visualizaciones) {
        this.visualizaciones = visualizaciones;
    }
}