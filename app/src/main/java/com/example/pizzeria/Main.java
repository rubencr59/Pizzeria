package com.example.pizzeria;

import static com.example.pizzeria.Clases.BackgroundManager.getColorFondo;
import static com.example.pizzeria.Clases.BackgroundManager.getColorTexto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pizzeria.Clases.Alertas;

public class Main extends AppCompatActivity implements View.OnClickListener {

    ConstraintLayout fondoMain;
    TextView mensajeInicio;
    TextView txtNombreUsuario;
    String colorFondo;
    String colorTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button botonPedidos = findViewById(R.id.btnPedido);
        botonPedidos.setOnClickListener(this);
        Button botonSalir = findViewById(R.id.btnCerrarSesion);
        botonSalir.setOnClickListener(this);
        mensajeInicio = findViewById(R.id.txtMensajeInicioSesion);
        txtNombreUsuario = findViewById(R.id.txtNombreUsuario);

        //Obtengo el nombre de usuario introducido en la activity InicioSesion.
        Intent intent = getIntent();
        String nombreUsuario = intent.getStringExtra("nombreUsuario");
        txtNombreUsuario.setText(nombreUsuario);

        fondoMain = findViewById(R.id.fondoMain);

        colorFondo = getColorFondo(this);

        colorTexto = getColorTexto(this);

        if (colorFondo != "" && colorTexto !=""){
            fondoMain.setBackgroundColor(Integer.parseInt(colorFondo));
            mensajeInicio.setTextColor(Integer.parseInt(colorTexto));
            txtNombreUsuario.setTextColor(Integer.parseInt(colorTexto));
            }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCerrarSesion){
            DialogInterface.OnClickListener confirmarClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(v.getContext(), InicioSesion.class);

                    dialog.dismiss();
                    startActivity(intent);
                    finish();
                }
            };
            Alertas.crearDialogo(this, "Cerrar sesión", "¿Seguro que quieres cerrar sesión?", true,confirmarClickListener);
        }else if(v.getId() == R.id.btnPedido){

            Intent intent = new Intent(this, Pedidos.class);
            startActivity(intent);
            finish();
        }else if(v.getId() == R.id.btnConfiguracion){

            Intent intent = new Intent(this, Configuracion.class);
            startActivity(intent);
            finish();
        } else if (v.getId() == R.id.btnWeb) {
            Intent intent = new Intent(this, Web.class);
            startActivity(intent);
            finish();
        }
    }
}