package com.upt.touchupt.Models;

import android.graphics.Bitmap;

/**
 * Created by SERGIO on 06/11/2017.
 */

public class ClsServicios {
    private String IdServicio;
    private String IdDetalleServicio;
    private String FechaServicio;
    private String NombreServicio;

    private String IdUsuario;
    private String nombre;
    private String email;
    private String DataFoto;
    private Bitmap foto = null;
    private String nivel;
    private String celular;

    private String IdUbicacion;
    private String Latitud;
    private String Longitud;
    private String Ciudad;
    private String Direccion;

    public String getNombreServicio() {
        return NombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        NombreServicio = nombreServicio;
    }

    public String getIdServicio() {
        return IdServicio;
    }

    public void setIdServicio(String idServicio) {
        IdServicio = idServicio;
    }

    public String getIdDetalleServicio() {
        return IdDetalleServicio;
    }

    public void setIdDetalleServicio(String idDetalleServicio) {
        IdDetalleServicio = idDetalleServicio;
    }

    public String getFechaServicio() {
        return FechaServicio;
    }

    public void setFechaServicio(String fechaServicio) {
        FechaServicio = fechaServicio;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataFoto() {
        return DataFoto;
    }

    public void setDataFoto(String dataFoto) {
        DataFoto = dataFoto;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
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
