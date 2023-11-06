package com.example.pizzeria.Clases;

import java.util.ArrayList;

public class DAOUsuarios {

    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();


    public DAOUsuarios(){
        Usuario ruben = new Usuario("ruven", "12345");
        usuarios.add(ruben);
    }
    public ArrayList<Usuario> obtenerUsuarios() {
        ArrayList<Usuario> copiaUsuarios = new ArrayList<>();

        for (Usuario usuario : this.usuarios) {
            Usuario copiaUsuario = new Usuario();
            copiaUsuario.setNombre(usuario.getNombre());
            copiaUsuario.setContraseña(usuario.getContraseña());
            copiaUsuarios.add(copiaUsuario);
        }
        return copiaUsuarios;
    }

    public void addUser(String usuario, String contraseña){

        Usuario newUser = new Usuario(usuario, contraseña);
        usuarios.add(newUser);
    }

}
