package com.upt.touchupt.Models;

/**
 * Created by SERGIO on 06/11/2017.
 */

public class ClsServicios {

    private Integer IdServicio;
    private Integer IdUsuario;
    private String NombreServicio;
    private String NivelServicio;
    private String FechaServicio;

    public ClsServicios() {
    }

    public ClsServicios(Integer idServicio, Integer idUsuario, String nombreServicio, String nivelServicio, String fechaServicio) {
        IdServicio = idServicio;
        IdUsuario = idUsuario;
        NombreServicio = nombreServicio;
        NivelServicio = nivelServicio;
        FechaServicio = fechaServicio;
    }

    public Integer getIdServicio() {
        return IdServicio;
    }

    public void setIdServicio(Integer idServicio) {
        IdServicio = idServicio;
    }

    public Integer getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getNombreServicio() {
        return NombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        NombreServicio = nombreServicio;
    }

    public String getNivelServicio() {
        return NivelServicio;
    }

    public void setNivelServicio(String nivelServicio) {
        NivelServicio = nivelServicio;
    }

    public String getFechaServicio() {
        return FechaServicio;
    }

    public void setFechaServicio(String fechaServicio) {
        FechaServicio = fechaServicio;
    }
}
