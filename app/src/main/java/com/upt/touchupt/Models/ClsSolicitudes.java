package com.upt.touchupt.Models;

/**
 * Created by SERGIO on 24/11/2017.
 */

public class ClsSolicitudes {

    private Integer IdSolicitud;
    private Integer IdUsuario;
    private Integer IdUbicacion;
    private Integer IdServicio;
    private String NivelSolicitud;
    private String EstadoSolicitud;
    private String FechaSolicitud;

    public ClsSolicitudes() {
    }

    public ClsSolicitudes(Integer idSolicitud, Integer idUsuario, Integer idUbicacion, Integer idServicio, String nivelSolicitud, String estadoSolicitud, String fechaSolicitud) {
        IdSolicitud = idSolicitud;
        IdUsuario = idUsuario;
        IdUbicacion = idUbicacion;
        IdServicio = idServicio;
        NivelSolicitud = nivelSolicitud;
        EstadoSolicitud = estadoSolicitud;
        FechaSolicitud = fechaSolicitud;
    }

    public Integer getIdSolicitud() {
        return IdSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        IdSolicitud = idSolicitud;
    }

    public Integer getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        IdUsuario = idUsuario;
    }

    public Integer getIdUbicacion() {
        return IdUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        IdUbicacion = idUbicacion;
    }

    public Integer getIdServicio() {
        return IdServicio;
    }

    public void setIdServicio(Integer idServicio) {
        IdServicio = idServicio;
    }

    public String getNivelSolicitud() {
        return NivelSolicitud;
    }

    public void setNivelSolicitud(String nivelSolicitud) {
        NivelSolicitud = nivelSolicitud;
    }

    public String getEstadoSolicitud() {
        return EstadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        EstadoSolicitud = estadoSolicitud;
    }

    public String getFechaSolicitud() {
        return FechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        FechaSolicitud = fechaSolicitud;
    }
}
