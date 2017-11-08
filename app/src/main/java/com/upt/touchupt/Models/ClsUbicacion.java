package com.upt.touchupt.Models;

/**
 * Created by SERGIO on 22/10/2017.
 */

public class ClsUbicacion {

    private String IdUbicacion;
    private String Latitud;
    private String Longitud;
    private String IdUsuario;
    private String Ciudad;
    private String Direccion;

    public ClsUbicacion(String idUbicacion, String latitud, String longitud, String idUsuario, String ciudad, String direccion) {
        IdUbicacion = idUbicacion;
        Latitud = latitud;
        Longitud = longitud;
        IdUsuario = idUsuario;
        Ciudad = ciudad;
        Direccion = direccion;
    }

    public String getIdUbicacion() {
        return IdUbicacion;
    }

    public void setIdUbicacion(String idUbicacion) {
        IdUbicacion = idUbicacion;
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

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
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
