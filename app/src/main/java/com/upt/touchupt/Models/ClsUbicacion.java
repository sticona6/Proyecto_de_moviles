package com.upt.touchupt.Models;

/**
 * Created by SERGIO on 22/10/2017.
 */

public class ClsUbicacion {

    private Integer IdUbicacion;
    private Integer IdUsuario;
    private String Latitud;
    private String Longitud;
    private String Ciudad;
    private String Direccion;

    public ClsUbicacion() {
    }

    public ClsUbicacion(Integer idUbicacion, Integer idUsuario, String latitud, String longitud, String ciudad, String direccion) {
        IdUbicacion = idUbicacion;
        IdUsuario = idUsuario;
        Latitud = latitud;
        Longitud = longitud;
        Ciudad = ciudad;
        Direccion = direccion;
    }

    public Integer getIdUbicacion() {
        return IdUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        IdUbicacion = idUbicacion;
    }

    public Integer getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String latitud) {
        Latitud = latitud;
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String longitud) {
        Longitud = longitud;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }
}
