package com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "planes")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan")
    private Integer idPlan;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "descripcion", length = 150)
    private String descripcion;

    @Column(name = "precio_mensual", precision = 16, scale = 2)
    private BigDecimal precioMensual;

    @Column(name = "pantallas_simultaneas")
    private Integer pantallasSimultaneas;

    @Column(name = "calidad_video", length = 20)
    private String calidadVideo;

    @Column(name = "estado")
    private Boolean estado;

    @OneToMany(mappedBy = "plan")
    private List<Suscripcion> suscripciones = new ArrayList<>();

    public Plan() {
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioMensual() {
        return precioMensual;
    }

    public void setPrecioMensual(BigDecimal precioMensual) {
        this.precioMensual = precioMensual;
    }

    public Integer getPantallasSimultaneas() {
        return pantallasSimultaneas;
    }

    public void setPantallasSimultaneas(Integer pantallasSimultaneas) {
        this.pantallasSimultaneas = pantallasSimultaneas;
    }

    public String getCalidadVideo() {
        return calidadVideo;
    }

    public void setCalidadVideo(String calidadVideo) {
        this.calidadVideo = calidadVideo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<Suscripcion> getSuscripciones() {
        return suscripciones;
    }

    public void setSuscripciones(List<Suscripcion> suscripciones) {
        this.suscripciones = suscripciones;
    }
}