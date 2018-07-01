package com.appaloossa.honorgame.model;

import java.util.List;

/**
 * Created by Ines on 30/3/2018.
 */

public class User {
    public final static String modelName = "users";

    private String cbu;
    private String ciudad;
    private Coordenadas coordenadas;
    private String dni;
    private String mail;
    private Integer sexoFutbol;
    private String username;
    private Integer usuarioTipo;

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getSexoFutbol() {
        return sexoFutbol;
    }

    public void setSexoFutbol(Integer sexoFutbol) {
        this.sexoFutbol = sexoFutbol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUsuarioTipo() {
        return usuarioTipo;
    }

    public void setUsuarioTipo(Integer usuarioTipo) {
        this.usuarioTipo = usuarioTipo;
    }
}
