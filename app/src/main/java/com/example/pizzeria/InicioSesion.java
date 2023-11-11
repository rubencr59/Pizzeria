package com.example.pizzeria;

import static com.example.pizzeria.Clases.BackgroundManager.getColorFondo;
import static com.example.pizzeria.Clases.BackgroundManager.getColorTexto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.pizzeria.Clases.Alertas;
import com.example.pizzeria.Clases.DAOUsuarios;
import com.example.pizzeria.Clases.Usuario;

import java.util.ArrayList;

public class InicioSesion extends AppCompatActivity implements View.OnClickListener {
    DAOUsuarios TodosLosUsuarios;
    ConstraintLayout base;
    TextView txtInicioSesion;
    TextView nombreUsuario;
    String textoNombreUsuario;
    TextView contraseñaUsuario;
    String textoContraseñaUsuario;
    SharedPreferences sharedPreferences;
    String savedUsername;
    String savedPassword;
    Boolean checked;
    String colorFondo;
    String colorTexto;
    CheckBox txtRecuerdame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciosesion);
        //Botón de incio de sesión.
        Button botonInicioSesion = findViewById(R.id.btnIniciarSesion);
        botonInicioSesion.setOnClickListener(this);

        //Obtengo el txtfield del nombre de usuario y la contraseña.
        nombreUsuario = findViewById(R.id.txfieldUsuario);
        contraseñaUsuario = findViewById(R.id.txtFieldContraseña);

        //Instancio el DAO donde tengo guardado los usuarios.
        TodosLosUsuarios = new DAOUsuarios();


        //Obtiene las preferencias.
        sharedPreferences = getSharedPreferences("Preferencias", MODE_PRIVATE);

        //Obtiene el nombre de usuario y la contraseña si han sido guardadas (con el check Recuerdame).

        checked = Boolean.parseBoolean(sharedPreferences.getString("checked", ""));

        if (checked) {
            savedUsername = sharedPreferences.getString("nombreUsuario", "");
            savedPassword = sharedPreferences.getString("contraseñaUsuario", "");

            nombreUsuario.setText(savedUsername);
            contraseñaUsuario.setText(savedPassword);
        }else{
            nombreUsuario.setText("");
            contraseñaUsuario.setText("");
        }

        base = findViewById(R.id.fondoInicioSesion);
        txtInicioSesion = findViewById(R.id.txtInicioSesion);
        txtRecuerdame = findViewById(R.id.checkRecuerdame);

        colorFondo = getColorFondo(this);

        colorTexto = getColorTexto(this);

        if (colorFondo != "" && colorTexto !=""){
            base.setBackgroundColor(Integer.parseInt(colorFondo));
            nombreUsuario.setTextColor(Integer.parseInt(colorTexto));
            nombreUsuario.setHintTextColor(Integer.parseInt(colorTexto));
            contraseñaUsuario.setTextColor(Integer.parseInt(colorTexto));
            contraseñaUsuario.setHintTextColor(Integer.parseInt(colorTexto));

            txtInicioSesion.setTextColor(Integer.parseInt(colorTexto));
            txtRecuerdame.setTextColor(Integer.parseInt(colorTexto));

        }


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

                // Verificar si el CheckBox está marcado
                CheckBox checkBoxRecuerdame = findViewById(R.id.checkRecuerdame);

                SharedPreferences preferences = getSharedPreferences("Preferencias", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                if (checkBoxRecuerdame.isChecked()) {
                    // Guardar el nombre de usuario y contraseña en preferencias compartidas
                    editor.putString("nombreUsuario", nombreUsuarioAIniciar);
                    editor.putString("contraseñaUsuario", contraseñaUsuarioAIniciar);
                    editor.putString("checked", "true");
                }else{
                    editor.putString("checked", "false");

                }
                editor.apply();
                Intent intent = new Intent(this, Main.class);
                Usuario.setUsuarioActual(new Usuario(nombreUsuarioAIniciar, contraseñaUsuarioAIniciar));

                intent.putExtra("nombreUsuario", nombreUsuarioAIniciar);
                startActivity(intent);
                finish();
            }else{
                Alertas.crearDialogo(this,"Contraseña incorrecta", "Has introducido mal la contraseña.",false,null);
            }
        }else{
            Alertas.crearDialogo(this,"Usuario no registrado", "El usuario no está registrado.",false,null);

        }

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
        }else if(view.getId() == R.id.btnSalirFav){
            //Creo la función que quiero que haga.
            DialogInterface.OnClickListener confirmarClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                    finish();
                }
            };
            Alertas.crearDialogo(this, "Salir", "¿Seguro que quieres salir de la aplicación?", true,confirmarClickListener);
        }
    }
}