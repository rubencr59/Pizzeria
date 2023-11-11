package com.example.pizzeria.Clases;

import java.util.ArrayList;

public class DAOUsuarios {

    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();


    public DAOUsuarios(){
        Usuario ruben = new Usuario("ruven", "12345");
        usuarios.add(ruben);
    }
    public ArrayList<Usuario> obtenerUsuarios() {
            return usuarios;
    }

    public void addUser(String usuario, String contraseña){
        usuarios.add(new Usuario(usuario, contraseña));
    }

}
