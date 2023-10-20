package com.example.pizzeria;

import java.util.Objects;

public class Usuario {

    private static int ID = 000;

    int idUsuario;
    String nombre;
    String contraseña;
    public Usuario(String nombre, String contraseña){
        this.idUsuario = Usuario.ID ++;
        this.nombre = nombre;
        this.contraseña = contraseña;
    }


    public Usuario(){
        
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }


}
