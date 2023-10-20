package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class InicioSesion extends AppCompatActivity implements View.OnClickListener {


    DAOUsuarios TodosLosUsuarios;

    TextView nombreUsuario;
    String textoNombreUsuario;
    TextView contraseñaUsuario;
    String textoContraseñaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciosesion);
        Button botonInicioSesion = findViewById(R.id.btnIniciarSesion);
        botonInicioSesion.setOnClickListener(this);
        nombreUsuario = findViewById(R.id.txfieldUsuario);
        contraseñaUsuario = findViewById(R.id.txtFieldContraseña);
        TodosLosUsuarios = new DAOUsuarios();

    }


    //Comprueba si ese nombre de usuario ya está registrado.
    public boolean comprobarNombreUsuarioRegistrado(String nombreUsuarioAcomprobar){
        boolean exito = false;
        ArrayList<Usuario> copiaUsuarios = TodosLosUsuarios.obtenerUsuarios();
        for (Usuario usuario : copiaUsuarios) {
            String nombreUsuario = usuario.getNombre();
            //Si el nombre de usuario está registrado cambia el exito a true.
            if (nombreUsuario.equals(nombreUsuarioAcomprobar)){
                exito = true;
            }
        }
        return exito;
    }

    //Comprueba si el nombre de usuario y la contraseña introducida está registrada
    public boolean validarInicioSesion(String contraseñaUsuarioAcomprobar) {
        boolean exito = false;
        ArrayList<Usuario> copiaUsuarios = TodosLosUsuarios.obtenerUsuarios();

        for (Usuario usuario : copiaUsuarios) {
            String contraseñaUsuario = usuario.getContraseña();
            //Si la contraseña es correcto cambia el exito a true.
            if ( contraseñaUsuario.equals(contraseñaUsuarioAcomprobar)){
                exito = true;
            }
        }
        return exito;
    }

    //Inicia sesión.
    public void InicioDeSesion(String nombreUsuarioAIniciar, String contraseñaUsuarioAIniciar){

        //Primero comprueba si el usuario está registrado y si es así, comprueba si contraseña.
        if (comprobarNombreUsuarioRegistrado(nombreUsuarioAIniciar)){
            //Si la contraseña es correcta inicia sesión.
            if (validarInicioSesion( contraseñaUsuarioAIniciar)) {
                Intent intent = new Intent(InicioSesion.this, Main.class);
                startActivity(intent);
            }//else{
                //CONTRASEÑA INCORRECTA;
            //}
        }//else{
            //MOSTRAR ERROR DE USUARIO NO REGISTRADO;
        //}

    }

    public void Registro(String nombreUsuarioARegistrar, String contraseñaUsuarioARegistrar){

        //Si el nombre de usuario no esta ya registrado, lo registra.
        if (!comprobarNombreUsuarioRegistrado(nombreUsuarioARegistrar)){
            TodosLosUsuarios.addUser(nombreUsuarioARegistrar,contraseñaUsuarioARegistrar);
            InicioDeSesion(nombreUsuarioARegistrar,contraseñaUsuarioARegistrar);
        }//else{
            //MOSTRAR ERROR USUARIO YA REGISTRADO.
        //}
    }


    @Override
    public void onClick(View view) {

        //nombre de usuario introducido toString.
        textoNombreUsuario = nombreUsuario.getText().toString();
        //contraseña de usuario introducido toString.
        textoContraseñaUsuario= contraseñaUsuario.getText().toString();
        if (view.getId() == R.id.btnIniciarSesion) {
            InicioDeSesion(textoNombreUsuario,textoContraseñaUsuario);
        }else if(view.getId() == R.id.btnRegistro){
            Registro(textoNombreUsuario,textoContraseñaUsuario);
        }


    }




}