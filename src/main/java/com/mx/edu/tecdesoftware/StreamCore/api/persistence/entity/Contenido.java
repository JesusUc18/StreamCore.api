package com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contenidos")
public class Contenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contenido")
    private Integer idContenido;

    @Column(name = "titulo", length = 100)
    private String titulo;

    @Column(name = "id_categoria", nullable = false)
    private Integer idCategoria;

    @Column(name = "tipo", length = 1)
    private String tipo;

    @Column(name = "anio_lanzamiento")
    private Integer anioLanzamiento;

    @Column(name = "duracion_minutos")
    private Integer duracionMinutos;

    @Column(name = "temporadas")
    private Integer temporadas;

    @Column(name = "clasificacion", length = 10)
    private String clasificacion;

    @Column(name = "estado")
    private Boolean estado;

    // Muchos contenidos pertenecen a una categoria
    @ManyToOne
    @JoinColumn(name = "id_categoria", insertable = false, updatable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "contenido", cascade = CascadeType.ALL)
    private List<SuscripcionContenido> visualizaciones = new ArrayList<>();

    public Contenido() {
    }

    public Integer getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(Integer idContenido) {
        this.idContenido = idContenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(Integer anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public Integer getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(Integer duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public Integer getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(Integer temporadas) {
        this.temporadas = temporadas;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<SuscripcionContenido> getVisualizaciones() {
        return visualizaciones;
    }

    public void setVisualizaciones(List<SuscripcionContenido> visualizaciones) {
        this.visualizaciones = visualizaciones;
    }
}