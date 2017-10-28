package com.upt.touchupt.Models;

import android.graphics.Bitmap;

/**
 * Created by SERGIO on 22/10/2017.
 */

public class ClsUsuarios {

    private Integer id;
    private String contrasena;
    private String nombre;
    private String estado;
    private String email;
    private String DataFoto;
    private Bitmap foto = null;
    private String nivel;
    private String celular;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contraseña) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
}
