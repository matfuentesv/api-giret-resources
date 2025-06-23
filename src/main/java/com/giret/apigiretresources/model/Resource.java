package com.giret.apigiretresources.model;


public class Resource {

    private Long IdRecurso;
    private String nombre;
    private String descripcion;
    private String numeroSerie;
    private String fechaVencimientoGarantia;
    private String fechaCompra;
    private Long idEstado;
    private int idCategoria;

    public Long getIdRecurso() {
        return IdRecurso;
    }

    public void setIdRecurso(Long idRecurso) {
        IdRecurso = idRecurso;
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

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getFechaVencimientoGarantia() {
        return fechaVencimientoGarantia;
    }

    public void setFechaVencimientoGarantia(String fechaVencimientoGarantia) {
        this.fechaVencimientoGarantia = fechaVencimientoGarantia;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
